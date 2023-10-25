package br.com.rfasioli.bootstrap.api.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class SpringBootstrapApplicationTest(
    private val applicationContext: ApplicationContext
) {

    @Test
    fun `Should context loads when application starts`() {
        assertThat(applicationContext)
            .isNotNull
    }
}
