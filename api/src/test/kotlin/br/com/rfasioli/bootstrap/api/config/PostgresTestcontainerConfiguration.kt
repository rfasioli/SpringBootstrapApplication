import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.Option
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@TestConfiguration
@EnableR2dbcRepositories
class PostgresTestcontainerConfiguration : AbstractR2dbcConfiguration() {
    companion object {
        @Container
        @JvmField
        val container: PostgreSQLContainer<*> =
            PostgreSQLContainer<Nothing>("postgres:13.4")
                .apply {
                    withDatabaseName("test")
                    withUsername("test")
                    withPassword("test")
                    start()
                }
    }

    @Bean
    @Primary
    override fun connectionFactory(): ConnectionFactory {
        val options =
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.HOST, container.containerIpAddress)
                .option(ConnectionFactoryOptions.PORT, container.firstMappedPort)
                .option(ConnectionFactoryOptions.DATABASE, container.databaseName)
                .option(ConnectionFactoryOptions.USER, container.username)
                .option(ConnectionFactoryOptions.PASSWORD, container.password)
                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                .option(Option.valueOf("schema"), "public")
                .build()

        return ConnectionFactories.get(options)
    }
}
