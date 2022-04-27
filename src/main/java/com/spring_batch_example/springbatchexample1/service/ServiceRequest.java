package com.spring_batch_example.springbatchexample1.service;

import com.spring_batch_example.springbatchexample1.Dao.InputPayload;
import com.spring_batch_example.springbatchexample1.Dao.User;
import com.spring_batch_example.springbatchexample1.datastore.DBConnClientFactory;
import com.spring_batch_example.springbatchexample1.router.DBRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceRequest {
    public DBRouter dbRouter;
    private RestTemplate restTemplate = new RestTemplate();

    public void routeRequest(InputPayload inputPayload){
        User user = new User();
        System.out.print("service request received");
//        dbRouter.dbIdMap.get(inputPayload.getDBType());
        user.setName(inputPayload.getName());
        user.setAddr(inputPayload.getAddr());
        restTemplate.postForLocation("http://localhost:8081/v1/user/save", user);
    }
}
