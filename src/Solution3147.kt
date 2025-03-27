class Solution3147 {
    fun maximumEnergy(energy: IntArray, k: Int): Int {
        val n = energy.size
        val ret = IntArray(k)

        var pos = 0
        while(true) {
            if(pos >= n) {
                break
            }
            for(j in 0..<k) {
                if(pos + j >= n) {
                    break
                }
                var lastRound = pos+k+j >= n
                if(!lastRound && ret[j] + energy[pos+j] < 0) {
                    ret[j] = 0
                } else {
                    ret[j] = ret[j] + energy[pos+j]
                }
            }
            pos += k
        }

        var max = Int.MIN_VALUE
        for(j in 0..<k) {
            max = Math.max(max, ret[j])
        }
        return max
    }
}