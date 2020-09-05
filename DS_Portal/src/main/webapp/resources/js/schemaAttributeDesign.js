import {getRuleSyntaxFromRule,getRuleDetailsOnApply,toggleInput,addNewParamForConcat,removeParamForConcat} from './rule-transformation.js';
//attaching event handlers to the page
let rootCount = $("#rootCount").text();

let sourceCnt = '';
//const targetCnt = rootArray[1].split("=")[1];
//const filterCnt = rootArray[2].split("=")[1];
//const joinerCnt = rootArray[3].split("=")[1];
//const unionCnt = rootArray[4].split("=")[1];
//const lookupCnt = rootArray[5]!=undefined ? rootArray[5].split("=")[1] : '0';
//const sorterCnt = rootArray[6]!=undefined ? rootArray[6].split("=")[1] : '0';

if(rootCount !=""){
	let rootArray = rootCount.split(",");
	console.log(rootArray);
	sourceCnt = rootArray[0].split("=")[1];
	const targetCnt = rootArray[1].split("=")[1];
	const filterCnt = rootArray[2].split("=")[1];
	const joinerCnt = rootArray[3].split("=")[1];
	const unionCnt = rootArray[4].split("=")[1];
	const lookupCnt = rootArray[5]!=undefined ? rootArray[5].split("=")[1] : '0';
	const sorterCnt = rootArray[6]!=undefined ? rootArray[6].split("=")[1] : '0';
}else{
	sourceCnt = '0';
	const targetCnt = '0';
	const filterCnt = '0';
	const joinerCnt = '0';
	const unionCnt = '0';
	const lookupCnt = '0';
	const sorterCnt = '0';
}

document.querySelector('.close-rule-console').addEventListener('click', function(){
	closeConsole();
});

$(document).on('click','#source-modal-add',function(e){
	showModal('Source');
});
$(document).on('click','#target-modal-add',function(e){
	showModal('Target');
});
$(document).on('click','#add-row',function(e){
	addNewEmptyRow();
});
$(document).on('click','#saveBtn',function(e){
	generateExcelSheet('save');
});
$(document).on('click','#nextBtn',function(e){
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let csrf_name = $('meta[name="_csrf_parameter_name"]').attr('content');
	let selectedProject = $('#selectedProject').val();
	let form = document.createElement('form');
	form.action = ctx+'/home/migrate/schema-migrator/schema-designer';
	form.method = 'GET';
	form.innerHTML = `<input type="hidden" name="selectedProject" value="${selectedProject}">`;
	form.innerHTML += `<input type="hidden" name="status" value="mapped">`;
	form.innerHTML += `<input type="hidden" name="${csrf_name}"value="${csrf_token}" />`;
	// the form must be in the document to submit it
	document.body.append(form);
	
	form.submit();
});
$(document).on('click','#derivedVariablesModal',function(e){
	addUserDefinedVariables();
});
$(document).on('change','#catalog',function(e){
	updateEntityForCatalog();
});
let sourceSearch = document.querySelector('#searchSourceSide');
sourceSearch.addEventListener('keyup', searchAttributesSide, false);
sourceSearch.param1 = 'source';

let targetSearch = document.querySelector('#searchTargetSide');
targetSearch.addEventListener('keyup', searchAttributesSide, false);
targetSearch.param1 = 'target';

let sourceSearchCenter = document.querySelector('#searchSourceAttr');
sourceSearchCenter.addEventListener('keyup', getSearchedRow, false);
sourceSearchCenter.param1 = 'source';

let targetSearchCenter = document.querySelector('#searchTargetAttr');
targetSearchCenter.addEventListener('keyup', getSearchedRow, false);
targetSearchCenter.param1 = 'target';


const t0 = performance.now();
let srcjsonObject = '';
const catalogsArray = document.querySelector('#catalogsArray').textContent;
const catalogJSONArray = document.querySelector('#catalogJSON').textContent;
function onlyUnique(value, index, self) { 
    return self.indexOf(value) === index;
}
let changeCheck= false;
//let oldData = JSON.parse(catalogJSONArray); //Removed for Data Switch
//console.log(oldData);
const catalogObj = JSON.parse(catalogsArray);
//console.log(catalogObj);
var awesompleteVar = '';
const updateEntityForCatalog = () => {
	console.log('2222222222');
	let toggleValueModal='';
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValueModal = "Physical";
	}else{
		toggleValueModal = "Logical";
	}
	let catalogName = $('#catalog').find('option:selected').text();
	let enOptions = '';
	for(let i = 0,len = catalogObj.length; i < len; i++){
		let obj = catalogObj[i];
		//var entityName = obj.entityName.split(',');
		if(obj.catalogName === catalogName){
			if(toggleValueModal=="Physical")
				enOptions += `<option value="${obj.entityName}" data-entityphyname="${obj.entityName}" data-entitylogname="${obj.entityLogName}" data-componentname="${obj.componentName}">${obj.entityName}</option>`;
			else
				enOptions += `<option value="${obj.entityLogName}" data-entityphyname="${obj.entityName}" data-entitylogname="${obj.entityLogName}" data-componentname="${obj.componentName}">${obj.entityLogName}</option>`;
		}
	}
	$('#entity').empty().append(enOptions);
}

const showModal = (type) => {
	let options = '';
	let catalogArray = [], unique = [];
	for(let i = 0,len = catalogObj.length; i < len; i++){
		let obj = catalogObj[i];
		catalogArray.push(obj.catalogName);
		unique = catalogArray.filter( onlyUnique );
	}
	for(let catalog of unique){
		options += `<option value="${catalog}">${catalog}</option>`
	}
	$('#catalog').empty().append(options);
	updateEntityForCatalog();
	$('#addAttributeModal').modal({
	  keyboard: false
	});
	$('#saveBtnNewEnt').off('click').on('click',function(e){
		saveData(type);
	});
}
function getUniqueCompName(initialID,selComponentName,componentArray,catalogName,entityName,type){
	console.log(componentArray);
	if(componentArray.includes(selComponentName+initialID)){
		initialID = initialID+1;
		getUniqueCompName(initialID,selComponentName,componentArray,catalogName,entityName,type);
	}else{
		console.log('compCnt---> '+initialID);
		addEntityToAttributePage(initialID,catalogName,entityName,type);
	}
}

function addEntityToAttributePage(initialID,catalogName,entityName,type){
	console.log('adding unique entity to attribute page'+initialID);
	let toggleValue = '';
	let selSystem = $('#selectedSystem').val();
	let selMapping = $('#selectedMapping').val();
	let url = `${ctx}/RDW/Intelligent-Mapper/system-mapping/${selSystem}/data-mapping/${selMapping}/predictive-mapping/attribute-mapping/addEntity`;
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValue = "Physical";
	}else{
		toggleValue = "Logical";
	}
	let attributeListLi = '';
	let entityNameFromJson='';
	let entityPhyName='';
	let entityLogName='';
	let phyComponentName='';
	console.log(catalogObj);
	for(let i=0;i<catalogObj.length;i++){
		if(toggleValue=="Physical"){
			if(catalogObj[i]['catalogName']==catalogName && catalogObj[i]['entityName']==entityName){
				entityNameFromJson = catalogObj[i]['entityName'];
				entityPhyName = catalogObj[i]['entityName'];
				entityLogName = catalogObj[i]['entityLogName'];
				let compName = entityPhyName+initialID;
				for(let j=0;j<catalogObj[i]['attributeList'].length;j++){
					let attributeName = catalogObj[i]['attributeList'][j];
					attributeListLi = 
						attributeListLi + 
						`<li data-attribphyname="${attributeName.split('::')[1]}" data-attriblogname="${attributeName.split('::')[0]}">
						 	<span title="${attributeName.split('::')[0]} (${entityLogName})" class="pl-4 pt-2 smfont tbl ui-draggable ui-draggable-handle">
			                	<a href="#" class="text-dark">${attributeName.split('::')[1]} (${compName})</a>
			            	</span>
						</li>`;
				}
			}
		}else{
			if(catalogObj[i]['catalogName']==catalogName && catalogObj[i]['entityLogName']==entityName){
				entityNameFromJson = catalogObj[i]['entityName'];
				entityPhyName = catalogObj[i]['entityName'];
				entityLogName = catalogObj[i]['entityLogName'];
				for(let j=0;j<catalogObj[i]['attributeList'].length;j++){
					let attributeName = catalogObj[i]['attributeList'][j];
					attributeListLi = 
						attributeListLi + 
						`<li data-attribphyname="${attributeName.split('::')[1]}" data-attriblogname="${attributeName.split('::')[0]}">
						 	<span title="${attributeName.split('::')[1]} (${entityPhyName})" class="pl-4 pt-2 smfont tbl ui-draggable ui-draggable-handle">
			                	<a href="#" class="text-dark">${attributeName.split('::')[0]} (${entityLogName})</a>
			            	</span>
						</li>`;
				}
			}
		}
	}
	phyComponentName = entityPhyName+initialID;
	if(type=="Source"){
//		$.ajax({
//			url:url,
//			type:'post',
//			data:{"catalogType":type,"catalogName":catalogName, "entityName":entityName, "componentName" : phyComponentName},
//			success:function(data){
//				$('#rootCount').text(data);
				changeCheck=true;
				let id = $('#src-box').find('div.sourceDiv').length;
				let div='';
				let latestElName = $('#src-box').find('div.sourceDiv:last').data('elementname');
				let elName = latestElName.substring(latestElName.indexOf(' '),latestElName.length).trim();
				elName = +elName+1;
				let elementName = 'Source '+elName;
				if(toggleValue=="Physical"){
					div = 
						`<div class="tree well catalog-tree newMember sourceDiv mt-2" data-catalogname="${catalogName}" data-elementname="${elementName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-componentname="${phyComponentName}" id="sourceEntities${id}">
							<ul id="sourceEntities_ul${id}" class="Tree">
								<li>
									<span class="text-dark pl-2 ov-el ui-draggable ui-draggable-handle Source" oncontextmenu="openModal('${id}','Source')" title="${entityLogName} (${catalogName})"><i class="far fa-minus-square" aria-hidden="true"></i>
										<a class="text-dark pl-1 smfont ov-el" title="${catalogName}" id="sourceEntityName${id}" href="#">${phyComponentName} (${catalogName})</a>
									</span>
									<ul>
										${attributeListLi}
									</ul>
								</li>
							</ul>
						</div>`;
				}else{
					div = 
						`<div class="tree well catalog-tree newMember sourceDiv mt-2" data-catalogname="${catalogName}" data-elementname="${elementName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-componentname="${phyComponentName}" id="sourceEntities${id}">
							<ul id="sourceEntities_ul${id}" class="Tree">
								<li>
									<span class="text-dark pl-2 ov-el ui-draggable ui-draggable-handle Source" oncontextmenu="openModal('${id}','Source')" title="${entityPhyName} (${catalogName})"><i class="far fa-minus-square" aria-hidden="true"></i>
										<a class=" text-dark pl-1 smfont ov-el" title="${catalogName}" id="sourceEntityName${id}" href="#">${entityLogName} (${catalogName})</a>
									</span>
									<ul>
										${attributeListLi}
									</ul>
								</li>
							</ul>
						</div>`;
				}
				$('#src-box').append(div);
				
				$('#sourceEntities'+id+' li:has(ul)').addClass('parent_li');
				$('#sourceEntities'+id+' li.parent_li > span').on('click', function(e) {
				    var children = $(this).parent('li.parent_li').find(' > ul > li');
				    if (children.is(":visible")) {
				        children.hide('fast');
				        $(this).find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
				    } else {
				        children.show('fast');
				        $(this).find(' > i').addClass('fa-minus-square').removeClass('fa-plus-square');
				    }
				    e.stopPropagation();
				});
				allowDrag();
//			}
//		});
		
	}else if(type=='Target'){
//		$.ajax({
//			url:url,
//			type:'post',
//			data:{"catalogType":type,"catalogName":catalogName, "entityName":entityName, "componentName" : phyComponentName},
//			success:function(data){
//				$('#rootCount').text(data);
				changeCheck=true;
				let id = $('#tgt-box').find('div.targetDiv').length;
				let div = '';
				let latestElName = $('#tgt-box').find('div.targetDiv:last').data('elementname');
				let elName = latestElName.substring(latestElName.indexOf(' '),latestElName.length).trim();
				elName = +elName+1;
				let elementName = 'Target '+elName;
				if(toggleValue=="Physical"){
					div = 
						`<div class="tree well targetDiv catalog-tree newMember mt-2" data-catalogname="${catalogName}" data-elementname="${elementName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-componentname="${phyComponentName}" id="targetEntities${id}">
							<ul id="targetEntities_ul${id}" class="Tree">
								<li>
									<span class="text-dark pl-2 ov-el ui-draggable ui-draggable-handle Source" oncontextmenu="openModal('${id}','Target')" title="${entityLogName} (${catalogName})"><i class="far fa-minus-square" aria-hidden="true"></i>
										<a class="text-dark pl-1 smfont ov-el" title="${catalogName}" id="targetEntityName${id}" href="#">${phyComponentName} (${catalogName})</a>
									</span>
									<ul>
										${attributeListLi}
									</ul>
								</li>
							</ul>
						</div>`;
				}else{
					div = 
						`<div class="tree well targetDiv catalog-tree newMember mt-2" data-catalogname="${catalogName}" data-elementname="${elementName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-componentname="${phyComponentName}" id="targetEntities${id}">
							<ul id="targetEntities_ul${id}" class="Tree">
								<li>
									<span class="text-dark pl-2 ov-el ui-draggable ui-draggable-handle Source" oncontextmenu="openModal('${id}','Target')" title="${entityPhyName} (${catalogName})"><i class="far fa-minus-square" aria-hidden="true"></i>
										<a class="text-dark pl-1 smfont ov-el" title="${catalogName}" id="targetEntityName${id}" href="#">${entityLogName} (${catalogName})</a>
									</span>
									<ul>
										${attributeListLi}
									</ul>
								</li>
							</ul>
						</div>`;
				}
				$('#tgt-box').append(div);
				
				$('#targetEntities'+id+' li:has(ul)').addClass('parent_li');
				$('#targetEntities'+id+' li.parent_li > span').on('click', function(e) {
				    var children = $(this).parent('li.parent_li').find(' > ul > li');
				    if (children.is(":visible")) {
				        children.hide('fast');
				        $(this).find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
				    } else {
				        children.show('fast');
				        $(this).find(' > i').addClass('fa-minus-square').removeClass('fa-plus-square');
				    }
				    e.stopPropagation();
				});
				allowDrag();
//			}
//		});
	}
	$('#addAttributeModal').modal('hide');
}

function saveData(type){
	const selSystem = $('#selectedSystem').val();
	const selMapping = $('#selectedMapping').val();
	const url = `${ctx}/RDW/Intelligent-Mapper/system-mapping/${selSystem}/data-mapping/${selMapping}/predictive-mapping/attribute-mapping/addEntity`;
	let compNameSuffix = '';
	let attributeListLi = '';
	let entityNameFromJson='';
	let entityPhyName='';
	let entityLogName='';
	let toggleValue='';
	let componentArray = [];
	const catalogName = $('#catalog').find('option:selected').text();
	const entityName = $('#entity').find('option:selected').text();
	let selectedCompName = $('#entity').find('option:selected').data('componentname');
	
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValue = "Physical";
	}else{
		toggleValue = "Logical";
	}
	$('#src-box').find('div.catalog-tree').each(function(i){
		componentArray.push($(this).data('componentname'));
    });
    $('#tgt-box').find('div.catalog-tree').each(function(i){
    	componentArray.push($(this).data('componentname'));
    });
    
	console.log(componentArray);
	console.log(selectedCompName);
    if(componentArray.includes(selectedCompName)){
    	let initialID = 1;
    	getUniqueCompName(initialID,selectedCompName,componentArray,catalogName,entityName,type);
    }else{
    	addEntityToAttributePage('',catalogName,entityName,type)
    }
}


