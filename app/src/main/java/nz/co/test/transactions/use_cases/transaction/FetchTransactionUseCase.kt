package nz.co.test.transactions.use_cases.transaction

import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import nz.co.test.transactions.use_cases.FetchUseCase

class FetchTransactionUseCase
    constructor(private  val service: TransactionsService): FetchUseCase<List<Transaction>> {
    override suspend fun invoke(): List<Transaction> {
        return service.retrieveTransactions()
    }
}