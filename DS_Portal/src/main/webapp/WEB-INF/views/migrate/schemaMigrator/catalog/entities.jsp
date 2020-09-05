<!DOCTYPE html>
<html>
	<head>
	   <%@ include file="/WEB-INF/views/common/header.jsp" %>
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">

	</head>
	<body class="entity-view">
		
		<input type="hidden" value="${entityList }" id="entityList" name="entityList"/>
		<input type="hidden" value="${catalogdbType}" id="catalogdbType"/>
		<p class="none" id="entityJson">${entityJson}</p>
		<input type="hidden" id="dbListArray" value='${dbList}'/>
		<input type="hidden" id="selectedCatalog" value='${selectedCatalog}'/>
		<div>
			<header>
		        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
		    </header>
		</div>
		<div class="">
		<section class="pb-3 bg-white overflow-sec">
	        <div class="container-fluid p-0">
	        	<nav aria-label="breadcrumb" class="pt-3">
	                <ol class="breadcrumb ml-4">
	                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home">Home</a></li>
	                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate">Migrate</a></li>
	                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs">Data Catalog</a></li>
	                    <li class="breadcrumb-item active" aria-current="page">${selectedCatalog}</li>
	                </ol>
	            </nav>
	            <div class="items-box border">
	        		<div class="container-fluid">
						<div id="AddEditEntity">
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
									  <input type="text" class="form-control" name="search-entity" placeholder="Search for entities" aria-label="Search for catalogs" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="row p-0 mt-3 mb-2">
								<div class="col-9">
									<button type="button" id="downloadBtn" class="d-inline btn btn-sm btn-custom nav-link btn btn-green add-entity"><i class="fa fa-plus"></i>&ensp;Add Entity</button>
								</div>	
								
								<div class="col-3 text-right">
									<button type="button" id="exportMetadata" class="d-inline btn btn-sm btn-custom nav-link btn btn-green"><i class="fa fa-file-excel"></i>&ensp;Export Metadata</button>
									<button type="button" id="generateDDLBtn" disabled class="d-inline btn btn-sm btn-custom nav-link btn btn-green"><i class="fa fa-download"></i>&ensp;Generate DDL</button>									
								</div>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12 p-0">
								<div class="container-fluid">
								<table class="table" id="exisitingEntity">
										<thead class="bg-light text-dark text-left">
											<tr>
											  <th>
											  	<div class="custom-control custom-checkbox">
													<input type="checkbox" class="custom-control-input" name="select-all-entities" id="select-all-entities">
													<label class="custom-control-label" for="select-all-entities"></label> 
												</div>
											  </th>
											  <th class="w-60">Action</th>
										      <th>Physical Name</th>
										      <th>Logical Name</th>
										      <th>Description</th>
										      <th>Type</th>
										      <th>XSD Ref Path</th>
										      <th>Record Identifier</th>
										    </tr>
										 </thead>
								    	<tbody id="entityBody" class="custom-table">												
											<c:forEach items="${entityList}" var="current" varStatus="id">
												<tr id="exEnt" >
													<td>
														<div class="custom-control custom-checkbox">
															<input type="checkbox" class="custom-control-input en-sel" id=${current.physicalName}>
															<label class="custom-control-label" for=${current.physicalName}></label> 
														</div>
													</td>
													<td class="d-flex justify-content-start">
														<i class="fa fa-edit edit-entity text-secondary" title="Edit entity"></i>
														<i class="fa fa-trash pl-3 delete-entity text-secondary" title="Delete entity"></i>
													</td>
													<td class="ov-el m-width-150"><a href="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs/${selectedCatalog}/${current.physicalName}" style="color: #048efb"><c:out value="${current.physicalName}"></c:out></a></td>
													<td class="ov-el m-width-150"><c:out value="${current.logicalName}"></c:out></td>
													<td class="ov-el m-width-150"><c:out value="${current.description}"></c:out></td>
														<c:if test="${empty current.entityType}">
															<td class="ov-el m-width-150">Table</td>
														</c:if>
														<c:if test="${not empty current.entityType}">
															<td class="ov-el m-width-150"><c:out value="${current.entityType}"></c:out></td>
														</c:if>
													<td class="ov-el m-width-150"><c:out value="${current.xsdRefName}"></c:out></td>
													<td class="ov-el m-width-150" title="${current.recordIdentifier}"><c:out value="${current.recordIdentifier}"></c:out></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
	        		</div>
	        	</div>
	        </div>
	    </section>
	    </div>
		
	

	<div class="modal fade" id="entity-add-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="entity-add-modal" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered modal-xl">
	    <div class="modal-content border">
	    	<form autocomplete="off" action="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs/${selectedCatalog}/saveEntity" class="needs-validation" novalidate method="post" id="newEntityForm">
	       <section>
				<div class="tabs tabs-style-linemove two-tab" id="entity-add-tab">
					<nav>
						<ul>
							<li class="tab-current"><a href="#section-entity-1" class="icon icon-home"><span><span class="step">1</span>&ensp;Name and Description</span></a></li>
							<li class="tab-current"><a href="#section-entity-2" class="icon icon-home"><span><span class="step">2</span>&ensp;Details</span></a></li>
						</ul>
					</nav>
					<div class="content-wrap">
							<section id="section-entity-1" class="content-current">
								<div class="row m-0 text-left">
									<div class="col-12">
										<div class="form-row mb-2 ml-1 entityTypDiv">
											<label class="fnt-15" for="">Entity Type</label>							  
										  </div>
										  <div class="form-row entityTypDiv">
										  		<div class="form-group col-md-1">
										  			<div class="custom-control custom-radio">
														  <input type="radio" class="custom-control-input" name="entityRadio" id="table" value="Table" checked>
														  <label class="fnt-15 custom-control-label" for="table">Table</label>
													 </div>
										  		</div>
										  		<div class="form-group col-md-1">
										  			<div class="custom-control custom-radio">
													    <input type="radio" class="custom-control-input" name="entityRadio" id="view" value="View">
													    <label class="fnt-15 custom-control-label" for="view">View</label>
											  		</div>
										  		</div>
										  		<div class="form-group col-md-1">
										  			<div class="custom-control custom-radio">
													    <input type="radio" class="custom-control-input" name="entityRadio" id="query" value="Query">
													    <label class="fnt-15 custom-control-label" for="query">Query</label>
												    </div>
										  		</div>
										  		<div class="form-group col-md-12 none" id="query-string-div">
										  			<textarea class="w-100" rows="12" placeholder="Enter SQL Query" id="query-string"></textarea>
										  		</div>
										  		<div class="form-group col-md-12 none" id="view-string-div">
										  			<textarea class="w-100" rows="12" placeholder="Enter view definition" id="view-string"></textarea>
										  		</div>
										  		<input type="hidden" name="entityType" />
										  </div>
									</div>
								</div>
								<div class="row m-0 text-left table-props">
									<div class="form-group col-md-6">
								      <label for="physicalName">Physical Name</label>
								      <input type="text" class="form-control" placeholder="Physical Name" id="physicalName" name="physicalName" required />
								      <div class="invalid-feedback">Physical name is mandatory!</div>
								    </div>
								    <div class="form-group col-md-6">
								      <label for="logicalName">Logical Name</label>
								      <input type="text" class="form-control" placeholder="Logical Name" id="logicalName" name="logicalName" />
								    </div>
								</div>
								<div class="row m-0 mt-1 text-left table-props">
									<div class="col-12">
										<div class="form-group text-left">
											<label for="description">Entity Description</label>
											<textarea class="form-control" rows="4" id="description" name="description" placeholder="Enter entity description"></textarea>
										</div>
									</div>
								</div>
								<div class="row m-0 mt-1">
									<div class="col-12 text-right">
										<button class="btn btn-green" type="button" id="entity-section-2">Next</button>
										<button class="btn btn-light" type="button" data-dismiss="modal">Close</button>
									</div>
								</div>
							</section>
							<section id="section-entity-2" class="fnt-15 text-left">
								<div class="filesDiv" id="form-params">
								  	<div class="row">
								  		<div class="col-6 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML", "JSON" , "VSAM" , "Sequential"]'>
											<div class="form-group">
												<label class="fnt-15">DB Type<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
												<select class="custom-select" id="entityDbType" name="entityDbType" required>
									  			</select>
											</div>
										</div>
									  	<div class="col-6 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML", "JSON" , "VSAM" , "Sequential"]'>
									  		<div class="form-group">
									  			<div class="form-group">
													<label class="fnt-15" for="connName">Storage Type<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup>
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
									  	</div>
								  	</div>
								  	<div class="row">
								  		<div class="col-12 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML", "JSON" , "VSAM" , "Sequential"]'>
											<div class="form-group">
												<label class="fnt-15">Directory Path<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
												<input type="text" class="form-control" id="directoryPath" name="directoryPath" placeholder="Enter Directory Path">
												<div class="invalid-feedback">Please enter a path!</div>
											</div>
										</div>
								  	</div>
								  	<div class="row">
								  		<div class="col-2 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML" , "VSAM" , "Sequential"]'>
											<div class="form-group">
												<label class="fnt-15">No of Header Rows<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
												<input type="text" pattern="[0-9]*" class="form-control" id="headerRows" name="headerRows" placeholder="Enter Header Rows">
												<div class="invalid-feedback">Please enter a number!</div>
											</div>
										</div>
									  	<div class="col-3 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML", "JSON" , "VSAM" , "Sequential"]'>
											<div class="form-group">
												<label class="fnt-15">File Extension<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
												<select class="custom-select" id="fileExtenstion" name="fileExtenstion">
													<option value="none">none</option>
													<option value=".dat">.dat</option>
													<option value=".csv">.csv</option>
													<option value=".txt">.txt</option>
													<option value=".gz">.gz</option>
													<option value=".tar">.tar</option>
													<option value=".zip">.zip</option>
													<option value=".parquet">.parquet</option>
													<option value=".avro">.avro</option>
												</select>
												<div class="invalid-feedback">Please select an extension!</div>
											</div>
										</div>
										<div class="col-3 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML" , "VSAM" , "Sequential"]'>
											<div class="form-group">
												<label class="fnt-15">Text Qualifier<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
												<select class="custom-select" id="textQualifier" name="textQualifier">
													<option value="None">None</option>
													<option value="Single">Single</option>
													<option value="Double">Double</option>
												</select>
												<div class="invalid-feedback">Please select the text qualifier!</div>
											</div>
										</div>
										<div class="col-2 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML" , "VSAM" , "Sequential"]'>
											<div class="form-group">
												<label class="fnt-15">Column Delimiter<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
												<input type="text" class="form-control" id="columnDelimiter" name="columnDelimiter" placeholder="Enter Column Delimiter">
												<div class="invalid-feedback">Please enter a delimiter!</div>
											</div>
										</div>
										<div class="col-2 d-form none" data-dbtype='["Delimited", "Fixed Width", "XML" , "VSAM" , "Sequential"]'>
											<div class="form-group">
												<label class="fnt-15">Row Delimiter</label>
												<input type="text" class="form-control" id="rowDelimiter" name="rowDelimiter" placeholder="Enter Row Delimiter">
											</div>
										</div>
								  	</div>
								</div>
								<div class="col-12 text-right">
									<button class="btn btn-green" id="create-upd-btn">Create</button>
									<button class="btn btn-light" type="button" id="entity-section-1">Previous</button>
									<button class="btn btn-light" type="button" data-dismiss="modal">Close</button>
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

	
	
	<div class="modal fade" id="sqlQueryModal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog h-100 modal-dialog-scrollable modal-lg">
	    <div class="modal-content h-75">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">SQL Query</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body h-100 p-2">
	        <textarea class="h-100 w-100 border-0 sqlText"></textarea>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Cancel</button>
	        <button type="button" class="btn btn-info btn-sm" onclick="CallSqlParser()">Save</button>
	      </div>
	    </div>
	  </div>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/catalog.js"></script>

</html>