document.addEventListener("DOMContentLoaded", function(){
	//console.log('Fetching all sources and targets in mapping');
	const progressChild = $('.progress-child');
	setInterval(function(){ 
		if(progressChild.css('opacity') == '0.15')
			progressChild.css('opacity', 0.4);
		else
			progressChild.css('opacity', 0.15);
	}, 500);
	
	
	$(document).on('click','#toggleBtnForPhyLog',function(e){
		toggleBtwnPhyLog();
	});
	$('#saveBtn').removeAttr("disabled");
	$('#nextBtn').removeAttr("disabled");
	

	//tree structure toggle js
	$('.tree li:has(ul)').addClass('parent_li');
	$('.tree li.parent_li > span').on('click', function(e) {
	    var children = $(this).parent('li.parent_li').find(' > ul > li');
	    if (children.is(":visible")) {
	        children.hide('fast');
	        $(this).find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
	    } else {
	        children.show('fast');
	        $(this).find(' > i').addClass('fa-minus-square').removeClass('fa-plus-square');
	    }
	    e.stopPropagation();
	});
	allowDrop();

	/*const selectedSystem = $('#selectedSystem').val();
	const selectedMapping = $('#selectedMapping').val();
	const url = `${ctx}/RDW/Intelligent-Mapper/system-mapping/${selectedSystem}/data-mapping/${selectedMapping}/predictiveMapping/attributeMapping/fetchAllSourcesAndTargetsInMapping`;
	$.ajax({
        url: url,
        type: 'GET',
        timeout: 600000,
        success:function(data){
        	const jsonObj = JSON.parse(data);
        	console.log('jsonObj');
        	console.log(jsonObj);
        	let divValTarget = '', divValSource = '';
        	
        	for(let tgt in jsonObj.Target){
        		let columns = '';
        		for(let attrib of jsonObj.Target[tgt].attributes){
        			let phyAttr = '', logAttr = '';
        			if(attrib.includes('::')){
        				phyAttr = attrib.split('::')[0];
        				logAttr = attrib.split('::')[1];
        			}
        			columns = columns+
        			`<li data-attribphyname="${phyAttr}" data-attriblogname="${logAttr}">
        				<span title="${phyAttr} (${jsonObj.Target[tgt].componentName})" class="pl-4 pt-2 smfont tbl">
        					<a href="#" class="text-dark">${logAttr} (${jsonObj.Target[tgt].logicalEntityName})</a>
        				</span>
        			</li>`
        		}
        		divValTarget +=
        			`<div class="tree well mt-2 targetDiv catalog-tree" data-catalogName="${jsonObj.Target[tgt].catalogName}" data-entityphyname="${jsonObj.Target[tgt].entityName}" data-elementname="${jsonObj.Target[tgt].elementName}" data-entitylogname="${jsonObj.Target[tgt].logicalEntityName}" data-componentName="${jsonObj.Target[tgt].componentName}" id="targetEntities${tgt}">
        				<ul class="Tree">
        					<li class="parent_li">
        						<span class="text-dark pl-2 tbl table-section ov-el" oncontextmenu="openModal('${tgt}','Target')" title="${jsonObj.Target[tgt].entityName}(${jsonObj.Target[tgt].catalogName})">
        							&nbsp;<i class="far fa-minus-square" aria-hidden="true"></i>
        							<a href="#" title="${jsonObj.Target[tgt].catalogName}" class="text-dark pl-1 smfont ov-el" id="targetEntityName${tgt}">${jsonObj.Target[tgt].logicalEntityName} (${jsonObj.Target[tgt].catalogName})</a>
        						</span>
        						<ul>${columns}</ul>
        					</li>
        				</ul>
        			</div>`;
    		}
        	//$('#targetEntities-ldr').fadeOut(500);
        	//$('#tgt-box').append(divValTarget);
        	
        	for(let src in jsonObj.Source){
        		let columns = '';
        		for(let attrib of jsonObj.Source[src].attributes){
        			let phyAttr = '', logAttr = '';
        			if(attrib.includes('::')){
        				phyAttr = attrib.split('::')[0];
        				logAttr = attrib.split('::')[1];
        			}
        			columns = columns+
        			`<li data-attribphyname="${phyAttr}" data-attriblogname="${logAttr}">
        				<span title="${phyAttr} (${jsonObj.Source[src].componentName})" class="pl-4 pt-2 smfont tbl">
        					<a href="#" class="text-dark">${logAttr} (${jsonObj.Source[src].logicalEntityName})</a>
        				</span>
        			</li>`
        		}
        		divValSource +=
        			`<div class="tree well mt-2 sourceDiv catalog-tree" data-catalogName="${jsonObj.Source[src].catalogName}" data-entityphyname="${jsonObj.Source[src].entityName}" data-elementname="${jsonObj.Source[src].elementName}"  data-entitylogname="${jsonObj.Source[src].logicalEntityName}" data-componentName="${jsonObj.Source[src].componentName}" id="sourceEntities${src}">
        				<ul class="Tree">
        					<li class="parent_li">
        						<span class="text-dark pl-2 ov-el" oncontextmenu="openModal('${src}','Source')" title="${jsonObj.Source[src].componentName}(${jsonObj.Source[src].catalogName})">
        							&nbsp;<i class="far fa-minus-square" aria-hidden="true"></i>
        							<a href="#" title="${jsonObj.Source[src].catalogName}" class="text-dark pl-1 smfont ov-el" id="sourceEntityName${src}">${jsonObj.Source[src].logicalEntityName} (${jsonObj.Source[src].catalogName})</a>
        						</span>
        						<ul>${columns}</ul>
        					</li>
        				</ul>
        			</div>`;
        	}
        	//$('#sourceEntities-ldr').fadeOut(500);
        	//$('#src-box').append(divValSource);
        	
        	$(document).on('click','#toggleBtnForPhyLog',function(e){
        		toggleBtwnPhyLog();
        	});
        	$('#saveBtn').removeAttr("disabled");
        	$('#nextBtn').removeAttr("disabled");
        	
        	//tree structure toggle js
        	$('.tree li:has(ul)').addClass('parent_li');
        	$('.tree li.parent_li > span').on('click', function(e) {
        	    var children = $(this).parent('li.parent_li').find(' > ul > li');
        	    if (children.is(":visible")) {
        	        children.hide('fast');
        	        $(this).find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
        	    } else {
        	        children.show('fast');
        	        $(this).find(' > i').addClass('fa-minus-square').removeClass('fa-plus-square');
        	    }
        	    e.stopPropagation();
        	});
        	allowDrop();
	        	
	    },
        error:function(data){
        	//console.log('Error fetching all sources and targets in mapping '+data);
        }
	});*/
});

///////////////////Added for Data Switch//////////////////
let wrkTgtCollection = '';
let gblEntityDesignJson = {};
let selParentNodeArr = [];
let mstEntityDesignArr = [];
let tmpEntityDesignArr = [];
let domCnt = 0;

let selectedEntityName = '';
let projectName = $("#selectedProject").val();
let isInitTgtToggleLoad = false;

//to initiate the source catalog for fa - plus/minus
initiateSrcFaPlusMinusOps();

//to initiate the right side - source catalog for fa - plus/minus
//to initiate fa - plus/minus when dynamically adding/editing source catalogs - by default plus (i.e., hide)
function initiateSrcFaPlusMinusOps(){
	//when page launches only
	//adding '+' (collapse) by default in source catalog when page launches	
	$('.sourceDiv > ul.Tree').each(function(e) {
		 var children = $(this).children('li.parent_li').find(' > ul > li');
		 if (children.is(":visible")) {
	        children.hide('fast');
	        $(this).children('li.parent_li > span').find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
		 }
	});
}

//to initiate the target catalog tree when page launches
initiateTgtTree();

function initiateTgtTree(){
	//////////Added for Data Switch - Code starts here////////
	//to get the target catalog details and display it in target catalog side when launching the page
	$.ajax({
		url:ctx+'/schema-designer/getSchemaDesignDetails?projectName='+projectName,
		type:'get',
		success:function(data){
			try{
				 let objSchemaDesign = JSON.parse(data);
				 console.log('ObjSchemaDesign:',objSchemaDesign);
				 let tgtEntityDesign = {};
				 console.log('schemaDesign:',objSchemaDesign.schemaDesign);
				 if (objSchemaDesign.hasOwnProperty("schemaDesign")){
					 tgtEntityDesign = JSON.parse(objSchemaDesign.schemaDesign);
					 console.log('tgtEntityDesign:',tgtEntityDesign);
					 gblEntityDesignJson = tgtEntityDesign;
					 //get keys (entity name) from json and extract the data
					 //then construct master entity design json
					 
					 let entityNameArr = [];
					 Object.keys(tgtEntityDesign).forEach(function(key) {
						let valueJson = tgtEntityDesign[key];
						entityNameArr.push(key);
						//re-initialize
						mstEntityDesignArr = [];
						//load collection (that is root)
						mstEntityDesignArr.push({
							'dropId': valueJson.dropId,
							'nodeName': valueJson.nodeName,
							'nodeSrcRefName': valueJson.nodeSrcRefName,
							'referenceAttribs': valueJson.referenceAttribs,
							'denormalizeAttribs': valueJson.denormalizeAttribs
						});
						//load embed
						for (let i=0;i<valueJson.nodes.length;i++){
							mstEntityDesignArr.push(valueJson.nodes[i]);
						}
						
						isInitTgtToggleLoad = true;
						
						//initiate the target tree creation
						initiateFnlTgtTree();
						
						isInitTgtToggleLoad = false;
						
						//initiate new collection loop - reset the global variable 'mstEntityDesignArr', 'selParentNodeArr' and 'wrkTgtCollection'
						reInitializeGlobalVariable();
					});
					
					//using the entity name and get the attribute of the entity to display it in screen by default when page launches
					if (entityNameArr.length > 0){
						getSpfSchemaEntityDetails(entityNameArr[0]);
						//$("#schemaSrcRef").text(`${entityNameArr[0]} (${projectName})`)
					}					 
					 
				 }else{
					 console.log("Apologies, Unable to get the data");
				 }
				 
			}catch(e){
				console.log('Error in parsing project details - '+e);
				validationDiv = 
					`
					<div class="alert alert-danger alert-dismissible fade show" role="alert">
					  <strong>Oops!</strong> Unable to get project details from db. Please try again after some time.
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>
					`
				$('.validation-message').empty().append(validationDiv);
				setTimeout(function(){ $('.validation-message').empty(); }, 4000);
			} 
		}
	});
	//////////Added for Data Switch - Code ends here////////
}

//function to construct the right side catalog using master entity design array
function initiateFnlTgtTree(){
	//empty the existing right side catalog
	//$('#sourceEntities-ldr').empty();

	//re-initialize the temporary entity design array
	//tmpEntityDesignArr = [];
	
	console.log('///////////Initiate Final Target Tree//////////////');	
	console.log('Before - Master Entity Design Array: ',mstEntityDesignArr);
	console.log('Before - Working Target Collection: ',wrkTgtCollection);

	//loop through all elements in the master entity design array
	for (let i=0;i<mstEntityDesignArr.length;i++){
		let entityDesignJson = mstEntityDesignArr[i];

		if (!entityDesignJson.hasOwnProperty('parentName')){
			let collectionId = entityDesignJson.dropId;
			//remove the collection if already exist in the target catalog
			if($('#'+'tgt-'+collectionId).length){
				$('#'+'tgt-'+collectionId).remove();
			}

			//that is 'root', 'root' node doesn't have the 'parent' node
			constructFnlTgtRootTree(entityDesignJson);

			//to initiate fa - plus/minus 
			//faPlusMinusOps(entityDesignJson.dropId);
			
			//to initiate fa - plus/minus 
			clickTgtFaPlusMinusOps(entityDesignJson.dropId);
		}else{
			//that is 'embed', 'embed' node have the 'parent' node
			constructFnlTgtEmbedTree(entityDesignJson);

			//to initiate fa - plus/minus 
			//faPlusMinusOps(entityDesignJson.dropId);
			
			//to initiate fa - plus/minus 
			clickTgtFaPlusMinusOps(entityDesignJson.dropId);
		}
	}
	//end of for loop
	
	initiateTgtFaPlusMinusOps();
	
	//to check and construct the 'gblEntityDesignJson' values
	let collectionInfo = {};
	let nodesArr= [];
	for (let j=0;j<mstEntityDesignArr.length;j++){
		let entityDesignJson = mstEntityDesignArr[j];
		
		if (!entityDesignJson.hasOwnProperty('parentName')){
			wrkTgtCollection = entityDesignJson.nodeName;
			collectionInfo['dropId'] = entityDesignJson.dropId;
			collectionInfo['nodeName'] = entityDesignJson.nodeName;
			collectionInfo['nodeSrcRefName'] = entityDesignJson.nodeSrcRefName;
			collectionInfo['referenceAttribs'] = entityDesignJson.referenceAttribs;
			collectionInfo['denormalizeAttribs'] = entityDesignJson.denormalizeAttribs;
		}else{
			nodesArr.push(entityDesignJson)
		}
	}

	collectionInfo['nodes'] = nodesArr;
	gblEntityDesignJson[wrkTgtCollection] = collectionInfo;
	console.log("Final gblEntityDesignJson: ",gblEntityDesignJson);
}

//when user clicks fa - plus/minus in the target catalogs 
function clickTgtFaPlusMinusOps(tgtCollectionId){
	//it triggers when user changes in the designer area
	//it won't triggers when page launches
	if (!isInitTgtToggleLoad){
		$('#fnlTgtCatalog li:has(ul)').addClass('parent_li');
		$('#tgt-' + tgtCollectionId + ' > span').on('click', function(e) {
		    let children = $(this).parent('li.parent_li').find(' > ul > li');
		    
			if (children.is(":visible")) {
				console.log("Loop 0")
		        children.hide('fast');
		        $(this).find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
		    } else {
		    	console.log("Loop 1")
		        children.show('fast');
		        $(this).find(' > i').addClass('fa-minus-square').removeClass('fa-plus-square');
		    }			
			
		    e.stopPropagation();
		});
	}    
}

//to initiate fa - plus/minus when dynamically adding/editing target catalogs - by default plus (i.e., hide)
function initiateTgtFaPlusMinusOps(){
	//when page launches only
	if (isInitTgtToggleLoad){
		//adding '+' (collapse) by default in target catalog when page launches
		for (let i=0;i<mstEntityDesignArr.length;i++){
			let mstEntityDesignJson = mstEntityDesignArr[i];
			
			//parsing each nodes and check whether the node contains sub nodes (i.e., ul > li), if exist -> then add 'fa plus' and 'hide' it
			let children = $('#tgt-' + mstEntityDesignJson.dropId + ' > span').parent('li.parent_li').find(' > ul > li');    
			if (children.is(":visible")) {
				children.hide('fast');
				$('#tgt-' + mstEntityDesignJson.dropId + ' > span').find('i').addClass('fa-plus-square').removeClass('fa-minus-square');
			}
			
			//Adding click event for each node when page launches
			$('#tgt-' + mstEntityDesignJson.dropId + ' > span').on('click', function(e) {
			    let children = $(this).parent('li.parent_li').find(' > ul > li');
			    
				if (children.is(":visible")) {
					console.log("Loop 0")
			        children.hide('fast');
			        $(this).find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
			    } else {
			    	console.log("Loop 1")
			        children.show('fast');
			        $(this).find(' > i').addClass('fa-minus-square').removeClass('fa-plus-square');
			    }			
				
			    e.stopPropagation();
			});
		}

	}		
}

//function to construct target catalog for 'root' node
function constructFnlTgtRootTree(entityDesignJson){
	let dropId = entityDesignJson.dropId;
	let spanContent = entityDesignJson.nodeName;
	let spanTitle = entityDesignJson.nodeSrcRefName;
	
	//get the catalogName from 'nodeSrcRef' field
	let catalogNmSplit = entityDesignJson.nodeSrcRefName.split(' (');
	let catalogName = catalogNmSplit[1].substring(0,catalogNmSplit[1].length-1);
	
	//get the attributes - i.e., reference, denormalize and subsetting
	let attribLi = constructFnlTgtAttribTree(entityDesignJson);
	//console.log('attribLi: ',attribLi)

	let rootDiv = 			
		 `<li class="parent_li" id='tgt-${dropId}' ondblclick="onSelectTgtCollection(this)">
                <span class="text-dark pl-2 pt-1 ov-el"
                	title="${spanTitle}"
                    data-nodename="${entityDesignJson.nodeName}"
                    data-catalogname="${catalogName}"
                    data-type="root" id='tgt-span-${dropId}'>
                    &nbsp;
                	<i class="far fa-minus-square" aria-hidden="true"></i>
                	<!-- ${spanContent} -->
                	<a class="text-dark pl-1 mt-2 ov-el" id="tgt-a-${dropId}" href="#">${spanContent}</a>
                </span>

            	<ul id='tgt-ul-${dropId}'>
            		${attribLi}
            	</ul>
            </li>`;

	////$('#sourceEntities-ldr').append(rootDiv);
	//$('#fnlTgtCatalog').prepend(rootDiv);
	$('#fnlTgtCatalog').append(rootDiv);
}

//function to construct target catalog for 'embed' node
function constructFnlTgtEmbedTree(entityDesignJson){
	//for 'embed' type
	let parentName = entityDesignJson.parentName;
	
	//get the target - parent node id
	let isParentNameExist = false;
	let parentTgtId = '';

	//get the target parent node id using name of the parent and master entity design array
	for (let x=0;x<mstEntityDesignArr.length;x++){
		let mstEntityDesignJson = mstEntityDesignArr[x];
		if ( mstEntityDesignJson.nodeName === parentName){
			isParentNameExist = true;
			parentTgtId = mstEntityDesignJson.dropId;
			break;
		}
	}

	//parent node identified and extracted the parent drop id
	if (isParentNameExist){
		let parentUlId = `tgt-ul-${parentTgtId}`;
		
		let dropId = entityDesignJson.dropId;
		let spanContent = entityDesignJson.nodeName;
		let spanTitle = entityDesignJson.nodeSrcRefName;
		
		//get the catalogName from 'nodeSrcRef' field
		let catalogNmSplit = entityDesignJson.nodeSrcRefName.split(' (');
		let catalogName = catalogNmSplit[1].substring(0,catalogNmSplit[1].length-1);
		
		//get the attributes - i.e., reference, denormalize and subsetting
		let attribLi = constructFnlTgtAttribTree(entityDesignJson);
		
		let embedDiv = 
			`<li class="parent_li" id='tgt-${dropId}'>
	            <span class="text-dark pl-2 pt-1 ov-el" style="margin-top: 10px;"
	            	title="${spanTitle}"
	                data-nodename="${entityDesignJson.nodeName}"
	                data-catalogname="${catalogName}"
	                data-type="embed" id='tgt-span-${dropId}'>
	                &nbsp;
	            	<i class="far fa-minus-square" aria-hidden="true"></i>
	            	<!-- ${spanContent} -->
	            	<a class="text-dark pl-1 mt-2 ov-el" id="tgt-a-${dropId}" href="#">${spanContent}</a>
	            </span>
	
	        	<ul id='tgt-ul-${dropId}'>
	        		${attribLi}	        		
	        	</ul>
	        </li>`;
		
		$('#'+parentUlId).append(embedDiv);
	}else{
		//ignore the current node to add it in tree
	}
}

