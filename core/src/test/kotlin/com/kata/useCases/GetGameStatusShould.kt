package com.kata.useCases

import com.kata.domain.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetGameStatusShould {
    @Test
    fun `return the health of the human player`() {
        val humanHealth = 20
        currentGame.current = aGameWith(humanHealth = humanHealth)

        val status = GetGameStatus(currentGame).execute()

        Assertions.assertThat(status.human.health).isEqualTo(humanHealth)
    }

    @Test
    fun `return the mana of the human player`() {
        val humanMana = 0
        currentGame.current = aGameWith(humanMana = humanMana)

        val status = GetGameStatus(currentGame).execute()

        Assertions.assertThat(status.human.mana).isEqualTo(humanMana)
    }

    @Test
    fun `return the remaining cards of the human player`() {
        val deck = Deck()
        deck.pickCards(AMOUNT_TO_PICK)
        currentGame.current = aGameWith(deck = deck)

        val status = GetGameStatus(currentGame).execute()

        Assertions.assertThat(status.human.remainingCards).isEqualTo(INITIAL_CARD_AMOUNT - AMOUNT_TO_PICK)
    }

    @Test
    fun `return the hand of the human player`() {
        val deck = Deck()
        currentGame.current = aGameWith(deck = deck)

        val status = GetGameStatus(currentGame).execute()

        Assertions.assertThat(status.human.hand).isEqualTo(mutableListOf(0, 0, 1))
    }

    private fun aGameWith(humanMana: Int = 0, humanHealth: Int = 30, deck: Deck = Deck()): Game {
        val human = Human(humanMana, humanHealth, deck)
        return Game(human, Machine())
    }

    @BeforeEach
    fun setupProviderMock() {
        currentGame.current = null
    }

    private val currentGame = Games()
    private val AMOUNT_TO_PICK = 8
    private val INITIAL_CARD_AMOUNT = Deck().size()
}
