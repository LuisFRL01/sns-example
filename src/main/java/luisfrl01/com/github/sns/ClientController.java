package luisfrl01.com.github.sns;

import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sns-example")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/subscribe")
    public void subscribeSns() {
        clientService.subscribeSns();
    }

    @GetMapping("/topics")
    public List<Topic> getTopics() {
        return clientService.getTopics();
    }

    @PostMapping("/create/topic/{topicName}")
    public void createTopic(@PathVariable("topicName") String topicName) {
        clientService.createTopic(topicName);
    }

    @PostMapping("/publish")
    public void postWithPublish(@RequestBody Client client) {
        clientService.sendByPublish(client);
    }

    @PostMapping("/send")
    public void postWithSendClient(@RequestBody Client client) {
        clientService.sendClient(client);
    }

    @PostMapping("/send-topic-name")
    public void postWithsendClientWithTopicName(@RequestBody Client client) {
        clientService.sendClientWithTopicName(client);
    }

    @PostMapping("/send-topic-name-header")
    public void postWithSendClientWithTopicNameAndHeaders(@RequestBody Client client) {
        clientService.sendClientWithTopicNameAndHeaders(client);
    }

    @PostMapping("/send-convert")
    public void postWithSendClientWithConvertAndSend(@RequestBody Client client) {
        clientService.sendClientWithConvertAndSend(client);
    }

    @PostMapping("/send-convert-topic-name")
    public void postWithSendClientWithConvertAndSendAndTopicName(@RequestBody Client client) {
        clientService.sendClientWithConvertAndSendAndTopicName(client);
    }

    @PostMapping("/send-convert-topic-name-headers")
    public void postWithSendClientWithConvertAndSendAndTopicNameAndHeaders(@RequestBody Client client) {
        clientService.sendClientWithConvertAndSendAndTopicNameAndHeaders(client);
    }

    @PostMapping("/send-notification")
    public void postWithSendClientWithNotification(@RequestBody Client client) {
        clientService.sendClientWithNotification(client);
    }

    @PostMapping("/send-notification-topic-name")
    public void postWithSendClientWithNotificationWithTopic(@RequestBody Client client) {
        clientService.sendClientWithNotificationWithTopic(client);
    }
}
