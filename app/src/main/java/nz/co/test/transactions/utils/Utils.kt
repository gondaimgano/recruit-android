package nz.co.test.transactions.utils

import android.content.Context
import androidx.core.content.ContextCompat
import nz.co.test.transactions.services.Transaction

object Utils {
    fun formatTransactionAmount(transaction: Transaction): String {
        return if (transaction.credit > 0) {
            "Cr: ${transaction.credit}"
        } else {
            "Dr: ${transaction.debit}"
        }
    }

    fun getTransactionAmountColor(context: Context, transaction: Transaction): Int {
        return ContextCompat.getColor(
            context,
            if (transaction.credit > 0) android.R.color.holo_green_dark else android.R.color.holo_red_dark
        )
    }
}