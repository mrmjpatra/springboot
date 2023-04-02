<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${!empty jsList}">
		<table border="1" align="center" bgcolor="cyan">
			<tr>
				<th>JsID</th>
				<th>JsName</th>
				<th>JsAddrs</th>
				<th>Resume</th>
				<th>Photo</th>
			</tr>
			<c:forEach var="info" items="${jsList}">
				<tr>
					<td>${info.jsId}</td>
					<td>${info.jsName}</td>
					<td>${info.jsAddrs}</td>
					<td><a href="download?jsId=${info.jsId}&type=resume">Download</a></td>
					<td><a href="download?jsId=${info.jsId}&type=photo">Download</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<h1 style="color: red">Record Not Found</h1>
	</c:otherwise>
</c:choose>

