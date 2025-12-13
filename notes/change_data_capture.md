# Intro to Change Data Capture *using debezium kafka connector*

---

- Use Push method as opposed to Pulling - closer to real time and less overheads
- Push database records into target source (Kafka) by reading from Transaction Logs
  - Transaction Log: Data change records (Insert/Update/Delete) made by each transaction.
- In Postgres Transaction Log is called a "Write Ahead Log" (WAL)
- Provides near real-time data with very low overhead by listening to the transaction logs and sending them to a target source (Kafka) immediately.
  - This is fast as there are no constraints when writing to logs in comparison to the database
- These are append only logs

---

## Debezium