package pl.rafalmiskiewicz.data.source

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import pl.rafalmiskiewicz.data.source.local.CredentialStore
import java.util.*
import java.util.concurrent.TimeUnit

class HttpClientFactory(
    private val credentialStore: CredentialStore
) {

    fun getHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
            .addInterceptor { chain ->
                val ongoing = chain.request().newBuilder()
                if (credentialStore.isLogged) {
                    ongoing.addHeader("Authorization", "Bearer " + credentialStore.restore().token)//RM
                }
                return@addInterceptor chain.proceed(ongoing.build())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(null)

        builder.certificatePinner(CertificatePinner.DEFAULT)

        return builder.build()
    }
}