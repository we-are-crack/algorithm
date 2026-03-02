import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    var shark: Sea.Shark? = null
    val fish = arrayOfNulls<Sea.Fish>(Sea.SIZE * Sea.SIZE)
    repeat(Sea.SIZE) { r ->
        val st = StringTokenizer(readln())
        repeat(Sea.SIZE) { c ->
            val id = st.nextToken().toInt()
            val d = st.nextToken().toInt() - 1

            if (r == 0 && c == 0) {
                shark = Sea.Shark(r = r, c = c, d = d, eat = id)
            } else fish[id - 1] = Sea.Fish(id = id, r = r, c = c, d = d)
        }
    }

    val sea = Sea(shark!!, fish)
    sea.moveFish()
    print(dfs(sea))
}

private fun dfs(sea: Sea): Int {
    var ret = sea.shark.eat

    for (step in 1..3) {
        var nr = 0; var nc = 0
        with(sea.shark) {
            nr = r + (dir[d][0] * step)
            nc = c + (dir[d][1] * step)
        }

        if (!sea.isInBounds(nr, nc)) {
            break
        }

        if (sea[nr][nc] != null) {
            val clone = sea.clone()
            with(clone) {
                shark.eat(map[nr][nc]!!)
                moveFish()
            }

            ret = max(ret, dfs(clone))
        }
    }

    return ret
}

private val dir = arrayOf(
    intArrayOf(-1, 0),
    intArrayOf(-1, -1),
    intArrayOf(0, -1),
    intArrayOf(1, -1),
    intArrayOf(1, 0),
    intArrayOf(1, 1),
    intArrayOf(0, 1),
    intArrayOf(-1, 1),
)

private class Sea(val shark: Shark, val fish: Array<Fish?>) {

    val map = Array(SIZE) { arrayOfNulls<Fish>(SIZE) }

    init {
        map[shark.r][shark.c] = shark
        for (f in fish) {
            f?.let { map[it.r][it.c] = it }
        }
    }

    operator fun get(i: Int) = map[i]

    fun clone(): Sea {
        val cloneFish = Array(fish.size) { i -> fish[i]?.clone() }
        return Sea(shark.clone(), cloneFish)
    }

    fun isInBounds(r: Int, c: Int) = r in 0..<SIZE && c in 0..<SIZE

    fun moveFish() {
        for (f in fish) {
            f?.move()
        }
    }

    fun Shark.eat(fish: Fish) {
        map[r][c] = null

        eat += fish.id
        r = fish.r
        c = fish.c
        d = fish.d
        map[r][c] = this

        fish.eaten()
    }

    private fun Fish.eaten() {
        fish[id - 1] = null
    }

    private fun Fish.move() {
        for (i in 0..7) {
            val nd = (d + i) % dir.size
            val nr = r + dir[nd][0]
            val nc = c + dir[nd][1]

            if (isInBounds(nr, nc)) {
                if (map[nr][nc] == null) {
                    map[r][c] = null

                    r = nr
                    c = nc
                    d = nd
                    map[r][c] = this
                    break
                }

                if (map[nr][nc] !is Shark) {
                    d = nd
                    swapWith(map[nr][nc]!!)
                    break
                }
            }
        }
    }

    private fun Fish.swapWith(other: Fish) {
        val tr = r
        val tc = c

        r = other.r
        c = other.c
        map[r][c] = this

        other.r = tr
        other.c = tc
        map[tr][tc] = other
    }

    companion object {
        const val SIZE = 4
    }

    open class Fish(val id: Int, var r: Int, var c: Int, var d: Int) {

        open fun clone(): Fish = Fish(id, r, c, d)

        override fun toString(): String {
            return "Fish(id=$id, r=$r, c=$c, d=$d)"
        }
    }

    class Shark(r: Int, c: Int, d: Int, var eat: Int) : Fish(-1, r, c, d) {

        override fun clone(): Shark = Shark(r, c, d, eat)

        override fun toString(): String {
            return "Shark(r=$r, c=$c, d=$d, eat=$eat)"
        }
    }
}