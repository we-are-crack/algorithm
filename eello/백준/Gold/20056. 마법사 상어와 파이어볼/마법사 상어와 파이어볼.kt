import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val fireballs = mutableListOf<Grid.Fireball>()
    repeat(m) {
        val fb = StringTokenizer(readln())
        val r = fb.nextToken().toInt() - 1
        val c = fb.nextToken().toInt() - 1
        val w = fb.nextToken().toInt()
        val s = fb.nextToken().toInt()
        val d = fb.nextToken().toInt()

        fireballs.add(Grid.Fireball(r, c, w, s, Direction[d]))
    }

    val grid = Grid(n, fireballs)
    print(grid.move(k))
}

private enum class Direction(val r: Int, val c: Int) {
    UP(-1, 0),          // 0
    RIGHT_UP(-1, 1),    // 1
    RIGHT(0, 1),        // 2
    RIGHT_DOWN(1, 1),   // 3
    DOWN(1, 0),         // 4
    LEFT_DOWN(1, -1),   // 5
    LEFT(0, -1),        // 6
    LEFT_UP(-1, -1);    // 7

    companion object {
        operator fun get(ordinal: Int) = entries[ordinal]
    }
}

private class Grid(val n: Int, var fireballs: List<Fireball>) {

    fun move(k: Int): Int {
        repeat(k) {
            moveAll()
            merge()
        }

        return fireballs.sumOf { it.weight }
    }

    private fun moveAll() {
        for (fireball in fireballs) {
            fireball.move()
        }
    }

    private fun merge() {
        val temp = mutableListOf<Fireball>()
        fireballs.groupBy { it.r to it.c }
            .forEach { (pos, fbs) ->
                if (fbs.size > 1) {
                    var totalW = 0
                    var totalS = 0
                    var isSameDir = true
                    for (f in fbs) {
                        totalW += f.weight
                        totalS += f.speed

                        if (isSameDir && fbs[0].d.ordinal % 2 != f.d.ordinal % 2) {
                            isSameDir = false
                        }
                    }

                    if (totalW >= 5) {
                        val (r, c) = pos
                        val eachW = totalW / 5
                        val eachS = totalS / fbs.size

                        val dirs = if (isSameDir) arrayOf(0, 2, 4, 6) else arrayOf(1, 3, 5, 7)
                        for (dir in dirs) {
                            temp.add(Fireball(r, c, eachW, eachS, Direction[dir]))
                        }
                    }
                } else {
                    temp.add(fbs[0])
                }
            }

        fireballs = temp
    }

    fun Fireball.move() {
        val mod = speed % n
        r = (r + (d.r * mod) + n) % n
        c = (c + (d.c * mod) + n) % n
    }

    data class Fireball(var r: Int, var c: Int, val weight: Int, val speed: Int, val d: Direction)
}