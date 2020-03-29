package suffixarrays

class DefaultSuffixArray(
    override val text: IntArray,
    saAlgorithm: SuffixArrayAlgorithm,
    lcpAlgorithm: LongestCommonPrefixAlgorithm
) : SuffixArray {

    override val sortedSuffixArray: IntArray = saAlgorithm.sa(text)
    override val longestCommonPrefixes: IntArray = lcpAlgorithm.lcp(text, sortedSuffixArray)

    override fun lrs(): String {
        if (text.isEmpty()) {
            return ""
        }
        val (index, lcp) = longestCommonPrefixes.withIndex().maxBy { it.value }!!
        return buildString {
            sortedSuffixArray[index].until(sortedSuffixArray[index] + lcp)
                .forEach { append(text[it].toChar()) }
        }
    }

    override fun toString(): String = buildString(text.size) {
        append("------i------SA------LCP---Suffix\n")
        for (i in 0.until(text.size)) {
            val suffix = buildString {
                sortedSuffixArray[i].until(text.size).forEach {
                    append(text[it].toChar())
                }
            }
            append(String.format(
                "% 7d % 7d % 7d    %s\n",
                i,
                sortedSuffixArray[i],
                longestCommonPrefixes[i],
                suffix
            ))
        }
    }
}
