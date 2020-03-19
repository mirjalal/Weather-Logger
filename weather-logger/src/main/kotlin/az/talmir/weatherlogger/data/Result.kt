package az.talmir.weatherlogger.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R : Any> {
    data class Success<out R : Any>(val data: R) : Result<R>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String = when (this) {
        is Success<*> -> "Success[data=$data]"
        is Error -> "Error[exception=$exception]"
        Loading -> "Loading"
    }
}

/**
 * `true` if [Result] is of type [Result.Success] & holds non-null [Result.Success.data].
 */
//val Result<*>.succeeded
//    get() = this is Result.Success && data != null
