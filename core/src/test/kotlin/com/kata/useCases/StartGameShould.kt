package com.kata.useCases

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class StartGameShould {
    @Test
    fun `return the health of the human player`() {
        val status = StartGame().execute()

        Assertions.assertThat(status.human.health).isEqualTo(30)
    }

    @Test
    fun `return the mana of the human player`() {
        val status = StartGame().execute()

        Assertions.assertThat(status.human.mana).isEqualTo(0)
    }

    @Test
    fun `return the initial hand of the human player`() {
        val status = StartGame().execute()

        Assertions.assertThat(status.human.hand.size).isEqualTo(3)
    }

    @Test
    fun `decrease the human deck in 3 cards`() {
        val status = StartGame().execute()

        Assertions.assertThat(status.human.remainingCards).isEqualTo(17)
    }
}
