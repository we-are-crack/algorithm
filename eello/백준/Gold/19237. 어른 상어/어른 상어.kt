import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    var st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val sharks = arrayOfNulls<Sea.Shark>(m)
    val grid = Array(n) { r ->
        val line = StringTokenizer(readln())
        IntArray(n) { c ->
            val id = line.nextToken().toInt()
            if (id != 0) {
                val shark = Sea.Shark(id = id, r = r, c = c)
                sharks[id - 1] = shark
                id
            } else Sea.EMPTY
        }
    }

    st = StringTokenizer(readln())
    sharks.forEach { s ->
        val d = st.nextToken().toInt() - 1
        s?.d = d
    }

    repeat(m) { i ->
        val searchPriority = Array(4) { IntArray(4) }
        for (direction in Direction.entries) {
            val ordinal = direction.ordinal
            val line = StringTokenizer(readln())
            repeat(4) { j ->
                val d = line.nextToken().toInt() - 1
                searchPriority[ordinal][j] = d
            }
        }

        sharks[i]?.searchPriority = searchPriority
    }

    val sea = Sea(sharks = sharks, grid = grid, smellTtl = k)
    print(sea.solve())
}

private enum class Direction(val r: Int, val c: Int) {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

    companion object {
        operator fun get(ordinal: Int) = entries[ordinal]
    }
}

private class Sea(val sharks: Array<Shark?>, val grid: Array<IntArray>, val smellTtl: Int) {

    val n = grid.size
    val smell = Array(n) { r -> Array(n) { c -> Smell(-1, -1, r, c) } }
    val move = ArrayDeque<Shark>()

    var time = 0
        private set
    var alive = 0
        private set

    init {
        sharks.forEach { shark ->
            shark?.let {
                alive++
                smell[it.r][it.c] = Smell(it.id, smellTtl, it.r, it.c)
            }
        }
    }

    fun solve(): Int {
        while (alive > 1 && time < 1000) {
            time++
            moveAll()
        }

        return if(alive > 1) -1 else time
    }

    fun moveAll() {
        sharks.forEach { shark ->
            shark?.tryMove()
        }

        processMove()
    }

    private fun Shark.tryMove() {
        var moveInfo = searchNextCell { r, c -> smell[r][c].removeAt < time } // 이동할 수 있는 빈 칸 조회
        if (moveInfo == null) moveInfo = searchNextCell { r, c -> smell[r][c].createdBy == id } // 자신의 냄새가 있는 칸 조회

        moveInfo?.let {
            val (nd, nr, nc) = it
            moveTo(nd, nr, nc)
        }
    }

    fun Shark.searchNextCell(condition: (Int, Int) -> Boolean): MoveInfo? {
        val priority = searchPriority[d]

        for (nd in priority) {
            val direction = Direction[nd]
            val nr = r + direction.r
            val nc = c + direction.c

            if (isInBounds(nr, nc) && condition(nr, nc)) {
                return MoveInfo(nd, nr, nc)
            }
        }

        return null
    }

    private fun Shark.moveTo(nd: Int, nr: Int, nc: Int) {
        grid[r][c] = EMPTY
        d = nd
        r = nr
        c = nc

        move.add(this)
    }

    private fun processMove() {
        while (move.isNotEmpty()) {
            val shark = move.removeFirst()
            with(shark) {
                if (grid[r][c] < id) {
                    sharks[id - 1] = null
                    alive--
                }

                if (grid[r][c] > id || grid[r][c] == EMPTY) {
                    if (grid[r][c] != EMPTY) {
                        // 이전에 존재하던 상어 삭제
                        sharks[grid[r][c] - 1] = null
                        alive--
                    }

                    grid[r][c] = id
                    smell[r][c] = Smell(id, time + smellTtl, r, c)
                }
            }
        }
    }

    private fun isInBounds(r: Int, c: Int) = r in 0..<n && c in 0..<n

    companion object {
        const val EMPTY = 401
    }

    class Shark(val id: Int, var r: Int, var c: Int) {
        var d = 0
        lateinit var searchPriority: Array<IntArray>

    }

    data class Smell(val createdBy: Int, val removeAt: Int, val r: Int, val c: Int)
    data class MoveInfo(val nd: Int, val nr: Int, val nc: Int)
}