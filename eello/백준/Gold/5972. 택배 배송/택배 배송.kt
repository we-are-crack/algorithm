import java.util.*

fun main() {
    var st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val path = Array(n) { mutableListOf<Path>() }
    repeat(m) {
        st = StringTokenizer(readln())
        val a = st.nextToken().toInt() - 1
        val b = st.nextToken().toInt() - 1
        val c = st.nextToken().toInt()

        path[a].add(Path(a, b, c))
        path[b].add(Path(b, a, c))
    }

    print(dijkstra(path, 0, n - 1))
}

private fun dijkstra(path: Array<out List<Path>>, start: Int, end: Int): Int {
    val costs = IntArray(path.size) { Int.MAX_VALUE }.apply { this[start] = 0 }
    val pq = PriorityQueue(compareBy<Node> { it.cost }).apply { add(Node(start, 0)) }

    while (pq.isNotEmpty()) {
        val (curr, cost) = pq.poll()

        if (costs[curr] < cost) {
            continue
        }

        for ((_, next, nCost) in path[curr]) {
            if (cost + nCost < costs[next]) {
                costs[next] = cost + nCost
                pq.add(Node(next, costs[next]))
            }
        }
    }

    return costs[end]
}

private data class Path(val start: Int, val end: Int, val cost: Int)
private data class Node(val cur: Int, val cost: Int)