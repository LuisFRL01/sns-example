package luisfrl01.com.github.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sns-example")
public class ClientController {

    @Autowired
    private ClientService clientService;

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
