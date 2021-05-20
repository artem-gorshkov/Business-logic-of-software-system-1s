C:\kafka\bin\windows\zookeeper-server-start.bat C:\kafka\config\zookeeper.properties
SLEEP 3
C:\kafka\bin\windows\kafka-server-start.bat C:\kafka\config\server.properties
SLEEP 3
::C:\kafka\bin\windows\kafka-topics.bat --create --topic report-events --bootstrap-server localhost:9092
::C:\kafka\bin\windows\kafka-console-consumer.bat --topic report-events --from-beginning --bootstrap-server localhost:9092
::C:\kafka\bin\windows\kafka-topics.bat --delete --zookeeper localhost:2181 --topic report-events