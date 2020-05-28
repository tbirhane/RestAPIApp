package com.javainterview.java_apple.controller;

import com.javainterview.java_apple.dto.DataDto;
import com.javainterview.java_apple.model.Data;
import com.javainterview.java_apple.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {
    @Autowired
    private DataService dataService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${URL}")
    private String URL;
    @Value("${maxNumberOfCalls}")
    private int number;
    /*Note:
           Asynchronous call would be
          WebCleint webclient = WebClient.builder()
       .baseUrl("https://gturnquist-quoters.cfapps.io/api/random")
       .build();

      Mono<DataDto> result =  webClient.get()
           .uri(uriBuilder -> uriBuilder
               .build())
           .retrieve()
           .bodyToMono(DataDto.class);
            */
    @GetMapping("/data")
    public DataDto getDataFromAPI() throws Exception {
        DataDto dataDto = null;
        dataDto = restTemplate.getForObject(URL, DataDto.class);
        dataService.save(dataDto);
        return dataDto;
    }

    @GetMapping("/data/{n}")
    //    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
    public List<DataDto> getAllDataFromAPI(@PathVariable int n) throws  Exception {
        if(n > number) throw new IllegalArgumentException("Invalid input");
        List<DataDto> datas = new ArrayList<>();
        for(int i=0; i<n; i++) {
            DataDto data = restTemplate.getForObject(URL, DataDto.class);
            datas.add(data);
        }
        dataService.save(datas);
        return datas;
    }
    @GetMapping("/getData/{id}")
    public Data getDataFromRepo(@PathVariable int id) throws Exception{
        return  dataService.getDataFromRepo(id);
    }
    @GetMapping("/getAllData")
    public List<Data> getAllDataFromRepo(){
        return dataService.getAllFromRepo();
    }
}
