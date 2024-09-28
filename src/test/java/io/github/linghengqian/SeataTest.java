package io.github.linghengqian;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.linghengqian.commons.TestShardingService;
import org.apache.seata.rm.RMClient;
import org.apache.seata.rm.datasource.DataSourceProxy;
import org.apache.seata.tm.TMClient;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("resource")
@Testcontainers
public class SeataTest {

    @Container
    public GenericContainer<?> seataContainer = new GenericContainer<>("apache/seata-server:2.1.0")
            .withExposedPorts(7091, 8091)
            .waitingFor(Wait.forHttp("/health").forPort(7091).forResponsePredicate("ok"::equals));

    private static final String SERVICE_DEFAULT_GROUP_LIST_KEY = "service.default.grouplist";

    @Test
    void assertSeataTransactions() throws SQLException {
        assertThat(System.getProperty(SERVICE_DEFAULT_GROUP_LIST_KEY), is(nullValue()));
        System.setProperty(SERVICE_DEFAULT_GROUP_LIST_KEY, "127.0.0.1:" + seataContainer.getMappedPort(8091));
        String applicationId = "seata-client-graalvm-native-test";
        String txServiceGroup = "default_tx_group";
        TMClient.init(applicationId, txServiceGroup);
        RMClient.init(applicationId, txServiceGroup);
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.testcontainers.jdbc.ContainerDatabaseDriver");
        config.setJdbcUrl("jdbc:tc:postgresql:17.0-bookworm://test-databases-postgres/demo_ds?TC_INITSCRIPT=seata-script-client-at-postgresql.sql");
        DataSource dataSource = new DataSourceProxy(new HikariDataSource(config));
        TestShardingService testShardingService = new TestShardingService(dataSource);
        testShardingService.processSuccess();
        System.clearProperty(SERVICE_DEFAULT_GROUP_LIST_KEY);
    }
}
