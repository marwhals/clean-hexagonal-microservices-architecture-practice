package service.domain.ports.input.service;

import service.domain.dto.create.CreateOrderCommand;
import service.domain.dto.create.CreateOrderResponse;
import service.domain.dto.track.TrackOrderQuery;
import service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);

}
