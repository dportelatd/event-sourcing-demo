package com.dportela.eventsourcingdemo.command_side.model.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class AddFundsCommand(
    @TargetAggregateIdentifier
    val walletId: String,
    val amount: Int
)