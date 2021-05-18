package pl.rafalmiskiewicz.util.errorhandling

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun <A> Either<Failure, A>.mapLeftIfErrorCode(vararg predictedError: Pair<String, Failure>): Either<Failure, A> {
    return fold({ failure ->
        if (failure !is Failure.FeatureFailure) return@fold Left(failure)
        var mappedFailure = failure
        for (pair in predictedError) {
            if(failure.errorBody?.errorCode == pair.first) {
                mappedFailure = pair.second
            }
        }
        return@fold Left(mappedFailure)
    }, { Right(it) })
}

suspend fun <T> handleRequest(requestFunc: suspend () -> T): Either<Failure, T> {
    return try {
        val response = requestFunc.invoke()
        return Right(response)
    } catch (exception: Throwable) {
        // For client tests
//        Timber.tag("REST Exception").e(exception)
        when (exception) {
            is UnknownHostException -> Left(Failure.NetworkConnection)
            is TimeoutException, is SocketTimeoutException -> Left(Failure.TimeOut)
            is HttpException -> {
                when (exception.code()) {
                    500 -> Left(Failure.ServerError)
                    503 -> Left(Failure.ServiceUnavailable)
                    401 -> Left(Failure.Unauthorized)
                    else -> Left(Failure.UnknownError)
                }
            }
            else -> {
                Timber.tag("UNUSUAL ERROR").e(exception)
                Left(Failure.ServerError)
            }
        }
    }
}
