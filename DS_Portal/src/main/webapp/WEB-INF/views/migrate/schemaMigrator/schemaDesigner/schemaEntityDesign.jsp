<!DOCTYPE html>
<html>
	<head>
	    <%@ include file="/WEB-INF/views/common/header.jsp" %>
	
		<style>
			.auto-box {
				min-height: 68vh;
			}
			
			.highlight {
				background-color: #2CCE71;
				color: #fff;
			}
			
			.box,.emptybox {
				width: 100%;
				min-height: auto;
				max-height: auto;
				overflow: hidden;
				border: 1px solid #EDEDED;
			}
			
			.Tree {
				padding: 0;
				margin: 0;
			}
			
			.Tree li {
				list-style: none;
				margin: 3px;
				padding: 3px;
				font-size: 11px;
			}
			.progress-child{transition:0.5s}
			.vl {
			  	border-left: 1px solid #dee2e6;
			  	height: 430px;
			}
			.tourbtn{
				position: absolute;
		        right: 20px;
		        top: 7px;
			}		
			.help-header{cursor:move;}
			
			/*Added for Data Switch*/
			.table thead th {
			    vertical-align: bottom;
			    border-bottom: 0px solid #dee2e6;
			    border-right: 1px solid #dee2e6;    
			    border-top: 0px solid #dee2e6;
			    text-align: center;
			}
			
			/*Added for Data Switch*/
			.box-bdy-row {
				margin-right: 0px!important;
   				margin-left: 0px!important;
			}
			
			/*Added for Data Switch*/
			.paddingBoxBdyCol {
				padding-right: 0px!important;
				padding-left: 0px!important;
			}
			.borderRgtBoxBdyCol {
				border-right: 1px solid #ccc!important;
			}
			
			.dsBackground{
				background: linear-gradient(150deg, #97c74e 0%, #2ab9a5 100%);
			}
			
		</style>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">
	    <!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_schema_condition.css"> -->
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_schemaEntityDesign.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/awesomplete.css">

	</head>

<body>
	<body>
	<div class="none loaderEnh" id="mainOverlay"></div>
	<% String noRecords=( String) request.getSession().getAttribute( "noRecords"); %>
		<input type="hidden" value="${selectedSystem }" id="selectedSystem" />
		<input type="hidden" value="${selectedMapping }" id="selectedMapping" />
		<input type="hidden" value="${TrRuleMap }" id="RuleMap" />
		<input type="hidden" value="${TransformationRuleAutoComplete}" id="ruleArray" />
		<input type="hidden" name="catalogArrayKey" id="catalogArrayKey" value="${catalogArrayKey}" />
		<input type="hidden" id="selectedProject" value="${selectedProject }" />
		<input type="hidden" id="srcCatalogName" value="${srcCatalogName }" />
		
		<p class="none" id="targetCols">${targetEntityJson}</p>
		<p class="none" id="sourceCols">${sourceEntityJson}</p>
		<p class="none" id="targetMap">${targetMap}</p>
		<p class="none" id="sourceMap">${sourceMap}</p>
		<p class="none" id="catalogJSON">${catalogJSON}</p>
		<p class="none" id="mappingArray">${mappingArray}</p>
		<input type="hidden" id="sourceElementsList" value="${sourceElements}" />
		<input type="hidden" id="predictionType" value="${predictionType}" />
		<p class="none" id="trfmRules">${TransformationRules}</p>
		<p class="none" id="rules">${rules}</p>
		<p class="none" id="catalogsArray">${catalogsArray}</p>
		<p class="none" id="derCols">${derivedCols}</p>
		<p class="none" id="ruleMappings">${ruleMappings}</p>
		<p class="none" id="rootCount">${rootCount}</p>
		
		<form action="<%=request.getContextPath()%>/schema-designer/${selectedProject}/saveSchemaDesign" method="POST" id="frmTgtSchemaDesign"> 
			<input type="hidden" id="tgtSchemaDesign" name="schemaDesign" value="" placeholder="Target Entity Design" />
		</form>	

	</form>
	
		<div>
			<header>
		        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
		    </header>
		</div>
		
		<section>
	        <div class="container-fluid bg-white">
	            <!-- links history label -->
	            <nav aria-label="breadcrumb" class="pt-3">
	                <ol class="breadcrumb">
	                    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home">Home</a></li>
					    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate">Migrate</a></li>
					    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate/schema-migrator/schema-designer">Schema Designer</a></li>
				  		<li class="breadcrumb-item" aria-current="page">${selectedProject}</li>
				  		<li class="breadcrumb-item active" aria-current="page">Schema Design</li>
	                </ol>
	            </nav>
	        </div>
    	</section>
    	
		<div class="row no-gutters">
			<div class="col-12">
				<form id="attributeForm" action="<%=request.getContextPath()%>/RDW/Intelligent-Mapper/system-mapping/${selectedSystem }/data-mapping/${selectedMapping }/predictiveMapping/attributeMapping/transformation-mapping" method="GET">
					
					<div class="container-fluid bg-white">
						<div class="row">
								
							<div class="col-md-8 text-right toggle-phyLog">
								<p>Physical Names <i class="fas fa-toggle-on pointer" id="toggleBtnForPhyLog"></i> Logical Names</p>
							</div>
							<div class="col-md-4">
								<!-- <button type="button" class="btn text-light bg-info ml-3 btn btn-green" id="add-row" title="Add new row">Add row</button> -->
								<button type="button" class="btn text-light bg-info ml-3 btn btn-green" id="clear-all" title="Clear all rows">Clear all</button>
								<button type="button" class="btn text-light bg-info ml-3 btn btn-green" id="save-design" title="Save entity design">Save</button>
								<button type="button" class="btn text-light bg-info ml-3 btn btn-green" id="next-attrib" title="Proceed attribute design">Next</button>
							</div>
						</div>
						<div class="row no-gutters">
							<div class="col-md-2 pr-2">
								<div class="card p-0 target-section auto-box">
									<div class="card-header bg-light text-dark dsBackground">Source Catalog
										<!-- Edited On 30 August2018 -->
										<select class="form-control form-control-sm none" id="testp">
											<c:forEach items="${TrRuleMap}" var="TransformationRule">
												<option value="${TransformationRule.key }">${TransformationRule.key }</option>
											</c:forEach>
										</select>
										<select class="form-control form-control-sm none" id="testp1">
											<c:forEach items="${SourceCatalogAttributes}" var="sourceAttributes">
												<option value="${sourceAttributes }">${sourceAttributes}</option>
											</c:forEach>
										</select>
									</div>
									<div class="card-body p-0" id="tgt-box">
										<input type="text" id="searchTargetSide" class="bg-light text-dark form-control form-control-sm border-0" placeholder="Search entities" aria-label="Search Attributes" aria-describedby="basic-addon2">
										<span class="pl-2"><i class="far fa-lightbulb text-warning"></i><small class="text-muted">&ensp;Drag the table to design the schema</small></span>
										<div class="tree well mt-3 dvSource tree well targetDiv catalog-tree" id="targetEntities-ldr">
										
											<!-- <ul id="mainCatalogContent">
			                                  <li>
			                                      <span class="text-dark pt-4 pl-2 ex-span">&nbsp;<i class="far fa-minus-square" aria-hidden="true"></i>&ensp;Catalogs </span> removed 0808 -->
			                                      
			                                      <!-- <ul id="mainCatalogContent" style="margin-top: 10px"> removed 1608 -->
			                                       <c:forEach items="${catalogArrayKey}" var="catalog" varStatus="id">
			                                          <!-- <li> removed 1608 -->
			                                                <!--  <span class="text-dark pl-2 pt-1 ex-span ov-el" >&nbsp;<i class="far fa-plus-square" aria-hidden="true"></i>
			                                                	<a class="text-dark pl-1 mt-2 ov-el" id="list-home-list${id.index}" href="#">${catalog.catalogName}</a>
			                                                </span> removed 1608-->
			                                                
			                                                <!-- To loop the specific project catalog only -->
			                                                <!-- Ignore the other catalogs -->
			                                                <%-- <c:out value="${catalog.catalogName}">${catalog.catalogName}</c:out>
			                                                <c:out value="${srcCatalogName}">${srcCatalogName}</c:out> --%>
			                                                <c:choose>			                                                	
															    <c:when test="${catalog.catalogName == srcCatalogName}">
																	 <!--  Entity Level -->
					                                                 <ul class='Tree' id="srcCatalog">
							                                      		  <c:forEach items="${catalog.getEntityLst()}" var="entityLst" varStatus="entityCnt">
									                                          <li class="parent_li">
									                                               <!-- <span class="text-dark pl-4 pt-1 ov-el tbl drag" data-entitylogname="${entityLst.getLogicalName()}" data-catalogname="${catalog.catalogName}" data-entityphyname="${entityLst.getPhysicalName()}">
									                                               		${entityLst.getPhysicalName()}
									                                               </span> -->
									                                               
										                                           <span class="text-dark pl-4 pt-1 ov-el tbl drag" style="margin-left: -30px;"
										                                           data-entitylogname="${entityLst.getLogicalName()}" 
										                                           data-catalogname="${catalog.catalogName}" 
										                                           data-entityphyname="${entityLst.getPhysicalName()}"
										                                           data-type="Entity" >
									                                               		&nbsp;<i class="far fa-minus-square" aria-hidden="true"></i>
									                                               		<a class="text-dark pl-1 mt-2 ov-el" href="#">
										                                               		<c:choose>
																							    <c:when test="${entityLst.getLogicalName() != ''}">
																									${entityLst.getLogicalName()}
																								</c:when>
																								<c:otherwise>
																									${entityLst.getPhysicalName()}
																								</c:otherwise>
																							</c:choose>
																						</a>					                                               		
									                                               </span>
									                                               
									                                               <!--  Attribute Level -->
									                                               <ul>
								                                      		  			 <c:forEach items="${entityLst.getAttrList()}" var="attributeLst" varStatus="attributeCnt">
										                                      		  			<li>
										                                      		  				<span class="text-dark pl-4 pt-1 ov-el tbl drag" style="margin-top: 10px;"
										                                      		  				data-attriblogname="${attributeLst.getLogicalName()}" 
										                                      		  				data-attribphyname="${attributeLst.getPhysicalName()}" 
										                                      		  				data-catalogname="${catalog.catalogName}"
										                                      		  				data-entitylogname="${entityLst.getLogicalName()}" 
										                                      		  				data-entityphyname="${entityLst.getPhysicalName()}"
										                                      		  				data-type="Attribute" >
										                                      		  					<a class="text-dark pl-1 mt-2 ov-el" href="#">										                                      		  					
											                                      		  					<c:choose>
																											    <c:when test="${attributeLst.getLogicalName() != ''}">
																													${attributeLst.getLogicalName()}
																												</c:when>
																												<c:otherwise>
																													${attributeLst.getPhysicalName()}
																												</c:otherwise>
																											</c:choose>						                                      		  				
										                                      		  						<!-- ${attributeLst.getLogicalName()} -->
										                                      		  					</a>
										                                      		  				</span>
										                                      		  			</li>
								                                      		  			 </c:forEach>
							                                      		  		   </ul>									                                               
								                                               							                                               
								                                               </li>
								                                            </c:forEach>
							                                          </ul>
																</c:when>
																<c:otherwise>																	
																</c:otherwise>
															</c:choose>
			                                          
			                                          <!-- </li> removed 1608-->
			                                        </c:forEach> 
			                                      <!--  </ul> removed 1608-->
			                                  <!-- </li>
			                              </ul> -->
										

										</div>
									</div>
								</div>
							</div>
							<div class="col-md-8 pr-2">
								<div class="card p-0 mapping-card auto-box">
									<div class="card-header bg-light dsBackground">
										<div class="card-body p-0">
											<table class="table table-sm table-responsive" style="overflow-x: hidden !important; margin-bottom: 0px; margin-left: -10px;" id="schematblheading">
												<thead class="bg-light text-dark dsBackground">
													<tr>
														<th style="max-width: 140px; min-width: 140px;" rowspan="3">Collection</th>
														<th style="max-width: 390px; min-width: 390px; border-bottom: 1px solid #dee2e6;" colspan="3">Embed</th>
														<th style="max-width: 75px; min-width: 75px;" rowspan="3">Embed As Ref</th>
														<!-- <th style="max-width: 140px; min-width: 140px;" rowspan="3">Reference</th> -->
														<th style="max-width: 120px; min-width: 120px;" rowspan="3">Denormalize</th>
														<th style="max-width: 120px; min-width: 120px;" rowspan="3">Subset (n Rows)</th>
													</tr>
													<tr>
														<th style="max-width: 130px; min-width: 130px;">Node</th>
														<th style="max-width: 130px; min-width: 130px;">Parent Node</th>
														<th style="max-width: 130px; min-width: 130px;">Condition</th>
														<!-- <th style="max-width: 140px; min-width: 140px;"></th>
														<th style="max-width: 140px; min-width: 140px;"></th>
														<th style="max-width: 120px; min-width: 120px;"></th> -->							
													</tr>
												</thead>
											</table>										
										</div>
									</div>
									<div class="card-body p-0 newColumn" style="overflow-x:hidden">
										<!-- Search division -->
										<div class="box-head">
											<!-- <div class="row">
												<div class="col borderCol bg-light" style='display: none;'>
													<input type="text" class="bg-light text-dark form-control form-control-sm border-0" id="searchTargetAttr" onkeyup="getSearchedRow()" placeholder="Search target entities" aria-label="Search Attributes" aria-describedby="basic-addon2">
												</div>
												<div class="col-md-auto borderCol" style="padding: 0.75rem !important"></div>
												<div class="col-md-4 borderCol"></div>
												<div class="col bg-light" style='display: none;'>
													<input type="text" class="bg-light text-dark form-control form-control-sm border-0" id="searchSourceAttr" placeholder="Search source entities" onkeyup="getSearchedRowSource()" aria-label="Search Attributes" aria-describedby="basic-addon2">
												</div>
											</div> -->
										</div>
										<div id="box-body"></div>
										<div class="emptybox newTargetCol0" id="newTargetCol0" style="min-height: 30px;">
											
											<div class="box" style="display: none">
											    <div class="row box-bdy-row emptybox-row" id="emty-15976829251111-0">
											    
											        <div class="col borderCol emtyTargetdiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable root-size" id="rnd-15976829251111-0">
											            <ul class="Tree" id="emtyTargetDrop-0">
											                <li id="targetAttrLi-0" title="">
											                    <span class="ui-draggable ui-draggable-handle">
											                        
											                    </span>
											                    <span class="pl-2 ui-draggable ui-draggable-handle">
											                        
											                    </span>
											                </li>
											            </ul>
											        </div>
											        
											       
											        <div class="col borderCol emtyNodeDiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable embed-size" id="end-15976829251111-0">
											            <ul class="Tree w-100" id="emtyNodeDrop-0-0">						
											            </ul>
											        </div>
											        <div class="col borderCol emtyParentNodeDiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="epnd-15976829251111-0">
																									
											        </div>
											        <div class="col borderCol emtyConditionDiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="cond-15976829251111-0">
											            <span class="ed-cond-span" id="conditionSpanDrop-0-0" style="float: right;">
											            </span>
											            <ul class="Tree w-100" id="conditionDrop-0-0" style="margin-top: 4px;">						
											            </ul>								
											        </div>
											        
											        <div class="col borderCol emtyEmbedRefDiv paddingBoxBdyCol borderRgtBoxBdyCol embedRef-size" id="eref-15976829251111-0">
											        </div>
											
											        <div class="col borderCol emtyDenormalizeDiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable denormalize-size" id="denorm-15976829251111-0">
											            <ul class="Tree w-100" id="emtyDenormalizeDrop-0-0">											                
											            </ul>
											        </div>							
											        
											        <div class="col borderCol emtySubsetDiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable subset-size" id="sub-15976829251111-0">
											            <ul class="Tree w-100" id="emtySubsetDrop-0-0">											                
											            </ul>
											        </div>
											
											    </div>
											</div>										
										
										</div>
										<div class="emptybox newTargetCol" id="newTargetCol1" style="min-height: 30px;"></div>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="card p-0 source-section auto-box">
									<div class="card-header bg-light text-dark dsBackground">Target Catalog
										<!-- <span class="pl-3 pointer" style="margin-left:35%;" id="source-modal-add" title="Add Target manually"><i class="fas fa-ellipsis-h"></i></span> -->
									</div>
									<div class="card-body p-0" id="src-box">
										<input type="text" id="searchSourceSide" class="bg-light text-dark form-control form-control-sm border-0" placeholder="Search entities" aria-label="Search Attributes" aria-describedby="basic-addon2">
										<div class="tree well mt-2 catalog-tree" id="sourceEntities-ldr">
											<ul class="" id='fnlTgtCatalog'>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</form>
			</div>
		</div>
		
		<div id="conditionModal"></div>
		<!-- <div class="modal" id="conditionModal" tabindex="-1" role="dialog" aria-labelledby="conditionModal" aria-hidden="true" style="display: none">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" style="max-width:800px">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Target Column Selection Modal</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <table class="table table-striped table-bordered table-sm">
		        	<thead>
		        		<tr>
		        			<th>
			        			<div class="custom-control custom-checkbox">
								  <input type="checkbox" class="custom-control-input" id="sel-all-tgt-col" checked>
								  <label class="custom-control-label" for="sel-all-tgt-col">Select</label>
								</div>
							</th>
		        			<th>Target Columns</th>
		        			<th>Source Columns</th>
		        		</tr>
		        	</thead>
		        	<tbody id="selectionTableBody"></tbody>
		        </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-info btn-sm" id="save-target-sel">Save</button>
		      </div>
		    </div>
		  </div>
		<div> -->
	
		
		<div class="modal fade" id="targetSelectionModal" tabindex="-1" role="dialog" aria-labelledby="targetSelectionModal" aria-hidden="true">
		  <div class="modal-dialog modal-lg modal-dialog-scrollable" style="max-width:800px">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Target Column Selection Modal</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <table class="table table-striped table-bordered table-sm">
		        	<thead>
		        		<tr>
		        			<th>
			        			<div class="custom-control custom-checkbox">
								  <input type="checkbox" class="custom-control-input" id="sel-all-tgt-col" checked>
								  <label class="custom-control-label" for="sel-all-tgt-col">Select</label>
								</div>
							</th>
		        			<th>Target Columns</th>
		        			<th>Source Columns</th>
		        		</tr>
		        	</thead>
		        	<tbody id="selectionTableBody"></tbody>
		        </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-info btn-sm" id="save-target-sel">Save</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<input type="hidden" id="sourceCatalogName" value="${sourceCatalogName }" />
		<input type="hidden" id="targetCatalogName" value="${targetCatalogName }" />
		<%-- <div class="modal fade" id="workflow" tabindex="-1" role="dialog" aria-labelledby="workflow" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header bg-custom">
						<h5 class="modal-title" id="exampleModalCenterTitle">Select Workflow</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-3">
								<label>Select Workflow</label>
							</div>
							<div class="col-md-7">
								<select class="form-control form-control-sm" id="workflowSelect">
									<c:forEach items="${WrkflwfolderNames}" var="entry" varStatus="id">
										<option value="${entry }">${entry }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-custom btn-sm" onclick="workflowSaved()">Save</button>
						<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div> --%>
		
		<div class="modal fade" id="deleteEntityModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="deleteEntityModalTitle"><b>Do you want to Delete?</b></h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		      	<input type="hidden" id="idSelected" />
		      	<input type="hidden" id="catType" />
		        Data will be Lost
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" id="deleteConfirmation">Yes</button>
		        <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		<!-- Modal for confirming lookup Source change -->
		<div class="modal fade" id="lookupSrcChange" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <!-- <div class="modal-header">
		        <h5 class="modal-title" id="deleteEntityModalTitle"><b>Do you want to Delete?</b></h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div> -->
		      <div class="modal-body" style="font-weight:600;">
		        Changing the Lookup Source will reset the condition. Do you wish to proceed?
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" id="lookupConfirmation">Yes</button>
		        <button type="button" class="btn btn-primary" id="lookupConfNoChng">No</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="userDefined" tabindex="-1" role="dialog" aria-labelledby="workflow" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header bg-info">
						<h5 class="modal-title text-light">Add Derived Attributes</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">	<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="derivedAttr">Derived Attribute</label>
							<input type="text" class="form-control form-control-sm" id="derivedAttr" placeholder="Enter column name" />
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info btn-sm" id="derivedVariablesModal">Save</button>
						<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!-- User Defined Variables Model -->
		<div id="addUDFConsole" class="console p-0 card-shadow bg-white console-h" >
			<div class="row bg-info">
				<div class="col-md-11 mt-0">	
					<a class="con-sm" id="consoleheading"><strong>Apply Transformation Rules</strong></a>
				</div>
				<div class="col-md-1 text-right">
					<a href="javascript:void(0)" class="text-white pt-2 pr-2 close-rule-console" style="font-size:20px">&times;</a>
				</div>
			</div>
			
			<div class="container">
				<div class="console-output">
					<div class = "row">
		      			<div class="col-md-12">
			 				<div class="applyRule"></div>
			 				
			 			</div>
			 			<!-- <div class="vl"></div>
			 			<div class="col-md-4">
			 				<div class="applyRule1"></div>
						</div> -->
			 		</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="addAttributeModal" tabindex="-1" role="dialog" aria-labelledby="confirmModal" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header bg-info dsBackground">
		        <h5 class="modal-title text-light" id="exampleModalLongTitle">Select Entity</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <div class="row nopadding">
		        	<div class="col-4">
		        		<div class="form-group">
						    <label for="catalog">Select Catalog</label>
						    <select class="form-control form-control-sm" id="catalog"></select>
						 </div>
					</div>
					<div class="col-4">
						 <div class="form-group">
						    <label for="entity">Select Entity</label>
						    <select class="form-control form-control-sm" id="entity"></select>
						 </div>
					</div>
		        </div>
		      </div>
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-info btn-sm btn btn-green" id="saveBtnNewEnt">Save</button>
		        <button type="button" class="btn btn-danger btn-sm btn btn-green" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="help-content none" style="position:absolute;top:10px;left:10px;z-index:2000;height:75vh;width:40%">
			<div class="card h-100">
				<div class="card-header bg-info">
					<h6>Tips</h6>
				</div>
				<div class="card-body">
					<div class='row'>
						<div class="col-2">
							<p>Sidebar</p>
						</div>
						<div class="col-10">
							<p>Main</p>
						</div>
					</div>
				</div>
			</div>
		</div>         
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/dependency/sweetAlert.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/dependency/awesomplete.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/schemaEntityDesign.js"></script>
		
<input type=hidden id="defaultadteformat" name="defaultadteformat" value="${defaultadteformat}"/></body>
</html>