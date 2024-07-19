package nz.co.test.transactions.utils

import android.content.Context
import androidx.core.content.ContextCompat
import nz.co.test.transactions.services.Transaction
import java.math.BigDecimal
import java.math.RoundingMode

object Utils {
    sealed class Symbol(val label:String){
        object Debit:Symbol("Dr:")
        object Credit:Symbol("Cr:")
    }
    fun formatTransactionAmount(transaction: Transaction, drSymbol:String=Symbol.Debit.label, creditSymbol:String=Symbol.Credit.label): String {
        return if (transaction.credit > 0) {
            "$creditSymbol ${transaction.credit}"
        } else {
            "$drSymbol ${transaction.debit}"
        }
    }

    fun getTransactionAmountColor(context: Context, transaction: Transaction): Int {
        return ContextCompat.getColor(
            context,
            if (transaction.credit > 0) android.R.color.holo_green_dark else android.R.color.holo_red_dark
        )
    }

    fun getAmountCalculatedGST(transaction: Transaction): Double {
        return formatGST(
            (if (transaction.credit > 0) transaction.credit
            else transaction.debit) * 0.15
        )
    }

    private fun formatGST(double: Double): Double {
        return BigDecimal(double).setScale(2, RoundingMode.HALF_UP).toDouble()
    }
}