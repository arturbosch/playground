package suffixarrays

import io.gitlab.arturbosch.kutils.measureAndReturn
import org.junit.jupiter.api.Test

internal class SAPerformance {

    private val text = "abaababbabbbsadasddssadnasbdasbdbasndasndnasdbna".toIntArray()

    @Test
    fun performance() {
        run {
            val (time, sa) = measureAndReturn { NaiveSuffixArrayAlgorithm().sa(text) }
            println("Naive algorithm")
            println(sa.contentToString())
            println("time: $time ms")
        }

        run {
            val (time, sa) = measureAndReturn { PrefixDoublingAlgorithm().sa(text) }
            println("PrefixDoubling algorithm")
            println(sa.contentToString())
            println("time: $time ms")
        }

        run {
            val (time, sa) = measureAndReturn {
                DefaultSuffixArray(
                    text,
                    NaiveSuffixArrayAlgorithm(),
                    KasaiAlgorithm()
                )
            }
            println("Naive algorithm")
            println(sa)
            println("time: $time ms")
        }

        run {
            val (time, sa) = measureAndReturn {
                DefaultSuffixArray(
                    text,
                    PrefixDoublingAlgorithm(),
                    KasaiAlgorithm()
                )
            }
            println("PrefixDoubling algorithm")
            println(sa)
            println("time: $time ms")
        }
    }
}
