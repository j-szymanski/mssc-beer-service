package prv.jws.brewery.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by Jerzy Szymanski on 24.09.2020 at 10:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateBeerOrderResponse {
    private UUID orderId;
    private Boolean isValid;
}
