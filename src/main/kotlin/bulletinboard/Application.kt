package bulletinboard

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("bulletinboard")
                .mainClass(Application.javaClass)
                .start()
    }
}