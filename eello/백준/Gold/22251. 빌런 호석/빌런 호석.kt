import java.util.StringTokenizer
import kotlin.math.pow

/*
  ㅡ       0
 | |    1    2
  ㅡ       3
 |_|    4    5
          6

각 세그먼트 디스플레이를 비트로 표현
0 = 1110111 = 119
1 = 0100100 = (1 << 2) + (1 << 5) = 36(4+32)
2 = 1011101 = (1 << 0) + (1 << 2) + (1 << 3) + (1 << 4) + (1 << 6) = 93(1+4+8+16+64)
...

점등된 led 조합으로 계산했을 때 만들어지는 수에 대한 연산
디스플레이:  0       1       2       3       4       5       6       7       8       9
비트연산값:  119     36      93      109     46      107     123     37      127     111
 */

private object SegmentDisplay {

    // value[0] = 세그먼트 디스플레이로 숫자 0을 만들기 위해 켜야하는 led 자리 비트의 10진수
    val bitValues = arrayOf(119, 36, 93, 109, 46, 107, 123, 37, 127, 111)

    fun bitValueFrom(display: Int): Int = bitValues[display]

    fun displayValueFrom(led: IntArray): Int {
        var display = 0
        var temp = 1
        for (i in led.lastIndex downTo 0) {
            display += getDisplayValue(led[i]) * temp
            temp *= 10
        }

        return display
    }

    private fun getDisplayValue(bitValue: Int): Int {
        for ((displayVal, bitVal) in bitValues.withIndex()) {
            if (bitVal == bitValue) return displayVal
        }
        return -1
    }
}

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val p = st.nextToken().toInt()
    var x = st.nextToken().toInt()

    var divide = 10.pow(k - 1)
    val led = IntArray(k) {
        val display = x / divide
        x %= divide
        divide /= 10
        SegmentDisplay.bitValueFrom(display)
    }

    print(dfs(led, 0, p, p, n))
}

private fun Int.pow(n: Int): Int = this.toDouble().pow(n).toInt()

/**
 * led: 각 자리 세그먼트 디스플레이 값, led[1] = 36 => 1번째 자리는 세그먼트 디스플레이로 1 표시
 * i: 자리수
 * k: 앞으로 스위칭 가능한 led 수(k = p - 현재까지 스위칭한 led 수)
 * p: 스위칭 가능한 최대 led 수(변하지 않는 값)
 * n: 나올 수 있는 결과의 최대값, 바뀐 값이 n을 초과할 수 없음
 */
private fun dfs(led: IntArray, i: Int, k: Int, p: Int, n: Int): Int {
    if (i == led.size || k == 0) {
        return if (k < p && SegmentDisplay.displayValueFrom(led) in 1..n) 1
        else 0
    }

    var ret = 0
    for (v in SegmentDisplay.bitValues) {
        val switchingCount = (led[i] xor v).countOneBits()
        if (switchingCount <= k) {
            val temp = led[i]
            led[i] = v
            ret += dfs(led, i + 1, k - switchingCount, p, n)
            led[i] = temp
        }
    }

    return ret
}