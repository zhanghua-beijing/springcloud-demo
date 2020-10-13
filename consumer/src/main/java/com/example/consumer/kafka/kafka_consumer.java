package com.example.consumer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class kafka_consumer {
	public static void main(String[] args) throws Exception {
		
		try {
			Properties props = new Properties();
			props.put("bootstrap.servers","10.19.1.9:9092,10.19.1.10:9092,10.19.1.11:9092");
			props.put("group.id", "pre_process");
			props.put("enable.auto.commit", "true");
			props.put("auto.offset.reset", "latest");
			props.put("auto.commit.interval.ms", "1000");
			props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
			consumer.subscribe(Collections.singletonList("test"));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					String value = record.value();
					System.out.println(value);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("when calling kafka output error." + ex.getMessage());
		}
	}
}