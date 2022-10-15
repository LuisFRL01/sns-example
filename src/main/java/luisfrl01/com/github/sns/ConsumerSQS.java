package luisfrl01.com.github.sns;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerSQS {

    @SqsListener(value = "${aws.fila}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumer(String message) {
        log.info("mensagem recebida {}", message);
    }
}
