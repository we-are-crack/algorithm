fun main() {
    val str = readln()
    val boom = readln()

    val answer = CharArray(str.length)
    var lastIndex = 0
    for (ch in str) {
        answer[lastIndex] = ch
        if (answer.endsWith(lastIndex, boom)) {
            lastIndex -= boom.length
        }

        lastIndex++
    }

    print(if (lastIndex == 0) "FRULA" else answer.subString(end = lastIndex))
}

private fun CharArray.endsWith(lastIndex: Int, str: String): Boolean {
    val len = str.length
    if (lastIndex < len - 1) return false

    val index = lastIndex - len + 1
    repeat(len) { t ->
        if (this[index + t] != str[t])
            return false
    }
    return true
}

private fun CharArray.subString(start: Int = 0, end: Int = size): String {
    return buildString {
        for (i in start..<end) {
            append(this@subString[i])
        }
    }
}