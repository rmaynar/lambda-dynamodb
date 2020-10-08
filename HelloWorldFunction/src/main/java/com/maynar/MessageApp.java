package com.maynar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.maynar.model.Message;
import com.maynar.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageApp implements RequestHandler<Message, String> {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageApp.class);

    private MessageRepository messageRepository;

    @Override
    public String handleRequest(Message message, Context context) {

        LOGGER.info("Lambda invoked with message :" + message.toString());

        messageRepository = new MessageRepository();
        messageRepository.insert(message);

        return "Message saved to DynamoDB";
    }
}
