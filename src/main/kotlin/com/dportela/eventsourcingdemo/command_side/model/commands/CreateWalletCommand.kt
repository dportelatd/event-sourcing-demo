package com.dportela.eventsourcingdemo.command_side.model.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateWalletCommand(
    @TargetAggregateIdentifier
    val walletId: String,
    val ownerId: String
)