//function to construct reference, denormalize and subsetting
function constructFnlTgtAttribTree(entityDesignJson){
	console.log('///////////Attribute Loop///////////////');
	//let catalogName = entityDesignJson.catalogName;
	let nodeName = entityDesignJson.nodeName;
	let parentName = '';
	if (entityDesignJson.hasOwnProperty('parentName')){
		parentName = entityDesignJson.parentName;	
	}

	//get the catalogName from 'nodeSrcRef' field
	let catalogNmSplit = entityDesignJson.nodeSrcRefName.split(' (');
	let catalogName = catalogNmSplit[1].substring(0,catalogNmSplit[1].length-1);

	let referenceAttribs = entityDesignJson.referenceAttribs;
	let denormalizeAttribs = entityDesignJson.denormalizeAttribs;

	let attribLi = '';
	//adding reference
	for (let x=0;x<referenceAttribs.length;x++){
		let refJson = referenceAttribs[x];
		//get the catalogName from 'nodeSrcRef' field
		let srcRefSplit = refJson.srcRef.split(' (');
		let srcRef = srcRefSplit[1].substring(0,srcRefSplit[1].length-1);
		
		attribLi += 
			`<li class="parent_li" id='tgt-${refJson.id}'>        
                <span class="text-dark pl-2 pt-1 ov-el" style="margin-top: 10px;"
                title="${refJson.srcRef}"
                data-catalogname="${catalogName}"
                data-entityname="${srcRef}"                
                data-attribname="${refJson.name}"
                data-type="reference" id='tgt-span-${refJson.id}'>
					&nbsp;
                	<a class="text-dark pl-1 mt-2 ov-el" id="tgt-a-${refJson.id}" href="#">${refJson.name}</a>
				</span>
			</li>`;
	}
	
	//adding denormalize
	for (let y=0;y<denormalizeAttribs.length;y++){
		let denormJson = denormalizeAttribs[y];
		//get the catalogName from 'nodeSrcRef' field
		let srcRefSplit = denormJson.srcRef.split(' (');
		let srcRef = srcRefSplit[1].substring(0,srcRefSplit[1].length-1);
		
		attribLi += 
			`<li class="parent_li" id='tgt-${denormJson.id}'>        
                <span class="text-dark pl-2 pt-1 ov-el"
                title="${denormJson.srcRef}"
                data-catalogname="${catalogName}"
                data-entityname="${srcRef}"                
                data-attribname="${denormJson.name}"
                data-type="reference" id='tgt-span-${denormJson.id}'>
					&nbsp;
                	<a class="text-dark pl-1 mt-2 ov-el" id="tgt-a-${denormJson.id}" href="#">${denormJson.name}</a>
				</span>
			</li>`;
	}	

	return attribLi;
}

//re-initialize the global variable
function reInitializeGlobalVariable(){
	//empty all row in the designer area
	//$('#box-body').empty();

	//re-initialize the global values	
	//delete gblEntityDesignJson[wrkTgtCollection];
	mstEntityDesignArr = [];
	selParentNodeArr = [];
	wrkTgtCollection = '';
}

//to add new collection
function addNewCollection(){
	//empty all row in the designer area
	$('#box-body').empty();

	//re-initialize the global values	
	//delete gblEntityDesignJson[wrkTgtCollection];
	mstEntityDesignArr = [];
	selParentNodeArr = [];
	wrkTgtCollection = '';
}

//to get the values when user double click the target catalog collection
//construct and display the values in center screen
window.onSelectTgtCollection = onSelectTgtCollection;
function onSelectTgtCollection(e){
	console.log("Collection Id: ",e.id);
	//list id
	let tgtCollectionId = e.id;
	let splitTgtCollectionId = tgtCollectionId.split('-');
	
	//list - span id
	let tgtCollectionSpanId = 'tgt-span-' + splitTgtCollectionId[1] + '-' + splitTgtCollectionId[2];
	
	//get the collection name
	let collectionName = $('#'+tgtCollectionSpanId).data("nodename");
	console.log("Collection Name: ",collectionName);
	getSpfSchemaEntityDetails(collectionName);
}

//to get and display the attribute details for the entity in the screen by default'attributes' of first entity is displayed
function getSpfSchemaEntityDetails(entityName){
	selectedEntityName = entityName;
	$("#schemaSrcRef").text(`${entityName} (${projectName})`)
	$.ajax({
		//url:ctx+'/schema-designer/getEntityDesignDetails/'+projectName+'/'+entityName,
		url:ctx+'/schema-designer/getEntityDesignDetails?projectName='+projectName+'&entityName='+entityName,
		type:'get',
		success:function(data){
			try{
				console.log('Entity Data0:',data);
				console.log('Entity Data1:',JSON.parse(data));
				let schemaDesignJson = JSON.parse(data);
				

				let mapSchemaDesignJson = {
				    "entityName":"customer",
				    "dtoVersion": 1,
				    "attributeArr":[
				     {
				        "logicalTargetEntityRef":"Dependent",
				        "logicalTargetAttributeRef": "Title",
				        "sourceListArr":[
				            {
				                "sourceCatalogName":"SEgf",
				                "logicalSourceDataElementRef":"Title",
				                "sourceCompName":"Party","logicalSourceFeedRef":"Party","sourceDataElementRef":"title",
				                "dtoVersion":3,
				                "sourceFeedRef":"Party"
				            }
				        ],
				        "targetEntityRef":"Dependent",
				        "targetCompName":"Dependent",
				        "dtoVersion":3,
				        "transformationRuleArr":[],
				        "targetCatalogName":"SEgf",
				        "derivedVarDetails":null,
				        "targetAttributeRef":"Title"
				    },
				    {
				    	"logicalTargetEntityRef":"Dependent","logicalTargetAttributeRef":"Member Id",
				    	"sourceListArr":[
				    		{
				    			"sourceCatalogName":"SEgf","logicalSourceDataElementRef":"Member Identifier","sourceCompName":"Party","logicalSourceFeedRef":"Party","sourceDataElementRef":"member_id","dtoVersion":3,"sourceFeedRef":"Party"
				    		}
				    	],
				    	"targetEntityRef":"Dependent",
				    	"targetCompName":"Dependent",
				    	"dtoVersion":3,
				    	"transformationRuleArr":[],
				    	"targetCatalogName":"SEgf",
				    	"derivedVarDetails":null,
				    	"targetAttributeRef":"member_id"
				    },
				    {
				    	"logicalTargetEntityRef":"Dependent",
				    	"logicalTargetAttributeRef":"First Name",
				    	"sourceListArr":[{"sourceCatalogName":"SEgf","logicalSourceDataElementRef":"First Name","sourceCompName":"Party","logicalSourceFeedRef":"Party","sourceDataElementRef":"fname","dtoVersion":3,"sourceFeedRef":"Party"}],
				    	"targetEntityRef":"Dependent",
				    	"targetCompName":"Dependent",
				    	"dtoVersion":3,
				    	"transformationRuleArr":[],
				    	"targetCatalogName":"SEgf",
				    	"derivedVarDetails":null,
				    	"targetAttributeRef":"fname"
				    },
				    {
				    	"logicalTargetEntityRef":"Dependent",
				    	"logicalTargetAttributeRef":"Middle Name",
				    	"sourceListArr":[
				    		{"sourceCatalogName":"SEgf","logicalSourceDataElementRef":"Middle Name","sourceCompName":"Party","logicalSourceFeedRef":"Party","sourceDataElementRef":"mname","dtoVersion":3,"sourceFeedRef":"Party"}],"targetEntityRef":"Dependent","targetCompName":"Dependent","dtoVersion":3,"transformationRuleArr":[],"targetCatalogName":"SEgf","derivedVarDetails":null,"targetAttributeRef":"mname"},{"logicalTargetEntityRef":"Dependent","logicalTargetAttributeRef":"Last Name","sourceListArr":[{"sourceCatalogName":"SEgf","logicalSourceDataElementRef":"Last Name","sourceCompName":"Party","logicalSourceFeedRef":"Party","sourceDataElementRef":"lname","dtoVersion":3,"sourceFeedRef":"Party"}],"targetEntityRef":"Dependent","targetCompName":"Dependent","dtoVersion":3,"transformationRuleArr":[],"targetCatalogName":"SEgf","derivedVarDetails":null,"targetAttributeRef":"lname"},{"logicalTargetEntityRef":"Dependent","logicalTargetAttributeRef":"Member Suffix","sourceListArr":[{"sourceCatalogName":"SEgf","logicalSourceDataElementRef":"Member Suffix","sourceCompName":"Party","logicalSourceFeedRef":"Party","sourceDataElementRef":"membe_suffix","dtoVersion":3,"sourceFeedRef":"Party"}],"targetEntityRef":"Dependent","targetCompName":"Dependent","dtoVersion":3,"transformationRuleArr":[],"targetCatalogName":"SEgf","derivedVarDetails":null,"targetAttributeRef":"member_suffix"},{"logicalTargetEntityRef":"Dependent","logicalTargetAttributeRef":"Birth Date","sourceListArr":[{"sourceCatalogName":"SEgf","logicalSourceDataElementRef":"Date of Birth","sourceCompName":"Party","logicalSourceFeedRef":"Party","sourceDataElementRef":"dob","dtoVersion":3,"sourceFeedRef":"Party"}],"targetEntityRef":"Dependent","targetCompName":"Dependent","dtoVersion":3,"transformationRuleArr":[],"targetCatalogName":"SEgf","derivedVarDetails":null,"targetAttributeRef":"bdate"},{"logicalTargetEntityRef":"Dependent","logicalTargetAttributeRef":"Gender Code","sourceListArr":[{"sourceCatalogName":"SEgf","logicalSourceDataElementRef":"Gender","sourceCompName":"Party","logicalSourceFeedRef":"Party","sourceDataElementRef":"gender","dtoVersion":3,"sourceFeedRef":"Party"}],"targetEntityRef":"Dependent","targetCompName":"Dependent","dtoVersion":3,"transformationRuleArr":[],"targetCatalogName":"SEgf","derivedVarDetails":null,"targetAttributeRef":"gender_cd"}
				    ]
				    
				};
				
				//console.log('mapSchemaDesignJson.attributeArr: ',mapSchemaDesignJson.attributeArr[0]);
				let derAttributeArr = [];
				for (let i=0;i<mapSchemaDesignJson.attributeArr.length;i++){
					let orgAttributeJson = mapSchemaDesignJson.attributeArr[i];
					derAttributeArr.push({
						"logicalTargetEntityRef": orgAttributeJson.logicalTargetEntityRef,
				    	"logicalTargetAttributeRef":orgAttributeJson.logicalTargetAttributeRef,
				    	"targetEntityRef":orgAttributeJson.targetEntityRef,
				    	"targetCompName":orgAttributeJson.targetCompName,
				    	"dtoVersion":orgAttributeJson.dtoVersion,
				    	"targetCatalogName":orgAttributeJson.targetCatalogName,
				    	"derivedVarDetails":orgAttributeJson.derivedVarDetails,
				    	"targetAttributeRef":orgAttributeJson.targetAttributeRef,				    	
				    	"srcAtrRefList": orgAttributeJson.sourceListArr,
				    	"transformationRuleList": orgAttributeJson.transformationRuleArr
					})
					
				}
				//update the value in the mapping array element for further processing
				//document.querySelector('#mappingArray').textContent = JSON.stringify(data);
				document.querySelector('#mappingArray').textContent = JSON.stringify(schemaDesignJson.attributeArr);
				console.log("After Mapping Array: ",JSON.stringify(schemaDesignJson.attributeArr))
				constructAttributeDesignArea();
				//console.log("Mapping Array: ",String(JSON.stringify(mapSchemaDesignJson.attributeArr,0,2)))
			}
			catch(e){
				/*console.log('Error in parsing project details - '+e);
				validationDiv = 
					`
					<div class="alert alert-danger alert-dismissible fade show" role="alert">
					  <strong>Oops!</strong> Unable to get project details from db. Please try again after some time.
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>
					`
				$('.validation-message').empty().append(validationDiv);
				setTimeout(function(){ $('.validation-message').empty(); }, 4000);*/
				console.log("Unable to get the details.");
			}
		}
	});
}

//function to get the entity name using physical and logical name
function getEntityName(phyEntityName, logEntityName){
	let entityName = '';
    if (logEntityName !== ''){
    	entityName = logEntityName;
    }else{
    	entityName = phyEntityName;
    }
    return entityName;
}

//function to get the attribute name using physical and logical name
function getAttributeName(phyAttribName, logAttribName){
	let attribName = '';
    if (logAttribName !== ''){
    	attribName = logAttribName;
    }else{
    	attribName = phyAttribName;
    }
    return attribName;
}

//to construct the attribute design area 
function constructAttributeDesignArea(){
	$('#box-body').empty();
	console.log('Inside "constructAttributeDesignArea" function');
	let mappingArray = document.querySelector('#mappingArray').textContent;
	//console.log("mapping Array");
	//console.log(mappingArray);
	mappingArray = JSON.parse(mappingArray);
	
	//making target attr occurence unique
	let lengthMappingArray = mappingArray.length;
	//alert("checking for predict Target");
	var start = new Date().getTime();
	for(let i=0;i<mappingArray.length;i++){
		let phyTargetColumn = mappingArray[i].targetAttributeRef;
		let phyTargetEntity = mappingArray[i].targetComponentName;
		for(let j=i+1;j<mappingArray.length;j++){
			let newPhyTgtCol = mappingArray[j].targetAttributeRef;
			let newPhyTgtEnt = mappingArray[j].targetComponentName;
			if((phyTargetColumn != null && newPhyTgtCol==phyTargetColumn) && (phyTargetEntity != null && newPhyTgtEnt==phyTargetEntity)){
				mappingArray[i]['srcAtrRefList'].push(mappingArray[j]['srcAtrRefList'][0]);
				mappingArray.splice(j,1);
			}
		}
	}
	var elapsed = new Date().getTime() - start;
	//console.log("Time taken to compare the tgt attr--> "+elapsed);
		
	let colMap = '';
	let Rules = document.querySelector('#ruleArray').value;
	var option = '';
	let derivedCols = [];
	
	Rules = Rules.substring(1,Rules.length-1);
	for(var i=0,length = Rules.split(',').length ;i<length;i++){
		option = option + 
		'<option value="'+Rules.split(',')[i]+'">'+Rules.split(',')[i]+'</option>';
	}
	
	console.log(mappingArray);
	for(let i=0,length = mappingArray.length; i<length;i++){
		var sourceCols = '', targetCols = '';
		let oldRuleObject = '', updatedRuleDesc = '', updatedParams ='' , updatedType = '';
		let rulediv = '';
		if(mappingArray[i].transformationRuleList.length>0){

			for(let rule in mappingArray[i].transformationRuleList){
				if(mappingArray[i].transformationRuleList[rule].ruleDescription == ''){
					oldRuleObject = getRuleDescForOldRules(mappingArray[i].srcAtrRefList, mappingArray[i].transformationRuleList[rule].parameter, mappingArray[i].transformationRuleList[rule].ruleName);
					updatedParams = JSON.stringify(oldRuleObject._paramValue);
					updatedType = oldRuleObject._type;
					updatedRuleDesc = oldRuleObject._updatedRuleDesc;
				}else{
					updatedRuleDesc = mappingArray[i].transformationRuleList[rule].ruleDescription;
					updatedParams = mappingArray[i].transformationRuleList[rule].parameter;
					updatedType = mappingArray[i].transformationRuleList[rule].type;
				}
			
				if(mappingArray[i].transformationRuleList[rule].ruleName!=''){
					let json = JSON.parse(mappingArray[i].transformationRuleList[rule].parameter);
					let derivedVarJson = JSON.parse(mappingArray[i].derivedVarDetails);
						rulediv += 
							`<div class="pl-1 flx flex-rule-div${i}">
								<p class="m-0 unselectable pointer" ondblclick="editRule('${i}','${rule}',event);" title="Double click on the rule to edit"
									data-rule-rulename="${mappingArray[i].transformationRuleList[rule].ruleName}" 
									data-rule-transComponentName="${mappingArray[i].transformationRuleList[rule].componentName}" 
									data-rule-parameter='${updatedParams}' 
									data-rule-type="${updatedType}" 
									data-rule-ruleDescription="${updatedRuleDesc}" 
									data-rule-descriptiontext="${mappingArray[i].transformationRuleList[rule].ruledesctext}"
									data-derived-var-dataType="${derivedVarJson.dataType}" 
									data-derived-var-precision="${derivedVarJson.precision}" 
									data-derived-var-scale="${derivedVarJson.scale}">
									${updatedRuleDesc}
								</p>
								<i class="text-right ruleRemove pointer fas fa-times text-danger"></i>
							</div>`;
				}
				if(mappingArray[i].transformationRuleList[rule].ruleName == 'Lookup'){
					rulediv = ''; //Removing lookup rules
				}
				
			}
		}
		
		for(let source in mappingArray[i].srcAtrRefList){
			if(mappingArray[i].srcAtrRefList[source].sourceFeedRef == 'exp_comp'){
				mappingArray[i].srcAtrRefList[source].sourceFeedRef = 'Derived_Var';
			}
			let sourceCatalog = mappingArray[i].srcAtrRefList[source].sourceCatalogName;
			let phySourceColumn = mappingArray[i].srcAtrRefList[source].sourceDataElementRef;
			let logSourceColum = mappingArray[i].srcAtrRefList[source].logicalSourceDataElementRef;
			let phySourceEntity = mappingArray[i].srcAtrRefList[source].sourceFeedRef;
			let logSourceEntity = mappingArray[i].srcAtrRefList[source].logicalSourceFeedRef;
			let sourceCompName = mappingArray[i].srcAtrRefList[source].sourceCompName;
			
			if(logSourceColum == ''){
				sourceCols += '';
				
			}else{
				sourceCols += 
					`<li title="Catalog Name : ${sourceCatalog}" data-entityphyname="${phySourceEntity}" data-entitylogname="${logSourceEntity}" data-attribphyname="${phySourceColumn}" data-attriblogname="${logSourceColum}" data-catalogname="${sourceCatalog}" data-componentName="${sourceCompName}">
						<span class="pr-2">
							${logSourceColum} (${logSourceEntity}.${sourceCatalog})
						</span>
						<span class="pointer">
							<i class="fas fa-times text-danger sourceRemove"></i>
						</span>
					</li>`
			}
			
		}
		
		if(mappingArray[i].targetEntity == 'exp_comp'){
			mappingArray[i].targetEntity = 'Derived_Var';
		}
		
		let targetCatalog = mappingArray[i].targetCatalogName;
		let phyTargetColumn = mappingArray[i].targetAttributeRef;
		let phyTargetEntity = mappingArray[i].targetEntityRef;
		let logTargetColumn = mappingArray[i].logicalTargetAttributeRef;
		let logTargetEntity= mappingArray[i].logicalTargetEntityRef;
		let targetCompName = mappingArray[i].targetCompName;
		let physicalData='';
		let logicalData='';
		if(phyTargetEntity == 'Derived_Var')
			derivedCols.push(phyTargetColumn);
		
		console.log(targetCatalog);
		if((phyTargetColumn==null && targetCompName==null) || (phyTargetColumn=='' && targetCompName=='')){
			physicalData='';
		}else{
			physicalData=phyTargetColumn+' ('+targetCompName+')';
		}
		
		if((logTargetColumn==null && targetCompName==null) || (logTargetColumn=='' && targetCompName=='')){
			logicalData='';
		}else{
			logicalData=logTargetColumn+' ('+logTargetEntity+'.'+targetCatalog+')';
		}
		
		colMap+=
		`<div class="box">
			<div class="row">
				<div class="col borderCol targetdiv" id="targetAttributes-${i}">
					<ul class="Tree">
						<li title="Catalog Name : ${targetCatalog}" data-entityphyname="${phyTargetEntity}" data-entitylogname="${logTargetEntity}" data-attribphyname="${phyTargetColumn}" data-attriblogname="${logTargetColumn}" data-catalogname="${targetCatalog}" data-componentName="${targetCompName}">
							<span>
								<i class="pointer fas fa-times text-danger"></i>
							</span>
							<span class="pl-2">
								${logicalData}
							</span>
						</li>
					</ul>
				</div>
				<div class="col-md-auto text-center borderCol p-1 rule-box" style="display:flex;justify-content:center">
					<span class="fa-layers fa-lg pointer open-rule-console" style=align-self:center;>
						<i class="far fa-plus-square text-info"></i>
					</span>
				</div>
				<div class="col-md-4 p-0 borderCol flex" id="ruleApplied${i}">
					${rulediv}
				</div>
				<div class="col sourceId" id="sourceAttributes-${i}">
					<ul class="Tree w-100" id="sourceDrop-${i}-${i}">
						${sourceCols}
					</ul>
				</div>
			</div>
		</div>`	
	}
	//end of main for loop - mapping array
		
	if(derivedCols.length > 0){
		let derivedColDiv = 
			`<div class="tree well catalog-tree sourceDiv mt-2" id="udfTree0" data-entityphyname="Derived_Var" data-entitylogname="Derived_Var" data-componentname="Derived_Var" data-catalogname="Derived_Attributes">
		        <ul class="sourceEntities_ul0 Tree">
			        <li class="parent_li">
			            <span class="text-dark pl-2 ov-el">
			                <i class="far fa-minus-square" aria-hidden="true"></i>
			                <a class="text-dark pl-1 smfont ov-el" href="#">Derived Attributes</a>
			            </span>
			            <ul></ul>
			        </li>
		    	</ul>
			</div>`
		$('#src-box').append(derivedColDiv);
	
		let cols = '';
		let entity = 'Derived_Var';
		
		for(let col of derivedCols){
			cols = 	`<li data-attribphyname="${col}" data-attriblogname="${col}">
						<span title="${col} (${entity})" class="pl-4 pt-2 smfont tbl"><a href="#" class="text-dark">${col} (${entity})</a></span>
					 </li>`;
			$('.sourceEntities_ul0').find('li ul').append(cols); 
		}
	}

	$('#box-body').append(colMap);
	let openRuleIcons = document.querySelectorAll('.rule-box span.open-rule-console');
	openRuleIcons.forEach(function(item, index){
		item.addEventListener('click', function(e){
			openUserDefinedVariableModel(index, '','new', e);
		});
	});
}

