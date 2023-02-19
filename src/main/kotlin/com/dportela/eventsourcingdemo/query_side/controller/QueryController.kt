package com.dportela.eventsourcingdemo.query_side.controller

import com.dportela.eventsourcingdemo.query_side.model.Wallet
import com.dportela.eventsourcingdemo.query_side.model.queries.GetAllWalletsQuery
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
class QueryController(
    val queryGateway: QueryGateway
) {
    @GetMapping("/wallet")
    fun getAllWallets() : CompletableFuture<List<Wallet>> =
        queryGateway.query(
            GetAllWalletsQuery(),
            ResponseTypes.multipleInstancesOf(Wallet::class.java)
        )
}