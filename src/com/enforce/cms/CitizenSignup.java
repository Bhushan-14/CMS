package com.enforce.cms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enforce.ConnectionDB.*;


/**
 * Servlet implementation class CitizenSignup
 */
public class CitizenSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CitizenSignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//int UserID =Integer.parseInt( request.getParameter("UserID"));
		String Username= request.getParameter("Username");
		String Email= request.getParameter("Email");
		String Password= request.getParameter("Password");
		String Role= request.getParameter("Role");
		try
		{	
			
			Connection con = ConnectDB.connect();
			PreparedStatement p1 = con.prepareStatement("select * from user where Email=?");
			p1.setString(1, Email);
			ResultSet rs = p1.executeQuery();
			if(rs.next())
			{
				response.sendRedirect("citizensignup.jsp");
			
			}
			else
			{  
				
				PreparedStatement p2 = con.prepareStatement("insert into user values(?,?,?,?,?)");
				p2.setInt(1, 0);
				p2.setString(2, Username);
				p2.setString(3, Email);
				p2.setString(4, Password);
				p2.setString(5, Role);
				
				int i = p2.executeUpdate();
				if(i>0)
				{
					response.sendRedirect("citizen.html");
				}
				else
				{
					response.sendRedirect("citizensignup.jsp");
				}
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	}

}
