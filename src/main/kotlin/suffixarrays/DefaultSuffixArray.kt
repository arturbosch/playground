package suffixarrays

class DefaultSuffixArray(
    override val text: IntArray,
    saAlgorithm: SuffixArrayAlgorithm,
    lcpAlgorithm: LongestCommonPrefixAlgorithm
) : SuffixArray {

    override val sortedSuffixArray: IntArray = saAlgorithm.sa(text)
    override val longestCommonPrefixes: IntArray = lcpAlgorithm.lcp(text, sortedSuffixArray)
}
