<!DOCTYPE html>
<html>
	<head>
	    <%@ include file="/WEB-INF/views/common/header.jsp" %>
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">

	</head>
	<body class="attribute-view">
		<div class="loadercss">
			<img src="<%=request.getContextPath()%>/resources/images/loader.gif" height="50" width="50">
		</div>
		<p class="none" id="catalogjsonString">${catalogjsonString}</p>
		<input type="hidden" value="${selectedCatalog }" id="selectedCatalog"/>
		<input type="hidden" value="${selectedEntity}" id="selectedEntity"/>
		<input type="hidden" value="${attrList }" id="attrList" />
		<input type="hidden" value='${entityAttrArray}' id="entityAttrArray"/>
		
		<div>
			<header>
		        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
		    </header>
		</div>
		<section class="pb-3 bg-white overflow-sec">
	        <div class="container-fluid p-0">
	        	<nav aria-label="breadcrumb" class="pt-3">
	                <ol class="breadcrumb  ml-4 bg-white">
				    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home" class="text-info">Home</a></li>
				    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate">Migrate</a></li>
				    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs">Data Catalog</a></li>
				  	<li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs/${selectedCatalog }">${selectedCatalog }</a></li>
				  	<li class="breadcrumb-item active" aria-current="page">${selectedEntity }</li>
				  </ol>
	            </nav>
	            <div class="items-box border">
        			<div class="container-fluid">
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
									  <input type="text" class="form-control" name="search-attribute" placeholder="Search for attributes" aria-label="Search for attributes" aria-describedby="basic-addon1">
									</div>
								</div>
							</div>
							<div class="row p-0 mt-3 mb-2">
								<div class="col-9">
									<button type="button" id="downloadBtn" class="d-inline btn btn-sm btn-custom nav-link btn btn-green add-attribute"><i class="fa fa-plus"></i>&ensp;Add Attribute</button>
								</div>	
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12 p-0">
								<form action="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs/${selectedCatalog }/entity/${selectedEntity}/saveAttributes" method="post">
								<div class="container-fluid">
									<table class="table" id="exisitingAttribute">
										<thead class="bg-light text-dark text-left">
											<tr>
												<th>Action</th>
												<th>Physical Name</th>
												<th>Logical Name</th>
												<th>Description</th>
												<th>Datatype</th>
												<th>Length / Precision</th>
												<th>Scale</th>
												<th>Nullable</th>
												<th>Xpath</th>
												<th>Data Format</th>
												<th>Default Value</th>
												<th>Check Constraint</th>
												<th>Key Type</th>
												<th>Primary Table</th>
												<th>Primary Column</th>
												<th>Referenced Attributes</th>
											</tr>
										</thead>
										<tbody class="custom-table" id="attributeBody">
											
											<c:forEach items="${attrList}" var="current" varStatus="id">
												<tr id="exAttr" class="clickable-row ov-el">
													<td class="d-flex justify-content-start">
														<i class="fa fa-edit pointer edit-attribute text-secondary" title="Edit attribute"></i>
														<i class="fa fa-trash pl-3 pointer text-secondary delete-attribute" title="Delete attribute"></i>
													</td>
													<td class="ov-el m-width-150"><div class="text-truncate" title="${current.physicalName}" style="width:120px;">${current.physicalName}</div></td>
													<td class="ov-el m-width-150"><div class="text-truncate" title="${current.logicalName}" style="width:120px;">${current.logicalName}</div></td>
													<td class="ov-el m-width-150">${current.description}</td>
													<td class="ov-el m-width-150">${current.datatype}</td>
													<c:choose>
														<c:when test="${not empty current.length_precision}">
															<td class="ov-el m-width-150">${current.length_precision}</td>
														</c:when>
														<c:when test="${not empty current.length}">
															<td class="ov-el m-width-150">${current.length}</td>
														</c:when>
													</c:choose>
													
													<td class="ov-el m-width-150">${current.scale}</td>
													<td class="ov-el m-width-150">${current.nullable}</td>
													<td title="${current.xpath}" class="ov-el m-width-150">${current.xpath}</td>
													<td class="ov-el m-width-150">${current.dataFormat}</td>
													<td class="ov-el m-width-150">${current.defaultValue}</td>
													<td class="ov-el m-width-150">${current.checkConstraint}</td>
													<td class="ov-el m-width-150">${current.keyType}</td>
													<td class="ov-el m-width-150">${current.primaryTableName}</td>
													<td class="ov-el m-width-150">${current.primaryColumnName}</td>
													<td class="ov-el m-width-150" title="Add/Edit reference attributes"><i class="fa fa-ellipsis-h"></i></td>
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								</form>
							</div>
						</div>
							
					</div>
        		</div>
	        </div>
	    </section>
		
    	<div class="modal fade" id="attribute-add-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="catalog-add-modal" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered modal-lg">
		    <div class="modal-content border">
		    	<form autocomplete="off" action="<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs/${selectedCatalog}/${selectedEntity}/saveAttributes" class="needs-validation" novalidate method="post" id="attributeForm">
		       <section>
					<div class="tabs tabs-style-linemove two-tab" id="attribute-add-tab">
						<nav>
							<ul>
								<li class="tab-current"><a href="#details" class="icon icon-home"><span><i class="fa fa-plus"></i>&ensp;Details</span></a></li>
								<li><a href="#configuration" class="icon icon-box"><span><i class="fa fa-cog"></i>&ensp;Configuration</span></a></li>
							</ul>
						</nav>
						<div class="content-wrap">
								<section id="details" class="content-current text-left">
									<div class="form-row">
									    <div class="form-group col-md-6">
									      <label for="physicalName">Physical Name<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
									      <input type="text" class="form-control form-control-sm" placeholder="Physical Name" id="physicalName"  name="physicalName" required />
									      <div class="invalid-feedback">Physical name is mandatory!</div>
									    </div>
									    <div class="form-group col-md-6">
									      <label for="logicalName">Logical Name</label>
									      <input type="text" class="form-control" placeholder="Logical Name" id="logicalName"  name="logicalName" />
									    </div>
									  </div>
									  <div class="form-row">
										  <div class="form-group col-md-12">
										    <label for="description">Description</label>
										    <textarea class="form-control" placeholder="Description" id="description" rows="3"  name="description"></textarea>
										  </div>
									  </div>
									 
									  <div class="form-row">
									    <div class="form-group col-6">
									      <label for="datatype">Datatype<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
									      <select class="form-control" name="datatype" id="datatype" required>
									      		<option value="">---Select Datatype---</option>
									      		<c:forEach items="${DatatypeList}" var="Datatypes">
									      		<option value=${Datatypes }>${Datatypes }</option>
									      		</c:forEach>
									      	</select>
									      	<div class="invalid-feedback">Datatype is mandatory!</div>
									    </div>
									    <div class="form-group col-3">
									      <label for="length">Length / Precision</label>
									      <input type="number" class="form-control" placeholder="Length or precision" name="length_precision" id="length_precision"/>
									    </div>
									    <div class="form-group col-3">
									      <label for="scale">Scale</label>
									      <input type="number" class="form-control" placeholder="Scale" name="scale" id="scale" />
									    </div>
									  </div>
									  <div class="row m-0 mt-1">
											<div class="col-12 text-right">
												<button class="btn btn-green" type="button" id="attribute-section-2">Next</button>
												<button class="btn btn-light" type="button" data-dismiss="modal">Close</button>
											</div>
									  </div>
								</section>
								<section id="configuration" class="text-left">
									  <div class="form-row">
									    <div class="form-group col-4">
									      <label for="dataFormat">Data Format</label>
									      <input type="text" class="form-control" placeholder="Data Format" name="dataFormat" id="dataFormat" />
									    </div>
									    <div class="form-group col-4">
									      <label for="defaultValue">Default Value</label>
									      <input type="text" class="form-control" placeholder="Default Value" name="defaultValue" id="defaultValue"/>
									    </div>
									    <div class="form-group col-4">
									      <label for="nullable">Nullable</label>
									        <select class="form-control" id="nullable" name="nullable">
									      		<option>Yes</option>
									      		<option>No</option>
									      	</select>
									    </div>
									  </div>
									  
									  <div class="form-row">
									    
									    <div class="form-group col-md-6">
									      <label for="checkConstraint">Check Constraint</label>
									     	<input type="text" class="form-control" placeholder="Check Constraint" name="checkConstraint" id="checkConstraint"/>
									    </div>
									    <div class="form-group col-md-6">
									      <label for="keyType">Key Type</label>
									      	<select class="form-control" name="keyType" id="keyType">
									      		<option value="None">None</option>
									      		<option value="Primary">Primary</option>
									      		<option value="Foreign">Foreign</option>
									      	</select>
									    </div>
									  </div>
									  
									  <div class="form-row">
									    <div class="form-group col-md-6 none" id="prTable">
									      <label for="keyType">Primary Table Name</label>
									      	<select class="form-control" name="primaryTableName"></select>
									    	<div class="invalid-feedback">Primary table name is mandatory!</div>
									    </div>
									    <div class="form-group col-md-6 none" id="prColumn">
									      <label for="keyType">Primary Column Name</label>
									      	<select class="form-control" name="primaryColumnName"></select>
									      	<div class="invalid-feedback">Primary column name is mandatory!</div>
									    </div>
									   
									  </div>
									  <div class="row m-0 mt-1">
										  <div class="col-12 text-right">
											 <button class="btn btn-green" id="create-upd-btn">Create</button>
											 <button class="btn btn-light" type="button" id="attribute-section-1">Previous</button>
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
    
		
		
		<div class="modal fade" id="catalogModal" tabindex="-1" role="dialog" aria-labelledby="confirmModal" aria-hidden="true">
		  <input type="hidden" id="type"/>
		  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header bg-info">
		        <h5 class="modal-title text-light" id="exampleModalLongTitle">Select Entity / Attributes</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p class="text-danger none errmsg">All fields marked in <strong>*</strong> are mandatory</p>
		        <input type="text" class="form-control form-control-sm" id="searchCatalog" placeholder="Search Catalog" /><hr>
		        <div class="row nopadding">
		        	<div class="col-4">
		        		<div class="form-group">
						    <label for="catalog">Select Catalog <sup class="text-danger"><strong>*</strong></sup></label>
						    <select class="form-control form-control-sm" id="catalog" onchange="updateEntityForCatalog()"></select>
						 </div>
					</div>
					<div class="col-4">
						 <div class="form-group">
						    <label for="entity">Select Entity <sup class="text-danger"><strong>*</strong></sup></label>
						    <select class="form-control form-control-sm" id="entity" onchange="updateAttributeforEntity()"></select>
						 </div>
					</div>
					<div class="col-4">
						 <div class="form-group">
						    <label for="Attribute">Select Attribute <sup class="text-danger"><strong>*</strong></sup></label>
						    <select class="form-control form-control-sm" id="attribute"></select>
						 </div>
		        	</div>
		        </div>
		      </div>
		      <div class="modal-footer" id="mdl-ftr">
		      	<button type="button" class="btn btn-info btn-sm" id="saveBtn" onclick="saveReferenceAttribs()">Save</button>
		      </div>
		    </div>
		  </div>
		</div>
		
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/catalog.js"></script>
</html>