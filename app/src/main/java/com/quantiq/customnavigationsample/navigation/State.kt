package com.quantiq.customnavigationsample.navigation

import androidx.fragment.app.Fragment
import com.quantiq.customnavigationsample.FirstFragment
import com.quantiq.customnavigationsample.SecondFragment

// The states of the state machine correspond to fragments, potentially with arguments
sealed class State(
    val fragmentClass: Class<out Fragment>,
    val args : Map<String, String?>? = null
) {
    object Empty : State(FirstFragment::class.java)

    object First : State(FirstFragment::class.java)
    object Second : State(SecondFragment::class.java)
}