$(document).on('click','.targetdiv i',function(e){
	removeTargetAttributes(e);
});
$(document).on('click','.sourceRemove',function(e){
	$(e.target).parents().eq(1).remove();
});
$(document).on('click','.ruleRemove',function(e){
	$(e.target).parent().remove();
});


const t1 = performance.now();
//console.log("Time to page load took " + (t1 - t0) + " milliseconds.");

function callAutocompleteFunction(array,id){
	//console.log(array);
	
	var input = document.querySelector("#predTyping"+id);
	var attributes = [];

	for(var i=0;i<srcjsonObject.length;i++){
		attributes = srcjsonObject[i].attributes;
	}
	var a = new Awesomplete(input, {
		autoFirst:true,
		minChars : 1,
		list: array,
	});
	input.addEventListener('keyup', function(e) {
		if(e.keyCode == 32){
			var currentList = [];
			for(var str of attributes){
				var currentVal = input.value;
				currentVal = currentVal  +str.split('::')[1];
				currentList.push(currentVal);
			}
			 a.list = currentList;
			 a.evaluate();
		}
		if(e.keyCode == 8 || e.keyCode == 46){
			var val = input.value;
			//console.log(val);
			if(val == ''){
				a.list = array;
				 a.evaluate();
			}else{
				for(var str of array){
					if(val == str){
						a.list = array;
						 a.evaluate();
					}
				}
			}
		}
	});

}


function allowDrop(){
	$(".Tree span.tbl , .Tree li span").draggable({
	    revert: "invalid",
	    cursor: "move",
	    refreshPositions: true,
	    drag: function(event, ui) {
	        ui.helper.addClass("draggable");
	    },
	    helper: function(event) {
	        return $(event.target).clone().css({
	            width: $(event.target).width()
	        });
	    }
	});
	
	$(".sourceId").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree li span",
	    drop: function(event, ui) {
	        $(this).addClass("ui-state-highlight");
	        var clone = ui.draggable.clone();
	        clone.addClass("dropped");
	        let catalogName = ui.draggable.parents().eq(4).data('catalogname');
	        let entityPhyName = ui.draggable.parents().eq(4).data('entityphyname');
	        let entityLogName = ui.draggable.parents().eq(4).data('entitylogname');
	        let componentName = ui.draggable.parents().eq(4).data('componentname');
	        let attribphyname = ui.draggable.parent().data('attribphyname');
	        let attriblogname = ui.draggable.parent().data('attriblogname');
	        let componentNametxt = ui.draggable.parents().eq(4).data('componentname');
	        
	        //Added for Data Switch
	        catalogName = ui.draggable.data('catalogname');
	        entityPhyName = ui.draggable.data('entityphyname');
	        entityLogName = ui.draggable.data('entitylogname');
	        attribphyname = ui.draggable.data('attribphyname');
	        attriblogname = ui.draggable.data('attriblogname');	        
	        componentNametxt = getEntityName(entityPhyName, entityLogName);
	        let draggedElementType = ui.draggable.data('type');

	        console.log('Source Area - catalogName: ',catalogName)
	        console.log('Source Area - entityPhyName: ',entityPhyName)
	        console.log('Source Area - entityLogName: ',entityLogName)
	        console.log('Source Area - attribphyname: ',attribphyname)
	        console.log('Source Area - attriblogname: ',attriblogname)
	        console.log('Source Area - componentNametxt: ',componentNametxt)
	        
	        let devariable = ui.draggable.attr('title');
	        if(devariable.includes('Derived_Var')){
	        	//let devariable = ui.draggable.title();
	        	let variableName = devariable.substring(0,devariable.indexOf('(')).trim();
	        	catalogName = "Derived_Attributes";
		        entityPhyName = "Derived_Var";
		        entityLogName = "Derived_Var";
		        attribphyname = variableName;
		        attriblogname = variableName;
		        componentNametxt = "Derived_Var";
	        }
	        if(clone.text().trim()!=''){
	        	//let cloneText = clone.text().trim();
		        //cloneText = cloneText.substring(0,cloneText.length-1);
		        //cloneText += '.'+catalogName+')';
		        
		        let entityName = getEntityName(entityPhyName, entityLogName);
		        let cloneText = clone.text().trim();  
		        cloneText += ` (${entityName}.${catalogName}`;

	        	let divAppend = 
	    		    `<li title="Catalog Name : ${catalogName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-attribphyname="${attribphyname}" data-attriblogname="${attriblogname}" data-catalogname="${catalogName}" data-componentname="${componentNametxt}"> 
	    					<span>
	    					${cloneText}
	    				</span>
	    				<span class="pointer">
	    					<i class="fas fa-times text-danger sourceRemove"></i>
	    				</span>
	    			</li>`
	    	        $(event.target).find('ul').append(divAppend);
	    	        $(event.target).parents().eq(1).removeClass('box-error');
	        }
	    }
	});
	
	$(".targetdiv").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree span.tbl",
	    drop: function(event, ui) {
	        $(this).addClass("ui-state-highlight");

	        if(ui.draggable.hasClass('table-section')){
	        	alert('Table can be dragged only on an empty column. Please drag from the column list to replace.');
	        }else{
	        	var clone = ui.draggable.clone();
		        clone.addClass("dropped");
		        let catalogName = ui.draggable.parents().eq(4).data('catalogname');
		        let entityPhyName = ui.draggable.parents().eq(4).data('entityphyname');
		        let entityLogName = ui.draggable.parents().eq(4).data('entitylogname');
		        let attribphyname = ui.draggable.parent().data('attribphyname');
		        let attriblogname = ui.draggable.parent().data('attriblogname');
		        let componentNametxt = ui.draggable.parents().eq(4).data('componentname');

		        //Added for Data Switch
		        catalogName = ui.draggable.data('catalogname');
		        entityPhyName = ui.draggable.data('entityphyname');
		        entityLogName = ui.draggable.data('entitylogname');
		        attribphyname = ui.draggable.data('attribphyname');
		        attriblogname = ui.draggable.data('attriblogname');	        
		        componentNametxt = getEntityName(entityPhyName, entityLogName);
		        let draggedElementType = ui.draggable.data('type');

		        console.log('Target Area - catalogName: ',catalogName)
		        console.log('Target Area - entityPhyName: ',entityPhyName)
		        console.log('Target Area - entityLogName: ',entityLogName)
		        console.log('Target Area - attribphyname: ',attribphyname)
		        console.log('Target Area - attriblogname: ',attriblogname)
		        console.log('Target Area - componentNametxt: ',componentNametxt)
		        
		        //let cloneTextTgt = clone.text().trim();
		        //cloneTextTgt = cloneTextTgt.substring(0,cloneTextTgt.length-1);
		        //cloneTextTgt += '.'+catalogName+')';        
		        
		        let entityName = getEntityName(entityPhyName, entityLogName);
		        let cloneTextTgt = clone.text().trim();     
		        cloneTextTgt += ` (${entityName}.${catalogName}`;
		        
				var divAppend = 
				`<li title="Catalog Name : ${catalogName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-attribphyname="${attribphyname}" data-attriblogname="${attriblogname}" data-catalogname="${catalogName}" data-componentname="${componentNametxt}">
						<span>
							<i class="pointer fas fa-times text-danger"></i>
						</span>
						<span class="pl-2">
							${cloneTextTgt}
						</span>
					</li>`
		        $(event.target).find('ul').empty().append(divAppend);
	        }
	    }
	});

	$(".newTargetCol").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree span.tbl",
	    
	    drop: function(event, ui) {
	    	let i = $('#box-body').find('div.box').length;
	    	let boxIndex = $('.newColumn').find('div.newTargetCol').length - 1;
	    	var clone = ui.draggable.clone();
	        clone.addClass("dropped");
	        let catalogName = ui.draggable.parents().eq(4).data('catalogname');
	        let entityPhyName = ui.draggable.parents().eq(4).data('entityphyname');
	        let entityLogName = ui.draggable.parents().eq(4).data('entitylogname');
	        let componentName = ui.draggable.parents().eq(4).data('componentname');
	        let attribphyname = ui.draggable.parent().data('attribphyname');
	        let attriblogname = ui.draggable.parent().data('attriblogname');
	        let componentNametxt = ui.draggable.parents().eq(4).data('componentname');
	        
	        //Added for Data Switch
	        catalogName = ui.draggable.data('catalogname');
	        entityPhyName = ui.draggable.data('entityphyname');
	        entityLogName = ui.draggable.data('entitylogname');
	        attribphyname = ui.draggable.data('attribphyname');
	        attriblogname = ui.draggable.data('attriblogname');	        
	        componentNametxt = getEntityName(entityPhyName, entityLogName);
	        let draggedElementType = ui.draggable.data('type');
	        
	    	let colDiv = "";
	    	if(ui.draggable.hasClass('table-section')){
	    		//dragging a table, check if table has already been mapped
	    		
	    		let componentList = [];
	    		$('#box-body').find('div.box').each(function(){
	    			if(!componentList.includes($(this).find('ul li').data('componentname'))){
	    				componentList.push($(this).find('ul li').data('componentname'));
	    			}
	    		});
	    		
	    		let catalogName = ui.draggable.parents().eq(2).data('catalogname');
		        let entityPhyName = ui.draggable.parents().eq(2).data('entityphyname');
		        let entityLogName = ui.draggable.parents().eq(2).data('entitylogname');
		        let componentName = ui.draggable.parents().eq(2).data('componentname');
		        if(componentList.includes(componentName)){
		        	//component is already mapped, show only unmapped columns of component
		        	let mappedAttributes = [];
		        	$('#box-body').find('div.box').each(function(){
		        		let compNm = $(this).find('ul li').data('componentname');
		        		if(compNm == componentName){
		        			mappedAttributes.push($(this).find('ul li').data('attribphyname'));
		        		}
		        	});
		        	let selectionTable = '';
		        	ui.draggable.parent().find('ul li').each(function(i){
		        		let attribphyname = $(this).data('attribphyname');
		        		let attriblogname = $(this).data('attriblogname');
		        		if(!mappedAttributes.includes(attribphyname)){
		        			selectionTable += 
				    			`<tr data-attribphyname="${attribphyname}">
				    				<td>
			    						<div class="custom-control custom-checkbox">
										  <input type="checkbox" class="custom-control-input" id="tgt-sel-${i}" checked>
										  <label class="custom-control-label" for="tgt-sel-${i}"></label>
										</div>
			    					</td>
				    				<td data-componentname="${componentName}" data-catalogname="${catalogName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-attribphyname="${attribphyname}" data-attriblogname="${attriblogname}">
				    					${attribphyname} (${entityPhyName}.${catalogName})
				    				</td>
				    				<td></td>
				    			</tr>`;
		        		}
		        		$('#selectionTableBody').empty().append(selectionTable);
		        		$('#targetSelectionModal').modal('show');
		        		$('#targetSelectionModal').modal('handleUpdate');
			    		document.querySelector('#save-target-sel').removeEventListener('click', saveTargetSelection);
			    		document.querySelector('#save-target-sel').addEventListener('click', saveTargetSelection);
			    		document.querySelector('#sel-all-tgt-col').removeEventListener('click', toggleTargetColumnSelection);
			    		document.querySelector('#sel-all-tgt-col').addEventListener('click', toggleTargetColumnSelection);
		        	});
		        	
		        }else{
		        	let selectionTable = '';
		    		ui.draggable.parent().find('ul li').each(function(i){
		    			let attribphyname = $(this).data('attribphyname');
		    			let attriblogname = $(this).data('attriblogname');
		    			selectionTable += 
			    			`<tr data-attribphyname="${attribphyname}">
			    				<td>
		    						<div class="custom-control custom-checkbox">
									  <input type="checkbox" class="custom-control-input" id="tgt-sel-${i}" checked>
									  <label class="custom-control-label" for="tgt-sel-${i}"></label>
									</div>
		    					</td>
			    				<td data-componentname="${componentName}" data-catalogname="${catalogName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-attribphyname="${attribphyname}" data-attriblogname="${attriblogname}">
			    					${attribphyname} (${entityPhyName}.${catalogName})
			    				</td>
			    				<td></td>
			    			</tr>`;
		    		});
		    		$('#selectionTableBody').empty().append(selectionTable);
		    		$('#box-body').find('div.box:not(.dropped-box)').each(function(){
		    			let that = this;
		    			let targetEn = $(this).find('ul li').data('entityphyname');
		    			let targetAttr = $(this).find('ul li').data('attribphyname');
		    			if(targetEn == entityPhyName){
		    				$('#selectionTableBody tr').each(function(i){
		    					let selectionTableRow = this;
		    					if($(this).data('attribphyname') == targetAttr){
		    						$(that).find('div.sourceId ul li').each(function(index, value){
		    							let sourceCol = $(value).data('attribphyname');
			    						let sourceCollog = $(value).data('attriblogname');
			    						let sourceEn = $(value).data('entityphyname');
			    						let sourceEnlog = $(value).data('entitylogname');
			    						let sourceCatalog = $(value).data('catalogname');
			    						let sourceComponent = $(value).data('componentname');
				    					$(selectionTableRow).find('td:eq(2)').append(`<p class="mb-1" data-componentname="${sourceComponent}" data-catalogname="${sourceCatalog}" data-entityphyname="${sourceEn}" data-entitylogname="${sourceEnlog}" data-attribphyname="${sourceCol}" data-attriblogname="${sourceCollog}">${sourceCol} (${sourceEn}.${sourceCatalog})</p>`);
		    						});
			    				}
		    				});
		    			}
		    		});
		    		console.table($('#selectionTableBody').html());
		    		$('#targetSelectionModal').modal('show');
		    		$('#targetSelectionModal').modal('handleUpdate');
		    		document.querySelector('#save-target-sel').removeEventListener('click', saveTargetSelection);
		    		document.querySelector('#save-target-sel').addEventListener('click', saveTargetSelection);
		    		document.querySelector('#sel-all-tgt-col').removeEventListener('click', toggleTargetColumnSelection);
		    		document.querySelector('#sel-all-tgt-col').addEventListener('click', toggleTargetColumnSelection);
		        }
	    		
	    	}else{
	    		var clone = ui.draggable.clone();
		        clone.addClass("dropped");
		    	//let cloneTextNew = clone.text().trim();
		    	//cloneTextNew = cloneTextNew.substring(0,cloneTextNew.length-1);
		    	//cloneTextNew += '.'+catalogName+')';
		    	
		    	let entityName = getEntityName(entityPhyName, entityLogName);
		    	let cloneTextNew = clone.text().trim();     
		    	cloneTextNew += ` (${entityName}.${catalogName}`;
		    	
		    	colDiv += 
		    		`<div class="box">
						<div class="row">
							<div class="col borderCol targetdiv" id="targetAttributes-${i}">
								<ul class="Tree">
									<li title="Catalog Name : ${catalogName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-attribphyname="${attribphyname}" data-attriblogname="${attriblogname}" data-catalogname="${catalogName}" data-componentname="${componentNametxt}">
										<span>
											<i class="pointer fas fa-times text-danger"></i>
										</span>
										<span class="pl-2">${cloneTextNew}</span>
									</li>
								</ul>
							</div>
							<div class="col-md-auto text-center borderCol p-1" style="display:flex;justify-content:center">
								<span class="fa-layers fa-lg pointer" style=align-self:center; onclick="openUserDefinedVariableModel('${i}','','new',event)">
									<i class="far fa-plus-square text-info"></i>
								</span>
							</div>
							<div class="col-4 p-0 borderCol" id="ruleApplied${i}">
								
							</div>
							<div class="col sourceId ui-droppable" id="sourceAttributes-${i}">
								<ul class="Tree w-100" id="sourceDrop-${i}-${i}">
									
								</ul>
							</div>
						</div>
					</div>`
		    
			    $("#box-body").append(colDiv);
			    $("#newTargetCol"+boxIndex).removeClass("ui-droppable,highlight");
			    allowDrop();
	    	}
	    }
	});
}

