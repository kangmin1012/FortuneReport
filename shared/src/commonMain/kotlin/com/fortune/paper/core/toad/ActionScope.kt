package com.fortune.paper.core.toad

import com.fortune.paper.core.mvi.ViewEvent
import com.fortune.paper.core.mvi.ViewState

interface ActionScope<S : ViewState, E : ViewEvent> {
    val currentState: S
    fun setState(reducer: S.() -> S)
    fun sendEvent(event: E)
}
