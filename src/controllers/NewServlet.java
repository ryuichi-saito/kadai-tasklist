package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.TaskList_DTO;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId()); //セキュリティ対策
        request.setAttribute("task", new TaskList_DTO()); /*TaskList_DTOクラスのオブジェクトを作って、それをtaskという名前でリクエストスコープへ
                                                            何も入っていないことを示すために、空のオブジェクトを（new.jspに）渡す*/

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp"); //呼び出すJSPファイルを指定
        rd.forward(request, response); //forward()メソッドで指定したJSPファイルを呼ぶ
    }

}
