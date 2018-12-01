<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSTLのうちのコア機能をcという名前で利用できるように設定する --%>

<c:import url="../layout/app.jsp"> <%-- '<c:import>'で、url属性に指定したファイルをこの位置から読み込める --%>
    <c:param name="content"> <%-- '<c:param name ="content">'で囲まれた記述内容が、app.jspの${param.content} のところに当てはまる --%>

        <c:if test="${flush != null}"> <%--リクエストスコープ内にflushが存在する場合、それを取り出して表示する --%>
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>タスク一覧</h2>
        <ul>
            <c:forEach var="task" items="${tasks}"> <%--IndexServletでリクエストスコープに格納したList<TaskList_DTO> tasksを１つずつtask変数に代入する --%>
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${task.id}"> <%--詳細画面へのリンク--%>
                        <c:out value="${task.id}" />
                    </a>
                    ：<c:out value="${task.title}"></c:out> &gt; <c:out value="${task.content}" />
                </li>
            </c:forEach>
        </ul>

        <p><a href="${pageContext.request.contextPath}/new">新規タスクの登録</a></p>
    </c:param>
</c:import>