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
			top: 0;
    		z-index:3000;
	     	left:0;
	     	right:0;
	     	width:100%;
	     	height:100%;
	     	display:none;
	     	background-color:#fff;
	     	margin:0 auto;
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/awesomplete.css">
</head>
<body id="main" class="catalog-view">
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
                    <li class="breadcrumb-item active" aria-current="page">Schema Designer</li>
                </ol>
            </nav>
        	<div class="items-box border">
        		<div class="container-fluid">
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
					<div class="row">
						<div class="col-md-12">
							
							<div id="AddEdit">
									<div class="row m-0">
										<div class="col-12 p-0">
											<div class="input-group mb-3">
											  <div class="input-group-prepend bg-white">
											    <span class="input-group-text" id="basic-addon1"><i class="fa fa-search"></i></span>
											  </div>
											  <input type="text" class="form-control" name="search-projects" placeholder="Search for projects" aria-label="Search for projects" aria-describedby="basic-addon1">
											</div>
										</div>
									</div>
									<div class="row p-0 mt-1 mb-2">
										<div class="col-9">
											<button type="button" id="add-project" class="d-inline btn btn-sm btn-custom nav-link btn btn-green"><i class="fa fa-plus"></i>&ensp;Add Project</button>
										</div>	
										
									</div>
								</div>
							<div class="row p-0 mt-3">
								<div class="col-md-12">
									<table class="table text-left">
										<thead class="bg-light text-dark">
											<th>Action</th>
											<th>Project Name&ensp;<i class="fas fa-sort-up pointer sortable" data-sort="desc"></i></th>													
											<th>Project DB&ensp;<i class="fas fa-sort-up pointer sortable" data-sort="desc"></i></th>
											<th>Catalog Name&ensp;<i class="fas fa-sort-up pointer sortable" data-sort="desc"></i></th>
											<th>Description</th>
											<th>Visualization</th>
											<th>Schema Definition</th>
											<th>Generate Jobs</th>
											<th>Status</th>
										</thead>
										<tbody id="projectsBody">
											<c:forEach items="${projectArray}" var="project" varStatus="id">
												<tr>
													<td class="d-flex justify-content-start">
														<i class="fa fa-edit edit-project pointer text-secondary" title="Edit project"></i>
														<i class="fa fa-trash pl-3 text-secondary pointer delete-project" title="Delete project"></i>
													</td>
													<td><a style="color: #048efb" href="<%=request.getContextPath()%>/home/migrate/schema-migrator/schema-designer/${project.projectName}/entityDesign">${project.projectName}</a></td>
													<td>${project.dbType}</td>
													<td>${project.catalogName}</td>
													<td>${project.projectDescription}</td>
													<c:choose>
														<c:when test="${project.status eq 'mapped'}">
															<td class="pl-3"><i class="fa fa-project-diagram visualize pointer" ></i></td>
															<td class="pl-3"><i class="fa fa-download download-schema pointer"></i></td>
															<td class="pl-3"><i class="fa fa-play download-code pointer"></i></td>
														</c:when>
														<c:otherwise>
															<td class="pl-3 text-muted"></td>
															<td class="pl-3 text-muted"></td>
															<td class="pl-3 text-muted"></td>
														</c:otherwise>
													</c:choose>
													<td>${project.status}</td>
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
     
     
     <div class="modal fade" id="project-add-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="project-add-modal" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered modal-xl">
	    <div class="modal-content border">
	    	<form autocomplete="off" action="<%=request.getContextPath()%>/home/migrate/schema-designer/add-schema-project" class="needs-validation" novalidate method="post" id="AddprojectForm">
	       <section>
				<div class="tabs tabs-style-linemove two-tab" id="project-add-tab">
					<nav>
						<ul>
							<li class="tab-current"><a href="#section-linemove-1" class="icon icon-home"><span><span class="step">1</span>&ensp;Add New Project</span></a></li>
						</ul>
					</nav>
					<div class="content-wrap">
							<section id="section-linemove-1" class="content-current">
								<div class="row m-0 text-left">
									<div class="col-6">
										<div class="form-group">
											<label for="projectName">Project Name<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup>
											</label>
											<input type="text" class="form-control" required id="projectName" name="projectName" placeholder="Enter project name" required>
											<small>Project name has to be unique</small>
											<div class="invalid-feedback">Please enter a name!</div>
										</div>
									</div>
									<div class="col-6">
										<div class="form-group">
											<label for="dbType">Project DB<sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup>
											</label>
											<select class="custom-select" id="dbType" name="dbType" required>
												<option value="" selected disabled>Select project target db</option>
											</select>
											<div class="invalid-feedback">Please select the project target db!</div>
										</div>	
									</div>
								</div>
								<div class="row m-0 mt-1 text-left">
									<div class="col-12">
										<div class="form-group">
											<label for="projectDescription">Project Description</label>
											<textarea class="form-control" id="projectDescription" name="projectDescription" rows="3" placeholder="Enter project description"></textarea>
										</div>
									</div>
								</div>
								<div class="row m-0 mt-1 text-left" id="form-params">
									<div class="col-6">
										<div class="form-group">
										    <label for="catalogName">Catalog Name</label>
										    <select class="form-control" id="catalogName" name="catalogName">
												<c:forEach items="${catalogList}" var="entry" varStatus="id">
													<option>${entry}</option>
												</c:forEach>
											</select>
									  	</div>
								  	</div>
								  	<div class="col-6">
								  		<div class="form-group">
										    <label for="dbStat">Upload DB Stats</label>
										    <div class="custom-file">
											  <input type="file" class="custom-file-input" id="trainingFile">
											  <label class="custom-file-label" for="trainingFile">Choose file</label>
											</div>
									  	</div>
								  	</div>
								 </div>
							</section>
					</div>
				</div>
			</section>
			<div class="modal-footer">
				<button class="btn btn-green" id="crt-upd-prj-btn">Create</button>
		        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
		    </div>
		    <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
		    </form>
	    </div>
	  </div>
	</div>
	
	<div class="full-overlay p-0" style="display:none;">
      	<button type="button" class="close full-overlay-close" aria-label="Close" style="z-index:2000">
		  <span aria-hidden="true">&times;</span>
		</button>
		<div class="filter-overlay">
			<div class="row m-0 mt-2">
				<div class="col-4">
					<input type="text" class="form-control" name="search-viz" placeholder="Search for collections / attributes" />
				</div>
				<!-- <div class="col-1 pl-0">
					<button class="btn btn-green search-viz-btn">Search</button>
				</div> -->
				<!-- <div class="col-6">
					<div class="custom-control custom-radio custom-control-inline mt-1">
					  <input type="radio" id="showAll" name="visualization-filter" class="custom-control-input" checked>
					  <label class="custom-control-label" for="showAll">Show All</label>
					</div>
					<div class="custom-control custom-radio custom-control-inline">
					  <input type="radio" id="showCollections" name="visualization-filter" class="custom-control-input">
					  <label class="custom-control-label" for="showCollections">Show only Collections</label>
					</div>
					<div class="custom-control custom-radio custom-control-inline">
					  <input type="radio" id="showAttributes" name="visualization-filter" class="custom-control-input">
					  <label class="custom-control-label" for="showAttributes">Show only attributes</label>
					</div>
				</div> -->
			</div>
		</div>
		<div id="contents" class="h-100">
		  <div id="legend">
		   
		    <div>
		      <span id="error"></span>
		    </div>
		  </div>
		  <div id="left" class="h-100">
		    <div id="mynetwork" class="w-100 h-100"></div>
		  </div>
		</div>
		<div class="graph-legends">
			<div class="main-legend mr-3">
				<div class="legend-box"></div>
				<p>Main Collections</p>
			</div>
			<div class="embed-legend mr-3">
				<div class="legend-box"></div>
				<p>Embed Collections</p>
			</div>
			<div class="attribute-legend">
				<div class="legend-box"></div>
				<p>Attribute Groups</p>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script src="<%=request.getContextPath()%>/resources/js/dependency/vis-network.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/dependency/lodash_min.js" ></script>
	<script src="<%=request.getContextPath()%>/resources/js/dependency/awesomplete.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/schemaDesigner.js"></script>
</body>
</html>