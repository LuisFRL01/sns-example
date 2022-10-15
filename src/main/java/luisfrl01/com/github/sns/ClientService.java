package luisfrl01.com.github.sns;

import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${aws.sns-topic.name}")
    private String topicName;

    public void sendClient(Client client) {
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
