<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook5/guest/delete" method="get">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
				<td><button type="submit">삭제</button></td>
				
				<input type="text" name="no" value="${param.no}">
			</tr>
		</table>
	</form>
	
	<br><br>
	<a href="/guestbook5/guest/addList">메인으로 돌아가기</a>

</body>
</html>