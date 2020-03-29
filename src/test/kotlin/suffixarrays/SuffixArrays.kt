package suffixarrays

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.expect
import org.junit.jupiter.api.Test

internal class SuffixArrays {

    val content = "ABBABAABAA"
    private val text = content.toIntArray()

    @Test
    fun `naive algorithm`() {
        val sa = NaiveSuffixArrayAlgorithm().sa(text)
        expect(sa.contentToString()).toBe("[9, 8, 5, 6, 3, 0, 7, 4, 2, 1]")
    }

    @Test
    fun `double prefix algorithm`() {
        val sa = PrefixDoublingAlgorithm().sa(text)
        expect(sa.contentToString()).toBe("[9, 8, 5, 6, 3, 0, 7, 4, 2, 1]")
    }

    @Test
    fun `kasai algorithm`() {
        val sa = intArrayOf(9, 8, 5, 6, 3, 0, 7, 4, 2, 1)
        val lcp = KasaiAlgorithm().lcp(text, sa)
        expect(lcp.contentToString()).toBe("[0, 1, 2, 1, 4, 2, 0, 3, 2, 1]")
    }

    @Test
    fun `suffix array`() {
        val sa = DefaultSuffixArray(
            text,
            PrefixDoublingAlgorithm(),
            KasaiAlgorithm()
        )

        println(sa)
        println(sa.lrs())
    }
}

