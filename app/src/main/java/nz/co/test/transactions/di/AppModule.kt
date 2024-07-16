package nz.co.test.transactions.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.co.test.transactions.BuildConfig
import nz.co.test.transactions.services.TransactionsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun providesTransactionService(): TransactionsService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL).addConverterFactory(MoshiConverterFactory.create()).build()
        .create(TransactionsService::class.java)
}