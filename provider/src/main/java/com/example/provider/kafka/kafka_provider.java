package com.example.provider.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

/**
 * apache kafka生产者工具
 * Created on : 2020年6月30日
 *
 * @author poke
 * @version 1.0
 */
public class kafka_provider {
    public static Logger LOG = LoggerFactory.getLogger(kafka_provider.class);
    public static String content = "{\n" +
            "    \"mediaNumId\":\"9409850\",\n" +
            "    \"content\":\"<P>人都说“少来夫妻老来伴”，表力，不是吗？</P>\",\n" +
            "    \"title\":\"父母爱情：人老珠黄被丈夫嫌弃的张桂英和王秀娥，如何实现逆袭？\",\n" +
            "    \"author\":\"老崔评剧\",\n" +
            "    \"copyrightSource\":\"老崔评剧\",\n" +
            "    \"url\":\"http://kuaibao.qq.com/s/20200629A0FR8Z00\",\n" +
            "    \"appSourceUrl\":\"http://kuaibao.qq.com/s/20200629A0FR8Z00\",\n" +
            "    \"pubTime\":\"2020-06-29 15:07:31\",\n" +
            "    \"crawlTime\":\"2020-08-26 16:03:36\",\n" +
            "    \"serviceId\":402\n" +
            "}";

    public static void main(String[] args) {
        try {
            sendArticle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Producer<String, String> producer;

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.19.1.9:9092,10.19.1.10:9092,10.19.1.11:9092");  //服务器地址端口
        /*ack 配置项用来控制producer要求leader确认多少消息后返回调用成功。当值为0时producer不需要等待任何确认消息。当值为1时只需要等待leader确认。当值为-1或all时需要全部ISR集合返回确认才可以返回成功。*/
        props.put("acks", "all");
        /*当 retries > 0 时，如果发送失败，会自动尝试重新发送数据。发送次数为retries设置的值。*/
        props.put("retries", 0);
        /*buffer.memory、batch.size、linger.ms三个参数用来控制缓冲区大小和延迟发送时间，具体含义可以参考官方文档的配置。*/
        props.put("batch.size", 16384);
        /*key.serializer 和 value.serializer 指定使用什么序列化方式将用户提供的key和value进行序列化。运行此程序，在$KAFKA_HOME目录下运行：*/
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        producer = new KafkaProducer<String, String>(props);
    }

    /**
     * 向kafka发送消息
     *
     * @param message
     * @return
     */
    public static void sendMessage(ProducerRecord message) {
        try {
            producer.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.flush();
            }
        }
    }

    /**
     * 向kafka发送消息
     *
     * @param
     */
    public static void sendArticle() {
        int a = 0;
        String file = "D:\\writeDoc\\es.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String textLine = null;
            while ((textLine = br.readLine()) != null) {//使用readLine方法，一次读一行
                JSONObject article = JSONObject.parseObject(textLine, JSONObject.class);
                sendMessage(new ProducerRecord<String, String>("test", article.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 向kafka发送消息
     *
     * @param topic 主题
     * @param key   消息key
     * @param value 消息值
     */
    public static void sendMessage(String topic, String key, String value) {
        sendMessage(new ProducerRecord(topic, key, value));
    }

//    /**
//     * 单例模式确保全局中只有一份该实例
//     */
//    private static class ProducerKafkaHolder{
//        private static KafkaProducerUtil instance = new KafkaProducerUtil();
//    }
//
//    /**
//     * 延迟加载，避免启动加载
//     * @return
//     */
//    public static KafkaProducerUtil getInstance(){
//        return ProducerKafkaHolder.instance;
//    }


    /**
     * 并发测试
     */
//    private void testConcurrent () {
//        try {
//            // 初始化计数器为1
//            for (int i = 0; i < 100; i ++) {
//                new Thread(new TestRun(i)).start();
//            }
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        }
//    }

    /**
     * 线程类
     */
//    private class TestRun implements Runnable {
//        private final int num;
//
//        public TestRun(int num) {
//            this.num = num;
//        }
//
//        @Override
//        public void run() {
//            try {
//                // 执行操作
//                DataTestKafka.sendMessage("PRE_ARTICLE",content);
//                System.out.println(num);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

}