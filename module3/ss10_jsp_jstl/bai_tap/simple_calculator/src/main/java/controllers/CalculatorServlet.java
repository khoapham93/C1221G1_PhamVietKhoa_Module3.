package controllers;

import models.Calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstNumberInput = request.getParameter("firstNumber");
        String secondNumberInput = request.getParameter("secondNumber");
        char operator = request.getParameter("operator").charAt(0);
        if (!"".equals(firstNumberInput) && !"".equals(secondNumberInput)) {
            try {
                double firstNumber = Double.parseDouble(firstNumberInput);
                double secondNumber = Double.parseDouble(secondNumberInput);
                double result = Calculator.calculate(firstNumber, secondNumber, operator);
                request.setAttribute("result", result);
                request.getRequestDispatcher("/").forward(request, response);
            } catch (Exception e) {
                String message = e.getMessage();
                request.setAttribute("result", message);
                request.getRequestDispatcher("/").forward(request, response);
            }
        } else {
            String message = "The Operand is required";
            request.setAttribute("result", message);
            request.getRequestDispatcher("/").forward(request, response);
        }

    }
}
