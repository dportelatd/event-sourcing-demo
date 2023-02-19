package com.dportela.eventsourcingdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventSourcingDemoApplication

fun main(args: Array<String>) {
	runApplication<EventSourcingDemoApplication>(*args)
}
