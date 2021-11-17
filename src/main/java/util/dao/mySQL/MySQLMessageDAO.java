package util.dao.mySQL;

import util.dao.MessageDAO;
import entity.MessageDTO;
import model.Message;
import lombok.extern.slf4j.Slf4j;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MySQLMessageDAO implements MessageDAO {

    private final static String GET_MESSAGES_LIST = "SELECT messages.messageid, messages.content, users.name FROM messages INNER JOIN users ON messages.userid = users.userid ORDER BY messages.messageid DESC LIMIT 50;";
    private final static String ADD_MESSAGE = "INSERT INTO messages (content,userid) VALUES (?,?)";
    private final MySQLDAOFactory ms;

    public MySQLMessageDAO(MySQLDAOFactory ms) {
        this.ms = ms;
    }

    @Override
    public List<Message> getMessages() {

        List<Message> messageList = new ArrayList<>();
        Connection connection = ms.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_MESSAGES_LIST);
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt("messageid"));
                message.setContent(resultSet.getString("content"));
                message.setUserName(resultSet.getString("name"));
                messageList.add(message);
            }
        } catch (SQLException e) {
            log.error("Get message history from database | FAIL");
            e.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                log.error("Close connections | FAIL");
                e.printStackTrace();
            }
        }

        return messageList;
    }

    @Override
    public void insertMessage(MessageDTO messageDto) {
        PreparedStatement preparedStatement = null;
        Connection connection = ms.getConnection();

        try {
            preparedStatement = connection.prepareStatement(ADD_MESSAGE);
            preparedStatement.setString(1, messageDto.getContent());
            preparedStatement.setInt(2, messageDto.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Insert message to database | FAIL");
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                log.error("Close connections | FAIL");
                e.printStackTrace();
            }
        }
    }
}
