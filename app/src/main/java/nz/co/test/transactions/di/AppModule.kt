package nz.co.test.transactions.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.co.test.transactions.BuildConfig
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import nz.co.test.transactions.use_cases.FetchUseCase
import nz.co.test.transactions.use_cases.transaction.FetchTransactionUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesTransactionService(): TransactionsService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(TransactionsService::class.java)

    @Provides
    fun providesTransactionUseCase(transactionsService: TransactionsService): FetchUseCase<List<Transaction>> =
        FetchTransactionUseCase(transactionsService)
}