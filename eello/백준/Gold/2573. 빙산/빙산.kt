import java.util.*
import kotlin.collections.ArrayDeque

private data class Position(val r: Int, val c: Int) {
    operator fun plus(other: Position) = Position(r + other.r, c + other.c)
}

private val DIRECTION = arrayOf(
    Position(-1, 0),
    Position(0, 1),
    Position(1, 0),
    Position(0, -1),
)

private fun Array<IntArray>.isSafe(pos: Position) = pos.r in indices && pos.c in this[0].indices
private fun Array<IntArray>.isAllMelted() = this.all { r -> r.all { it < 1 } }
private fun Array<IntArray>.getChunkCount(): Int {
    var chunkCount = 0
    val visit = Array(size) { BooleanArray(this[0].size) }

    for (r in 1..<size) {
        for (c in 1..<this[0].size) {
            if (this[r][c] < 1 || visit[r][c]) {
                continue
            }

            chunkCount++
            val que = ArrayDeque<Position>()

            que.add(Position(r, c))
            visit[r][c] = true

            while (que.isNotEmpty()) {
                val cur = que.removeFirst()

                for (dir in DIRECTION) {
                    val nxt = cur + dir

                    if (this.isSafe(nxt) && !visit[nxt.r][nxt.c] && this[nxt.r][nxt.c] > 0) {
                        que.add(nxt)
                        visit[nxt.r][nxt.c] = true
                    }
                }
            }
        }
    }

    return chunkCount
}

fun main() {
    val st1 = StringTokenizer(readln())
    val n = st1.nextToken().toInt()
    val m = st1.nextToken().toInt()

    val map = Array(n) {
        val st2 = StringTokenizer(readln())
        IntArray(m) { st2.nextToken().toInt() }
    }

    val iceberg = ArrayDeque<Position>()
    for (r in map.indices) {
        for (c in map[0].indices) {
            if (map[r][c] != 0) {
                continue
            }

            for (dir in DIRECTION) {
                val nxt = Position(r + dir.r, c + dir.c)

                if (map.isSafe(nxt) && map[nxt.r][nxt.c] > 0) {
                    iceberg.add(Position(r + dir.r, c + dir.c))
                }
            }
        }
    }

    var answer = 0
    while (true) {
        if (map.isAllMelted()) {
            answer = 0
            break
        }

        if (map.getChunkCount() > 1) {
            break
        }

        answer++
        val icebergSize = iceberg.size
        repeat(icebergSize) {
            val ib = iceberg.removeFirst()
            map[ib.r][ib.c]--

            if (map[ib.r][ib.c] == 0) {
                for (dir in DIRECTION) {
                    val nxt = ib + dir
                    if (map.isSafe(nxt) && map[nxt.r][nxt.c] > 0) {
                        iceberg.add(nxt)
                    }
                }
            } else if (map[ib.r][ib.c] > 0) {
                iceberg.add(ib)
            }
        }
    }

    print(answer)
}