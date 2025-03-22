class Solution2818 {
    fun maximumScore(nums: List<Int>, k: Int): Int {
        var n = nums.size
        val primeScore = Array<Int>(n){0}

        nums.forEachIndexed { index, num ->
            primeScore[index] = countDistinctPrimeFactors(num)
        }

        val prefix = Array<Int>(n) {0}
        val suffix = Array<Int>(n) {0}
        // how much postion can be included before i
        for (i in 0 until n) {
            if (i == 0) {
                prefix[i] = 0
                continue
            }
            var index = i-1
            while(index >= 0 && primeScore[i] > primeScore[index]) {
                index = index - prefix[index]
                if(prefix[index] == 0) index --
            }
            if (index <= -1) {
                prefix[i] = i
            } else {
                prefix[i] = i - index - 1
            }
        }
        // how much postion can be included after i
        for (i in n-1 downTo 0) {
            if (i == n-1) {
                suffix[i] = 0
                continue
            }
            var index = i+1
            while(index < n && primeScore[i] >= primeScore[index]) {
                index = index + suffix[index]
                if(suffix[index] == 0) index ++
            }
            if (index >= n) {
                suffix[i] = n - i - 1
            } else {
                suffix[i] = index - i - 1
            }
        }

        val sortedIndices = nums.indices.sortedByDescending { nums[it] }

        var operateCount = 0
        var ans = 1L
        val limitation = 1000000007

        for (index in sortedIndices) {
            var numOfSubArray = 1
            if (prefix[index] != 0 ) {
                numOfSubArray *= prefix[index] + 1
            }
            if (suffix[index] != 0) {
                numOfSubArray *= suffix[index] + 1
            }
            for (i in 0 until numOfSubArray) {
                ans = ans * nums[index]
                ans = ans % limitation
                operateCount ++
                if (operateCount >= k) {
                    return ans.toInt()
                }
            }
        }

        return ans.toInt()

    }

    fun countDistinctPrimeFactors(n: Int): Int {
        var num = n
        var count = 0
        var i = 2

        while (i * i <= num) {
            if (num % i == 0) {
                count++ // Count the prime factor once
                while (num % i == 0) {
                    num /= i // Remove all occurrences of this factor
                }
            }
            i++
        }

        if (num > 1) { // If there's a prime factor left
            count++
        }
        return count
    }
}