package com.example.overseas_football.data


data class Resource<out T>(val status: Status, val data: T?, val throwable: Throwable?) {

    val isError: Boolean
        get() = status == Status.ERROR
    val isSuccess: Boolean
        get() = status == Status.SUCCESS
    val isLoading: Boolean
        get() = status == Status.LOADING

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", throwable='" + throwable?.message +
                ", data=" + data +
                '}'.toString()
    }

    fun <V> map(transform: (T) -> V): Resource<V> {
        val newData: V?
        try {
            newData = if (data != null) {
                transform.invoke(data)
            } else {
                null
            }
        } catch (e: Exception) {
            return Resource.error(e, null)
        }
        return when {
            isLoading -> Resource.loading(newData)
            isSuccess -> Resource.success(newData)
            else -> Resource.error(throwable ?: IllegalStateException(), newData)
        }
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, throwable)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun combineStatus(resource1: Resource<Any>, resource2: Resource<Any>): Status {
            if (resource1.isError || resource2.isError) {
                return Status.ERROR
            }
            if (resource1.isLoading || resource2.isLoading) {
                return Status.LOADING
            }
            return Status.SUCCESS
        }

        fun <T, V> combineResource(_resource1: Resource<T>?, _resource2: Resource<V>?): Resource<Pair<T, V>> {
            try {
                val resource1 = checkNotNull(_resource1)
                val resource2 = checkNotNull(_resource2)
                if (resource1.isError || resource2.isError) {
                    val throwable = if (resource1.isError) resource1.throwable!! else resource2.throwable!!
                    return Resource.error(throwable, null)
                }
                if (resource1.isLoading || resource2.isLoading) {
                    return Resource.loading(null)
                }
                return Resource.success(Pair(resource1.data!!, resource2.data!!))
            } catch (e: Exception) {
                return Resource.error(e, null)
            }

        }

        fun <T> from(result: T?, throwable: Throwable?): Resource<T> {
            if (throwable != null) {
                return Resource.error(throwable, result)
            }
            if (result == null) {
                return Resource.error(IllegalStateException("DATA_NOT_FOUND"), null)
            }
            return Resource.success(result)
        }

    }

}