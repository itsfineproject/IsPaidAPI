package m2m.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import m2m.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@EnableBinding(Processor.class)
public class CheckService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    Processor processor;

    //for imitation of request to outer resource
    static RestTemplate restTemplate = new RestTemplate();

    // url that returns json with random letters string
    static String url = "https://helloacm.com/api/random/?n=5";


    @StreamListener(Processor.INPUT)
    void takeSensorData(String parkedCarData) throws IOException {

        CarDto carData = mapper.readValue(parkedCarData, CarDto.class);
        String carNumber = carData.getCarNumber();

        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);

        // to get imitation of boolean "paid/not paid" with 18% probability:
        if(response.getBody().toLowerCase().contains("a")){
            System.out.println("not paid " + carNumber);
            processor.output().send(MessageBuilder.withPayload(parkedCarData).build());
        }
    }
}
