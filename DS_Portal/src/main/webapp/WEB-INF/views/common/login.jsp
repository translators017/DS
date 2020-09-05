<!DOCTYPE html>
<html>
	<head>
	  <%@ include file="/WEB-INF/views/common/header.jsp" %>
	</head>	
	<body style=overflow-x:hidden;>
		<div class="login-wrapper-new" style='background: linear-gradient(150deg, #97c74e 0%, #2cc185 100%) ! important;'>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">

				</div>
				<div class="col-md-6 text-right">
					<a class="navbar-brand text-light" href="#">

					</a>
				</div>
			</div>
		</div>
			<div class="text-center">
				<div style="margin:0 auto;padding-top:7vh">
				</div>
				<div class="main-head">
					<div>
						<img src="<%=request.getContextPath()%>/resources/images/ds_logo_header.png" height="100" width="300" alt="DataSwitch">
					</div>
				</div>
			</div>
			<div class="wrapper-overlay">
				<div class="container" style="margin-top: 40px">
					<div class="row">
						<div class="col-md-6 col-sm-12 col-xs-12 offset-md-3">
							<div class="login-container bg-transparent">
								<div class="row" style='background-color: #fff;
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,0.05);
    box-shadow: 0 1px 1px rgba(0,0,0,0.05);'>
									<div class="col-md-12 col-sm-12 col-xs-12">
										<div class="login-form">
											
											<p class="text-center text-light loginAcc text-light-new">Login to your Accounts</p>
											<form id="loginform" class="pt-3" action="<%=request.getContextPath()%>/login" method="post" onsubmit=" return validateForm()">
												<input type="hidden" id="URLvalue" name="URLvalue">
											  <div class="form-group rel">
											    <input type="text" class="form-control bg-transparent text-light text-light-new" style='padding-left: 35px;' name="username" id="username" placeholder="Enter Email">
											    <span class="input-fa"><i class="fa fa-user text-light text-light-new"></i></span>
											  </div>
											  <div class="form-group rel">
											    <input type="password" class="form-control bg-transparent text-light text-light-new" style='padding-left: 35px;' name="password" id="password" placeholder="Enter Password">
											    <span class="input-fa"><i class="fa fa-key text-light text-light-new"></i></span>
											    <span toggle="#password" id="toggle-password" class="fa fa-fw fa-eye field-icon toggle-password text-light text-light-new"></span>
											  </div>
											  <div class="custom-control custom-checkbox">
												  <input type="checkbox" class="custom-control-input" id="remember">
												  <label class="custom-control-label text-light text-light-new" for="remember">Remember me</label>
											  </div>
											  <div class="text-center pt-4">
											  	<button class="btn btn-custom btn-block" id="loginBtn" type="submit" style='background: #4cbd89 ! important'>Login</button>
											  </div>
											  <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
											</form>
											<c:if test="${not empty error}">
												<div class="error text-danger">${error}</div>
											</c:if>
											<c:if test="${not empty msg}">
												<div class="msg text-info">${msg}</div>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>
		<script src="<%=request.getContextPath()%>/resources/js/common.js"></script>
		
</html>