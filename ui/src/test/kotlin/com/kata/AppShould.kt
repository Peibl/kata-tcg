package com.kata

import com.kata.domain.GameStatus
import com.kata.domain.HumanStatus
import com.kata.domain.MachineStatus
import com.kata.ui.Screen
import com.kata.useCases.StartGame
import io.mockk.*
import org.junit.jupiter.api.Test

class AppShould {
    @Test
    fun `show a welcome message on start`() {
        every { startGame.execute() } returns statusWith()
        App(screen, keyboard, startGame).run()

        verify { screen.printLine("Welcome to the Game, press any key to start") }
    }

    @Test
    fun `start a new game when the user press any key after the welcome message`() {
        every { startGame.execute() } returns statusWith()
        App(screen, keyboard, startGame).run()

        verifyOrder {
            screen.printLine("Welcome to the Game, press any key to start")
            keyboard.readKey()
            startGame.execute()
        }
    }

    @Test
    fun `show the initial health after create a game`() {
        val initialHealth = 30
        every { startGame.execute() } returns statusWith(initialHealth)

        App(screen, keyboard, startGame).run()

        verifyOrder {
            startGame.execute()
            screen.printLine("Health: $initialHealth")
        }
    }

    @Test
    fun `show the initial mana after create a game`() {
        val initialMana = 0
        every { startGame.execute() } returns statusWith(initialMana = initialMana)

        App(screen, keyboard, startGame).run()

        verifyOrder {
            startGame.execute()
            screen.printLine("Mana: $initialMana")
        }
    }

    @Test
    fun `show the initial hand after create a game`() {
        val cards = listOf(5, 3, 2)
        every { startGame.execute() } returns statusWith(cards = cards)

        App(screen, keyboard, startGame).run()

        verify { screen.printLine("Card 1: 5") }
        verify { screen.printLine("Card 2: 3") }
        verify { screen.printLine("Card 3: 2") }
    }

    private fun statusWith(
        initialHealth: Int = 30,
        initialMana: Int = 0,
        cards: List<Int> = listOf(1, 1, 1),
        remainingCards: Int = 0
    ): GameStatus {
        val humanStatus = HumanStatus(initialHealth, initialMana, cards, remainingCards)
        val machineStatus = MachineStatus(initialHealth)
        return GameStatus(humanStatus, machineStatus)
    }

    private val screen = mockk<Screen>(relaxed = true)
    private val keyboard = mockk<Keyboard>(relaxed = true)
    private val startGame = mockk<StartGame>(relaxed = true)
}
