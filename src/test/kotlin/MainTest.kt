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
    fun `turn on single light twice should increase the brightness`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 1, 1)
        christmasLights.turnOn(0, 0, 1, 1)
        val actual = christmasLights.lights

        assertEquals(2, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 2 } })
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
    fun `turn off should decrease brightness by 1`() {
        val christmasLights = ChristmasLights()

        christmasLights.turnOn(0, 0, 1, 1)
        christmasLights.turnOn(0, 0, 1, 1)
        christmasLights.turnOff(0, 0, 1, 1)
        val actual = christmasLights.lights

        assertEquals(1, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 1 } })
    }

    @Test
    fun `turn off should never lower brightness below 0`() {
        val christmasLights = ChristmasLights()


        christmasLights.turnOff(0, 0, 1, 1)
        christmasLights.turnOff(0, 0, 1, 1)
        val actual = christmasLights.lights

        assertEquals(0, actual[0][0])
        assertEquals(1000000, actual.sumOf { row -> row.count { column -> column == 0 } })
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
        christmasLights.toggle(0, 0, 1, 1)
        val actual = christmasLights.lights

        assertEquals(1, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 1 } })

    }

    @Test
    fun `toggle an on light should switch it off`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 1000, 1000)
        christmasLights.toggle(0, 0, 1, 1)

        val actual = christmasLights.lights

        assertEquals(0, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 0 } })
    }

    @Test
    fun `a`(){
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(887, 9, 959, 629)
        christmasLights.turnOn(454, 398, 844, 448)
        christmasLights.turnOff(539, 243, 559, 965)
        christmasLights.turnOff(370, 819, 676, 868)
        christmasLights.turnOff(145, 40, 370, 997)
        christmasLights.turnOff(301, 3, 808, 453)
        christmasLights.turnOn(351, 678, 951, 908)
        christmasLights.toggle(720, 196, 897, 994)
        christmasLights.toggle(831, 394, 904, 860)

        //assertEquals(228698, christmasLights.lights.sumOf { row -> row.count { column -> column == 1 } })

    }
}
