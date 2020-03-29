package suffixarrays

import kotlin.math.min

/** Time complexity: O(n^2log(2)) */
class NaiveSuffixArrayAlgorithm : SuffixArrayAlgorithm {

    inner class Suffix(
        private val text: IntArray,
        val index: Int
    ) : Comparable<Suffix> {

        private val len = text.size - index

        private fun getAt(current: Int) = text[index + current]

        override fun compareTo(other: Suffix): Int {
            if (this == other) return 0
            val minLength = min(len, other.len)
            for (i in (0.until(minLength))) {
                if (getAt(i) < other.getAt(i)) {
                    return -1
                }
                if (getAt(i) > other.getAt(i)) {
                    return 1
                }
            }
            return len - other.len
        }

        override fun toString(): String = String(text, index, len)
    }

    override fun sa(text: IntArray): IntArray {
        val sa = IntArray(text.size)
        val suffixes = Array(text.size) { Suffix(text, it) }
        suffixes.sort()
        0.until(text.size).forEach { sa[it] = suffixes[it].index }
        return sa
    }
}
