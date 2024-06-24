package com.aws.dynamodb;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.aws.dynamodb.service.ArticleDao;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RequiredArgsConstructor
public class DynamodbApplication {

	
	Region region = Region.AP_SOUTH_1;
    String accessKeyId = "**********";
    String secAccKey = "**********";

	
	public static void main(String[] args) {
		SpringApplication.run(DynamodbApplication.class, args);
	}

    @Bean
    public DynamoDbClient dynamoDbClient(AwsCredentialsProvider awsCredentialsProvider) {
        return DynamoDbClient.builder()
        		.endpointOverride(URI.create("https://dynamodb.ap-south-1.amazonaws.com"))
                .region(region)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        AwsCredentials awsCredentials = AwsBasicCredentials.create(
                accessKeyId,
                secAccKey
        );
        return StaticCredentialsProvider.create(awsCredentials);
    }
}
