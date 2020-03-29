package suffixarrays

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.expect
import org.junit.jupiter.api.Test

internal class SuffixArrays {

    private val text = "ABBABAABAA".toIntArray()

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
    fun `suffix array constructed with naive algorithm`() {
    }
}

