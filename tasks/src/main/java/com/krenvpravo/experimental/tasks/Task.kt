package com.krenvpravo.experimental.tasks

import kotlinx.coroutines.experimental.CoroutineDispatcher
import java.util.concurrent.atomic.AtomicReference
import kotlin.reflect.KClass

interface Task<out P> {
    fun <T> add(work: (previous: P) -> T): Task<T>

    /**
     * Adds on error inteceptor.
     * There can be multiple interceptors in the chain, so just catch when you need here
     */
    fun onError(errorType: KClass<out Any> = Exception::class, throwable: Throwable): Task<P>

    fun switchExecutor(dispatcher: CoroutineDispatcher): Task<P> //todo abstract it
    fun run(): P

    fun isComplete(): Boolean

    fun isSuccessful(): Boolean

    fun isCanceled(): Boolean
}


class TaskImpl<C>(internal val previousTask: AtomicReference<Task<P>>, ) : Task<C> {
    var nextTask : AtomicReference<Task<*>>? = null

    override fun <T> add(work: (previous: C) -> T): Task<T> {
        return nextTask = TaskImpl(AtomicReference(this())
    }

    override fun onError(errorType: KClass<out Any>, throwable: Throwable): Task<C> {
    }

    override fun switchExecutor(dispatcher: CoroutineDispatcher): Task<C> {
    }

    override fun run(): C {
    }
}

fun <T> createTask(work: () -> T) = TaskImpl(Unit).add { work() }




