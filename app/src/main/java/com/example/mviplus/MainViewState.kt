package com.example.mviplus

sealed class MainViewState {
    //idle
    object Idle:MainViewState()

    //number
    data class Number(val number:Int):MainViewState()

    //error
    data class Error(val error:String):MainViewState()
}