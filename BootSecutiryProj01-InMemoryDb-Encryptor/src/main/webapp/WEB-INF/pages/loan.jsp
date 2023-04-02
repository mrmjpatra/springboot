<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>
<h1 style="color: red; text-align: center">Loan Approved Page</h1>
<b>Your approved loan amount : <%=new Random().nextInt(100000)%></b>
<a href="./">Home</a>
<a href="balance">Show Balance</a>
<a href="offers">Show Offers</a>
<a href="logout">Log Out</a>