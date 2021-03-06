<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>タスク管理アプリケーション</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1><a href="${pageContext.request.contextPath}/index">タスク管理アプリケーション</a></h1>
            </div>
            <div id="content">
                ${param.content} <%--ここに、各ページのビューの内容を記入する--%>
            </div>
            <div id="footer">
                by Ryuichi Saito.
            </div>
        </div>
    </body>
</html>