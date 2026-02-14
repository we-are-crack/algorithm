import java.util.StringTokenizer

private data class Pos(val r: Int, val c: Int)

private enum class Direction(val r: Int, val c: Int) {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1),
    ;

    companion object {
        val clockwise = arrayOf(RIGHT, DOWN, LEFT, UP)
        val counterClockwise = arrayOf(RIGHT, UP, LEFT, DOWN)
    }
}

private fun Array<IntArray>.isInBounds(r: Int, c: Int) = r in indices && c in this[0].indices

private class AirPurifier(val pos: Pos, val cycle: Array<Direction>) {

    private val cleaningArea = mutableListOf<Pos>()

    fun operate(grid: Array<IntArray>) {
        if (cleaningArea.isEmpty()) {
            setCleaningArea(grid)
        }

        for (i in cleaningArea.lastIndex downTo 1) {
            val (r, c) = cleaningArea[i]
            val (pr, pc) = cleaningArea[i - 1]
            grid[r][c] = grid[pr][pc]

            if (i == 1) {
                grid[pr][pc] = 0
            }
        }
    }

    private fun setCleaningArea(grid: Array<IntArray>) {
        var (r, c) = pos
        for (dir in cycle) {
            r += dir.r
            c += dir.c

            while (grid.isInBounds(r, c)) {
                cleaningArea.add(Pos(r, c))
                r += dir.r
                c += dir.c

                if (r == pos.r && c == pos.c) {
                    return
                }
            }

            r -= dir.r
            c -= dir.c
        }
    }
}

private class Home(val state: Array<IntArray>, val airPurifier: List<AirPurifier>) {

    val remainAmount get() = state.sumOf { row -> row.sum() } + 2

    fun spread() {
        val spreadTargets = mutableListOf<SectionState>()
        for (r in state.indices) {
            for (c in state[0].indices) {
                if (state[r][c] / 5 > 0) {
                    spreadTargets.add(SectionState(r, c, state[r][c]))
                }
            }
        }

        for ((r, c, amount) in spreadTargets) {
            val nAmount = amount / 5
            for (dir in Direction.entries) {
                val nr = r + dir.r
                val nc = c + dir.c

                if (state.isInBounds(nr, nc) && state[nr][nc] != -1) {
                    state[nr][nc] += nAmount
                    state[r][c] -= nAmount
                }
            }
        }
    }

    fun operateAirPurifier() {
        airPurifier.forEach { it.operate(state) }
    }

    private data class SectionState(val r: Int, val c: Int, val amount: Int)
}

fun main() {
    val st = StringTokenizer(readln())
    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val t = st.nextToken().toInt()

    val cycles = arrayOf(Direction.counterClockwise, Direction.clockwise)
    var airPurifierId = 0
    val airPurifier = mutableListOf<AirPurifier>()

    val state = Array(r) { y ->
        val line = StringTokenizer(readln())
        IntArray(c) { x ->
            var elem = line.nextToken().toInt()
            if (elem == -1) {
                airPurifier.add(AirPurifier(Pos(y, x), cycles[airPurifierId++]))
            }

            elem
        }
    }

    val home = Home(state, airPurifier)

    repeat(t) {
        home.spread()
        home.operateAirPurifier()
    }

    print(home.remainAmount)
}