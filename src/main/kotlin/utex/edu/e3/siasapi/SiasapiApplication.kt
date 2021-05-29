package utex.edu.e3.siasapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class SiasapiApplication

fun main(args: Array<String>) {
    runApplication<SiasapiApplication>(*args)

    
}


@RestController
class test () {

    @GetMapping("/hello")
    fun hello(): String = "Hello"
    
}