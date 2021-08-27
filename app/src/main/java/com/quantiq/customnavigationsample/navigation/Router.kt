package com.quantiq.customnavigationsample.navigation

import com.tinder.StateMachine

interface Router {
    val stateMachine: StateMachine<State, Event, SideEffect>
}
