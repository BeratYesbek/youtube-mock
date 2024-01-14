package beratyesbek.youtube.mock.integrationtest.testcontainers

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

@TestConfiguration
open class TestContainer {

    @Bean
    @ServiceConnection
    open fun postgresSQLContainer(): PostgreSQLContainer<Nothing> {
        val container = PostgreSQLContainer<Nothing>("postgres:16-alpine");
        container.withDatabaseName("test")
        container.withUsername("test")
        container.withPassword("test")
        return container
    }
}