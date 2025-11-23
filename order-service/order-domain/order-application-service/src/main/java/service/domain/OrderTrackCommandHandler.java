package service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import service.domain.dto.track.TrackOrderQuery;
import service.domain.dto.track.TrackOrderResponse;

@Slf4j
@Component
public class OrderTrackCommandHandler {

    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }

}
