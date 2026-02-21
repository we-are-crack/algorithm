import java.util.*
import kotlin.math.max
import kotlin.math.min

private class MyArray(initArray: Array<IntArray>) {
    private val array = Array(100) { IntArray(100) }
    var rSize = 3 // 행의 갯수 (y)
        private set
    var cSize = 3 // 열의 갯수 (x)
        private set

    init {
        for (i in 0..<rSize) {
            for (j in 0..<cSize) {
                array[i][j] = initArray[i][j]
            }
        }
    }

    operator fun get(index: Int) = array[index]

    // 모든 열에 대해 정렬
    fun sortC() {
        var newRSize = 0
        for (x in 0..<cSize) {
            val map = mutableMapOf<Int, Int>()
            for (y in 0..<rSize) {
                val value = array[y][x]
                if (value == 0) {
                    continue
                }

                array[y][x] = 0
                map[value] = map.getOrDefault(value, 0) + 1
            }

            val sorted = sort(map)
            newRSize = max(newRSize, sorted.size)

            // 배열에 재배치
            for ((y, value) in sorted.withIndex()) {
                array[y][x] = value
            }
        }

        rSize = newRSize
    }

    // 모든 행에 대해 정렬
    fun sortR() {
        var newCSize = 0
        for (y in array.indices) {
            val map = mutableMapOf<Int, Int>()
            for (x in 0..<cSize) {
                val value = array[y][x]
                if (value == 0) continue

                array[y][x] = 0
                map[value] = map.getOrDefault(value, 0) + 1
            }

            val sorted = sort(map)
            newCSize = max(newCSize, sorted.size)

            // 배열에 재배치
            for ((x, value) in sorted.withIndex()) {
                array[y][x] = value
            }
        }

        cSize = newCSize
    }

    private fun sort(map: Map<Int, Int>): List<Int> {
        val sorted = map.entries.sortedWith(compareBy({ it.value }, { it.key }))
        return mutableListOf<Int>().apply {
            val end = min(100, sorted.size)
            for (i in 0..<end) {
                this.add(sorted[i].key)
                this.add(sorted[i].value)
            }
        }
    }
}

fun main() {
    val st = StringTokenizer(readln())
    val r = st.nextToken().toInt() - 1
    val c = st.nextToken().toInt() - 1
    val k = st.nextToken().toInt()

    val initArray = Array(3) {
        val line = StringTokenizer(readln())
        IntArray(3) { line.nextToken().toInt() }
    }

    val array = MyArray(initArray)

    var answer = 0
    while (array[r][c] != k && answer <= 100) {
        answer++

        if (array.rSize >= array.cSize) array.sortR()
        else array.sortC()
    }

    if (answer > 100) print(-1) else print(answer)
}