package com.quantiq.customnavigationsample.navigation

import android.os.Bundle

// These are used to select the correct transition between screens
sealed class SideEffect(val args: Bundle? = null) {
    class NavigateForward(args: Bundle? = null) : SideEffect(args)
    class GoBack(args: Bundle? = null) : SideEffect(args)
}
