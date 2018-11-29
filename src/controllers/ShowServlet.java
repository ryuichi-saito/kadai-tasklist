package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.TaskList_DTO;
import utils.DBUtil_DAO;
/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil_DAO.createEntityManager(); //ENtityManagerのオブジェクトを生成

		TaskList_DTO t = em.find(TaskList_DTO.class, Integer.parseInt(request.getParameter("id")));
		/*request.getParameter()でidの値を受け取り、Integer型に変換する
		 * それをfindメソッドの引数にし、タスクの詳細を取得する*/
		em.close();

		request.setAttribute("task", t); //TaskList_DTO型のオブジェクト t に入ったタスクの詳細を、taskという名前でshow.jsp内で扱えるようにリクエストスコープにセットする

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/show.jsp"); //viewとなるJSPファイルを指定
		rd.forward(request,  response); //指定したJSPファイルを呼び出す
	}

}
