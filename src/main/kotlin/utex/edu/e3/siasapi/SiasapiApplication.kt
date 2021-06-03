package utex.edu.e3.siasapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SiasapiApplication

fun main(args: Array<String>) {
    runApplication<SiasapiApplication>(*args)
}
