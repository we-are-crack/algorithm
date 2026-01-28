import java.util.*

data class Point(val r: Int, val c: Int) {
    operator fun plus(other: Point) = Point(r + other.r, c + other.c)
}

data class Step(val pos: Point, val step: Int) : Comparable<Step> {
    override fun compareTo(other: Step): Int {
        return if (step != other.step) step - other.step
        else if (pos.r != other.pos.r) pos.r - other.pos.r
        else pos.c - other.pos.c
    }
}

class Shark(initialPos: Point) {
    private var eatCount = 0

    var size: Int = 2
        private set

    var pos: Point = initialPos
        private set

    var moveCount: Int = 0
        private set

    fun moveAndEat(pos: Point, moveCount: Int) {
        this.pos = pos
        this.moveCount += moveCount

        eatCount += 1
        size += if (eatCount == size) {
            eatCount = 0
            1
        } else 0
    }
}

fun Shark.searchTarget(map: Array<IntArray>): Step? {
    val n = map.size
    val que = PriorityQueue<Step>()
    val visit = Array(n) { BooleanArray(n) }

    que.add(Step(pos, 0))
    visit[pos.r][pos.c] = true

    val dir = arrayOf(
        Point(-1, 0), // N
        Point(0, -1), // W
        Point(0, 1), // E
        Point(1, 0), // S
    )

    while (que.isNotEmpty()) {
        val cur = que.poll()
        val (r, c) = cur.pos

        if (map[r][c] in 1..<size) {
            return cur
        }

        for (d in dir) {
            val nxt = cur.pos + d
            val (nr, nc) = nxt

            if (map.isInBounds(nxt) && !visit[nr][nc] && map[nr][nc] <= size) {
                que.offer(Step(nxt, cur.step + 1))
                visit[nr][nc] = true
            }
        }
    }

    return null
}

fun Array<IntArray>.isInBounds(pos: Point): Boolean {
    val n = this.size
    return pos.r in 0..<n && pos.c in 0..<n
}

fun main() {
    val n = readln().toInt()
    val map = Array(n) { IntArray(n) }
    var initialPos: Point? = null
    for (i in 0..<n) {
        val st = StringTokenizer(readln())
        for (j in 0..<n) {
            map[i][j] = st.nextToken().toInt()
            if (map[i][j] == 9) {
                initialPos = Point(i, j)
                map[i][j] = 0
            }
        }
    }

    val shark = Shark(initialPos ?: Point(0, 0))

    while (true) {
        val (nxtPos, moveCount) = shark.searchTarget(map) ?: break
        shark.moveAndEat(nxtPos, moveCount)
        map[nxtPos.r][nxtPos.c] = 0
    }

    print(shark.moveCount)
}