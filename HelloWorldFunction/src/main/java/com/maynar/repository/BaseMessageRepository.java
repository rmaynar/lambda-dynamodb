package com.maynar.repository;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.maynar.model.BaseMessage;

public class BaseMessageRepository {

    private DynamoDBMapper mapper;

    public void insert(BaseMessage message){

        mapper = new DynamoDBMapper(amazonDynamoDBConfigStandard());
        mapper.save(message);
    }

    /**
     * Config using parameters
     * @return
     */
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

    /**
     * Default configuration: lambda needs AmazonDynamoDBFullAccess permission
     * @return
     */
    public AmazonDynamoDB amazonDynamoDBConfigStandard(){

        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build();
    }
}
