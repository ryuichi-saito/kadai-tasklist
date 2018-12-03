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
        EntityManager em = DBUtil_DAO.createEntityManager();

        // ページネーション
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        List<TaskList_DTO> tasks = em.createNamedQuery("getAllTasks", TaskList_DTO.class)
                                    .setFirstResult(15 * (page - 1))
                                    .setMaxResults(15)
                                    .getResultList();

        long tasks_count = (long)em.createNamedQuery("getTasksCount", Long.class)
                                         .getSingleResult();

        em.close();

        request.setAttribute("tasks", tasks);
        request.setAttribute("tasks_count", tasks_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("sessionScope.flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("sessionScope.flush"));
            request.getSession().removeAttribute("sessionScope.flush");
        }


        // 以下の２行で、サーブレットからビューとなるJSPを呼び出す
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);
    }

}
