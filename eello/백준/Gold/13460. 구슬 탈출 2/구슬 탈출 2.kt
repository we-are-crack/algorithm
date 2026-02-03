import java.util.StringTokenizer
import kotlin.math.abs

private enum class Direction(val r: Int, val c: Int) {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1),
    ;
}

private data class Position(val r: Int, val c: Int) {
    fun distanceTo(other: Position) = abs(r - other.r) + abs(c - other.c)
}

private data class State(
    val red: Position,
    val blue: Position,
    val count: Int
)

private class MarbleEscape(val n: Int, val m: Int, val board: Array<CharArray>) {

    fun solve(red: Position, blue: Position): Int {
        val que = ArrayDeque<State>().apply { add(State(red, blue, 0)) }
        val visited = Array(n) { Array(m) { Array(n) { BooleanArray(m) } } }.apply {
            this[red.r][red.c][blue.r][blue.c] = true
        }

        while (que.isNotEmpty()) {
            val curState = que.removeFirst()

            if (curState.count >= 10) return -1

            for (dir in Direction.entries) {
                val nxtState = tiltAll(curState, dir)
                val (nRed, nBlue, nCount) = nxtState

                if (isInHole(nBlue)) continue
                if (isInHole(nRed)) return nCount

                if (visited[nRed.r][nRed.c][nBlue.r][nBlue.c]) continue
                visited[nRed.r][nRed.c][nBlue.r][nBlue.c] = true

                que.add(nxtState)
            }
        }

        return -1
    }

    private fun tiltAll(state: State, dir: Direction): State {
        val (red, blue, count) = state
        var nRed = tiltEach(red, dir)
        var nBlue = tiltEach(blue, dir)

        if (!isInHole(nRed) && nRed == nBlue) {
            val redDist = red.distanceTo(nRed)
            val blueDist = blue.distanceTo(nBlue)

            if (redDist < blueDist) {
                nBlue = Position(nBlue.r - dir.r, nBlue.c - dir.c)
            } else nRed = Position(nRed.r - dir.r, nRed.c - dir.c)
        }

        return State(nRed, nBlue, count + 1)
    }

    private fun tiltEach(cur: Position, dir: Direction): Position {
        var (r, c) = cur

        while (true) {
            val nr = r + dir.r
            val nc = c + dir.c

            if (board[nr][nc] == '#') return Position(r, c)
            if (board[nr][nc] == 'O') return Position(nr, nc)

            r = nr
            c = nc
        }
    }

    private fun isInHole(pos: Position) = board[pos.r][pos.c] == 'O'
}

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    var red = Position(0, 0)
    var blue = Position(0, 0)
    val board = Array(n) { r ->
        val line = readln().toCharArray()
        CharArray(m) { c ->
            when (line[c]) {
                'R' -> { red = Position(r, c); '.' }
                'B' -> { blue = Position(r, c); '.' }
                else -> line[c]
            }
        }
    }

    print(MarbleEscape(n, m, board).solve(red, blue))
}