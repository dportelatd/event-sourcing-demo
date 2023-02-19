package com.dportela.eventsourcingdemo.query_side.service

import com.dportela.eventsourcingdemo.command_side.model.events.FundsAddedEvent
import com.dportela.eventsourcingdemo.command_side.model.events.WalletCreatedEvent
import com.dportela.eventsourcingdemo.query_side.model.Wallet
import com.dportela.eventsourcingdemo.query_side.repository.WalletRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
@ProcessingGroup("wallets")
class WalletEventsHandler(
    val walletRepository: WalletRepository
) {
    @EventHandler
    fun onWalletCreated(event: WalletCreatedEvent) =
        walletRepository.add(
            Wallet(
                walletId = event.walletId,
                ownerId = event.ownerId
            )
        )

    @EventHandler
    fun onFundsAdded(event: FundsAddedEvent) =
        walletRepository.update(event.walletId) { wallet ->
            wallet.balance += event.amount
            wallet
        }
}