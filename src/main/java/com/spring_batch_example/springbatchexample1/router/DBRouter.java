package com.spring_batch_example.springbatchexample1.router;

import com.spring_batch_example.springbatchexample1.datastore.DBConnClientFactory;
import com.spring_batch_example.springbatchexample1.datastore.ElasticsearchConnection;
import com.spring_batch_example.springbatchexample1.datastore.ScyllaConnection;

import java.util.HashMap;
import java.util.Map;

public class DBRouter {
    public Map<String, DBConnClientFactory> dbIdMap = new HashMap<>() ;

    public DBRouter() {
        dbIdMap.put("elasticsearch", new ElasticsearchConnection());
        dbIdMap.put("scylla", new ScyllaConnection());
    }
}
