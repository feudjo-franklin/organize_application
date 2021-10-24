package org.studyeasy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.studyeasy.entity.User;
import org.studyeasy.model.UsersModel;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/operation")
public class OperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource dataSource;
	
    public OperationController() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();
		
		switch(page) {
			case "listusers":
				listUsers(request, response);
				break;
			case "adduser":
				addUser(request, response);
				break;
			default:
				errorPage(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> listUsers = new ArrayList<>();
		listUsers = new UsersModel().listUsers(dataSource);
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("title", "List of users");
		request.getRequestDispatcher("listUser.jsp").forward(request, response);
	}
	
	protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Add User");
		request.getRequestDispatcher("addUser.jsp").forward(request, response);
	}
	
	protected void errorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Error page");
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
}
