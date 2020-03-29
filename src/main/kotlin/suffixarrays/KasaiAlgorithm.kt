package suffixarrays

/**
 * https://github.com/williamfiset/Algorithms/blob/d5d31d272091541250a11ec3223b82577cd83f50/src/main/java/com/williamfiset/algorithms/datastructures/suffixarray/SuffixArray.java
 *
 * http://www.mi.fu-berlin.de/wiki/pub/ABI/RnaSeqP4/suffix-array.pdf
 */
class KasaiAlgorithm : LongestCommonPrefixAlgorithm {

    override fun lcp(text: IntArray, sortedSA: IntArray): IntArray {
        val lcp = IntArray(text.size)
        val inv = IntArray(text.size)
        0.until(text.size).forEach { inv[sortedSA[it]] = it }
        var height = 0
        for (i in 0.until(text.size)) {
            if (inv[i] > 0) {
                val k = sortedSA[inv[i] - 1]
                while (
                    (i + height < text.size) &&
                    (k + height < text.size) &&
                    text[i + height] == text[k + height]
                ) {
                    height++
                }
                lcp[inv[i]] = height
                if (height > 0) {
                    height--
                }
            }
        }
        return lcp
    }
}
