<!DOCTYPE html>
<html>
	<head>
	  <%@ include file="/WEB-INF/views/common/header.jsp" %>
	</head>	
	<body>
		<div class="login-wrapper-new" style='background: linear-gradient(150deg, #97c74e 0%, #2cc185 100%) ! important;'>
		<img src="<%=request.getContextPath()%>/resources/images/ds_logo_header.png" height="80" width="250" alt="DataSwitch" style="position:absolute">
		<div class="h-100 d-flex align-items-center flex-column justify-content-center">
			<div class="border-0 border-secondary border-right">
				<h1 class="display-1 font-weight-bold">404</h1>
			</div>
			<div class="ml-3 text-center">
				<h2><strong>Sorry!</strong> We couldn't find this page.</h2>
				<a class="btn btn-light mt-2" href="<%=request.getContextPath()%>/home">Take me home!</a>
			</div>
		</div>
		</div>
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>