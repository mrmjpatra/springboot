<%@ page  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  

<h1 style="color:red;text-align:center">Student Registration</h1>
<form:form method="POST" modelAttribute="stud">

	<table align="center" bgcolor="cyan">
		<tr>
			<td>Student Number</td>
			<td><form:input path="sno"/> </td>
		</tr>
		<tr>
			<td>Student Name</td>
			<td><form:input path="sname"/> </td>
		</tr>
		<tr>
			<td>Student Address</td>
			<td><form:input path="sadd"/> </td>
		</tr>
			<tr>
			<td>Student Average</td>
			<td><form:input path="avg"/> </td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Register"></td>
		</tr>
	</table>
</form:form>

