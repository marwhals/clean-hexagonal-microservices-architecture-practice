package kafka.consumer;

import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

public interface KafkaSingleItemConsumer<T extends SpecificRecordBase> {
    void receive(T messages, String keys, Integer partitions, Long offsets);
}
