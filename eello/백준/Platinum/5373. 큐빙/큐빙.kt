import java.util.*

private class Color(var color: Char) {

    override fun toString(): String {
        return color.toString()
    }
}

private fun Array<Array<Color>>.fill(color: Char) {
    this.forEach { row ->
        row.forEach { elem ->
            elem.color = color
        }
    }
}

private class Cube {

    val up = Array(3) { Array(3) { Color('w') } }
    val down = Array(3) { Array(3) { Color('y') } }
    val front = Array(3) { Array(3) { Color('r') } }
    val back = Array(3) { Array(3) { Color('o') } }
    val left = Array(3) { Array(3) { Color('g') } }
    val right = Array(3) { Array(3) { Color('b') } }
    private val copy = Array(3) { Array(3) { Color('w') } }

    private val rotateUp = arrayOf(
        front[0][0], front[0][1], front[0][2],
        right[0][0], right[0][1], right[0][2],
        back[0][0], back[0][1], back[0][2],
        left[0][0], left[0][1], left[0][2]
    )

    private val rotateDown = arrayOf(
        front[2][2], front[2][1], front[2][0],
        left[2][2], left[2][1], left[2][0],
        back[2][2], back[2][1], back[2][0],
        right[2][2], right[2][1], right[2][0]
    )

    private val rotateFront = arrayOf(
        up[2][2], up[2][1], up[2][0],
        left[0][2], left[1][2], left[2][2],
        down[0][0], down[0][1], down[0][2],
        right[2][0], right[1][0], right[0][0]
    )

    private val rotateBack = arrayOf(
        up[0][0], up[0][1], up[0][2],
        right[0][2], right[1][2], right[2][2],
        down[2][2], down[2][1], down[2][0],
        left[2][0], left[1][0], left[0][0]
    )

    private val rotateLeft = arrayOf(
        up[2][0], up[1][0], up[0][0],
        back[0][2], back[1][2], back[2][2],
        down[2][0], down[1][0], down[0][0],
        front[2][0], front[1][0], front[0][0]
    )

    private val rotateRight = arrayOf(
        up[0][2], up[1][2], up[2][2],
        front[0][2], front[1][2], front[2][2],
        down[0][2], down[1][2], down[2][2],
        back[2][0], back[1][0], back[0][0]
    )

    private val rotateTarget = mapOf(
        'U' to (up to rotateUp),
        'D' to (down to rotateDown),
        'F' to (front to rotateFront),
        'B' to (back to rotateBack),
        'L' to (left to rotateLeft),
        'R' to (right to rotateRight),
    )

    fun rotate(target: Char, dir: Char) {
        val (face, line) = rotateTarget[target]!!
        if (dir == '+') {
            rotateClockwise(line)
            rotate90(face)
        } else {
            rotateCounterClockwise(line)
            rotate270(face)
        }
    }

    fun rotate90(target: Array<Array<Color>>) {
        copy(target)
        for (i in target.indices) {
            for (j in target[0].indices) {
                target[i][j].color = copy[3 - j - 1][i].color
            }
        }
    }

    fun rotate270(target: Array<Array<Color>>) {
        copy(target)
        for (i in target.indices) {
            for (j in target[0].indices) {
                target[i][j].color = copy[j][3 - i - 1].color
            }
        }
    }

    private fun copy(target: Array<Array<Color>>) {
        for (i in target.indices) {
            for (j in target[i].indices) {
                copy[i][j].color = target[i][j].color
            }
        }
    }

    private fun rotateClockwise(target: Array<Color>) { // 반시계방향
        val temp1 = target[0].color
        val temp2 = target[1].color
        val temp3 = target[2].color

        for (i in 0..8) {
            target[i].color = target[i + 3].color
        }

        target[9].color = temp1
        target[10].color = temp2
        target[11].color = temp3
    }

    private fun rotateCounterClockwise(target: Array<Color>) { // 시계방향
        val temp1 = target[9].color
        val temp2 = target[10].color
        val temp3 = target[11].color

        for (i in 11 downTo 3) {
            target[i].color = target[i - 3].color
        }

        target[0].color = temp1
        target[1].color = temp2
        target[2].color = temp3
    }

    fun reset() {
        up.fill('w')
        down.fill('y')
        front.fill('r')
        back.fill('o')
        left.fill('g')
        right.fill('b')
    }

    fun printState(target: Array<Array<Color>> = up): StringBuilder {
        return StringBuilder().apply {
            target.forEach { row -> append("\n").append(row.joinToString("")) }
        }
    }
}

fun main() {
    val cube = Cube()
    val answer = StringBuilder()
    val k = readln().toInt()
    repeat(k) {
        val n = readln().toInt()
        val commands = StringTokenizer(readln())
        repeat(n) {
            val command = commands.nextToken()
            val target = command[0]
            val dir = command[1]
            cube.rotate(target, dir)
        }
        answer.append(cube.printState())
        cube.reset()
    }
    println(answer.trim())
}