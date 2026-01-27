val dir = arrayOf(
    intArrayOf(-1, 0), // N
    intArrayOf(0, 1), // E
    intArrayOf(1, 0), // S
    intArrayOf(0, -1) // W
)

class Cleaner(var position: IntArray, var head: Int) {

    var cleanArea = 0

    fun clean() {
        cleanArea += 1
    }

    fun moveTo(r: Int, c: Int) {
        position[0] = r
        position[1] = c
    }
}

/**
 * 4방향에 청소되지 않은 빈 칸이 존재하는지 확인
 *
 * 청소되지 않은 빈 칸이 존재하면 `true` 리턴
 * 존재하지 않으면 `false` 리턴
 */
fun Cleaner.scan(map: Array<IntArray>): Boolean {
    for (d in dir) {
        val nr = position[0] + d[0]
        val nc = position[1] + d[1]

        if (map[nr][nc] == 0) {
            return true
        }
    }

    return false
}

fun Cleaner.operate(map: Array<IntArray>) {
    while (true) {
        val (r, c) = position

        if (map[r][c] == 0) {
            clean()
            map[r][c] = -1
        }

        if (scan(map)) {
            head = (head + 3) % 4

            val nr = r + dir[head][0]
            val nc = c + dir[head][1]
            if (map[nr][nc] == 0) {
                moveTo(nr, nc)
            }
        } else {
            val bd = (head + 2) % 4
            val br = r + dir[bd][0]
            val bc = c + dir[bd][1]

            if (map[br][bc] == 1) {
                return
            }

            moveTo(br, bc)
        }
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val (r, c, d) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    val cleaner = Cleaner(intArrayOf(r, c), d)
    cleaner.operate(map)
    print(cleaner.cleanArea)
}