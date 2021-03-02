package com.example.androiddevchallenge.model


sealed class BaseBean<out R> {
    data class Success<out T>(val data: T) : BaseBean<T>()
    data class Error(val exception: Exception) : BaseBean<Nothing>()
}

val BaseBean<*>.succeeded
    get() = this is BaseBean.Success && data != null

fun <T> BaseBean<T>.successOr(fallback: T): T {
    return (this as? BaseBean.Success<T>)?.data ?: fallback
}
