<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="title">タイトル</label><br />
<input type="text" name="title" value="${task.title}" /> <%--送られて来た新規登録画面では、インスタンスフィールドがnullのオブジェクトのインスタンスフィールドが入っている--%>
<br /><br />                                             <%--つまり、何も入っていないので、何も表示されない。表示のないものが"title"として送られてくる。--%>
                                                         <%--${task.title}はあくまで初期値、上書きされる --%>
<label for="content">タスク内容</label><br />
<input type="text" name="content" value="${task.content}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button> <%--submitした時点で、今度はフォームに入力した内容が"title"や"content"として上書きされて、CreateServletとして送られる --%>