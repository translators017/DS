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
                <h2 class="text-center mb-30">Schema Migrator</h2>
                <div class="card-deck justify-content-center CardP-0">
                    <div class="card card3d max-w-250" onclick="onDataCatalogClick()">
                        <!--  <a class="card-body text-center" href="${pageContext.request.contextPath}/migrate/datacatalog"> -->
                        <a class="card-body text-center" href="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Data_Catalog.png" alt="Data Catalog">
                                    <h3>Data Catalog</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Define, design and maintain the metadata of source and target structure</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="card card3d max-w-250">
                        <a class="card-body text-center" href="<%=request.getContextPath()%>/home/migrate/schema-migrator/schema-designer">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Schema_Designer.png" alt="Schema Designer ">
                                    <h3>Schema Designer</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Truly unique, user-friendly capability. Simply drag and drop the schema for redesign and the platform converts it to a document-based JSON structure</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="card card3d max-w-250">
                        <a class="card-body text-center" href="#">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Data_Mapper.png" alt="Data Mapper">
                                    <h3>Data Mapper</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content">
                                    <p>Apply the business transformation logic using intuitive and business user friendly data mapping for automated code generation</p>
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