package com.enforce.cms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enforce.ConnectionDB.ConnectDB;

/**
 * Servlet implementation class CitizenLogin
 */
public class CitizenLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CitizenLogin() {
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
		String Username = request.getParameter("Username");
		String Password = request.getParameter("Password");
		try
		{
			Connection con = ConnectDB.connect();
			PreparedStatement ps2 = con.prepareStatement("select * from user where Username=? and Password=?");
			ps2.setString(1, Username);
			ps2.setString(2, Password);
			ResultSet rs = ps2.executeQuery();
			if(rs.next())
			{ 
				response.sendRedirect("citizen.html");
				
			}
			else
			{
				response.sendRedirect("CitizenLogin.jsp");
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
