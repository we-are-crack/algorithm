import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()

    val monominoDomino = MonominoDomino()
    repeat(n) {
        val st = StringTokenizer(readln())
        val t = st.nextToken().toInt()
        val r = st.nextToken().toInt()
        val c = st.nextToken().toInt()

        monominoDomino.drop(t, r, c)
    }

    println(monominoDomino.score)
    print(monominoDomino.tileCount)
}

private class MonominoDomino {

    private val blue = Board()
    private val green = Board()

    val tileCount: Int get() = green.tileCount + blue.tileCount

    var score = 0
        private set

    fun drop(t: Int, r: Int, c: Int) {
        val blueBlock = when (t) {
            1 -> arrayOf(Tile(0, r))
            2 -> arrayOf(Tile(1, r), Tile(0, r))
            3 -> arrayOf(Tile(0, r), Tile(0, r + 1))
            else -> throw IllegalArgumentException()
        }

        val greenBlock = when (t) {
            1 -> arrayOf(Tile(0, c))
            2 -> arrayOf(Tile(0, c), Tile(0, c + 1))
            3 -> arrayOf(Tile(1, c), Tile(0, c))
            else -> throw IllegalArgumentException()
        }

        score += blue.process(blueBlock)
        score += green.process(greenBlock)
    }

    data class Tile(val r: Int, val c: Int)

    class Board {

        val state = IntArray(R) { 0 }
        var tileCount = 0
            private set

        fun process(block: Array<Tile>): Int {
            drop(block)
            val score = score()
            specialProcess()

            return score
        }

        private fun drop(block: Array<Tile>) {
            tileCount += block.size

            var d = 0
            loop@ while (true) {
                for (tile in block) {
                    val nr = tile.r + d
                    val bitmask = 1 shl tile.c

                    if (nr >= R || state[nr] and bitmask != 0) {
                        d--
                        break@loop
                    }
                }

                d++
            }

            for (tile in block) {
                val r = tile.r + d
                val bitmask = 1 shl tile.c
                state[r] = state[r] or bitmask
            }
        }

        private fun score(): Int {
            var score = 0
            for (r in R - 1 downTo 0) {
                if (r >= 2 && state[r] == FULL_ROW_SCORE) {
                    score++
                } else {
                    if (score == 0) continue
                    state[r + score] = state[r]
                    state[r] = 0
                }
            }

            tileCount -= score * C
            return score
        }

        private fun specialProcess() {
            var d = 0
            for (r in 0..1) {
                if (state[r] != 0) d++
            }

            if (d == 0) return

            for (r in R - 1 downTo 0) {
                if (r >= R - d) {
                    for (c in 0..<C) {
                        if (state[r] and (1 shl c) != 0) {
                            tileCount--
                        }
                    }
                }

                state[r] = if (r >= 2) state[r - d] else 0
            }
        }

        companion object {
            private const val R = 6
            private const val C = 4
            private const val FULL_ROW_SCORE = (1 shl C) - 1
        }
    }
}