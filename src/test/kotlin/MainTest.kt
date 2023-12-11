import org.example.ChristmasLights
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


const val WIDTH = 10
const val HEIGHT = 10
private operator fun Any.rangeTo(i: Int) {
    TODO("Not yet implemented")
}

class MainTest {
    @Test
    fun `christmasLights should init a lights grid off`() {
        val christmasLights = ChristmasLights()

        val actual = christmasLights.lights

        assertEquals(List(1000) { List(1000) { 0 } }, actual)
    }

    @Test
    fun `turn on single light should update the grid accordingly`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 1, 1)
        val actual = christmasLights.lights

        assertEquals(1, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 1 } })    }

    @Test
    fun `turn on all lights should update the whole grid`() {
        val christmasLights = ChristmasLights()

        christmasLights.turnOn(0, 0, 1000, 1000)
        val actual = christmasLights.lights

        assertEquals(List(1000) { List(1000) { 1 } }, actual)
    }

    @Test
    fun `turn off single light should update the grid accordingly`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 1000, 1000)

        christmasLights.turnOff(0, 0, 1, 1)
        val actual = christmasLights.lights

        assertEquals(0, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 0 } })
    }

    @Test
    fun `turning on and off should go back to initial state`(){
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 1000, 1000)
        christmasLights.turnOff(0, 0, 1000, 1000)

        val actual = christmasLights.lights

        assertEquals(ChristmasLights().lights, actual)
    }
}
