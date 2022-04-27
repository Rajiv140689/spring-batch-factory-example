package com.spring_batch_example.springbatchexample1;

import com.spring_batch_example.springbatchexample1.datastore.DBConnClientFactory;
import com.spring_batch_example.springbatchexample1.datastore.ElasticsearchConnection;
import com.spring_batch_example.springbatchexample1.router.DBRouter;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchFactoryExampleApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBatchFactoryExampleApp.class, args);

//		ElasticsearchConnection es = new ElasticsearchConnection();
//		RestHighLevelClient esClient = es.createClient();
//		es.testConnection(esClient);

//		DBRouter dbRouter = new DBRouter();
//		String dbName = dbRouter.dbIdMap.get("ELASTICSEARCH").createClient();
//		System.out.println("dbName: "+dbName);
//
//		String dbName1 = dbRouter.dbIdMap.get("SCYLLA").createClient();
//		System.out.println("dbName: "+dbName1);


//		DBRouter dbRouter = new DBRouter();
//		DBConnClientFactory elasticDBConnClientFactory = dbRouter.dbIdMap.get("ELASTICSEARCH");
//		System.out.println("elasticDBConnClientFactory "+elasticDBConnClientFactory);
//
//		DBConnClientFactory scyllaDBConnClientFactory= dbRouter.dbIdMap.get("SCYLLA");
//		System.out.println("scyllaDBConnClientFactory "+scyllaDBConnClientFactory);
	}

}
