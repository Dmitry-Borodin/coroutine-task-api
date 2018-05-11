package com.krenvpravo.experimental.tasks

/**
 * @author Dmitry Borodin on 5/11/18.
 */

fun main(args: Array<String>) {
    createTask { print("first task") }
            .add { throw RuntimeException("something went wrong") }
            .add { print("third task")}
            .onError (RuntimeException::class) { }
}