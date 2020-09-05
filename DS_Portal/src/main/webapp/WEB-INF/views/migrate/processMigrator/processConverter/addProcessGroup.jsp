<!doctype html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
	<style>
		
	    #viz {
	            width: 1200px;
	            height: 700px;
	            border: 0px solid lightgray;
	            font: 12pt arial;
	    }
	    .dropdown-menu{/*height:75vh;*/overflow:auto;}
		.scroll-help{
		    position: absolute;
	    	right: 10px;
	    	bottom: 50px;
		}
		.visualization-wrapper{
			position:absolute;
			top: 50%;
    		transform: translateY(-50%);
	     	left:0;
	     	right:0;
	     	width:80%;
	     	height:80%;
	     	display:none;
	     	background-color:#fff;
	     	margin:0 auto;
	     	box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
		}
		.close-visualization{
			position:absolute;
			top:5px;
			right:5px;
		}
		#legend{
		position: absolute;
    top: 20px;
    height: 200px;
    width: 200px;
    }
	</style>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/filepond.min.css">
</head>
<body id="main" class="add-proc-view">
	<input type="hidden" id="dbListArray" value='${dbListArray}'/>
	<input type="hidden" name="catalogArray" id="catalogArray" value="${catalogArray}" />
	<div>
		<header>
	        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
	    </header>
	</div>
	
	<section class="pb-3 bg-white overflow-sec">
        <div class="container-fluid p-0">
        	<nav aria-label="breadcrumb bg-white" class="pt-3">
                <ol class="breadcrumb ml-4">
                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home">Home</a></li>
                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate">Migrate</a></li>
                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate/process-migrator">Process Migrator</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Process Converter</li>
                </ol>
            </nav>
        	<div class="items-box border">
        		<div class="container-fluid">
        			<div class="validation-message">
						
					</div>
					<section>
				<div class="tabs mt-2 tabs-style-linemove two-tab rounded" id="process-mig-tab" style="box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);">
					
					<nav>
						<ul>
							<li class="tab-current"><a href="#section-processConv-1" class="icon icon-home"><span><span class="step">1</span>&ensp;Select Current Technology Code</span></a></li>
							<li><a href="#section-processConv-2" class="icon icon-home"><span><span class="step">2</span>&ensp;Select Target Technology Code</span></a></li>
							<!-- <li><a href="#section-processConv-3" class="icon icon-home"><span><span class="step">3</span>&ensp;Upload Source Code</span></a></li>
							<li><a href="#section-processConv-4" class="icon icon-home"><span><span class="step">4</span>&ensp;Generated Output</span></a></li> -->
						</ul>
					</nav>
					<div class="content-wrap bg-white pt-4 pb-4">
							<section id="section-processConv-1" class="content-current bg-white border" style="border-color:#ecf0f1">
								<div class="row m-0 text-left ml-3 source-sel">
									<div class="custom-control custom-control-inline custom-radio">
									   <input type="radio" class="custom-control-input" name="radio-1" id="radio-db-1" value="DB" checked>
									   <label class="custom-control-label fnt-15 text-dark" for="radio-db-1">DB</label>
									</div>
									<div class="custom-control custom-control-inline custom-radio">
									   <input type="radio" class="custom-control-input" name="radio-1" id="radio-etl-1" value="ETL">
									   <label class="custom-control-label fnt-15 text-dark" for="radio-etl-1">ETL</label>
									</div>
									<div class="custom-control custom-control-inline custom-radio">
									   <input type="radio" class="custom-control-input" name="radio-1" id="radio-others-1" value="Others" disabled>
									   <label class="custom-control-label fnt-15 text-dark" for="radio-others-1">Others</label>
									</div>
								</div>
								<div class="row m-0 text-left mt-3 data-expose-source" data-expose="['DB']">
									<div class="col-6">
										<div class="form-group">
											<label class="font-weight-bold">Source DB<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<select class="form-control" id="sourceDb" name="sourceDb" required>
												<option value="Teradata">Teradata</option>
												<option value="Oracle">Oracle</option>
												<option value="Netezza">Netezza</option>
												<option value="Netezza">MySQL</option>
												<option value="Vertica">Vertica</option>
												<option value="SQL Server">SQL Server</option>
												<option value="BigQuery">BigQuery</option>
											</select>
											<div class="invalid-feedback">Please select a db!</div>
										</div>
									</div>
									<div class="col-6">
										<div class="form-group">
											<label class="font-weight-bold">Script Type<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<select class="form-control" id="sourceScriptType" name="sourceScriptType" required>
												<option value="BTEQ">BTEQ</option>
												<option value="TPMP">TPMP</option>
												<option value="(k)sh">(k)sh</option>
												<option value="Macro">Macro</option>
												<option value="PLSQL Proc">Stored Procedure / PLSQL</option>
												<option value="DDL(Table/Views)">DDL(Table/Views)</option>
											</select>
											<div class="invalid-feedback">Please enter a script type!</div>
										</div>
									</div>
								</div>
								<div class="data-expose-source" data-expose="['DB']">
									<div class="row m-0 text-left">
										<p class="pl-3 fnt-15 text-dark text-left mt-2">Select Current Catalog <small>(Mandatory if target technology is ETL)</small></p>
										<div class="input-group mb-1 ml-3">
										  <div class="input-group-prepend bg-white">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-search"></i></span>
										  </div>
										  <input type="text" class="form-control" name="search-current-catalog" placeholder="Search catalogs" aria-label="Search for catalogs" aria-describedby="basic-addon1">
										</div>
									</div>
									<div class="row m-0 text-left" id="catalogs">
										<c:forEach items="${catalogNames}" var="name" varStatus="loop">
											<div class="col-3">
												<div class="custom-control custom-checkbox">
												   <input type="checkbox" class="custom-control-input" id="${name}">
												   <label class="custom-control-label fnt-15 text-dark" for="${name}">${name}</label>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<div class="row m-0 mt-3 text-left data-expose-source none" data-expose="['ETL']">
									<div class="col-6">
										<div class="form-group">
											<label class="font-weight-bold">Source ETL<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<select class="form-control" id="sourceETL" name="sourceETL" required>
												<option value="Apache Spark (Python)">Apache Spark (Python)</option>
												<option value="Apache Spark (Scala)">Apache Spark (Scala)</option>
												<option value="AWS Glue (Python)">Apache Spark (Scala)</option>
												<option value="AWS Glue (Scala)">AWS Glue (Scala)</option>
												<option value="Azure Data Flow">Azure Data Flow</option>
												<option value="Azure Databricks">Azure Databricks</option>
												<option value="GCP Dataflow (Beam Java)">GCP Dataflow (Beam Java)</option>
												<option value="GCP Dataflow (Beam Python)">GCP Dataflow (Beam Python)</option>
												<option value="GCP Dataproc">GCP Dataproc</option>
												<option value="Python (Pandas)">Python (Pandas)</option>
												<option value="ELT Stored Procs">ELT Stored Procs</option>
												<option value="Talend">Talend</option>
												<option value="Informatica PC (9.6 +)">Informatica PC (9.6 +)</option>
												<option value="Informatica BDM (10.2.0 +)">Informatica BDM (10.2.0 +)</option>
												<option value="Informatica IICS">Informatica IICS</option>
												<option value="Datastage">Datastage</option>
												<option value="SSIS">SSIS</option>
											</select>
										</div>
									</div>
									<div class="col-6">
										<p class="fnt-15 text-left text-dark">Is underlying DB also changing?</p>
										<div class="custom-control custom-control-inline custom-radio">
										   <input type="radio" class="custom-control-input" name="radio-ETLDB" value="Yes" id="etlDB-true">
										   <label class="custom-control-label fnt-15 text-dark" for="etlDB-true">Yes</label>
										</div>
										<div class="custom-control custom-control-inline custom-radio">
										   <input type="radio" class="custom-control-input" name="radio-ETLDB" value="No" id="etlDB-false" checked>
										   <label class="custom-control-label fnt-15 text-dark" for="etlDB-false">No</label>
										</div>
									</div>
								</div>
								<div class="data-expose-source none"  data-expose="['ETL']">
									<div class="new-conn-div none">
									<p class="fnt-15 font-weight-bold text-left text-dark pl-3">Please add existing connection names</p>
										<div class="row m-0 mt-2 text-left">
											<div class="form-group row m-0">
											    <div class="col-5">
											      <input type="text" class="form-control" id="connName" placeholder="Connection Name">
											    </div>
											    <div class="col-5">
											      <select class="form-control" id="dbType">
											      	<option value="Teradata">Teradata</option>
													<option value="Oracle">Oracle</option>
													<option value="Netezza">Netezza</option>
													<option value="Vertica">Vertica</option>
													<option value="SQL Server">SQL Server</option>
													<option value="BigQuery">BigQuery</option>
											      </select>
											    </div>
											    <div class="col-2">
											    	<button class="btn btn-green add-conn">Add</button>
											    </div>
											</div>
										</div>
										<div class="row m-0 mt-3 fnt13 badge-conn">
											
										</div>
									</div>
								</div>
								<div class="text-right">
									<button class="btn btn-green next-btn" data-href="#section-processConv-2">Next</button>
								</div>
							</section>
							<section id="section-processConv-2" class="bg-white border"  style="border-color:#ecf0f1">
								<div class="row m-0 text-left ml-3 target-sel">
									<div class="custom-control custom-control-inline custom-radio">
									   <input type="radio" class="custom-control-input" name="radio-2" value="DB" id="radio-db-2" checked>
									   <label class="custom-control-label fnt-15 text-dark" for="radio-db-2">DB</label>
									</div>
									<div class="custom-control custom-control-inline custom-radio">
									   <input type="radio" class="custom-control-input" name="radio-2" value="ETL" id="radio-etl-2">
									   <label class="custom-control-label fnt-15 text-dark" for="radio-etl-2">ETL</label>
									</div>
									<div class="custom-control custom-control-inline custom-radio">
									   <input type="radio" class="custom-control-input" name="radio-2" value="Others" id="radio-others-2" disabled>
									   <label class="custom-control-label fnt-15 text-dark" for="radio-others-2">Others</label>
									</div>
								</div>
								<div class="row m-0 text-left mt-3 data-expose-target" data-expose="['DB']">
									<div class="col-6">
										<div class="form-group">
											<label class="font-weight-bold">Target DB<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<select class="form-control" id="targetDb" name="targetDb" required>
												<option value="Snowflake">Snowflake</option>
												<option value="AWS Redshift">AWS Redshift</option>
												<option value="Azure SQL DB">Azure SQL DB</option>
												<option value="Azure Synapse">Azure Synapse</option>
												<option value="GCP Bigquery">GCP Bigquery</option>
											</select>
											<div class="invalid-feedback">Please select a db!</div>
										</div>
									</div>
									<div class="col-6">
										<div class="form-group">
											<label class="font-weight-bold">Script Type<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<select class="form-control" id="targetScriptType" name="targetScriptType" required>
												<option value="Python QL">Python QL</option>
												<option value="Query">Query</option>
												<option value="DDL(Table/Views)">DDL(Table/Views)</option>
												<option value="PLSQL Proc">Stored Procedure / PLSQL</option>
												<option value="Cloud / Big Data / ETL">Cloud / Big Data / ETL</option>
											</select>
											<div class="invalid-feedback">Please enter a script type!</div>
										</div>
									</div>
								</div>
								<div class="row m-0 text-left mt-0 data-expose-target" data-expose="['DB']">
									<div class="col-6">
										<p class="pl-1 fnt-15 text-dark text-left mt-2">Is Schema / Data Mapping done?</p>
										<div class="custom-control custom-control-inline custom-radio">
										   <input type="radio" class="custom-control-input" name="mpdone" value="Yes" id="map-true">
										   <label class="custom-control-label fnt-15 text-dark" for="map-true">Yes</label>
										</div>
										<div class="custom-control custom-control-inline custom-radio">
										   <input type="radio" class="custom-control-input" name="mpdone" value="No" id="map-false" checked>
										   <label class="custom-control-label fnt-15 text-dark" for="map-false">No</label>
										</div>
									</div>
								</div>
								<div class="row m-0 text-left mt-2 none map-done-div data-expose-source" data-expose="['DB']">
									<div class="col-6">
										<select class="form-control" id="schemaMappingName">
											<c:forEach items="${projectData}" var="project">
												<option>${project}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="m-0 text-left data-expose-target none" data-expose="['ETL']">
									<div class="row m-0 mt-3 text-left">
										<div class="col-6">
											<div class="form-group">
												<label class="font-weight-bold">Target ETL<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
												<select class="form-control" id="targetETL" name="targetETL" required>
													<option value="Apache Spark (Python)">Apache Spark (Python)</option>
													<option value="Apache Spark (Scala)">Apache Spark (Scala)</option>
													<option value="AWS Glue (Python)">Apache Spark (Scala)</option>
													<option value="AWS Glue (Scala)">AWS Glue (Scala)</option>
													<option value="Azure Data Flow">Azure Data Flow</option>
													<option value="Azure Databricks">Azure Databricks</option>
													<option value="GCP Dataflow (Beam Java)">GCP Dataflow (Beam Java)</option>
													<option value="GCP Dataflow (Beam Python)">GCP Dataflow (Beam Python)</option>
													<option value="GCP Dataproc">GCP Dataproc</option>
													<option value="Python (Pandas)">Python (Pandas)</option>
													<option value="ELT Stored Procs">ELT Stored Procs</option>
													<option value="Talend">Talend</option>
													<option value="Informatica PC (9.6 +)">Informatica PC (9.6 +)</option>
													<option value="Informatica BDM (10.2.0 +)">Informatica BDM (10.2.0 +)</option>
													<option value="Informatica IICS">Informatica IICS</option>
													<option value="Datastage">Datastage</option>
													<option value="SSIS">SSIS</option>
												</select>
												<div class="invalid-feedback">Please select a db!</div>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group">
												<label class="font-weight-bold">Process Flow<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
												<select class="form-control" id="targetProcessFlow" name="targetProcessFlow" required>
													<option value="Apache Airflow">Apache Airflow</option>
													<option value="Shell Scripts" disabled >Shell Scripts</option>
													<option value="Python" disabled >Python</option>
													<option value="AWS Step Function" disabled >AWS Step Function</option>
													<option value="AWS Lambda" disabled >AWS Lambda</option>
													<option value="AWS Batch" disabled >AWS Batch</option>
													<option value="ADF Pipeline" disabled >ADF Pipeline</option>
													<option value="Azure Functions" disabled >Azure Functions</option>
													<option value="GC Compozer" disabled >GC Compozer</option>
													<option value="Informatica Workflow" disabled >Informatica (Workflow)</option>
													<option value="Datastage Sequence" disabled >Datastage (Sequence Job)</option>
													<option value="SSIS Processflow" disabled >SSIS (Control Flow)</option>
													<option value="Talend Processflow" disabled >Talend (Orchestration Job)</option>
												</select>
												<div class="invalid-feedback">Please enter a script type!</div>
											</div>
										</div>
									</div>
								</div>
								<div class="m-0 text-left data-expose-target new-conn-tbl none" data-expose="['ETL']">
									<p class="pl-3 fnt-15 text-dark">Please provide the new connection details</p>
									<div class="row m-0 mt-2 text-left">
										<table class="table text-left fnt-15 ml-3 mr-3">
											<thead class="bg-light">
												<tr>
													<th>Current Connection</th>
													<th>Current DB</th>
													<th>New Connection</th>
													<th>New DB</th>
													<th>Schema Mapping</th>
												</tr>
											</thead>
											<tbody id="new-conn-dtls"></tbody>
										</table>
									</div>
								</div>
								<div class="text-right">
									<button class="btn btn-green" data-toggle="modal" data-target="#groupNameModal">Save</button>
								</div>
							</section>
							<!-- <section id="section-processConv-3" class="bg-white border"  style="border-color:#ecf0f1">
								<div class="row m-0 text-left">
									<div class="col-12">
										<p class="fnt-15 text-left" style="color:#666666">Upload File</p>
										<input type="file" name="filepond" class="filepond" id="processConvFile" multiple 
    data-allow-reorder="true"
    data-max-file-size="15MB"
    data-max-files="10">
									</div>
								</div>
							</section>
							<section id="section-processConv-4" class="bg-white border"  style="border-color:#ecf0f1">
								<p class="fnt-15 text-dark text-left">Output unavailable. Please upload a file.</p>
							</section> -->
					</div>
				</div>
			</section>
        		</div>
        	</div>
        </div>
     </section>
    <div class="side-console">
    	<div class="side-console-head">
    		<div class="row m-0">
    			<div class="col-11">
    				<span>Report Details</span>
    			</div>
    			<div class="col-1 text-center">
    				<button type="button" class="close side-console-close" aria-label="Close">
					  <span aria-hidden="true">&times;</span>
					</button>
    			</div>
    		</div>
    	</div>
    	<div class="side-console-body p-3">
    	
    	</div>
    </div>
	<div class="modal fade" id="groupNameModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="groupNameModal" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Almost done!</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <div class="form-group">
			    <label for="group-name">Group Name</label>
			    <input type="text" class="form-control" placeholder="Enter group name" id="group-name">
			</div>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-green save-btn">Save</button>
	        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script src="<%=request.getContextPath()%>/resources/js/dependency/filepond.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/processMigrator/processConverter.js"></script>
</body>

</html>