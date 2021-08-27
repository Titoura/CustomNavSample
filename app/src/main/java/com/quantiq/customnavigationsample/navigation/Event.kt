package com.quantiq.customnavigationsample.navigation

// When passed to the state machine, it will trigger a change of state
sealed class Event {
    object OnStart : Event()
    object OnNext : Event()
    object OnPrevious : Event()
}
