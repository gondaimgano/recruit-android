package nz.co.test.transactions.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.databinding.TransactionItemBinding
import nz.co.test.transactions.services.Transaction

class TransactionListAdapter :
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

    class ViewHolder(private val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.title.text = transaction.summary
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