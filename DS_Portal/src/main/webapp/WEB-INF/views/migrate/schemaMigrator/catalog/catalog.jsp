<!doctype html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
	<style>
		.option{
	    	display:none;
	    }
	    .option span i.fas{
	    	font-size:20px;
	    }
	   
		#catalogTableDiv tr.active, #catalogTableDiv tr.active a{
	    	/*background-color:#2CC185;*/
	    	background-color:#B0BED9;	    	
	    	color:#212529;
	    }
	    
	    #catalogTableDiv thead tr th{
	    	border-bottom-color: white!important;
	    }
	    table.dataTable.no-footer {
    		border-bottom: 0px solid white!important;
		}
		#catalogTableDiv tbody.tr{
			border-bottom: 0px solid white!important;
		}

	  	#toolTable tr.active ,#toolTable tr.active a {
	    	background-color:#2CC185;
	    	color:#fff;
	    }
	    #viz {
	            width: 1200px;
	            height: 700px;
	            border: 0px solid lightgray;
	            font: 12pt arial;
	    }
	    .custom-control-label:before{
		  background-color:rgba(52, 73, 94, 0.4);
		}
		.custom-checkbox .custom-control-input:checked~.custom-control-label::before{
		  background-color:rgba(41, 128, 185,1.0);
		}
	    .dropdown-menu{/*height:75vh;*/overflow:auto;}
		.scroll-help{
		    position: absolute;
	    	right: 10px;
	    	bottom: 50px;
		}
	</style>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">
