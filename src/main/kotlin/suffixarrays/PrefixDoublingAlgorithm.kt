package suffixarrays

import java.util.Arrays
import kotlin.math.max

/**
 * Based on https://github.com/williamfiset/Algorithms/blob/d5d31d272091541250a11ec3223b82577cd83f50/src/main/java/com/williamfiset/algorithms/datastructures/suffixarray/SuffixArrayFast.java
 *
 * Time Complexity: O(nlog(n))
 * */
class PrefixDoublingAlgorithm(
    private var initAlphabetSize: Int = DEFAULT_ALPHABET_SIZE
) : SuffixArrayAlgorithm {

    override fun sa(text: IntArray): IntArray {
        var alphabetSize = initAlphabetSize
        val sa = IntArray(text.size)
        var sa2 = IntArray(text.size)
        var rank = IntArray(text.size)
        val c = IntArray(max(alphabetSize, text.size))

        var tmp: IntArray?

        0.until(text.size).forEach {
            rank[it] = text[it]
            c[text[it]]++
        }

        1.until(alphabetSize).forEach { c[it] += c[it - 1] }

        (text.size - 1).downTo(0)
            .forEach { sa[--c[text[it]]] = it }

        var p = 1

        while (p < text.size) {

            var r = 0
            (text.size - p).until(text.size)
                .forEach { sa2[r++] = it }

            0.until(text.size)
                .asSequence()
                .filter { sa[it] >= p }
                .forEach { sa2[r++] = sa[it] - p }

            Arrays.fill(c, 0, alphabetSize, 0)

            0.until(text.size)
                .forEach { c[rank[it]]++ }
            1.until(alphabetSize)
                .forEach { c[it] += c[it - 1] }
            (text.size - 1).downTo(0)
                .forEach { sa[--c[rank[sa2[it]]]] = sa2[it] }

            sa2[sa[0]] = 0
            r = 0
            1.until(text.size).forEach {
                if (
                    !(rank[sa[it - 1]] == rank[sa[it]] &&
                        sa[it - 1] + p < text.size &&
                        sa[it] + p < text.size &&
                        rank[sa[it - 1] + p] == rank[sa[it] + p])
                ) {
                    r++
                }
                sa2[sa[it]] = r
            }

            tmp = rank
            rank = sa2
            sa2 = tmp

            if (r == text.size - 1) {
                break
            }
            alphabetSize = r + 1

            p = p.shl(1)
        }

        return sa
    }
}

const val DEFAULT_ALPHABET_SIZE = 256
