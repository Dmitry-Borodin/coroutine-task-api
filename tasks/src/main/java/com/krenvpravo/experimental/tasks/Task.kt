package com.krenvpravo.experimental.tasks

import kotlinx.coroutines.experimental.CoroutineDispatcher

interface Task<out P> {
    val previousResult : P
    fun <T>add(work : (previous : P) -> T)
    fun onError(throwable: Throwable)
    fun switchExecutor( dispatcher : CoroutineDispatcher) //todo abstract it
    fun run()
}

class TaskImpl<P>(override val previousResult: P) : Task<P> {
    val taskList = listOf<Task<*>>()

    override fun <T> add(work: (previous: P) -> T) {

    }

    override fun switchExecutor(dispatcher: CoroutineDispatcher) {
    }

    override fun onError(throwable: Throwable) {
    }

    override fun run() {
    }

}

