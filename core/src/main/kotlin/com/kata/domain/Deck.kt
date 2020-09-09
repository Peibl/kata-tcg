package com.kata.domain

class Deck() {
    private val damageCards = mutableListOf(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8)

    fun size() = damageCards.size

    fun pickCards(amount: Int): List<Int> {
        val cards = mutableListOf<Int>()
        repeat(amount) { cards.add(pickCard()) }
        return cards
    }

    private fun pickCard(): Int {
        val card = damageCards.random()
        damageCards.remove(card)
        return card
    }
}
