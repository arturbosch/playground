import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.containsExactly
import ch.tutteli.atrium.api.fluent.en_GB.get
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.expect
import org.junit.jupiter.api.Test

@ExperimentalStdlibApi
class NewIn1370 {

    @Test
    fun `scan function`() {
        val intermediateSums = sequenceOf(1, 2, 3, 4, 5)
            .scan(0) { acc, next -> acc + next }
            .toList()

        expect(intermediateSums).contains(1, 3, 6, 10, 15)
    }

    @Test
    fun `build list`() {
        val result = buildList(5) {
            add(1)
            listOf(2, 3, 4).mapTo(this) { it }
            add(0, 0)
        }

        expect(result).containsExactly(0, 1, 2, 3, 4)
    }

    @Test
    @Suppress("RemoveExplicitTypeArguments") // type inference bug!
    fun `build map`() {
        val map = buildMap<Int, Int> {
            put(1, 1)
        }

        expect(map).contains(1 to 1)
    }

    @Test
    fun `array deque`() {
        val deck = ArrayDeque<Int>()
        deck.addFirst(1)
        deck.add(2)
        deck.addLast(0)

        expect(deck).containsExactly(1, 2, 0)
        expect(deck).get(0).toBe(1)

        deck.removeFirst()
        deck.removeLast()

        expect(deck).toBe(ArrayDeque<Int>().apply { add(2) })
    }

    @Test
    fun `new string builder methods`() {
        val builder = StringBuilder("cb")
        builder[0] = 'a'
        builder.insert(2, 'c')
        // more jvm-equivalent string builder methods ...

        expect(builder.toString()).toBe("abc")
    }

    @Test
    fun `reduce or null`() {
        expect(sequenceOf<Int>().reduceOrNull { acc, next -> acc + next }).toBe(null)
        expect(listOf(1).reduceOrNull { acc, next -> acc + next }).toBe(1)
    }

    @Test
    fun `random or null`() {
        expect(listOf<Int>().randomOrNull()).toBe(null)
    }
}
