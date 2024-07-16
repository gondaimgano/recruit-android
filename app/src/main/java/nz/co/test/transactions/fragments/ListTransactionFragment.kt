package nz.co.test.transactions.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nz.co.test.transactions.R
import nz.co.test.transactions.databinding.FragmentListTransactionBinding


class ListTransactionFragment : Fragment(R.layout.fragment_list_transaction) {

    private lateinit var binding: FragmentListTransactionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

}