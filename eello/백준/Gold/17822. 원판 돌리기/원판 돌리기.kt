import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val t = st.nextToken().toInt()

    val initState = Array(n) {
        val line = StringTokenizer(readln())
        IntArray(m) { line.nextToken().toInt() }
    }

    val discSystem = DiscSystem(n, m, initState)
    repeat(t) {
        val rotate = StringTokenizer(readln())
        val x = rotate.nextToken().toInt()
        val d = rotate.nextToken().toInt()
        val k = rotate.nextToken().toInt()

        discSystem.process(x, d, k)
    }

    print(discSystem.sum)
}

private class DiscSystem(val n: Int, val m: Int, initState: Array<IntArray>) {

    var sum: Int = 0 // 모든 디스크에 적힌 수의 합
        private set
    private var count: Int = n * m // 디스크에 적힌 수의 개수
    val avg get() = sum.toDouble() / count

    private val discs = Array(n + 1) { r ->
        val disc = Disc()
        if (r > 0) {
            initState[r - 1].forEach { num ->
                disc.addNumber(num)
                sum += num
            }
        }

        disc
    }

    fun process(x: Int, d: Int, k: Int) {
        // 1. 번호가 x의 배수인 원판을 d 방향으로 k 칸 회전
        rotateAll(x, d, k)

        // 2. (지워야할) 인접한 같은 수들의 위치 모음
        val targets = getRemoveTargets()

        // 3. 지워야할 인접한 수들이 있으면 지우고 없으면 평균값을 기준으로 재조정
        if (targets.isNotEmpty()) removeTargets(targets)
        else adjust()
    }

    private fun rotateAll(x: Int, d: Int, k: Int) {
        for (i in x..n step x) {
            discs[i].rotate(d, k)
        }
    }

    private fun getRemoveTargets(): List<Pair<Int, Int>> {
        val removeTargets = mutableListOf<Pair<Int, Int>>()
        val visited = Array(n + 1) { BooleanArray(m) }
        for (r in 1..n) {
            for (c in 0..<m) {
                if (discs[r][c] == 0 || visited[r][c]) continue
                removeTargets.addAll(getRemoveTargets(r, c, visited))
            }
        }

        return removeTargets
    }

    private fun getRemoveTargets(r: Int, c: Int, visited: Array<BooleanArray>): List<Pair<Int, Int>> {
        val targetNumber = discs[r][c]
        val removeTarget = mutableListOf<Pair<Int, Int>>()

        val que = ArrayDeque<Pair<Int, Int>>().apply { add(r to c) }
        visited[r][c] = true

        val dir = arrayOf(
            arrayOf(-1, 0), // UP
            arrayOf(0, 1),  // RIGHT
            arrayOf(1, 0),  // DOWN
            arrayOf(0, -1)  // LEFT
        )

        while (que.isNotEmpty()) {
            val cur = que.removeFirst()
            removeTarget.add(cur)

            val (cr, cc) = cur
            for (d in dir) {
                val nr = cr + d[0]

                var nc = cc + d[1]
                nc = if (nc < 0) m - 1 else if (nc >= m) 0 else nc

                if (nr !in 1..n || discs[nr][nc] != targetNumber || visited[nr][nc]) {
                    continue
                }

                que.add(nr to nc)
                visited[nr][nc] = true
            }
        }

        return if (removeTarget.size > 1) removeTarget else emptyList()
    }

    private fun removeTargets(targets: List<Pair<Int, Int>>) {
        targets.forEach { (r, c) ->
            sum -= discs[r][c]
            count--
            discs[r][c] = 0
        }
    }

    private fun adjust() {
        val curAvg = avg
        for (r in 1..n) {
            for (c in 0..<m) {
                var value = discs[r][c]
                if (value == 0) continue

                if (value > curAvg) {
                    value--
                    sum--
                } else if (value < curAvg) {
                    value++
                    sum++
                }

                discs[r][c] = value
            }
        }
    }

    class Disc() {

        // 0번째 인덱스가 12시 기준, 삭제된 숫자 = 0
        private val nums = ArrayDeque<Int>()

        operator fun set(index: Int, value: Int) {
            nums[index] = value
        }

        operator fun get(index: Int): Int = nums[index]

        fun addNumber(num: Int) {
            nums.addLast(num)
        }

        fun rotate(d: Int, k: Int) {
            when (d) {
                0 -> rotateClockwise(k)
                1 -> rotateCounterClockwise(k)
            }
        }

        private fun rotateClockwise(k: Int) { // 시계 방향 회전
            repeat(k) { nums.addFirst(nums.removeLast()) }
        }

        private fun rotateCounterClockwise(k: Int) { // 반시계 방향 회전
            repeat(k) { nums.addLast(nums.removeFirst()) }
        }
    }
}