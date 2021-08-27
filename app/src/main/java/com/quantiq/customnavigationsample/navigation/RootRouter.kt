package com.quantiq.customnavigationsample.navigation

import android.util.Log
import com.tinder.StateMachine

class RootRouter(
    private val navigator: Navigator
) : Router {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: RootRouter
            private set
    }

    private val TAG = "RootRouter"


    override val stateMachine = StateMachine.create<State, Event, SideEffect> {

        // First screen to be displayed
        initialState(State.Empty)


        // Instantiation of states, and the transition that are allowed according to the event received
        state(State.Empty) {
            on<Event.OnStart> {
                transitionTo(State.First, SideEffect.NavigateForward())
            }
        }
        state(State.First) {
            on<Event.OnNext> {
                transitionTo(State.Second, SideEffect.NavigateForward())
            }
        }
        state(State.Second) {
            on<Event.OnPrevious> {
                transitionTo(State.First, SideEffect.NavigateForward())
            }
        }

        // When there's a transition between states, we check whether the transition is allowed (valid)
        // If it is, we pick the navigation animation, and call the navigator with the correct fragments
        onTransition {
            val validTransition = it as? StateMachine.Transition.Valid ?: return@onTransition
            val toState = validTransition.toState
            val previousState = validTransition.fromState

            val sideEffect = validTransition.sideEffect as SideEffect
            when (sideEffect) {
                is SideEffect.NavigateForward -> navigator.handleStackChange(
                    previousState,
                    toState,
                    Direction.Forward,
                    sideEffect.args
                )
                is SideEffect.GoBack -> navigator.handleStackChange(
                    previousState,
                    toState,
                    Direction.Backward,
                    sideEffect.args
                )
            }
        }
    }

    // This just adds logs to the statemachine transition method
    private fun transition(event: Event) {
        val transition = stateMachine.transition(event)
        if (transition is StateMachine.Transition.Valid) {
            Log.i(TAG, transition.toString())
        } else {
            Log.e(TAG, transition.toString())
        }
    }

    fun start() = transition(Event.OnStart)
    fun onNavigateToNext() = transition(Event.OnNext)
    fun onNavigateToPrevious() = transition(Event.OnPrevious)
}

