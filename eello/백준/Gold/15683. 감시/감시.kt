import java.util.*
import kotlin.math.min

private data class Pos(val r: Int, val c: Int)

private val DIRECTIONS = arrayOf(
    Pos(-1, 0), // UP
    Pos(0, 1), // RIGHT
    Pos(1, 0), // DOWN
    Pos(0, -1), // LEFT
)

private class Room(val n: Int, val m: Int, val grid: Array<IntArray>, val cctvs: List<CCTV>) {
    fun getBlindSpotCount(): Int {
        val map = Array(n) { r -> BooleanArray(m) { c -> grid[r][c] != 0 } }
        for (cctv in cctvs) {
            for (d in cctv.monitoringArea) {
                var r = cctv.r + DIRECTIONS[d].r
                var c = cctv.c + DIRECTIONS[d].c
                while (isInBounds(r, c) && grid[r][c] != 6) {
                    map[r][c] = true
                    r += DIRECTIONS[d].r
                    c += DIRECTIONS[d].c
                }
            }
        }

        return map.sumOf { line -> line.count { !it } }
    }

    private fun isInBounds(r: Int, c: Int) = r in 0..<n && c in 0..<m

}

private class CCTV(val r: Int, val c: Int, val type: Int) {

    val monitoringAreaCandidate = MONITORING_AREA[type - 1]
    var monitoringDirection = 0
    val monitoringArea get() = monitoringAreaCandidate[monitoringDirection]

    companion object {
        val MONITORING_AREA = arrayOf(
            arrayOf(
                intArrayOf(0), intArrayOf(1), intArrayOf(2), intArrayOf(3),
            ),
            arrayOf(
                intArrayOf(0, 2), intArrayOf(1, 3)
            ),
            arrayOf(
                intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 0),
            ),
            arrayOf(
                intArrayOf(0, 1, 2), intArrayOf(1, 2, 3), intArrayOf(2, 3, 0), intArrayOf(3, 0, 1)
            ),
            arrayOf(
                intArrayOf(0, 1, 2, 3)
            )
        )
    }
}

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val cctvs = mutableListOf<CCTV>()
    val grid = Array(n) { r ->
        val line = StringTokenizer(readln())
        IntArray(m) { c ->
            val elem = line.nextToken().toInt()
            if (elem in 1..5) cctvs.add(CCTV(r, c, elem))
            elem
        }
    }

    val room = Room(n, m, grid, cctvs)
    print(dfs(0, room))
}

private fun dfs(k: Int, room: Room): Int {
    if (k == room.cctvs.size) {
        return room.getBlindSpotCount()
    }

    var ret = Int.MAX_VALUE
    val cctv = room.cctvs[k]

    for (dir in cctv.monitoringAreaCandidate.indices) {
        cctv.monitoringDirection = dir
        ret = min(ret, dfs(k + 1, room))
    }

    return ret
}