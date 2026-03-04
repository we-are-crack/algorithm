import java.util.StringTokenizer

private const val IMPOSSIBLE = 400

fun main() {
    var st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val fuel = st.nextToken().toInt()

    val map = Array(n) {
        st = StringTokenizer(readln())
        IntArray(n) { -st.nextToken().toInt() }
    }

    st = StringTokenizer(readln())
    val tsr = st.nextToken().toInt() - 1
    val tsc = st.nextToken().toInt() - 1

    val taxi = Taxi(tsr, tsc, fuel)

    val passengers = Array(m) { i ->
        st = StringTokenizer(readln())

        val id = i + 1
        val sr = st.nextToken().toInt() - 1
        val sc = st.nextToken().toInt() - 1
        val dr = st.nextToken().toInt() - 1
        val dc = st.nextToken().toInt() - 1

        map[sr][sc] = id
        Passenger(id, Pos(sr, sc), Pos(dr, dc))
    }

    calculateFuelRequired(map, passengers)

    var remain = m
    repeat(m) {
        val pi = taxi.searchPassenger(map)
        pi?.let {
            val (pId, dist) = it
            val passenger = passengers[pId - 1]

            if (passenger.unreachable) return@repeat

            taxi.pickUp(passenger, dist)
            map[passenger.start.r][passenger.start.c] = 0

            if (!taxi.run(passenger))
                return@repeat

            remain--
        } ?: return@repeat
    }

    print(if (remain > 0) -1 else taxi.fuel)
}

private fun Array<IntArray>.isInBounds(r: Int, c: Int) = r in indices && c in this[0].indices

private fun calculateFuelRequired(map: Array<IntArray>, passengers: Array<Passenger>) {
    val n = map.size
    val m = passengers.size

    val que = ArrayDeque<Triple<Int, Pos, Int>>() // 승객번호-1, 현재 위치, 거리
    val visited = Array(m) { Array(n) { BooleanArray(n) } }

    passengers.forEachIndexed { i, passenger ->
        with(passenger) {
            que.add(Triple(i, start, 0))
            visited[i][start.r][start.c] = true
        }
    }

    while (que.isNotEmpty()) {
        val (pi, pos, dist) = que.removeFirst()
        val passenger = passengers[pi]

        if (passenger.distance <= dist) continue
        if (pos == passenger.destination) {
            passenger.distance = dist
            continue
        }

        for (dir in Direction.entries) {
            val nr = pos.r + dir.r
            val nc = pos.c + dir.c

            if (map.isInBounds(nr, nc) && !visited[pi][nr][nc] && map[nr][nc] != -1) {
                visited[pi][nr][nc] = true
                que.add(Triple(pi, Pos(nr, nc), dist + 1))
            }
        }
    }
}

private enum class Direction(val r: Int, val c: Int) {
    UP(-1, 0), LEFT(0, -1), RIGHT(0, 1), DOWN(1, 0);
}

private data class Pos(val r: Int, val c: Int)
private data class Passenger(val id: Int, val start: Pos, val destination: Pos, var distance: Int = IMPOSSIBLE) {
    val unreachable get() = distance == IMPOSSIBLE
}

private class Taxi(var r: Int, var c: Int, var fuel: Int) {

    // (다음 승객의 번호, 승객까지의 거리) 리턴, 다음 승객까지 갈 수 없으면 null 리턴
    fun searchPassenger(map: Array<IntArray>): Pair<Int, Int>? {
        val que = ArrayDeque<Pair<Int, Pos>>().apply { add(0 to Pos(r, c)) } // (이동거리, 좌표)
        val visited = Array(map.size) { BooleanArray(map.size) }.apply { this[r][c] = true }

        var passenger: Pair<Int, Pos>? = null // 승객까지의 거리, 승객의 좌표
        while (que.isNotEmpty()) {
            val cur = que.removeFirst()
            val (dist, pos) = cur
            val (cr, cc) = pos

            if (fuel < dist || (passenger != null && passenger.first < dist))
                break

            if (map[cr][cc] > 0) {
                passenger = passenger?.let {
                    return@let if (cr < it.second.r) cur
                    else if (cr == it.second.r && cc < it.second.c) cur
                    else it
                } ?: cur

                continue
            }

            for (dir in Direction.entries) {
                val nr = cr + dir.r
                val nc = cc + dir.c

                if (map.isInBounds(nr, nc) && !visited[nr][nc] && map[nr][nc] >= 0) {
                    visited[nr][nc] = true
                    que.add(dist + 1 to Pos(nr, nc))
                }
            }
        }

        return passenger?.let { map[it.second.r][it.second.c] to it.first }
    }

    fun pickUp(passenger: Passenger, distance: Int): Boolean {
        if (fuel < distance) return false

        r = passenger.start.r
        c = passenger.start.c
        fuel -= distance

        return true
    }

    fun run(passenger: Passenger): Boolean {
        return with(passenger) {
            if (fuel < distance) false
            else {
                r = destination.r
                c = destination.c
                fuel += distance
                true
            }
        }
    }
}