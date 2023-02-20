package org.commonjava.indy.service.tracking.client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.commonjava.event.file.FileEvent;
import org.commonjava.event.file.FileEventType;

import java.util.Properties;

public class SimpleProducer {

    public static void main(String[] args) throws Exception
    {

        String topicName = "file-event";

        Properties props = new Properties();

        props.put(ProducerConfig.CLIENT_ID_CONFIG, "Simple producer");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.commonjava.service.metadata.client.FileEventSerializer");

        Producer<String, FileEvent> producer = new KafkaProducer(props);

        for(int i = 0; i < 10; i++)
        {
            FileEvent event = new FileEvent(FileEventType.STORAGE);
            event.setSessionId(String.valueOf(i));
            event.setTargetPath("io/enmasse/keycloak-user-api/0.34.0.test-0001/keycloak-user-api-0.34.0.test-0001.pom");
            event.setStoreKey("maven:hosted:build-0001");
            producer.send(new ProducerRecord(topicName,
                    String.valueOf(i), event ));

        }
        System.out.println("Message sent successfully");
        producer.close();
    }
}
