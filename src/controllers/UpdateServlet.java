package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.TaskList_DTO;
import models.validators.TaskValidator;
import utils.DBUtil_DAO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token"); //セキュリティ対策
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil_DAO.createEntityManager(); //EntityManagerのオブジェクト生成

            TaskList_DTO t = em.find(TaskList_DTO.class, (Integer)(request.getSession().getAttribute("task_id")));
            //EditServletでセッションスコープにtask_idの名前で保存したデータを使う
            /*request.getParameter()でtask_idの値を受け取り、Integer型に変換する
             * それをfindメソッドの引数にし、変更するタスクを特定する*/

            String title = request.getParameter("title"); //変更するタスクにインスタンスフィールドを設定し直す=更新処理
            t.setTitle(title);

            String content = request.getParameter("content");
            t.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setUpdated_at(currentTime);

            /*em.getTransaction().begin();
            em.getTransaction().commit();
            request.getSession().setAttribute("sessionScope.flush", "更新が完了しました");  //更新完了の文言をflushという名前でセッションスコープに格納
            em.close();

            request.getSession().removeAttribute("sessionScope.task_id"); //セッションスコープに保存されたtask_idを消去する

            response.sendRedirect(request.getContextPath() + "/index"); //index.jspへリダイレクト*/

            List<String> errors = TaskValidator.validate(t);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                request.getSession().setAttribute("sessionScope.flush", "更新が完了しました。");
                em.close();

                request.getSession().removeAttribute("sessionScope.task_id");

                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }

}
