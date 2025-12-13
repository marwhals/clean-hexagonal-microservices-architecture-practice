package order.service.domain.ports.input.service;

import jakarta.validation.Valid;
import order.service.domain.dto.create.CreateOrderCommand;
import order.service.domain.dto.create.CreateOrderResponse;
import order.service.domain.dto.track.TrackOrderQuery;
import order.service.domain.dto.track.TrackOrderResponse;


public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);

}
