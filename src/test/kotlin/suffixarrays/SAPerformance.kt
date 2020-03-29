package suffixarrays

import io.gitlab.arturbosch.kutils.measureAndReturn
import org.junit.jupiter.api.Test

internal class SAPerformance {

    private val text = "ABBABAABAA".toIntArray()

    @Test
    fun performance() {
        val (time, sa) = measureAndReturn { NaiveSuffixArrayAlgorithm().sa(text) }
        println("Naive algorithm")
        println(sa.contentToString())
        println("time: $time ms")
    }
}
