<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${task != null}">
                <h2>id : ${task.id} のタスク編集ページ</h2>

                <form method="POST" action="${pageContext.request.contextPath}/update">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>

                <%--以下、削除機能に関わるコード--%>
                <p><a href="#" onclick="confirmDestroy();">このタスクを削除する</a></p>  <%--クリック時にconfirmDestroyを呼び出す --%>
                <form method="POST" action="${pageContext.request.contextPath}/destroy">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>

                <%--JavaScriptの文、関数の定義--%>
                <%--送信するフォームはform配列のインデックスで指定する --%>
                <script>
                    function confirmDestroy() {
                        if(confirm("本当に削除してよろしいですか？")) {
                            document.forms[1].submit();
                        }
                    }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>