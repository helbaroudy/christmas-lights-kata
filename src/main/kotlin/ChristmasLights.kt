package org.example

typealias Matrix = MutableList<MutableList<Int>>

class ChristmasLights {
    val lights = MutableList(1000) { MutableList(1000) { 0 } }

    fun turnOn(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        for (i in fromX until toX) {
            for (j in fromY until toY) {
                lights[i][j] = 1
            }
        }
    }

    fun turnOff(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        for (i in fromX until toX) {
            for (j in fromY until toY) {
                lights[i][j] = 0
            }
        }
    }
}



