package com.dportela.eventsourcingdemo.command_side.model.aggregates

import com.dportela.eventsourcingdemo.command_side.model.commands.AddFundsCommand
import com.dportela.eventsourcingdemo.command_side.model.commands.CreateWalletCommand
import com.dportela.eventsourcingdemo.command_side.model.events.FundsAddedEvent
import com.dportela.eventsourcingdemo.command_side.model.events.WalletCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class WalletAggregate() {
    @AggregateIdentifier
    lateinit var walletId: String
    lateinit var ownerId: String
    var balance: Int = 0

    @CommandHandler
    constructor(command: CreateWalletCommand): this() {
        AggregateLifecycle.apply(
            WalletCreatedEvent(
                walletId = command.walletId,
                ownerId = command.ownerId
            )
        )
    }

    @EventSourcingHandler
    fun onWalletCreated(event: WalletCreatedEvent) {
        this.walletId = event.walletId
        this.ownerId = event.ownerId
    }

    @CommandHandler
    fun handleAddFundsCommand(command: AddFundsCommand) {
        val amountToAdd = command.amount
        if (this.balance + amountToAdd <= 100) {
            AggregateLifecycle.apply(
                FundsAddedEvent(
                    walletId = command.walletId,
                    amount = command.amount
                )
            )
        }
    }

    @EventSourcingHandler
    fun onFundsAdded(event: FundsAddedEvent) {
        this.balance += event.amount
    }
}