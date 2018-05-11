package com.krenvpravo.experimental.tasks

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.Deferred
import kotlin.reflect.KClass

interface Task<out P> {
    fun <T> add(work: (previous: P) -> T): Task<T>

    /**
     * Adds on error inteceptor.
     * There can be multiple interceptors in the chain, so just catch when you need here
     */
    fun onError(errorType: KClass<out Any> = Exception::class, throwable: Throwable): Task<P>

    fun switchExecutor(dispatcher: CoroutineDispatcher) //todo abstract it
    fun run()
}


class TaskImpl<P>(internal val previousResult: P) : Task<P> {
    internal val previousTask: Deferred<P>

    override fun <T> add(work: (previous: P) -> T): Task<T> {
        return TaskImpl(work(previousResult))
    }

    override fun switchExecutor(dispatcher: CoroutineDispatcher) {
    }

    override fun onError(errorType: KClass<out Any>, throwable: Throwable): Task<P> {
    }

    override fun run() {
    }

}

fun <T> createTask(work: () -> T) = TaskImpl(Unit).add { work() }



