import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.List
import kotlin.collections.forEachIndexed
import kotlin.collections.map
import kotlin.collections.mutableListOf
import kotlin.collections.toList
import kotlin.math.pow

private class Gears(val gears: List<Gear>) {

    val score: Int get() {
        var score = 0
        gears.forEachIndexed { index, gear ->
            score += gear.value * 2.0.pow(index.toDouble()).toInt()
        }
        return score
    }

    fun rotate(index: Int, dir: Int, visited: BooleanArray = BooleanArray(4)) {
        val left = index - 2
        val cur = index - 1
        val right = index

        val gear = gears[cur]
        val leftGear = if (left >= 0) gears[left] else null
        val rightGear = if (right < gears.size) gears[right] else null

        visited[index - 1] = true

        leftGear?.let { lg ->
            if (!visited[left] && lg.rightPole != gear.leftPole) {
                rotate(index - 1, dir * -1, visited)
            }
        }

        rightGear?.let { rg ->
            if (!visited[right] && rg.leftPole != gear.rightPole) {
                rotate(index + 1, dir * -1, visited)
            }
        }

        if (dir == 1) gear.rotate()
        else gear.rotateReverse()
    }
}

private class Gear(initState: List<Int>) {
    val state = ArrayDeque(initState)
    val value get() = state.first()
    val leftPole get() = state[LEFT]
    val rightPole get() = state[RIGHT]


    fun rotate() {
        state.addFirst(state.removeLast())
    }

    fun rotateReverse() {
        state.addLast(state.removeFirst())
    }

    companion object {
        private const val RIGHT = 2
        private const val LEFT = 6
    }
}

fun main() {
    val initGears = mutableListOf<Gear>()
    repeat(4) {
        val gear = Gear(readln().toCharArray().map { it - '0' }.toList())
        initGears.add(gear)
    }

    val gears = Gears(initGears)

    val k = readln().toInt()
    repeat(k) {
        val line = StringTokenizer(readln())
        val index = line.nextToken().toInt()
        val dir = line.nextToken().toInt()

        gears.rotate(index, dir)
    }

    print(gears.score)
}