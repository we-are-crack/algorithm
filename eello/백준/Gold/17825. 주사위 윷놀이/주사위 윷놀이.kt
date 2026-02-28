import java.util.StringTokenizer
import kotlin.math.max

private var answer = 0

fun main() {
    val st = StringTokenizer(readln())
    val diceValues = IntArray(10) { st.nextToken().toInt() }

    val diceYutnori = DiceYutnori()
    dfs(Array(4) { diceYutnori.start }, diceValues, 0, 0)
    print(answer)
}

private fun dfs(state: Array<DiceYutnori.Section>, diceValues: IntArray, turn: Int, totalScore: Int) {
    answer = max(answer, totalScore)

    if (diceValues.size == turn) return
    if (totalScore + 40 * (10 - turn) <= answer) return

    for ((i, cur) in state.withIndex()) {
        if (cur.isEnd) continue

        val next = cur.next(diceValues[turn])
        if (!next.use || next.isEnd) {
            state[i] = next
            next.use()
            cur.unuse()

            dfs(state, diceValues, turn + 1, totalScore + next.score)

            next.unuse()
            cur.use()
            state[i] = cur
        }
    }
}

private class DiceYutnori {

    val start: Section = RedSection(0)
    val end: Section = RedSection(0, true)

    init {
        val center = RedSection(25)
        var pre = start
        for (i in 1..20) {
            val score = i * 2
            val cur = if (score <= 30 && score % 10 == 0) BlueSection(score) else RedSection(score)
            pre.connect(cur)
            pre = cur

            if (cur is BlueSection) {
                when (score) {
                    10 -> {
                        cur.connectBlueArrow(RedSection(13))
                            .connect(RedSection(16))
                            .connect(RedSection(19))
                            .connect(center)
                    }

                    20 -> {
                        cur.connectBlueArrow(RedSection(22))
                            .connect(RedSection(24))
                            .connect(center)
                    }

                    30 -> {
                        cur.connectBlueArrow(RedSection(28))
                            .connect(RedSection(27))
                            .connect(RedSection(26))
                            .connect(center)
                    }
                }
            }

            if (score == 40) {
                center.connect(RedSection(30))
                    .connect(RedSection(35))
                    .connect(cur)
                    .connect(end)
            }
        }
    }

    interface Section {

        val score: Int
        val isEnd: Boolean
        var use: Boolean
        val cache: Array<Section?>

        fun next(diceValue: Int, isStart: Boolean = true): Section
        fun connect(next: Section): Section
        fun use() {
            use = true
        }

        fun unuse() {
            use = false
        }
    }

    class RedSection(override val score: Int, override val isEnd: Boolean = false) : Section {

        override var use: Boolean = false
        override val cache: Array<Section?> = arrayOfNulls(6)

        override fun next(diceValue: Int, isStart: Boolean): Section {
            if (isEnd) return this
            return cache[diceValue]
                ?: cache[1]!!.next(diceValue - 1, false).also { cache[diceValue] = it }
        }

        override fun connect(next: Section): Section {
            cache[1] = next
            return next
        }
    }

    class BlueSection(override val score: Int) : Section {

        override var use: Boolean = false
        override val isEnd: Boolean = false
        override val cache: Array<Section?> = arrayOfNulls(6)
        private val cacheWhenBlueStart = arrayOfNulls<Section>(6)

        override fun next(diceValue: Int, isStart: Boolean): Section {
            return when (isStart) {
                true -> cacheWhenBlueStart[diceValue]
                    ?: cacheWhenBlueStart[1]!!.next(diceValue - 1, false).also { cacheWhenBlueStart[diceValue] = it }

                false -> cache[diceValue]
                    ?: cache[1]!!.next(diceValue - 1, false).also { cache[diceValue] = it }
            }
        }

        override fun connect(next: Section): Section {
            cache[1] = next
            return next
        }

        fun connectBlueArrow(next: Section): Section {
            cacheWhenBlueStart[1] = next
            return next
        }
    }
}