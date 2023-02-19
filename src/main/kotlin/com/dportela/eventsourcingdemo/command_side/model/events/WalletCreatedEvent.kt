package com.dportela.eventsourcingdemo.command_side.model.events

data class WalletCreatedEvent(
    val walletId: String,
    val ownerId: String
)