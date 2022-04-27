package com.spring_batch_example.springbatchexample1.datastore;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.common.settings.Settings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class ElasticsearchConnection implements DBConnClientFactory {

//    public static void main(String[] args) throws Exception {
//        RestHighLevelClient client = createClient();
//        testConnection(client);
//    }

//    public static RestHighLevelClient createClient() {
//    @Bean(name="esCreateClient")
//    public RestHighLevelClient createClient() {
    @Override
    public String createClient() {
        String esUserName = "";
        String esPassword = "";
        boolean esAuthenticationRequired = false;

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esUserName, esPassword));

        RestClientBuilder restClientBuilder = RestClient
                .builder(new HttpHost("localhost", 9200, "http"));

        // Use this one if your ElasticSearch server is setup to use username & password authentication
        if (esAuthenticationRequired) {
            restClientBuilder.setHttpClientConfigCallback(h -> h.setDefaultCredentialsProvider(credentialsProvider));
        }
        return new RestHighLevelClient(restClientBuilder).toString();
    }


//    public static void testConnection(RestHighLevelClient restHighLevelClient) throws Exception {

//    @Bean(name="esTestConnection")
    public Boolean testConnection(RestHighLevelClient restHighLevelClient) throws Exception {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("hello-world");
            restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT); // 1
        } catch (Exception ignored) {
        }

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("hello-world");
        createIndexRequest.settings(
                Settings.builder().put("index.number_of_shards", 1)
                        .put("index.number_of_replicas", 0));
        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        Request request = new Request("GET", "/_cat/indices?h=i");
        
        InputStream inputStream = restHighLevelClient.getLowLevelClient()
                .performRequest(request)
                .getEntity()
                .getContent();

        List<String> indexes = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.toList());

        for(String indexName: indexes){
            System.out.println("indexName: "+indexName);
        }
        return true;
    }
}
