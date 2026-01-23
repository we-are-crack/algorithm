import kotlin.math.max

fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map(String::toInt)

    val inc = IntArray(n) { 1 } // 정방향로 증가하는 LIS 길이
    val dec = IntArray(n) { 1 } // 역방향로 증가하는 LIS 길이

    for (i in 0 until n) {
        for (j in 0 until i) { // 정방향 LIS 계산
            if (arr[i] > arr[j]) {
                inc[i] = max(inc[i], inc[j] + 1)
            }
        }

        for (j in n - i until n) { // 역방향 LIS 계산
            if (arr[n - 1 - i] > arr[j]) {
                dec[n - 1 - i] = max(dec[n - 1 - i], dec[j] + 1)
            }
        }
    }

    val answer = arr.indices.maxOf { i -> inc[i] + dec[i] - 1 }
    print(answer)
}