import java.util.LinkedList

class Solution2121 {
    fun getDistances(arr: IntArray): LongArray {
        val n = arr.size
        val valueMap = HashMap<Int, LinkedList<LongArray>>()
        for (i in 0..<n) {
            if (!valueMap.contains(arr[i])) {
                valueMap[arr[i]] = LinkedList<LongArray>()
            }
            val temp = valueMap[arr[i]]
            var distance = 0L
            if (temp != null) {
                for(index in temp) {
                    index[1] += (i.toLong() - index[0])
                    distance += (i.toLong() - index[0])
                }
            }
            temp?.add(longArrayOf(i.toLong(), distance.toLong()))
        }
        val ret = LongArray(n)
        valueMap.forEach { entry ->
            entry.value.forEach { item ->
                ret[item[0].toInt()] = item[1]
            }
        }
        return ret
    }
}