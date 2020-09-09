package com.kata.domain

class Machine {
    var health = 30

    fun reduceHealth(damage: Int) {
        health -= damage
    }

    fun getStatus() = MachineStatus(health)
}
