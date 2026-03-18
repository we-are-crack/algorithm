import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

private const val INF = 1_000_000_001
private var maxAns = -INF
private var minAns = INF

fun main() {
    val n = readln().toInt()

    var st = StringTokenizer(readln())
    val seq = IntArray(n) { st.nextToken().toInt() }

    st = StringTokenizer(readln())
    val stock = IntArray(4) { st.nextToken().toInt() }

    dfs(seq, stock, 1, seq[0])

    println(maxAns)
    print(minAns)
}

private fun dfs(seq: IntArray, stock: IntArray, index: Int, result: Int) {
    if (index == seq.size) {
        maxAns = max(maxAns, result)
        minAns = min(minAns, result)
        return
    }

    for (i in stock.indices) {
        if (stock[i] == 0) {
            continue
        }

        stock[i]--
        when (i) {
            0 -> dfs(seq, stock, index + 1, result + seq[index])
            1 -> dfs(seq, stock, index + 1, result - seq[index])
            2 -> dfs(seq, stock, index + 1, result * seq[index])
            3 -> dfs(seq, stock, index + 1, result / seq[index])
        }
        stock[i]++
    }
}