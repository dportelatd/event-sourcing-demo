package com.dportela.eventsourcingdemo.query_side.repository

import com.dportela.eventsourcingdemo.query_side.model.Wallet
import org.springframework.stereotype.Repository

@Repository
class WalletRepository {
    private val wallets: MutableMap<String, Wallet> = mutableMapOf()

    fun get(walletId: String) = wallets[walletId]

    fun getAll() = wallets.values.toList()

    fun add(wallet: Wallet) {
        wallets[wallet.walletId] = wallet
    }

    fun update(walletId: String, updateFunction: (wallet: Wallet) -> Wallet?) {
        get(walletId)?.let(updateFunction)
    }
}