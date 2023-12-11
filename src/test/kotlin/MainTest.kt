import org.example.ChristmasLights
import org.example.LightRange
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


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
        christmasLights.turnOn(0, 0, 0, 0)
        val actual = christmasLights.lights

        assertEquals(1, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 1 } })
    }

    @Test
    fun `turn on single light twice should increase the brightness`() {
        val christmasLights = ChristmasLights()
        christmasLights turnOn LightRange(0, 0, 0, 0)
        christmasLights.turnOn(0, 0, 0, 0)
        val actual = christmasLights.lights

        assertEquals(2, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 2 } })
    }

    @Test
    fun `turn on all lights should update the whole grid`() {
        val christmasLights = ChristmasLights()

        christmasLights.turnOn(0, 0, 999, 999)
        val actual = christmasLights.lights

        assertEquals(List(1000) { List(1000) { 1 } }, actual)
    }

    @Test
    fun `turn off single light should update the grid accordingly`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 999, 999)

        christmasLights.turnOff(0, 0, 0, 0)
        val actual = christmasLights.lights

        assertEquals(0, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 0 } })
    }

    @Test
    fun `turn off should decrease brightness by 1`() {
        val christmasLights = ChristmasLights()

        christmasLights.turnOn(0, 0, 0, 0)
        christmasLights.turnOn(0, 0, 0, 0)
        christmasLights.turnOff(0, 0, 0, 0)
        val actual = christmasLights.lights

        assertEquals(1, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == 1 } })
    }

    @Test
    fun `turn off should never lower brightness below 0`() {
        val christmasLights = ChristmasLights()


        christmasLights.turnOff(0, 0, 0, 0)
        christmasLights.turnOff(0, 0, 0, 0)
        val actual = christmasLights.lights

        assertEquals(0, actual[0][0])
        assertEquals(1000000, actual.sumOf { row -> row.count { column -> column == 0 } })
    }


    @Test
    fun `turning on and off should go back to initial state`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 999, 999)
        christmasLights.turnOff(0, 0, 999, 999)

        val actual = christmasLights.lights

        assertEquals(ChristmasLights().lights, actual)
    }


    @Test
    fun `turn on 0,0 through 0,0 would increase the total brightness by 1`() {
        val christmasLights = ChristmasLights()
        christmasLights.turnOn(0, 0, 0, 0)

        val actual = christmasLights.brightness()

        assertEquals(1, actual)

    }

    @Test
    fun `toggle 0,0 through 999,999 would increase the total brightness by 2000000`() {
        val christmasLights = ChristmasLights()
        christmasLights.toggle(0, 0, 999, 999)

        val actual = christmasLights.brightness()

        assertEquals(2000000, actual)

    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 100])
    fun `toggle a light should increase its brightness by 2`(brightness: Int) {
        val christmasLights = ChristmasLights()
        for (i in 0..brightness) {
            christmasLights.turnOn(0, 0, 0, 0)
        }
        val beforeBrightness = christmasLights.lights[0][0]
        christmasLights.toggle(0, 0, 0, 0)

        val actual = christmasLights.lights

        assertEquals(beforeBrightness + 2, actual[0][0])
        assertEquals(1, actual.sumOf { row -> row.count { column -> column == beforeBrightness + 2 } })
    }

    @Test
    fun `a`() {
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
