package com.spring_batch_example.springbatchexample1.datastore;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;


public class ScyllaConnection implements DBConnClientFactory{
    @Override
    public String createClient() throws Exception {
//        CqlSession scyllaSession = CqlSession
//                .builder()
//                .addContactPoint(InetSocketAddress.createUnresolved("localhost", 9042))
//                .withLocalDatacenter("datacenter1")
//                .withKeyspace("excelsior")
//                .build();

        InetAddress addr = InetAddress.getByName("localhost");
        InetSocketAddress addrSoc = new InetSocketAddress(addr,9042);
        CqlSession scyllaSession = CqlSession
                .builder()
                .addContactPoint(addrSoc)
                .withLocalDatacenter("datacenter1")
                .withKeyspace("catalog")
                .build();

        testConnection(scyllaSession);

        return scyllaSession.toString();
    }

    public void testConnection(CqlSession scyllaSession){
        ResultSet results = scyllaSession.execute("SELECT * FROM catalog.mutant_data");
        for (Row row : results) {
            String first_name = row.getString("first_name");
            String last_name = row.getString("last_name");
            System.out.print("\n" + first_name + " " + last_name);
        }
    }
}
