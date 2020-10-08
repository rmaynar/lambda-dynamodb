package com.maynar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.maynar.model.BaseMessage;
import com.maynar.repository.BaseMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseMessageApp implements RequestHandler<BaseMessage, String> {

    private final Logger LOGGER = LoggerFactory.getLogger(BaseMessageApp.class);

    private BaseMessageRepository messageRepository;

    @Override
    public String handleRequest(BaseMessage message, Context context) {

        LOGGER.info("Lambda invoked with message :" + message.toString());

        messageRepository = new BaseMessageRepository();
        messageRepository.insert(message);

        return "Message saved to DynamoDB";
    }
}
