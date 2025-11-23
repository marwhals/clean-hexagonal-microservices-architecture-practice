package service.domain.ports.input.service;

import jakarta.validation.Valid;
import service.domain.dto.create.CreateOrderCommand;
import service.domain.dto.create.CreateOrderResponse;
import service.domain.dto.track.TrackOrderQuery;
import service.domain.dto.track.TrackOrderResponse;


public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);

}
