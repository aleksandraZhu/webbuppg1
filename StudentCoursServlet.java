package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name="StudentCoursesServlet", urlPatterns = "/attendance" )
public class StudentCoursServlet extends HttpServlet {

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
        out.println("<title>Students-Courses</title>");
        out.println("</head>");
        out.println("<body>");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8888/gritAcademy", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from attendance");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Student ID</th><th>Course ID</th></tr>");
            while (rs.next()) {
                int id = rs.getInt("id");
                String studentid = rs.getString("student.id");
                String coursesid = rs.getString("course.id");
                out.println("<tr><td>" + id + "</td><td>" + studentid + "</td><td>" + coursesid + "</td></tr>");

            }
            out.println("</table>");
            out.println("<div text-align=center>");
            out.println("<a href=\"http://localhost:8081\"> Home </a>");
            out.println("<a href=\"http://localhost:8081/students\">Students</a>");
            out.println("<a href=\"http://localhost:8081/courses\">Courses</a>");
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