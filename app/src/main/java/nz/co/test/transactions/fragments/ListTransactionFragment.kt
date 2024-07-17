package nz.co.test.transactions.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import nz.co.test.transactions.R
import nz.co.test.transactions.adapters.TransactionListAdapter
import nz.co.test.transactions.databinding.FragmentListTransactionBinding
import nz.co.test.transactions.viewmodels.TransactionState
import nz.co.test.transactions.viewmodels.TransactionsViewModel

@AndroidEntryPoint
class ListTransactionFragment : Fragment(R.layout.fragment_list_transaction) {

    private lateinit var binding: FragmentListTransactionBinding
    private val viewModel: TransactionsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListTransactionBinding.inflate(inflater, container, false)
        val transactionListAdapter = TransactionListAdapter {
            findNavController().navigate(
                R.id.action_listTransactionFragment_to_transactionDetailFragment,
                Bundle().apply {
                    this.putParcelable(TransactionDetailFragment.TRANSACTION_KEY, it)
                })
        }
        with(binding.transactionList) {
            adapter = transactionListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is TransactionState.Error -> {
                            with(binding) {
                                transactionList.visibility = View.GONE
                                tryAgainButton.visibility = View.VISIBLE
                                messageText.visibility = View.VISIBLE
                                messageText.text = it.message
                            }
                        }

                        is TransactionState.Loading -> {
                            with(binding) {
                                transactionList.visibility = View.GONE
                                tryAgainButton.visibility = View.GONE
                                messageText.visibility = View.VISIBLE
                                messageText.text = getString(R.string.loading)
                            }
                        }

                        is TransactionState.Success -> {
                            with(binding) {
                                transactionList.visibility = View.VISIBLE
                                tryAgainButton.visibility = View.GONE
                                messageText.visibility = View.GONE
                            }
                            transactionListAdapter.submitList(it.response)
                        }
                    }

                }
            }
        }
        return binding.root
    }

}