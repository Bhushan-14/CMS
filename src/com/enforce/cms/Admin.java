package com.enforce.cms;

import java.io.IOException;
import java.sql.*;
import com.enforce.ConnectionDB.ConnectDB;
import java.lang.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
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
		String ademail = request.getParameter("ademail");
		String adpassword = request.getParameter("adpassword");
		try
		{
			Connection con = ConnectDB.connect();
			PreparedStatement ps2 = con.prepareStatement("select * from admin where ademail=? and adpassword=?");
			ps2.setString(1, ademail);
			ps2.setString(2, adpassword);
			ResultSet rs = ps2.executeQuery();
			if(rs.next())
			{ 
				response.sendRedirect("admin.html");
				
			}
			else
			{
				response.sendRedirect("index.html");
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
