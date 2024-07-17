package nz.co.test.transactions.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.use_cases.FetchUseCase
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel
@Inject
constructor(
    private val fetchTransactions: FetchUseCase<List<Transaction>>
) : ViewModel() {

    private var _state = MutableStateFlow(emptyList<Transaction>())
    val state = _state.asStateFlow()

    init {
        fetchAll()
    }

    private fun fetchAll() = viewModelScope.launch {
        try {
            val response = fetchTransactions()
            _state.emit(response)

        } catch (e: Exception) {

        }
    }
}