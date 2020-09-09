package com.kata

import com.kata.useCases.StartGame

fun main(){
    App(SystemScreen(), SystemKeyboard(), StartGame()).run()
}
