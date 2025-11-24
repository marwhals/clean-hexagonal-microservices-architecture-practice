# Kafka Notes - See diagrams

---

- Kafka brokers: Servers run in a cluster
- Topics: Logical data unit that holds multiple partitions
- Partitions: Smallest storage unit that holds a subset or records
- Producers: Writes to end of a specific partition
- Consumers: Reads from a partition using an offset
- Replication: Resilient and fault-tolerant
- Scaling: Partition strategy
- Immutable append-only event logs

----

## Zookeeper and Schema Registry

- Zookeeper: Manage cluster, store metadata
- KIP-500: Removal of zookeeper, still not production ready!
- Schema Registry: Stores versioned history of all schemas by id
- Producer send schema to schema registry and get schema id
- Consumer retrieve schema by id
- Data is serialized / deserialized with registered schema


