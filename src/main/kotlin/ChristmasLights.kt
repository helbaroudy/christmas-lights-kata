package org.example

typealias Matrix = MutableList<MutableList<Int>>

class ChristmasLights {
    val lights = MutableList(1000) { MutableList(1000) { 0 } }

    fun turnOn(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        setValue(fromX, toX, fromY, toY) { _, _ -> 1 }
    }

    fun turnOff(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        setValue(fromX, toX, fromY, toY) { _, _ -> 0 }
    }

    fun switch(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        setValue(fromX, toX, fromY, toY) { i, j -> lights[i][j].switch() }
    }

    private fun setValue(fromX: Int, toX: Int, fromY: Int, toY: Int, value: (Int, Int) -> Int) {
        for (i in fromX until toX) {
            for (j in fromY until toY) {
                lights[i][j] = value(i, j)
            }
        }
    }

}

private fun Int.switch(): Int =
    if (this == 0) 1
    else 0



