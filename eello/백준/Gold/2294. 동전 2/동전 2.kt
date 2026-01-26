import kotlin.math.min

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val coins = List(n) { readln().toInt() }

    val INF = 100_001
    val dp = IntArray(k + 1) { INF }
    dp[0] = 0

    for (coin in coins) {
        for (i in coin..k) {
            dp[i] = min(dp[i], dp[i - coin] + 1)
        }
    }

    val answer = if (dp[k] >= INF) -1 else dp[k]
    print(answer)
}