package com.brain.crud.socialclub.controller;

import com.brain.crud.socialclub.model.User;
import com.brain.crud.socialclub.service.OnlineUsers;
import com.brain.crud.socialclub.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;
    private RequestDispatcher requestDispatcher;
    private String nickname, password;
    private User user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService = new UserService();
        requestDispatcher = request.getRequestDispatcher("/login.jsp");
        if (request.getMethod().equals("POST")) checkAuthData(request, response);
        requestDispatcher.forward(request, response);
    }

    private void checkAuthData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isFieldMissing("nickname", request) &&
                isFieldMissing("password", request)) {
            nickname = request.getParameter("nickname");
            password = request.getParameter("password");
            user = userService.checkUser(nickname, password);
        }

        if (user != null) {
            request.setAttribute("nickname", nickname);
            request.setAttribute("name", user.getName());
            request.setAttribute("surname", user.getSurname());
            OnlineUsers.getInstance().setUserOnline(request.getSession().getId());
            requestDispatcher = request.getRequestDispatcher("/profile");
            requestDispatcher.forward(request, response);
        }

    }


    private boolean isFieldMissing(String field, HttpServletRequest request) {
        if (request.getParameter(field) == null || request.getParameter(field) == "") {
            request.setAttribute("null_" + field, "please input " + field);
            return false;
        }
        return true;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
