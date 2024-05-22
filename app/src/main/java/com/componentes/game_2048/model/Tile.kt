package com.componentes.game_2048.model

data class Tile constructor(val number: Int, val id: Int){

    companion object{
        var tileIdCounter = 0
    }

    constructor(number: Int): this(number, tileIdCounter++)

    operator fun times(operand: Int): Tile = Tile(number * operand)
}
