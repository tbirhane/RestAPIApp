package com.javainterview.java_apple.service;

import com.javainterview.java_apple.dto.DataDto;
import com.javainterview.java_apple.dto.ValueDto;
import com.javainterview.java_apple.model.Data;
import com.javainterview.java_apple.model.Value;
import com.javainterview.java_apple.repository.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DataService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DataRepo dataRepo;

    private static Logger log = Logger.getLogger(DataService.class.getName());

    public void save(DataDto dataDto){
        if(dataDto != null) {
            ValueDto valueDto = dataDto.getValue();
            Value value = valueDto != null? new Value(valueDto.getId(), valueDto.getQuote()):null;
            Data data = new Data(dataDto.getType(), value);
            if(data != null)
                dataRepo.save(data);
        }
    }

    public void save(List<DataDto> dataDtos) throws InterruptedException {
        for(DataDto dataDto: dataDtos) {
            if (dataDto != null) {
                ValueDto valueDto = dataDto.getValue();
                Value value = valueDto != null ? new Value(valueDto.getId(), valueDto.getQuote()) : null;
                Data data = new Data(dataDto.getType(), value);
                if (data != null && value != null)
                    dataRepo.save(data);
                Thread.sleep(500);
            }
        }
    }

    public Data getDataFromRepo(int  id){
        Optional<Data> d = dataRepo.findById(id);
        if(d.isPresent()){
           return d.get();
        }
        return null ;
    }
    public List<Data> getAllFromRepo(){
        List<Data> list = new ArrayList<>();
        Iterable<Data> it =  dataRepo.findAll();
        if(it != null)
            it.forEach(d -> list.add(d));
        return list;
    }
}
