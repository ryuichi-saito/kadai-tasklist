package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.TaskList_DTO;
import utils.DBUtil_DAO;
/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil_DAO.createEntityManager(); //EntityManagerのオブジェクト生成

            TaskList_DTO t = em.find(TaskList_DTO.class, (Integer)(request.getSession().getAttribute("sessionScope.task_id")));


            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            request.getSession().setAttribute("sessionScope.flush", "削除が完了しました");
            em.close();

            request.getSession().removeAttribute("sessionScope.task_id");

            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
