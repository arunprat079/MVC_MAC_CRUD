package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class EmpCrudController extends MultiActionController 
{
	
	
	public ModelAndView empsave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/arun","root","");
		ResultSet rs= con.createStatement().executeQuery("select max(id) from emp");
		int maxid=0;
		if(rs.next())
		{
			maxid=rs.getInt(1);
			maxid++;
		}
		PreparedStatement ps=con.prepareStatement("insert into emp values(?,?,?,?)");
		ps.setInt(1, maxid);
		ps.setString(2, name);
		ps.setString(3, email);
		ps.setString(4, address);
		
		int i=ps.executeUpdate();
		con.close();
		ModelAndView mav=null;
		
		if(i!=0)
		{
			mav=new ModelAndView("success");
		}
		else
		{
			mav=new ModelAndView("fail");
		}
		
		return mav;
	}
	
	
    public ModelAndView empupdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
    	int id=Integer.parseInt(request.getParameter("id"));
    	String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/arun","root","");
		PreparedStatement ps=con.prepareStatement("update emp set name=?, email=?, address=? where id=?");
		
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setString(3, address);
		ps.setInt(4, id);
		
		int i=ps.executeUpdate();
		con.close();
		ModelAndView mav=null;
		
		if(i!=0)
		{
			mav=new ModelAndView("success");
		}
		else
		{
			mav=new ModelAndView("fail");
		}
		
		return mav;
	}

}

    
    