function toggleTargetColumnSelection(){
	$('#selectionTableBody').find('tr').each(function(){
		if($('#sel-all-tgt-col').is(':checked')){
			$(this).find('td:eq(0) input').prop('checked', true);
		}else{
			$(this).find('td:eq(0) input').prop('checked', false);
		}
	});
}

function saveTargetSelection(){
	let i = $('#box-body').find('div.box').length;
	$('#selectionTableBody').find('tr').each(function(){
		let colDiv = "";
		if($(this).find('td:eq(0) input').is(':checked')){
			let targetComponentName = $(this).find('td:eq(1)').data('componentname');
			let targetCatalogName = $(this).find('td:eq(1)').data('catalogname');
			let targetEntityPhyName = $(this).find('td:eq(1)').data('entityphyname');
			let targetEntityLogName = $(this).find('td:eq(1)').data('entitylogname');
			let targetAttribPhyName = $(this).find('td:eq(1)').data('attribphyname');
			let targetAttribLogName = $(this).find('td:eq(1)').data('attriblogname');
			let divAppend = '';
			if($(this).find('td:eq(2) p').length > 0){
				$(this).find('td:eq(2) p').each(function(){
					let sourceComponentName = $(this).data('componentname');
					let sourceCatalogName = $(this).data('catalogname');
					let sourceEntityPhyName = $(this).data('entityphyname');
					let sourceEntityLogName = $(this).data('entitylogname');
					let sourceAttribPhyName = $(this).data('attribphyname');
					let sourceAttribLogName = $(this).data('attriblogname');
					divAppend += 
		    		    `<li title="Catalog Name : ${sourceCatalogName}" data-entityphyname="${sourceEntityPhyName}" data-entitylogname="${sourceEntityLogName}" data-attribphyname="${sourceAttribPhyName}" data-attriblogname="${sourceAttribLogName}" data-catalogname="${sourceCatalogName}" data-componentname="${sourceComponentName}"> 
		    					<span>
		    					${sourceAttribLogName} (${sourceEntityLogName}.${sourceCatalogName})
		    				</span>
		    				<span class="pointer">
		    					<i class="fas fa-times text-danger sourceRemove"></i>
		    				</span>
		    			</li>`
				});
			}
			
			colDiv += 
	    		`<div class="box dropped-box">
					<div class="row">
						<div class="col borderCol targetdiv" id="targetAttributes-${i}">
							<ul class="Tree">
								<li title="${targetAttribPhyName} (targetEntityPhyName.targetCatalogName)" data-componentname="${targetComponentName}" data-catalogname="${targetCatalogName}" data-entityphyname="${targetEntityPhyName}" data-entitylogname="${targetEntityLogName}" data-attribphyname="${targetAttribPhyName}" data-attriblogname="${targetAttribLogName}">
									<span>
										<i class="pointer fas fa-times text-danger"></i>
									</span>
									<span class="pl-2">${targetAttribLogName} (${targetEntityLogName}.${targetCatalogName})</span>
								</li>
							</ul>
						</div>
						<div class="col-md-auto text-center borderCol p-1" style="display:flex;justify-content:center">
							<span class="fa-layers fa-lg pointer" style=align-self:center; onclick="openUserDefinedVariableModel('${i}','','new',event)">
								<i class="far fa-plus-square text-info"></i>
							</span>
						</div>
						<div class="col-4 p-0 borderCol" id="ruleApplied${i}">
							
						</div>
						<div class="col sourceId ui-droppable" id="sourceAttributes-${i}">
							<ul class="Tree w-100" id="sourceDrop-${i}-${i}">
								${divAppend}
							</ul>
						</div>
					</div>
				</div>`
		}
		$('#box-body').append(colDiv);
		i++;
	});
	$('#targetSelectionModal').modal('hide');
	allowDrop();
}

function getSearchedRow(evt) {
	const type = evt.currentTarget.param1;
	if(type == 'target'){
		let input = $('#searchTargetAttr').val().toUpperCase();
	    $('.newColumn').find('div.box').each(function() {
	        var fullval = $(this).find('.targetdiv ul.Tree').find('li span:nth-child(2)').text().trim();

	        var attribute = fullval.substring(0, fullval.lastIndexOf('('));
	        if (attribute.toUpperCase().indexOf(input) > -1) {
	            $(this).show();
	        } else {
	            $(this).hide();
	        }
	    });
	}else{
		let input = $('#searchSourceAttr').val().toUpperCase();
	    $('.newColumn').find('div.box').each(function() {
	        var fullval = $(this).find('.sourceId ul.Tree').find('li span:first-child').text().trim();

	        var attribute = fullval.substring(0, fullval.lastIndexOf('('));
	        if (attribute.toUpperCase().indexOf(input) > -1) {
	            $(this).show();
	        } else {
	            $(this).hide();
	        }
	    });
	}
    
}

