import org.junit.Test;
import itsfine.com.ispaidapi.controller.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class JUnitTests {

    private IsPaidApiController controller = new IsPaidApiController();

    private String car_number = "123-456-789";

    @Test
    public void testIsPaidProbability() {
        List<Boolean> results = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            results.add(controller.isPaid(car_number));
        }
        ;
        List<Boolean> trueAnswers = results.stream().filter(unit -> unit == true).collect(Collectors.toList());
        double presetProbability = controller.getProbability() * 1000;
        assertTrue((trueAnswers.size() <= (presetProbability + 50)) && (trueAnswers.size() > presetProbability - 50));
    }

}
