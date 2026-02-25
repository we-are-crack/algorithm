import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() {
    val n = readln().toInt()
    val map = Array(n) {
        val line = StringTokenizer(readln())
        IntArray(n) {
            line.nextToken().toInt()
        }
    }

    print(getMinDiff(map))
}

private fun getMinDiff(arr: Array<IntArray>): Int {
    val n = arr.size
    val acc = accumulate(arr)
    val total = acc.sumOf { it[it.lastIndex] }
    val funs = arrayOf<(Array<IntArray>, Int, Int, Int, Int) -> Int>(
        ::getSection1, ::getSection2, ::getSection3, ::getSection4
    )

    var answer = total
    for (y in 0..<n - 2) {
        for (x in 1..<n - 1) {
            for (d1 in 1..x) {
                for (d2 in 1..<n - x) {
                    if (y + d1 + d2 >= n) {
                        continue
                    }

                    var max = 0
                    var min = total
                    var s5 = total // 5구역
                    for (f in funs) { // 1~4구역 계산
                        val p = f(acc, y, x, d1, d2)
                        max = max(max, p)
                        min = min(min, p)
                        s5 -= p
                    }

                    max = max(max, s5)
                    min = min(min, s5)

                    answer = min(answer, max - min)
                }
            }
        }
    }

    return answer
}

private fun accumulate(arr: Array<IntArray>): Array<IntArray> {
    val n = arr.size
    return Array(n) { r ->
        var acc = 0
        IntArray(n) { c ->
            acc += arr[r][c]
            acc
        }
    }
}

// [0 <= r < y && 0 <= c <= x] + [y <= r < y+d1 && 0 <= c <= x-(1..d1)]
private fun getSection1(acc: Array<IntArray>, y: Int, x: Int, d1: Int, d2: Int): Int {
    var sum = 0
    var temp = 0
    for (r in 0..<y + d1) {
        if (y <= r) {
            temp++
        }

        sum += acc[r][x - temp]
    }

    return sum
}

// [0 <= r < y && x < c < N] + [y <= r <= y+d2 && x+(1..d2+1) < c < N]
private fun getSection2(acc: Array<IntArray>, y: Int, x: Int, d1: Int, d2: Int): Int {
    val lastIndex = acc[0].lastIndex
    var sum = 0
    var temp = 0

    for (r in 0..<y + d2 + 1) {
        sum += acc[r][lastIndex] - acc[r][x + temp]
        if (y <= r) {
            temp++
        }
    }

    return sum
}

// [y+d1 <= r <= y+d1+d2 && 0 <= c < x-d1+(0..d2)] + [y+d1+d2 < r < N && 0 <= c < x-d1+d2]
private fun getSection3(acc: Array<IntArray>, y: Int, x: Int, d1: Int, d2: Int): Int {
    val lastIndex = acc.lastIndex
    var sum = 0
    var temp = 0

    for (r in y + d1..lastIndex) {
        if (0 <= x - d1 - 1 + temp) {
            sum += acc[r][x - d1 - 1 + temp]
        }

        if (r < y + d1 + d2) {
            temp++
        }
    }

    return sum
}

// [y+d2 < r <= y+d1+d2 && x+d2-(1..d2) < c < N] + [y+d1+d2 < r < N && x-d1+d2 <= c < N]
private fun getSection4(acc: Array<IntArray>, y: Int, x: Int, d1: Int, d2: Int): Int {
    val lastIndex = acc.lastIndex
    var sum = 0
    var temp = 0

    for (r in y + d2 + 1..lastIndex) {
        sum += acc[r][lastIndex] - acc[r][x - temp + d2 - 1]
        if (r <= y + d1 + d2) {
            temp++
        }
    }

    return sum
}