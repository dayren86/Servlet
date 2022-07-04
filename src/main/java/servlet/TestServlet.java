package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.StringJoiner;

@WebServlet(value = "/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("<h1>hello ${name}<h1>".replace("${name}", parseName(req)));
        resp.getWriter().write("<br>Параметры<br>");
        resp.getWriter().write(getAllParameters(req));
        resp.getWriter().write("<br><br>");
        resp.getWriter().write(getHeaders(req));
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public String parseName(HttpServletRequest req) {
        if (req.getParameterMap().containsKey("name" )) {
            return req.getParameter("name");
        }
        return "unknown";
    }
    public String getAllParameters(HttpServletRequest req) {
        Enumeration<String> parameterNames = req.getParameterNames();

        StringJoiner stringJoiner = new StringJoiner("<br>");

        while (parameterNames.hasMoreElements()) {
            String parametersName = parameterNames.nextElement();
            String s = Arrays.toString(req.getParameterValues(parametersName));
            stringJoiner.add(s);
        }
        return stringJoiner.toString();
    }

    public String getHeaders(HttpServletRequest req) {
        Enumeration<String> headers = req.getHeaderNames();
        StringJoiner stringJoiner = new StringJoiner("<br>");

        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            String header1 = req.getHeader(header);
            stringJoiner.add(header + ": " + header1);
        }
        return stringJoiner.toString();
    }
}
