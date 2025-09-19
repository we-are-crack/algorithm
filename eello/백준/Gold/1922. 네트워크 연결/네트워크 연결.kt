import java.util.*

data class Edge(val v: Int, val cost: Int)

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val adj = Array(n + 1) { mutableListOf<Edge>() }

    repeat(m) {
        val (a, b, c) = readln().split(" ").map(String::toInt)
        adj[a].add(Edge(b, c))
        adj[b].add(Edge(a, c))
    }

    val priorityQueue = PriorityQueue(compareBy<Edge> { it.cost })
    val visit = BooleanArray(n + 1)

    var mst = 0
    var connected = 0

    priorityQueue += Edge(1, 0)
    while (priorityQueue.isNotEmpty() && connected < n) {
        val (v, c) = priorityQueue.poll()

        if (visit[v]) continue

        visit[v] = true
        mst += c
        connected++

        priorityQueue.addAll(adj[v])
    }

    println(mst)
}