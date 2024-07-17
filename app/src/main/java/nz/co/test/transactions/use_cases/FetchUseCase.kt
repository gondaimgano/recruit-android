package nz.co.test.transactions.use_cases

interface FetchUseCase<T> {
    suspend operator fun invoke(): T
}