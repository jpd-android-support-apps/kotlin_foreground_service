package edu.msoe.demastri.foreground

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class someState {
    var updates = 0;

    fun bump() : Int {
        return ++updates
    }
    fun current() : Int {
        return updates
    }
    fun outFlow(): Flow<Int> = flow {
        var lastValue = -1
        while(true) {
            if( updates != lastValue ) {
                emit(updates)
                lastValue = updates
            }
            delay(200)
        }
    }

    companion object {
        private var thisState: someState? = null
        fun factory(): someState {
            if( thisState == null) {
                thisState = someState()
            }
            return thisState!!
        }
    }
}