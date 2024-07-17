package nz.co.test.transactions.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import nz.co.test.transactions.R
import nz.co.test.transactions.databinding.FragmentTransactionDetailBinding
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.utils.Utils

class TransactionDetailFragment : Fragment(R.layout.fragment_transaction_detail) {
    private lateinit var binding: FragmentTransactionDetailBinding

    companion object {
        const val TRANSACTION_KEY = "TRANSACTION_KEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailBinding.inflate(inflater, container, false)
        arguments?.let {
            it.getParcelable<Transaction>(TRANSACTION_KEY)?.let {
                with(binding) {
                    textFirstLetter.text = it.summary.first().toString()
                    textAmount.text = Utils.formatTransactionAmount(it)
                    textAmount.setTextColor(Utils.getTransactionAmountColor(this@TransactionDetailFragment.requireContext(),it))
                    textSummary.text = it.summary
                    textTransactionDate.text = it.transactionDate
                    textGST.text = "15% GST: ${Utils.getAmountCalculatedGST(it)}"

                }
            }
        }
        return binding.root
    }
}