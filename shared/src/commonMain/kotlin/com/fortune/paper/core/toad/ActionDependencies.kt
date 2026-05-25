package com.fortune.paper.core.toad

import kotlinx.coroutines.CoroutineScope

abstract class ActionDependencies {
    abstract val coroutineScope: CoroutineScope
}
