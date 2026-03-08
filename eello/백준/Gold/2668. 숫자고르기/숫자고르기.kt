fun main() {
    val n = readln().toInt()
    val arr = IntArray(n + 1)
    repeat(n) { i ->
        arr[i + 1] = readln().toInt()
    }

    val answer = Solution(arr).cycle
    print(
        buildString {
            append(answer.size).append("\n")
            append(answer.joinToString("\n"))
        }
    )
}

private class Solution(val arr: IntArray) {

    val n = arr.size
    val cycle = mutableListOf<Int>()
    val visited = BooleanArray(n)

    init {
        for (i in 1..<n) {
            if (!visited[i]) dfs(i)
        }

        cycle.sort()
    }

    private fun dfs(curr: Int, path: MutableSet<Int> = mutableSetOf()): Int? {
        visited[curr] = true
        path.add(curr)

        val next = arr[curr]
        if (curr == next) {
            cycle.add(curr)
            return null
        }

        if (visited[next]) {
            return if (path.contains(next)) {
                cycle.add(curr)
                next
            } else null
        }

        return dfs(next, path)?.let { csp -> // cycle start point
            cycle.add(curr)

            if (curr != csp) csp
            else null
        }
    }
}