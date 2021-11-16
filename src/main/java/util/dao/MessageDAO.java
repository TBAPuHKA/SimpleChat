package util.dao;

import entity.*;
import model.*;

import java.util.List;

public interface MessageDAO {

    List<Message> getMessages();

    void insertMessage(MessageDTO messageDto);
}
