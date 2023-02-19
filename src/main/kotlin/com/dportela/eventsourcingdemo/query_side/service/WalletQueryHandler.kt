package com.dportela.eventsourcingdemo.query_side.service

import com.dportela.eventsourcingdemo.query_side.model.queries.GetAllWalletsQuery
import com.dportela.eventsourcingdemo.query_side.repository.WalletRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class WalletQueryHandler(
    val walletRepository: WalletRepository
) {
    @QueryHandler
    fun handleGetAllWallets(query: GetAllWalletsQuery) =
        walletRepository.getAll()
}