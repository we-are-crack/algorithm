import java.util.*

private enum class Direction(val r: Int, val c: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    RIGHT(0, 1),
    LEFT(0, -1),
    ;

    fun reverse() = when (this) {
        UP -> DOWN
        DOWN -> UP
        RIGHT -> LEFT
        LEFT -> RIGHT
    }

    companion object {
        fun get(d: Int) = entries[d - 1]
    }
}

private fun Array<Array<Shark?>>.isInBounds(r: Int, c: Int) = r in indices && c in this[0].indices

private class FishingArea(r: Int, c: Int) {

    val sharks = mutableMapOf<Int, Shark>()
    val grid = Array(r) { arrayOfNulls<Shark>(c) }

    fun addShark(shark: Shark) {
        sharks[shark.size] = shark
        grid[shark.r][shark.c] = shark
    }

    fun moveAllShark() {
        sharks.forEach { (_, shark) -> grid[shark.r][shark.c] = null }

        val dead = mutableListOf<Shark>()
        sharks.forEach { (_, shark) ->
            val d = shark.move(grid)
            d?.let { dead.add(it) }
        }

        dead.forEach { sharks.remove(it.size) }
    }
}

private class Shark(var r: Int, var c: Int, val speed: Int, var size: Int, var direction: Direction) {

    fun move(grid: Array<Array<Shark?>>): Shark? {
        val cycle = if (direction.r == 0) 2 * c + 2 * (grid[0].lastIndex - c) // 횡 이동
        else 2 * r + 2 * (grid.lastIndex - r) // 종 이동

        val times = if (cycle == 0) 0 else speed % cycle
        repeat(times) {
            var nr = r + direction.r
            var nc = c + direction.c

            if (!grid.isInBounds(nr, nc)) {
                direction = direction.reverse()
                nr = r + direction.r
                nc = c + direction.c
            }

            r = nr
            c = nc
        }

        if (grid[r][c] == null) {
            grid[r][c] = this
            return null
        }

        return if (grid[r][c]!!.size < size) {
            val dead = grid[r][c]
            grid[r][c] = this
            dead
        } else this
    }
}

private fun FishingArea.catch(col: Int): Int {
    var catchSize = 0
    for (row in grid.indices) {
        if (grid[row][col] != null) {
            catchSize = grid[row][col]!!.size
            sharks.remove(catchSize)
            grid[row][col] = null
            break
        }
    }
    return catchSize
}

fun main() {
    val st = StringTokenizer(readln())
    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val fishingArea = FishingArea(r, c)

    repeat(m) {
        val info = StringTokenizer(readln())
        val y = info.nextToken().toInt() - 1
        val x = info.nextToken().toInt() - 1
        val s = info.nextToken().toInt()
        val d = info.nextToken().toInt()
        val z = info.nextToken().toInt()

        fishingArea.addShark(Shark(r = y, c = x, speed = s, size = z, direction = Direction.get(d)))
    }

    var fisher = 0
    var catchSize = 0

    repeat(c) {
        catchSize += fishingArea.catch(fisher++)
        fishingArea.moveAllShark()
    }

    print(catchSize)
}