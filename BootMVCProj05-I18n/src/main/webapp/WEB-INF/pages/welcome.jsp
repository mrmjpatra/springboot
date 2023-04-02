<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="sp"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>




<h1 style="color: red; text-align: center">
	<sp:message code="home.title" />
</h1>
<br>

<h1 style="color:red;text-align:center">Current Active Locale :: ${pageContext.response.locale}</h1>
<fmt:setLocale value="${pageContext.response.locale}"/>

<jsp:useBean id="dt" class="java.util.Date"/>

<fmt:formatDate var="fdt" value="${dt}" type="date" dateStyle="FULL"/>
<b>Formatted Time :: ${fdt}</b>
<br>
<fmt:formatNumber var="fNumber" value="100000000" type="number"/>
<b>Formatted Number :: ${fNumber}</b>
<br>
<fmt:formatNumber var="fcurrency" value="100000000" type="currency"/>
<b>Formatted Currency ${fcurrency}</b>
<br>
<fmt:formatNumber var="fpercent" value="0.21" type="PERCENT"/>
<b>Formatted Currency ${fpercent}</b>











<br>
<a href="register">
	<h2 style="color: red; text-align: center">
		<sp:message code="home.link" />
	</h2>
</a>

<br>
<br>
<p align="center">
	<a href="?lang=fr_FR">French</a>&nbsp;&nbsp;&nbsp;
	<a href="?lang=de_DE">Germani</a>&nbsp;&nbsp;&nbsp;
	<a href="?lang=hi_IN">Hindi</a>&nbsp;&nbsp;&nbsp;
	<a href="?lang=en_US">English</a>&nbsp;&nbsp;&nbsp;
</p>




