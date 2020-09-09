package com.kata

import com.kata.ui.Screen
import com.kata.useCases.StartGame

class App(private val screen: Screen, private val keyboard: Keyboard, private val startGame: StartGame) {
    fun run() {
        screen.printLine("Welcome to the Game, press any key to start")
        keyboard.readKey()
        val status = startGame.execute()
        screen.printLine("Health: ${status.human.health}")
        screen.printLine("Mana: ${status.human.mana}")
        screen.printLine("Card 1: ${status.human.hand[0]}")
        screen.printLine("Card 2: ${status.human.hand[1]}")
        screen.printLine("Card 3: ${status.human.hand[2]}")
    }
}
