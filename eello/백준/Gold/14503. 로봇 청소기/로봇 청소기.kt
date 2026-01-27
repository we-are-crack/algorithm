import java.util.StringTokenizer

enum class Direction(val r: Int, val c: Int) {
    North(-1, 0),
    East(0, 1),
    South(1, 0),
    West(0, -1),
    ;

    fun rotateLeft(): Direction = from((this.ordinal + 3) % 4)
    fun backward(): Direction = from((this.ordinal + 2) % 4)

    companion object {
        fun from(value: Int) = entries[value]
    }
}

data class Point(val r: Int, val c: Int) {
    operator fun plus(dir: Direction) = Point(r + dir.r, c + dir.c)
}

class Cleaner(
    initialPos: Point,
    initialHead: Direction,
) {
    var pos = initialPos
    private set

    var head = initialHead
    private set

    var cleanCount: Int = 0
    private set

    fun clean() {
        cleanCount++
    }

    fun turnAndMove(nextHead: Direction, nextPoint: Point) {
        head = nextHead
        pos = nextPoint
    }
}

fun Cleaner.operate(map: Array<IntArray>) {
    while (true) {
        if (map[pos.r][pos.c] == 0) {
            map[pos.r][pos.c] = -1
            clean()
        }

        val hasUncleanedArea = Direction.entries.any { dir ->
            val next = pos + dir
            map[next.r][next.c] == 0
        }

        if (hasUncleanedArea) {
            val nextHead = head.rotateLeft()
            val nextPoint = pos + nextHead

            if (map[nextPoint.r][nextPoint.c] == 0) {
                turnAndMove(nextHead, nextPoint)
            } else {
                turnAndMove(nextHead, pos)
            }
        } else {
            val backPoint = pos + head.backward()

            if (map[backPoint.r][backPoint.c] == 1) {
                break
            }

            turnAndMove(head, backPoint)
        }
    }
}

fun main() {
    val st1 = StringTokenizer(readln())
    val n = st1.nextToken().toInt()
    val m = st1.nextToken().toInt()

    val st2 = StringTokenizer(readln())
    val r = st2.nextToken().toInt()
    val c = st2.nextToken().toInt()
    val d = st2.nextToken().toInt()

    val map = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(m) { st.nextToken().toInt() }
    }

    val cleaner = Cleaner(Point(r, c), Direction.from(d))
    cleaner.operate(map)
    print(cleaner.cleanCount)
}