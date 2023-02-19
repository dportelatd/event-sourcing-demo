package com.dportela.eventsourcingdemo.command_side.model.events

data class FundsAddedEvent(
    val walletId: String,
    val amount: Int
)