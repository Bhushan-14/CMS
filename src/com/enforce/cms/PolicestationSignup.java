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
 * Servlet implementation class PolicestationSignup
 */
public class PolicestationSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PolicestationSignup() {
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
		String StationName= request.getParameter("StationName");
		String Location= request.getParameter("Location");
		String PhoneNumber= request.getParameter("PhoneNumber");
		String Email= request.getParameter("Email");
		String Password= request.getParameter("Password");
		String ChiefOfficer= request.getParameter("ChiefOfficer");
		//String LastUpadated= request.getParameter("LastUpadated");
		
		try
		{	
			
			Connection con = ConnectDB.connect();
			PreparedStatement p1 = con.prepareStatement("select * from policestations where Email=?");
			p1.setString(1, Email);
			ResultSet rs = p1.executeQuery();
			if(rs.next())
			{
				response.sendRedirect("policestationsignup.jsp");
			
			}
			else
			{  
				
				PreparedStatement p2 = con.prepareStatement("insert into policestations values(?,?,?,?,?,?,?)");
				p2.setInt(1, 0);
				p2.setString(2, StationName);
				p2.setString(3, Location);
				p2.setString(4, PhoneNumber);
				p2.setString(5, Email);
				p2.setString(6, Password);
				p2.setString(7, ChiefOfficer);
				
				int i = p2.executeUpdate();
				if(i>0)
				{
					response.sendRedirect("police.html");
				}
				else
				{
					response.sendRedirect("policestationlogin.jsp");
				}
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	
	}

}
