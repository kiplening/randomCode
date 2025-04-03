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
            temp?.add(longArrayOf(i.toLong(), 0))
        }

        valueMap.forEach { entry ->
            val temp = entry.value
            val prefix = LongArray(temp.size)
            var pos = 0
            temp.forEach { index ->
                if (pos == 0) {
                    prefix[pos] = 0
                } else {
                    prefix[pos] = prefix[pos - 1] + (index[0] - temp.get(pos - 1)[0])
                }
                pos++
            }

            val suffix = LongArray(temp.size)
            pos = temp.size - 1
            temp.forEach { index ->
                if (pos == temp.size - 1) {
                    suffix[pos] = 0
                } else {
                    suffix[pos] = suffix[pos + 1] + (index[0] - temp[pos + 1][0])
                }
                pos--
            }

            pos = 0
            temp.forEach { index ->
                index[1] = suffix[pos] + prefix[pos]
                pos++
            }
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