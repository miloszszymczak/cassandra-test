package com.example.cassandrademo;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class Repository {
    private final CqlSession session;

    public Repository() {
        session = CqlSession.builder()
            .addContactPoint(InetSocketAddress.createUnresolved("cassandra-test-0.cassandra-test.cassandra-test.svc.cluster.local", 9042))
            .addContactPoint(InetSocketAddress.createUnresolved("cassandra-test-1.cassandra-test.cassandra-test.svc.cluster.local", 9042))
            .addContactPoint(InetSocketAddress.createUnresolved("cassandra-test-2.cassandra-test.cassandra-test.svc.cluster.local", 9042))
            .withLocalDatacenter("datacenter1")
            .build();
    }

    public Long getCount() {
        return session.execute("SELECT COUNT(*) FROM dev.meta_ranks").all().stream().map(row -> row.getLong(0)).findFirst().orElse(0L);
    }
}
