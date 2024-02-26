package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name="CoursesServlet", urlPatterns = "/courses" )
public class CoursesServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Courses</title>");
        out.println("</head>");
        out.println("<body>");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8888/gritAcademy", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from courses");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>NAME</th><th>YHP</th><th>DESCRIPTION</th></tr>");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String yhp = rs.getString("YHP");
                String desc = rs.getString("description");
                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + yhp + "</td><td>" + desc +  "</td></tr>");

            }
            out.println("</table>");
            out.println("<div text-align=center>");
            out.println("<a href=\"http://localhost:8081\"> Home </a>");
            out.println("<a href=\"http://localhost:8081/students\">Students</a>");
            out.println("<a href=\"http://localhost:8081/attendance\">Students-Courses</a>");
            out.println("</div>");
            out.println("</body></html>");
            con.close();
        }
        catch (Exception e)
        {
            out.println("error");
        }
    }
}