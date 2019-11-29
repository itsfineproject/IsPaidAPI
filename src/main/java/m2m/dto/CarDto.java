package m2m.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarDto {

    private String parkingId;
    private String carNumber;
    private String dateTime;

}
