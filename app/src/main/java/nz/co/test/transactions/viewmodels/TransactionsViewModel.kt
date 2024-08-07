package nz.co.test.transactions.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.use_cases.FetchUseCase
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel
@Inject
constructor(
    private val fetchTransactions: FetchUseCase<List<Transaction>>
) : ViewModel() {

    private var _state = MutableStateFlow<TransactionState>(TransactionState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchAll()
    }

     fun fetchAll() = viewModelScope.launch {
        try {
            _state.emit(TransactionState.Loading)
            val response = withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                fetchTransactions()
            }
            _state.emit(TransactionState.Success(response))

        } catch (e: Exception) {
            _state.emit(TransactionState.Error(e.message))
        }
    }
}


sealed class TransactionState {
    object Loading : TransactionState()
    data class Success(val response: List<Transaction>) : TransactionState()
    data class Error(val message: String?) : TransactionState()
}