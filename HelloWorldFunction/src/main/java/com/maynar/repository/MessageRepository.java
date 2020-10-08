package com.maynar.repository;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.maynar.model.Message;

public class MessageRepository {

    private DynamoDBMapper mapper;

    public void insert(Message message){

        mapper = new DynamoDBMapper(amazonDynamoDBConfig());
        mapper.save(message);
    }

    public AmazonDynamoDB amazonDynamoDBConfig(){

        String awsEndPoint = System.getenv("endpoint");
        String awsAccessKey = System.getenv("key");
        String awsSecretKey = System.getenv("secret");
        String awsRegion = System.getenv("region");
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndPoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey,awsSecretKey)))
                .build();
    }
}
