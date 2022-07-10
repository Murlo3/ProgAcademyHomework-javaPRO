package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet{
    static final String LOGIN = "admin";
    static final String PASS = "admin1admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        Checker one = new Checker();

        if (login.equals("") || password.equals("") || age.equals("")) {
            HttpSession session = request.getSession(false);
            session.setAttribute("empty_field", "0");
            age = "-1";
        }
        if (Integer.parseInt(age) < 18 && Integer.parseInt(age) > 0) {
            HttpSession session = request.getSession(false);
            session.setAttribute("age", "0");
        }
        if (!(LOGIN.equals(login)) || !(PASS.equals(password))){
            HttpSession session = request.getSession(false);
            session.setAttribute("wrong_data", "0");
        }
        if (!one.passChecker(password)) {
            HttpSession session = request.getSession(false);
            session.setAttribute("short_pass", "0");
        }
        if (LOGIN.equals(login) && PASS.equals(password) && Integer.parseInt(age) >= 18) {
            HttpSession session = request.getSession();
            session.setAttribute("user_login", login);
            session.setAttribute("age", "1");
            session.setAttribute("empty_field", "1");
            session.setAttribute("wrong_data", "1");
            session.setAttribute("short_pass", "1");
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");
    }
}
