import java.util.StringTokenizer

private data class Pos(val r: Int, val c: Int) {
    operator fun plus(pos: Pos) = Pos(r + pos.r, c + pos.c)
}

private val directions = arrayOf(
    Pos(-1, -1),
    Pos(-1, 0),
    Pos(-1, 1),
    Pos(0, 1),
    Pos(1, 1),
    Pos(1, 0),
    Pos (1, -1),
    Pos(0, -1)
)

private class Tree(var age: Int) {
    fun die(): Int = age / 2
    fun grow() { age++ }
}

private class Section(val pos: Pos, val addNutrientAmount: Int) {

    private var curNutrient = 5
    private val trees = ArrayDeque<Tree>() // <- 젊은 나무 | 늙은 나무 ->

    val treeCount get() = trees.size

    fun springAndSummer() {
        // spring
        var i = 0
        while (i in trees.indices && trees[i].age <= curNutrient) {
            curNutrient -= trees[i].age
            trees[i].grow()
            i++
        }

        // summer
        repeat(trees.size - i) {
            val tree = trees.removeLast()
            curNutrient += tree.die()
        }
    }

    fun fall(): List<Pos> {
        val around = ArrayList<Pos>()
        for (tree in trees) {
            if (tree.age % 5 == 0) {
                for (dir in directions) {
                    around.add(pos + dir)
                }
            }
        }
        return around
    }

    fun winter() {
        curNutrient += addNutrientAmount
    }

    fun addTree(tree: Tree) {
        trees.addFirst(tree)
    }
}

private class Land(val n: Int, val addNutrientAmount: Array<IntArray>) {

    val sections = Array(n) { r ->
        Array(n) { c ->
            Section(Pos(r, c), addNutrientAmount[r][c])
        }
    }

    val treeCount: Int get() = sections.sumOf { line -> line.sumOf { section -> section.treeCount } }

    fun year() {
        springAndSummer()
        fall()
        winter()
    }

    private fun springAndSummer() {
        sections.forEach { line -> line.forEach { section -> section.springAndSummer() } }
    }

    private fun fall() {
        val reproduce = ArrayList<Pos>()
        sections.forEach { line ->
            line.forEach { section ->
                section.fall().forEach { next ->
                    if (isInBounds(next)) reproduce.add(next)
                }
            }
        }

        for ((r, c) in reproduce) {
            sections[r][c].addTree(Tree(1))
        }
    }

    private fun winter() {
        sections.forEach { line -> line.forEach { section -> section.winter() } }
    }

    // (r, c)에 age 나이의 나무 심음
    fun plant(r: Int, c: Int, age: Int) {
        sections[r][c].addTree(Tree(age))
    }

    private fun isInBounds(pos: Pos) = pos.r in 0..<n && pos.c in 0..<n
}

fun main() {
    val st1 = StringTokenizer(readln())
    val n = st1.nextToken().toInt()
    val m = st1.nextToken().toInt()
    val k = st1.nextToken().toInt()

    val nutrient = Array(n) {
        val line = StringTokenizer(readln())
        IntArray(n) { line.nextToken().toInt() }
    }
    val land = Land(n, nutrient)

    repeat(m) {
        val st2 = StringTokenizer(readln())
        val r = st2.nextToken().toInt() - 1
        val c = st2.nextToken().toInt() - 1
        val age = st2.nextToken().toInt()

        land.plant(r, c, age)
    }

    repeat(k) {
        land.year()
    }

    print(land.treeCount)
}