</head>
<body id="main" class="catalog-view">
	<form id="XSDConvertor" action="<%=request.getContextPath()%>/catalogs/importXSDMetadata/XMLConversion" method="get">
		<input id="CataLogNameXSD" name="CataLogNameXSD" value="" type="hidden"/>
	</form>
	<input type="hidden" id="dbListArray" value='${dbListArray}'/>
	<input type="hidden" id="syncArray" value='${syncArray}' />
	<input type="hidden" id="catalogingToolList" value='${catalogingToolList}' />
	<input type="hidden" name="catalogArray" id="catalogArray" value="${catalogArray}" />
	
	
	<div>
		<header>
	        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
	    </header>
	</div>
	<div class="">
		<section class="pb-3 bg-white overflow-sec">
	        <div class="container-fluid p-0">
	        	<nav aria-label="breadcrumb bg-white" class="pt-3">
	                <ol class="breadcrumb ml-4">
	                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home">Home</a></li>
	                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate">Migrate</a></li>
	                    <li class="breadcrumb-item active" aria-current="page">Data Catalog</li>
	                </ol>
	            </nav>
	        	<div class="items-box border">
	        		<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								<div id="AddEdit">
									<div class="validation-message">
										<c:if test="${type eq 'Failed'}">
											<div class="alert alert-danger alert-dismissible fade show" role="alert">
											  <strong>${type}!</strong> ${message}
											  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
											    <span aria-hidden="true">&times;</span>
											  </button>
											</div>
										</c:if>
										<c:if test="${type eq 'Success'}">
											<div class="alert alert-success alert-dismissible fade show" role="alert">
											  <strong>${type}!</strong> ${message}
											  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
											    <span aria-hidden="true">&times;</span>
											  </button>
											</div>
										</c:if>
									</div>
									<div class="row m-0">
										<div class="col-12 p-0">
											<div class="input-group mb-3">
											  <div class="input-group-prepend bg-white">
											    <span class="input-group-text" id="basic-addon1"><i class="fa fa-search"></i></span>
											  </div>
											  <input type="text" class="form-control" name="search-catalog" placeholder="Search for catalogs" aria-label="Search for catalogs" aria-describedby="basic-addon1">
											</div>
										</div>
									</div>
									<div class="row p-0 mt-3 mb-2">
										<div class="col-9">
											<button type="button" id="downloadBtn" class="d-inline btn btn-sm btn-custom nav-link btn btn-green add-catalog"><i class="fa fa-plus"></i>&ensp;Add Catalog</button>
											<button type="button" id="downloadBtn" class="d-inline btn btn-sm btn-custom nav-link btn btn-green import-btn"><i class="fa fa-upload"></i>&ensp;Import</button>									
										</div>	
										
										<div class="col-3 text-right">
											<a type="button" href="<%=request.getContextPath()%>/resources/data/Metadata.xlsx" id="downloadBtn" class="d-inline btn btn-sm btn-custom nav-link btn btn-green"><i class="fa fa-download"></i>&ensp;Download Metadata Template</a>									
										</div>
									</div>
								</div>
								<div class="row mt-3 p-0">
									<div class="col-md-12">
										<table class="table" id="catalogTableDiv">
											<thead class="bg-light-custom text-dark">
												<tr>
													<th class="w-60">Action</th>
													<th>Catalog Name</th>
													<th>DB Type</th>
													<th>Description</th>
												</tr>
											</thead>
											<tbody id="catalogBody" class="custom-table">
												<c:forEach items="${catalogArray}" var="catalog" varStatus="id">
													<tr>
														<td class="d-flex justify-content-start">
															<i class="fa fa-edit edit-catalog text-secondary" title="Edit catalog"></i>
															<i class="fa fa-trash pl-3 text-secondary delete-catalog" title="Delete catalog"></i>
														</td>
														<td class=""><a href="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs/${catalog.catalogName}" style="color: #048efb">${catalog.catalogName}</a></td>
														<td>${catalog.dbType}</td>
														<td>${catalog.catalogDescription}</td>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
	
					</div>
	        	</div>
	        </div>
	    </section>
	</div>
	
	<div class="modal fade" id="catalog-add-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="catalog-add-modal" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered modal-xl">
	    <div class="modal-content border">
	    	<form autocomplete="off" action="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs/add-catalog" class="needs-validation" novalidate method="post" id="AddcatalogForm">
	       <section>
				<div class="tabs tabs-style-linemove two-tab" id="catalog-add-tab">
					<nav>
						<ul>
							<li class="tab-current"><a href="#section-linemove-1" class="icon icon-home"><span><span class="step">1</span>&ensp;Name and Connection type</span></a></li>
							<li><a href="#section-linemove-2" class="icon icon-box"><span><span class="step">2</span>&ensp;Additional Details</span></a></li>
							<!-- <li><a href="#section-linemove-3" class="icon icon-display"><span>Analytics</span></a></li>
							<li><a href="#section-linemove-4" class="icon icon-upload"><span>Upload</span></a></li>
							<li><a href="#section-linemove-5" class="icon icon-tools"><span>Settings</span></a></li> -->
						</ul>
					</nav>
					<div class="content-wrap">
							<section id="section-linemove-1" class="content-current">
								<div class="row m-0 text-left">
									<div class="col-6">
										<div class="form-group">
											<label for="connName">Catalog Name<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup>
											</label>
											<input type="text" class="form-control" required id="catalogName" name="catalogName" placeholder="Enter catalog name" required>
											<small class="text-muted">Catalog name has to be unique</small>
											<div class="invalid-feedback">Please enter a name!</div>
										</div>
									</div>
									<div class="col-6">
										<div class="form-group">
											<label for="connName">Connection Type<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup>
											</label>
											<select class="custom-select" id="dbType" name="dbType" required>
												<option value="" selected disabled>Select Connection Type</option>
											</select>
											<div class="invalid-feedback">Please select the connection type!</div>
										</div>
									</div>
								</div>
								<div class="row m-0 mt-1 text-left">
									<div class="col-12">
										<div class="form-group text-left">
											<label for="connName">Catalog Description</label>
											<textarea class="form-control" rows="4" id="catalogDescription" name="catalogDescription" placeholder="Enter catalog description"></textarea>
										</div>
									</div>
								</div>
								<div class="row m-0 mt-1">
									<div class="col-12 text-right">
										<button class="btn btn-green" type="button" id="catalog-section-2">Next</button>
										<button class="btn btn-light" type="button" data-dismiss="modal">Close</button>
									</div>
								</div>
							</section>
							<section id="section-linemove-2">
								<div class="row text-left" id="form-params">
									<div class="col-6 d-form none" data-dbtype='["JSON", "Delimited", "Fixed Width", "XML", "Avro", "Parquet"]'>
										<div class="form-group">
											<label for="connName">Storage Type<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup>
											</label>
											<select class="form-control" id="storageType" name="storageType" required>
												<option value="S3">S3</option>
												<option value="Azure Blob storage">Azure Blob</option>
												<option value="Google cloud storage">Google cloud storage</option>
												<option value="FTP">FTP</option>
												<option value="SFTP">SFTP</option>
												<option value="Shared Folder">Shared Folder</option>
											</select>
											<div class="invalid-feedback">Please select a storage type!</div>
										</div>
									</div>
									<div class="col-6 d-form" data-dbtype='["Oracle", "BigQuery", "Snowflake", "Cosmos", "Aurora DB", "Hadoop HDFS", "Hadoop Hive", "Sequential", "VSAM", "Azure Blob Storage", "S3", "Google Cloud Storage", "Oracle", "SQL Server", "DB2 LUW", "Teradata", "Netezza", "ODBC", "PostgreSQL", "MySQL", "Sybase", "MonetDB"]'>
										<div class="form-group">
											<label for="connName">Connection Name
											</label>
											<input type="text" class="form-control not-req" id="connectionName" name="connectionName" placeholder="Enter connection name">
											<div class="invalid-feedback">Please enter a connection name!</div>
										</div>
									</div>
									<div class="col-6 d-form" id="portNo" data-dbtype='["Azure SQL DB", "Azure SQL Data Warehouse", "MongoDb", "Cosmos", "Aurora DB", "Hadoop HDFS", "Hadoop Hive", "Sequential", "VSAM", "Azure Blob Storage", "S3", "Google Cloud Storage", "Oracle", "SQL Server", "DB2 LUW", "Teradata", "Netezza", "ODBC", "PostgreSQL", "MySQL", "Sybase", "MonetDB"]'>
										<div class="form-group">
											<label for="connName">Port
											</label>
											<input type="number" class="form-control not-req" id="port" name="port" placeholder="Enter port">
											<div class="invalid-feedback">Port value is mandatory!</div>
										</div>
									</div>
									<div class="col-6 d-form" id="userDiv" data-dbtype='["Azure SQL DB", "Azure SQL Data Warehouse", "Snowflake", "MongoDb", "Cosmos", "Aurora DB", "Hadoop HDFS", "Hadoop Hive", "Sequential", "VSAM", "Azure Blob Storage", "S3", "Google Cloud Storage", "Oracle", "SQL Server", "DB2 LUW", "Teradata", "Netezza", "ODBC", "PostgreSQL", "MySQL", "Sybase", "MonetDB"]'>
										<div class="form-group">
											<label for="connName" id="userlabel">Username</label>
											<input type="text" class="form-control not-req" id="userName" name="userName" placeholder="Enter username">
											<div class="invalid-feedback">Username is mandatory!</div>
										</div>
									</div>
									<div class="col-6 d-form" id="hostDiv" data-dbtype='["Azure SQL DB", "Azure SQL Data Warehouse", "Snowflake", "MongoDb", "Cosmos", "Aurora DB", "Hadoop HDFS", "Hadoop Hive", "Sequential", "VSAM", "Azure Blob Storage", "S3", "Google Cloud Storage", "Oracle", "SQL Server", "DB2 LUW", "Teradata", "Netezza", "ODBC", "PostgreSQL", "MySQL", "Sybase", "MonetDB"]'>
										<div class="form-group">
											<label for="connName" id="hostLabel">Host Name</label>
											<input type="text" class="form-control not-req" id="hostName" name="hostName" placeholder="Enter host name">
											<div class="invalid-feedback">Host name is mandatory!</div>
										</div>
									</div>
									<div class="col-6 d-form" id="schemaDiv" data-dbtype='["Azure SQL DB", "Azure SQL Data Warehouse", "Snowflake", "MongoDb", "Cosmos", "Aurora DB", "Hadoop HDFS", "Hadoop Hive", "Sequential", "VSAM", "Azure Blob Storage", "S3", "Google Cloud Storage", "Oracle", "SQL Server", "DB2 LUW", "Teradata", "Netezza", "ODBC", "PostgreSQL", "MySQL", "Sybase", "MonetDB"]'>
										<div class="form-group">
											<label for="connName">DB Schema Name
											</label>
											<input type="text" class="form-control not-req" id="schemaName" name="schemaName" placeholder="Enter DB schema">
											<div class="invalid-feedback">Schema name is mandatory!</div>
										</div>
									</div>
									<div class="col-6 d-form none" id="accountNameDiv" data-dbtype='["Snowflake"]'>
										<div class="form-group">
											<label for="connName">Account Name
											</label>
											<input type="text" class="form-control not-req" id="accountName" name="accountName" placeholder="Enter account name">
											<div class="invalid-feedback">Account name is mandatory!</div>
										</div>
									</div>
									<div class="col-6 d-form none" id="masterUsernameDiv" data-dbtype='["Amazon Redshift"]'>
										<div class="form-group">
											<label for="connName">Master Username
											</label>
											<input type="text" class="form-control not-req" id="masterUsername" name="masterUsername" placeholder="Enter master username">
											<div class="invalid-feedback">Master username is mandatory!</div>
										</div>
									</div>
									<div class="col-6 d-form none" id="jdbcUrlDiv" data-dbtype='["Amazon Redshift"]'>
										<div class="form-group">
											<label for="connName">JDBC URL
											</label>
											<input type="text" class="form-control not-req" id="jdbcUrl" name="jdbcUrl" placeholder="Enter JDBC url">
											<div class="invalid-feedback">JDBC url is mandatory!</div>
										</div>
									</div>
									<div class="col-6 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML", "Parquet", "Avro", "JSON"]'>
										<div class="form-group">
											<label>Directory Path<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<input type="text" class="form-control" id="directoryPath" name="directoryPath" placeholder="Enter Directory Path" required>
											<div class="invalid-feedback">Please enter a path!</div>
										</div>
									</div>
									<div class="col-6 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML", "Parquet", "Avro", "JSON"]'>
										<div class="form-group">
											<label>File Extension<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<select class="custom-select" id="fileExtenstion" name="fileExtenstion" required>
												<option value="">--Select file extension--</option>
												<option value="none">none</option>
												<option value=".dat">.dat</option>
												<option value=".csv">.csv</option>
												<option value=".txt">.txt</option>
												<option value=".gz">.gz</option>
												<option value=".tar">.tar</option>
												<option value=".zip">.zip</option>
												<option value=".parquet">.parquet</option>
												<option value=".avro">.avro</option>
												<option value=".json">.json</option>
												<option value=".xml">.xml</option>
												<option value=".xsd">.xsd</option>
											</select>
											<div class="invalid-feedback">Please select an extension!</div>
										</div>
									</div>
									<div class="col-6 d-form none" data-dbtype='["Delimited", "Fixed Width"]'>
										<div class="form-group">
											<label>No of Header Rows<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<input type="text" pattern="[0-9]*" class="form-control" id="headerRows" name="headerRows" placeholder="Enter Header Rows" required>
											<div class="invalid-feedback">Please enter a number!</div>
										</div>
									</div>
									<div class="col-6 d-form none" data-dbtype='["Delimited", "Fixed Width"]'>
										<div class="form-group">
											<label>Text Qualifier<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<select class="custom-select" id="textQualifier" name="textQualifier" required>
												<option value="None">None</option>
												<option value="Single">Single</option>
												<option value="Double">Double</option>
											</select>
											<div class="invalid-feedback">Please select the text qualifier!</div>
										</div>
									</div>
									<div class="col-6 d-form none" data-dbtype='["Delimited", "Fixed Width"]'>
										<div class="form-group">
											<label>Column Delimiter<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
											<input type="text" class="form-control" id="columnDelimiter" name="columnDelimiter" placeholder="Enter Column Delimiter" required>
											<div class="invalid-feedback">Please enter a delimiter!</div>
										</div>
									</div>
									<div class="col-6 d-form none" data-dbtype='["Delimited", "Fixed Width"]'>
										<div class="form-group">
											<label>Row Delimiter</label>
											<input type="text" class="form-control not-req" id="rowDelimiter" name="rowDelimiter" placeholder="Enter Row Delimiter">
										</div>
									</div>
								</div>
								<div class="row m-0 mt-1">
									<div class="col-12 text-right">
										<button class="btn btn-green" id="create-upd-btn">Create</button>
										<button class="btn btn-light" type="button" id="catalog-section-1">Previous</button>
										<button class="btn btn-light" type="button" data-dismiss="modal">Close</button>
									</div>
								</div>
							</section>
						
					</div>
				</div>
			</section>
		    <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
		    </form>
	    </div>
	  </div>
	</div>
	
	
	
	<div class="modal fade" id="catalog-import-ddl-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="catalog-import-ddl-modal" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered modal-xl modal-dialog-scrollable">
	    <div class="modal-content border">
	       
	       <div class="row">
			  <div class="col-4 p-4">
			    <div class="list-group" id="list-tab" role="tablist">
			      <select class="form-control mb-2 mt-1" id="import-catalog-list">
			      
			      </select>
			      <a class="list-group-item list-group-item-action active" id="list-ddl-list" data-toggle="list" href="#list-ddl" role="tab" aria-controls="DDL">Import DDL</a>
			      <a class="list-group-item list-group-item-action" id="list-flatfile-list" data-toggle="list" href="#list-flatfile" role="tab" aria-controls="FlatFile">Import flat file</a>
			      <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#list-messages" role="tab" aria-controls="messages">Import XML / XSD</a>
			      <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-settings" role="tab" aria-controls="settings">Import JSON</a>
			      <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-settings" role="tab" aria-controls="settings">Import Avro</a>
			      <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-settings" role="tab" aria-controls="settings">Import Parquet</a>
			      <a class="list-group-item list-group-item-action" id="list-metadataTemp-list" data-toggle="list" href="#list-metadataTemp" role="tab" aria-controls="metadata">Import Metadata Template</a>
			    </div>
			  </div>
			  <div class="col-8">
			    <div class="tab-content" id="nav-tabContent">
			      <div class="tab-pane fade show active" id="list-ddl" role="tabpanel" aria-labelledby="list-ddl-list">
			      	<section>
						<div class="tabs tabs-style-linemove two-tab" id="import-ddl-tab">
							<nav>
								<ul>
									<li class="tab-current"><a href="#section-ddl-1" class="icon icon-home"><span><span class="step">1</span>&ensp;Upload File</span></a></li>
									<li><a href="#section-ddl-2" class="icon icon-box"><span><span class="step">2</span>&ensp;Select Tables</span></a></li>
								</ul>
							</nav>
							<div class="content-wrap">
									<section id="section-ddl-1" class="content-current bg-white">
										<div class="custom-file fnt-16 text-left mt-1">
										  <input type="file" accept=".sql, .txt" class="custom-file-input" id="ddlFile">
										  <label class="custom-file-label" for="ddlFile">Choose file</label>
										  <small>Supported file types are .sql and .txt</small>
										</div>
										<p class="ddl-validation fnt-13 text-left"></p>
									</section>
									<section id="section-ddl-2" class="bg-white" style="height:40vh;overflow:auto;">
										<div class="input-group mb-1">
										  <div class="input-group-prepend bg-white">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-search"></i></span>
										  </div>
										  <input type="text" class="form-control" name="search-ddl-table" placeholder="Search tables" aria-label="Search for catalogs" aria-describedby="basic-addon1">
										</div>
										<table class="table custom-table fnt-16 text-left mt-2">
											<thead class="bg-light-custom text-dark">
												<th>
													<div class="custom-control custom-checkbox fnt-16 text-left">
													  <input type="checkbox" class="custom-control-input" id="ddl-sel" name="select-all-tables-ddl" checked>
													  <label class="custom-control-label" for="ddl-sel"></label>
													</div>
												</th>
												<th>Table Name</th>
											</thead>
											<tbody id="ddl-body">
											
											</tbody>
										</table>
									</section>
							</div>
						</div>
						<div class="modal-footer bg-white">
							<button class="btn btn-green" id="ddl-create">Create</button>
					        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
					    </div>
					</section>
			      </div>
			      <div class="tab-pane fade" id="list-flatfile" role="tabpanel" aria-labelledby="list-flatfile-list">
			      	<section>
						<div class="tabs tabs-style-linemove" id="import-flatfile-tab">
							<nav>
								<ul>
									<li class="tab-current"><a href="#section-flatfile-1" class="icon icon-home"><span><span class="step">1</span>&ensp;Upload File</span></a></li>
									<li><a href="#section-flatfile-2" class="icon icon-box flatfile-1"><span><span class="step">2</span>&ensp;Select Delimiter</span></a></li>
									<li><a href="#section-flatfile-3" class="icon icon-box flatfile-2"><span><span class="step">3</span>&ensp;Select Datatype</span></a></li>
								</ul>
							</nav>
							<div class="content-wrap">
									<p class="none" id="flatfilePreview"></p>
									<section id="section-flatfile-1" class="content-current bg-white">
										<div class="custom-file fnt-16 text-left mt-1">
										  <input type="file" accept=".csv, .txt, .xlsx" class="custom-file-input" id="flatfile">
										  <label class="custom-file-label" for="flatfile">Choose file</label>
										  <small>Supported file types are .csv and .txt</small>
										</div>
										<div class="row m-0 text-left fnt-15 mt-3">
											<div class="custom-control custom-radio custom-control-inline">
											  <input type="radio" id="delimited" name="type" class="custom-control-input" checked>
											  <label class="custom-control-label fnt-15" for="delimited">Delimited</label>
											</div>
											<div class="custom-control custom-radio custom-control-inline">
											  <input type="radio" id="fixedwidth" name="type" class="custom-control-input" disabled>
											  <label class="custom-control-label fnt-15" for="fixedwidth">Fixed Width</label>
											</div>
										</div>
										<div class="row m-0 text-left fnt-15 mt-3">
											<div class="col-4 pl-0">
												<div class="custom-control custom-checkbox">
												  <input type="checkbox" class="custom-control-input" id="hasHeader" checked>
												  <label class="custom-control-label fnt-15" for="hasHeader">Data has header</label>
												</div>
											</div>
										</div>
										<div class="modal-footer bg-white">
											<button class="btn btn-green flatfile-1">Next</button>
									        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
									    </div>
									</section>
									<section id="section-flatfile-2" class="bg-white">
										<div class="fnt-15 text-left del-section">
											<p class="fnt-15 text-dark">Please select the appropriate delimiters for your data. You can see how the data is affected below.</p>
											<div class="custom-control custom-checkbox custom-control-inline del mt-2">
											  <input type="checkbox" id="tab-del" name="tab-del" class="custom-control-input" value="	">
											  <label class="custom-control-label" for="tab-del">Tab</label>
											</div>
								      		<div class="custom-control custom-checkbox custom-control-inline del">
											  <input type="checkbox" id="semicolon-del" name="semicolon-del" class="custom-control-input" value=";">
											  <label class="custom-control-label" for="semicolon-del">Semicolon</label>
											</div>
								      		<div class="custom-control custom-checkbox custom-control-inline del">
											  <input type="checkbox" id="comma-del" name="comma-del" class="custom-control-input" value=",">
											  <label class="custom-control-label" for="comma-del">Comma</label>
											</div>
								      		<div class="custom-control custom-checkbox custom-control-inline del">
											  <input type="checkbox" id="space-del" name="space-del" class="custom-control-input" value=" ">
											  <label class="custom-control-label" for="space-del">Space</label>
											</div>
								      		<div class="custom-control custom-checkbox custom-control-inline del">
											  <input type="checkbox" id="other-del" name="other-del" class="custom-control-input" value="other-del">
											  <label class="custom-control-label" for="other-del">Other</label>
											</div>
											<input type="text" class="input-sm none pl-2" id="other-del-val" style="width:25px" onkeyup="updateTable(this)">
											<div class="row mt-3">
												<div class="col">
													<div class="custom-control custom-checkbox">
													  <input type="checkbox" class="custom-control-input" id="conseq-del" value="other" onclick="updateTable(this)">
													  <label class="custom-control-label" for="conseq-del">Treat consecutive delimiters as one.</label>
													</div>
												</div>
											</div>
											<table class="table table-responsive mt-3">
												<tbody id="flatfile-preview"></tbody>
											</table>
											
											<div class="modal-footer bg-white">
												<button class="btn btn-green flatfile-2">Next</button>
										        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
										    </div>
										</div>
									</section>
									<section id="section-flatfile-3" class="bg-white">
										<table class="table table-responsive mt-3 fnt-15 text-left">
											<tbody id="flatfile-datatype-preview"></tbody>
										</table>
										<div class="modal-footer bg-white">
											<button class="btn btn-green flatfile-3" id="import-ff">Import</button>
									        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
									    </div>
									</section>
							</div>
						</div>
					</section>
			      </div>
			      <div class="tab-pane fade" id="list-metadataTemp" role="tabpanel" aria-labelledby="list-metadataTemp-list">
			      		<section>
							<div class="tabs tabs-style-linemove two-tab" id="import-metadata-tab">
								<nav>
									<ul>
										<li class="tab-current"><a href="#section-ddl-1" class="icon icon-home"><span><span class="step">1</span>&ensp;Upload File</span></a></li>
									</ul>
								</nav>
								<div class="content-wrap">
										<section id="section-ddl-1" class="content-current bg-white">
											<div class="custom-file fnt-16 text-left mt-1">
											  <input type="file" accept=".xlsx" class="custom-file-input" id="metadataFile">
											  <label class="custom-file-label" for="metadataFile">Choose file</label>
											  <small>Supported file types are .xlsx</small>
											</div>
										</section>
								</div>
							</div>
							<div class="modal-footer bg-white">
								<button class="btn btn-green" id="ddl-create">Create</button>
						        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
						    </div>
						</section>
			      	</div>
			      <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">...</div>
			    </div>
			  </div>
			</div>
	       
	    </div>
	  </div>
	</div>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/catalog.js"></script>
</body>
</html>