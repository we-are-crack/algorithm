import java.util.StringTokenizer
import kotlin.math.abs

private enum class Direction(val r: Int, val c: Int) {
    RIGHT(0, 1),
    DOWN(1, 0),
    ;
}

private class Map(val n: Int, val map: Array<IntArray>) {

    fun solve(l: Int): Int {
        var answer = 0
        for (i in 0..<n) {
            if (inspect(i, 0, Direction.RIGHT, l)) answer++
            if (inspect(0, i, Direction.DOWN, l)) answer++
        }
        return answer
    }

    private fun inspect(sr: Int, sc: Int, d: Direction, l: Int): Boolean {
        var r = sr
        var c = sc
        var builtDown = false
        while (true) {
            var nr = r + (d.r * l)
            var nc = c + (d.c * l)

            if (isInBounds(nr, nc) && abs(map[nr][nc] - map[r][c]) == 1) { // 경사로가 필요한 경우
                val diff = map[nr][nc] - map[r][c] // 올라가는 경사로 1, 내려가는 경사로 -1
                if (!(diff > 0 && builtDown) && isBuildable(r, c, d, diff, l)) {
                    r = nr
                    c = nc

                    if (diff < 0) builtDown = true
                    continue
                }
            }

            nr = r + d.r
            nc = c + d.c
            if (isInBounds(nr, nc)) {
                if (map[r][c] == map[nr][nc]) {
                    r = nr
                    c = nc
                    builtDown = false
                } else return false
            } else break
        }

        return true
    }

    // diff > 0: 올라가는 경사로인 경우 현재 칸부터 l 칸이 연속되었는지
    // diff < 0: 내려가는 경사로인 경우 다음 칸부터 l 칸이 연속되었는지
    private fun isBuildable(r: Int, c: Int, d: Direction, diff: Int, l: Int): Boolean {
        var sr = r
        var sc = c
        if (diff < 0) { // 내려가는 경사로인 경우 다음 칸부터
            sr += d.r
            sc += d.c
        }

        var nr = sr + d.r
        var nc = sc + d.c
        repeat(l - 1) { // 시작 칸을 포함하기 때문에 (l-1)번 반복
            if (map[sr][sc] != map[nr][nc]) return false
            nr += d.r
            nc += d.c
        }

        return true
    }

    private fun isInBounds(r: Int, c: Int) = r in 0..<n && c in 0..<n
}

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val l = st.nextToken().toInt()
    val map = Array(n) {
        val line = StringTokenizer(readln())
        IntArray(n) { line.nextToken().toInt() }
    }

    print(Map(n, map).solve(l))
}