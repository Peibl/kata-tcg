package com.kata.useCases

import com.kata.domain.Game
import com.kata.domain.GameStatus

class StartGame {
    fun execute(): GameStatus {
        val game = Game.new()
        game.start()
        return game.getStatus()
    }
}
