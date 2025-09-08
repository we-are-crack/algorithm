fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val parent = IntArray(n + 1) { it }

    repeat(n) { i ->
        val city = readln().split(" ").map { it.toInt() }
        city.forEachIndexed { j, connected ->
            if (connected == 1) union(parent, i + 1, j + 1)
        }
    }

    val route = readln().split(" ").map { it.toInt() }
    val possible = route.all { findParent(parent, route[0]) == findParent(parent, it) }
    println(if (possible) "YES" else "NO")
}

fun findParent(parent: IntArray, node: Int): Int {
    return if (parent[node] == node) node
    else findParent(parent, parent[node]).also { parent[node] = it }
}

fun union(parent: IntArray, node1: Int, node2: Int) {
    val p1 = findParent(parent, node1)
    val p2 = findParent(parent, node2)

    if (p1 != p2) {
        if (p1 < p2) parent[p2] = p1
        else parent[p1] = p2
    }
}