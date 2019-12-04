package itsfine.com.ispaidapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class IsPaidApiController {

    Random gen = new Random();
    private double probabitity = 0.8;

    @GetMapping("/checkpaid")
    public boolean isPaid(@RequestParam String car_number){
        return gen.nextDouble()<probabitity;
    }

    public double getProbabitity() {
        return probabitity;
    }
}
