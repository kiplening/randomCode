class Solution2426 {
    fun numberOfPairs(nums1: IntArray, nums2: IntArray, diff: Int): Long {
        var n = nums1.size
        //nums1[i] - nums2[i] <= nums1[j] - nums2[j] + diff
        var diffArray = IntArray(n)
        for(i in 0..<n) {
            diffArray[i] = nums1[i] - nums2[i]
        }
        var ret = 0L
        diffArray.sort()
        var startPos = 1
        for(i in 0..<n) {
            var pos = diffArray.binarySearch((diffArray[i]-diff), fromIndex = startPos, toIndex = n-1)
            if(pos > 0) {
                ret += n-pos
            } else {
                ret += n + pos + 1
                pos = -pos - 1
                if(pos >= n) {
                    break
                }
            }
            startPos = pos
        }

        return ret
    }
}