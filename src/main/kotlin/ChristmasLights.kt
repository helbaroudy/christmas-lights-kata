package org.example

import kotlin.math.max

typealias Matrix = MutableList<MutableList<Int>>

class ChristmasLights {
    val lights = MutableList(1000) { MutableList(1000) { 0 } }

    fun turnOn(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        setValue(fromX, toX, fromY, toY) { i, j -> lights[i][j] + 1 }
    }

    fun turnOff(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        setValue(fromX, toX, fromY, toY) { i, j -> max(0, lights[i][j] - 1) }
    }

    fun toggle(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        setValue(fromX, toX, fromY, toY) { i, j -> lights[i][j].toggle() }
    }

    private fun setValue(fromX: Int, toX: Int, fromY: Int, toY: Int, value: (Int, Int) -> Int) {
        for (i in fromX until toX) {
            for (j in fromY until toY) {
                lights[i][j] = value(i, j)
            }
        }
    }

    fun printm() {
        for (i in 0 until lights.size) {
            for (j in 0 until lights.size) {
                print(lights[i][j])
            }
            println()
        }

    }
}

private fun Int.toggle(): Int =
    if (this == 0) 1
    else 0



