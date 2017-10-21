package com.mikebull94.result

class UnwrapException(message: String) : Exception(message)

/**
 * Unwraps a [Result], yielding the [value][Ok.value].
 *
 * - Rust: [Result.unwrap](https://doc.rust-lang.org/std/result/enum.Result.html#method.unwrap)
 *
 * @throws UnwrapException if the [Result] is an [Error], with a message containing the [error][Error.error].
 */
fun <V, E> Result<V, E>.unwrap(): V {
    return when (this) {
        is Ok -> value
        is Error -> throw UnwrapException("called Result.wrap on an Error value $error")
    }
}

/**
 * Unwraps a [Result], yielding the [value][Ok.value].
 *
 * - Rust: [Result.expect](https://doc.rust-lang.org/std/result/enum.Result.html#method.expect)
 *
 * @param message The message to include in the [UnwrapException] if the [Result] is an [Error].
 * @throws UnwrapException if the [Result] is an [Error], with the specified [message].
 */
infix fun <V, E> Result<V, E>.expect(message: String): V {
    return when (this) {
        is Ok -> value
        is Error -> throw UnwrapException("$message $error")
    }
}

/**
 * Unwraps a [Result], yielding the [error][Error.error].
 *
 * - Rust: [Result.unwrap_err](https://doc.rust-lang.org/std/result/enum.Result.html#method.unwrap_err)
 *
 * @throws UnwrapException if the [Result] is [Ok], with a message containing the [value][Ok.value].
 */
fun <V, E> Result<V, E>.unwrapError(): E {
    return when (this) {
        is Ok -> throw UnwrapException("called Result.unwrapError on an Ok value $value")
        is Error -> error
    }
}

/**
 * Unwraps a [Result], yielding the [error][Error.error].
 *
 * - Rust: [Result.expect_err](https://doc.rust-lang.org/std/result/enum.Result.html#method.expect_err)
 *
 * @param message The message to include in the [UnwrapException] if the [Result] is [Ok].
 * @throws UnwrapException if the [Result] is [Ok], with the specified [message].
 */
infix fun <V, E> Result<V, E>.expectError(message: String): E {
    return when (this) {
        is Ok -> throw UnwrapException("$message $value")
        is Error -> error
    }
}