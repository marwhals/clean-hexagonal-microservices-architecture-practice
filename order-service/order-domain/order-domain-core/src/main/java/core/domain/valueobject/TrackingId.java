package core.domain.valueobject;

import microservices.practice.valueobject.BaseId;

import javax.sound.midi.Track;
import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }

}
