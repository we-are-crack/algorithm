import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() {
    var st = StringTokenizer(readln())
    val maxHeight = st.nextToken().toInt()
    val width = st.nextToken().toInt()

    st = StringTokenizer(readln())
    val height = IntArray(width) { st.nextToken().toInt() }

    // dp[i] = i 이상의 인덱스 중 가장 큰 높이
    val dp = IntArray(width) { 0 }.apply { this[lastIndex] = height[lastIndex] }
    for (i in dp.lastIndex - 1 downTo 1) {
        dp[i] = max(height[i], dp[i + 1])
    }

    var l = 0
    var r = 1
    var answer = 0
    while (r < width - 1) {
        val h = min(height[l], dp[r])

        if (height[r] < h)
            answer += h - height[r]

        if (height[l] < height[r] || height[r] == dp[r]) {
            // 왼쪽 벽이 바뀌는 시점
            // 1. height[l] < height[r]: 현재 왼쪽 벽보다 오른쪽 벽의 높이가 높을 때
            //  ex: 3(l) 1 1 4(r) 2 2 => 3 1 1 4(l) 2 2 .. r
            // 2. height[r] == dp[r]: 현재 오른쪽 벽이 그 뒤의 모든 벽보다 높이가 높을 때
            //  ex: 1(l) 2 3(r) 1 1 2 => 1 2 3(l) 1 1 2 .. r
            l = r
        }

        r++
    }

    print(answer)
}