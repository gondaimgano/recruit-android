package nz.co.test.transactions.di.network

import dagger.Module
import dagger.Provides
import nz.co.test.transactions.BuildConfig
import nz.co.test.transactions.services.TransactionsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {
    @Provides
    fun providesTransactionService(): TransactionsService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TransactionsService::class.java)
}