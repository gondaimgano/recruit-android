package nz.co.test.transactions

import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun testFormatTransactionAmount_credit() {
        // Given a transaction with credit
        val transaction = Transaction(1, "2024-07-17", "Test Transaction", 0.0, 100.0)

        // When formatting the transaction amount
        val formattedAmount = Utils.formatTransactionAmount(transaction)

        // Then it should format as "Cr: 100.0"
        assertEquals("Cr: 100.0", formattedAmount)
    }

    @Test
    fun testFormatTransactionAmount_debit() {
        // Given a transaction with debit
        val transaction = Transaction(1, "2024-07-17", "Test Transaction", 50.0, 0.0)

        // When formatting the transaction amount
        val formattedAmount = Utils.formatTransactionAmount(transaction)

        // Then it should format as "Dr: 50.0"
        assertEquals("Dr: 50.0", formattedAmount)
    }

    @Test
    fun testFormatTransactionAmount_zero() {
        // Given a transaction with both credit and debit as 0
        val transaction = Transaction(1, "2024-07-17", "Test Transaction", 0.0, 0.0)

        // When formatting the transaction amount
        val formattedAmount = Utils.formatTransactionAmount(transaction)

        // Then it should format as "Dr: 0.0" (assuming debit default)
        assertEquals("Dr: 0.0", formattedAmount)
    }
}
