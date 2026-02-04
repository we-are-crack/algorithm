import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

private enum class Direction(val r: Int, val c: Int) {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1),
    ;
}

private data class Pos(val r: Int, val c: Int) {
    operator fun plus(dir: Direction) = Pos(r + dir.r, c + dir.c)
}

private class Tetronomino(val n: Int, val m: Int, val grid: Array<IntArray>) {

    var answer = 0
        private set

    private val elementMaxValue = grid.maxOf { it.max() }

    fun solve(): Int {
        answer = 0

        val visited = Array(n) { BooleanArray(m) }
        for (r in 0..<n) {
            for (c in 0..<m) {
                visited[r][c] = true
                make(Pos(r, c), grid[r][c], 1, visited)
                visited[r][c] = false
            }
        }

        return answer
    }

    private fun make(cur: Pos, sum: Int, depth: Int, visited: Array<BooleanArray>) {
        if (depth == 4) {
            answer = max(answer, sum)
            return
        }

        if (sum + (4 - depth) * elementMaxValue <= answer) {
            return
        }

        for (dir in Direction.entries) {
            val nxt = cur + dir
            if (isInBounds(nxt) && !visited[nxt.r][nxt.c]) {
                val nxtSum = sum + grid[nxt.r][nxt.c]

                if (depth == 2) {
                    visited[nxt.r][nxt.c] = true
                    make(cur, nxtSum, depth + 1, visited)
                    visited[nxt.r][nxt.c] = false
                }

                visited[nxt.r][nxt.c] = true
                make(nxt, nxtSum, depth + 1, visited)
                visited[nxt.r][nxt.c] = false
            }
        }
    }

    private fun isInBounds(pos: Pos) = pos.r in grid.indices && pos.c in grid[0].indices
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val grid = Array(n) {
        val line = StringTokenizer(br.readLine())
        IntArray(m) { line.nextToken().toInt() }
    }

    print(Tetronomino(n, m, grid).solve())
}