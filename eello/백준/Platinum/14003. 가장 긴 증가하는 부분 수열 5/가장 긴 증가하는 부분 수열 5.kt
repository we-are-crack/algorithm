data class Element(
    val value: Int,
    val index: Int
)

fun main() {
    val n = readln().toInt()
    val seq = readln().split(" ").map { it.toInt() }.toIntArray()
    val lis = mutableListOf<Element>() // Pair = (값, 원본 배열의 인덱스)
    val parent = IntArray(n) // parent[i] = lis[i] 번째 원소의 이전 원소의 원본 인덱스

    seq.forEachIndexed { index, value ->
        val elem = Element(value, index)
        val insertionPoint = lowerBound(lis, value)

        if (insertionPoint == lis.size) lis.add(elem)
        else lis[insertionPoint] = elem

        parent[index] = if (insertionPoint == 0) -1 else lis[insertionPoint - 1].index
    }

    val reconstructedLis = mutableListOf(lis.last().value)
    var i = lis.last().index
    while (parent[i] != -1) {
        i = parent[i]
        reconstructedLis.add(seq[i])
    }

    println(lis.size)
    println(buildString {
        reconstructedLis.reversed().forEach {
            append("$it ")
        }
    })
}

fun lowerBound(sequence: List<Element>, target: Int): Int {
    var insertionPoint = sequence.size
    var l = 0
    var r = sequence.size - 1
    while (sequence.isNotEmpty() && l <= r) {
        val mid = (l + r) / 2
        if (target <= sequence[mid].value) {
            r = mid - 1
            insertionPoint = mid
        } else {
            l = mid + 1
        }
    }

    return insertionPoint
}