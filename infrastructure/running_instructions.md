# Running instructions for Kafka cluster

---

```bash
## Run Zookeeper if still using

docker-compose -f common.yml -f zookeeper.yml up

```

```bash
## Health check for zookeeper

echo ruok | nc localhost 2181

```

```bash
## Run cluster

docker-compose -f common.yml -f kafka_cluster.yml up

```

```bash
## Setup Kafka topics

docker-compose -f common.yml -f init_kafka.yml up

```