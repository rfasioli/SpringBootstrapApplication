package br.com.rfasioli.bootstrap.api.application

import br.com.rfasioli.bootstrap.api.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext

class SpringBootstrapApplicationIT(
    private val applicationContext: ApplicationContext,
) : IntegrationTest() {
    @Test
    fun `Should context loads when application starts`() {
        assertThat(applicationContext)
            .isNotNull
    }

    @Test
    fun `Should contain all expected beans when application starts`() {
        val expectedBeans = listOf(
            "springBootstrapApplication",
            "coursesResource",
            "enrollmentResource",
            "publishEnrollmentAnalysisAdapter",
            "coursePersistenceAdapter",
            "courseRepository",
        )

        expectedBeans.forEach { beanName ->
            assertThat(applicationContext.containsBean(beanName))
                .withFailMessage("Expected bean $beanName to be present, but it was not found")
                .isTrue()
        }
    }
}
