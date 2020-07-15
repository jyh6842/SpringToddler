<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="registMemberURI" value="/06/memberForm.jsp"></c:url>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css" type="text/css">
<title>회원관리 관리자 로그인</title>
<script type='text/javascript' src = 'http://code.jquery.com/jquery-latest.js'></script>
<script type='text/javascript' src = '${pageContext.request.contextPath }/js/common/validation.js'></script>
<script type='text/javascript' src = '${pageContext.request.contextPath }/js/common/cookieControl.js'></script>
<script type="text/javascript">
	window.onload = function(){
		
			if(${!empty param.message}){
				alert('${param.message}');
			}
			
			if(Get_Cookie("mem_id")){
				$('input[name=saveID]').attr('checked', true);
				$('input[name=mem_id]').val(Get_Cookie("mem_id"));
// 				Delete_Cookie("mem_id", '/');
			}
		document.querySelector(".loginBtn").addEventListener('click', function(){
			var mem_id = $('input[name=mem_id]').val();
			if(!mem_id.validationID()){
				alert('아이디를 바르게 입력해주세요.');
				return false;
				}
			var mem_pass = $('input[name=mem_pass]').val();
			if(!mem_pass.validationPWD()){
				alert('패스워드를 바르게 입력해주세요.');
				return false;
				}
			if($('input[name=saveID]').is(':checked')){
				Set_Cookie("mem_id", $('input[name=mem_id]').val(), 1, "/");	
				
			}else{
				Delete_Cookie('mem_id', '/');
			}
			
			
			$('form').submit();
			
			
			
		});
		
				
	}
</script>
</head>
<body>
<form action="loginCheck.jsp" method="post">
	<table width="770" border="0" align="center" cellpadding="0"
		cellspacing="0" style="margin: 90px;">
		<tr>
			<td height="150" align="center"><img src="${pageContext.request.contextPath }/image/p_login.gif" /></td>
		</tr>
		<tr>
			<td height="174"
				style="background: url(${pageContext.request.contextPath }/image/login_bg.jpg); border: 1px solid #e3e3e3;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="260" height="110" align="center"
							style="border-right: 1px dotted #736357;">
							<img src="${pageContext.request.contextPath }/image/logo.jpg" />
						</td>
						<td>
							<table border="0" align="center" cellpadding="5"
								cellspacing="0">
								<tr>
									<td><b>아이디</b></td>
									<td><input type="text" name="mem_id" class="box" tabindex="3" height="18" /></td>
									<td rowspan="2">
										<img src="${pageContext.request.contextPath }/image/login.gif" class="loginBtn"/>
									</td>
								</tr>
								<tr>
									<td><b>패스워드</b></td>
									<td><input type="password" name="mem_pass" class="box" tabindex="3" height="18" /></td>
								</tr>
								<tr>
									<td colspan="2">
										아이디 저장 : <input type="checkbox" name="saveID">
								<a href = '${registMemberURI }'>회원가입을 원하세요?</a>
									</td>
								</tr>
							</table>
							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>
