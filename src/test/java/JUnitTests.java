package test.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.application.controller.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;

public class JUnitTests {

    IsPaidApiController controller = new IsPaidApiController();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    String car_number = "123-456-789";

    @Test
    public void testIsPaidProbability() {
        List<Boolean> results = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            results.add(controller.isPaid(car_number));
        };
        List<Boolean> trueAnswers = results.stream().filter(unit -> unit == true).collect(Collectors.toList());
        double presetProbability = controller.getProbabitity()*1000;
        assertTrue((trueAnswers.size() <= (presetProbability+50)) && (trueAnswers.size() > presetProbability-50));
    }

}
