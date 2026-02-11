import java.util.StringTokenizer

private data class Pos(val y: Int, val x: Int)

private val direction = arrayOf(
    Pos(0, 1),
    Pos(-1, 0),
    Pos(0, -1),
    Pos(1, 0)
)

fun main() {
    val size = 100
    val map = Array(size + 1) { BooleanArray(size + 1) }
    val n = readln().toInt()
    repeat(n) {
        val line = StringTokenizer(readln())
        val x = line.nextToken().toInt()
        val y = line.nextToken().toInt()
        val d = line.nextToken().toInt()
        val g = line.nextToken().toInt()

        val pos = mutableListOf(Pos(y, x), Pos(y + direction[d].y, x + direction[d].x))
        repeat(g) { dragonCurve(pos) }

        for (p in pos) {
            map[p.y][p.x] = true
        }
    }

    var answer = 0
    for (y in 0..<size) {
        for (x in 0..<size) {
            if (isRectangle(map, y, x)) {
                answer++
            }
        }
    }

    print(answer)
}

private fun dragonCurve(pos: MutableList<Pos>) {
    val lastIndex = pos.lastIndex
    val (dy, dx) = pos[lastIndex]
    for (i in lastIndex - 1 downTo 0) {
        val (y, x) = pos[i]
        val ny = dy + (x - dx)
        val nx = dx + (dy - y)
        pos.add(Pos(ny, nx))
    }
}

// (y, x)가 사각형의 왼쪽 위 좌표를 기준으로 사각형이 되는지 확인
private fun isRectangle(map: Array<BooleanArray>, y: Int, x: Int): Boolean {
    val rectangle = arrayOf(Pos(0, 1), Pos(1, 0), Pos(1, 1))
    if (!map[y][x]) return false
    for (rect in rectangle) {
        val ny = y + rect.y
        val nx = x + rect.x

        if (!map[ny][nx]) {
            return false
        }
    }

    return true
}