package com.pkuokuo.MQConsumer.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class ConsumerKafka {


    @org.springframework.kafka.annotation.KafkaListener(topics = {"pkuokuo"})
    public void topic_test(ConsumerRecord<?, ?> record, Acknowledgment ack, String data) {
        //record是offest等kafka的信息
        log.warn("record ======== " + record);
        //message是kafka读到的数据
//        ack是用于手动提交offest的
        log.warn("ack ======== " + ack);


        Object message = data;
        String msg = null;
        try {
            //msg是json字符串
            msg = URLDecoder.decode(String.valueOf(message), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            log.warn("msg can not conver to json,msg:" + message);
            log.warn(e1.getMessage() + "  " + e1.getCause());
        }
        System.out.println("ConsumerKafka_topic_test_msg:"+ msg);

//        手动提交偏移量offest
        ack.acknowledge();

    }


}
