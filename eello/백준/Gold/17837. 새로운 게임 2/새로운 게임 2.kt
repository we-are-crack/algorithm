import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val board = Array(n) {
        val line = StringTokenizer(readln())
        IntArray(n) { line.nextToken().toInt() }
    }

    val pieces = Array(k) { id ->
        val line = StringTokenizer(readln())
        val r = line.nextToken().toInt() - 1
        val c = line.nextToken().toInt() - 1
        val d = line.nextToken().toInt() - 1
        Chess.Piece(id, r, c, Direction[d])
    }

    print(Chess(board, pieces).play())
}

private enum class Direction(val r: Int, val c: Int) {
    RIGHT(0, 1),
    LEFT(0, -1),
    UP(-1, 0),
    DOWN(1, 0),
    ;

    lateinit var reverse: Direction

    companion object {
        init {
            UP.reverse = DOWN
            DOWN.reverse = UP
            LEFT.reverse = RIGHT
            RIGHT.reverse = LEFT
        }

        operator fun get(ordinal: Int) = entries[ordinal]
    }
}

private class Chess(val board: Array<IntArray>, val pieces: Array<Piece>) {

    val n = board.size
    val state = Array(n) { Array(n) { ArrayDeque<Piece>() } }

    init {
        pieces.forEach { p -> state[p.r][p.c].addLast(p) }
    }

    fun play(): Int {
        for (turn in 1..1000) {
            for (piece in pieces) {
                if (move(piece) >= 4) {
                    return turn
                }
            }
        }

        return -1
    }

    // 이동한 칸 위에 있는 말의 개수 리턴
    private fun move(piece: Piece): Int {
        val temp = ArrayDeque<Piece>()
        var (nr, nc) = piece.getNextPos()

        if (!isInBounds(nr, nc) || board[nr][nc] == BLUE) {
            piece.reverseDirection()
            val (rnr, rnc) = piece.getNextPos()

            if (!isInBounds(rnr, rnc) || board[rnr][rnc] == BLUE) {
                // 양쪽 모두 파란색 혹은 한쪽 파란색 + 한쪽 범위 밖이면
                return -1
            }

            nr = rnr
            nc = rnc
        }

        while (state[piece.r][piece.c].isNotEmpty()) {
            val p = state[piece.r][piece.c].removeLast()
            temp.addFirst(p)

            if (p.id == piece.id) {
                break
            }
        }

        while (temp.isNotEmpty()) {
            val p = if (board[nr][nc] == RED) temp.removeLast() else temp.removeFirst()

            p.moveTo(nr, nc)
            state[nr][nc].addLast(p)
        }

        return state[nr][nc].size
    }

    private fun isInBounds(r: Int, c: Int) = r in 0..<n && c in 0..<n

    companion object {
        const val RED = 1
        const val BLUE = 2
    }

    class Piece(val id: Int, var r: Int, var c: Int, var d: Direction) {

        fun getNextPos() = (r + d.r) to (c + d.c)

        fun moveTo(nr: Int, nc: Int) {
            r = nr
            c = nc
        }

        fun reverseDirection() {
            d = d.reverse
        }
    }
}