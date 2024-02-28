<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook5/guest/write" method="get">
		<table border="1" width="540px">
			<tr>
				<td>이름</td><td><input type="text" name="name"></td>
				<td>비밀번호</td><td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">등록</button></td>

			</tr>
		</table>
	</form>
	<br>


<c:forEach items ="${requestScope.mList}" var="memberVo">
	<table border="1" width="540px">
		<tr>
			<td>${memberVo.no}</td>
			<td>${memberVo.name}</td>
			<td>${memberVo.reg_date}</td>
			<td><a href="/guestbook5/guest/deleteForm?no=${memberVo.no}">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4">${memberVo.content}</td>
			
		</tr>
	</table>
	<br>
</c:forEach>	

	
</body>
</html>