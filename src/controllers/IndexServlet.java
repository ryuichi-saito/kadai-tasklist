package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.TaskList_DTO;
import utils.DBUtil_DAO;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath()); Tomcat動作確認済み

	    EntityManager em = DBUtil_DAO.createEntityManager();

	    List<TaskList_DTO> tasks = em.createNamedQuery("getAllTasks", TaskList_DTO.class)  //JPQL("getAllTasks")を引数に指定、DBへの問い合わせを実行
	                                 .getResultList();  //getResultList()でリスト形式で結果を取得する

	    response.getWriter().append(Integer.valueOf(tasks.size()).toString());  //データの登録件数を取得する

	    em.close();
	}

}
