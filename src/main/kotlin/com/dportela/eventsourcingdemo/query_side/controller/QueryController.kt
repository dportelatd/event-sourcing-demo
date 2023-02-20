package com.dportela.eventsourcingdemo.query_side.controller

import com.dportela.eventsourcingdemo.query_side.model.Wallet
import com.dportela.eventsourcingdemo.query_side.model.queries.GetAllWalletsQuery
import com.dportela.eventsourcingdemo.query_side.repository.WalletRepository
import org.axonframework.config.EventProcessingConfiguration
import org.axonframework.eventhandling.TrackingEventProcessor
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
class QueryController(
    val queryGateway: QueryGateway,
    val walletRepository: WalletRepository,
    val eventsProcessingConfiguration: EventProcessingConfiguration
) {
    @GetMapping("/wallet")
    fun getAllWallets() : CompletableFuture<List<Wallet>> =
        queryGateway.query(
            GetAllWalletsQuery(),
            ResponseTypes.multipleInstancesOf(Wallet::class.java)
        )

    @DeleteMapping("/wallet")
    fun resetWallets() = walletRepository.resetWallets()

    @PostMapping("/wallet/replay")
    fun replayEvents() {
        eventsProcessingConfiguration.eventProcessor<TrackingEventProcessor>("wallets")
            .ifPresent { processor ->
                processor.shutDown()
                processor.resetTokens()
                processor.start()
            }
    }
}