package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.TaskList_DTO;
import utils.DBUtil_DAO;
/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil_DAO.createEntityManager(); //Entityマネージャーのオブジェクトを生成

            TaskList_DTO t = new TaskList_DTO();

            String title = request.getParameter("title"); //ブラウザから送信された情報（title）をサーブレットで受け取るrequest.getParameter()メソッド
            t.setTitle(title);                            //このメソッドで取得した値は全てString型のデータとなる

            String content = request.getParameter("content");
            t.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

            em.getTransaction().begin();
            em.persist(t); //必要な情報をセットしたTaskListクラスのオブジェクトをpersistメソッドを使ってDBにセーブする
            em.getTransaction().commit(); //コミットを忘れずに
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");
        }
	}

}
