<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>id : ${task.id} のタスク詳細ページ</h2>

        <p>タイトル：<c:out value="${task.title}" /></p> <%--ShowServletでTaskList_DTO型のオブジェクト t に入ったタスクの詳細を、taskという名前でスコープに配置した --%>
                                                       <%--今度はそのtaskをスコープから取り出し、フィールドのtitleを初期値として出力した --%>
        <p>タスク内容：<c:out value="${task.content}" /></p>
        <p>作成日時：<fmt:formatDate value="${task.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
        <p>更新日時：<fmt:formatDate value="${task.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></p>

        <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>
        <p><a href="${pageContext.request.contextPath}/edit?id=${task.id}">このタスクを編集する</a></p>

    </c:param>
</c:import>