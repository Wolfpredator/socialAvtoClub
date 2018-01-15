package com.brain.crud.socialclub.controller;


import com.brain.crud.socialclub.service.OnlineUsers;
import com.brain.crud.socialclub.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AuthServlet", value = {"/registration"})
public class AuthServlet extends HttpServlet {
    RequestDispatcher requestDispatcher;
    Boolean allDateCorrect;
    UserService userService;

    String nickname, name, password, surname;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService = new UserService();
        requestDispatcher = request.getRequestDispatcher("/registration.jsp");
        if (request.getMethod().equals("POST")) registerUser(request, response); //todo test this for doGet()
        requestDispatcher.forward(request, response);
    }


    private boolean isFieldMissing(String field, HttpServletRequest request) {
        if (request.getParameter(field) == null || request.getParameter(field) == "") {
            request.setAttribute("null_" + field, "please input " + field);
            return false;
        }

        return true;
    }

    private void sendToPersist(HttpServletRequest request) {

        if (surname == "") {
            userService.createUser(nickname, name, password);
        } else {
            userService.createUser(nickname, name, password, surname);
        }
        OnlineUsers.getInstance().setUserOnline(request.getSession().getId());
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        allDateCorrect = isFieldMissing("nickname", request);
        allDateCorrect = isFieldMissing("name", request) && allDateCorrect;
        allDateCorrect = isFieldMissing("password", request) && allDateCorrect;

        nickname = request.getParameter("nickname");
        name = request.getParameter("name");
        password = request.getParameter("password");
        surname = request.getParameter("surname");

        request.setAttribute("nickname", nickname);
        request.setAttribute("name", name);
        request.setAttribute("surname", surname);

        if (allDateCorrect == true) {
            sendToPersist(request);
            requestDispatcher = request.getRequestDispatcher("/profile");
            requestDispatcher.forward(request, response);
        }
        request.setAttribute("password", password);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
