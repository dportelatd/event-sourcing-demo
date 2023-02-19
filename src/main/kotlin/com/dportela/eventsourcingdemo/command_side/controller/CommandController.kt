package com.dportela.eventsourcingdemo.command_side.controller

import com.dportela.eventsourcingdemo.command_side.model.commands.AddFundsCommand
import com.dportela.eventsourcingdemo.command_side.model.commands.CreateWalletCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Random
import java.util.concurrent.CompletableFuture

@RestController
class CommandController(
    val commandGateway: CommandGateway
) {
    @PostMapping("/wallet")
    fun createWallet(): CompletableFuture<String> =
        commandGateway.send(
            CreateWalletCommand(
                walletId = Random().nextInt(1000).toString(),
                ownerId = "my_owner"
            )
        )

    @PostMapping("/wallet/{walletId}/funds/{amount}")
    fun addFunds(@PathVariable walletId: String, @PathVariable amount: Int): CompletableFuture<String> =
        commandGateway.send(
            AddFundsCommand(
                walletId = walletId,
                amount = amount
            )
        )
}