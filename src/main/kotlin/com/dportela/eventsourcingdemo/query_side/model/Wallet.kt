package com.dportela.eventsourcingdemo.query_side.model

data class Wallet(
    val walletId: String,
    val ownerId: String,
    var balance: Int = 0
)