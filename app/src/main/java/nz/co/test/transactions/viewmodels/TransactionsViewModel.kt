package nz.co.test.transactions.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel
@Inject
constructor(
    private val transactionsService: TransactionsService
) : ViewModel() {

    private var _state = MutableStateFlow(emptyList<Transaction>())
    val state = _state.asStateFlow()

    init {
        fetchAll()
    }

    fun fetchAll() = viewModelScope.launch {
        try {
            val response = withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                transactionsService.retrieveTransactions()
            }
            _state.emit(response)

        } catch (e: Exception) {
           Log.d("Gondai",e.localizedMessage)
        }
    }
}