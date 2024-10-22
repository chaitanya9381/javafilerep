import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
     //   response.getWriter().write("you have registered");
    //    RETURN REDIRECTACTION("LOGIN");
try (PrintWriter out = response.getWriter()) {

			
			String firstName = request.getParameter("FIRST_NAME");
			String lastName = request.getParameter("LAST_NAME");
			String userName = request.getParameter("USER_NAME");
			String emailId = request.getParameter("EMAIL_ID");
			String password = request.getParameter("PASSWORD");
			String confirmPassword = request.getParameter("CONFIRM_PASSWORD");

			
			if (!password.equals(confirmPassword))
			{
				out.println("Passwords do not match.");
				return;
			}
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application", "sa", "1234")) 
				{
					
				
					String query = "INSERT INTO Registration (first_name, last_name, user_name, email_id, password) VALUES (?, ?, ?, ?, ?)";
					try (PreparedStatement ps = con.prepareStatement(query)) 
					{
						ps.setString(1, firstName);
						ps.setString(2, lastName);
						ps.setString(3, userName);
						ps.setString(4, emailId);
						ps.setString(5, password);

					
						int rowsInserted = ps.executeUpdate();
						
						if (rowsInserted > 0) 
						{
							out.println("Record successfully inserted.");
						} 
						else
						{
							out.println("Error inserting record.");
						}
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace(out);
			}
}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		
	}
}