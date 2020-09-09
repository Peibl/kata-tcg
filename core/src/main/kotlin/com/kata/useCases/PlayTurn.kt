package com.kata.useCases

import com.kata.domain.Games

class PlayTurn(private val games: Games) {
    fun execute(cardValues: List<Int>) {
        games.current?.playHumanTurn(cardValues)
    }
}
