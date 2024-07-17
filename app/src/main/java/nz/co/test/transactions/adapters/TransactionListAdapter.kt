package nz.co.test.transactions.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.databinding.TransactionItemBinding
import nz.co.test.transactions.services.Transaction

class TransactionListAdapter(private val onClick:(t:Transaction)->Unit) :
    ListAdapter<Transaction, TransactionListAdapter.ViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            TransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            with(binding) {
                binding.firstLetter.text = transaction.summary.first().toString()
                transactionSummary.text = transaction.summary
                transactionAmount.text =
                    (if (transaction.credit > 0) transaction.credit else transaction.debit).toString()
                transactionAmount.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        if (transaction.credit > 0) android.R.color.holo_green_dark else android.R.color.holo_red_dark
                    )
                )

                itemView.setOnClickListener {
                   onClick(transaction)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}