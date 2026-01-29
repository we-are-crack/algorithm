import java.util.*
import kotlin.collections.ArrayDeque

enum class Direction(val r: Int, val c: Int) {
    C(0, 0),
    E(0, 1),
    W(0, -1),
    N(-1, 0),
    S(1, 0),
    ;

    companion object {
        fun from(d: Int) = entries[d]
    }
}

data class Point(val r: Int, val c: Int) {
    operator fun plus(direction: Direction) = Point(r + direction.r, c + direction.c)
}

class Dice {

    private val vertical = ArrayDeque<Int>().apply { addAll(arrayOf(1, 5, 6, 2)) }
    private val horizontal = ArrayDeque<Int>().apply { addAll(arrayOf(1, 3, 6, 4)) }
    private val value = IntArray(7)

    fun getTopValue() = value[vertical.first()]
    fun getBottomValue() = value[7 - vertical.first()]
    fun changeBottomValue(value: Int) {
        this.value[7 - vertical.first()] = value
    }

    fun roll(d: Int) {
        when (d) {
            1 -> rollRight()
            2 -> rollLeft()
            3 -> rollUp()
            4 -> rollDown()
        }
    }

    private fun rollUp() {
        vertical.addLast(vertical.removeFirst())
        horizontal[0] = vertical.first()
        horizontal[2] = 7 - vertical.first()
    }

    private fun rollDown() {
        vertical.addFirst(vertical.removeLast())
        horizontal[0] = vertical.first()
        horizontal[2] = 7 - vertical.first()
    }

    private fun rollLeft() {
        horizontal.addLast(horizontal.removeFirst())
        vertical[0] = horizontal.first()
        vertical[2] = 7 - horizontal.first()
    }

    private fun rollRight() {
        horizontal.addFirst(horizontal.removeLast())
        vertical[0] = horizontal.first()
        vertical[2] = 7 - horizontal.first()
    }
}

fun Array<IntArray>.isInBounds(pos: Point) = pos.r in indices && pos.c in this[0].indices

fun main() {
    val (n, m, x, y, k) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(m) { st.nextToken().toInt() }
    }

    val st = StringTokenizer(readln())
    val command = IntArray(k) { st.nextToken().toInt() }

    val dice = Dice()
    var cur = Point(x, y)

    map[cur.r][cur.c] = dice.getBottomValue()

    val answer = StringBuilder(dice.getTopValue())
    for (cmd in command) {
        val nxt = cur + Direction.from(cmd)
        if (!map.isInBounds(nxt)) continue

        dice.roll(cmd)
        cur = nxt

        if (map[cur.r][cur.c] == 0) {
            map[cur.r][cur.c] = dice.getBottomValue()
        } else {
            dice.changeBottomValue(map[cur.r][cur.c])
            map[cur.r][cur.c] = 0
        }

        answer.append("\n").append(dice.getTopValue())
    }

    print(answer.trim())
}