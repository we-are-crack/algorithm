import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

data class Point(
    var x: Float = 0f,
    var y: Float = 0f
) {
    fun calculateDistance(other: Point): Float {
        return sqrt((x - other.x).pow(2) + (y - other.y).pow(2))
    }
}

data class Distance(
    val p1: Int,
    val p2: Int,
    val distance: Float
) : Comparable<Distance> {
    override fun compareTo(other: Distance): Int = (this.distance - other.distance).toInt()
}

fun main() {
    val n = readln().toInt()
    val points = mutableListOf<Point>()
    val distances = PriorityQueue<Distance>()

    repeat(n) {
        val pos = readln().split(" ")
        points.add(Point(pos.first().toFloat(), pos.last().toFloat()))
    }

    // 모든 점 사이의 거리 계산
    points.forEachIndexed { i, p1 ->
        for (j in (i + 1) until points.size) {
            distances.add(Distance(i, j, p1.calculateDistance(points[j])))
        }
    }

    val parent = IntArray(n) { it }
    var answer: Float = 0f
    var connected: Int = 0
    while (distances.isNotEmpty() && connected < n) {
        val dist = union(parent = parent, dist = distances.poll())
        answer += dist

        if (dist != 0f) {
            connected++
        }
    }

    print("%.2f".format(answer))
}

fun findParent(parent: IntArray, point: Int): Int =
    if (parent[point] == point) point
    else findParent(parent, parent[point]).also {
        parent[point] = it
    }

fun union(parent: IntArray, dist: Distance): Float {
    val parent1 = findParent(parent, dist.p1)
    val parent2 = findParent(parent, dist.p2)

    return if (parent1 != parent2) {
        if (parent1 > parent2) {
            parent[parent1] = parent2
        } else parent[parent2] = parent1

        dist.distance
    } else 0f
}