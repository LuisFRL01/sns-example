package luisfrl01.com.github.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${aws.sns-topic.name}")
    private String topicName;

    @Value("${aws.fila}")
    private String fila;

    @Value("${aws.region}")
    private String region;

    @Autowired
    private AmazonSNS amazonSNS;

    @Autowired
    private AmazonSQSAsync amazonSQSAsync;

    public void subscribeSns() {
        GetQueueUrlResult queueUrl = amazonSQSAsync.getQueueUrl(fila);
        amazonSNS.subscribe("arn:aws:sns:" + region + ":000000000000:" + topicName, "sqs", queueUrl.getQueueUrl());
    }

    public List<Topic> getTopics() {
        ListTopicsResult listTopicsResult = amazonSNS.listTopics();
        return listTopicsResult.getTopics();
    }

    public void createTopic(String topic) {
        amazonSNS.createTopic(topic);
    }

    public void sendByPublish(Client client) {
        amazonSNS.publish("arn:aws:sns:" + region + ":000000000000:" + topicName, client.toString());
    }

    public void sendClient(Client client)  {
        GenericMessage<Client> message = new GenericMessage<>(client);
        log.info("Method send args <<generic message>>. Using default topic.");
        notificationMessagingTemplate.send(message);
    }

    public void sendClientWithTopicName(Client client) {
        GenericMessage<Client> message = new GenericMessage<>(client);
        log.info("Method send args to topic {} and <<generic message>>.", topicName);
        notificationMessagingTemplate.send(topicName, message);
    }

    public void sendClientWithTopicNameAndHeaders(Client client) {
        log.info("Method send args to topic {} and <<generic message>>. Message with headers", topicName);
        HashMap<String, Object> messageHeaders = new HashMap<>();
        messageHeaders.put("Key_send_message", "Value_send_message");
        GenericMessage<Client> message = new GenericMessage<>(client, messageHeaders);
        notificationMessagingTemplate.send(topicName, message);
    }

    public void sendClientWithConvertAndSend(Client client) {
        log.info("Method convertAndSend args <<object>>. Using default topic.");
        notificationMessagingTemplate.convertAndSend(new GenericMessage<>(client));
    }

    public void sendClientWithConvertAndSendAndTopicName(Client client) {
        log.info("Method convertAndSend args to topic {} and <<object>>.", topicName);
        notificationMessagingTemplate.convertAndSend(topicName, client);
    }

    public void sendClientWithConvertAndSendAndTopicNameAndHeaders(Client client) {
        HashMap<String, Object> messageHeaders = new HashMap<>();
        messageHeaders.put("Key_send_message", "Value_send_message");
        log.info("Method convertAndSend args to topic {}, <<object>>, <<messageHeaders>>.", topicName);
        notificationMessagingTemplate.convertAndSend(topicName, client, messageHeaders);
    }

    public void sendClientWithNotification(Client client) {
        log.info("Method convertAndSend args to topic {}, <<object>>, <<messageHeaders>>.", topicName);
        notificationMessagingTemplate.sendNotification(client, "transaction");
    }

    public void sendClientWithNotificationWithTopic(Client client) {
        log.info("Method sendNotification args to topic {}, <<object dto>>, <<subject>>.", topicName);
        notificationMessagingTemplate.sendNotification(topicName, client, "purchase-transaction-subject");
    }
}
