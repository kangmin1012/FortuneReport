package com.fortune.paper.core.toad

import com.fortune.paper.core.mvi.ViewEvent
import com.fortune.paper.core.mvi.ViewState

abstract class ViewAction<S : ViewState, E : ViewEvent, D : ActionDependencies> {
    abstract suspend fun execute(dependencies: D, scope: ActionScope<S, E>)
}
