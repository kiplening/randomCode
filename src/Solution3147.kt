class Solution3147 {
    fun maximumEnergy(energy: IntArray, k: Int): Int {
        if (energy.isEmpty()) return 0
        val n = energy.size
        val ret = IntArray(k)

        for (pos in 0 until n step k) {
            for (j in 0 until k) {
                if (pos + j >= n) break
                
                if (pos + k + j < n && ret[j] + energy[pos + j] < 0) {
                    ret[j] = 0
                } else {
                    ret[j] += energy[pos + j]
                }
            }
        }

        return ret.maxOrNull() ?: Int.MIN_VALUE
    }
}