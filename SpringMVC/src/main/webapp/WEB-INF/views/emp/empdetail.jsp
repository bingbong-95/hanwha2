<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script></script>
	</head>
	<body>
		<h1>직원 상세보기</h1>
		<form action="empdetail" method="post">
		직원번호 : <input type="number" disabled="disabled" name="aa" value="${emp.employee_id }"><br>
		<input type="hidden" name="employee_id" value="${emp.employee_id }"><br>
		직원이름 : <input type="text" name="first_name" value="${emp.first_name }"><br>
		직원성 : <input type="text" name="last_name" value="${emp.last_name }"><br>
		이메일 : <input type="email" name="email" value="${emp.email }"><br>
		전화번호 : <input type="tel" name="phone_number"  pattern="\d{3}-\d{4}-\d{4}" value="${emp.phone_number }"><br>
		급여 : <input type="number" name="salary" value="${emp.salary }"><br>
		입사일 : <input type="date" name="hire_date" value="${emp.hire_date }"><br>
		매니저 : 
		<select name="manager_id">
		<c:forEach items="${managerlist }" var="m">
		<option ${emp.manager_id==m?"selected":""}>${m }</option>
		</c:forEach>
		</select> <br>
	
		부서번호 :
		<select name="department_id">
		<c:forEach items="${deptlist }" var="dept">
		<c:if test="${emp.department_id == dept.department_id }">
			<option selected value="${dept.department_id }">${dept.department_name }</option>
		</c:if>
		<c:if test="${emp.department_id != dept.department_id }">
			<option value="${dept.department_id }">${dept.department_name }</option>
		</c:if>
		</c:forEach>
		</select> <br>
		커미션 : <input type="text" name="commission_pct" value="${emp.commission_pct }"><br>
		직무 : 
		<select name="job_id">
		<c:forEach items="${joblist }" var="job">
		<option ${emp.job_id==job?"selected":""}>${job }</option>
		</c:forEach>
		</select> <br>
		<input type="submit" value="수정하기">
		</form>
	</body>
</html>