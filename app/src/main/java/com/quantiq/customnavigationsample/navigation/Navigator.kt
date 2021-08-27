package com.quantiq.customnavigationsample.navigation

import android.os.Bundle
import android.util.Log
import com.quantiq.customnavigationsample.MainActivity
import com.quantiq.customnavigationsample.R

sealed class Direction {
    object Forward : Direction()
    object Backward : Direction()
}


// This class doesn't contain business logic
// Its purpose is to handle the fragments transitions
// We pass the previous and next screens, along with the animation needed

class Navigator(
    private val rootActivity: MainActivity
) {

    private val TAG = "Navigator"

    fun handleStackChange(
        previousState: State,
        nextState: State,
        direction: Direction,
        args: Bundle?
    ) {
        rootActivity.runOnUiThread {

            val bundle = args ?: Bundle()
            for (arg in nextState.args?.entries ?: emptyList()) {
                bundle.putString(arg.key, arg.value)
            }

            Log.i(TAG, "handleStackChange(): From state: $previousState")
            Log.i(TAG, "handleStackChange(): Goto state: $nextState")
            try {
                rootActivity.supportFragmentManager.executePendingTransactions()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val fragmentTransaction = rootActivity.supportFragmentManager.beginTransaction()

            fragmentTransaction
                .apply {
                    try {
                        val previousFragment =
                            rootActivity.supportFragmentManager.findFragmentByTag(previousState.fragmentClass.canonicalName)
                        if (previousFragment != null) {
                            remove(previousFragment)
                        }
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }

                    val fragment = nextState.fragmentClass.newInstance()
                    fragment.arguments = bundle

                    replace(
                        R.id.fragment_container,
                        fragment,
                        nextState.fragmentClass.canonicalName
                    )


                    Log.d(TAG, "Replacing ${previousState.fragmentClass.canonicalName} by ${nextState.fragmentClass.canonicalName}")
                }
            fragmentTransaction.commitNowAllowingStateLoss()
        }
    }
}

