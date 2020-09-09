package com.kata.useCases

import com.kata.domain.GameStatus
import com.kata.domain.Games

class GetGameStatus(val games: Games) {
    fun execute(): GameStatus {
        return games.current!!.getStatus()
    }
}
