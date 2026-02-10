import java.util.*

private class LadderGame(val n: Int, val h: Int, val ladder: Array<IntArray>) {

    var answer: Int = 4

    fun solve(col: Int = 1, height: Int = 1, k: Int = 0) {
        if (k >= answer) return

        if (isValid()) {
            answer = k
            return
        }

        if (k == 3) return

        for (i in col..<n) {
            val sj = if (i == col) height else 0
            for (j in sj..h) {
                val right = i + 1
                if (ladder[i][j] == 0 && right <= n && ladder[right][j] == 0) {
                    ladder[i][j] = 1
                    ladder[right][j] = -1
                    solve(i, j + 1, k + 1)
                    ladder[i][j] = 0
                    ladder[right][j] = 0
                }
            }
        }
    }

    private fun isValid(): Boolean {
        for (start in 1..n) {
            var col = start
            for (y in 1..h) {
                if (ladder[col][y] != 0) {
                    col += ladder[col][y]
                }
            }

            if (start != col) return false
        }

        return true
    }

}

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val h = st.nextToken().toInt()

    // ladder[i][j] = i번째 세로선에 j번째 높이에 가로선이 놓여있는지
    // -1 => 왼쪽 가로선, 1 => 오른쪽 가로선
    val ladder = Array(n + 1) { IntArray(h + 1) }
    repeat(m) {
        val line = StringTokenizer(readln())
        val y = line.nextToken().toInt()
        val left = line.nextToken().toInt()
        val right = left + 1

        ladder[left][y] = 1
        ladder[right][y] = -1
    }

    val ladderGame = LadderGame(n, h, ladder)
    ladderGame.solve()
    print(if (ladderGame.answer > 3) -1 else ladderGame.answer)
}