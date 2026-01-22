import kotlin.math.max

fun main() {
    val str1 = readln()
    val str2 = readln()
    val dp = Array(str1.length + 1) { IntArray(str2.length + 1) }

    for (i in str1.indices) {
        for (j in str2.indices) {
            dp[i + 1][j + 1] = if (str1[i] == str2[j]) {
                dp[i][j] + 1
            } else {
                max(dp[i][j + 1], dp[i + 1][j])
            }
        }
    }

    print(dp[str1.length][str2.length])
}