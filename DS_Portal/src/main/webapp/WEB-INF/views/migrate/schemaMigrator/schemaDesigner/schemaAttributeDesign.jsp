<!DOCTYPE html>
<html>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		</style>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_common.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/ds_style.css">
	</head>

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
		
		<p class="none" id="entityCnt"></p>
		
		<div>
			<header>
		        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
		    </header>
		</div>
		
		<section>
	        <div class="container" style="padding-left: 0px; max-width: 100%;">
	            <!-- links history label -->
	            <nav aria-label="breadcrumb" class="pt-3">
	                <ol class="breadcrumb">
	                	<li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home">Home</a></li>
					    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate">Migrate</a></li>
					    <li class="breadcrumb-item"><a class="page-nav-clr" href="<%=request.getContextPath()%>/home/migrate/schema-migrator/schema-designer">Schema Designer</a></li>
				  		<li class="breadcrumb-item aria-current="page">${selectedProject}</li>
				  		<li class="breadcrumb-item aria-current="page">Attribute Design</li>
	                </ol>
	            </nav>
	        </div>
    	</section>
    	
		<div class="row no-gutters">
			<div class="col-12 mainContent">
				<!--
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb bg-white">
						<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/home" class="text-info">Home</a>
						</li>
						<%-- <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/home?slide=manage" class="text-info">Manage and Govern</a>
						</li> --%>
						<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>${currentwbURL}?workbench=${currentwb}" class="text-info">${currentwb}</a>
						<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/RDW/Intelligent-Mapper" class="text-info">Intelligent Mapper</a>
						</li>
						<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/RDW/Intelligent-Mapper/system-mapping" class="text-info">Mapping groups</a>
						</li>
						<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/RDW/Intelligent-Mapper/system-mapping/${selectedSystem}" class="text-info">Data Mapping</a>
						</li>
						<li class="breadcrumb-item active" aria-current="page">Attribute Mapping</li>
						<button type="button" class="btn btn-sm text-light bg-info text-right tourbtn btn btn-green" style="margin-left:42%; margin-top: 80px; margin-right: 45px;" onclick="introProfile.start();">Tour</button>
					</ol>					
				</nav>
				-->
				<form id="attributeForm" action="<%=request.getContextPath()%>/RDW/Intelligent-Mapper/system-mapping/${selectedSystem }/data-mapping/${selectedMapping }/predictiveMapping/attributeMapping/transformation-mapping" method="GET">
					
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-2">
								<p class="text-info" style="color: #6c757d!important;">	<span><i class="fa fa-columns text-info"></i>&ensp;
									Attribute Design <!-- <i class="far fa-lightbulb text-warning pl-2 attrib-help" data-toggle="modal" data-target="#helpModal" title="Help"></i> --></span>
								</p>
							</div>
							<div class="col-md-3 derived-div">
								<button type="button" class="btn btn-sm text-light bg-info mt-1 nav-link btn btn-green" data-toggle="modal" data-target="#userDefined"><i class="fa fa-plus-square"></i>&ensp;Derived Attributes</button>	<span class="text-muted">(User Defined Rules)</span>
							</div>
							<div class="col-md-3 text-center">
								<!--  <h5 class="text-muted pt-1">${selectedMapping} (${selectedProject})</h5> -->
								<h5 class="text-muted pt-1"><span id="schemaSrcRef">${selectedProject}</span></h5>
							</div>
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-7 toggle-phyLog">
										<p style="font-size:115%;">Physical Names <i class="fas fa-toggle-on pointer" id="toggleBtnForPhyLog"></i> Logical Names</p>
									</div>
									<div class="col-md-5">
										<button type="button" class="btn btn-sm text-light bg-info ml-3 btn btn-green" id="saveBtn" disabled>Save</button>
										<button type="button" class="btn btn-sm text-light bg-info ml-3 btn btn-green" id="nextBtn" title="Submit" disabled>Submit</button>
									</div>
									<%-- <img id="loaderDDL" src="<%=request.getContextPath()%>/resources/images/loader.gif" height="50" width="50" style="display: none;"> --%>
								</div>
							</div>
						</div>
						<div class="row no-gutters">
							<div class="col-md-2 pr-2">
								<div class="card p-0 target-section">
									<div class="card-header bg-info" style="background: linear-gradient(150deg, #97c74e 0%, #2ab9a5 100%);">Target Entities
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
										<!-- Edited On 30 August2018 -->
										<span class="pl-3 pointer" style="margin-left:35%;" id="target-modal-add" title="Add Target manually"><i class="fas fa-ellipsis-h"></i></span>
									</div>
									<div class="card-body auto-box p-0" id="tgt-box">
										<input type="text" id="searchTargetSide" class="bg-light text-dark form-control form-control-sm border-0" placeholder="Search attributes" aria-label="Search Attributes" aria-describedby="basic-addon2">
										<span class="pl-2"><i class="far fa-lightbulb text-warning"></i><small class="text-muted">&ensp;Drag the table to map all the columns</small></span>
										<div class="tree well mt-2" id="targetEntities-ldr">
										
											<ul class="Tree" id='fnlTgtCatalog'>
											</ul>
											<!--  <ul class="Tree">
												<li class="parent_li">
													
													<div class="progress progress-child bg-secondary opless">
													  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
													</div>
													<ul>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped  progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
														<div class="progress mt-3 progress-child bg-secondary opless">
														  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
														</div>
													</li>
													<li>
														<div class="progress mt-3 progress-child bg-secondary opless">
														  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
														</div>
													</li>
													<li>
														<div class="progress mt-3 progress-child bg-secondary opless">
														  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
														</div>
													</li>
													</ul>
												</li>
											</ul> -->
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-8 pr-2">
								<div class="card mapping-card">
									<div class="card-header bg-info" style="background: linear-gradient(150deg, #97c74e 0%, #2ab9a5 100%);">
										<div class="row">
											<div class="col text-left">Target Attributes</div>
											<div class="col-md-auto">Rule</div>
											<div class="col-md-4 text-center">Transformation Rules</div>
											<div class="col text-center">Source Attributes</div>
											<div class="col-md-1 text-center"><i class="fas fa-plus-square fa-lg pointer" id="add-row"></i></div>
										</div>
									</div>
									<div class="card-body auto-box p-0 newColumn" style="overflow-x:hidden">
										<!-- Search division -->
										<div class="box-head">
											<div class="row">
												<div class="col borderCol bg-light">
													<input type="text" class="bg-light text-dark form-control form-control-sm border-0" id="searchTargetAttr" onkeyup="getSearchedRow()" placeholder="Search target attributes" aria-label="Search Attributes" aria-describedby="basic-addon2">
												</div>
												<div class="col-md-auto borderCol" style="padding: 0.75rem !important"></div>
												<div class="col-md-4 borderCol"></div>
												<div class="col bg-light">
													<input type="text" class="bg-light text-dark form-control form-control-sm border-0" id="searchSourceAttr" placeholder="Search source attributes" onkeyup="getSearchedRowSource()" aria-label="Search Attributes" aria-describedby="basic-addon2">
												</div>
											</div>
										</div>
										<div id="box-body"></div>
										<div class="emptybox newTargetCol" id="newTargetCol0" style="min-height: 30px;"></div>
										<div class="emptybox newTargetCol" id="newTargetCol1" style="min-height: 30px;"></div>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="card p-0 source-section">
									<div class="card-header bg-info" style="background: linear-gradient(150deg, #97c74e 0%, #2ab9a5 100%);">Source Entities
									<span class="pl-3 pointer" style="margin-left:35%;" id="source-modal-add" title="Add Source manually"><i class="fas fa-ellipsis-h"></i></span>
									</div>
									<div class="card-body auto-box p-0" id="src-box">
										<input type="text" id="searchSourceSide" class="bg-light text-dark form-control form-control-sm border-0" placeholder="Search attributes" aria-label="Search Attributes" aria-describedby="basic-addon2">
										
										<c:set var="count" value="0" scope="page" />
										<!--  Catalog Loop --> 
										<c:forEach items="${catalogArrayKey}" var="catalogLst" varStatus="id">		
											<c:if test="${catalogLst.catalogName eq srcCatalogName }">
													<c:forEach items="${catalogLst.getEntityLst()}" var="entityLst" varStatus="entityCnt">
														<c:set var="count" value="${count + 1}" scope="page"/>
														
														<div class="tree well mt-2 sourceDiv catalog-tree"
															data-catalogname="${catalogLst.catalogName}"
															data-entityphyname="${entityLst.getPhysicalName()}"
															data-entitylogname="${entityLst.getLogicalName()}"
															data-elementname="Source ${count}"													
															data-componentname="${entityLst.getPhysicalName()}"
															data-type="Entity"
															id="sourceEntities${count}">
															<ul class="Tree">
		       													<li class="parent_li">
		       														<span class="text-dark pl-2 ov-el" 
		       														oncontextmenu="openModal('${count}','Source')"      														
		        													data-catalogname="${catalogLst.catalogName}"
																	data-entityphyname="${entityLst.getPhysicalName()}"
																	data-entitylogname="${entityLst.getLogicalName()}"
																	data-elementname="Source ${count}"											
																	data-componentname="${entityLst.getPhysicalName()}"
																	data-type="Entity"       														
		       														title="${catalogLst.catalogName}">
									        							&nbsp;<i class="far fa-plus-square" aria-hidden="true"></i>
									        							<c:choose>
																		    <c:when test="${entityLst.getLogicalName() != ''}">
																				${entityLst.getLogicalName()} (${catalogLst.catalogName})
																			</c:when>
																			<c:otherwise>
																				${entityLst.getPhysicalName()} (${catalogLst.catalogName})
																			</c:otherwise>
																		</c:choose>
																					
									        							<!-- <a href="#" title="${catalogLst.catalogName}" class="text-dark pl-1 smfont ov-el" id="sourceEntityName${count}">${entityLst.getLogicalName()} (${catalogLst.catalogName})</a> -->
									        						</span>
									        						
									        						<!--  Attribute Loop -->
					                                               	<ul>
				                                      		  			 <c:forEach items="${entityLst.getAttrList()}" var="attributeLst" varStatus="attributeCnt">
					                                      		  			<li data-attribphyname="${attributeLst.getPhysicalName()}" data-attriblogname="${attributeLst.getLogicalName()}" >
					                                      		  				<span class="text-dark pl-4 pt-1 ov-el tbl drag" style="margin-top: 10px;"
					                                      		  				title="<c:choose>
																						    <c:when test="${attributeLst.getLogicalName() != ''}">
																								${attributeLst.getPhysicalName()} (${entityLst.getPhysicalName()})
																							</c:when>
																							<c:otherwise>
																								${attributeLst.getLogicalName()} (${entityLst.getPhysicalName()})
																							</c:otherwise>
																						</c:choose>"
					                                       		  				data-attriblogname="${attributeLst.getLogicalName()}" 
					                                      		  				data-attribphyname="${attributeLst.getPhysicalName()}" 
					                                      		  				data-catalogname="${catalogLst.catalogName}"
					                                      		  				data-entitylogname="${entityLst.getLogicalName()}" 
					                                      		  				data-entityphyname="${entityLst.getPhysicalName()}"
					                                      		  				data-type="Attribute" >								                                      		  				
					                                      		  					${attributeLst.getLogicalName()}
					                                      		  				</span>
					                                      		  			</li>
				                                      		  			 </c:forEach>
			                                      		  		   	</ul>									        						
									        						
									        					</li>
									        				</ul>
														</div>
													</c:forEach>
												</c:if>
											
										</c:forEach>
										
										<div class="tree well mt-2" id="sourceEntities-ldr">									
											 
											<!-- <ul id="mainCatalogContent">
			                                  <li>
			                                      <span class="text-dark pt-4 pl-2 ex-span">&nbsp;<i class="far fa-minus-square" aria-hidden="true"></i>&ensp;Catalogs </span> -->
			                                      
			                                      <!-- <ul id="mainCatalogContent">
			                                       <c:forEach items="${catalogArrayKey}" var="catalog" varStatus="id">
			                                          <li>
			                                                <span class="text-dark pl-2 pt-1 ex-span ov-el" >&nbsp;<i class="far fa-plus-square" aria-hidden="true"></i>
			                                                	<a class="text-dark pl-1 mt-2 ov-el" id="list-home-list${id.index}" href="#">${catalog.catalogName}</a>
			                                                </span>

			                                                 <ul class='Tree'>
					                                      		  <c:forEach items="${catalog.getEntityLst()}" var="entityLst" varStatus="entityCnt">
							                                          <li class="parent_li">

								                                           <span class="text-dark pl-4 pt-1 ov-el tbl drag" 
								                                           data-entitylogname="${entityLst.getLogicalName()}" 
								                                           data-catalogname="${catalog.catalogName}" 
								                                           data-entityphyname="${entityLst.getPhysicalName()}"
								                                           data-type="Entity" >
							                                               		&nbsp;<i class="far fa-plus-square" aria-hidden="true"></i>
							                                               		<c:choose>
																				    <c:when test="${entityLst.getLogicalName() != ''}">
																						${entityLst.getLogicalName()}
																					</c:when>
																					<c:otherwise>
																						${entityLst.getPhysicalName()}
																					</c:otherwise>
																				</c:choose>                   		
							                                               </span>
							                                               
							                                               <ul>
						                                      		  			 <c:forEach items="${entityLst.getAttrList()}" var="attributeLst" varStatus="attributeCnt">
								                                      		  			<li>
								                                      		  				<span class="text-dark pl-4 pt-1 ov-el tbl drag" 
								                                      		  				title="<c:choose>
																									    <c:when test="${attributeLst.getLogicalName() != ''}">
																											${attributeLst.getPhysicalName()} (${entityLst.getPhysicalName()})
																										</c:when>
																										<c:otherwise>
																											${attributeLst.getLogicalName()} (${entityLst.getPhysicalName()})
																										</c:otherwise>
																									</c:choose>"
								                                       		  				data-attriblogname="${attributeLst.getLogicalName()}" 
								                                      		  				data-attribphyname="${attributeLst.getPhysicalName()}" 
								                                      		  				data-catalogname="${catalog.catalogName}"
								                                      		  				data-entitylogname="${entityLst.getLogicalName()}" 
								                                      		  				data-entityphyname="${entityLst.getPhysicalName()}"
								                                      		  				data-type="Attribute" >								                                      		  				
								                                      		  					${attributeLst.getLogicalName()}
								                                      		  				</span>
								                                      		  			</li>
						                                      		  			 </c:forEach>
					                                      		  		   </ul>									                                               
						                                               							                                               
						                                               </li>
						                                            </c:forEach>
					                                          </ul>					                                               
															
			                                          </li>
			                                        </c:forEach> 
			                                      </ul> -->
			                                  <!--  </li>
			                              </ul> -->
			                              
			                              
			                              
			                              
			                              
			                              
			                              
			                              
											<!-- <ul class="Tree">
												<li class="parent_li">
													
													<div class="progress progress-child bg-secondary opless">
													  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
													</div>
													<ul>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
															<div class="progress mt-3 progress-child bg-secondary opless">
															  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
															</div>
														</li>
														<li>
														<div class="progress mt-3 progress-child bg-secondary opless">
														  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
														</div>
													</li>
													<li>
														<div class="progress mt-3 progress-child bg-secondary opless">
														  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
														</div>
													</li>
													<li>
														<div class="progress mt-3 progress-child bg-secondary opless">
														  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
														</div>
													</li>
													</ul>
												</li>
											</ul> -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</form>
			</div>
		</div>
		
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
		      <div class="modal-header bg-info" style="background: linear-gradient(150deg, #97c74e 0%, #2ab9a5 100%);">
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
		<script src="<%=request.getContextPath()%>/resources/js/dependency/sweetAlert.js"></script>
		<script type="module" src="<%=request.getContextPath()%>/resources/js/dependency/lodash_min.js"></script>
		<script type="module" src="<%=request.getContextPath()%>/resources/js/schemaAttributeDesign.js"></script>
		
<input type=hidden id="defaultadteformat" name="defaultadteformat" value="${defaultadteformat}"/></body>
</html>