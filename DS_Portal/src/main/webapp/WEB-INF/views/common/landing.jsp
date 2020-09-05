<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">   

    <style>
         .btn-outline-custom1 {
             background-color: #fff;
             color: #2cc185;
         }
         .no-cursor {
            /*  touch-action: none!important; */
			cursor: not-allowed!important; 
			/* pointer-events:none!important; */
				
		 }
		 .no-cursor:hover {
		    opacity: 0.5;
		    filter: alpha(opacity=50);
		 }
         .btn-outline-custom1:hover {
             background-color: #fff;
             color: #fff;
             border-color: #2cc185;
             border-radius: 0;
         }
         .new-font{
         font-family: 'Roboto Slab', serif;
         letter-spacing:1px;
         color: #6c757d;
         }
         #main-heading2{
         font-family: 'Roboto Slab', serif;
         }
         .secondary-heading-line{
         	border-bottom:4px solid #17a2b8;
         	margin-top:12px;
         	width:5%;
         	margin-bottom:25px;
         }
         .pos{
         	position:absolute;
         	top:170px;
         	left:0;
         	right:0;
         	margin:0 auto;
         	width:85%;
         }         

         h6.card-title{
         	font-family: 'Roboto Slab', serif;
         	font-weight:500;
         	text-align:center;
         	margin-top:20px;
         }
		 .card-1 {
		  	box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
		   	transition: all 0.3s cubic-bezier(.25,.8,.25,1);
		 }
         .p-space{line-height:1.8}
         .se-pre-con {
			position: fixed;
			left: 0px;
			top: 0px;
			width: 100%;
			height: 100%;
			z-index: 9999;
			background: url(./resources/images/Preloader_3.gif) center no-repeat #fff;
		}
		.bg-medium{background-color:#E1E6E9}
		.borderCol{height:100%;}
		
		
	    .div-face2-content {
			overflow-y: auto;
		    font-size: 12px;
		    margin-top: 30px;
		    margin-bottom: 30px;
		    margin-left: 5px;
		    margin-right: 5px;
		    position: fixed;
		    max-height: 120px;
	    }
	    .p-face2-para {
	     	margin-top: 10px ! important; 
	     	margin-bottom: 10px ! important;
	     }
     </style>
</head>

<body class="pageid-01">
	
    <header>
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
    </header>

    <section class="vwh-100 min-h-300 d-flex align-items-center justify-content-center">
        <div class="container">
            <!-- Below is the start of the main section -->
            <div class="items-box border">
                <h2 class="text-center mb-30">Data Switch - The Digital Data Transformation Platform</h2>
                <div class="card-deck justify-content-center CardP-0">
	
                    <div class="card card3d max-w-250" onclick="onMigrateClick()">
                        <a class="card-body text-center" href="<%=request.getContextPath()%>/home/migrate">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Migrate.png" alt="DS:Migrate">
                                    <h3>Migrate</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content div-face2-content">
                                    <p class="p-face2-para">Data Modernization toolkit leveraging extreme automation to migrate schema, data and process from legacy database to modern database services</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="card card3d max-w-250">
                        <a class="card-body text-center" href="#">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Integrate.png" alt="DS:Integrate">
                                    <h3>Integrate</h3>
                                </div>
                            </div>
                            <div class="face face2">
                                <div class="content div-face2-content">
                                    <p class="p-face2-para">Self Serviceable, Business User Friendly, Metadata Based, AI/ML Driven Data Aggregation, Consolidation And Integration Tool For Domain Specific Data Applications (PIM, Supply Chain, Data Aggregators)</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="card card3d max-w-250">
                        <a class="card-body text-center" href="#">
                            <div class="face face1">
                                <div class="content">
                                    <img class="rounded-circle" src="<%=request.getContextPath()%>/resources/images/v1_02/Democratize.png" alt="DS:Democratize">
                                    <h3>Democratize</h3>
                                </div>
                            </div>
                            <div class="face face2" style="font-size: 12px;">
                                <div class="content div-face2-content">
                                    <p class="p-face2-para">Intuitive, No Code, Self Serviceable, Conversational AI Driven 'Data as a Service' Tool For Various Data And Analytics Consumption</p>
                                </div>
                            </div>
                        </a>
                    </div>

                </div>
            </div>
        </div>

        <form action="<%=request.getContextPath()%>/migrate" method="post" id="launchMigrate">
			<input type="hidden" id="grpNmForMigrateView" name="groupName">
		</form>
    </section>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>