function searchAttributesSide(evt) {
	const type = evt.currentTarget.param1;
    if (type == 'target') {
        const input = $('#searchTargetSide').val().toUpperCase();
        $('.targetDiv').find('ul li ul li').each(function() {
            let value = $(this).find('span a').text().toUpperCase();
            value = value.substring(0,value.indexOf('(')).trim();
            if (value.indexOf(input) > -1) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    } else {
    	const input = $('#searchSourceSide').val().toUpperCase();
        $('.sourceDiv').find('ul li ul li').each(function() {
            let value = $(this).find('span a').text().toUpperCase();
            value = value.substring(0,value.indexOf('(')).trim();
            if (value.indexOf(input) > -1) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    }
}

window.showExpressionForTransformation = showExpressionForTransformation;
function showExpressionForTransformation(id,type,source) {
	console.log("Inside showExpressionForTransformation id: ",id)
	console.log("Inside showExpressionForTransformation type: ",type)
	console.log("Inside showExpressionForTransformation source: ",source)
	let toggleValueRule='';
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValueRule = "Physical";
	}else{
		toggleValueRule = "Logical";
	}
	let targetCol = $('#targetAttributes-'+id).find('ul li').attr('data-entityphyname');
	
	console.log("targetCol: ",targetCol);
	console.log("toggleValueRule: ",toggleValueRule);
	
	//alert(targetCol.split(':')[1].trim());
	if(targetCol != null){
		let targetTable = targetCol.trim();
		if(targetTable == 'Derived_Var'){
			$('.derivedDiv').removeClass('none');
			let varName = $('#targetAttributes-'+id).find('ul li').attr('data-attribphyname');
			$('#derivedVariableName').val(varName);
		}
	}
	
	/*if(targetCol.includes('(')){
		let targetTable = targetCol.substring(targetCol.indexOf('(')+1, targetCol.indexOf(')')).trim();
		if(targetTable == 'Derived_Var'){
			$('.derivedDiv').removeClass('none');
		}
	}*/
	
	
	$('#requiredVar').hide();
	let optionTag = '';
	if(toggleValueRule=='Physical'){
		$('.sourceDiv').each(function(){
			$(this).find('ul:nth-child(2) li').each(function(index,li){
				let column = $(li).find('span a').text().trim();
				if(column!='')
					optionTag += `<option value="${column}">${column}</option>`; 
			});
		});
	}else{
		console.log("Inside Source Div")
		$('.sourceDiv').each(function(){
			$(this).find('ul:nth-child(2) li').each(function(index,li){
				let column = $(li).find('span').attr('title').trim();
				if(column!='')
					optionTag += `<option value="${column}">${column}</option>`; 
			});
		});
		console.log("optionTag: ",optionTag);
	}

	var div = '';
	let selectedRule = $('#transformationRule'+id).find('option:selected').text().trim();
	//console.log("$$$ "+selectedRule);
	if(selectedRule == 'Rank Index'){
		$('#ruleSyntaxRow'+id).hide();
		var div = '';
		div = 
			`<div class="row mt-2">
					<div class="col-md-6">
						<div class="form-group">
							<label for="rankCol${id}">Rank Column</label>
							<select id="rankCol${id}" class="form-control form-control-sm">
								${optionTag}
							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="noRanks${id}">Number of Ranks</label>
							<input type="text" id="noRanks${id}" class="form-control form-control-sm" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="topBot${id}">Top/Bottom</label>
							<select id="topBot${id}" class="form-control form-control-sm">
								<option>Top</option>
								<option>Bottom</option>
							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="groupBy${id}">GroupBy</label>
							<select id="groupBy${id}" class="form-control form-control-sm">
								<option></option>
								${optionTag}
							</select>
						</div>
					</div>
				</div>`
		$('#appliedRule'+id).html(div);
		$('#appRule'+id).removeClass('none');
		
	}else if(selectedRule == 'Union'){
		//console.log('Yyy');
		let div = 'Union';
		$('#appliedRule'+id).html(div);
		$('#appRule'+id).removeClass('none');
	}else{
		optionTag = '';
		$('#appliedRule'+id).val('');
	    let type = '';
	    $('#trUlList'+id).find('a').each(function(){
	    	if($(this).text() == selectedRule){
	    		type = $(this).attr('title');
	    	}
	    });
	    console.log("type: ",type)
	    $('#src-box').find('div.catalog-tree').each(function(){
	    	let that = this;
	    	if($(this).children().length > 0){
	    		$(this).find('ul li ul li').each(function(){
	    			optionTag += '<option data-entityphyname="'+$(that).data('entityphyname')+'" data-entitylogname="'+$(that).data('entitylogname')+'" data-componentname="'+$(that).data('componentname')+'" data-attribphyname="'+$(this).data('attribphyname')+'" data-attriblogname="'+$(this).data('attriblogname')+'" data-catalogname="'+$(that).data('catalogname')+'" data-edit-value="'+$(that).data('entityphyname')+'.'+$(this).data('attribphyname')+'" value="'+$(this).data('attribphyname')+' ('+$(that).data('componentname')+'.'+$(that).data('catalogname')+')" >'+$(this).data('attribphyname')+' ('+$(that).data('componentname')+'.'+$(that).data('catalogname')+')</option>';
	    		});
	    	}
	    });
	    console.log(optionTag);
	    let selectedSources = [];
	    $('#sourceAttributes-'+id).find('ul li').each(function(){
	    	selectedSources.push($(this).data('attribphyname')+' ('+$(this).data('entityphyname')+'.'+$(this).data('catalogname')+')');
	    });
	    console.log(selectedSources);
		let divVal = getRuleSyntaxFromRule(selectedRule,optionTag,selectedSources,'mapper',type);
//		alert(divVal);
		$('.applyRule'+id).show();
		$('#appRule'+id).removeClass('none');
		$('#appliedRule'+id).html(divVal);
		$('#select0').val(selectedSources[0]);
		let _ruleMappings = document.querySelector('#ruleMappings').textContent;
		console.log('Rule Mappings');
		console.log(_ruleMappings);
		let _ruleMapObj = JSON.parse(_ruleMappings);
		let paramCount = '';
		for(let i = 0, len = _ruleMapObj.length; i < len; i++)
		{
			
			let ruleObj = _ruleMapObj[i];
			let ruleName = ruleObj.transformationRule.ruleName; // String - Trim Leading Spaces
			let ruleType = ruleName.substring(0,ruleName.indexOf('-')).trim();
			ruleName = ruleName.substring(ruleName.indexOf('-')+1, ruleName.length).trim(); // Trim Leading Spaces
			if(selectedRule === ruleName && ruleType.includes(type))
			{
				if(ruleObj.transformationRule.parameter == null)
					paramCount = 0;
				else
					paramCount = ruleObj.transformationRule.parameter.length;
			}
			if(ruleType=='Aggregate'){
				paramCount=1;
			}
		}
		
		let ruleId = selectedRule.replace(/ /g, "-").replace('/','-');
		if(ruleId!='GroupBy'){
			for(let p = 0; p < paramCount; p++){
				document.querySelector('.iTag'+ruleId+p).addEventListener('click',function(e){
					toggleInput($(e.target),id,optionTag);
		    	});
			}
		}
		if(selectedRule == 'Concatenation'){
			document.querySelector('.iTag'+ruleId+'1').addEventListener('click',function(e){
				toggleInput($(e.target),id,optionTag);
	    	});
			document.querySelector('.add1').addEventListener('click',function(e){
				addNewParamForConcat($(e.target),id,1,optionTag,ruleId);
	    	});
		}
		if(selectedRule == 'Difference between Dates'){
			document.querySelector('.iTag'+ruleId+'2').addEventListener('click',function(e){
				toggleInput($(e.target),id,optionTag);
	    	});
		}
	}
}

//function addNewParamForConcat(el, id, paramInd, options, ruleId){
//	let index = paramInd+1;
//	let radioButtonsWithNone = `<div class="custom-control custom-radio custom-control-inline none ml-2" title="String Constant">
//								  <input type="radio" class="custom-control-input" id="SCnone${index}" name="typeValueRadio${index}" value="SC" checked>
//								  <label class="custom-control-label" for="SCnone${index}">SC</label>
//								</div>
//								
//								<div class="custom-control custom-radio custom-control-inline none" title="Numeric Constant">
//								  <input type="radio" class="custom-control-input" id="NCnone${index}" name="typeValueRadio${index}" value="NC">
//								  <label class="custom-control-label" for="NCnone${index}">NC</label>
//								</div>
//								
//								<div class="custom-control custom-radio custom-control-inline none" title="Expression">
//								  <input type="radio" class="custom-control-input" id="EXPnone${index}" name="typeValueRadio${index}" value="EXP">
//								  <label class="custom-control-label" for="EXPnone${index}">EXP</label>
//								</div>`;
//	let divVal = `<div class="inp-param param${index}"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}${index}"></i><select id="select${index}" class="form-control form-control-sm custom-width ml-1 mr-1 mt-1" style="width:50%!important">${options}</select><input id="input${index}" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />${radioButtonsWithNone}<i class="pl-1 pointer fa fa-plus-circle text-info add${index}" title="Add another parameter"></i></div>`;
//	let parent = $(el).parent();
//	$(el).parent().after(divVal);
//	console.log($(el).parent());
//	document.querySelector('.add'+index).addEventListener('click',function(e){
//		addNewParamForConcat($(e.target),id,index,options);
//	});
//	document.querySelector('.iTag'+ruleId+index).addEventListener('click',function(e){
//		toggleInput($(e.target),id,options);
//	});
//	$(el).remove();
//	$(parent).append('<i class="pl-1 pointer fa fa-times text-danger removeParam'+paramInd+'" title="Remove Parameter"></i>')
//	document.querySelector('.removeParam'+paramInd).addEventListener('click',function(e){
//		removeParamForConcat($(e.target));
//	});
//}
//
//function removeParamForConcat(el){
//	$(el).parent().remove();
//}

window.filterTrRules = filterTrRules;
function filterTrRules(id,source) {
	
	let Rules = document.querySelector('#ruleArray').value;
    var option = '';
    Rules = Rules.substring(1,Rules.length-1);
    for(var j=0;j<Rules.split(',').length;j++){
    	option = option + 
    	'<option value="'+Rules.split(',')[j]+'">'+Rules.split(',')[j]+'</option>';
    }
    $('#transformationRule' + id).empty().append(option);
	
    var searchInput = $('#searchRule'+id).val();
    
    var divVal = '';
    var filter = searchInput.toUpperCase();
    for (var i = 0; i < $('#transformationRule' + id + ' option').length; i++) {
    	
        if ($('#transformationRule' + id + ' option')[i].value.toUpperCase().indexOf(filter) > -1) {
            divVal = divVal + '<option>' + $('#transformationRule' + id + ' option')[i].value + '</option>';
        }
    }
    $('#transformationRule' + id).empty().append(divVal);

    showExpressionForTransformation(id,'auto-variable',source);
    $('#mandatory').addClass('none');
}

window.saveTrRule = saveTrRule;
function saveTrRule(id,ruleNo,type,sourceDtls){
	//alert(1);
	const selSystemVal = $('#selectedSystem').val();
	const selMappingVal = $('#selectedMapping').val();
	let toggleValueSave='';
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValueSave = "Physical";
	}else{
		toggleValueSave = "Logical";
	}
	var arrow = '<';
	var selectedRule = '';
	var index = '';
	if(type=='new'){
		index = $('.divRule'+id).length;
	}else{
		/*let rlNm = $('#rulename'+id+ruleNo).text().split(':')[1].trim();
		if(rlNm=='Concatenation'){
			$('#sourceAttributes-'+id).find('ul li:nth-child(2)').remove();
		}*/
		index = ruleNo;
		$('#divRule'+id+ruleNo).remove();
		$('#divRuleHidden'+id+ruleNo).remove();
	}

	selectedRule = $('#transformationRule'+id).find('option:selected').text();
	if(selectedRule == '--Select Transformation Rule--'){
		selectedRule = $('#ruleBody'+id).find('tr:eq(0) td:eq(0)').text();
	}
	
	$('#ruleApplied'+id).parents().eq(1).removeClass('box-error');
	let sourceCol = sourceDtls.substring(0,sourceDtls.indexOf('(')).trim();
	let sourceEnt = sourceDtls.substring(sourceDtls.indexOf('(')+1,sourceDtls.indexOf(')')).trim();
	
		if (selectedRule.trim() === 'Concatenation') {
			let appendColumn = $('#select1').find('option:selected').text();
			let logAppendCol = $('#select1').find('option:selected').attr('title');
			if($('#sourceAttributes-'+id).find('ul li:nth-child(2)').html()!='undefined'){
				$('#sourceAttributes-'+id).find('ul li:nth-child(2)').remove();
			}
			var divAppend ='';
			if(toggleValueSave=='Physical'){
				divAppend = `<li title="${logAppendCol}" class="text-truncate"> 
					<span class="text-left">
						${appendColumn}
					</span>
					<span style="float:right">
						<i class="text-right pointer fas fa-times text-danger" onclick="removeSourceAttributes(event)"></i>
					</span>
				</li>`;
			}else{
				divAppend = `<li title="${appendColumn}" class="text-truncate"> 
					<span class="text-left">
						${logAppendCol}
					</span>
					<span style="float:right">
						<i class="text-right pointer fas fa-times text-danger" onclick="removeSourceAttributes(event)"></i>
					</span>
				</li>`;
			}
			$('#sourceAttributes-'+id).find('ul').append(divAppend);
		}
		let dataType = '', precision = '', scale = '';
		if(!document.querySelector("#derivedDatatypeId").disabled){
			dataType = $("#derivedDatatypeId").val();
			precision = $("#derivedPrecisionId").val();
			scale = $("#derivedScaleId").val();
		}
		if(!document.querySelector("#derivedDatatypeId").disabled && (dataType =='' || precision =='' || scale == '')){
			$('#mandatory').removeClass('none');
		}else{
			$('#mandatory').addClass('none');
			let type = '';
		    $('#trUlList'+id).find('a').each(function(){
		    	if($(this).text() == selectedRule.trim()){
		    		type = $(this).attr('title');
		    	}
		    });
		    //alert(type);
			let ruleJsonObj = getRuleDetailsOnApply(selectedRule.trim(),sourceEnt,sourceCol,id,type);
			console.log(ruleJsonObj);
			if(ruleJsonObj.sourceEnAttr == ''){
				$('#sourceAttributes-'+id).find('ul').empty();
			}else{
				$('#sourceAttributes-'+id).find('ul').empty();
//				alert(ruleJsonObj.inputDataElements.length);
				for(let q = 0; q < ruleJsonObj.inputDataElements.length; q++){
					let data = ruleJsonObj.inputDataElements[q].inputLogDataElementRef +' ('+ ruleJsonObj.inputDataElements[q].componentname+'.'+ruleJsonObj.inputDataElements[q].catalogName+')';
					if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off'))
						data = ruleJsonObj.inputDataElements[q].inputDataElementRef +' ('+ ruleJsonObj.inputDataElements[q].componentname+'.'+ruleJsonObj.inputDataElements[q].catalogName+')';
					let el = 
						'<li title="Catalog Name : '+ruleJsonObj.inputDataElements[q].catalogName+'" data-entityphyname="'+ruleJsonObj.inputDataElements[q].inputComponentRef+'" data-entitylogname="'+ruleJsonObj.inputDataElements[q].inputLogComponentRef+'" data-componentname="'+ruleJsonObj.inputDataElements[q].componentname+'" data-attribphyname="'+ruleJsonObj.inputDataElements[q].inputDataElementRef+'" data-attriblogname="'+ruleJsonObj.inputDataElements[q].inputLogDataElementRef+'" data-catalogname="'+ruleJsonObj.inputDataElements[q].catalogName+'">'+
							'<span class="pr-2 ui-draggable ui-draggable-handle">'+
								data+
							'</span>'+
							'<span class="pointer ui-draggable ui-draggable-handle">'+
								'<i class="fas fa-times text-danger sourceRemove"></i>'+
							'</span>'+
						'</li>';
					$('#sourceAttributes-'+id).find('ul').append(el);
				}
			}
			let divVal = `<div class="pl-1 flx flex-rule-div${id}">
							<p class="m-0 unselectable pointer" ondblclick="editRule('${id}','null',event);" title="Double click on the rule to edit" data-rule-rulename="${ruleJsonObj.rulename}" data-rule-parameter='${JSON.stringify(ruleJsonObj.parameters)}' data-rule-type="${ruleJsonObj.type}" data-rule-description="${ruleJsonObj.ruleDescription}" data-derived-var-dataType="${ruleJsonObj.derivedDatatype}" data-derived-var-precision="${ruleJsonObj.derivedPrecision}" data-derived-var-scale="${ruleJsonObj.derivedScale}">${ruleJsonObj.ruleDescription}</p>
							<i class="text-right pointer fas fa-times text-danger"></i>
						</div>`;
			if(validateParametersBeforeSaving(id)){
		    	$('#ruleApplied'+id).empty().append(divVal);
		    	document.querySelector('.flex-rule-div'+id+' i').addEventListener('click',function(e){
		    		$(e.target).parent().remove();
		    	});
		    	document.querySelector('#addUDFConsole').style.width = '0px';
		    }else{
		    	$('#mandatory').removeClass('none');
		    }
		
			$('#overlay').addClass('none');
		}
}


function validateParametersBeforeSaving(id){
	let isValid = true;
	$('#appliedRule'+id).find('input[type!="hidden"].isRequired, select').each(function(){
		if($(this).val() == '' || $(this).val() == 'undefined'){
			isValid = false;
			$(this).css('border','1px solid red');
			$(this).keyup(function(){
				$(this).css('border','1px solid #ced4da');
			});
		}
	});
	return isValid;
}

function removeAppliedRule(event) {
    $(event.target).parents().eq(1).next('div').remove();
    $(event.target).parents().eq(1).remove();
}

function removeSourceAttributes(event) {
	$(event.target).parents().eq(1).remove();
}
function removeTargetAttributes(event) {
	var result = confirm("Deleting the target attribute will remove the entire row. If you want to change only the target column please drag and drop the required column"); 
	if(result)
		$(event.target).parents().eq(5).remove();
	//loop through all the boxes and update the id
	var t0 = performance.now();
	$('.box').each(function(i){
		$(this).find('div.targetdiv').attr('id','targetAttributes-'+i);
		
		$(this).find('div.col-md-auto span').attr('onclick','openUserDefinedVariableModel(\''+i+'\',\'\',\'new\',event)');
		$(this).find('div.col-md-4').attr('id','ruleApplied'+i);
		$(this).find('div.sourceId').attr('id','sourceAttributes-'+i);
		$(this).find('div.sourceId ul li').each(function(index,li){
			$(li).parent().attr('id','sourceDrop-'+i+'-'+i);
		});
	});
	var t1 = performance.now();
	//console.log("Call to reset id took " + (t1 - t0) + " milliseconds.")
}

window.getRuleDetails = getRuleDetails;
function getRuleDetails(id){
	//console.log('getting rule')
	var selectedSystem = $('#selectedSystem').val();
    var selectedMapping = $('#selectedMapping').val();
	var input = $('#predTyping'+id).val();
	//console.log(input);
	if(input!=''){
		$('.loaderGenerate').removeClass('invisible');
		$.ajax({
			url:ctx+'/RDW/Intelligent-Mapper/system-mapping/' + selectedSystem + '/data-mapping/' + selectedMapping + '/predictive-mapping/attribute-mapping/getRuleDetails?input='+encodeURI(input),
			type:'get',
			success:function(data){
				//console.log(data);
				//var data = 'formula for |"trim leading and trailing spaces of EMPLOYEE_CODE" |trim leading|LTRIM(EMPLOYEE_CODE)';
				$('.loaderGenerate').addClass('invisible');
				var ruleSyntax = '',ruleSelected = '', otherString = '';
				if(data.includes('|')){
					ruleSyntax = data.substring(data.lastIndexOf('|')+1,data.length);
					otherString = data.substring(0,data.lastIndexOf('|'));
					ruleSelected = otherString.substring(otherString.lastIndexOf('|')+1,otherString.length);
				}
				
				var div =   '<tr class="text-center">'+
								'<td>'+ruleSelected+'</td>'+
								'<td>'+ruleSyntax+'</td>'+
							+'</tr>';
				
				$('#ruleBody'+id).empty().append(div);		
				$('#ruleTable'+id).removeClass('none');
				$('#saveBt'+id).removeClass('none');
			}
		});
	}
}

window.openUserDefinedVariableModel = openUserDefinedVariableModel;
function openUserDefinedVariableModel(index,ruleNo,type,event){
	//console.log(event);
		let source = $('#sourceAttributes-'+index).find('ul li:first-child span:first-child').text().trim();
		let Rules = document.querySelector('#ruleArray').value;
		let logTargetEnAttr = $('#targetAttributes-' + index).find('ul li').find('span:nth-child(2)').text().trim();
		let logTargetEntity = logTargetEnAttr.substring(logTargetEnAttr.lastIndexOf('(')+1,logTargetEnAttr.lastIndexOf(')')).trim();
		let option = '';
        Rules = Rules.substring(1,Rules.length-1);
        for(var j=0;j<Rules.split(',').length;j++){
        	option = option + 
        	`<option value="${Rules.split(',')[j]}">${Rules.split(',')[j]}</option>`;
        }
        console.log('source: ',source);
        console.log('logTargetEnAttr',logTargetEnAttr);
        console.log('logTargetEntity: ',logTargetEntity);
		let div = getRuleConsole(index,ruleNo,type,event,source,option,logTargetEntity);
		console.log("div: ",div)
		
		$('.applyRule').empty().append(div.split('@@')[0]);
		$('.applyRule1').empty().append(div.split('@@')[1]);
		$('#requiredVar').hide();
		if(logTargetEntity != "Derived_Var"){
			$("#derivedDatatypeId").prop("disabled",true);
			$("#derivedPrecisionId").prop("disabled",true);
			$("#derivedScaleId").prop("disabled",true);
		}else{
			$("#derivedDatatypeId").prop("disabled",false);
			$("#derivedPrecisionId").prop("disabled",false);
			$("#derivedScaleId").prop("disabled",false);
		}
		$("#derivedDatatypeId").val("String");
		$("#derivedPrecisionId").val("");
		$("#derivedScaleId").val("");
		$('#ruleSection'+index).removeClass('none');
		document.querySelector('#consoleheading').innerHTML = '<strong>Apply Transformation Rule</strong>';
		$('.divCheck').removeClass('none');
		$('#UserDefined').addClass('none');
		$('.dropdown-toggle').dropdown();
	
	document.querySelector('#addUDFConsole').style.width = '700px';
	$('#overlay').removeClass('none');
	//$('#'+type+index).removeClass('none');
	
	$('.ruleSection').find('li').click(function(){
		var id = $(this).parent().find('li:first').attr('id');
		$('.ulDiv').addClass('none');
		$('#ruleSection'+id).find('li').find('a.active').removeClass('active');
		var href = $(this).find('a').attr('href');
		
		$(href).removeClass('none');
		$(this).find('a').addClass('active');
		if(href == '#predictiveTyping'+id){
			$('.applyRule1').hide();
		}else{
			$('.applyRule1').show();
		}
	});
	
	let transformationArray = $('#ruleArray').val();
	let newArray = [];
	transformationArray = transformationArray.substring(1,transformationArray.length);
	 for(let value in transformationArray.split(',')){
		 newArray.push(transformationArray.split(',')[value]+' of');
		 newArray.push(transformationArray.split(',')[value]+' and');
		 newArray.push(transformationArray.split(',')[value]+' with');
		 newArray.push(transformationArray.split(',')[value]+' from');
		 newArray.push(transformationArray.split(',')[value]+' for');
		 newArray.push(transformationArray.split(',')[value]+' on');
	 }
	
	callAutocompleteFunction(newArray,index);
	$('.card-deck').find('div.card:first-child').click();
}

function showList(id,type,type2, source){
	$('#trUlList'+id).hide();
	$('.fa-stack-2x').css("opacity","0.3");
	$('#'+type+'_i').css("opacity","1");
	var trfmRules = $('#trfmRules').text();
	$('.heading').removeClass('text-dark').addClass('text-muted');
	$('#head'+id).removeClass('text-muted').addClass('text-dark');
	trfmRules = trfmRules.substring(1,trfmRules.length-1);
	var ruleArrayFortype = [];
	
	for(var rule of trfmRules.split(',')){
		var firstRulePart = rule.substring(0,rule.indexOf('-')).trim();
		var secondRulePart = rule.substring(rule.indexOf('-')+1,rule.length).trim();
		if(firstRulePart.includes(type) || firstRulePart.includes(type2)){
			if(secondRulePart=='Joiner' || secondRulePart=='Filter')
				continue;
				ruleArrayFortype.push(secondRulePart);
		}
	}
	var divVal = '';
	for(var rule of ruleArrayFortype){
		divVal += `<a href="#" class="list-group-item list-group-item-action" title="${type}">${rule}</a>`
	}
	$('#trUlList'+id).empty().append(divVal);
	$('#trUlList'+id).slideDown();
	allowClick(id,source);
}

function allowClick(id,source){
	$('#trUlList'+id).find('a').click(function(){
		let title = $(this).attr('title');
		var val = $(this).text();
		val = val.substring(val.indexOf('-')+1,val.length).trim();
		$('#searchRule'+id).val(val);
		$('#ruleType'+id).text(title);
		filterTrRules(id,source);
	});
}

function closeConsole(){
	document.querySelector('#addUDFConsole').style.width = '0px';
	$('#overlay').addClass('none');
}

function addUserDefinedVariables(){
	let i = $('#box-body').find('div.box').length;
	let boxIndex = $('.newColumn').find('div.newTargetCol').length - 1;
	let variableCol = $('#derivedAttr').val();
	
	const options = {
			"closeButton" : true,
			"closeMethod" : 'fadeOut',
			"closeDuration" : 300,
			"closeEasing" : 'swing',
			"preventDuplicates" : true,
			"progressBar" : true,
			"positionClass" : 'toast-bottom-right'
		}
	var index = $('#src-box').find('div.tree').length;
	
	let duplicateCol = false;
	$('#box-body').find('div.derived-row').each(function(){
		let val = $(this).find('div.targetdiv ul li span:nth-child(2)').text();
		let col = val.substring(0,val.lastIndexOf('(')).trim();
		if(col == variableCol){
			duplicateCol = true;
		}
	});
	if(duplicateCol){
		Swal.fire({
			  type: 'error',
			  title: 'Duplicate Column',
			  timer: 2000,
			  text: 'The applied derived column already exists. Please choose a different name',
			})
	}else{
		
		let colDiv = "";
		colDiv = colDiv +'<div class="box bg-light derived-row">'+
		
		'<div class="row">'+
				'<div class="col borderCol targetdiv" id="targetAttributes-'+i+'">'+
					'<ul class="Tree">'+
						/*'<li title="'+variableCol+ ' (Derived_Var)">'+*/
					'<li title="Catalog Name : Derived_Attributes" drvtitle="Derived_Var"  data-entityphyname="Derived_Var" data-entitylogname="Derived_Var" data-attribphyname="'+variableCol+ '" data-attriblogname="'+variableCol+ '" data-catalogname="Derived_Attributes" data-componentname="Derived_Var">'+
							'<span>'+
								'<i class="pointer fas fa-times text-danger" onclick="removeTargetAttributes(event)"></i>'+
							'</span>'+
							'<span class="pl-2">'+variableCol+ ' (Derived_Var)</span>'+
						'</li>'+
					'</ul>'+
				'</div>'+
				'<div class="-md-auto text-center borderCol p-1" style="display:flex;justify-content:center">'+
					'<span class="fa-layers fa-lg pointer" style=align-self:center; onclick="openUserDefinedVariableModel(\''+i+'\',\'\',\'new\',event)">'+
						'<i class="far fa-plus-square text-info"></i>'+
				'</div>'+
				'<div class="col-4 p-0 borderCol" id="ruleApplied'+i+'">'+
					
				'</div>'+
				'<div class="col sourceId ui-droppable" id="sourceAttributes-'+i+'">'+
					'<ul class="Tree w-100" id="sourceDrop-'+i+'-'+i+'">'+
						
					'</ul>'+
				'</div>'+
			'</div>'+
		'</div>';
		
		$("#box-body").append(colDiv);
		$("#newTargetCol"+boxIndex).removeClass("ui-droppable,highlight");
		allowDrop();
		var elem = $("#targetAttributes-"+i),
	    container = $('.newColumn'),
	    pos = elem.position().top + container.scrollTop() - container.position().top;

		container.animate({
		  scrollTop: pos
		});
		
		addUDFVariableInSourceBox();
		
	}
	
}

function addUDFVariableInSourceBox(){
	let variableCol = $('#derivedAttr').val();
		if($('#udfTree0').length == 0){
			let derivedColDiv = 
				`<div class="tree well sourceDiv catalog-tree mt-2" id="udfTree0" data-entityphyname="Derived_Var" data-entitylogname="Derived_Var" data-componentname="Derived_Var" data-catalogname="Derived_Attributes">
			        <ul class="sourceEntities_ul0 Tree">
				        <li class="parent_li">
				            <span class="text-dark pl-2 ov-el">
				                <i class="far fa-minus-square" aria-hidden="true"></i>
				                <a class="text-dark pl-1 smfont ov-el" href="#">Derived Attributes</a>
				            </span>
				            <ul></ul>
				        </li>
			    	</ul>
				</div>`
				$('#src-box').append(derivedColDiv);
		}
		var divVal = 
			'<li data-attribphyname="'+variableCol+'" data-attriblogname="'+variableCol+'">'+
        	'<span title="'+variableCol+' (Derived_Var)" class="pl-4 pt-2 smfont tbl"><a href="#" class="text-dark">'+variableCol+' (Derived_Var)</a></span>'+
        '</li>';
	$('.sourceEntities_ul0').find('li ul').append(divVal);
	$('#udfTree0').removeClass('none');
	allowDrag();
	$('#userDefined').modal('hide');	
}

function allowDrag(){
	$(".Tree span.tbl").draggable({
	    revert: "invalid",
	    cursor: "move",
	    refreshPositions: true,
	    drag: function(event, ui) {
	        ui.helper.addClass("draggable");
	    },
	    helper: function(event) {
	        return $(event.target).clone().css({
	            width: $(event.target).width()
	        });

	    }
	});
}

function generateExcelSheet(type) {
	$('.loaderEnh').removeClass('none');
	let toggleValueSave='';
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValueSave = "Physical";
	}else{
		toggleValueSave = "Logical";
	}
		let map = [];
	    let entityMap = {};
	    $('.box').each(function(i) {
	    	let sourceEntityAttributeArray  = [];
	    	let transformationRuleList = [];
	    	
	    	let sourceCatalog = '', targetCatalog = '', targetEntityPhyName = '', targetEntityLogName = '', targetAttribPhyName = '', targetAttribLogName = '', sourceEntityPhyName = '', sourceEntityLogName = '', sourceAttribPhyName = '', sourceAttribLogName = '',targetCompName='';
	    	const targetLi = $('#targetAttributes-' + i).find('ul li');
	    	targetCatalog = $(targetLi).data('catalogname');
	    	targetEntityPhyName = $(targetLi).data('entityphyname');
	    	targetEntityLogName = $(targetLi).data('entitylogname');
	    	targetAttribPhyName = $(targetLi).data('attribphyname');
	    	targetAttribLogName = $(targetLi).data('attriblogname');
	    	targetCompName = $(targetLi).data('componentname');
	    	$('#sourceAttributes-' + i).find('ul li').each(function(){
	    		sourceCatalog = $(this).data('catalogname');
	    		sourceEntityPhyName = $(this).data('entityphyname');
	    		sourceEntityLogName = $(this).data('entitylogname');
	    		sourceAttribPhyName = $(this).data('attribphyname');
	    		sourceAttribLogName = $(this).data('attriblogname');
	    		let sourceObject = {
	    				"sourceCatalog" : sourceCatalog,
	    				"sourceFeedRef" : sourceEntityPhyName,
	    				"sourceDataElementRef" : sourceAttribPhyName,
	    				"logicalSourceFeedRef" : sourceEntityLogName,
	    				"logicalSourceDataElementRef" : sourceAttribLogName,
	    				"sourceCompName" : $(this).data('componentname'),
	    		}
	    		sourceEntityAttributeArray.push(sourceObject);
	    	});
	    	
	    	let derivedVarDetails = {};
	    	let componentName = '', derivedElmnt = '';
	    	if($('#ruleApplied'+i).children().length>0){
	    		let noOfRules = $('#ruleApplied'+i).find('div').length;
	    		
	    		$('#ruleApplied'+i).find('div').each(function(index){
	    			derivedElmnt = $('.flex-rule-div'+i+' > p');
	    	    	derivedVarDetails = {
	    	    			"dataType" : $(derivedElmnt).data("derived-var-datatype"),
	    	    			"precision" : $(derivedElmnt).data("derived-var-precision"),
	    	    			"scale" : $(derivedElmnt).data("derived-var-scale"),
	    	    	}
	    			let ruleName = $(derivedElmnt).data("rule-rulename");
	    	    	console.log($(derivedElmnt).data("rule-parameter"));
	    	    	console.log(JSON.stringify($(derivedElmnt).data("rule-parameter")));
	        		let parameterValue = JSON.stringify($(derivedElmnt).data("rule-parameter"));
	        		let ruleDescription = $(derivedElmnt).text().trim();
	        		let ruleType = $(derivedElmnt).data("rule-type");
	        		
	        		let ruledesctext = $('.flex-rule-div'+i+' > p').data("rule-descriptiontext");
	        		
	        		if(noOfRules == (index+1)){
	        			componentName = targetCompName+'_'+targetAttribPhyName;
	        		}else{
	        			componentName = targetEntityPhyName+'_'+targetAttribPhyName+'_'+index;
	        		}
	        		let prevComponent = '';
	        		if(index === 0) {
	        			prevComponent = sourceEntityPhyName;
	        		}else{
	        			prevComponent = targetEntityPhyName+'_'+targetEntityPhyName+'_'+(index-1);
	        		}
	        		
	        		let transformation_rule = {
	    					"ruleName" : ruleName,
	    					"componentName" : componentName,
	    					"parameter" : $(derivedElmnt).data("rule-parameter"),
	    					"prevComponentRef" : prevComponent,
	    					"ruleDescription" : ruleDescription,
	    					"ruleType" : ruleType,
	    					"ruledesctext" : ruledesctext
	    			}
	        		transformationRuleList.push(transformation_rule);
	        		
	        	});
	    		
	    		entityMap = {
	    				"targetCatalog" : targetCatalog,
	    				"targetEntityRef" : targetEntityPhyName,
	    		        "targetAttributeRef": targetAttribPhyName,
	    		        "logicalTargetEntityRef" : targetEntityLogName,
	    		        "logicalTargetAttributeRef" : targetAttribLogName,
	    		        "srcAtrRefList" : sourceEntityAttributeArray,
	    		        "transformationRuleList": transformationRuleList,
	    		        "derivedVarDetails" : derivedVarDetails,
	    		        "targetCompName" : targetCompName,
	    		    }
	    	    map.push(entityMap);
	    	}else{
	    		entityMap = {
	    				"targetCatalog" : targetCatalog,
	    				"targetEntityRef" : targetEntityPhyName,
	    		        "targetAttributeRef": targetAttribPhyName,
	    		        "logicalTargetEntityRef" : targetEntityLogName,
	    		        "logicalTargetAttributeRef" : targetAttribLogName,
	    		        "srcAtrRefList" : sourceEntityAttributeArray,
	    		        "transformationRuleList": transformationRuleList,
	    		        "derivedVarDetails" : derivedVarDetails,
	    		        "targetCompName" : targetCompName,
	    		    }
	    	    map.push(entityMap);
	    	}
	    	
	    });
	    
	    let catalogArray = [];
	    let catalogJson = {};
	    $('#src-box').find('div.catalog-tree').each(function(i){
    		catalogJson = {
	    			"catalogName" : $(this).data('catalogname'),
					"catalogType" : "Source",
					"entityName" : $(this).data('entityphyname'),
					"logicalEntityName" : $(this).data('entitylogname'),
					"componentName" : $(this).data('componentname'),
					"elementName" : $(this).data('elementname'),
	    	}
	    	catalogArray.push(catalogJson);
	    });
	    $('#tgt-box').find('div.catalog-tree').each(function(i){
	    	catalogJson = {
	    			"catalogName" : $(this).data('catalogname'),
					"catalogType" : "Target",
					"entityName" : $(this).data('entityphyname'),
					"logicalEntityName" : $(this).data('entitylogname'),
					"componentName" : $(this).data('componentname'),
					"elementName" : $(this).data('elementname'),
	    	}
	    	catalogArray.push(catalogJson);
	    });
	    
		/*var catalogJSON = {};
	    var catalogJsonContent = [];
	    catalogJSON.catalogJsonContent = catalogJsonContent;
	    $('#src-box').find('.sourceDiv').each(function(i) {
	    	if(toggleValueSave=="Physical"){
	    		if($(this).hasClass('newMember')){
	    			var newEntityName = '';
	    			if(!($(this).attr('id').includes('lkpTree'))){
	    				newEntityName = $(this).find('.Tree .parent_li span:first-child a').html();
	    			}else{
	    				newEntityName = $(this).find('.Tree .parent_li span:first-child').attr('title');
	    			}
	            	var newCatalogName = $(this).find('.Tree .parent_li span:first-child a').attr('title');
	            	//console.log("Data---> "+newEntityName+"::"+newCatalogName);
	            	if(!($(this).attr('id').includes('udfTree'))){
	            		var appendDataToCatalogJSON = {
	            	    		"catalogName":newCatalogName,
	            	    		"catalogType":"Source",
	            	    		"entityName":newEntityName
	            	    	}
	        	    	 catalogJSON.catalogJsonContent.push(appendDataToCatalogJSON);
	            	}
	        	}else{
	        		var newEntityName = $(this).find('.Tree .parent_li span:first-child a').html();
	        		for(let data in oldData){
	        			//console.log("This---> "+oldData[data].entityName);
	        			if(oldData[data].entityName==newEntityName && oldData[data].catalogType=='Source'){
	        				var appendDataToCatalogJSON = {
	                	    		"catalogName":oldData[data].catalogName,
	                	    		"catalogType":"Source",
	                	    		"entityName":newEntityName
	                	    	}
	            	    	 catalogJSON.catalogJsonContent.push(appendDataToCatalogJSON);
	        			}
	        		}
	        	}
	    	}else if(toggleValueSave=="Logical"){
	    		if($(this).hasClass('newMember')){
	    			if(!($(this).attr('id').includes('lkpTree'))){
	    				newEntityName = $(this).find('.Tree .parent_li span:first-child a').html();
	    			}else{
	    				newEntityName = $(this).find('.Tree .parent_li span:first-child').attr('title');
	    			}
	    			let newEntityName = $(this).find('.Tree .parent_li span:first-child').attr('title');
	            	var newCatalogName = $(this).find('.Tree .parent_li span:first-child a').attr('title');
	            	//console.log("Data---> "+newEntityName+"::"+newCatalogName);
	            	if(!($(this).attr('id').includes('udfTree'))){
	            		var appendDataToCatalogJSON = {
	            	    		"catalogName":newCatalogName,
	            	    		"catalogType":"Source",
	            	    		"entityName":newEntityName
	            	    	}
	        	    	 catalogJSON.catalogJsonContent.push(appendDataToCatalogJSON);
	            	}
	        	}else{
	        		var newEntityName = $(this).find('.Tree .parent_li span:first-child').attr('title');
	        		for(let data in oldData){
	        			//console.log("This---> "+oldData[data].entityName);
	        			if(oldData[data].entityName==newEntityName && oldData[data].catalogType=='Source'){
	        				var appendDataToCatalogJSON = {
	                	    		"catalogName":oldData[data].catalogName,
	                	    		"catalogType":"Source",
	                	    		"entityName":newEntityName
	                	    	}
	            	    	 catalogJSON.catalogJsonContent.push(appendDataToCatalogJSON);
	        			}
	        		}
	        	}
	    	}
	    });
	    $('#tgt-box').find('.targetDiv').each(function(i) {
	    	if(toggleValueSave=="Physical"){
	    		if($(this).hasClass('newMember')){
	        		var newEntityName = $(this).find('.Tree .parent_li span:first-child a').html();
	            	var newCatalogName = $(this).find('.Tree .parent_li span:first-child a').attr('title');
	            	//console.log("Data---> "+newEntityName+"::"+newCatalogName)
	            	var appendDataToCatalogJSON = {
	            		"catalogName":newCatalogName,
	            		"catalogType":"Target",
	            		"entityName":newEntityName
	            	}
	            	 catalogJSON.catalogJsonContent.push(appendDataToCatalogJSON);
	        	}else{
	        		var newEntityName = $(this).find('.Tree .parent_li span:first-child a').html();
	        		for(let data in oldData){
	        			//console.log("This target---> "+oldData[data].entityName);
	        			if(oldData[data].entityName==newEntityName && oldData[data].catalogType=='Target'){
	        				var appendDataToCatalogJSON = {
	                	    		"catalogName":oldData[data].catalogName,
	                	    		"catalogType":"Target",
	                	    		"entityName":newEntityName
	                	    	}
	            	    	 catalogJSON.catalogJsonContent.push(appendDataToCatalogJSON);
	        			}
	        		}
	        	}
	    	}else if(toggleValueSave=="Logical"){
	    		if($(this).hasClass('newMember')){
	        		var newEntityName = $(this).find('.Tree .parent_li span:first-child').attr('title');
	            	var newCatalogName = $(this).find('.Tree .parent_li span:first-child a').attr('title');
	            	//console.log("Data---> "+newEntityName+"::"+newCatalogName)
	            	var appendDataToCatalogJSON = {
	            		"catalogName":newCatalogName,
	            		"catalogType":"Target",
	            		"entityName":newEntityName
	            	}
	            	 catalogJSON.catalogJsonContent.push(appendDataToCatalogJSON);
	        	}else{
	        		var newEntityName = $(this).find('.Tree .parent_li span:first-child').attr('title');
	        		for(let data in oldData){
	        			//console.log("This target---> "+oldData[data].entityName);
	        			if(oldData[data].entityName==newEntityName && oldData[data].catalogType=='Target'){
	        				var appendDataToCatalogJSON = {
	                	    		"catalogName":oldData[data].catalogName,
	                	    		"catalogType":"Target",
	                	    		"entityName":newEntityName
	                	    	}
	            	    	 catalogJSON.catalogJsonContent.push(appendDataToCatalogJSON);
	        			}
	        		}
	        	}
	    	}
	    });*/
	    console.log(catalogArray);
	    let formattedJson = JSON.stringify(map);
	    console.log('//////////////////////////////////////////////////////////////');
	    console.log('//////////////////////////////////////////////////////////////');
	    console.log("formattedJson:",formattedJson);
	    let updatedCatalogData = JSON.stringify(catalogArray);
	    //updatedCatalogData = updatedCatalogData.substring(updatedCatalogData.indexOf(':') + 1, updatedCatalogData.length - 1);
	    //console.log("@#@##@ "+updatedCatalogData);
	    $('#map').val(formattedJson);
	    $('#type').val(type);
	    let existingJson = document.querySelector('#mappingArray').textContent;
	    console.log('//////////////////////////////////////////////////////////////');
	    console.log('//////////////////////////////////////////////////////////////');
	    console.log('existingJson: ',existingJson);
	    console.log('changeCheck: ',changeCheck);
	    let chunkArray = _.chunk(JSON.parse(formattedJson), 100);
	    console.log('//////////////////////////////////////////////////////////////');
	    console.log('chunkArray: ',chunkArray);
	    if(_.isEqual(JSON.parse(existingJson), JSON.parse(formattedJson)) && !changeCheck){
	    	console.log("No changes in the attribute design area");
	    	$('.loaderEnh').addClass('none');
	    	if(validateAttributeMapDetails(type).length > 0 && type == 'next'){
	    		let errArr = validateAttributeMapDetails(type);
	    		let div = '';
	    		//console.log(errArr);
	    		errArr.forEach(function(arr){
	    			div += `<p class="p-1">${arr}</p>`;
	    		});
	    		//console.log(div);
	    		Swal.fire({
					  type: 'warning',
					  confirmButtonText: 'Proceed',
					  showCloseButton: true,
					  showCancelButton: true,
					  html:div,
					  title: 'Following Target elements require either a source column or a transformation rule',
					}).then(function(result){
						if(result.value){
							runSync(chunkArray,type,updatedCatalogData);
				        }else if(result.dismiss == 'cancel'){
				           Swal.close();
				        }
						
					});
			}else if(validateAttributeMapDetails(type).length == 0 && type == 'next'){
				runSync(chunkArray,type,updatedCatalogData);
			}
	    	else{
				Swal.fire({
					  type: 'info',
					  title: 'Data Unchanged',
					  timer: 2000,
					  text: 'No change has been detected from the previous state!',
					})
			}
	    }else{
	    	console.log("Any changes in the attribute design area");
	        if(formattedJson!=''){
	        	//console.log('Submitting form');
	    		
	    		if(validateAttributeMapDetails(type).length > 0 && type != 'save'){
	    			let errArr = validateAttributeMapDetails(type);
		    		let div = '';
		    		//console.log("errArr:",errArr);
		    		errArr.forEach(function(arr){
		    			div += `<p class="p-1">${arr}</p>`;
		    		});
		    		//console.log(div);
		    		Swal.fire({
						  type: 'warning',
						  confirmButtonText: 'Proceed',
						  showCloseButton: true,
						  showCancelButton: true,
						  html:div,
						  title: 'Following Target elements require either a source column or a transformation rule',
						}).then(function(result){
							if(result.value){
								runSync(chunkArray,type,updatedCatalogData);
					        }else if(result.dismiss == 'cancel'){
					        	$('.loaderEnh').addClass('none');
					        	Swal.close();
					        }
							
						});
	    		}else{
	    			//console.log(chunkArray.length)
	    			console.log(JSON.stringify(chunkArray))
	    			runSync(chunkArray,type,updatedCatalogData);
	    		}
	        }else{
	        	$('.loaderEnh').addClass('none');
	        	Swal.fire({
					  type: 'error',
					  title: 'Failed',
					  timer: 2000,
					  text: 'Unable to save attribute design',
					})
	        }
	    }
}

async function runSync(chunkArray,type,updatedCatalogData) {
	$('.loaderEnh').removeClass('none');
	let result = '';
	console.log("////////////////chunkArray.length//////////////: ",chunkArray.length)
	for (var i = 0; i < chunkArray.length; i++) {		
       try{
    	   result = await saveEachChunk(chunkArray[i],i,type,updatedCatalogData);
    	   //console.log(result);
    		$('#mappingArray').empty().append(result);
    		$('.loaderEnh').addClass('none');
    		if(i == (chunkArray.length-1)){
    			Swal.fire({
  				  type: 'success',
  				  title: 'Successfull',
  				  timer: 2000,
  				  text: 'Schema attribute design have been saved successfully',
  				})
    		}
    		
    		if(type != 'save'){
    			document.querySelector('#attributeForm').submit();
    		}
       }catch(e){
    	   //console.log(e);
       		$('.loaderEnh').addClass('none');
       		Swal.fire({
				  type: 'error',
				  timer: 2000,
				  title: 'Failed',
				  text: 'Unable to save schema attribute design',
				})
       }
       $('.loaderEnh').addClass('none');
    }
}

async function saveEachChunk(chunk,i,type,updatedCatalogData){
	console.log("////////////////chunk//////////////: ",chunk)
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	const selectedSystem = $('#selectedSystem').val();
	const selectedMapping = $('#selectedMapping').val();
	//const url = `${ctx}/RDW/Intelligent-Mapper/system-mapping/${selectedSystem}/data-mapping/${selectedMapping}/predictive-mapping/attribute-mapping/save`;
	const url = `${ctx}/schema-designer/${projectName}/${selectedEntityName}/attribute-design/save`;
	//console.log("chunk ID -- "+i);
	//console.log("JSON.stringify(chunk): ", JSON.stringify(chunk))
	return await 
    $.ajax({
      type:"POST",
      url: url,
	  headers: {'X-CSRF-Token': csrf_token },
      data : {"map" : JSON.stringify(chunk), "index" : i,"catalogJSON":updatedCatalogData},
    })
}

const validateAttributeMapDetails = (type) =>{
	let invalidTargetAttr = [];
	if(type == 'save'){
		return invalidTargetAttr;
	}else{
		$('.box').each(function(i) {
			if($('#sourceAttributes-' + i).find('ul').children().length == 0){
				//no source column selected. Transformation rule is required
				if($('#ruleApplied'+i).children().length == 0){
					//no transformation rule. Throw error
					invalidTargetAttr.push($('#targetAttributes-'+i).find('ul li span:nth-child(2)').text().trim());
					
				}
			}
		});
	}
	
	return invalidTargetAttr;
}

function toggleBtwnPhyLog(){
	let toggleValue = '';
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-on')){
		//Change to Physical Names
		$('#toggleBtnForPhyLog').addClass('fa-toggle-off');
		$('#toggleBtnForPhyLog').removeClass('fa-toggle-on');
		toggleValue = 'Physical';
	}else if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		//Change to Logical Names
		$('#toggleBtnForPhyLog').addClass('fa-toggle-on');
		$('#toggleBtnForPhyLog').removeClass('fa-toggle-off');
		toggleValue = 'logical';
	}
	
	//Toggle for target entities and Attributes
	$('#tgt-box').find('.targetDiv').each(function(i){
		updateTableColumnType(this, toggleValue);
	});
	
	$('#src-box').find('.sourceDiv').each(function(i){
		updateTableColumnType(this, toggleValue);
	});
	
	
	//Toggle for the Central Part
	$('.box').each(function(i){
		let targetCol = $('#targetAttributes-' + i).find('ul li');
		let attribPhyName = $(targetCol).data('attribphyname');
		let attribLogName = $(targetCol).data('attriblogname');
		let entityPhyName = $(targetCol).data('entityphyname');
		let entityLogName = $(targetCol).data('entitylogname');
		let componentName = $(targetCol).data('componentname');
		let catalogName = $(targetCol).data('catalogname');
		if(toggleValue == 'Physical'){
			$(targetCol).find('span:nth-child(2)').text(attribPhyName+' ('+componentName+'.'+catalogName+')');
			$(targetCol).find('span:nth-child(2)').attr('title', attribLogName+' ('+entityLogName+'.'+catalogName+')');
		}else{
			$(targetCol).find('span:nth-child(2)').text(attribLogName+' ('+entityLogName+'.'+catalogName+')');
			$(targetCol).find('span:nth-child(2)').attr('title', attribPhyName+' ('+componentName+'.'+catalogName+')');
		}
		$('#sourceAttributes-' + i).find('ul li').each(function(){
			let attribPhyName = $(this).data('attribphyname');
			let attribLogName = $(this).data('attriblogname');
			let entityPhyName = $(this).data('entityphyname');
			let entityLogName = $(this).data('entitylogname');
			let componentName = $(this).data('componentname');
			let catalogName = $(this).data('catalogname');
			if(toggleValue == 'Physical'){
				$(this).find('span:first').text(attribPhyName+' ('+componentName+'.'+catalogName+')');
				$(this).find('span:first').attr('title',attribLogName+' ('+entityLogName+'.'+catalogName+')');
			}else{
				$(this).find('span:first').text(attribLogName+' ('+entityLogName+'.'+catalogName+')');
				$(this).find('span:first').attr('title', attribPhyName+' ('+componentName+'.'+catalogName+')');
			}
		});
	});
}

