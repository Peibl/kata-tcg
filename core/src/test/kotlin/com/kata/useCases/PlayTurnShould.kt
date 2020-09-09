package com.kata.useCases

import com.kata.domain.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayTurnShould {
    @Test
    fun `decrease machine health in 5 when human card played is five`() {
        val games = Games(game(6))
        PlayTurn(games).execute(listOf(5))

        Assertions.assertThat(games.current!!.getStatus().machineStatus.health).isEqualTo(25)
    }

    @Test
    fun `decrease machine health in 5 when human card played are two and three`() {
        val games = Games(game(6))
        PlayTurn(games).execute(listOf(2, 3))

        Assertions.assertThat(games.current!!.getStatus().machineStatus.health).isEqualTo(25)
    }

    @Test
    fun `can't play without enough mana`() {
        val games = Games(game(1))

        assertThrows<Error> { PlayTurn(games).execute(listOf(5) )}
    }

    @Test
    fun `can't play a card with value higher than 8`() {
        val games = Games(game(100000))
        assertThrows<Error> { PlayTurn(games).execute(listOf(555) )}
    }

    private fun game(mana: Int) = Game(Human(mana, 30, Deck()), Machine())
}
