package com.kata.domain

class Game(private val human: Human, private val machine: Machine) {
    fun start() {
        human.pickCards(3)
    }

    fun playHumanTurn(cardValues: List<Int>) {
        this.machine.reduceHealth(human.playCards(cardValues))
    }

    fun getStatus(): GameStatus {
        val humanStatus = human.getStatus()
        val machineStatus = machine.getStatus()
        return GameStatus(humanStatus, machineStatus)
    }

    companion object {
        fun new(): Game {
            val human = Human(0, 30, Deck())
            val machine = Machine()
            return Game(human, machine)
        }
    }
}