function updateTableColumnType(el, toggleValue){
	let componentName = $(el).data('componentname');
	let logEntityName = $(el).data('entitylogname');
	let catalogName = $(el).data('catalogname');
	if(toggleValue == 'Physical'){
		$(el).find('.Tree .parent_li span:first a').html(componentName+' ('+catalogName+')');
		$(el).find('.Tree .parent_li span:first a').attr('title',logEntityName+' ('+catalogName+')');
		$(el).find('.Tree .parent_li ul li').each(function(){
			let attrPhyName = $(this).data('attribphyname');
			let attrLogName = $(this).data('attriblogname');
			$(this).find('span a').html(attrPhyName+ ' ('+componentName+')');
			$(this).find('span').attr('title',attrLogName+ ' ('+logEntityName+')');
		});
	}else{
		$(el).find('.Tree .parent_li span:first a').html(logEntityName+' ('+catalogName+')');
		$(el).find('.Tree .parent_li span:first a').attr('title',componentName+' ('+catalogName+')');
		$(el).find('.Tree .parent_li ul li').each(function(){
			let attrPhyName = $(this).data('attribphyname');
			let attrLogName = $(this).data('attriblogname');
			$(this).find('span a').html(attrLogName+ ' ('+logEntityName+')');
			$(this).find('span').attr('title',attrPhyName+ ' ('+componentName+')');
		});
	}
}

