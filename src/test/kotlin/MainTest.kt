import org.example.ChristmasLights
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


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
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 1 } })
    }

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
    fun `turning on and off should go back to initial state`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 1000, 1000)
        christmasLights.turnOff(0, 0, 1000, 1000)

        val actual = christmasLights.lights

        assertEquals(ChristmasLights().lights, actual)
    }

    @Test
    fun `toggle an off light should switch it on`() {
        val christmasLights = ChristmasLights()
        christmasLights.switch(0, 0, 1, 1)
        val actual = christmasLights.lights

        assertEquals(1, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 1 } })

    }

    @Test
    fun `toggle an on light should switch it off`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 1000, 1000)
        christmasLights.switch(0, 0, 1, 1)

        val actual = christmasLights.lights

        assertEquals(0, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 0 } })
    }
}
