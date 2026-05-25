package com.fortune.paper.core.toad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fortune.paper.core.mvi.ViewEvent
import com.fortune.paper.core.mvi.ViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class ToadViewModel<S : ViewState, E : ViewEvent>(
    initialState: S,
    private val dependencies: ActionDependencies
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _events = MutableSharedFlow<E>(extraBufferCapacity = 16)
    val events: SharedFlow<E> = _events.asSharedFlow()

    fun <D : ActionDependencies> dispatch(action: ViewAction<S, E, D>) {
        viewModelScope.launch {
            @Suppress("UNCHECKED_CAST")
            action.execute(
                dependencies = dependencies as D,
                scope = object : ActionScope<S, E> {
                    override val currentState: S get() = _state.value
                    override fun setState(reducer: S.() -> S) {
                        _state.value = _state.value.reducer()
                    }
                    override fun sendEvent(event: E) {
                        viewModelScope.launch { _events.emit(event) }
                    }
                }
            )
        }
    }
}
