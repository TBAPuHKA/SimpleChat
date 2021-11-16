package controller;

import model.User;
import lombok.extern.slf4j.Slf4j;
import service.LoginService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginController extends HttpServlet {

    private static final String LOGIN_FORM = "WEB-INF/views/loginView.jsp";
    private static final String CHAT_FORM = "WEB-INF/views/chatView.jsp";
    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            log.warn(String.format("Login session for user [%s] NOT found", session.getAttribute("user")));
            rd = request.getRequestDispatcher(LOGIN_FORM);
        } else {
            rd = request.getRequestDispatcher("/chat");
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user;

        RequestDispatcher rd;
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");

        if (userName == null || userName.length() == 0) {
            rd = request.getRequestDispatcher(LOGIN_FORM);
        } else {
            user = loginService.getUserByName(userName);

            if (user == null) {
                loginService.insertUser(new User(userName));
                user = loginService.getUserByName(userName);
            }
            session.setAttribute("user", user);
            rd = request.getRequestDispatcher("/chat");
        }


        rd.forward(request, response);
    }

}
