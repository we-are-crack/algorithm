import java.util.StringTokenizer
import kotlin.math.min

private var n = 0
private var emptyCell = 0 // 벽이 아닌 모든 칸의 수
private val directions = arrayOf(
    Pos(-1, 0),
    Pos(0, 1),
    Pos(1, 0),
    Pos(0, -1)
)

fun main() {
    val st = StringTokenizer(readln())
    n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val candidates = mutableListOf<Pos>() // 활성 후보
    val map = Array(n) { r ->
        val line = StringTokenizer(readln())
        IntArray(n) { c ->
            val state = line.nextToken().toInt()
            if (state == 0) emptyCell++
            if (state == 2) candidates.add(Pos(r, c))

            state
        }
    }

    val result = if (emptyCell == 0) 0 else dfs(map, candidates, 0, Array(m) { Pos(-1, -1) }, 0)
    print(if (result == Int.MAX_VALUE) -1 else result)
}

private fun dfs(map: Array<IntArray>, candidates: List<Pos>, i: Int, sel: Array<Pos>, selCnt: Int): Int {
    if (selCnt == sel.size) return bfs(map, sel)
    if (i == candidates.size) return Int.MAX_VALUE

    var ret = Int.MAX_VALUE

    sel[selCnt] = candidates[i]
    ret = min(ret, dfs(map, candidates, i + 1, sel, selCnt + 1))
    ret = min(ret, dfs(map, candidates, i + 1, sel, selCnt))

    return ret
}

private fun bfs(map: Array<IntArray>, starts: Array<Pos>): Int {
    val que = ArrayDeque<Pair<Pos, Int>>()
    val visited = Array(n) { BooleanArray(n) }
    starts.forEach {
        que.addLast(it to 0)
        visited[it] = true
    }

    var time = 0
    var fill = 0 // 바이러스로 채운 칸의 수
    while (que.isNotEmpty()) {
        val (cPos, cTime) = que.removeFirst()

        if (map[cPos] == 0) {
            time = cTime
            fill++
        }

        for (dir in directions) {
            val nPos = cPos + dir
            if (nPos.isValid() && map[nPos] != 1 && !visited[nPos]) {
                que.addLast(nPos to (cTime + 1))
                visited[nPos] = true
            }
        }
    }

    return if (fill == emptyCell) time else Int.MAX_VALUE
}

private data class Pos(val r: Int, val c: Int)

private operator fun Pos.plus(pos: Pos) = Pos(r + pos.r, c + pos.c)
private fun Pos.isValid() = r in 0..<n && c in 0..<n

private operator fun Array<IntArray>.get(pos: Pos) = this[pos.r][pos.c]
private operator fun Array<BooleanArray>.get(pos: Pos) = this[pos.r][pos.c]
private operator fun Array<BooleanArray>.set(pos: Pos, value: Boolean) {
    this[pos.r][pos.c] = value
}