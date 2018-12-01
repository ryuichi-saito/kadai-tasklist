<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${task.title}" /> <%--送られて来た新規登録画面では、インスタンスフィールドがnullのオブジェクトのインスタンスフィールドが入っている--%>
<br /><br />                                             <%--つまり、何も入っていないので、何も表示されない。表示のないものが"title"として送られてくる。ただし、この時オブジェクトが送られてくるのではない。--%>
                                                         <%--nullという、インスタンスフィールドの"中身"だけが送られてくる --%>
                                                         <%--${task.title}はあくまで初期値、別のものに置き換わる。--%>
                                                         <%--ここではnullという値からフォームで入力された値が返される。決してtask.title="フォームで入力した値"となって送り返されるのではない --%>
                                                         <%--フォームで入力した値が単体で送られる、詳しくはSlack参照 --%>
<label for="content">タスク内容</label><br />
<input type="text" name="content" value="${task.content}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button> <%--submitした時点で、今度はフォームに入力した内容が"title"や"content"として上書きされて、CreateServletとして送られる --%>