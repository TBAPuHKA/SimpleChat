package controller;

import entity.MessageDTO;
import model.User;
import lombok.extern.slf4j.Slf4j;
import service.ChatService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class ChatController extends HttpServlet {

    private static final String LOGIN_FORM = "WEB-INF/views/loginView.jsp";
    private static final String CHAT_FORM = "WEB-INF/views/chatView.jsp";
    private RequestDispatcher rd;
    private final ChatService chatService = new ChatService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if(session.getAttribute("user")==null){
            log.warn(String.format("Login session for user [%s] NOT found", session.getAttribute("user")));
            rd = request.getRequestDispatcher(LOGIN_FORM);
        } else {
            String jsonList = chatService.parseToJSONfromList(chatService.getMessageHistory());
            request.setAttribute("messageList", jsonList);
            rd = request.getRequestDispatcher(CHAT_FORM);
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String sendMessage = request.getParameter("sendMessage");

        if (session.getAttribute("user") == null || request.getParameter("logOut") != null) {
            log.warn("No login session for user");
            session.setAttribute("user", null);
            rd = request.getRequestDispatcher(LOGIN_FORM);
        } else {
            if(sendMessage!=null){
                chatService.insertMessage(new MessageDTO(sendMessage, ((User)session.getAttribute("user")).getId()));
            }
            String jsonList = chatService.parseToJSONfromList(chatService.getMessageHistory());
            request.setAttribute("messageList", jsonList);
            rd = request.getRequestDispatcher(CHAT_FORM);
        }

        rd.forward(request, response);
    }
}
