package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.MessageDTO;
import model.Message;
import util.dao.DaoFactory;
import lombok.extern.slf4j.Slf4j;
import util.dao.MessageDAO;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ChatService {

    private final MessageDAO messageDAO;

    public ChatService() {
        DaoFactory daoFactory = DaoFactory.getInstance(1);
        assert daoFactory != null;
        this.messageDAO = daoFactory.getMessageDAO();
    }

    public List<Message> getMessages() {
        return messageDAO.getMessages();
    }

    public void insertMessage(MessageDTO messageDto) {
        messageDAO.insertMessage(messageDto);
    }

    public List<Message> getMessageHistory(){
        List<Message>resList = getMessages();
        Collections.reverse(resList);
        return resList;
    }

    public String parseToJSONfromList(List list) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFromList = null;
        try {
            jsonFromList = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.error("Parse JSON from list | FAIL");
            e.printStackTrace();
        }
        return jsonFromList;
    }
}
