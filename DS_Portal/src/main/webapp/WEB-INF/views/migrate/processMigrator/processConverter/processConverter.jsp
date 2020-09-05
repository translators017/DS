<!doctype html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">
    <style>
    .w-35{width:35px!important;}
    .popover-body{
    	overflow: auto;
    	height: 200px;
    	box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
    }
    </style>
</head>
<body class="project-groups">
	<div>
		<header>
	        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
	    </header>
	</div>
	<div class="h-100">
	<section class="pb-3 bg-white overflow-sec overflow-hide h-100">
        <div class="container-fluid p-0 h-100">
        	<nav aria-label="breadcrumb bg-white" class="pt-3">
                <ol class="breadcrumb ml-4">
                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home">Home</a></li>
                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate">Migrate</a></li>
                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate/process-migrator">Process Migrator</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Process Converter</li>
                </ol>
            </nav>
        	<div class="items-box border h-100 pt-3 pl-2 pr-3">
	        		<div class="container-fluid h-100">
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
									<div class="row p-0">
										<div class="col-2 pr-0">
											<button type="button" class="d-inline btn btn-sm btn-custom nav-link btn btn-green add-grp">Add Group</button>
											<button type="button" disabled class="d-inline btn btn-sm btn-custom nav-link btn btn-green ml-1 conv-btn">Convert Code</button>
											<input type="file" multiple class="convertFileInput" style="opacity: 0;"/>
										</div>	
										<div class="col-10">
											<div class="input-group mb-3">
											  <div class="input-group-prepend bg-white">
											    <span class="input-group-text" id="basic-addon1"><i class="fa fa-search"></i></span>
											  </div>
											  <input type="text" class="form-control" name="search-group" placeholder="Search for groups" aria-label="Search for groups" aria-describedby="basic-addon1">
											</div>
										</div>
									</div>
								</div>
								<div class="row p-0" id="group-sec">
									<div class="col-md-12 pr-0">
										<table class="table" id="processGroupDiv">
											<thead class="bg-light-custom text-dark">
												<tr>
													<th class="d-flex w-35 border-0">
													</th>
													<th class="w-60">Action</th>
													<th>Group Name</th>
													<th>Process type</th>
													<th>Selected Technology</th>
													<th>Last Modified</th>
												</tr>
											</thead>
											<tbody id="processGroupBody" class="custom-table">
												<c:choose>
													<c:when test="${empty processGroups}">
														<tr>
															<td colspan="5">No process groups available</td>
														</tr>
													</c:when>
													<c:otherwise>
														<c:forEach items="${processGroups}" var="group" varStatus="id">
															<tr data-groupname="${group.groupName}" data-technology="${group.selectedTechnology}">
																<td class="w-35">
																	<div class="custom-control custom-radio">
																		<input type="radio" class="custom-control-input group-sel" name="groups" id="${group.groupName}">
																		<label class="custom-control-label" for="${group.groupName}"></label> 
																	</div>
																</td>
																<td class="d-flex justify-content-start">
																	<i class="fa fa-edit edit-group text-secondary" title="Edit group"></i>
																	<i class="fa fa-trash pl-3 text-secondary delete-group" title="Delete group"></i>
																</td>
																<td><c:out value="${group.groupName}"></c:out></td>
																<td><c:out value="${group.processType}"></c:out></td>
																<td><c:out value="${group.selectedTechnology}"></c:out></td>
																<td><c:out value="${group.lastModified}"></c:out></td>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
								<div class="resize none"></div>
								
								
								<div class="row mt-0 p-0" id="run-sec">
									<div class="row m-0 d-flex align-items-center">
										<div class="col-11">
											<ul class="nav nav-tabs mt-2 run-navs">
											  <li class="nav-item">
											    <a class="nav-link active" href="#summary">Summary</a>
											  </li>
											  <li class="nav-item">
											    <a class="nav-link" href="#details">Details</a>
											  </li>
											  <li class="nav-item">
											    <a class="nav-link" href="#metrics">Metrics</a>
											  </li>
											</ul>
										</div>
										<div class="col-1 text-right pt-2 extra-options">
											<i class="fa fa-list-alt lead pr-2 pointer login-popover" data-toggle="popover"></i>
											<i class="fa fa-sync lead pointer pr-2 retry disable" title="Re-run the process"></i>
											<!-- <i class="fa fa-box-open lead pointer bulk-download disable" title="Download bulk"></i> -->
										</div>
									</div>
									<div class="row m-0 mt-2 group-nav" id="summary">
										<div class="col-md-12 pr-0">
											<table class="table text-center" id="processRunDiv">
												<thead class="bg-light-custom text-dark">
													<tr>	
														<th></th>
														<th>Run ID</th>
														<th>File Name</th>
														<th>Status</th>
														<th class="none">Error Log</th>
														<th>Start Time</th>
														<th>End Time</th>
														<th>Complexity</th>
														<th>Automation %</th>
														<th>SQL Queries</th>
														<th>Entity Transformations</th>
														<th>Attribute Transformations</th>
														<th class="none">Total Transformations</th>
														<th class="none">Unsuccessful Conversions</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody class="custom-table run-table">
													
												</tbody>
											</table>
										</div>
									</div>
									<div class="row m-0 mt-2 group-nav none" id="details">
									
									</div>
									<div class="row m-0 mt-2 group-nav none" id="metrics">
									
									</div>
								</div>
							</div>
						</div>
	
					</div>
	        	</div>
        </div>
     </section>
     <div class="full-overlay none">
     	<h6 class="font-weight-bold">Output Comparison <i class="fa fa-spin fa-spinner spin-loader"></i></h6>
     	<button type="button" class="close full-overlay-close" aria-label="Close">
		  <span aria-hidden="true">&times;</span>
		</button>
		<div class="row m-0">
			<div class="col-4 inputType"></div>
			<div class="col-4 outputType"></div>
			<div class="col-4 font-weight-bold">Differences</div>
		</div>
     	<div class="row m-0">
     		<div class="col-4 compare-window" id="input-compare" spellcheck="false">
     			
     		</div>
     		<div class="col-4 compare-window" id="output-compare" spellcheck="false">
     			
     		</div>
     		<div class="col-4 compare-window" id="compare" spellcheck="false">
     			
     		</div>
     	</div>
     </div>
    </div>

	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script src="<%=request.getContextPath()%>/resources/js/dependency/diff.min.js"></script>
	<%-- <script src="<%=request.getContextPath()%>/resources/js/dependency/mergely.min.js"></script> --%>
	<script src="<%=request.getContextPath()%>/resources/js/processMigrator/processConverter.js"></script>
	
</body>
</html>