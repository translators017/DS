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
                <h2 class="text-center mb-30">Migrate</h2>
                <div class="card-deck justify-content-center CardP-0">
                    <div class="card card3d max-w-250" onclick="onDataCatalogClick()">
                        <!--  <a class="card-body text-center" href="${pageContext.request.contextPath}/migrate/datacatalog"> -->
                        <a class="card-body text-center" href="<%=request.getContextPath()%>/home/migrate/schema-migrator">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Schema.png" alt="Data Catalog">
                                    <h3>Schema</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Truly unique, user-friendly capability. Simply drag and drop the schema for redesign</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="card card3d max-w-250">
                        <a class="card-body text-center" href="<%=request.getContextPath()%>/home/migrate/data-migrator">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Data.png" alt="Schema Designer ">
                                    <h3>Data</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Fully automated and scalable Migration, Modernization and Transformation of data</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="card card3d max-w-250">
                        <a class="card-body text-center" href="<%=request.getContextPath()%>/home/migrate/process-migrator">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Process.png" alt="Data Mapper">
                                    <h3>Process</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Fully Automated conversion of Enterprise data process</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        
        <form id="entity-mapping-form" name="entity-mapping-form" action="<%=request.getContextPath()%>/RDW/Intelligent-Mapper/system-mapping" method="get"></form>
                        
        <form action="<%=request.getContextPath()%>/migrate/datacatalog" method="post" id="launchDataCatalog">
			<input type="hidden" id="grpNmForDataCatalogView" name="groupName">
		</form>
    </section>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    
</body>

</html>