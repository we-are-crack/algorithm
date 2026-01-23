fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map(String::toInt)

    val inc = IntArray(n) { 1 } // 정방향로 증가하는 LIS 길이
    val dec = IntArray(n) { 1 } // 역방향로 증가하는 LIS 길이

    val lis = mutableListOf<Int>()
    for (i in 0 until n) { // 정방향 LIS 계산
        var insertPoint = lis.binarySearch(arr[i])
        if (insertPoint < 0) insertPoint = -(insertPoint + 1)

        if (insertPoint == lis.size) lis.add(arr[i])
        else lis[insertPoint] = arr[i]

        inc[i] = insertPoint + 1
    }

    lis.clear()
    for (i in n - 1 downTo 0) { // 역방향 LIS 계산
        var insertPoint = lis.binarySearch(arr[i])
        if (insertPoint < 0) insertPoint = -(insertPoint + 1)

        if (insertPoint == lis.size) lis.add(arr[i])
        else lis[insertPoint] = arr[i]

        dec[i] = insertPoint + 1
    }

    val answer = arr.indices.maxOf { i -> inc[i] + dec[i] - 1 }
    print(answer)
}