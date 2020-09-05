<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">   
</head>
<body>
    <header>
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
    </header>
    <section class="vwh-100 min-h-300 max-h-600 d-flex align-items-center justify-content-center">
        <div class="container">
            <!-- Below is the start of the main section -->
            <div class="items-box border">
                <h2 class="text-center mb-30">Process Migrator</h2>
                <div class="card-deck justify-content-center CardP-0">
                    <div class="card card3d max-w-250" onclick="onDataCatalogClick()">
                        <!--  <a class="card-body text-center" href="${pageContext.request.contextPath}/migrate/datacatalog"> -->
                        <a class="card-body text-center" href="<%=request.getContextPath()%>/home/migrate/process-migrator/process-converter">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Process_Converter.png" alt="Data Catalog">
                                    <h3>Process Converter</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Fully Automated Legacy scripts & Tools conversion  to Modern technologies</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="card card3d max-w-250">
                        <a class="card-body text-center" href="#">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Process_Remediator.png" alt="Schema Designer ">
                                    <h3>Process Remediator</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Refactor of data process to modern data process on existing applications</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    
                    <div class="card card3d max-w-250">
                        <a class="card-body text-center" href="#">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Data_Mapper.png" alt="Data Mapper">
                                    <h3>Process Validator</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Validation of migration accuracy of data and process conversion</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        
       
    </section>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    
</body>

</html>