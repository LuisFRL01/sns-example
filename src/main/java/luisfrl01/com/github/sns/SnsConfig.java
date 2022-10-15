package luisfrl01.com.github.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class SnsConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.access-key}")
    private String awsAccessKey;

    @Value("${aws.secret-key}")
    private String awsSecretKey;

    @Value("${aws.endpoint}")
    private String snsEndpoint;

    @Value("${aws.sns-topic.name}")
    private String topicName;

    @Bean
    public AWSCredentials credentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    @Bean
    public AmazonSNS amazonSNS() {
        return AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(snsEndpoint, awsRegion))
                .build();
    }

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate() {
        NotificationMessagingTemplate notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSNS());
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setSerializedPayloadClass(String.class);
        notificationMessagingTemplate.setMessageConverter(mappingJackson2MessageConverter);
        notificationMessagingTemplate.setDefaultDestinationName(topicName);
        return notificationMessagingTemplate;
    }

//    @Bean
//    public NotificationMessagingTemplate notificationMessagingTemplate(
//            AmazonSNS amazonSNS) {
//        return new NotificationMessagingTemplate(amazonSNS);
//    }
}
