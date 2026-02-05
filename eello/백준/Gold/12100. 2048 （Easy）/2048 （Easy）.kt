import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

private enum class Direction(val r: Int, val c: Int) {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1),
    ;
}

private class GameBoard(val n: Int, val board: Array<IntArray>, val maxValue: Int) {

    fun move(direction: Direction): GameBoard {
        var mv = 0
        val newBoard = Array(n) { IntArray(n) }
        for (i in 0..<n) {
            var sr = 0; var sc = 0
            when (direction) {
                Direction.UP -> { sc = i }
                Direction.RIGHT -> { sr = i; sc = n - 1 }
                Direction.DOWN -> { sr = n - 1; sc = i }
                Direction.LEFT -> { sr = i }
            }

            mv = max(mv, moveLine(newBoard, sr, sc, direction))
        }

        return GameBoard(n, newBoard, mv)
    }

    private fun moveLine(newBoard: Array<IntArray>, sr: Int, sc: Int, direction: Direction): Int {
        if (deq.isNotEmpty()) deq.clear()

        var r = sr; var c = sc
        while (isInBounds(r, c)) { // 합치는 과정
            if (board[r][c] == 0) {
                r -= direction.r
                c -= direction.c
                continue
            }

            if (deq.isNotEmpty() && board[r][c] == deq.last()) {
                deq.addLast(deq.removeLast() * 2)
                deq.addLast(-1) // 한 번 충돌 이후 다시 충돌하지 않도록 -1로 구분
            } else deq.addLast(board[r][c])

            r -= direction.r
            c -= direction.c
        }

        var mv = 0
        r = sr; c = sc
        while (isInBounds(r, c) && deq.isNotEmpty()) { // 합친 결과를 재배치하는 과정
            val value = deq.removeFirst()
            if (value == -1) continue

            mv = max(mv, value)
            newBoard[r][c] = value

            r -= direction.r
            c -= direction.c
        }

        return mv
    }

    private fun isInBounds(r: Int, c: Int) = r in 0..<n && c in 0..<n

    companion object {
        val deq = ArrayDeque<Int>()
    }
}

private data class State(val gameBoard: GameBoard, val moveCount: Int)

private fun Int.pow(amount: Double) = this.toDouble().pow(amount).toInt()

fun main() {
    val n = readln().toInt()
    var maxValue = 0
    val board = Array(n) {
        val line = StringTokenizer(readln())
        IntArray(n) {
            val value = line.nextToken().toInt()
            maxValue = max(maxValue, value)
            value
        }
    }

    val initBoard = GameBoard(n, board, maxValue)

    val que = ArrayDeque<State>().apply { add(State(initBoard, 0)) }

    var answer = 0
    while (que.isNotEmpty()) {
        val (cBoard, moveCount) = que.removeFirst()

        if (moveCount == 5) continue
        if (2.pow(sqrt(cBoard.maxValue.toDouble()) + 5) <= answer) continue

        for (dir in Direction.entries) {
            val nBoard = cBoard.move(dir)
            answer = max(answer, nBoard.maxValue)
            que.add(State(nBoard, moveCount + 1))
        }
    }

    print(answer)
}