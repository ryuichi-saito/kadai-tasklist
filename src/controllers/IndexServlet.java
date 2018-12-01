package controllers;

import java.io.IOException;
import java.util.List;

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

        EntityManager em = DBUtil_DAO.createEntityManager(); //EntityManagerのオブジェクト生成

        // ページネーション
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        List<TaskList_DTO> tasks = em.createNamedQuery("getAllTasks", TaskList_DTO.class) //JPQL("getAllTasks")を引数に指定、DBへの問い合わせを実行
                                    .setFirstResult(15 * (page - 1))
                                    .setMaxResults(15)
                                    .getResultList(); //getResultList()でリスト形式で結果を取得する

        long tasks_count = (long)em.createNamedQuery("getTasksCount", Long.class)
                                         .getSingleResult();

        em.close();

        request.setAttribute("tasks", tasks);  // List<TaskList_DTO> tasksに入ったタスクの内容を、同じtasksというリスト名でJSP内で扱えるようにリクエストスコープに格納
        request.setAttribute("tasks_count", tasks_count);  // tasks_countをリクエストスコープに格納*/
        request.setAttribute("page", page); // pageをリクエストスコープへ

        /*List<TaskList_DTO> tasks = em.createNamedQuery("getAllTasks", TaskList_DTO.class)  //JPQL("getAllTasks")を引数に指定、DBへの問い合わせを実行
                                     .getResultList();  //getResultList()でリスト形式で結果を取得する

        //response.getWriter().append(Integer.valueOf(tasks.size()).toString());  //データの登録件数を取得する、最初だけ確認、その後コメントアウト

        em.close();

        request.setAttribute("tasks", tasks);
        // List<TaskList_DTO> tasksに入ったタスクの内容を、同じtasksというリスト名でJSP内で扱えるようにリクエストスコープに格納*/

        if(request.getSession().getAttribute("sessionScope.flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("sessionScope.flush"));
            request.getSession().removeAttribute("sessionScope.flush");
        } //セッションスコープからリクエストスコープへフラッシュメッセージ文言を移し替えた後、セッションスコープから削除する


        // 以下の２行で、サーブレットからビューとなるJSPを呼び出す
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp"); //getRequestDispatcherメソッドの引数にJSPファイルを指定する
        rd.forward(request, response); //forwardメソッドでレスポンスの画面としてJSPファイルを呼び出す
    }

}