function addNewEmptyRow(){
	let id = $('#box-body').find('.box').length;
	let divVal=`<div class="box">
					<div class="row">
						<div class="col borderCol targetdiv ui-droppable" id="targetAttributes-${id}">
							<ul class="Tree" id="targetDrop-${id}">
								<li id="targetAttrLi-${id}" title="">
									<span class="ui-draggable ui-draggable-handle">
										<i class="pointer fas fa-times text-danger" onclick="removeTargetAttributes(event)"></i>
									</span>
									<span class="pl-2 ui-draggable ui-draggable-handle">
										
									</span>
								</li>
							</ul>
						</div>
						<div class="col-md-auto text-center borderCol p-1 rule-box" style="display:flex;justify-content:center">
							<span class="fa-layers fa-lg pointer" style="align-self:center;" onclick="openUserDefinedVariableModel('${id}','','new',event)">
								<i class="far fa-plus-square text-info"></i>
							</span>
						</div>
						<div class="col-md-4 p-0 borderCol" id="ruleApplied${id}">
						
						</div>
						<div class="col sourceId ui-droppable" id="sourceAttributes-${id}">
							<ul class="Tree w-100" id="sourceDrop-${id}-${id}">
							</ul>
						</div>
					</div>
				</div>`;
	$('#box-body').append(divVal);
	allowDrop();
}
window.openModal = openModal;
function openModal(id,type){
	event.stopPropagation();
	event.preventDefault();
	$('#deleteEntityModal').modal('show');
	$('#catType').val(type);
	$('#idSelected').val(id);
}

$('#deleteConfirmation').click(function(){
	let id = $('#idSelected').val();
	let type =  $('#catType').val();
	deleteTheSourceAndTarget(id,type);
});
function deleteTheSourceAndTarget(id,type){
	let toggleValueModal='';
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValueModal = "Physical";
	}else{
		toggleValueModal = "Logical";
	}
	changeCheck=true;
	//alert(type);
	let selSystem = $('#selectedSystem').val();
	let selMapping = $('#selectedMapping').val();
	let url = `${ctx}/RDW/Intelligent-Mapper/system-mapping/${selSystem}/data-mapping/${selMapping}/predictive-mapping/attribute-mapping/deleteEntity`;
	if(type=="Source"){
		let entityName='';
		if(toggleValueModal=='Logical'){
			entityName = $('#sourceEntities_ul'+id).find('li span:first').attr('title');
		}else{
			entityName = $('#sourceEntityName'+id).html();
		}
		let srcCatalogName = $('#sourceEntityName'+id).attr('title');
//		$.ajax({
//			url:url,
//			type:'POST',
//			data:{"catalogtype":"Source","entityName":entityName,"catalogName":srcCatalogName},
//			success:function(data){
				//console.log(entityName);
				$('#src-box').find('div.tree').each(function(){
					if(toggleValueModal=='Logical'){
						if($(this).find('.Tree li span:first').attr('title')==entityName){
							$(this).remove();
						}
					}else{
						if($(this).find('.Tree li span:first a').html()==entityName){
							$(this).remove();
						}
					}
				});
				$('#src-box').find('.sourceDiv').each(function(i){
					if(!($(this).attr('id').includes('udfTree'))){
						$(this).attr('id','sourceEntities'+i);
						$(this).find('ul:first').attr('id','sourceEntities_ul'+i);
						$(this).find('ul li span:first a').attr('id','sourceEntityName'+i);
						$(this).find('ul li span:first').attr('oncontextmenu','openModal(\''+i+'\',\'Source\')');
					}
				});
				$('#deleteEntityModal').modal('hide');
//			}
//		});
	}else if(type=="Target"){
		let entityName='';
		if(toggleValueModal=='Logical'){
			entityName = $('#targetEntities'+id).find('.Tree li span:first').attr('title');
		}else{
			entityName = $('#targetEntityName'+id).html();
		}
		let tgtCatalogName = $('#targetEntityName'+id).attr('title');
//		$.ajax({
//			url:url,
//			type:'POST',
//			data:{"catalogtype":"Target","entityName":entityName,"catalogName":tgtCatalogName},
//			success:function(){
				//console.log(entityName);
				$('#tgt-box').find('div.tree').each(function(){
					if(toggleValueModal=='Logical'){
						if($(this).find('.Tree li span:first').attr('title')==entityName){
							$(this).remove();
						}
					}else{
						if($(this).find('.Tree li span:first a').html()==entityName){
							$(this).remove();
						}
					}
				});
				$('#tgt-box').find('.targetDiv').each(function(i){
					$(this).attr('id','targetEntities'+i);
					$(this).find('ul:first').attr('id','targetEntities_ul'+i);
					$(this).find('ul li span:first a').attr('id','targetEntityName'+i);
					$(this).find('ul li span:first').attr('oncontextmenu','openModal(\''+i+'\',\'Target\')');
				});
				$('#deleteEntityModal').modal('hide');
//			}
//		});
	}
	$('#deleteConfirmation').prop('onclick',null); 
}

window.editRule = editRule;
function editRule(id,ruleNo,event){
	//console.log("Entering Edit Function for the attributes");
	let toggleValueRule='';
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValueRule = "Physical";
	}else{
		toggleValueRule = "Logical";
	}
	let type = $(event.target).data('rule-type');
	let selectedRule = $(event.target).data('rule-rulename');
	let parameter = $(event.target).data('rule-parameter');
	let source = $('#sourceAttributes-'+id).find('ul li:first-child span:first-child').text().trim();
	let sourceList = [];
	$('#sourceAttributes-'+id).find('ul li').each(function(){
		sourceList.push($(this).find('span:first-child').text().trim());
	})
	let sourceEnt = "" , sourceColumn = '';
    if(source.includes('(')){
    	sourceEnt = source.substring(source.indexOf('(')+1,source.indexOf(')')).trim();
    	sourceColumn = source.substring(0,source.indexOf('(')).trim();
    }
	let Rules = document.querySelector('#ruleArray').value;
	let logTargetEnAttr = $('#targetAttributes-' + id).find('ul li').find('span:nth-child(2)').text().trim();
	let logTargetEntity = logTargetEnAttr.substring(logTargetEnAttr.lastIndexOf('(')+1,logTargetEnAttr.lastIndexOf(')')).trim();
	let option = '';
    Rules = Rules.substring(1,Rules.length-1);
    for(var j=0;j<Rules.split(',').length;j++){
    	option += `<option value="${Rules.split(',')[j]}">${Rules.split(',')[j]}</option>`;
    }
	let div = getRuleConsole(id,ruleNo,type,event,source,option,logTargetEntity);
	console.log(div);
	$('.applyRule').empty().append(div.split('@@')[0]);
	$('.applyRule1').empty().append(div.split('@@')[1]);
	$('#ruleSection'+id).removeClass('none');
	document.querySelector('#consoleheading').innerHTML = '<strong>Apply Transformation Rule</strong>';
	$('.divCheck').removeClass('none');
	$('#UserDefined').addClass('none');
	document.querySelector('#addUDFConsole').style.width = '700px';
	$('#overlay').removeClass('none');
	
	$('#appRule'+id).removeClass('none');
	//$('#appliedRule'+id).html(divVal);
	
	$('#predictiveTyping'+id).hide();
	$('#manualSearch'+id).show();
	$('.applyRule1').show();
	$('#derVariableName').hide();
	let optionTag = '';
	$('#appliedRule'+id).val('');
//    let Datatype = '';
//    $('#trUlList'+id).find('a').each(function(){
//    	if($(this).text() == selectedRule){
//    		Datatype = $(this).attr('title');
//    	}
//    });
    $('#src-box').find('div.catalog-tree').each(function(){
    	let that = this;
    	if($(this).children().length > 0){
    		$(this).find('ul li ul li').each(function(){
    			optionTag += '<option data-entityphyname="'+$(that).data('entityphyname')+'" data-entitylogname="'+$(that).data('entitylogname')+'" data-attribphyname="'+$(this).data('attribphyname')+'" data-componentname="'+$(this).data('componentname')+'" data-attriblogname="'+$(this).data('attriblogname')+'" data-catalogname="'+$(that).data('catalogname')+'" data-edit-value="'+$(that).data('entityphyname')+'.'+$(this).data('attribphyname')+'" value="'+$(this).data('attribphyname')+' ('+$(that).data('componentname')+'.'+$(that).data('catalogname')+')" >'+$(this).data('attribphyname')+' ('+$(that).data('componentname')+'.'+$(that).data('catalogname')+')</option>';
    		});
    	}
    });
	
	getRuleDetailsForEdit(id,sourceList,selectedRule,type,parameter,optionTag);
	
	//Checking for Derived Var
	//let tgtAttr = $('#targetAttributes-'+id).find('.Tree li').attr('drvtitle');
	let tgtAttr = $('#targetAttributes-'+id).find('ul li').attr('data-entityphyname');
	if(tgtAttr.includes('Derived_Var')){
		
		$('#ruleApplied'+id).find('div').each(function(index){
			let derivedElmnt = $('.flex-rule-div'+id+' > p');
	    	$('#derivedDatatypeId').val(derivedElmnt.data("derived-var-datatype"));
			$('#derivedPrecisionId').val(derivedElmnt.data("derived-var-precision"));
			$('#derivedScaleId').val(derivedElmnt.data("derived-var-scale"));
	    	
		});
		//console.log('Derived Variable');
		/*let dataType = $('#ruleApplied-'+id).find('ul li').attr('data-entityphyname');
		dtPrecScale = $('#divRuleHidden'+id+ruleNo).find('p:nth-child(4)').text();
		let dtPrecScaleJson = JSON.parse(dtPrecScale);
		$('#derivedDatatypeId').val(dtPrecScaleJson.dataType);
		$('#derivedPrecisionId').val(dtPrecScaleJson.precision);
		$('#derivedScaleId').val(dtPrecScaleJson.scale);*/
	}else{
		
		$('#derivedDatatypeId').attr('disabled',true);
		$('#derivedPrecisionId').attr('disabled',true);
		$('#derivedScaleId').attr('disabled',true);
	}
}

