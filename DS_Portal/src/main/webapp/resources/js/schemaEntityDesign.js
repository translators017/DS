//import {getRuleSyntaxFromRule,getRuleDetailsOnApply,toggleInput,addNewParamForConcat,removeParamForConcat} from './rule-transformation.js';
//attaching event handlers to the page
let rootCount = $("#rootCount").text();

let sourceCnt = '';

$(function () {
    $(".dvSource span.tbl").draggable({
        revert: "invalid",
        cursor: "move",
        scroll: false,
        refreshPositions: true,
        drag: function (event, ui) {
            ui.helper.addClass("draggable");
        },
        helper: function(event) {
            return $(event.target).clone().css({
                width: $(event.target).width()
            });
            
        }
               
    });

	document.querySelector('.close-rule-console').addEventListener('click', function(){
		closeConsole();
	});
});

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

//Added for Data Switch
let joinerImg = "data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAACoAAAAqCAIAAABKoV4MAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAANuSURBVFhH7ZhNKKxRGMfPsBhFSomwsFFYyUqUJpvJihJz78pGKSsWpJSPkXGRiIWuYsZHiiyU2GkmXIYoIen6FvmK5GuQj7l/c557e6+ZM+57m/e1md9m5n3OM8//ec8573med5jT6Xx8fJycnGxvb6+trW1oaPimGAgOieHh4b29PegCdn9/39XVVV9fPzExsba29lNhFhcXBwYGkMrS0tKbvM1mg/bZ2RlPRx3Gx8cbGxuvr69Za2srMiCzWtze3tbV1WEyGP8gs4q0tLRgIRiWYWtri2xq8fr6ip0+Pz/PsPCbm5tkVgvIt7W1+eX98hL5h4cHHMCjo6Pr6+tkUgCh/PHxcUJCAmMsJiYmMzMTThsbGzTmO7zJx8fHQ/4PkZGRFRUVp6en5OELhPJHR0exsbGkLCE1NXV6epqcvDI2NnZwcEAXAoTyKAPV1dW5ubkZGRmBgYEk7iIqKgq1kvzElJSUJCcno3jStSeE8hwM393d2e32vLw8aRLBwcGDg4PkJGB2dhaeyLWvr49MbnwgL6WnpyciIoLLg5CQELPZTGMCkDQ8g4KCMJEOh4OsEmTIAzyK0g2h1WrLy8unpqZ+eAJ3X1VVRa6MZWdn7+7uUqDfyJMHc3NziYmJFPIjAgIC6JsL/BD7kQK5kC0P0B5JV0EWSGhkZIQC/Z88+qKwsDCKJ5P09PSFhQUKJFf+6emptLSUIrnAxv7q4osb+fn5Op1Oo9FwT9x3WVnZzc0NxXIhQx6/LCws5LE4aWlpq6urNOyJpqYm7hkdHd3b20tWCf8qj52s1+t5LA6Oo52dHRr2xOXlZVxcHDxTUlLQzZH1b4TymOft7W3cXH9/v8FgCA0N5aqcnJycDw9/nBPwLCoqQh5kckMof3JykpSUxMXegYjvltAjNTU16J6fn5/p2hNCefeKB/C8oTF9eXkhJ69cXV3RNzFC+XcVLzw8vLi4eHl5mYZ9hFD+4uKioKAgKyursrKyu7vb/bz0CUJ5dfDL++U/Xf4TX7DRSzKTyeS9fCkBDtDm5mb0L8xisQwNDZFZLfb3941G4+HhIcPMox+1Wq14u0NSmBZFQSnCSwgaJxRGVNe3//VmZmbQIGAVOjo6Ojs7vysGgkMYmx3HOU53SL/Jg/Pz85WVFcwBsCkGgqNjRrdC5djp/AVXAD36W31WOgAAAABJRU5ErkJggg==";
let filterImg = "data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAADEAAAAqCAIAAACV7yQTAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAhPSURBVFhHxZhpbFTXFcfPvW+dfZ/xMh4PBhuMjTE0EEPqOJUVgvoldI0qolahqaqkUqWq/VJVKpVAqFRRq/ZL+6lqi/hQlabqQiCJZKIGDKExhYQYG9vYjO3ZPJtne/PW2zv4KcoHiGdslfw0o3lz373n/eec8847dxAhRFGUa9eu3bp1q1Ao8DxPR+AxghCiAnp7e/ft29fZ2VkfkSTp7Nmz6XR6//79ra2tLMuuTX2clEql6enpWCx2+PDhwcFBuHTp0qlTp6gm6p7PlvPnz58+fbpYLOKbN28eOHAgEAiYmjeELINhmMcbZmRkRFXVRCLBDA8PDw0N+Xw+88zD0AkkK7CwCrNFMltE82W4VyT3ijBfgrtFdDtn3CmiuSIkJPBhEHhzVbPQVJ6YmKD5g5kHmMOP4NYK/HEGFmv1hAxaSKed9LjR7hB6sh2NhuFIFH+tCw5FwSPAP+PwTpwUyubCpqDho9ms6zpei6U5/AhcDNg58sUOeKoF+r2oy4lCFhBRPWQZCWJVuC/B+wmV0fWj3eDh4e/L5EIM8hVzeeOsKWFGR0f7+vq8Xu/a6EPxWuFyEqJ+xBM4MwO/+QguLMPFJRhLwngGJnLw0SqkNeZuUgoJaKefGfSjsgGXU4THKGAxjTTC9evXw+EwNr+tx6AP3UmBwNQd84cp+PMcXEtBsgocQFiAXU4YDcBIWLgSUwu1erbv8cD+DnQ5uZFS16im3S7ISvULfD4EL22HH/XBL/fBz/fCD3bAC1F4OgBhkXQ4GYZjxub1+6vkr5MyqoGLJ4vFNQNN0Kgmnw0qGuQM2O6Gk3vhq1ESFImskpWysZzX76X1yZh2e051yjhbZv81A3ae63HDgB/PlJt2VaOaEAIHCzdSYOWhrEJSIj4BGA3pNZwtMAsZvFJlgeMdXpYRQNb1gB1kDTpdoGimhcZpVBPloB/N5AhigMGACJopQkoneZYoVsI6AUSjomurRS2ClaGQvFTRczWg+U1/QKrUnKua0NTmAhuHChqwGKwMxMpG/ZaSkFVGfg35DCwApvlU4/FCBceLSkYiNQ0ibvTvNFqz0CBNaKL0+mEqAzYeGIRCItIBKgRyGPI8KdsIsYCmG1rZ6HMZETvcL5LVGgz4oKr93/z0nw8SF/5x59JUySIAj0HX0aJE6LGuEFGDLhYHAck1pgLckswWFaOmqAW5XixCgvG397K0QJuG1qMhTVc/SH79+LuHTkwcP594e6ZaVEBkgUUQtkHUDi4BGSyarpGpGmAH6W3Xe92k34dKqp6qklKNBp157Z30iyevXhyPrfvMoKyvKZuXXv31xF+WHIVQFAc9tFjPFYhdAJFBSyW4kTdUrn4hTkJbMNplxzpi3lthr8YxddRySUmWgcbxued6ta39P/3T1NxCwbT7aNbXZBHZ/b0BsboKqwVDUbO5ylhMc9tA5GlZx9vc+AkfahEBi5DmYEqFu0lkq+E+q9DhsHE8b2BgdMO+movdS25tdzqdgmn30az/vOM4ZnggGJuNyyXVgTRUqeaKih+p91O1QlFOF5V0RZsqqIWqppQUj6LykpwqSXdz5Zl4sZLM3ptNjX+YHr+R4cuFX7wyGAnZTLsPY+1511Cn67Zzx48NvHTi3XSJ6XYw/Erm9xeLPC0JCCQNVAIOHiwINA1xHFDH0IofcbMdXt7rFf1u141FPZ5KnfxmT3f7pwn6mEa7761h58+ODXzjx2PTUxrL6G6Pze2xCyLHMJgWnxxNXIQ4ltYnFmHcHXY8szvosGCeRe/PlF5/c/6Fg95DB8KmrfVoqFdZY0vY1RGyv/VhVnZ4q1U5t7K6Es+nlnP0lY7n08l8IrmaTOTT6QL1kM/JEQQqYt+4muz1GCdf3UdzwDT0aNZi14QmSv82r8jjsUVd6IqKQa+1LWgPB5yRoKcz5I+2tG1rCXe37d4TeWYo0hn1htq9GcOGq6snXt5tpzdqAzTXP33M97+y43tP2vRyldjs4HSwfo/QErCHW9ydLZ4trYEtrd07Wp0+h8EJUyvw1pv//dbTrS6naC5ujKY10QQ69e2B5yOaWlYVndRqGn1VqkpZUkslRZYVXdGyZXVmhbw9NnlsOLSzx2+ubJimNVEsFv617/Q/Yc1rMqgGUlRDVmmBNCTFMHSoKHq8DJfG5w73WA49Vd/XNstGNFEiba7fvbIramQ0HdNaQB0ma4aiG6pu0BZl4nZ6r0f97pe2m7ObZIOaKLt6/L96caszn1CrqqpoNJCyrGULtempdIeW+8nRPkT7wA2xcU2UL3yutUtUlGRWWkqW7sfLC/HSYnrm1vyz/V6Xq7m8/iSb0mQYRLAKEPDpPr9qdfJ+T1tPO6K3GW0bNsGmNNF7UOSQyGGOtlE2C7JYdMwyoNMaZs7YEJvUhKw8dgnYbWEsHHAYNIPQnbXIr1+yP4XN+skmsgEbG3KwPhvnFBkOIx6B5TPUhDG2cLjFyUT9fLub8ztYu8DYOLDQ/fIm2JQmCvVT2MVtD1m6/ELYwwfsjF2gGbY5TbSKbLiQUGgT1dtqG9rm3NNp39lu2xK0WDAINNE3BFViGAamb0mSzLHm2Rp2LMwullYqAt1GVZTUfLrDzfk8GylOtKtXFEUURdzW1jY5OWkON8/Lz/d+ebg9s5yOzy1pK+nRbcJvfzgUCtnN082wtLRUqVQCgQCanZ09c+bMyMjIwYMHOY5rNo4Pnd/IhumT0FglEolz5855PJ6jR4/W/x8fHx+/cuWKqqp0iGGYZi1SVXVlDxaR+kfd5oMzDUHXVqtVmj+hUOjIkSO0uzTXZ7PZeDyeyWTWJj2Y/JigAqxWazAYjEQi9X9WAf4H58cuI9PKmogAAAAASUVORK5CYII=";

let wrkTgtCollection = '';
let gblEntityDesignJson = {};
let selParentNodeArr = [];
let mstEntityDesignArr = [];
let tmpEntityDesignArr = [];
let domCnt = 0;

let projectName = $("#selectedProject").val();
let srcCatalogName = $("#srcCatalogName").val();
let isInitTgtToggleLoad = false;
let isInitSrcToggleLoad = false;

//to initiate the source catalog for fa - plus/minus
initiateSrcFaPlusMinusOps();

//to initiate the source catalog for fa - plus/minus
//to initiate fa - plus/minus when dynamically adding/editing source catalogs - by default plus (i.e., hide)
function initiateSrcFaPlusMinusOps(){
	isInitSrcToggleLoad = true;
	//when page launches only
	if (isInitSrcToggleLoad){
		//adding '+' (collapse) by default in target catalog when page launches	
		$('#srcCatalog li:has(ul)').addClass('parent_li');
		$('#srcCatalog li.parent_li > span').each(function(e) {
		    var children = $(this).parent('li.parent_li').find(' > ul > li');
		    if (children.is(":visible")) {
		        children.hide('fast');
		        $(this).find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
		    }		    
		});		
	}	
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
				 //console.log('ObjSchemaDesign:',objSchemaDesign);
				 let tgtEntityDesign = {};
				 //console.log('schemaDesign:',objSchemaDesign.schemaDesign);
				 if (objSchemaDesign.hasOwnProperty("schemaDesign")){
					 tgtEntityDesign = JSON.parse(objSchemaDesign.schemaDesign);
					 console.log('tgtEntityDesign:',tgtEntityDesign);
					 gblEntityDesignJson = tgtEntityDesign;
					 //get keys (entity name) from json and extract the data
					 //then construct master entity design json
					 Object.keys(tgtEntityDesign).forEach(function(key) {
						let valueJson = tgtEntityDesign[key];
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
						addNewCollection();
					});
					 
				 }else{
					 console.log("Apologies, Unable to get the data");
				 }		 
				 
			}catch(e){
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
				
				addNewCollection();
			} 
		}
	});
	//////////Added for Data Switch - Code ends here////////
}

//to initiate the empty designer box by default
initiateEmptyDesignerBox();
function initiateEmptyDesignerBox(){
	let id = $('#box-body').find('.box').length;
	console.log("//////////////id/////////////:",id)
	let designTime = getDesignerTime();
	  
	let divVal=`<div class="box">
			<div class="row box-bdy-row emptybox-row" id="emty-${getDesignerTime()}-${id}">
				<div class="col borderCol emtyTargetdiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable root-size" id="rnd-${designTime}-${id}">
					<ul class="Tree" id="emtyTargetDrop-${id}">
						<li id="targetAttrLi-${id}" title="">
							<span class="ui-draggable ui-draggable-handle">
								<!-- <i class="pointer fas fa-times text-danger" onclick="removeTargetAttributes(event)"></i> -->
							</span>
							<span class="pl-2 ui-draggable ui-draggable-handle">
								
							</span>
						</li>
					</ul>
				</div>
								
				<div class="col borderCol emtyNodeDiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable embed-size" id="end-${designTime}-${id}">
					<ul class="Tree w-100" id="emtyNodeDrop-${id}-${id}">						
					</ul>
				</div>
				
				<div class="col borderCol emtyParentNodeDiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="epnd-${designTime}-${id}">												
				</div>
				
				<div class="col borderCol emtyConditionDiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="cond-${designTime}-${id}">
					<span class="ed-cond-span" id="emtyConditionSpanDrop-${id}-${id}" style="float: right;">
					</span>
					<ul class="Tree w-100" id="emtyConditionDrop-${id}-${id}" style="margin-top: 4px;">						
					</ul>								
				</div>
				
				<div class="col borderCol emtyEmbedRefDiv paddingBoxBdyCol borderRgtBoxBdyCol embedRef-size" id="eref-${designTime}-${id}">
				</div>
				
				
				<div class="col borderCol emtyDenormalizeDiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable denormalize-size" id="denorm-${designTime}-${id}">
					<ul class="Tree w-100" id="emtyDenormalizeDrop-${id}-${id}">						
					</ul>
				</div>							
				
				<div class="col borderCol emtySubsetDiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable subset-size" id="sub-${designTime}-${id}">
					<ul class="Tree w-100" id="emtySubsetDrop-${id}-${id}">						
					</ul>
				</div>
			</div>
		</div>`;
	
	$('#newTargetCol0').append(divVal);
	allowDrop();
}


$(document).on('click','#clear-all',function(e){
	clearAllRows();
});
$(document).on('click','#add-collection',function(e){
	addNewCollection();
});
$(document).on('click','#save-design',function(e){
	saveSchemaEntityDesign('save');
});
$(document).on('click','#next-attrib',function(e){
	saveSchemaEntityDesign('next');
	//launchSchemaAttributeDesign();
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
	generateExcelSheet('next');
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

/*
Removed for Data Switch
let sourceSearchCenter = document.querySelector('#searchSourceAttr');
sourceSearchCenter.addEventListener('keyup', getSearchedRow, false);
sourceSearchCenter.param1 = 'source';

let targetSearchCenter = document.querySelector('#searchTargetAttr');
targetSearchCenter.addEventListener('keyup', getSearchedRow, false);
targetSearchCenter.param1 = 'target';
*/


const t0 = performance.now();
let srcjsonObject = '';
const catalogsArray = document.querySelector('#catalogsArray').textContent;
const catalogJSONArray = document.querySelector('#catalogJSON').textContent;
function onlyUnique(value, index, self) { 
    return self.indexOf(value) === index;
}
let changeCheck= false;
////let oldData = JSON.parse(catalogJSONArray);
//console.log(oldData);
////const catalogObj = JSON.parse(catalogsArray);
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
	const selectedSystem = $('#selectedSystem').val();
	const selectedMapping = $('#selectedMapping').val();
	const url = `${ctx}/RDW/Intelligent-Mapper/system-mapping/${selectedSystem}/data-mapping/${selectedMapping}/predictiveMapping/attributeMapping/fetchAllSourcesAndTargetsInMapping`;
	/*$.ajax({
        url: url,
        type: 'GET',
        timeout: 600000,
        success:function(data){
        	console.log(data);
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
        	
    		//Removed for Data Switch changes - To display the catalogs in the screen instead of dynamic catalog
        	//$('#targetEntities-ldr').fadeOut(500);
        	//$('#tgt-box').append(divValTarget);
	        	
        	for (let src in jsonObj.Source){
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
});

let mappingArray = document.querySelector('#mappingArray').textContent;
//console.log("mapping Array");
//console.log(mappingArray);
/*mappingArray = JSON.parse(mappingArray);

//making target attribute occurance unique
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
var elapsed = new Date().getTime() - start;*/
//console.log("Time taken to compare the tgt attr--> "+elapsed);
	
let colMap = '';
let Rules = document.querySelector('#ruleArray').value;
var option = '';
let derivedCols = [];

/*Rules = Rules.substring(1,Rules.length-1);
for(var i=0,length = Rules.split(',').length ;i<length;i++){
	option = option + 
	'<option value="'+Rules.split(',')[i]+'">'+Rules.split(',')[i]+'</option>';
}
	console.log(mappingArray);
	for(let i=0,length = mappingArray.length; i<length;i++){
		var sourceCols = '', targetCols = '';
		let oldRuleObject = '', updatedRuleDesc = '', updatedParams ='' , updatedType = '';
		let rulediv = '';
		if(mappingArray[i].transformationRuleList.length!=0){
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
					<span class="fa-layers fa-lg pointer" style=align-self:center; onclick="openUserDefinedVariableModel('${i}','','new',event)">
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
	
$('#box-body').append(colMap);*/
$('#box-body').empty(); //Added for Data Switch to clear the center div to avoid loading mapped attributes

$(document).on('click','.targetdiv i',function(e){
	removeTargetAttributes(e);
});
$(document).on('click','.sourceRemove',function(e){
	$(e.target).parents().eq(1).remove();
});
$(document).on('click','.ruleRemove',function(e){
	$(e.target).parent().remove();
});

//Added for Data Switch - To remove the node and parent embed
$(document).on('click','.nodeRemove',function(e){
	let entityLiId = $(this).parents().eq(1).attr('id');
	let entityDivId = $(this).parents().eq(3).attr('id');
	$(e.target).parents().eq(1).remove();
	
	//to remove the embed parent selection group when embed node is removed
	let splitEntityDivId = entityDivId.split('-');
	let parentNodeDivId = 'epnd-' + splitEntityDivId[1] + '-' + splitEntityDivId[2];
	$('#'+parentNodeDivId).empty();
	
	//to remove the condition for the node 
	let conditionDivId = 'cond-' + splitEntityDivId[1] + '-' + splitEntityDivId[2];
	//$('#'+conditionDivId).empty();
	$('#'+conditionDivId).find('span.ed-cond-span').empty();
	$('#'+conditionDivId).find('ul').empty();
	
	//to remove the 'embed as ref' checkbox for the node 
	let embedAsRefDivId = 'eref-' + splitEntityDivId[1] + '-' + splitEntityDivId[2];
	$('#'+embedAsRefDivId).empty();
	
	removeEmbedNode(entityLiId);
});
$(document).on('click','.referenceRemove',function(e){
	let attribLiId = $(this).parents().eq(1).attr('id');
	$(e.target).parents().eq(1).remove();
	removeAttribForReference(attribLiId);
});
$(document).on('click','.denormalizeRemove',function(e){
	let attribLiId = $(this).parents().eq(1).attr('id');
	$(e.target).parents().eq(1).remove();
	removeAttribForDenormalize(attribLiId);
});

//to remove the conditions
$(document).on('click','.joinerRemove',function(e){
	let joinerLiId = $(this).parents().eq(1).attr('id');
	$(e.target).parents().eq(1).remove();
	removeJoinerCondition(joinerLiId);
});
$(document).on('click','.filterRemove',function(e){
	let filterLiId = $(this).parents().eq(1).attr('id');
	$(e.target).parents().eq(1).remove();
	removeFilterCondition(filterLiId);
});

//to remove the right side target catalog collection
$(document).on('click','.tgtNodeRemove',function(e){
	console.log("/////////tgtNodeRemove////////")
	console.log($(this).parents().eq(0).attr('id'))
	console.log($(this).parents().eq(0).data('nodename'))
	let tgtNodeSpanId = $(this).parents().eq(0).attr('id');
	let tgtNodeName = $(this).parents().eq(0).data('nodename');
	$(e.target).parents().eq(0).remove();
	removeTargetCollection_class(tgtNodeSpanId,tgtNodeName);
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
	        	let cloneText = clone.text().trim();
		        cloneText = cloneText.substring(0,cloneText.length-1);
		        cloneText += '.'+catalogName+')';
		        //alert(cloneText);
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
		        let draggedElementType = ui.draggable.data('type');	
		        componentNametxt = entityLogName;
		        
		        let cloneTextTgt = clone.text().trim();
		        cloneTextTgt = cloneTextTgt.substring(0,cloneTextTgt.length-1);
		        cloneTextTgt += '.'+catalogName+')';
		        
		        //Added for Data Switch
		        cloneTextTgt = clone.text().trim();
		    	if (draggedElementType === 'Entity'){
		    		//cloneTextTgt = `${cloneTextTgt} (${catalogName})`;
		    	}else if(draggedElementType === 'Attribute'){
		    		//cloneTextTgt = `${cloneTextTgt} (${entityLogName}.${catalogName})`;
		    	}
		    	
		    	if (draggedElementType === 'Entity' && selParentNodeArr.length === 0){
    	        	
    	        	//to load the parent node selection and get the entity name
			        let entityName = '';
			    	if (entityLogName != ''){
			    		entityName = entityLogName.trim();
			    		selParentNodeArr.push(entityLogName);
			        }else if (entityPhyName != ''){
			        	entityName = entityPhyName.trim();
			        	selParentNodeArr.push(entityLogName);
			        }
			    	
			    	//generate the id for the list element
			    	let divIndex = event.target.id.split('-')[2]; //e.g., 'rnd-1234567890-1'
			    	let listTime = getDesignerTime();
			    	let listId = `${listTime}-${divIndex}`;
		    		let divAppend = 
						`<li id="${listId}" title="${entityName} (${catalogName})" data-entityname="${entityName}" data-attribname="" data-catalogname="${catalogName}" data-type="${draggedElementType}">
							<span>
								<i class="pointer fas fa-times text-danger"></i>
							</span>
							<span class="pl-2 ed-span" id="span-${listId}" contenteditable="true">
								${entityName}
							</span>
							<!-- <input type="text" class="pl-2" id="inp-${listId}" value="${entityName}""> -->
						</li>`;
			        $(event.target).find('ul').empty().append(divAppend);			
					//console.log('event.target.id: ',event.target.id);
					//console.log('this.id: ',this.id);	        
			        
			        //the event is triggered when user changes the node name in the designer area
			        //when editing the node name to focus the span and capture the result
			    	onFocusEditNodeName();

			        /*
	    			//the event is triggered when user changes the node name in the designer area
			        document.getElementById('span-'+listId).addEventListener('input', function(e) {
			            console.log('Edit Node Name Triggered: ', e);
			            //to get the parent li id for the span input
			            //onEditNodeName(e.path[1].id)
			        });
			        */
	    			
	    			let entityJson = {
			    		'dropId': listId, //id of the list element
			    		'nodeName': entityName,
			    		'nodeSrcRefName': `${entityName} (${catalogName})`, 
			    		'referenceAttribs': [],
			    		'denormalizeAttribs': []
					}
	    			
	    			//loading into master entity design array
			    	mstEntityDesignArr.push(entityJson);
			    	//construct the right target catalog
			    	initiateFnlTgtTree();
		    	}else{
		    		alert("Please select the entity and drop it.")
		    	}
			    //end of 'Entity' check loop	

	        }
	    }
	});

	$(".nodediv").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree span.tbl",
	    drop: function(event, ui) {
	        $(this).addClass("ui-state-highlight");
	        if(ui.draggable.hasClass('table-section')){
	        	alert('Table can be dragged only on an empty column. Please drag from the column list to replace.');
	        }else{
	        	let clone = ui.draggable.clone();
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
		        let draggedElementType = ui.draggable.data('type');	
		        componentNametxt = entityLogName;
		        
		        let cloneTextNode = clone.text().trim();
		        cloneTextNode = cloneTextNode.substring(0,cloneTextNode.length-1);
		        cloneTextNode += '.'+catalogName+')';
		        
		        //Added for Data Switch
		        cloneTextNode = clone.text().trim();
		    	if (draggedElementType === 'Entity'){
		    		//cloneTextNode = `${cloneTextNode} (${catalogName})`;
		    	}else if(draggedElementType === 'Attribute'){
		    		//cloneTextNode = `${cloneTextNode} (${entityLogName}.${catalogName})`;
		    	}
				
		    	/*//removed for data switch - drop only entities in the 'node' cell
				let divAppend = 
	    		    `<li title="Catalog Name : ${catalogName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-attribphyname="${attribphyname}" data-attriblogname="${attriblogname}" data-catalogname="${catalogName}" data-componentname="${componentNametxt}" data-type="${draggedElementType}">
	    				<span>
	    					${cloneTextNode}
	    				</span>
	    				<span class="pointer">
	    					<i class="fas fa-times text-danger nodeRemove"></i>
	    				</span>
	    			</li>`;
				
				console.log('event.target.id: ',event.target.id);
				console.log('this.id: ',this.id);
				let curNodeId = event.target.id;
    	        $(event.target).find('ul').append(divAppend);
    	        $(event.target).parents().eq(1).removeClass('box-error');
    	        */
    	        
    	        /////////////////////////////////Data Switch - Parent Node - Selection///////////////////////////
    	        //Add the select group for 'parent node' dynamically when adding the 'node' in the table cell
    	        if (draggedElementType === 'Entity'){
    	        	let divIndex = event.target.id.split('-')[2]; //e.g., 'end-1234567890-1'
    	        	
    	        	//to load the master entity design array
			        let entityName = '';
			    	if (entityLogName != ''){
			    		entityName = entityLogName.trim();
			        }else if (entityPhyName != ''){
			        	entityName = entityPhyName.trim();
			        }
			    	
			    	//generate the id for the list element
			    	let listTime = getDesignerTime();
			    	let listId = `${listTime}-${divIndex}`;
    	        	//construct the 'li' and load the 'node' cell
    	        	let divAppend = 
    	    		    `<li id="${listId}" title="${entityName} (${catalogName})" data-entityname="${entityName}" data-attribname="" data-catalogname="${catalogName}" data-type="${draggedElementType}">
    	    				<span class="ed-span" id="span-${listId}" contenteditable="true">
    	    					${entityName}
    	    				</span>
    	    				<span class="pointer">
    	    					<i class="fas fa-times text-danger nodeRemove"></i>
    	    				</span>
    	    			</li>`;
    				
    				//console.log('event.target.id: ',event.target.id);
    				//console.log('this.id: ',this.id);
    				let curNodeDivId = event.target.id;
        	        $(event.target).find('ul').append(divAppend);
        	        $(event.target).parents().eq(1).removeClass('box-error');
        	        
        	        //the event is triggered when user changes the node name in the designer area
        	        //when editing the node name to focus the span and capture the result 
        	    	onFocusEditNodeName();
        	        
        	        //Add the select group for 'parent node' dynamically
	    	        let splitCurNodeDivId = curNodeDivId.split('-');  //to get the id number alone from 'node' div	        
	    	        let curParentNodeDivId = 'epnd-' + splitCurNodeDivId[1] + '-' + splitCurNodeDivId[2]; //id of the parent node div
	    	        let curEmbedRefDivId = 'eref-' + splitCurNodeDivId[1] + '-' + splitCurNodeDivId[2]; //id fo the embed as ref div
	    	        
	    	        //get existing added node for selection group - options
			    	let optParentNode = '';
			    	for (let m=0; m<selParentNodeArr.length; m++){
			    		optParentNode += `<option value="${selParentNodeArr[m]}">${selParentNodeArr[m]}</option>`;
			    	}

	    	        //construct the selection group and append it in parent node div
	    	        let divParentNode = `    	        
	    	        	<select class="form-control form-control-sm" data-prevsel="${selParentNodeArr[0]}" id="parentNodeSelGrp-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}" name="parentNodeSelGrp" onchange="changeParentNodeSelGrp(this)">								
							${optParentNode}																			
						</select>`;
	    	        	    	        
	    	        $('#'+curParentNodeDivId).empty();
	    	        $('#'+curParentNodeDivId).append(divParentNode);
	    	        
	    	        //construct the 'embed as ref' div - input element
			    	let divEmbedAsRef = `
				    	<div class="custom-control custom-checkbox" style="margin-left: 30px; top: 4px;">
						  <input type="checkbox" class="custom-control-input" id="embedref-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}" style="margin-left: -22px;" onclick="onEmbedAsRefClick(this)">
						  <label class="custom-control-label" for="embedref-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}"></label>
						</div>`;
					
	    	        $('#'+curEmbedRefDivId).empty();
	    	        $('#'+curEmbedRefDivId).append(divEmbedAsRef);
	    	        
	    	        //Added for Data Switch - to capture the current 'node' and add it in array for next row - select list	    	
			        if (entityLogName !== ''){
			        	selParentNodeArr.push(entityLogName);
			        }else if (entityPhyName !== ''){
			        	selParentNodeArr.push(entityPhyName);
			        }        
			        		    	
			    	let entityJson = {
			    		'dropId': listId, //id of the list element
			    		'nodeName': entityName,
			    		'parentName': selParentNodeArr[0], //get the first record of the array as it is default for 'parent node' select group
			    		'nodeSrcRefName': `${entityName} (${catalogName})`, 
			    		'referenceAttribs': [],
			    		'denormalizeAttribs': [],
			    		'join': {},
			    		'filter': {},
			    		'embedAsRef': false			    		
				    };

			    	//loading into master entity design array
			    	mstEntityDesignArr.push(entityJson);
			    	//construct the target catalog
			    	initiateFnlTgtTree();
			    	
			    	console.log("////////////////////embed Node Loop////////////////")
			    	console.log("NodeDivIndex: ",divIndex)
			    	console.log("curNodeDivId: ",curNodeDivId)
			    	console.log("curParentNodeDivId: ",curParentNodeDivId)
			    	//when dropping the nodes, display modal window to add the join condition
			    	displayConditionModal(divIndex,curNodeDivId,curParentNodeDivId);
		    	}
    	        //end of if loop - 'Entity' check
			     
	        }
	        //end of else loop
	    }
	});

	$(".referencediv").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree span.tbl",
	    drop: function(event, ui) {
	        $(this).addClass("ui-state-highlight");
	        console.log("////////////////Reference Div - Start///////////////")
	        if(ui.draggable.hasClass('table-section')){
	        	alert('Table can be dragged only on an empty column. Please drag from the column list to replace.');
	        }else{
	        	console.log("////////////////Reference Div - Main Loop///////////////")
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
		        let draggedElementType = ui.draggable.data('type');	
		        componentNametxt = entityLogName;
		        
		        let cloneTextRef = clone.text().trim();
		        cloneTextRef = cloneTextRef.substring(0,cloneTextRef.length-1);
		        cloneTextRef += '.'+catalogName+')';
		        
		        //Added for Data Switch
		        cloneTextRef = clone.text().trim();
		    	if (draggedElementType === 'Entity'){
		    		//cloneTextRef = `${cloneTextRef} (${catalogName})`;
		    	}else if(draggedElementType === 'Attribute'){
		    		//cloneTextRef = `${cloneTextRef} (${entityLogName}.${catalogName})`;
		    	}
		    	
		    	if (draggedElementType === 'Attribute'){        
    	        	//to get the entity name
			        let entityName = '';
			    	if (entityLogName != ''){
			    		entityName = entityLogName.trim();
			        }else if (entityPhyName != ''){
			        	entityName = entityPhyName.trim();
			        }
			    	
			    	//to get the attribute name
			        let attribName = '';
			    	if (attriblogname != ''){
			    		attribName = attriblogname;
			        }else if (attribphyname != ''){
			        	attribName = attribphyname;
			        }
			    	
			    	//generate the id for the list element
			    	let divIndex = event.target.id.split('-')[2]; //e.g., 'ref-1234567890-1' - 'o/p' is 1   	        	
			    	let listTime = getDesignerTime();
			    	let listId = `${listTime}-${divIndex}`;
    	        	//construct the 'li' and load the 'node' cell
    	        	let divAppend = 
    	    		    `<li id="${listId}" title="${attribName} (${entityName})" data-entityname="${entityName}" data-attribname="${attribName}" data-catalogname="${catalogName}" data-type="${draggedElementType}">
    	    				<span class="ed-span" id="span-${listId}" contenteditable="true">
    	    					${attribName}
    	    				</span>
    	    				<span class="pointer">
    	    					<i class="fas fa-times text-danger referenceRemove"></i>
    	    				</span>
    	    			</li>`;
    				
        	        $(event.target).find('ul').append(divAppend);
        	        $(event.target).parents().eq(1).removeClass('box-error');
        	        
        	        //the event is triggered when user changes the node name in the designer area
        	        //when editing the node name to focus the spanand capture the result 
        	    	onFocusEditNodeName();
        	        
        	        //the event is triggered when user changes the node name in the designer area
			        /*$('#span-'+listId).focusin(function(){
			            $(this).css("background-color", "#FFFFCC");
			        });
			        $('#span-'+listId).focusout(function(){
			            $(this).css("background-color", "#FFFFFF");
			            let edSpanText = $(this).text().trim();
			            let edSpanId = this.id;
			            //call the edit name function once renamed
			            onEditNodeName(edSpanId, edSpanText);
			        });*/
			        
        	        //get the index of the reference div
        	        let curRefDivId = event.target.id;
	    	        let splitCurRefDivId = curRefDivId.split('-');  //to get the id number	        
	    	        let curRefDivIndex = splitCurRefDivId[2];
	    	        console.log('Reference - curRefDivIndex: ',curRefDivIndex);

	    	        let derNodeName = '';
	    	        let derParentName = '';
	    	        let derDropId = '';
	    	        let derReferenceAttribs = [];	    	        
	    	        //get the root/embed node element from master entity design array for the reference index and add the reference attribute to the element
	    	        for (let i=0;i<mstEntityDesignArr.length;i++){
	    	        	let mstEntityDesignJson = mstEntityDesignArr[i];
	    	        	let dropId = mstEntityDesignJson.dropId;
	    	        	let dropNodeLiIndex = dropId.split('-')[1];
	    	        	console.log('dropNodeLiIndex: ',dropNodeLiIndex);

	    	        	//check the 'parentName' exist in the master entity design for the reference index
	    	        	if (dropNodeLiIndex === curRefDivIndex
	    	        		&& !mstEntityDesignJson.hasOwnProperty('parentName')
	    	        		&& mstEntityDesignJson.nodeName !== ''){
	    	        			//for adding reference in index of the 'root' node  
	    	        			derNodeName = mstEntityDesignJson.nodeName;
	    	        			derDropId = mstEntityDesignJson.dropId;
	    	        			derReferenceAttribs = mstEntityDesignJson.referenceAttribs;
		    	        }else if (dropNodeLiIndex === curRefDivIndex
	    	        		&& mstEntityDesignJson.hasOwnProperty('parentName')
	    	        		&& mstEntityDesignJson.nodeName !== ''){
		    	        	//for adding reference in index of the 'embed' node (embed has 'parentName' element)
	    	        			derNodeName = mstEntityDesignJson.nodeName;
	    	        			derParentName = mstEntityDesignJson.parentName;
	    	        			derDropId = mstEntityDesignJson.dropId;
	    	        			derReferenceAttribs = mstEntityDesignJson.referenceAttribs;
	    	        	}
	    	        	
	    	        	if (derDropId !== ''){
	    	        		break;
	    	        	}
	    	        }
	    	        //end of for loop
	    	        
	    	        console.log("//////////////Loop 1 - Completed//////////////")
	    	        console.log('derNodeName: ',derNodeName);
	    	        console.log('derDropId: ',derDropId);
	    	        console.log('derReferenceAttribs: ',derReferenceAttribs);
	    	        
	    	        //if there is no 'root/embed' exist for the reference index, add the reference attribute in the 'root'
	    	        if (derDropId === ''){
	    	        	for (let i=0;i<mstEntityDesignArr.length;i++){
		    	        	let mstEntityDesignJson = mstEntityDesignArr[i];		    	        	
		    	        	if (!mstEntityDesignJson.hasOwnProperty('parentName') && mstEntityDesignJson.nodeName !== ''){
	    	        			//for adding reference in index of the 'root' node  
	    	        			derNodeName = mstEntityDesignJson.nodeName;
	    	        			derDropId = mstEntityDesignJson.dropId;
	    	        			derReferenceAttribs = mstEntityDesignJson.referenceAttribs;
				    	    }
	    	        	}
	    	        	//end of for loop
	    	        }
	    	        
	    	        //if identified the embed/root element for the reference index, add the reference attribute in the element
	    	        if (derDropId !== ''){
	    	        	for (let n=0;n<mstEntityDesignArr.length;n++){
		    	        	let mstEntityDesignJson = mstEntityDesignArr[n];
		    	        	if (mstEntityDesignJson.dropId === derDropId){
		    	        		mstEntityDesignJson.referenceAttribs.push({
		        					'id': listId, 
		        					'name': attribName, 
		        					'srcRef': `${attribName} (${entityName})`
		        				});
		    	        		break;
		    	        	}
		    	        }
	    	        	//end of for loop
	    	        }

			    	//construct the target catalog
			    	initiateFnlTgtTree();
		    	}	    	
		    	else{
		    		alert("Please select the attribute and drop it.")
		    	}
		    	//end of 'Attribute' check if loop
				
				
	        }
	        //end of else loop
	    }
	});

	$(".denormalizediv").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree span.tbl",
	    drop: function(event, ui) {
	        $(this).addClass("ui-state-highlight");
	        console.log("////////////////Denormalize Div - Start///////////////")
	        if(ui.draggable.hasClass('table-section')){
	        	alert('Table can be dragged only on an empty column. Please drag from the column list to replace.');
	        }else{
	        	console.log("////////////////Denormalize Div - Main Loop///////////////")
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
		        let draggedElementType = ui.draggable.data('type');	
		        componentNametxt = entityLogName;
		        
		        let cloneTextDeNorm = clone.text().trim();
		        cloneTextDeNorm = cloneTextDeNorm.substring(0,cloneTextDeNorm.length-1);
		        cloneTextDeNorm += '.'+catalogName+')';
		        
		        //Added for Data Switch
		        cloneTextDeNorm = clone.text().trim();
		    	if (draggedElementType === 'Entity'){
		    		cloneTextDeNorm = `${cloneTextDeNorm} (${catalogName})`;
		    	}else if(draggedElementType === 'Attribute'){
		    		cloneTextDeNorm = `${cloneTextDeNorm} (${entityLogName}.${catalogName})`;
		    	}
 	        
    	        if (draggedElementType === 'Attribute'){
    	        	//to get the entity name
			        let entityName = '';
			    	if (entityLogName != ''){
			    		entityName = entityLogName.trim();
			        }else if (entityPhyName != ''){
			        	entityName = entityPhyName.trim();
			        }
			    	
			    	//to get the attribute name
			        let attribName = '';
			    	if (attriblogname != ''){
			    		attribName = attriblogname;
			        }else if (attribphyname != ''){
			        	attribName = attribphyname;
			        }
			    	
			    	//generate the id for the list element
			    	let divIndex = event.target.id.split('-')[2]; //e.g., 'denorm-1234567890-1' - 'o/p' is 1   	        	
			    	let listTime = getDesignerTime();
			    	let listId = `${listTime}-${divIndex}`;
    	        	//construct the 'li' element
    	        	let divAppend = 
    	    		    `<li id="${listId}" title="${attribName} (${entityName})" data-entityname="${entityName}" data-attribname="${attribName}" data-catalogname="${catalogName}" data-type="${draggedElementType}">
    	    				<span class="ed-span" id="span-${listId}" contenteditable="true">
    	    					${attribName}
    	    				</span>
    	    				<span class="pointer">
    	    					<i class="fas fa-times text-danger denormalizeRemove"></i>
    	    				</span>
    	    			</li>`;
    				
        	        $(event.target).find('ul').append(divAppend);
        	        $(event.target).parents().eq(1).removeClass('box-error');
        	        	
        	        //the event is triggered when user changes the node name in the designer area
        	        //when editing the node name to focus the spanand capture the result 
        	    	onFocusEditNodeName();
        	        
        	        //get the index of the reference div
        	        let curDenormDivId = event.target.id;
	    	        let splitCurDenormDivId = curDenormDivId.split('-');  //to get the id number alone	        
	    	        let curDenormDivIndex = splitCurDenormDivId[2];
	    	        
	    	        let derNodeName = '';
	    	        let derParentName = '';
	    	        let derDropId = '';
	    	        let derDenormalizeAttribs = [];	    	        
	    	        //get the root/embed node element from master entity design array for the denormalize index and add the denormalize attribute to the element
	    	        for (let i=0;i<mstEntityDesignArr.length;i++){
	    	        	let mstEntityDesignJson = mstEntityDesignArr[i];
	    	        	let dropId = mstEntityDesignJson.dropId;
	    	        	let dropNodeLiIndex = dropId.split('-')[1];

	    	        	//check the 'parentName' exist in the master entity design for the reference index
	    	        	if (dropNodeLiIndex === curDenormDivIndex
	    	        		&& !mstEntityDesignJson.hasOwnProperty('parentName')
	    	        		&& mstEntityDesignJson.nodeName !== ''){
	    	        			//for adding reference in index of the 'root' node  
	    	        			derNodeName = mstEntityDesignJson.nodeName;
	    	        			derDropId = mstEntityDesignJson.dropId;
	    	        			derDenormalizeAttribs = mstEntityDesignJson.denormalizeAttribs;
		    	        }else if (dropNodeLiIndex === curDenormDivIndex
	    	        		&& mstEntityDesignJson.hasOwnProperty('parentName')
	    	        		&& mstEntityDesignJson.nodeName !== ''){
		    	        		//for adding reference in index of the 'embed' node (embed has 'parentName' element)
	    	        			derNodeName = mstEntityDesignJson.nodeName;
	    	        			derParentName = mstEntityDesignJson.parentName;
	    	        			derDropId = mstEntityDesignJson.dropId;
	    	        			derDenormalizeAttribs = mstEntityDesignJson.denormalizeAttribs;
	    	        	}
	    	        	
	    	        	if (derDropId !== ''){
	    	        		break;
	    	        	}
	    	        }
	    	        //end of for loop
	    	        
	    	        //if there is no 'root/embed' exist for the denormalize index, add the denormalize attribute in the 'root'
	    	        if (derDropId === ''){
	    	        	for (let i=0;i<mstEntityDesignArr.length;i++){
		    	        	let mstEntityDesignJson = mstEntityDesignArr[i];		    	        	
		    	        	if (!mstEntityDesignJson.hasOwnProperty('parentName') && mstEntityDesignJson.nodeName !== ''){
	    	        			//for adding reference in index of the 'root' node  
	    	        			derNodeName = mstEntityDesignJson.nodeName;
	    	        			derDropId = mstEntityDesignJson.dropId;
	    	        			derDenormalizeAttribs = mstEntityDesignJson.denormalizeAttribs;
				    	    }
	    	        	}
	    	        	//end of for loop
	    	        }
	    	        
	    	        //if identified the embed/root element for the reference index, add the reference attribute in the element
	    	        if (derDropId !== ''){
	    	        	for (let n=0;n<mstEntityDesignArr.length;n++){
		    	        	let mstEntityDesignJson = mstEntityDesignArr[n];
		    	        	if (mstEntityDesignJson.dropId === derDropId){
		    	        		mstEntityDesignJson.denormalizeAttribs.push({
		        					'id': listId, 
		        					'name': attribName, 
		        					'srcRef': `${attribName} (${entityName})`
		        				});
		    	        		break;
		    	        	}
		    	        }
	    	        	//end of for loop
	    	        }

			    	//construct the target catalog
			    	initiateFnlTgtTree();
		    	}	    	
		    	else{
		    		alert("Please select the attribute and drop it.")
		    	}
		    	//end of 'Attribute' check if loop  	        
    	        
	        }
	    }
	});

	$(".newTargetCol").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree span.tbl",
	    
	    drop: function(event, ui) {
	    	console.log("/////////////Test3/////////////")
	    	let i = $('#box-body').find('div.box').length;
	    	let boxIndex = $('.newColumn').find('div.newTargetCol').length - 1;
	    	var clone = ui.draggable.clone();
	        clone.addClass("dropped");
	        console.log(ui.draggable.parents())
	        console.log(ui.draggable)
	        let catalogName = ui.draggable.parents().eq(4).data('catalogname');
	        let entityPhyName = ui.draggable.parents().eq(4).data('entityphyname');
	        let entityLogName = ui.draggable.parents().eq(4).data('entitylogname');
	        let componentName = ui.draggable.parents().eq(4).data('componentname');
	        let attribphyname = ui.draggable.parents().data('attribphyname');
	        let attriblogname = ui.draggable.parents().data('attriblogname');	        
	        let componentNametxt = ui.draggable.parents().eq(4).data('componentname');
	        
	        //Added for Data Switch
	        catalogName = ui.draggable.data('catalogname');
	        entityPhyName = ui.draggable.data('entityphyname');
	        entityLogName = ui.draggable.data('entitylogname');
	        attribphyname = ui.draggable.data('attribphyname');
	        attriblogname = ui.draggable.data('attriblogname');	
	        let draggedElementType = ui.draggable.data('type');	
	        componentName = entityLogName;
	        componentNametxt = entityLogName;
	        
	        console.log("catalogName: ",catalogName);
	        console.log("attribphyname: ",attribphyname);
	        console.log("attriblogname: ",attriblogname);
	        console.log("draggedElementType: ",draggedElementType);
	        
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
	    		let clone = ui.draggable.clone();
		        clone.addClass("dropped");
		    	let cloneTextNew = clone.text().trim();
		    	cloneTextNew = cloneTextNew.substring(0,cloneTextNew.length-1);
		    	cloneTextNew += '.'+catalogName+')';
		    	
		    	//Added for Data Switch
		    	cloneTextNew = clone.text().trim();
		    	if (draggedElementType === 'Entity'){
		    		cloneTextNew = `${cloneTextNew} (${catalogName})`;
		    	}else if(draggedElementType === 'Attribute'){
		    		cloneTextNew = `${cloneTextNew} (${entityLogName}.${catalogName})`;
		    	}
		    	
		    	//construct selection group
		    	/*let optParentNode = '';
		    	for (let m=0; m<selParentNodeArr.length; m++){
		    		optParentNode += `<option value="${selParentNodeArr[m]}">${selParentNodeArr[m]}</option>`;
		    	}*/

		    	colDiv += 
		    		`<div class="box">
						<div class="row box-bdy-row">
							<div class="col borderCol targetdiv paddingBoxBdyCol borderRgtBoxBdyCol root-size" id="targetAttributes-${i}">
								<ul class="Tree">
									<li title="Catalog Name : ${catalogName}" data-entityphyname="${entityPhyName}" data-entitylogname="${entityLogName}" data-attribphyname="${attribphyname}" data-attriblogname="${attriblogname}" data-catalogname="${catalogName}" data-componentname="${componentNametxt}" data-type="${draggedElementType}">
										<span>
											<i class="pointer fas fa-times text-danger"></i>
										</span>
										<span class="pl-2">${cloneTextNew}</span>
									</li>
								</ul>
							</div>
							<!-- <div class="col-md-auto text-center borderCol p-1" style="display:flex;justify-content:center">
								<span class="fa-layers fa-lg pointer" style=align-self:center; onclick="openUserDefinedVariableModel('${i}','','new',event)">
									<i class="far fa-plus-square text-info"></i>
								</span>
							</div>
							<div class="col-4 p-0 borderCol" id="ruleApplied${i}">								
							</div> -->
							
							<div class="col borderCol nodediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable embed-size" id="nodeAttributes-${i}">
								<ul class="Tree w-100" id="nodeDrop-${i}-${i}">
									
								</ul>
							</div>
							<div class="col borderCol parentnodediv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="parentNodeAttributes-${i}">
								<!-- <ul class="Tree w-100" id="parentNodeDrop-${i}-${i}">
									
								</ul> -->
								
							</div>
							<div class="col borderCol conditiondiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="cond-${i}">
													
		    				</div>
							<div class="col borderCol embedrefdiv paddingBoxBdyCol borderRgtBoxBdyCol embedRef-size" id="eref-${i}">
																
							</div>
							
							<!-- <div class="col borderCol referencediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable" id="referenceAttributes-${i}" style='min-width: 140px; max-width: 140px;'>
								<ul class="Tree w-100" id="referenceDrop-${i}-${i}">
									
								</ul>
							</div> -->
							<div class="col borderCol denormalizediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable denormalize-size" id="denormalizeAttributes-${i}">
								<ul class="Tree w-100" id="denormalizeDrop-${i}-${i}">
									
								</ul>
							</div>							
							
							<div class="col borderCol sourceId paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable subset-size" id="sourceAttributes-${i}">
								<ul class="Tree w-100" id="sourceDrop-${i}-${i}">
									
								</ul>
							</div>
						</div>
					</div>`
		    
			    $("#box-body").append(colDiv);
			    $("#newTargetCol"+boxIndex).removeClass("ui-droppable,highlight");

			    allowDrop();
			    
			    //Added for Data Switch - to capture the node for parent node - select list
			    if (draggedElementType === 'Entity' && selParentNodeArr.length === 0){
			    	console.log("entityPhyName: ",entityPhyName);
			        console.log("entityLogName: ",entityLogName);
			        if (entityLogName != ''){
			        	selParentNodeArr.push(entityLogName);
			        }else if (entityPhyName != ''){
			        	selParentNodeArr.push(entityPhyName);
			        }
			        //console.log("Parent Node Select Grp: ",selParentNodeArr);
		    	}
			    
	    	}
	    }
	});
	
	
	$(".emtyTargetdiv").droppable({
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

		        //Added for Data Switch
		        catalogName = ui.draggable.data('catalogname');
		        entityPhyName = ui.draggable.data('entityphyname');
		        entityLogName = ui.draggable.data('entitylogname');
		        attribphyname = ui.draggable.data('attribphyname');
		        attriblogname = ui.draggable.data('attriblogname');	
		        let draggedElementType = ui.draggable.data('type');	
		        let componentNametxt = entityLogName;   
		    	
		    	//check whether root collection is already exist or not
		    	let isRootExist = false;
		    	for (let i=0;i<mstEntityDesignArr.length;i++){
		    		if (!mstEntityDesignArr[i].hasOwnProperty('parentName')){
		    			isRootExist = true;
		    		}
		    	}

		    	//allow drop only if it is 'entity' and collection not exist in the designer area
		    	if (draggedElementType === 'Entity' && !isRootExist){
    	        	let param = {
	        			'catalogName': catalogName,
	        			'entityPhyName': entityPhyName,
	    		        'entityLogName': entityLogName,
	    		        'attribphyname': attribphyname,
	    		        'attriblogname': attriblogname,
	    		        'draggedElementType': draggedElementType,
	    		        'emtyDropType': "EmtyTarget"
    	        	}
    	        	//create a div box in box-body and append the list value and then re-initailize the empty div
		    		createEmptyDivBox(param);
		    	}else{
		    		if (draggedElementType !== 'Entity'){
		    			alert("You have picked an attribute. Please select the entity.");
		    		}else{
		    			alert("Collection already exist.");
		    		}
		    	}
			    //end of 'Entity' check loop
	        }
	    }
	});
	
	$(".emtyNodeDiv").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree span.tbl",
	    drop: function(event, ui) {
	        $(this).addClass("ui-state-highlight");
	        if(ui.draggable.hasClass('table-section')){
	        	alert('Table can be dragged only on an empty column. Please drag from the column list to replace.');
	        }else{
	        	let clone = ui.draggable.clone();
		        clone.addClass("dropped");
	        
		        //Added for Data Switch
		        catalogName = ui.draggable.data('catalogname');
		        entityPhyName = ui.draggable.data('entityphyname');
		        entityLogName = ui.draggable.data('entitylogname');
		        attribphyname = ui.draggable.data('attribphyname');
		        attriblogname = ui.draggable.data('attriblogname');	
		        let draggedElementType = ui.draggable.data('type');	
		        let componentNametxt = entityLogName;
		        
		        //check whether root collection is already exist or not
		    	let isRootExist = false;
		    	for (let i=0;i<mstEntityDesignArr.length;i++){
		    		if (!mstEntityDesignArr[i].hasOwnProperty('parentName')){
		    			isRootExist = true;
		    		}
		    	}
    	        /////////////////////////////////Data Switch - Parent Node - Selection///////////////////////////
    	        //Add the select group for 'parent node' dynamically when adding the 'node' in the table cell
    	        if (draggedElementType === 'Entity' && isRootExist){
    	        	
    	        	let param = {
	        			'catalogName': catalogName,
	        			'entityPhyName': entityPhyName,
	    		        'entityLogName': entityLogName,
	    		        'attribphyname': attribphyname,
	    		        'attriblogname': attriblogname,
	    		        'draggedElementType': draggedElementType,
	    		        'emtyDropType': "EmtyNode"
    	        	}
    	        	//create a div box in box-body and append the list value and then re-initailize the empty div
		    		createEmptyDivBox(param);
		    	}
    	        //end of if loop - 'Entity' check
    	        else{
    	        	if (draggedElementType !== 'Entity'){    	        
    	        		alert("You have picked entity. Please select an attribute");
		    		}else{
		    			alert("Collection doesn't exist. Please create a collection first.");
		    		}
    	        }
			     
	        }
	        //end of else loop
	    }
	});
	
	$(".emtyDenormalizeDiv").droppable({
	    classes: {
	        'ui-droppable-hover': 'highlight'
	    },
	    accept: ".Tree span.tbl",
	    drop: function(event, ui) {
	        $(this).addClass("ui-state-highlight");
	        console.log("////////////////Denormalize Div - Start///////////////")
	        if(ui.draggable.hasClass('table-section')){
	        	alert('Table can be dragged only on an empty column. Please drag from the column list to replace.');
	        }else{
	        	console.log("////////////////Denormalize Div - Main Loop///////////////")
	        	var clone = ui.draggable.clone();
		        clone.addClass("dropped");
		        
		        //Added for Data Switch
		        catalogName = ui.draggable.data('catalogname');
		        entityPhyName = ui.draggable.data('entityphyname');
		        entityLogName = ui.draggable.data('entitylogname');
		        attribphyname = ui.draggable.data('attribphyname');
		        attriblogname = ui.draggable.data('attriblogname');	
		        let draggedElementType = ui.draggable.data('type');	
		        let componentNametxt = entityLogName;
		        
		        let cloneTextDeNorm = clone.text().trim();
		        cloneTextDeNorm = cloneTextDeNorm.substring(0,cloneTextDeNorm.length-1);
		        cloneTextDeNorm += '.'+catalogName+')';
		        
		        //Added for Data Switch
		        cloneTextDeNorm = clone.text().trim();
		    	if (draggedElementType === 'Entity'){
		    		cloneTextDeNorm = `${cloneTextDeNorm} (${catalogName})`;
		    	}else if(draggedElementType === 'Attribute'){
		    		cloneTextDeNorm = `${cloneTextDeNorm} (${entityLogName}.${catalogName})`;
		    	}
		    	
		    	//check whether root collection is already exist or not
		    	let isRootExist = false;
		    	for (let i=0;i<mstEntityDesignArr.length;i++){
		    		if (!mstEntityDesignArr[i].hasOwnProperty('parentName')){
		    			isRootExist = true;
		    		}
		    	}
 	        
    	        if (draggedElementType === 'Attribute' && isRootExist){
    	        	
    	        	let param = {
	        			'catalogName': catalogName,
	        			'entityPhyName': entityPhyName,
	    		        'entityLogName': entityLogName,
	    		        'attribphyname': attribphyname,
	    		        'attriblogname': attriblogname,
	    		        'draggedElementType': draggedElementType,
	    		        'emtyDropType': "EmtyDenormalize"
    	        	}
    	        	//create a div box in box-body and append the list value and then re-initailize the empty div
		    		createEmptyDivBox(param);  
		    	}	    	
		    	else{		    		
		    		if (draggedElementType !== 'Attribute'){
		    			alert("You have picked entity. Please select an attribute");
		    		}else{
		    			alert("Collection doesn't exist. Please create a collection first.");
		    		}
		    	}
		    	//end of 'Attribute' check if loop  	        
    	        
	        }
	    }
	});
}

//function to create a empty target drop box
function createEmptyDivBox(param){	
	
	let catalogName = param.catalogName;
	let entityPhyName = param.entityPhyName;
	let entityLogName = param.entityLogName;
	let attribphyname = param.attribphyname;
	let attriblogname = param.attriblogname;	
    let draggedElementType = param.draggedElementType;	
    let emtyDropType = param.emtyDropType;
    	
	let id = $('#box-body').find('.box').length;

	//construct selection group
	/*let optParentNode = '';
	for (let m=0; m<selParentNodeArr.length; m++){
		optParentNode += `<option value="${selParentNodeArr[m]}">${selParentNodeArr[m]}</option>`;
	}*/

	let designTime = getDesignerTime();	  
	let divVal=`<div class="box">
			<div class="row box-bdy-row" id="bx-${getDesignerTime()}-${id}">
				<div class="col borderCol targetdiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable root-size" id="rnd-${designTime}-${id}">
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
				
				<div class="col borderCol nodediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable embed-size" id="end-${designTime}-${id}">
					<ul class="Tree w-100" id="nodeDrop-${id}-${id}">						
					</ul>
				</div>

				<div class="col borderCol parentnodediv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="epnd-${designTime}-${id}">													
				</div>

				<div class="col borderCol conditiondiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="cond-${designTime}-${id}">
					<span class="ed-cond-span" id="conditionSpanDrop-${id}-${id}" style="float: right;">
					</span>
					<ul class="Tree w-100" id="conditionDrop-${id}-${id}" style="margin-top: 4px;">						
					</ul>								
				</div>

				<div class="col borderCol embedrefdiv paddingBoxBdyCol borderRgtBoxBdyCol embedRef-size" id="eref-${designTime}-${id}">
				</div>
				
				<div class="col borderCol denormalizediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable denormalize-size" id="denorm-${designTime}-${id}">
					<ul class="Tree w-100" id="denormalizeDrop-${id}-${id}">						
					</ul>
				</div>							

				<div class="col borderCol sourceId paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable subset-size" id="sub-${designTime}-${id}">
					<ul class="Tree w-100" id="sourceDrop-${id}-${id}">						
					</ul>
				</div>
			</div>
		</div>`;
	
	
	$('#box-body').append(divVal);	
	$('#newTargetCol0').empty();
	//re-initiate the empty div box
	initiateEmptyDesignerBox();
	allowDrop();
	
	//load the drop list based on the drop type
	if (emtyDropType === 'EmtyTarget'){
		//to load the parent node selection and get the entity name
        let entityName = getEntityName(entityPhyName,entityLogName);
        if (entityName !== ''){
        	selParentNodeArr.push(entityName);
        }
    	
    	//generate the id for the list element
    	let divIndex = id; //e.g., 'rnd-1234567890-1'
    	let listTime = getDesignerTime();
    	let listId = `${listTime}-${divIndex}`;
		let divAppend = 
			`<li id="${listId}" title="${entityName} (${catalogName})" data-entityname="${entityName}" data-attribname="" data-catalogname="${catalogName}" data-type="${draggedElementType}">
				<span>
					<i class="pointer fas fa-times text-danger"></i>
				</span>
				<span class="pl-2 ed-span" id="span-${listId}" contenteditable="true">
					${entityName}
				</span>
				<!-- <input type="text" class="pl-2" id="inp-${listId}" value="${entityName}""> -->
			</li>`;
        $('#rnd-'+`${designTime}-${id}`).find('ul').empty().append(divAppend);
        
        //the event is triggered when user changes the node name in the designer area
        //when editing the node name to focus the span and capture the result
    	onFocusEditNodeName();
	
		let entityJson = {
    		'dropId': listId, //id of the list element
    		'nodeName': entityName,
    		'nodeSrcRefName': `${entityName} (${catalogName})`, 
    		'referenceAttribs': [],
    		'denormalizeAttribs': []
		}
		
		//loading into master entity design array
    	mstEntityDesignArr.push(entityJson);
    	//construct the right target catalog
    	initiateFnlTgtTree();
	}else if (emtyDropType === 'EmtyNode'){
		let divIndex = id; //e.g., 'end-1234567890-1'
    	
    	//to load the master entity design array
        let entityName = getEntityName(entityPhyName,entityLogName);
    	
    	//generate the id for the list element
    	let listTime = getDesignerTime();
    	let listId = `${listTime}-${divIndex}`;
    	//construct the 'li' and load the 'node' cell
    	let divAppend = 
		    `<li id="${listId}" title="${entityName} (${catalogName})" data-entityname="${entityName}" data-attribname="" data-catalogname="${catalogName}" data-type="${draggedElementType}">
				<span class="ed-span" id="span-${listId}" contenteditable="true">
					${entityName}
				</span>
				<span class="pointer">
					<i class="fas fa-times text-danger nodeRemove"></i>
				</span>
			</li>`;
		
		
        $('#end-'+`${designTime}-${id}`).find('ul').append(divAppend);
        $('#end-'+`${designTime}-${id}`).parents().eq(1).removeClass('box-error');
        
        //the event is triggered when user changes the node name in the designer area
        //when editing the node name to focus the span and capture the result 
    	onFocusEditNodeName();
        
        //Add the select group for 'parent node' dynamically
        let curNodeDivId = 'end-'+`${designTime}-${id}`;
        //let curParentNodeDivId = 'epnd-'+`${designTime}-${id}`; //id of the parent node div
        //let curEmbedRefDivId = 'eref-'+`${designTime}-${id}` //id fo the embed as ref div
        
        //Add the select group for 'parent node' dynamically
        let splitCurNodeDivId = curNodeDivId.split('-');  //to get the id number alone from 'node' div	        
        let curParentNodeDivId = 'epnd-' + splitCurNodeDivId[1] + '-' + splitCurNodeDivId[2]; //id of the parent node div
        let curEmbedRefDivId = 'eref-' + splitCurNodeDivId[1] + '-' + splitCurNodeDivId[2]; //id fo the embed as ref div
        
        //get existing added node for selection group - options
    	let optParentNode = '';
    	for (let m=0; m<selParentNodeArr.length; m++){
    		optParentNode += `<option value="${selParentNodeArr[m]}">${selParentNodeArr[m]}</option>`;
    	}

        //construct the selection group and append it in parent node div
        let divParentNode = `    	        
        	<select class="form-control form-control-sm" data-prevsel="${selParentNodeArr[0]}" id="parentNodeSelGrp-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}" name="parentNodeSelGrp" onchange="changeParentNodeSelGrp(this)">								
				${optParentNode}																			
			</select>`;
        	    	        
        $('#'+curParentNodeDivId).empty();
        $('#'+curParentNodeDivId).append(divParentNode);
        
        //construct the 'embed as ref' div - input element
    	let divEmbedAsRef = `
	    	<div class="custom-control custom-checkbox" style="margin-left: 30px; top: 4px;">
			  <input type="checkbox" class="custom-control-input" id="embedref-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}" style="margin-left: -22px;" onclick="onEmbedAsRefClick(this)">
			  <label class="custom-control-label" for="embedref-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}"></label>
			</div>`;
		
        $('#'+curEmbedRefDivId).empty();
        $('#'+curEmbedRefDivId).append(divEmbedAsRef);
        
        //Added for Data Switch - to capture the current 'node' and add it in array for next row - select list	    	
        if (entityLogName !== ''){
        	selParentNodeArr.push(entityLogName);
        }else if (entityPhyName !== ''){
        	selParentNodeArr.push(entityPhyName);
        }        
        		    	
    	let entityJson = {
    		'dropId': listId, //id of the list element
    		'nodeName': entityName,
    		'parentName': selParentNodeArr[0], //get the first record of the array as it is default for 'parent node' select group
    		'nodeSrcRefName': `${entityName} (${catalogName})`, 
    		'referenceAttribs': [],
    		'denormalizeAttribs': [],
    		'join': {},
    		'filter': {},
    		'embedAsRef': false			    		
	    };

    	//loading into master entity design array
    	mstEntityDesignArr.push(entityJson);
    	//construct the target catalog
    	initiateFnlTgtTree();
    	
    	console.log("///////////////TEst////////////////")
    	//when dropping the nodes, display modal window to add the join condition
    	displayConditionModal(divIndex,curNodeDivId,curParentNodeDivId);
	}else if (emtyDropType === 'EmtyDenormalize'){
		//to get the entity name
        let entityName = getEntityName(entityPhyName,entityLogName);
    	
    	//to get the attribute name
        let attribName = getEntityName(attribphyname,attriblogname);

    	//generate the id for the list element
    	let divIndex = id; //e.g., 'denorm-1234567890-1' - 'o/p' is 1   	        	
    	let listTime = getDesignerTime();
    	let listId = `${listTime}-${divIndex}`;
    	//construct the 'li' element
    	let divAppend = 
		    `<li id="${listId}" title="${attribName} (${entityName})" data-entityname="${entityName}" data-attribname="${attribName}" data-catalogname="${catalogName}" data-type="${draggedElementType}">
				<span class="ed-span" id="span-${listId}" contenteditable="true">
					${attribName}
				</span>
				<span class="pointer">
					<i class="fas fa-times text-danger denormalizeRemove"></i>
				</span>
			</li>`;
		
        $('#denorm-'+`${designTime}-${id}`).find('ul').append(divAppend);
        $('#denorm-'+`${designTime}-${id}`).parents().eq(1).removeClass('box-error');
        	
        //the event is triggered when user changes the node name in the designer area
        //when editing the node name to focus the spanand capture the result 
    	onFocusEditNodeName();
        
        //get the index of the denormalize div
        //let curDenormDivId = `denorm-${designTime}-${id}`;
        //let splitCurDenormDivId = curDenormDivId.split('-');  //to get the id number alone	        
        //let curDenormDivIndex = splitCurDenormDivId[2];
        let curDenormDivIndex = id;
        
        let derNodeName = '';
        let derParentName = '';
        let derDropId = '';
        let derDenormalizeAttribs = [];	    	        
        //get the root/embed node element from master entity design array for the denormalize index and add the denormalize attribute to the element
        for (let i=0;i<mstEntityDesignArr.length;i++){
        	let mstEntityDesignJson = mstEntityDesignArr[i];
        	let dropId = mstEntityDesignJson.dropId;
        	let dropNodeLiIndex = dropId.split('-')[1];

        	//check the 'parentName' exist in the master entity design for the reference index
        	if (dropNodeLiIndex === curDenormDivIndex
        		&& !mstEntityDesignJson.hasOwnProperty('parentName')
        		&& mstEntityDesignJson.nodeName !== ''){
        			//for adding denormalize in index of the 'root' node  
        			derNodeName = mstEntityDesignJson.nodeName;
        			derDropId = mstEntityDesignJson.dropId;
        			derDenormalizeAttribs = mstEntityDesignJson.denormalizeAttribs;
	        }else if (dropNodeLiIndex === curDenormDivIndex
        		&& mstEntityDesignJson.hasOwnProperty('parentName')
        		&& mstEntityDesignJson.nodeName !== ''){
	        		//for adding denormalize in index of the 'embed' node (embed has 'parentName' element)
        			derNodeName = mstEntityDesignJson.nodeName;
        			derParentName = mstEntityDesignJson.parentName;
        			derDropId = mstEntityDesignJson.dropId;
        			derDenormalizeAttribs = mstEntityDesignJson.denormalizeAttribs;
        	}
        	
        	if (derDropId !== ''){
        		break;
        	}
        }
        //end of for loop
        
        //if there is no 'root/embed' exist for the denormalize index, add the denormalize attribute in the 'root'
        if (derDropId === ''){
        	for (let i=0;i<mstEntityDesignArr.length;i++){
	        	let mstEntityDesignJson = mstEntityDesignArr[i];		    	        	
	        	if (!mstEntityDesignJson.hasOwnProperty('parentName') && mstEntityDesignJson.nodeName !== ''){
        			//for adding reference in index of the 'root' node  
        			derNodeName = mstEntityDesignJson.nodeName;
        			derDropId = mstEntityDesignJson.dropId;
        			derDenormalizeAttribs = mstEntityDesignJson.denormalizeAttribs;
	    	    }
        	}
        	//end of for loop
        }
        
        //if identified the embed/root element for the reference index, add the reference attribute in the element
        if (derDropId !== ''){
        	for (let n=0;n<mstEntityDesignArr.length;n++){
	        	let mstEntityDesignJson = mstEntityDesignArr[n];
	        	if (mstEntityDesignJson.dropId === derDropId){
	        		mstEntityDesignJson.denormalizeAttribs.push({
    					'id': listId, 
    					'name': attribName, 
    					'srcRef': `${attribName} (${entityName})`
    				});
	        		break;
	        	}
	        }
        	//end of for loop
        }

    	//construct the target catalog
    	initiateFnlTgtTree();
	}
}

//function to get the entity name using physical and logical name
function getEntityName(phyEntityName, logEntityName){
	let entityName = '';
    if (logEntityName !== ''){
    	entityName = logEntityName.trim();
    }else{
    	entityName = phyEntityName.trim();
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

//when user drops the embed node, modal window will display to provide the joiner and filter condition
function displayConditionModal(index,curNodeDivId,curParentNodeDivId){
	let nodeName = $('#'+curNodeDivId).find('ul li').data('entityname');	
	
	let orgOptParentNodeArr = [];
	$('#'+curParentNodeDivId).find('option').each(function(){
		orgOptParentNodeArr.push($(this).val());
	});
	
	let orgSelParentVal = $('#'+curParentNodeDivId).find('select').val();
	let orgSelParentPrevVal = $('#'+curParentNodeDivId).find('select').data("prevsel");

	console.log('orgOptParentNodeArr: ',orgOptParentNodeArr);
	console.log('orgSelParentVal: ',orgSelParentVal);
	console.log('orgSelParentPrevVal: ',orgSelParentPrevVal);
	
	//construct parent node - option for condition modal
	let optCondParentNode = '';
	for (let m=0; m<orgOptParentNodeArr.length; m++){
		if (orgOptParentNodeArr[m] === orgSelParentVal){
			optCondParentNode += `<option value="${orgOptParentNodeArr[m]}" selected="true">${orgOptParentNodeArr[m]}</option>`;
		}else{
			optCondParentNode += `<option value="${orgOptParentNodeArr[m]}">${orgOptParentNodeArr[m]}</option>`;
		}		
	}
	
	//construct the selection group and append it in parent node div for modal window
	let splitCurNodeDivId = curNodeDivId.split('-');
	let selParentNodeCondId = `selParentNodeCondId-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}`;    
    let selCondParentNode = `    	        
    	<select class="form-control" data-prevsel="${orgSelParentPrevVal}" id="selCondParentNode" name="parentNodeCondSelGrp" onchange="changeParentNodeCondSelGrp(this, '${curNodeDivId}', '${curParentNodeDivId}')">								
			${optCondParentNode}
		</select>`;
    
    //empty the condition modal
	$('#conditionModal').empty();
	document.getElementById('conditionModal').style.display = 'block';

	let modalDiv = `
	<div class="modal fade" id="condition-add-modal" style="display: block; opacity: 1.0; top: 50px;" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="condition-add-modal" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
		    <div class="modal-content" id="condModal-${index}" data-entityname="${nodeName}">
		      <div class="modal-header" style="background: linear-gradient(150deg, #97c74e 0%, #2ab9a5 100%); color: #fff; max-height: 40px;">
		        <h5 class="modal-title" id="exampleModalLabel" style="margin-top: -5px; line-height: 1.0;">Reference Condition</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin: -1.4rem -1rem -1rem auto;" onclick="onCloseConditionClick(this, '${index}', '${curNodeDivId}', '${curParentNodeDivId}', '${nodeName}', '${orgSelParentVal}');">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		      				
		      	<div class="row">
		      		<div class="col-6">
				      	<label for="lblNodeName"><strong>Node Name</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
					    <input type="text" class="form-control" id="condJnNodeName" name="condNodeName" placeholder="Enter node name" value="${nodeName}" disabled>
					</div>
					<div class="col-6">
						<label for="lblParentName"><strong>Parent Name</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
					    <!-- <input type="text" class="form-control" id="condJnParentName" name="condParentName" placeholder="Enter parent name"
						data-prevsel="${orgSelParentPrevVal}" value="${orgSelParentVal}" disabled> -->
						${selCondParentNode}
					</div>
				</div>
												
	      		<ul class="tabs-nav cond-tab-ul mt-2">
				  <li class="cond-tab-li"><a href="#joiner-condition" class="cond-tab-a">Join</a></li>
				  <li class="cond-tab-li"><a href="#filter-condition" class="cond-tab-a">Filter</a></li>
				</ul>
				<div class="tabs-stage">
				  <div class="cond-div-tab" id="joiner-condition">				    
				
				    <!-- <label for="lblNodeName" style="margin-top: 5px;"><strong>Node Name</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
				    <input type="text" class="cond-input-area" id="condJnNodeName" name="condNodeName" placeholder="Enter node name" value="${nodeName}" disabled>

					<label for="lblParentName" style="margin-top: 10px;"><strong>Parent Name</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
				    <input type="text" class="cond-input-area" id="condJnParentName" name="condParentName" placeholder="Enter parent name"
					data-prevsel="${orgSelParentPrevVal}" value="${orgSelParentVal}" disabled> -->
					<!-- ${selCondParentNode} -->

					<label for="lblJoinCondition" style="margin-top: 10px;"><strong>Join Condition</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
					<textarea autocomplete="off" id="joinerCondition" class="form-control form-control-sm" rows="3" autofocus="true"></textarea>

					<label for="lblJoinType" style="margin-top: 10px;"><strong>Join Type</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
				    <select id="selCondJoinType" class="cond-input-area" style="width:50%;">						    
					    <option value="Inner">Inner</option>
					    <option value="Left Outer" selected>Left Outer</option>
					    <option value="Right Outer">Right Outer</option>
					    <option value="Outer">Outer</option>
					</select>
					
					<!-- <label for="lblSortedInput" style="margin-top: 10px;"><strong>Use Sorted Inputs</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
				    <select id="selCondSortedInput" class="cond-input-area">						    
					    <option value="Yes">Yes</option>
					    <option value="No">No</option>
					</select> -->					
						
					<div class="custom-control custom-checkbox mt-3 mb-3" style="position: inherit!important;">						
				  		<input type="checkbox" class="custom-control-input" id="selCondSortedInput" style="margin-left: -22px;">
				  		<label class="custom-control-label" for="selCondSortedInput" style="position: fixed!important;"><strong>Use Sorted Inputs</strong></label>
					</div>

				  </div>				  

				  <div class="cond-div-tab tab-none" id="filter-condition">
				    <!-- <label for="lblNodeName" style="margin-top: 5px;"><strong>Node Name</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
				    <input type="text" class="cond-input-area" id="condFlNodeName" name="condNodeName" placeholder="Enter node name" value="${nodeName}" disabled>

					<label for="lblParentName" style="margin-top: 10px;"><strong>Parent Name</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
				    <input type="text" class="cond-input-area" id="condFlParentName" name="condParentName" placeholder="Enter parent name"
					data-prevsel="${orgSelParentPrevVal}" value="${orgSelParentVal}" disabled> -->
					<!-- ${selCondParentNode} -->

					<label for="lblFilterCondition" style="margin-top: 10px;"><strong>Filter Condition</strong><sup class="pl-1"><small><i class="fas fa-asterisk text-danger"></i></small></sup></label>
					<textarea autocomplete="off" id="filterCondition" class="form-control form-control-sm" rows="3" autofocus="true"></textarea>
				  </div>				  
      
		      
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger btn-sm btn-green close-condition-modal" data-dismiss="modal" onclick="onCloseConditionClick(this, '${index}', '${curNodeDivId}', '${curParentNodeDivId}', '${nodeName}', '${orgSelParentVal}');">Close</button>
		        <button type="button" class="btn btn-info btn-sm btn-green save-condition-modal" id="saveCondModal" onclick="onSaveConditionClick(this, '${index}', '${curNodeDivId}', '${curParentNodeDivId}', '${nodeName}', '${orgSelParentVal}');">Save</button>
		      </div>
		    </div>
		  </div>
		</div>`;

	$('#conditionModal').fadeIn('fast');
	$('#conditionModal').append(modalDiv);
	$('#conditionModal').show();
	
	//adding event listener for click and close button in modal
	document.querySelector('.close-condition-modal').addEventListener('click', function(){
		onCloseConditionClick(this, index, curNodeDivId, curParentNodeDivId, nodeName, orgSelParentVal);
	});
	document.querySelector('.save-condition-modal').addEventListener('click', function(){
		onSaveConditionClick(this, index, curNodeDivId, curParentNodeDivId, nodeName, orgSelParentVal);
	});
	
	////$('#condition-add-modal').modal('show');
	
	//$('.tabs-stage div').not(':first').hide();
	//$('div.tabs-stage div.cond-div-tab').not(':first').hide();
	$('.tabs-nav li:first').addClass('tab-active');
	
	//default applying autocomplete for joiner
	autocomplete(orgSelParentVal,curNodeDivId, 'joinerCondition');
	//adding keyup event listerner for autocomplete
	document.querySelector('#joinerCondition').addEventListener('keyup', function(e){
		updateCondition(e,orgSelParentVal,curNodeDivId,'joinerCondition');
	});

	$('.tabs-nav a').on('click', function(event) {
	  // Add class
	  const currentTab = $(this).text();
		if(currentTab == 'Join'){
			autocomplete(orgSelParentVal,curNodeDivId, 'joinerCondition');
			//adding keyup event listerner for autocomplete
			document.querySelector('#joinerCondition').addEventListener('keyup', function(e){
				updateCondition(e,orgSelParentVal,curNodeDivId, 'joinerCondition');
			});
		}else{
			autocomplete(orgSelParentVal,curNodeDivId, 'filterCondition');
			//adding keyup event listerner for autocomplete
			document.querySelector('#filterCondition').addEventListener('keyup', function(e){
				updateCondition(e,orgSelParentVal,curNodeDivId, 'filterCondition');
			});
		}
	  $('.tabs-nav li').removeClass('tab-active');
	  $(this).parent().addClass('tab-active');
	  
	  //$('.tabs-stage div').hide();
	  //$('div.tabs-stage div.cond-div-tab').eq(i).hide(); 
	  $('div.tabs-stage').find('div.cond-div-tab').removeClass('tab-none');
	  
	  for (let i=0;i<$('div.tabs-stage').find('div.cond-div-tab').length;i++){
		  if (i !== $(this).parent().index()){
			  $('div.tabs-stage > div.cond-div-tab').eq(i).hide();
		  }else{
			  $('div.tabs-stage > div.cond-div-tab').eq(i).show();  
		  }
	  }
	  //console.log("Index: ",$(this).parent().index())		  
	  //console.log($('div.tabs-stage').find('div.cond-div-tab').length);
	});	
}

//Auto complete code for joiner and filter
function autocomplete(curParentNodeEntity,curNodeDivId, el){
	if(awesompleteVar instanceof Awesomplete){
		awesompleteVar.destroy();
	}
	let input = document.querySelector('#'+el);
	let currentNodeEntity = $('#'+curNodeDivId).find('ul li:eq(0)').data('entityname');
	let array = [];
	array.push(currentNodeEntity);
	array.push(curParentNodeEntity);
	//console.log(array);
	awesompleteVar = new Awesomplete(input, {
		autoFirst:true,
		minChars : 1,
		list: array,
	});
}

function updateCondition(e, curParentNodeEntity,curNodeDivId, el){
	let tableName = '';
	let currentNodeEntity = $('#'+curNodeDivId).find('ul li:eq(0)').data('entityname');
	let input = e.target.value;
	//console.log('currentNodeEntity: ',currentNodeEntity)
	//console.log('input: ',input)
	if(input.trim() == ''){
		autocomplete(curParentNodeEntity,curNodeDivId,el);
	}
	if(input.includes('=') && input.indexOf('and')==-1){
		tableName = input.substring(input.indexOf('=')+1,input.length-1).trim();
	}else if(input.includes('and')){
		tableName = input.substring(input.lastIndexOf(' ')+1,input.lastIndexOf('.'));
	}else{
		tableName = input.substring(0,input.lastIndexOf('.')).trim();
		tableName = tableName.substring(tableName.lastIndexOf(' ')+1,tableName.length);	
	}
	if(event.keyCode == 190){
		updateAttributes(tableName, input, currentNodeEntity, curParentNodeEntity);
	}
	let lastChar = input[input.length-1];
	
	if(lastChar == '=' || input.endsWith('and')){		
		let array = [];
		array.push(input + ' ' +currentNodeEntity);
		array.push(input + ' ' +curParentNodeEntity);
		//console.log(array);
		awesompleteVar.list = array;
		awesompleteVar.evaluate();
	}
}

function updateAttributes(tableName, input, currentNodeEntity, curParentNodeEntity){
	let array = [];
	//console.log(srcCatalogName+":::"+currentNodeEntity+":::"+curParentNodeEntity)
	let catalogs = $('#catalogsArray').text();	
	//console.log("Auto Complete - Catalogs: ",catalogs);
	//console.log("Auto Complete - Input: ",input);
	//console.log("Auto Complete - TableName: ",tableName)
	let catalogArray = JSON.parse(catalogs);
	for(let catalog of catalogArray){
		//if( catalog.catalogName === srcCatalogName && (catalog.entityName === currentNodeEntity || catalog.entityLogName === currentNodeEntity) ){
		if( catalog.catalogName === srcCatalogName && (catalog.entityName === tableName || catalog.entityLogName === tableName) ){
			for(let attr of catalog.attributeList){				
				array.push(input+attr.split('::')[0]);
			}
		}
		/*if( catalog.catalogName === srcCatalogName && (catalog.entityName === curParentNodeEntity || catalog.entityLogName === curParentNodeEntity) ){
			for(let attr of catalog.attributeList){
				array.push(input+attr.split('::')[0]);
			}
		}*/
	}
	//console.log("Auto Complete - Final Array: ",array);
	awesompleteVar.list = array;
	awesompleteVar.evaluate();
}

function changeParentNodeCondSelGrp(e,curNodeDivId,curParentNodeDivId){
	let condParentNodeVal = e.value;
	let condParentNodeId = e.id;
	console.log('e.id: ',e.id);
	console.log('e.id: ',e.value);
	console.log('e.curNodeDivId: ',curNodeDivId);
	console.log('e.curParentNodeDivId: ',curParentNodeDivId);

	let orgOptParentNodeArr = [];
	$('#'+curParentNodeDivId).find('option').each(function(){
		orgOptParentNodeArr.push($(this).val());
	});

	let orgSelParentVal = $('#'+curParentNodeDivId).find('select').val();
	let orgSelParentPrevVal = $('#'+curParentNodeDivId).find('select').data("prevsel");
	
	//if parent node value of designer area is different from parent node value of condition modal, then update the select option in parent node of designer area 
	if (condParentNodeVal !== orgSelParentVal){
		//parent node value is changed through condition modal window
		//so set the parent node value and prev select value in designer area
		$('#'+curParentNodeDivId).find('select').val(condParentNodeVal); //from condition parent node value 
		$('#'+curParentNodeDivId).find('select').data("prevsel", condParentNodeVal); //from condition parent node value 
		
		//get the select id of parent node in designer area
		let curParentNodeSelId = $('#'+curParentNodeDivId).find('select').attr('id');
		
		//call the function to edit the target catalog
		getNodeUsingParentNodeSel(curParentNodeSelId,condParentNodeVal,orgSelParentVal);
	}
}

//when user clicks the close button on modal window
function onCloseConditionClick(e,divIndex,curNodeDivId,curParentNodeDivId,nodeName,parentNodeName){
	//$('#conditionModal').empty();
	//document.getElementById('conditionModal').style.display = 'none';
	////$('#condition-add-modal').modal('hide');
	onSaveConditionClick(e,divIndex,curNodeDivId,curParentNodeDivId,nodeName,parentNodeName)
}

//when user clicks the save button on modal window 
function onSaveConditionClick(e,divIndex,curNodeDivId,curParentNodeDivId,nodeName,parentNodeName){
	//get the node name - from save button id
	//let condNodeModalId = $('#'+e.id).parents().eq(2).data("entityname"); //not using in the below loop
	////$('#condition-add-modal').modal('hide');
	console.log("////////Save Condition Loop1////////////");
	//console.log("divIndex: ",divIndex)
	//console.log("curNodeDivId: ",curNodeDivId)
	//console.log("curParentNodeDivId: ",curParentNodeDivId)
	//console.log("nodeName: ",nodeName)
	//console.log("parentNodeName: ",parentNodeName)
	
	let curNodeLiId = $('#'+curNodeDivId).find('ul li').attr("id");
	let splitCurNodeDivId = curNodeDivId.split('-');
	let condDivId = `cond-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}`;
	let condDivSpanId = `cond-span-${splitCurNodeDivId[1]}-${splitCurNodeDivId[2]}`;
	//console.log('condDivId: ',condDivId);
	
	$('#'+condDivId).find('span.ed-cond-span').empty();
	$('#'+condDivId).find('ul').empty();
	
	//construct the joiner and filter element
	//get the modal values
	let joinerCondition = $.trim($("#joinerCondition").val());
	let selCondJoinType = $("#selCondJoinType").val();
	//removed as it is checkbox
	/*let modSelCondSortedInput = $("#selCondSortedInput").val();	
	let selCondSortedInput;
	if (modSelCondSortedInput !== '' && modSelCondSortedInput === "Yes"){
		selCondSortedInput = true;
	}else{
		selCondSortedInput = false;
	}*/
	let selCondSortedInput = $('#selCondSortedInput').is(":checked");
	let filterCondition = $.trim($("#filterCondition").val());
	
	console.log("////////Save Condition Loop2////////////");
	console.log("joinerCondition: ",joinerCondition)
	console.log("selCondJoinType: ",selCondJoinType)
	console.log("selCondSortedInput: ",selCondSortedInput)
	console.log("filterCondition: ",filterCondition)
		
	//let joinerImg = "data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAACoAAAAqCAIAAABKoV4MAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAANuSURBVFhH7ZhNKKxRGMfPsBhFSomwsFFYyUqUJpvJihJz78pGKSsWpJSPkXGRiIWuYsZHiiyU2GkmXIYoIen6FvmK5GuQj7l/c557e6+ZM+57m/e1md9m5n3OM8//ec8573med5jT6Xx8fJycnGxvb6+trW1oaPimGAgOieHh4b29PegCdn9/39XVVV9fPzExsba29lNhFhcXBwYGkMrS0tKbvM1mg/bZ2RlPRx3Gx8cbGxuvr69Za2srMiCzWtze3tbV1WEyGP8gs4q0tLRgIRiWYWtri2xq8fr6ip0+Pz/PsPCbm5tkVgvIt7W1+eX98hL5h4cHHMCjo6Pr6+tkUgCh/PHxcUJCAmMsJiYmMzMTThsbGzTmO7zJx8fHQ/4PkZGRFRUVp6en5OELhPJHR0exsbGkLCE1NXV6epqcvDI2NnZwcEAXAoTyKAPV1dW5ubkZGRmBgYEk7iIqKgq1kvzElJSUJCcno3jStSeE8hwM393d2e32vLw8aRLBwcGDg4PkJGB2dhaeyLWvr49MbnwgL6WnpyciIoLLg5CQELPZTGMCkDQ8g4KCMJEOh4OsEmTIAzyK0g2h1WrLy8unpqZ+eAJ3X1VVRa6MZWdn7+7uUqDfyJMHc3NziYmJFPIjAgIC6JsL/BD7kQK5kC0P0B5JV0EWSGhkZIQC/Z88+qKwsDCKJ5P09PSFhQUKJFf+6emptLSUIrnAxv7q4osb+fn5Op1Oo9FwT9x3WVnZzc0NxXIhQx6/LCws5LE4aWlpq6urNOyJpqYm7hkdHd3b20tWCf8qj52s1+t5LA6Oo52dHRr2xOXlZVxcHDxTUlLQzZH1b4TymOft7W3cXH9/v8FgCA0N5aqcnJycDw9/nBPwLCoqQh5kckMof3JykpSUxMXegYjvltAjNTU16J6fn5/p2hNCefeKB/C8oTF9eXkhJ69cXV3RNzFC+XcVLzw8vLi4eHl5mYZ9hFD+4uKioKAgKyursrKyu7vb/bz0CUJ5dfDL++U/Xf4TX7DRSzKTyeS9fCkBDtDm5mb0L8xisQwNDZFZLfb3941G4+HhIcPMox+1Wq14u0NSmBZFQSnCSwgaJxRGVNe3//VmZmbQIGAVOjo6Ojs7vysGgkMYmx3HOU53SL/Jg/Pz85WVFcwBsCkGgqNjRrdC5djp/AVXAD36W31WOgAAAABJRU5ErkJggg==";
	//let filterImg = "data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAADEAAAAqCAIAAACV7yQTAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAhPSURBVFhHxZhpbFTXFcfPvW+dfZ/xMh4PBhuMjTE0EEPqOJUVgvoldI0qolahqaqkUqWq/VJVKpVAqFRRq/ZL+6lqi/hQlabqQiCJZKIGDKExhYQYG9vYjO3ZPJtne/PW2zv4KcoHiGdslfw0o3lz373n/eec8847dxAhRFGUa9eu3bp1q1Ao8DxPR+AxghCiAnp7e/ft29fZ2VkfkSTp7Nmz6XR6//79ra2tLMuuTX2clEql6enpWCx2+PDhwcFBuHTp0qlTp6gm6p7PlvPnz58+fbpYLOKbN28eOHAgEAiYmjeELINhmMcbZmRkRFXVRCLBDA8PDw0N+Xw+88zD0AkkK7CwCrNFMltE82W4VyT3ijBfgrtFdDtn3CmiuSIkJPBhEHhzVbPQVJ6YmKD5g5kHmMOP4NYK/HEGFmv1hAxaSKed9LjR7hB6sh2NhuFIFH+tCw5FwSPAP+PwTpwUyubCpqDho9ms6zpei6U5/AhcDNg58sUOeKoF+r2oy4lCFhBRPWQZCWJVuC/B+wmV0fWj3eDh4e/L5EIM8hVzeeOsKWFGR0f7+vq8Xu/a6EPxWuFyEqJ+xBM4MwO/+QguLMPFJRhLwngGJnLw0SqkNeZuUgoJaKefGfSjsgGXU4THKGAxjTTC9evXw+EwNr+tx6AP3UmBwNQd84cp+PMcXEtBsgocQFiAXU4YDcBIWLgSUwu1erbv8cD+DnQ5uZFS16im3S7ISvULfD4EL22HH/XBL/fBz/fCD3bAC1F4OgBhkXQ4GYZjxub1+6vkr5MyqoGLJ4vFNQNN0Kgmnw0qGuQM2O6Gk3vhq1ESFImskpWysZzX76X1yZh2e051yjhbZv81A3ae63HDgB/PlJt2VaOaEAIHCzdSYOWhrEJSIj4BGA3pNZwtMAsZvFJlgeMdXpYRQNb1gB1kDTpdoGimhcZpVBPloB/N5AhigMGACJopQkoneZYoVsI6AUSjomurRS2ClaGQvFTRczWg+U1/QKrUnKua0NTmAhuHChqwGKwMxMpG/ZaSkFVGfg35DCwApvlU4/FCBceLSkYiNQ0ibvTvNFqz0CBNaKL0+mEqAzYeGIRCItIBKgRyGPI8KdsIsYCmG1rZ6HMZETvcL5LVGgz4oKr93/z0nw8SF/5x59JUySIAj0HX0aJE6LGuEFGDLhYHAck1pgLckswWFaOmqAW5XixCgvG397K0QJuG1qMhTVc/SH79+LuHTkwcP594e6ZaVEBkgUUQtkHUDi4BGSyarpGpGmAH6W3Xe92k34dKqp6qklKNBp157Z30iyevXhyPrfvMoKyvKZuXXv31xF+WHIVQFAc9tFjPFYhdAJFBSyW4kTdUrn4hTkJbMNplxzpi3lthr8YxddRySUmWgcbxued6ta39P/3T1NxCwbT7aNbXZBHZ/b0BsboKqwVDUbO5ylhMc9tA5GlZx9vc+AkfahEBi5DmYEqFu0lkq+E+q9DhsHE8b2BgdMO+movdS25tdzqdgmn30az/vOM4ZnggGJuNyyXVgTRUqeaKih+p91O1QlFOF5V0RZsqqIWqppQUj6LykpwqSXdz5Zl4sZLM3ptNjX+YHr+R4cuFX7wyGAnZTLsPY+1511Cn67Zzx48NvHTi3XSJ6XYw/Erm9xeLPC0JCCQNVAIOHiwINA1xHFDH0IofcbMdXt7rFf1u141FPZ5KnfxmT3f7pwn6mEa7761h58+ODXzjx2PTUxrL6G6Pze2xCyLHMJgWnxxNXIQ4ltYnFmHcHXY8szvosGCeRe/PlF5/c/6Fg95DB8KmrfVoqFdZY0vY1RGyv/VhVnZ4q1U5t7K6Es+nlnP0lY7n08l8IrmaTOTT6QL1kM/JEQQqYt+4muz1GCdf3UdzwDT0aNZi14QmSv82r8jjsUVd6IqKQa+1LWgPB5yRoKcz5I+2tG1rCXe37d4TeWYo0hn1htq9GcOGq6snXt5tpzdqAzTXP33M97+y43tP2vRyldjs4HSwfo/QErCHW9ydLZ4trYEtrd07Wp0+h8EJUyvw1pv//dbTrS6naC5ujKY10QQ69e2B5yOaWlYVndRqGn1VqkpZUkslRZYVXdGyZXVmhbw9NnlsOLSzx2+ubJimNVEsFv617/Q/Yc1rMqgGUlRDVmmBNCTFMHSoKHq8DJfG5w73WA49Vd/XNstGNFEiba7fvbIramQ0HdNaQB0ma4aiG6pu0BZl4nZ6r0f97pe2m7ObZIOaKLt6/L96caszn1CrqqpoNJCyrGULtempdIeW+8nRPkT7wA2xcU2UL3yutUtUlGRWWkqW7sfLC/HSYnrm1vyz/V6Xq7m8/iSb0mQYRLAKEPDpPr9qdfJ+T1tPO6K3GW0bNsGmNNF7UOSQyGGOtlE2C7JYdMwyoNMaZs7YEJvUhKw8dgnYbWEsHHAYNIPQnbXIr1+yP4XN+skmsgEbG3KwPhvnFBkOIx6B5TPUhDG2cLjFyUT9fLub8ztYu8DYOLDQ/fIm2JQmCvVT2MVtD1m6/ELYwwfsjF2gGbY5TbSKbLiQUGgT1dtqG9rm3NNp39lu2xK0WDAINNE3BFViGAamb0mSzLHm2Rp2LMwullYqAt1GVZTUfLrDzfk8GylOtKtXFEUURdzW1jY5OWkON8/Lz/d+ebg9s5yOzy1pK+nRbcJvfzgUCtnN082wtLRUqVQCgQCanZ09c+bMyMjIwYMHOY5rNo4Pnd/IhumT0FglEolz5855PJ6jR4/W/x8fHx+/cuWKqqp0iGGYZi1SVXVlDxaR+kfd5oMzDUHXVqtVmj+hUOjIkSO0uzTXZ7PZeDyeyWTWJj2Y/JigAqxWazAYjEQi9X9WAf4H58cuI9PKmogAAAAASUVORK5CYII=";

	//generate the id for the list element
	let listJoinerTime = getDesignerTime();
	let listJoinerId = `${listJoinerTime}-${divIndex}`;
	
	//generate the id for the list element
	let listFilterTime = getDesignerTime();
	let listFilterId = `${listFilterTime}-${divIndex}`;
	
	//List Method
	if (joinerCondition !== ''){
		let divJoinerAppend = 
		    `<li id="${listJoinerId}" title="Joiner: ${joinerCondition}" data-nodename="${nodeName}" data-parentname="${parentNodeName}" data-jointype="${selCondJoinType}" data-isorted="${selCondSortedInput}" data-type="Condition" data-conditiontype="Joiner" style="display:inline;">
				<span id="span-${listJoinerId}">
					<img src="${joinerImg}" alt="Joiner Condition" style="width: 20px;" />
				</span>
				<span class="pointer">
					<i class="fas fa-times text-danger joinerRemove"></i>
				</span>
			</li>`;
		$('#'+condDivId).find('ul').append(divJoinerAppend);	
	}
	
	if (filterCondition !== ''){
		filterCondition = filterCondition.replace(/\"/gi, "'");
		console.log("Replaced filterCondition: ",filterCondition)
		let divFilterAppend = 
		    `<li id="${listFilterId}" title="Filter: ${filterCondition}" data-nodename="${nodeName}" data-parentname="${parentNodeName}" data-type="Condition" data-conditiontype="Filter" style="display:inline;">
				<span id="span-${listFilterId}">
					<img src="${filterImg}" alt="Filter Condition" style="width: 20px; height: 20px;" />
				</span>
				<span class="pointer">
					<i class="fas fa-times text-danger filterRemove"></i>
				</span>
			</li>`;
		
		$('#'+condDivId).find('ul').append(divFilterAppend);
	}
	
	let editCondition = `<i class="fas fa-edit text-info edit-condition-modal" title="Edit Condition"></i>`;
	$('#'+condDivId).find('span.ed-cond-span').append(editCondition);
	document.querySelector('.edit-condition-modal').addEventListener('click', function(){
		onEditConditionClick(this, condDivId);
	});
	//Table Method
	/*let condTblDetail = '';
	if (joinerCondition !== ''){
		condTblDetail += `<td style="padding-right: 10px;">
			<div id="${listJoinerId}" title="Joiner: ${joinerCondition}" data-nodename="${nodeName}" data-parentname="${parentNodeName}" data-jointype="${selCondJoinType}" data-isorted="${selCondSortedInput}" data-type="Condition" data-conditiontype="Joiner">
				<span id="span-${listJoinerId}">
					<img src="${joinerImg}" alt="Joiner Condition" style="width: 20px;" />
				</span>
				<span class="pointer">
					<i class="fas fa-times text-danger joinerRemove"></i>
				</span>
			</div>
		</td>`;
	}
	
	if (filterCondition !== ''){
		condTblDetail += `<td>
			<div id="${listFilterId}" title="Filter: ${filterCondition}" data-nodename="${nodeName}" data-parentname="${parentNodeName}" data-type="Condition" data-conditiontype="Filter">
				<span id="span-${listFilterId}">
					<img src="${filterImg}" alt="Filter Condition" style="width: 20px; height: 20px;" />
				</span>
				<span class="pointer">
					<i class="fas fa-times text-danger filterRemove"></i>
				</span>
			</div>
		</td>`;
	}
	
	let conditionTbl = `<table>
		<tbody>
			<tr>
				${condTblDetail}
			</tr>
		</tbody>
	</table>`;
	
	//empty the div and load the condition as table
	$('#'+condDivId).empty().append(conditionTbl);
	*/

	let dsgnJoinerJson = {};
	if (joinerCondition !== ''){
		dsgnJoinerJson['dropId'] = listJoinerId;
		dsgnJoinerJson['condition'] = joinerCondition;
		dsgnJoinerJson['type'] = selCondJoinType;
		dsgnJoinerJson['iSorted'] = selCondSortedInput;
	}
	
	let dsgnFilterJson = {};
	if (filterCondition !== ''){
		dsgnFilterJson['dropId'] = listFilterId;
		dsgnFilterJson['condition'] = filterCondition;
	}
	
	//load the joiner and filter conditions into master entity design array
	for (let p=0;p<mstEntityDesignArr.length;p++){
		let mstEntityDesignJson = mstEntityDesignArr[p];
		if (mstEntityDesignJson.dropId === curNodeLiId && mstEntityDesignJson.nodeName === nodeName){
			mstEntityDesignJson['join'] = {};
			mstEntityDesignJson['join'] = dsgnJoinerJson;
			
			mstEntityDesignJson['filter'] = {};
			mstEntityDesignJson['filter'] = dsgnFilterJson;		
			
			break;
		}
	}	
	
	//hide the modal window
	$('#conditionModal').hide();
	
	console.log("Save Loop - Entity Design Arr: ",mstEntityDesignArr)
}

//function when user hit the edit condition button 
function onEditConditionClick(e,curCondDivId){
	//check node and parent node exist for the condition
	//if not exist, then display alert message
	//otherwise get the joiner and filter values from master entity json array/direct elements and display it in modal window 
	//need to open the condition modal 
	
	let splitCurCondDivId = curCondDivId.split('-');
	let curNodeDivId = `end-${splitCurCondDivId[1]}-${splitCurCondDivId[2]}`;
	let curParentNodeDivId = `epnd-${splitCurCondDivId[1]}-${splitCurCondDivId[2]}`;
	let divIndex = splitCurCondDivId[2];
	
	let curNodeLiExist = $('#'+curNodeDivId).find('ul li').length;
	
	//node exist for the condition, then get the values and display it
	if (curNodeLiExist > 0){		
		//display the conditional modal
		displayConditionModal(divIndex,curNodeDivId,curParentNodeDivId);
		
		let curNodeLiId = $('#'+curNodeDivId).find('ul li').attr('id');
		//get the matched element from array using dropId
		let derJoinerJson = {};
		let derFilterJson = {};
		for (let i=0;i<mstEntityDesignArr.length;i++){
			if (mstEntityDesignArr[i].dropId === curNodeLiId){
				derJoinerJson = mstEntityDesignArr[i].join;
				derFilterJson = mstEntityDesignArr[i].filter;
				break;
			}
		}
		
		console.log('//////////////////Edit Condition Modal////////////////');
		console.log('derJoinerJson: ',derJoinerJson);
		console.log('derFilterJson: ',derFilterJson);
		
		//load the existing joiner/filter values in the condition modal
		if (derJoinerJson.hasOwnProperty('condition')){
			$("#joinerCondition").val(derJoinerJson.condition);
			$("#selCondJoinType").val(derJoinerJson.type);
			if (derJoinerJson.iSorted === true){
				$("#selCondSortedInput").prop("checked", true);
			}else{
				$("#selCondSortedInput").prop("checked", false);
			}			
		}
		
		if (derFilterJson.hasOwnProperty('condition')){
			$("#filterCondition").val(derFilterJson.condition);
		}		

	}else{
		alert("Embed node doesn't exist. Please add embed node.")
	}

}

//when editing the node name to focus the span and capture the result 
function onFocusEditNodeName(){
	//the event is triggered when user changes the node name in the designer area
	$('.ed-span').focusin(function(){
	    $(this).css("background-color", "#FFFFCC");
	    $(this).css("border", "1px solid #97c74e");
	    $(this).css("outline", "none");
	    $(this).css("border-radius", "5px");
	});
	$('.ed-span').focusout(function(){
	    $(this).css("background-color", "#FFFFFF");
	    $(this).css("border", "none");
	    let edSpanText = $(this).text().trim();
	    let edSpanId = this.id;
	    //call the edit name function once renamed
	    onEditNodeName(edSpanId, edSpanText);
	});
}

//function when user edit the name of the node
function onEditNodeName(edSpanId,edSpanText){
	let edSpanParentLiId = $('#'+edSpanId).parent().attr('id');
	let edSpanParentLiType = $('#'+edSpanId).parent().data('type');
	console.log("edSpanParentLiId: ",edSpanParentLiId);
	console.log("edSpanText: ",edSpanText);
	
	//check and get the root, embed or denormalize node from array to get the previous name(before editing)
	//root and embed nodes
	let orgRootNodeName = ''; //used to remove the collection from 'gblEntityDesignJson' - it holds collection name
	let orgRootDropId = ''; //used to remove the collection from 'gblEntityDesignJson' - it holds drop id of collection
	let derNodeName = '';
	let updNodeNameType = '';
	if (edSpanParentLiType === 'Entity'){
		for (let i=0;i<mstEntityDesignArr.length;i++){
			let mstEntityDesignJson = mstEntityDesignArr[i];
			console.log('MstEntityDesignJson: ',mstEntityDesignJson);
			
			//get the root collection name;
			if (!mstEntityDesignJson.hasOwnProperty('parentName')){
				orgRootNodeName = mstEntityDesignJson.nodeName; //used for 'gblEntityDesignJson' removal
				orgRootDropId = mstEntityDesignJson.dropId; //used for 'gblEntityDesignJson' removal
			}
			
			let isMatched = false;
			//root and embed nodes
			if (mstEntityDesignJson.dropId === edSpanParentLiId	&& !mstEntityDesignJson.hasOwnProperty('parentName')){
				//if it is root node name changes, then below Step1 flow - need to remove the collection from gblEntityDesignJson
				//orgRootNodeName = mstEntityDesignJson.nodeName;
				updNodeNameType = 'root';				
				isMatched = true;
			}else if(mstEntityDesignJson.dropId === edSpanParentLiId && mstEntityDesignJson.hasOwnProperty('parentName')){
				//if it is root node name changes, then below Step2 flow - no need to remove the collection from gblEntityDesignJson
				updNodeNameType = 'embed';
				isMatched = true;
			}
			
			if (isMatched){
				derNodeName =  mstEntityDesignJson.nodeName;
				mstEntityDesignJson.nodeName = edSpanText;
				break;
			}
		}
		//end of for loop
	}
	else if (edSpanParentLiType === 'Attribute'){		
		//for denormalize
		updNodeNameType = 'denormalize';
		for (let i=0;i<mstEntityDesignArr.length;i++){
			let denormalizeAttribsArr = mstEntityDesignArr[i].denormalizeAttribs; 
			
			//denormalize array
			for (let j=0;j<denormalizeAttribsArr.length;j++){
				let denormalizeAttribsJson = denormalizeAttribsArr[j];
				
				if (denormalizeAttribsJson.id === edSpanParentLiId){
					//update the changed name in the array of denomalize values
					denormalizeAttribsJson.name = edSpanText;
					break;
				}
			}
			
		}
	}
	
	//for root/embed - node name changes
	if (updNodeNameType === 'root' || updNodeNameType === 'embed'){
		//update the parent name for all sub nodes in master entity design array
		for(let x=0;x<mstEntityDesignArr.length;x++){
			let mstJson = mstEntityDesignArr[x];

			if (mstJson.hasOwnProperty('parentName') && mstJson.parentName === derNodeName){
				mstJson.parentName = edSpanText;
			}
	
		}

		//construct 'selParentNodeArr' group array with new node name values
		//re-initilaize - used to construct designer area again
		selParentNodeArr = [];	
		for (let y=0;y<mstEntityDesignArr.length;y++){
			selParentNodeArr.push(mstEntityDesignArr[y].nodeName);
		}
		
		//remove the existing collection in the 'gblEntityDesignJson' as either root or embed node name is changed and load the new collection
		//Used to construction of target catalog
		Object.keys(gblEntityDesignJson).forEach(function(key) {
			let collectionName = key;
			let gblCollectionJson = gblEntityDesignJson[key];
			console.log('gblCollectionJson - nodeName: ',gblCollectionJson['nodeName']);
			console.log('gblCollectionJson - dropId: ',gblCollectionJson['dropId']);
			if (orgRootNodeName === gblCollectionJson['nodeName'] && orgRootDropId === gblCollectionJson['dropId']){
				delete gblEntityDesignJson[orgRootNodeName]; //as root or embed node name is changed
			}
		});
		
		
		//check collection again whether it is properly deleted
		if (!gblEntityDesignJson.hasOwnProperty(orgRootNodeName)){			
			delete gblEntityDesignJson[orgRootNodeName];
		}	
		

		//construct the designer area using above 'mstEntityDesignArr' and 'selParentNodeArr'
		constructDesignerArea(mstEntityDesignArr);
			
		//re-initialize the target catalog UI to display the renamed node and load the 'gblEntityDesignJson' with new collection as existing collection is removed in the above steps
		initiateFnlTgtTree();
	}
	else{
		//for 'denormalize' - node name changes
		//re-initialize the right side target to display the renamed node in the target
		initiateFnlTgtTree();		
	}
	
	
	
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
	//let rootCollectionDropId = ''
	for (let i=0;i<mstEntityDesignArr.length;i++){
		let entityDesignJson = mstEntityDesignArr[i];
		
		if (!entityDesignJson.hasOwnProperty('parentName')){
			let collectionId = entityDesignJson.dropId;
			//rootCollectionDropId = entityDesignJson.dropId;
			//remove the collection if already exist in the target catalog
			if($('#'+'tgt-'+collectionId).length){
				$('#'+'tgt-'+collectionId).remove();
			}
			
			//that is 'root', 'root' node doesn't have the 'parent' node
			constructFnlTgtRootTree(entityDesignJson);
			
			//to initiate fa - plus/minus 
			clickTgtFaPlusMinusOps(entityDesignJson.dropId);
		}else{
			//that is 'embed', 'embed' node have the 'parent' node
			constructFnlTgtEmbedTree(entityDesignJson);
			
			//to initiate fa - plus/minus 
			clickTgtFaPlusMinusOps(entityDesignJson.dropId);
		}		
	}
	//end of for loop
	
	initiateTgtFaPlusMinusOps();
    /*let children = $('#tgt-' + rootCollectionDropId + ' > span').parent('li.parent_li').find(' > ul > li');
    
	if (children.is(":visible")) {
		console.log("Loop 0")
		children.hide('fast');
		$('#tgt-' + rootCollectionDropId + ' > span').find('i').addClass('fa-plus-square').removeClass('fa-minus-square');
	}*/
	
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
	
	//////////////////Validation of Collection Name/////////////////
	//check if root collection already exist in the global json
	//if exist, then alert the user to change the name
	let isCollectionExist = false;
	Object.keys(gblEntityDesignJson).forEach(function(key) {
		let gblCollectionName = key;
		let gblCollectionJson = gblEntityDesignJson[key];
		//console.log('gblCollectionJson - nodeName: ',gblCollectionJson['nodeName']);
		//console.log('gblCollectionJson - dropId: ',gblCollectionJson['dropId']);
		if (gblCollectionName === collectionInfo['nodeName'] &&  gblCollectionJson['dropId'] === collectionInfo['dropId']){
			isCollectionExist = false;
		}else if(gblCollectionName === collectionInfo['nodeName'] && gblCollectionJson['dropId'] !== collectionInfo['dropId']){
			isCollectionExist = true;
		}
	});
	
	//if collection not exist
	if (!isCollectionExist){
		gblEntityDesignJson[wrkTgtCollection] = collectionInfo;
	}else{
		//if collection already exist
		alert("Collection already exist with same name in the Target. Please rename the collection.")
	}

	//gblEntityDesignJson[wrkTgtCollection] = collectionInfo;
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
                	
                	<!--<i class="pointer fas fa-times text-danger tgtNodeRemove" onclick="removeTargetCollection(this,'tgt-span-${dropId}','${entityDesignJson.nodeName}')"></i>-->
                	<i class="pointer fas fa-times text-danger" onclick="removeTargetCollection(this,'tgt-span-${dropId}','${entityDesignJson.nodeName}')"></i>
                </span>
                
            	<ul id='tgt-ul-${dropId}'>
            		${attribLi}
            	</ul>
            </li>`;

	//$('#sourceEntities-ldr').append(rootDiv);
	$('#fnlTgtCatalog').prepend(rootDiv);
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
	            <span class="text-dark pl-2 pt-1 ov-el"
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
	//console.log('///////////Attribute Loop///////////////');
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
                <span class="text-dark pl-2 pt-1 ov-el"
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


//Added for Data Switch - to capture the 'parent node' value
//when user changes the 'parent node' select option
function changeParentNodeSelGrp(e){
	console.log('Parent Node Id: ',e.id);
	console.log('Parent Node Value: ',e.value);
	
	//get the previous value
	let prevSel = $('#'+e.id).data("prevsel");
	console.log('Parent Node Prev Value: ',prevSel);

	//update the new selection to previous value
	$('#'+e.id).data("prevsel",e.value);
	 
	getNodeUsingParentNodeSel(e.id,e.value,prevSel);
}

//when user changes the parent node
function getNodeUsingParentNodeSel(parentNodeSelId,parentNodeSelVal,parentNodePrevSelVal){
	//Add the select group for 'parent node' dynamically
	//to get the id number alone from 'parent' node div
    let splitCurParentNodeSelId = parentNodeSelId.split('-');
    let parentNodeSelDivIndex = splitCurParentNodeSelId[2];

    //let curNodeDivId = 'end-' + splitCurParentNodeSelId[0] + '-' + splitCurParentNodeSelId[1]; //derive 'node' id using 'parent' node id
    ////console.log('curNodeDivId: ',curNodeDivId)
    ////console.log($('#'+curNodeDivId).find('ul').eq(1));

    //updating the master entity design element for embed node when user changes the parent node
    for (let i=0;i<mstEntityDesignArr.length;i++){
    	let mstEntityDesignJson = mstEntityDesignArr[i];
    	let dropId = mstEntityDesignJson.dropId;
    	let dropNodeLiIndex = dropId.split('-')[1];

    	//update the 'parentName' in the master entity design
    	if (dropNodeLiIndex === parentNodeSelDivIndex && mstEntityDesignJson.hasOwnProperty('parentName')){
    		if (mstEntityDesignJson.parentName === parentNodePrevSelVal){
    			//check the previous parent value with array parent name, if it is same, then element is valid and update the new value
    			mstEntityDesignArr[i].parentName = parentNodeSelVal;
    		}
    	}
    }

    //updating the master and temporary entity design element for embed when user changes the parent node
    /*for (let i=0;i<mstEntityDesignArr.length;i++){
    	console.log('Each: ',mstEntityDesignArr[i].dropId)
    	if (mstEntityDesignArr[i].dropId === curNodeId && mstEntityDesignArr[i].type === 'embed'){
    		mstEntityDesignArr[i].parentNode = parentNodeSelVal;
    		//break;
    	}
    }

    //updating the master and temporary entity design element for embed when user changes the parent node
    for (let j=0;j<tmpEntityDesignArr.length;j++){
    	if (tmpEntityDesignArr[j].dropId === curNodeId && tmpEntityDesignArr[j].type === 'embed'){
    		tmpEntityDesignArr[j].parentNode = parentNodeSelVal;
    		//break;
    	}
    }*/

    //console.log('On-Change - mstEntityDesignArr: ',mstEntityDesignArr);
    //console.log('On-Change - tmpEntityDesignArr: ',tmpEntityDesignArr);

    //construct the right target catalog
    initiateFnlTgtTree();
    
    //when changing the parent nodes, display modal window to add the join/filter condition - newly and clear the existing one
    let curParentNodeDivId = $('#'+parentNodeSelId).parents().eq(0).attr("id");
    let splitCurParentNodeDivId = curParentNodeDivId.split('-');  //to get the id number alone from 'node' div	        
    let curNodeDivId = 'end-' + splitCurParentNodeDivId[1] + '-' + splitCurParentNodeDivId[2]; //id of the node div
    let divIndex = splitCurParentNodeDivId[2];

	displayConditionModal(divIndex,curNodeDivId,curParentNodeDivId);
}

//to capture the 'embed as ref' value
function onEmbedAsRefClick(e){
	let isChecked = $('#'+e.id).is(":checked");
	console.log('isChecked: ',isChecked);
	
	let curEmbedRefChkId = e.id;
	let splitCurEmbedRefChkId = curEmbedRefChkId.split('-');
    let divIndex = splitCurEmbedRefChkId[2];
    let divMainId = splitCurEmbedRefChkId[1];
    
    //get the li id of the embed node for the sequential 'embed as ref' column to updtae the 'embedasref' value in array
    let curNodeDivId = `end-${divMainId}-${divIndex}`;
    let curNodeLiId = $('#'+curNodeDivId).find('ul li').attr('id');   
    console.log('curNodeLiId: ',curNodeLiId);
    //update the master entity design element for embed node when user changes the checked in/out the embed as ref
    for (let i=0;i<mstEntityDesignArr.length;i++){
    	let mstEntityDesignJson = mstEntityDesignArr[i];
    	let dropId = mstEntityDesignJson.dropId;
    	let dropNodeLiIndex = dropId.split('-')[1];

    	//update the 'parentName' in the master entity design
    	if (mstEntityDesignJson.dropId === curNodeLiId && mstEntityDesignJson.hasOwnProperty('parentName')){
    		mstEntityDesignJson['embedAsRef'] = isChecked;
    	}
    }

    //construct the right target catalog
    initiateFnlTgtTree();
}

/////////////Added for Data Switch - Node removal - Code starts here//////////
//Added for data switch - 'reference' removal
function removeAttribForReference(attribLiId){
	for (let i=0;i<mstEntityDesignArr.length;i++){
		let referenceAttribArr = mstEntityDesignArr[i].referenceAttribs;
			for (let j=0;j<referenceAttribArr.length;j++){
			if (referenceAttribArr[j].id === attribLiId){
				referenceAttribArr.splice(j, 1);
				break;
			}
		}
	}
	
	//construct the target catalog once removed
	initiateFnlTgtTree();
}

//Added for data switch - 'denormalize' removal
function removeAttribForDenormalize(attribLiId){
	for (let i=0;i<mstEntityDesignArr.length;i++){
		let denormalizeAttribArr = mstEntityDesignArr[i].denormalizeAttribs;
		for (let j=0;j<denormalizeAttribArr.length;j++){
			if (denormalizeAttribArr[j].id === attribLiId){
				denormalizeAttribArr.splice(j, 1);
				break;
			}
		}
	}
	
	//construct the target catalog once removed
	initiateFnlTgtTree();
}

//Added for data switch - 'embed - node' removal
function removeEmbedNode_old(entityLiId){
	for (let i=0;i<mstEntityDesignArr.length;i++){
		let nodeDropId = mstEntityDesignArr[i].dropId;
		//check the removal node id master entity design array
		if (nodeDropId === entityLiId){
			//get the removal node details
			let rmNodeName = mstEntityDesignArr[i].nodeName;
			let rmParentName = mstEntityDesignArr[i].parentName;
			let rmNodeReferenceAttribs = mstEntityDesignArr[i].referenceAttribs;
			let rmNodeDenormalizeAttribs = mstEntityDesignArr[i].denormalizeAttribs;

			//check reference and denormalize attributes exist for removal node
			if (rmNodeReferenceAttribs.length > 0 || rmNodeDenormalizeAttribs.length > 0){
				for (let j=0;j<mstEntityDesignArr.length;j++){
					let mainEntityDesignJson = mstEntityDesignArr[j];
					if (mainEntityDesignJson.nodeName === rmParentName){
						//loading removal node reference to previous master
						for (let k=0;k<rmNodeReferenceAttribs.length;k++){
							mainEntityDesignJson.referenceAttribs.push(rmNodeReferenceAttribs[k]);
						}
						
						//loading removal node denormalize to previous master
						for (let k=0;k<rmNodeDenormalizeAttribs.length;k++){
							mainEntityDesignJson.denormalizeAttribs.push(rmNodeDenormalizeAttribs[k]);
						} 
						
						break;
					}
				}
				//end of for loop - loading attributes to parent element
			}
			//end of if loop - reference and denormalize check

			//update the parent name for child nodes.
			for (let p=0;p<mstEntityDesignArr.length;p++){
				let derMstEntityDesignJson = mstEntityDesignArr[p];
				if (derMstEntityDesignJson.parentName === rmNodeName){
					derMstEntityDesignJson.parentName = rmParentName;					
				}
			}

			//remove the option in parent node selection group for all child nodes and update the parent name for child nodes
			$('#box-body').find('option').each(function() {
				//entering the loop
			    if ( $(this).val() === rmNodeName ) {
			        //set the parent name of deleted node to parent name of child nodes
			        if ($(this).prev().val() === rmParentName){
			        	$(this).prev().val(rmParentName).prop('selected', true);
			        }
			        //update the new parent name to previous value of selection div
			        let parentNodeSelId = $(this).parent().attr('id');
			    	$('#'+parentNodeSelId).data("prevsel",rmParentName);
			    	
			        //console.log("///////Start///////");
			        //console.log($(this).parent().attr('id'));
			        //console.log($(this).prev().val(rmParentName).prop('selected', true));
			        //console.log($(this).prev().val());
			        //console.log("///////End///////");
			        
			        //remove the node deleted option
			        $(this).remove();
			    }
			});

			//remove the item from 'selParentNodeArr' list
			for (let q=0;q<selParentNodeArr.length;q++){
				if (selParentNodeArr[q] === rmNodeName){
					selParentNodeArr.splice(q, 1);
					break;
				}
			}

			//remove the element from master entity design array(embed node) 
			mstEntityDesignArr.splice(i, 1);
			break;
		}
	}
	
	//construct the target catalog once removed
	initiateFnlTgtTree();
}

//Added for data switch - 'embed - node' removal
function removeEmbedNode(entityLiId){
	for (let i=0;i<mstEntityDesignArr.length;i++){
		let nodeDropId = mstEntityDesignArr[i].dropId;
		//check the removal node id master entity design array
		if (nodeDropId === entityLiId){
			//get the removal node details
			let rmNodeName = mstEntityDesignArr[i].nodeName;
			let rmParentName = mstEntityDesignArr[i].parentName;
			let rmNodeReferenceAttribs = mstEntityDesignArr[i].referenceAttribs;
			let rmNodeDenormalizeAttribs = mstEntityDesignArr[i].denormalizeAttribs;

			//check reference and denormalize attributes exist for removal node
			if (rmNodeReferenceAttribs.length > 0 || rmNodeDenormalizeAttribs.length > 0){
				for (let j=0;j<mstEntityDesignArr.length;j++){
					let mainEntityDesignJson = mstEntityDesignArr[j];
					if (mainEntityDesignJson.nodeName === rmParentName){
						//loading removal node reference to previous master
						for (let k=0;k<rmNodeReferenceAttribs.length;k++){
							mainEntityDesignJson.referenceAttribs.push(rmNodeReferenceAttribs[k]);
						}
						
						//loading removal node denormalize to previous master
						for (let k=0;k<rmNodeDenormalizeAttribs.length;k++){
							mainEntityDesignJson.denormalizeAttribs.push(rmNodeDenormalizeAttribs[k]);
						} 
						
						break;
					}
				}
				//end of for loop - loading attributes to parent element
			}
			//end of if loop - reference and denormalize check

			//update the parent name for child nodes.
			for (let p=0;p<mstEntityDesignArr.length;p++){
				let derMstEntityDesignJson = mstEntityDesignArr[p];
				if (derMstEntityDesignJson.parentName === rmNodeName){
					//update the parent for the node as previous parent node is deleted
					derMstEntityDesignJson.parentName = rmParentName;
					//re-initialize the joiner and filter as parent for the node is changed
					derMstEntityDesignJson.join = {};
					derMstEntityDesignJson.filter = {};
				}
			}

			//remove the option in parent node selection group for all child nodes and update the parent name for child nodes
			$('#box-body').find('option').each(function() {
				//entering the loop
			    if ( $(this).val() === rmNodeName ) {
			        //set the parent name of deleted node to parent name of child nodes
			        if ($(this).prev().val() === rmParentName){
			        	$(this).prev().val(rmParentName).prop('selected', true);
			        }
			        //update the new parent name to previous value of selection div
			        let parentNodeSelId = $(this).parent().attr('id');
			    	$('#'+parentNodeSelId).data("prevsel",rmParentName);
			    	
			        //console.log("///////Start///////");
			        //console.log($(this).parent().attr('id'));
			        //console.log($(this).prev().val(rmParentName).prop('selected', true));
			        //console.log($(this).prev().val());
			        //console.log("///////End///////");
			        
			        //remove the node deleted option
			        $(this).remove();
			    }
			});

			//remove the item from 'selParentNodeArr' list
			for (let q=0;q<selParentNodeArr.length;q++){
				if (selParentNodeArr[q] === rmNodeName){
					selParentNodeArr.splice(q, 1);
					break;
				}
			}

			//remove the element from master entity design array(embed node) 
			mstEntityDesignArr.splice(i, 1);
			break;
		}
	}
	
	//construct the designer area when user delete the embed node
	constructDesignerArea(mstEntityDesignArr)
	
	//construct the target catalog once removed
	initiateFnlTgtTree();
}

//Added for data switch - 'joiner' condition removal
function removeJoinerCondition(joinerLiId){
	for (let i=0;i<mstEntityDesignArr.length;i++){
		//check the removal join condition in embed node only
		if (mstEntityDesignArr[i].hasOwnProperty('parentName')){
			let joinerJson = mstEntityDesignArr[i].join;
			console.log('joinerJson: ',joinerJson)
			if (joinerJson.hasOwnProperty("dropId")){
				if (joinerJson.dropId === joinerLiId){
					//remove the join in master entity design array
					mstEntityDesignArr[i]['join'] = {};
					break;
				}
			}
		}		
	}
	
	//construct the target catalog once removed
	initiateFnlTgtTree();
}

//Added for data switch - 'filter' condition removal
function removeFilterCondition(filterLiId){
	for (let i=0;i<mstEntityDesignArr.length;i++){
		//check the removal filter condition in embed node only
		if (mstEntityDesignArr[i].hasOwnProperty('parentName')){
			let filterJson = mstEntityDesignArr[i].filter;
			if (filterJson.hasOwnProperty("dropId")){
				if (filterJson.dropId === filterLiId){
					//remove the filter in master entity design array
					mstEntityDesignArr[i]['filter'] = {};
					break;
				}
			}
		}
	}
	
	//construct the target catalog once removed
	initiateFnlTgtTree();
}

//to remove the target collection in right side when user click the 'X'
function removeTargetCollection_class(tgtCollectionSpanId, tgtCollectionName){
	console.log("/////////tgtNodeRemove////////",tgtCollectionSpanId);
	console.log("/////////tgtNodeRemove////////",tgtCollectionName)
	//$('#'+tgtCollectionSpanId).parents().eq(0).remove();
	
	console.log("Before: ",gblEntityDesignJson);
	delete gblEntityDesignJson[tgtCollectionName];
	console.log("After: ",gblEntityDesignJson);
	
	//empty all row in the designer area
	$('#box-body').empty();
}

//to remove the target collection in right side when user click the 'X'
function removeTargetCollection(e, tgtCollectionSpanId, tgtCollectionName){
	$('#'+tgtCollectionSpanId).parents().eq(0).remove();

	delete gblEntityDesignJson[tgtCollectionName];

	//check whether the deleting collection in the target is same as current collection in designer area, then clear the designer area too
	//if deleting collection in the target is not same as current collection in designer area, then don't clear the designer area as it is another collection details
	let isTargetDesigner = false;
	for (let i=0;i<mstEntityDesignArr.length;i++){
		if (!mstEntityDesignArr[i].hasOwnProperty('parentName')){
			if (mstEntityDesignArr[i].nodeName === tgtCollectionName){				
				isTargetDesigner = true;				
				break;
			}
		}
	}
	
	if (isTargetDesigner){
		//empty all row in the designer area
		$('#box-body').empty();
	}else{
		//don't clear it
	}
}
/////////////Added for Data Switch - Node removal - Code ends here//////////

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

//to add new collection
function clearAllRows(){
	//empty all row in the designer area
	$('#box-body').empty();
	
	//re-initialize the global values
	mstEntityDesignArr = [];
	selParentNodeArr = [];
	wrkTgtCollection = '';
	
	//remove the collection from target catalog
	/*for (let i=0;i<mstEntityDesignArr.length;i++){
		let entityDesignJson = mstEntityDesignArr[i];
		
		if (!entityDesignJson.hasOwnProperty('parentName')){
			let tgtCollectionId = entityDesignJson.dropId;
			if($('#'+'tgt-'+tgtCollectionId).length){				
				$('#'+'tgt-'+tgtCollectionId).remove();
				break;
			}	
		}
	}
	
	//re-initialize the global values	
	delete gblEntityDesignJson[wrkTgtCollection];
	mstEntityDesignArr = [];
	selParentNodeArr = [];
	wrkTgtCollection = '';*/
}

//to get the values when user double click the target catalog collection
//construct and display the values in center screen
function onSelectTgtCollection(e){
	//console.log("Collection Id: ",e.id);
	//list id
	let tgtCollectionId = e.id;
	splitTgtCollectionId = tgtCollectionId.split('-');

	//list - span id
	let tgtCollectionSpanId = 'tgt-span-' + splitTgtCollectionId[1] + '-' + splitTgtCollectionId[2];
	
	//get the collection name
	let collectionName = $('#'+tgtCollectionSpanId).data("nodename");
	//console.log("Collection Name: ",collectionName)

	//fetch the collection details from global variable for the selected collection
	let spfCollectionJson = gblEntityDesignJson[collectionName];
	//need to add logic to get the right collection using dropId instead of collection name
	
	//get the root id using tgtCollectionId
	let curCollectionLiId = splitTgtCollectionId[1] + '-' + splitTgtCollectionId[2];
	console.log('////////Target Collection Selection////////');
	//console.log('curCollectionLiId: ',curCollectionLiId);
	//console.log('gblEntityDesignJson: ',gblEntityDesignJson)

	//fetch the right collection details
	Object.keys(gblEntityDesignJson).forEach(function(key) {
		let collectionNm = key;

		let collectionVal = gblEntityDesignJson[key];

		if (collectionVal['dropId'] === curCollectionLiId){
			spfCollectionJson = gblEntityDesignJson[key];
		}
	});
	
	//console.log('spfCollectionJson: ',spfCollectionJson)

	//re-set the collection name to wrkTgtCollection(global variable)
	wrkTgtCollection = collectionName;

	//construct the mstEntityDesignArr using collecion details as it is global variable
	//re-initilaize
	mstEntityDesignArr = [];
	let rootJson = {};
	rootJson['dropId'] = spfCollectionJson.dropId;
	rootJson['nodeName'] = spfCollectionJson.nodeName;
	rootJson['nodeSrcRefName'] = spfCollectionJson.nodeSrcRefName;
	rootJson['referenceAttribs'] = spfCollectionJson.referenceAttribs;
	rootJson['denormalizeAttribs'] = spfCollectionJson.denormalizeAttribs;
	mstEntityDesignArr.push(rootJson);
	for (let i=0;i<spfCollectionJson.nodes.length; i++){
		mstEntityDesignArr.push(spfCollectionJson.nodes[i])
	}

	//construct 'selParentNodeArr' group array with new collection values
	//re-initilaize
	selParentNodeArr = [];	
	for (let j=0;j<mstEntityDesignArr.length;j++){
		selParentNodeArr.push(mstEntityDesignArr[j].nodeName);
	}

	//construct the designer area
	constructDesignerArea(mstEntityDesignArr);
}

//construct the designer area
function constructDesignerArea(mstEntityDesignArr){
	let boxDiv = '';
	for (let x=0;x<mstEntityDesignArr.length;x++){
		let designTime = getDesignerTime();
		let mstEntityDesignJson = mstEntityDesignArr[x];
		console.log("Inside ConstructDesignerArea: ",mstEntityDesignJson);

		let dropLiId = mstEntityDesignJson.dropId;
		let dropIndex = dropLiId.split('-')[1];
		//console.log('dropIndex: ',dropIndex);
		
		let nodeName = mstEntityDesignJson.nodeName;
		let nodeSrcRefName = mstEntityDesignJson.nodeSrcRefName;
		let referenceAttribs = mstEntityDesignJson.referenceAttribs;
		let denormalizeAttribs = mstEntityDesignJson.denormalizeAttribs;
		
		let derCatalogName = nodeSrcRefName.split(' (')[1];
		derCatalogName = derCatalogName.substring(0,derCatalogName.length -1);
		
		let entityDiv = '';
		if (!mstEntityDesignJson.hasOwnProperty('parentName')){
			//that is collection node row
			entityDiv = 
				`<div class="col borderCol targetdiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable root-size" id="rnd-${designTime}-${dropIndex}">
					<ul class="Tree" id="targetDrop-${dropIndex}"><li id="${dropLiId}" title="${nodeSrcRefName}" data-entityname="${nodeName}" data-attribname="" data-catalogname="${derCatalogName}" data-type="Entity">
							<span class="ui-draggable ui-draggable-handle">
								<i class="pointer fas fa-times text-danger"></i>
							</span>
							<span class="pl-2 ui-draggable ui-draggable-handle ed-span" id="span-${dropLiId}" contenteditable="true">
								${nodeName}
							</span>
						</li>
					</ul>
				</div>
				
				
				<div class="col borderCol nodediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable embed-size" id="end-${designTime}-${dropIndex}">
					<ul class="Tree w-100" id="nodeDrop-${dropIndex}-${dropIndex}">
						
					</ul>
				</div>
				<div class="col borderCol parentnodediv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="epnd-${designTime}-${dropIndex}">													
				</div>
				<div class="col borderCol conditiondiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="cond-${designTime}-${dropIndex}">
				</div>
				<div class="col borderCol embedrefdiv paddingBoxBdyCol borderRgtBoxBdyCol embedRef-size" id="eref-${designTime}-${dropIndex}">											
				</div>
				`;
			
		}else{
			//that is embed node row
			let parentName = mstEntityDesignJson.parentName;
			
			//get all elements before the current element to construct the parent node selection list
			let parentNodeArr = mstEntityDesignArr.slice(0,x);
			//console.log('Parent Node Array: ',parentNodeArr);
			
			//get existing added node for selection group - options
			let optParentNode = [];
			for (let i=0;i<parentNodeArr.length;i++){
				//derParentNodeName.push(parentNodeArr[i].nodeName);
				let nodeName = parentNodeArr[i].nodeName;
				if (nodeName === parentName){
					optParentNode += `<option value="${nodeName}" selected="true">${nodeName}</option>`;
				}else{
					optParentNode += `<option value="${nodeName}">${nodeName}</option>`;
				}
			}
			
			//construct the condition div element
			let joinerJson = mstEntityDesignJson.join;
			let filterJson = mstEntityDesignJson.filter;
			let joinerLiId = '';
			let joinerCondition = '';
			let joinerType = '';
			let joinerSortedInput = '';
			let modJoinerSortedInput = '';
			let filterLiId = '';
			let filterCondition = '';
					
			let isJoinerExist = false;
			if (joinerJson.hasOwnProperty('dropId')){
				joinerLiId = joinerJson['dropId'];
				joinerCondition = joinerJson['condition'];
				joinerType = joinerJson['type'];
				joinerSortedInput = joinerJson['iSorted'];				
				if (joinerSortedInput){
					modJoinerSortedInput = "Yes"
				}else{
					modJoinerSortedInput = "No"
				}
				isJoinerExist = true;
			}
			
			let isFilterExist = false;
			if (filterJson.hasOwnProperty('dropId')){
				filterLiId = filterJson['dropId'];
				filterCondition = filterJson['condition'];
				isFilterExist = true;
			}
			
			let condDivId = `cond-${designTime}-${dropIndex}`;
			
			//List Method
			let divJoinerAppend = '';
			if (joinerCondition !== ''){
				divJoinerAppend = 
				    `<li id="${joinerLiId}" title="Joiner: ${joinerCondition}" data-nodename="${nodeName}" data-parentname="${parentName}" data-jointype="${joinerType}" data-isorted="${modJoinerSortedInput}" data-type="Condition" data-conditiontype="Joiner" style="display:inline;">
						<span id="span-${joinerLiId}">
							<img src="${joinerImg}" alt="Joiner Condition" style="width: 20px;" />
						</span>
						<span class="pointer">
							<i class="fas fa-times text-danger joinerRemove"></i>
						</span>
					</li>`;
			}
			
			let divFilterAppend = '';
			if (filterCondition !== ''){
				filterCondition = filterCondition.replace(/\"/gi, "'");
				divFilterAppend = 
				    `<li id="${filterLiId}" title="Filter: ${filterCondition}" data-nodename="${nodeName}" data-parentname="${parentName}" data-type="Condition" data-conditiontype="Filter" style="display:inline;">
						<span id="span-${filterLiId}">
							<img src="${filterImg}" alt="Filter Condition" style="width: 20px; height: 20px;" />
						</span>
						<span class="pointer">
							<i class="fas fa-times text-danger filterRemove"></i>
						</span>
					</li>`;
			}

			//construct the table details for condition div element
			/*let condTblDetail = '';
			if (joinerCondition !== ''){
				condTblDetail += `<td style="padding-right: 10px;">
					<div id="${joinerLiId}" title="Joiner: ${joinerCondition}" data-nodename="${nodeName}" data-parentname="${parentName}" data-jointype="${joinerType}" data-isorted="${modJoinerSortedInput}" data-type="Condition" data-conditiontype="Joiner">
						<span id="span-${joinerLiId}">
							<img src="${joinerImg}" alt="Joiner Condition" style="width: 20px;" />
						</span>
						<span class="pointer">
							<i class="fas fa-times text-danger joinerRemove"></i>
						</span>
					</div>
				</td>`;
			}

			if (filterCondition !== ''){
				condTblDetail += `<td>
					<div id="${filterLiId}" title="Filter: ${filterCondition}" data-nodename="${nodeName}" data-parentname="${parentName}" data-type="Condition" data-conditiontype="Filter">
						<span id="span-${filterLiId}">
							<img src="${filterImg}" alt="Filter Condition" style="width: 20px; height: 20px;" />
						</span>
						<span class="pointer">
							<i class="fas fa-times text-danger filterRemove"></i>
						</span>
					</div>
				</td>`;
			}
			
			//construct the table
			let conditionTbl = `<table>
				<tbody>
					<tr>
						${condTblDetail}
					</tr>
				</tbody>
			</table>`;*/
			
			//construct the 'embed as ref' div element
			//construct the condition div element
			let embedAsRef = mstEntityDesignJson.embedAsRef;
			let inputEmbedAsRef = '';
			if (embedAsRef){
				inputEmbedAsRef = `<input type="checkbox" class="custom-control-input" id="embedref-${designTime}-${dropIndex}" style="margin-left: -22px;" onclick="onEmbedAsRefClick(this)" checked>`;
			}else{
				inputEmbedAsRef = `<input type="checkbox" class="custom-control-input" id="embedref-${designTime}-${dropIndex}" style="margin-left: -22px;" onclick="onEmbedAsRefClick(this)">`;
			}

	    	let divEmbedAsRef = `
		    	<div class="custom-control custom-checkbox" style="margin-left: 30px; top: 4px;">
				  ${inputEmbedAsRef}
				  <label class="custom-control-label" for="embedref-${designTime}-${dropIndex}"></label>
				</div>`;
	    	
			entityDiv = 
				`<div class="col borderCol targetdiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable root-size" id="targetAttributes-${dropIndex}">
					<ul class="Tree" id="targetDrop-${dropIndex}">
						<li id="targetAttrLi-${dropIndex}" title="">
							<span class="ui-draggable ui-draggable-handle">
								<i class="pointer fas fa-times text-danger" onclick="removeTargetAttributes(event)"></i>
							</span>
							<span class="pl-2 ui-draggable ui-draggable-handle ed-span" contenteditable="true">
								
							</span>
						</li>
					</ul>
				</div>
				
				<div class="col borderCol nodediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable embed-size" id="end-${designTime}-${dropIndex}">
					<ul class="Tree w-100" id="nodeDrop-${dropIndex}-${dropIndex}">
						
						<li id="${dropLiId}" title="${nodeSrcRefName}" data-entityname="${nodeName}" data-attribname="" data-catalogname="${derCatalogName}" data-type="Entity">
							<span id="span-${dropLiId}" class="ed-span" contenteditable="true">
								${nodeName}
							</span>
							<span class="pointer">
								<i class="fas fa-times text-danger nodeRemove"></i>
							</span>
						</li>
					</ul>
				</div>
			
				<div class="col borderCol parentnodediv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="epnd-${designTime}-${dropIndex}">    	        
		        	<select class="form-control form-control-sm" data-prevsel="${parentName}" id="parentNodeSelGrp-${designTime}-${dropIndex}" name="parentNodeSelGrp" onchange="changeParentNodeSelGrp(this)">								
						${optParentNode}
					</select>
				</div>
				
				
				<div class="col borderCol conditiondiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="cond-${designTime}-${dropIndex}">
					<span class="ed-cond-span" id="conditionSpanDrop-${dropIndex}-${dropIndex}" style="float: right;">
						<i class="fas fa-edit text-info edit-condition-modal" title="Edit Condition" ></i>
					</span>

					<ul class="Tree w-100" id="conditionDrop-${dropIndex}-${dropIndex}" style="margin-top: 4px;">
						${divJoinerAppend}
						${divFilterAppend}
					</ul>
				</div>
				
				<div class="col borderCol embedrefdiv paddingBoxBdyCol borderRgtBoxBdyCol embedRef-size" id="eref-${designTime}-${dropIndex}">
					${divEmbedAsRef}				
				</div>
				`;
	
		}
		
		//for reference attributes
		//removed on 14/08/2020 - added checkbox
		/*let referenceDiv = '';
		if (referenceAttribs.length > 0){
			let refOption = '';
			for (let p=0;p<referenceAttribs.length;p++){
				let refId = referenceAttribs[p].id;
				let name = referenceAttribs[p].name;
				let srcRef = referenceAttribs[p].srcRef;
				let derEntityName = srcRef.split(' (')[1];
				derEntityName = derEntityName.substring(0,derEntityName.length -1);
		
				refOption += 
					`<li id="${refId}" title="${srcRef}" data-entityname="${derEntityName}" data-attribname="${name}" data-catalogname="${derCatalogName}" data-type="Attribute">
						<span id="span-${refId}" contenteditable="true">
							${name}
						</span>
						<span class="pointer">
							<i class="fas fa-times text-danger referenceRemove"></i>
						</span>
					</li>`;
			}
			//end of for loop

			referenceDiv = 
				`<div class="col borderCol referencediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable" id="ref-${designTime}-${dropIndex}" style="min-width: 120px; max-width: 120px;">
					<ul class="Tree w-100" id="referenceDrop-${dropIndex}-${dropIndex}">
						${refOption}
					</ul>
				 </div>`;
		}else{
			referenceDiv = 
				`<div class="col borderCol referencediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable" id="ref-${designTime}-${dropIndex}" style="min-width: 120px; max-width: 120px;">
					<ul class="Tree w-100" id="referenceDrop-${dropIndex}-${dropIndex}">
						
					</ul>
				</div>`;
		}*/

		//for denormalize attributes
		let denormalizeDiv = '';
		if (denormalizeAttribs.length > 0){
			let denormOption = '';
			for (let q=0;q<denormalizeAttribs.length;q++){
				let refId = denormalizeAttribs[q].id;
				let name = denormalizeAttribs[q].name;
				let srcRef = denormalizeAttribs[q].srcRef;
				let derEntityName = srcRef.split(' (')[1];
				derEntityName = derEntityName.substring(0,derEntityName.length -1);
			
				denormOption += 
					`<li id="${refId}" title="${srcRef}" data-entityname="${derEntityName}" data-attribname="${name}" data-catalogname="${derCatalogName}" data-type="Attribute">
						<span class="ed-span" id="span-${refId}" contenteditable="true">
							${name}
						</span>
						<span class="pointer">
							<i class="fas fa-times text-danger denormalizeRemove"></i>
						</span>
					</li>`;
			}
			//end of for loop

			denormalizeDiv = 
				`<div class="col borderCol denormalizediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable denormalize-size" id="denorm-${designTime}-${dropIndex}">
					<ul class="Tree w-100" id="referenceDrop-${dropIndex}-${dropIndex}">
						${denormOption}
					</ul>
				 </div>`;

		}else{
			denormalizeDiv = 
				`<div class="col borderCol denormalizediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable denormalize-size" id="denorm-${designTime}-${dropIndex}">
					<ul class="Tree w-100" id="denormalizeDrop-${dropIndex}-${dropIndex}">
						
					</ul>
				 </div>`;
		}
		
		//for subsetting attributes
		let subsetDiv =  
			`<div class="col borderCol sourceId paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable subset-size" id="sub-${designTime}-${dropIndex}">
				<ul class="Tree w-100" id="sourceDrop-${dropIndex}-${dropIndex}">
					
				</ul>
			 </div>`;
		
		//<!-- ${referenceDiv} -->
		boxDiv += `<div class="box">
			<div class="row box-bdy-row" id="bx-${designTime}-${dropIndex}">
				${entityDiv}
				${denormalizeDiv}
				${subsetDiv}				
			</div>
		</div>`;
		
		/*if (x===0){
			$('#box-body').empty();
		}

		$('#box-body').append(boxDiv);
		allowDrop();*/
	}
	//end of 1st for loop - 'mstEntityDesignArr' length
	
	//empty the box body and load the selected collection content
	$('#box-body').empty();
	$('#box-body').append(boxDiv);
	
	document.querySelector('.edit-condition-modal').addEventListener('click', function(){
		onEditConditionClick(this, condDivId);
	});
	
	//the event is triggered when user changes the node name in the designer area
	//when editing the node name to focus the span and capture the result 
	onFocusEditNodeName();
	
	allowDrop();
}

//to save the entity design in the nosql db
async function saveSchemaEntityDesign(type){
	//$('#tgtSchemaDesign').val(JSON.stringify(gblEntityDesignJson,0,2));
	//$('#frmTgtSchemaDesign').submit();
	
	/*const url = `${ctx}/schema-designer/${projectName}/saveSchemaDesign`;
	$.ajax({
		//url:ctx+'/schema-designer/getEntityDesignDetails/'+projectName+'/'+entityName,
		url: url,
		type: 'post',
		data : {"objSchemaDesignJson" : JSON.stringify(gblEntityDesignJson,0,2)},
		success: function(data){
			//console.log('Loaded Schema Design File:',data);			
		}
	});*/
	
	let result = '';
	try{
		result = await saveSchemaDesign();
		//console.log(result);
		$('#mappingArray').empty().append(result);
		$('.loaderEnh').addClass('none');
		Swal.fire({
		  type: 'success',
		  title: 'Successfull',
		  timer: 2000,
		  text: 'Schema design have been saved successfully',
		});
		
		if (type === 'next'){
			proceedSchemaAttributeDesign();
		}
	}catch(e){
 	    console.log(e);
   		$('.loaderEnh').addClass('none');
   		Swal.fire({
		  type: 'error',
		  timer: 2000,
		  title: 'Failed',
		  text: 'Unable to save schema design details',
   		})
	}
}

//to save the schema design
async function saveSchemaDesign(){
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	const url = `${ctx}/schema-designer/${projectName}/saveSchemaDesign`;
	return await 
    $.ajax({
      type: "POST",
      url: url,
	  headers: {'X-CSRF-Token': csrf_token },
      data : {"objSchemaDesignJson" : JSON.stringify(gblEntityDesignJson,0,2)}
    })
};

//to proceed the schema attribute design page
function proceedSchemaAttributeDesign(){	
	//need to save the design and launch the next page
	let form = document.createElement('form');
	console.log('Selected Project: ',projectName);
	//form.action = ctx + '/schema-designer/'+projectName;
	//Added Temporarily
    //form.action = ctx+'/schema-designer/'+'test-mapping-grp'+'/demo-1/'+projectName+"/attributeDesign";
    form.action = ctx+'/schema-designer/'+projectName+"/attributeDesign";
	form.method = 'GET';
	document.body.append(form);
	form.submit();
}

//////////////////Data Switch - Code Ends Final//////////////



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
	let toggleValueRule='';
	if($('#toggleBtnForPhyLog').hasClass('fa-toggle-off')){
		toggleValueRule = "Physical";
	}else{
		toggleValueRule = "Logical";
	}
	let targetCol = $('#targetAttributes-'+id).find('ul li').attr('data-entityphyname');
	
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
		$('.sourceDiv').each(function(){
			$(this).find('ul:nth-child(2) li').each(function(index,li){
				let column = $(li).find('span').attr('title').trim();
				if(column!='')
					optionTag += `<option value="${column}">${column}</option>`; 
			});
		});
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

//Added for Data Switch - 'Node' cell in table to remove it
function removeNodeAttributes(event) {
	$(event.target).parents().eq(1).remove();
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
		let div = getRuleConsole(index,ruleNo,type,event,source,option,logTargetEntity);
		
		
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
	    console.log(formattedJson);
	    let updatedCatalogData = JSON.stringify(catalogArray);
	    //updatedCatalogData = updatedCatalogData.substring(updatedCatalogData.indexOf(':') + 1, updatedCatalogData.length - 1);
	    //console.log("@#@##@ "+updatedCatalogData);
	    $('#map').val(formattedJson);
	    $('#type').val(type);
	    let existingJson = document.querySelector('#mappingArray').textContent;
	    let chunkArray = _.chunk(JSON.parse(formattedJson), 50);
	    if(_.isEqual(JSON.parse(existingJson), JSON.parse(formattedJson)) && !changeCheck){
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
	        if(formattedJson!=''){
	        	//console.log('Submitting form');
	    		
	    		if(validateAttributeMapDetails(type).length > 0 && type != 'save'){
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
					        	$('.loaderEnh').addClass('none');
					        	Swal.close();
					        }
							
						});
	    		}else{
	    			runSync(chunkArray,type,updatedCatalogData);
	    		}
	        }else{
	        	$('.loaderEnh').addClass('none');
	        	Swal.fire({
					  type: 'error',
					  title: 'Failed',
					  timer: 2000,
					  text: 'Unable to save mapping details',
					})
	        }
	    }
}

async function runSync(chunkArray,type,updatedCatalogData) {
	$('.loaderEnh').removeClass('none');
	let result = '';
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
  				  text: 'Mapping details have been saved successfully',
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
				  text: 'Unable to save mapping details',
				})
       }
       $('.loaderEnh').addClass('none');
    }
}

async function saveEachChunk(chunk,i,type,updatedCatalogData){
	//console.log("updatedCatalogData-----"+updatedCatalogData);
	const selectedSystem = $('#selectedSystem').val();
	const selectedMapping = $('#selectedMapping').val();
	const url = `${ctx}/RDW/Intelligent-Mapper/system-mapping/${selectedSystem}/data-mapping/${selectedMapping}/predictive-mapping/attribute-mapping/save`;
	//console.log("chunk ID -- "+i);
	//console.log(chunk);
	return await 
    $.ajax({
      type:"POST",
      url: url,
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

//function to get the current time
function getDesignerTime(){
	let d = new Date();
	let n = d.getTime();
	return n;
}

//When user add the empty row
function addNewEmptyRow(){
	let id = $('#box-body').find('.box').length;
	
	//construct selection group
	/*let optParentNode = '';
	for (let m=0; m<selParentNodeArr.length; m++){
		optParentNode += `<option value="${selParentNodeArr[m]}">${selParentNodeArr[m]}</option>`;
	}*/

	let designTime = getDesignerTime();
	  
	let divVal=`<div class="box">
			<div class="row box-bdy-row" id="bx-${getDesignerTime()}-${id}">
				<div class="col borderCol targetdiv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable root-size" id="rnd-${designTime}-${id}">
					<ul class="Tree" id="targetDrop-${id}">
						<li id="targetAttrLi-${id}" title="">
							<span class="ui-draggable ui-draggable-handle">
								<i class="pointer fas fa-times text-danger remove-row"></i>
							</span>
							<span class="pl-2 ui-draggable ui-draggable-handle">
								
							</span>
						</li>
					</ul>
				</div>
				
				<!-- <div class="col-md-auto text-center borderCol p-1" style="display:flex;justify-content:center">
					<span class="fa-layers fa-lg pointer" style=align-self:center; onclick="openUserDefinedVariableModel('${id}','','new',event)">
						<i class="far fa-plus-square text-info"></i>
					</span>
				</div>
				<div class="col-4 p-0 borderCol" id="ruleApplied${id}">								
				</div> -->
				
				<div class="col borderCol nodediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable embed-size" id="end-${designTime}-${id}">
					<ul class="Tree w-100" id="nodeDrop-${id}-${id}">						
					</ul>
				</div>
				<div class="col borderCol parentnodediv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="epnd-${designTime}-${id}">
					<!-- <ul class="Tree w-100" id="parentNodeDrop-${id}-${id}">						
					</ul> -->															
				</div>
				<div class="col borderCol conditiondiv paddingBoxBdyCol borderRgtBoxBdyCol embed-size" id="cond-${designTime}-${id}">
					<span class="ed-cond-span" id="conditionSpanDrop-${id}-${id}" style="float: right;">
					</span>
					<ul class="Tree w-100" id="conditionDrop-${id}-${id}" style="margin-top: 4px;">						
					</ul>								
				</div>
				
				<div class="col borderCol embedrefdiv paddingBoxBdyCol borderRgtBoxBdyCol embedRef-size" id="eref-${designTime}-${id}">
				</div>
				
				
				<!-- <div class="col borderCol referencediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable" id="ref-${designTime}-${id}" style='min-width: 140px; max-width: 140px;'>
					<ul class="Tree w-100" id="referenceDrop-${id}-${id}">						
					</ul>
				</div> -->
				<div class="col borderCol denormalizediv paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable denormalize-size" id="denorm-${designTime}-${id}">
					<ul class="Tree w-100" id="denormalizeDrop-${id}-${id}">
						
					</ul>
				</div>							
				
				<div class="col borderCol sourceId paddingBoxBdyCol borderRgtBoxBdyCol ui-droppable subset-size" id="sub-${designTime}-${id}">
					<ul class="Tree w-100" id="sourceDrop-${id}-${id}">
						
					</ul>
				</div>
			</div>
		</div>`;
	
	
	$('#box-body').append(divVal);
	document.querySelector('.remove-row').addEventListener('click', function(e){
		removeTargetAttributes(e);
	});
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
    	option = option + 
    	`<option value="${Rules.split(',')[j]}">${Rules.split(',')[j]}</option>`;
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

/////////////////////Rule Transformation - Moved from 'rule_transformation.js' to 'schema-design.js' file////////////////////

window.getRuleSyntaxFromRule = getRuleSyntaxFromRule;
window.getRuleDetailsForEdit = getRuleDetailsForEdit;
window.clickRule = clickRule;
window.getRuleConsole = getRuleConsole;
window.getRuleDetailsOnApply = getRuleDetailsOnApply;
window.getRuleDescForOldRules = getRuleDescForOldRules;
window.showList = showList;
window.allowClick = allowClick;
window.closeConsole = closeConsole;

function getRuleSyntaxFromRule(selectedRule,sourceOpts, _selectedSource, _tool, _type){
	let ruleId = selectedRule.replace(/ /g, "-").replace('/','-');
	const _ruleMappings = document.querySelector('#ruleMappings').textContent;
	const _ruleMapObj = JSON.parse(_ruleMappings);
	console.log(_ruleMapObj);
	
	let _paramCount = '', _divVal = '', _customWidth = '', _ruleType = '', _isMandatory = '', type='', radioDiv='';
	for(let i = 0, len = _ruleMapObj.length; i < len; i++)
	{
		//alert(i);
		let _ruleObj = _ruleMapObj[i];
		let _ruleName = _ruleObj.transformationRule.ruleName; // String - Trim Leading Spaces
		_ruleType = _ruleName.substring(0,_ruleName.indexOf('-')).trim();
		_ruleName = _ruleName.substring(_ruleName.indexOf('-')+1, _ruleName.length).trim(); // Trim Leading Spaces
		_ruleName == 'Expression' ?  _customWidth = '100%!important' : _customWidth = '120px!important';
		if(selectedRule === _ruleName && _ruleType.includes(_type))
		{
			if(_ruleObj.transformationRule.parameter == null)
				_paramCount = 0;
			else
				_paramCount = _ruleObj.transformationRule.parameter.length;
			
			_divVal = _ruleObj.expression.replace(/\(/g,'<br>(<br>').replace(/\)/g,'<br>)').replace(/,/g,'').replace(/'/g, '');
			
			if(selectedRule == 'Concatenation'){
				
				_divVal = _divVal.replace('#var1#', `<div class="inp-param param0"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}0"></i><select id="select0" class="form-control form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important">${sourceOpts}</select><input id="input0" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />
														<div class="custom-control custom-radio custom-control-inline ${ruleId}0 none" title="String Constant">
														  <input type="radio" class="custom-control-input" id="SC0" name="typeValueRadio0" value="SC" checked>
														  <label class="custom-control-label" for="SC0">SC</label>
														</div>
														
														<div class="custom-control custom-radio custom-control-inline ${ruleId}0 none" title="Numeric Constant">
														  <input type="radio" class="custom-control-input" id="NC0" name="typeValueRadio0" value="NC">
														  <label class="custom-control-label" for="NC0">NC</label>
														</div>
														
														<div class="custom-control custom-radio custom-control-inline ${ruleId}0 none" title="Expression">
														  <input type="radio" class="custom-control-input" id="EXP0" name="typeValueRadio0" value="EXP">
														  <label class="custom-control-label" for="EXP0">EXP</label>
														</div>
													</div>`);
				_divVal = _divVal.replace('#var2#', `<div class="inp-param param1"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}1"></i><select id="select1" class="form-control form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important">${sourceOpts}</select><input id="input1" placeholder="Enter Value" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />
														<div class="custom-control custom-radio custom-control-inline ${ruleId}1 none" title="String Constant">
														  <input type="radio" class="custom-control-input" id="SC1" name="typeValueRadio1" value="SC" checked>
														  <label class="custom-control-label" for="SC1">SC</label>
														</div>
														
														<div class="custom-control custom-radio custom-control-inline ${ruleId}1 none" title="Numeric Constant">
														  <input type="radio" class="custom-control-input" id="NC1" name="typeValueRadio1" value="NC">
														  <label class="custom-control-label" for="NC1">NC</label>
														</div>
														
														<div class="custom-control custom-radio custom-control-inline ${ruleId}1 none" title="Expression">
														  <input type="radio" class="custom-control-input" id="EXP1" name="typeValueRadio1" value="EXP">
														  <label class="custom-control-label" for="EXP1">EXP</label>
														</div>
													<i class="pl-1 pointer fa fa-plus-circle text-info add1" title="Add another parameter"></i></div>`);
			
			}else if(selectedRule == 'GroupBy'){
				_divVal = `Group by <select id="addedGroupByColumns" required="" multiple="" class="ml-3 mt-2 form-control select-t form-control-sm custom-width ml-1 mr-1" style="width:70%!important">${sourceOpts}</select><small id="emailHelp" class="form-text text-muted">Use ctrl key for multiselect.</small></div>`;
			
			}else if(selectedRule == 'Difference between Dates'){
				
				_divVal = _divVal.replace('#var1#', `<div class="inp-param param0"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}0"></i><select id="select0" class="form-control form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important">${sourceOpts}</select><input id="input0" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />
														<div class="custom-control custom-radio custom-control-inline ${ruleId}0 none" title="String Constant">
														  <input type="radio" class="custom-control-input" id="SC0" name="typeValueRadio0" value="SC" checked>
														  <label class="custom-control-label" for="SC0">SC</label>
														</div>
														
														<div class="custom-control custom-radio custom-control-inline ${ruleId}0 none" title="Numeric Constant">
														  <input type="radio" class="custom-control-input" id="NC0" name="typeValueRadio0" value="NC">
														  <label class="custom-control-label" for="NC0">NC</label>
														</div>
														
														<div class="custom-control custom-radio custom-control-inline ${ruleId}0 none" title="Expression">
														  <input type="radio" class="custom-control-input" id="EXP0" name="typeValueRadio0" value="EXP">
														  <label class="custom-control-label" for="EXP0">EXP</label>
														</div>
													</div>`);
				_divVal = _divVal.replace('#var2#', `<div class="inp-param param1"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}1"></i><select id="select1" class="form-control form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important">${sourceOpts}</select><input id="input1" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />
														<div class="custom-control custom-radio custom-control-inline ${ruleId}1 none" title="String Constant">
														  <input type="radio" class="custom-control-input" id="SC1" name="typeValueRadio1" value="SC" checked>
														  <label class="custom-control-label" for="SC1">SC</label>
														</div>
														
														<div class="custom-control custom-radio custom-control-inline ${ruleId}1 none" title="Numeric Constant">
														  <input type="radio" class="custom-control-input" id="NC1" name="typeValueRadio1" value="NC">
														  <label class="custom-control-label" for="NC1">NC</label>
														</div>
														
														<div class="custom-control custom-radio custom-control-inline ${ruleId}1 none" title="Expression">
														  <input type="radio" class="custom-control-input" id="EXP1" name="typeValueRadio1" value="EXP">
														  <label class="custom-control-label" for="EXP1">EXP</label>
														</div>
													</div>`);
				_divVal = _divVal.replace('#Date Part#', `<div class="inp-param param2"><i class="fas fa-toggle-off pointer text-info iTag${ruleId}2"></i><select id="select2" class="form-control form-control-sm custom-width ml-1 mr-1 mt-2 none" style="width:50%!important">${sourceOpts}</select><input id="input2" placeholder="Date Part" class="form-control input-t form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />
																<div class="custom-control custom-radio custom-control-inline ${ruleId}2" title="String Constant">
																  <input type="radio" class="custom-control-input" id="SC2" name="typeValueRadio2" value="SC" checked>
																  <label class="custom-control-label" for="SC2">SC</label>
																</div>
																
																<div class="custom-control custom-radio custom-control-inline ${ruleId}2" title="Numeric Constant">
																  <input type="radio" class="custom-control-input" id="NC2" name="typeValueRadio2" value="NC">
																  <label class="custom-control-label" for="NC2">NC</label>
																</div>
																
																<div class="custom-control custom-radio custom-control-inline ${ruleId}2" title="Expression">
																  <input type="radio" class="custom-control-input" id="EXP2" name="typeValueRadio2" value="EXP">
																  <label class="custom-control-label" for="EXP2">EXP</label>
																</div>
															</div>`);
			
			}else{
				if(_ruleObj.transformationRule.parameter!=null){
					for(let prm=0;prm<_ruleObj.transformationRule.parameter.length;prm++){
//						alert(_ruleObj.transformationRule.parameter.length);
						if(_ruleObj.transformationRule.parameter[prm].name=="var"){
							type="none";
						}else{
							type='';
						}
						radioDiv = getRadioDiv(prm,type,ruleId);
						if(_ruleObj.transformationType === 'Aggregator'){
							_divVal = _divVal.replace('#var#', `<div class="inp-param param0"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}0"></i><select id="select0" class="form-control select-t form-control-sm custom-width ml-1 mr-1" style="width:70%!important">${sourceOpts}</select><input id="input0" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />${radioDiv} group by <select id="addedGroupByColumns" required="" multiple="" class="ml-3 mt-2 form-control select-t form-control-sm custom-width ml-1 mr-1" style="width:70%!important">${sourceOpts}</select><small id="emailHelp" class="form-text text-muted">Use ctrl key for multiselect.</small></div>`);
						}else{
							if(_ruleObj.transformationRule.parameter[prm].name=="var"){
								_divVal = _divVal.replace('#var#',`<div class="inp-param param${prm}"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}${prm}"></i><select id="select${prm}" class="form-control select-t form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important">${sourceOpts}</select><input id="input${prm}" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />${radioDiv}</div>`);
								
							}else{
								if((_ruleObj.transformationRule.ruleName.startsWith('Check') ||_ruleObj.transformationRule.ruleName.startsWith('Conversion')) && _ruleObj.transformationRule.parameter[prm].name == 'Date Format' && document.getElementById('defaultadteformat').value != ""){
									//_divVal = _ruleObj.expression.replace(/'/g, '').replace('#var#', tblCol).replace('#'+_ruleObj.transformationRule.parameter[0].name+'#', `<input id="input0" style="width:${_customWidth}" class="custom-width form-control form-control-sm ml-1 mr-1 ${_isMandatory}" value="`+document.getElementById('defaultadteformat').value+`" placeholder="${_ruleObj.transformationRule.parameter[0].name}" />`);
									_divVal = _divVal.replace('#'+_ruleObj.transformationRule.parameter[prm].name+'#', `<div class="inp-param param${prm}"><i class="fas fa-toggle-off pointer text-info iTag${ruleId}${prm}"></i><select id="select${prm}" class="form-control none select-t form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important">${sourceOpts}</select><input id="input${prm}" value="`+document.getElementById('defaultadteformat').value+`"  placeholder="${_ruleObj.transformationRule.parameter[prm].name}" class="form-control input-t form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />${radioDiv}</div>`);
								}else{
								_divVal = _divVal.replace('#'+_ruleObj.transformationRule.parameter[prm].name+'#', `<div class="inp-param param${prm}"><i class="fas fa-toggle-off pointer text-info iTag${ruleId}${prm}"></i><select id="select${prm}" class="form-control none select-t form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important">${sourceOpts}</select><input id="input${prm}" placeholder="${_ruleObj.transformationRule.parameter[prm].name}" class="form-control input-t form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />${radioDiv}</div>`);
								}
							}
						}
					}
				}
			}
		}
	}
	return _divVal;
}
 function getRadioDiv(id,type,ruleId){
	 let _divVal = `<div class="custom-control custom-radio custom-control-inline ${ruleId}${id}  ${type} ml-2" title="String Constant">
					  <input type="radio" class="custom-control-input" id="SC${id}" name="typeValueRadio${id}" value="SC" checked>
					  <label class="custom-control-label" for="SC${id}">SC</label>
					</div>
					
					<div class="custom-control custom-radio custom-control-inline ${ruleId}${id} ${type}" title="Numeric Constant">
					  <input type="radio" class="custom-control-input" id="NC${id}" name="typeValueRadio${id}" value="NC">
					  <label class="custom-control-label" for="NC${id}">NC</label>
					</div>
					
					<div class="custom-control custom-radio custom-control-inline ${ruleId}${id} ${type}" title="Expression">
					  <input type="radio" class="custom-control-input" id="EXP${id}" name="typeValueRadio${id}" value="EXP">
					  <label class="custom-control-label" for="EXP${id}">EXP</label>
					</div>`;
	 return _divVal;
 }
/*function getRuleSyntaxFromRule(id, _sourceEnt, _sourceColumn, selectedRule, sourceOpts, _toolType) {
	let tblCol = _sourceEnt+"."+_sourceColumn;
	if(_toolType == 'Designer'){
		tblCol = `<select id="select0" class="form-control form-control-sm custom-width ml-1 mr-1" style="width:50%!important">${sourceOpts}</select>`;
	}
	let _paramName = '', _paramList = '', _paramCount = '', customWidth = '', _ruleMappings = '', _divVal = '';
	_ruleMappings = document.querySelector('#ruleMappings').textContent;
	const _ruleMapObj = JSON.parse(_ruleMappings);
	console.log(_ruleMapObj);
	for(let i = 0, len = _ruleMapObj.ruleMapping.length; i < len; i++){
		let _ruleObj = _ruleMapObj.ruleMapping[i];
		let _ruleName = _ruleObj.transformationRule.ruleName;
		_ruleName = _ruleName.substring(_ruleName.indexOf('-')+1, _ruleName.length).trim();
		_ruleName == 'General Expression' ?  customWidth = '100%!important' : customWidth = '100px!important';
		if(selectedRule === _ruleName){
			_paramCount = _ruleObj.transformationRule.parameter.length;
			
			if(_paramCount === 0){
				switch(_ruleName){
					case 'Concatenation' :
						_divVal = _ruleObj.expression.replace('#var1#', tblCol).replace('#var2#',`<select id="select1" class="form-control form-control-sm custom-width ml-1 mr-1" style="width:50%!important">${sourceOpts}</select>`);
						break;
					default :
						_divVal = _ruleObj.expression.replace('#var#', tblCol);
						break;
				}
				
			}
			else if(_paramCount === 1){
				switch(_ruleName){
					
				    case 'Difference between Dates':
						_paramName = _ruleObj.transformationRule.parameter[0].name;
						_divVal = _ruleObj.expression.replace('#var1#', tblCol).replace('#var2#',`<select id="select${id}" class="form-control form-control-sm custom-width ml-1 mr-1" style="width:50%!important">${sourceOpts}</select>`).replace('#'+_paramName+'#',`<input type="text" id="input${id}" placeholder="${_paramName}" class="form-control form-control-sm custom-width ml-1 mr-1" />`);
						break;
					
					case 'GroupBy' :
						_divVal = `<select class="custom-select bg-white" id="addedGroupByColumns" required="" multiple="" style="min-height:110px!important;width:80%;margin-left:5%;margin-top:5%;">
									${sourceOpts}
								  </select>`;
						break;
					
					case 'DescriptiveTransRule' :
						_divVal = `<textarea type="text" id="input${+j+1}" placeholder="Enter Expression/Description" class="form-control form-control-sm ml-1 mr-1" ></textarea>`;
						break;
						
					default :
						_paramName = _ruleObj.transformationRule.parameter[0].name;
						if(_ruleObj.transformationType === 'Aggregator'){
							_divVal = `${_ruleObj.expression.replace('#var#', tblCol)} group by <div class="form-group"><select class="form-control form-control-sm mt-2 bg-white" id="addedGroupByColumns" required="" multiple="" style="min-height:110px!important;">${sourceOpts}</select><small id="emailHelp" class="form-text text-muted">Use ctrl key for multiselect.</small></div>`;
						}else{
							if(_ruleObj.expression.includes('#var#')){
								_divVal = _ruleObj.expression.replace('#var#', tblCol).replace('#'+_paramName+'#',`<input type="text" id="input${id}" placeholder="${_paramName}" class="form-control form-control-sm custom-width ml-1 mr-1" />`);
							}else{
								_divVal = _ruleObj.expression.replace(/'/g, '').replace('#'+_paramName+'#',`<input type="text" id="input${id}" placeholder="${_paramName}" class="form-control form-control-sm ml-1 mr-1" style="width:${customWidth}" />`);
							}
							
						}
				}
				
			}
			else if(_paramCount > 1){
				_paramList = _ruleObj.transformationRule.parameter;
				_divVal = _ruleObj.expression;
				for(let j = 0; j < _paramList.length; j++){
					_paramName = _ruleObj.transformationRule.parameter[j].name;
					_divVal = _divVal.replace('#var#', tblCol).replace('#'+_paramName+'#',`<input type="text" id="input${j}" placeholder="${_paramName}" class="form-control form-control-sm custom-width ml-1 mr-1" />`);
				}
			}
		}
	}
	return _divVal;
}*/
 
 function getHTMLDiv(_id,_parameter,ruleName,optionTag){
	 for(let p in _parameter){
		 
		 if(_parameter[p].type == 'DE'){
			 $('.iTag'+ruleName+p).removeClass('fa-toggle-off').addClass('fa-toggle-on');
			 $('#select'+p).removeClass('none');
			 $('#input'+p).addClass('none');
			 $('.'+ruleName+p).addClass('none');
		 }else{
			 $('.iTag'+ruleName+p).removeClass('fa-toggle-on').addClass('fa-toggle-off');
			 $('#select'+p).addClass('none');
			 $('#input'+p).removeClass('none');
			 $('.'+ruleName+p).removeClass('none');
		 }
		 
		 if(ruleName=='Concatenation' && p>1){
			 document.querySelector('.iTag'+ruleName+p).addEventListener('click',function(e){
					toggleInput($(e.target),_id,optionTag);
		    	});
			 if(parseInt(p)+1==_parameter.length){
				 document.querySelector('.add'+p).addEventListener('click',function(e){
						addNewParamForConcat($(e.target),_id,p,optionTag,ruleName);
			    	});
			 }else{
				 document.querySelector('.removeParam'+p).addEventListener('click',function(e){
						removeParamForConcat($(e.target));
					});
			 }
		 }
	 }
 }

function getRuleDetailsForEdit(_id, _sourceList, _selectedRule, _type, _parameter,optionTag){
	console.log(_parameter);
	$('#select0').val(_sourceList);
	let ruleId = _selectedRule.replace(/ /g, "-").replace('/','-');
//	alert(_type);
	switch(_type){
		case 'String' :
			$('.card-deck').find('div.card-string').click();
			clickRule(_id,_selectedRule);
			if(_selectedRule!='Concatenation'){
				getHTMLDiv(_id,_parameter,ruleId,optionTag);
			}
			if(_selectedRule.includes('Concatenation')){
				let paramCnt = _parameter.length;
				if(paramCnt==2){
					for(let p in _parameter){
						if(_parameter[p].type=="DE"){
							$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
						}else{
							$('#input'+p).val(_parameter[p].value);
							$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
						}
					}
				}else{
					let divVal='';
					for(let p=2;p<_parameter.length;p++){
						let btn='';
						if(p+1==_parameter.length){
							btn = `<i class="pl-1 pointer fa fa-plus-circle text-info add${p}" title="Add another parameter"></i>`;
						}else{
							btn = `<i class="pl-1 pointer fa fa-times text-danger removeParam${p}" title="Remove Parameter"></i>`;
						}
						divVal = divVal+ `<div class="inp-param param${p}"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}${p}"></i><select id="select${p}" class="form-control form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important">${optionTag}</select><input id="input${p}" placeholder="Enter Value" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />
										<div class="custom-control custom-radio custom-control-inline ${ruleId}${p} none" title="String Constant">
										  <input type="radio" class="custom-control-input" id="SC${p}" name="typeValueRadio${p}" value="SC" checked>
										  <label class="custom-control-label" for="SC${p}">SC</label>
										</div>
										
										<div class="custom-control custom-radio custom-control-inline ${ruleId}${p} none" title="Numeric Constant">
										  <input type="radio" class="custom-control-input" id="NC${p}" name="typeValueRadio${p}" value="NC">
										  <label class="custom-control-label" for="NC${p}">NC</label>
										</div>
										
										<div class="custom-control custom-radio custom-control-inline ${ruleId}${p} none" title="Expression">
										  <input type="radio" class="custom-control-input" id="EXP${p}" name="typeValueRadio${p}" value="EXP">
										  <label class="custom-control-label" for="EXP${p}">EXP</label>
										</div>
										${btn}
									</div>`;
					}
					$('.add1').remove();
					let removeBtn = `<i class="pl-1 pointer fa fa-times text-danger removeParam1" title="Remove Parameter"></i>`;
					$('.param1').append(removeBtn);
					$(divVal).insertAfter('div.param1');
					document.querySelector('.removeParam1').addEventListener('click',function(e){
						removeParamForConcat($(e.target));
					});
					getHTMLDiv(_id,_parameter,ruleId,optionTag);
					//populating Data
					for(let p in _parameter){
						if(_parameter[p].type=="DE"){
							$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
						}else{
							$('#input'+p).val(_parameter[p].value);
							$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
						}
					}
				}
			}else{
				if(_parameter.length == 1){
					if(_parameter[0].type=="DE"){
						$("#select0").find("option[data-edit-value='" + _parameter[0].value + "']").attr('selected','selected');
					}else{
//						$('.iTag'+_selectedRule+0).removeClass('fa-toggle-on').addClass('fa-toggle-off');
						$('#input0').val(_parameter[0].value);
						$("input[name=typeValueRadio0][value="+_parameter[0].type+"]").prop("checked",true);
//						toggleInput($(e.target),_id,_sourceList);
						
					}
				}
				if(_parameter.length > 1){
					for(let p in _parameter){
						if(_parameter[p].type=="DE"){
							$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
						}else{
							$('#input'+p).val(_parameter[p].value);
							$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
						}
					}
				}
			}
		break;
		case 'Date/Time' :
			$('.card-deck').find('div.card-date').click();
			clickRule(_id,_selectedRule);
			getHTMLDiv(_id,_parameter,ruleId,optionTag);
			if(_parameter.length > 1){
				for(let p in _parameter){
					if(_parameter[p].type=="DE"){
						$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
					}else{
						$('#input'+p).val(_parameter[p].value);
//						$('.iTag'+_selectedRule+p).removeClass('fa-toggle-on').addClass('fa-toggle-off');
						$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
					}
				}
			}
		break;
		case 'Numeric' :
			$('.card-deck').find('div.card-numeric').click();
			clickRule(_id,_selectedRule);
			getHTMLDiv(_id,_parameter,ruleId,optionTag);
			if(_parameter.length == 1){
				if(_parameter[0].type=="DE"){
					$("#select0").find("option[data-edit-value='" + _parameter[0].value + "']").attr('selected','selected');
				}else{
					$('#input0').val(_parameter[0].value);
					$("input[name=typeValueRadio0][value="+_parameter[0].type+"]").prop("checked",true);
				}
			}else{
				for(let p in _parameter){
					if(_parameter[p].type=="DE"){
						$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
					}else{
						$('#input'+p).val(_parameter[p].value);
						$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
					}
				}
			}
		break;
		case 'Aggregate' :
			$('.card-deck').find('div.card-aggregate').click();
			clickRule(_id,_selectedRule);
			getHTMLDiv(_id,_parameter,ruleId,optionTag);
			if(_selectedRule=='GroupBy'){
				for(let p in _parameter){
					$('#addedGroupByColumns').find('option[data-edit-value="' +_parameter[p].value+ '"]').attr('selected', 'selected');
				}
			}else{
				for(let p in _parameter){
					if(p==0){
						if(_parameter[p].type=="DE"){
							$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
						}else{
							$('#input'+p).val(_parameter[p].value);
							$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
						}
					}else{
						$('#addedGroupByColumns').find('option[data-edit-value="' +_parameter[p].value+ '"]').attr('selected', 'selected');
					}
				}
			}
		break;
		case 'Conversion' :
			$('.card-deck').find('div.card-conversion').click();
			clickRule(_id,_selectedRule);
			getHTMLDiv(_id,_parameter,ruleId,optionTag);
			if(_parameter.length == 1){
				if(_parameter[0].type=="DE"){
					$("#select0").find("option[data-edit-value='" + _parameter[0].value + "']").attr('selected','selected');
				}else{
					$('#input0').val(_parameter[0].value);
					$("input[name=typeValueRadio0][value="+_parameter[0].type+"]").prop("checked",true);
				}
			}else{
				for(let p in _parameter){
					if(_parameter[p].type=="DE"){
						$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
					}else{
						$('#input'+p).val(_parameter[p].value);
						$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
					}
				}
			}
		break;
		case 'Encoding' :
			$('.card-deck').find('div.card-encoding').click();
			clickRule(_id,_selectedRule);
			getHTMLDiv(_id,_parameter,ruleId,optionTag);
			if(_parameter.length == 1){
				if(_parameter[0].type=="DE"){
					$("#select0").find("option[data-edit-value='" + _parameter[0].value + "']").attr('selected','selected');
				}else{
					$('#input0').val(_parameter[0].value);
					$("input[name=typeValueRadio0][value="+_parameter[0].type+"]").prop("checked",true);
				}
			}else{
				for(let p in _parameter){
					if(_parameter[p].type=="DE"){
						$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
					}else{
						$('#input'+p).val(_parameter[p].value);
						$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
					}
				}
			}
		break;
		case 'Check' :
			$('.card-deck').find('div.card-check').click();
			clickRule(_id,_selectedRule);
			getHTMLDiv(_id,_parameter,ruleId,optionTag);
			if(_parameter.length == 1){
				if(_parameter[0].type=="DE"){
					$("#select0").find("option[data-edit-value='" + _parameter[0].value + "']").attr('selected','selected');
				}else{
					$('#input0').val(_parameter[0].value);
					$("input[name=typeValueRadio0][value="+_parameter[0].type+"]").prop("checked",true);
				}
			}
			if(_parameter.length > 1){
				for(let p in _parameter){
					if(_parameter[p].type=="DE"){
						$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
					}else{
						$('#input'+p).val(_parameter[p].value);
						$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
					}
				}
			}
		break;
		case 'Special' :
			$('.card-deck').find('div.card-special').click();
			clickRule(_id,_selectedRule);
			getHTMLDiv(_id,_parameter,ruleId,optionTag);
			if(_parameter.length == 1){
				if(_parameter[0].type=="DE"){
					$("#select0").find("option[data-edit-value='" + _parameter[0].value + "']").attr('selected','selected');
				}else{
					$('#input0').val(_parameter[0].value);
					$("input[name=typeValueRadio0][value="+_parameter[0].type+"]").prop("checked",true);
				}
			}
			if(_parameter.length > 1){
				if(_selectedRule == 'Rank Index'){
					$('#rankCol'+_id).val(_parameter[0].value);
					$('#noRanks'+_id).val(_parameter[1].value);
					$('#topBot'+_id).val(_parameter[2].value);
					$('#groupBy'+_id).val(_parameter[3].value);
				}else{
					for(let p in _parameter){
						if(_parameter[p].type=="DE"){
							$("#select"+p).find("option[data-edit-value='" + _parameter[p].value + "']").attr('selected','selected');
						}else{
							$('#input'+p).val(_parameter[p].value);
							$("input[name=typeValueRadio"+p+"][value="+_parameter[p].type+"]").prop("checked",true);
						}
					}
				}
			}
		break;
		case 'General' :
			$('.card-deck').find('div.card-general').click();
			clickRule(_id,_selectedRule);
			getHTMLDiv(_id,_parameter,ruleId,optionTag);
			if(_parameter.length == 1){
				if(_parameter[0].type=="DE"){
					$("#select0").find("option[data-edit-value='" + _parameter[0].value + "']").attr('selected','selected');
				}else{
					$('#input0').val(_parameter[0].value);
					$("input[name=typeValueRadio0][value="+_parameter[0].type+"]").prop("checked",true);
				}
			}
		break;
		case 'Audit / Generated' :
			$('.card-deck').find('div.card-audit').click();
			clickRule(_id,_selectedRule);
//			if(_parameter.length == 1){
//				$('#input0').val(_parameter[0].value);
//			}
		break;
	}
	
}

function clickRule(_id,_selectedRule){
	if(_selectedRule.includes('-')){
		_selectedRule = _selectedRule.substring(_selectedRule.indexOf('-')+1,_selectedRule.length).trim();
	}
	$('#trUlList'+_id).find('a').each(function(){
		if($(this).text() == _selectedRule.trim()){
			$(this).click();
		}
	});
}

function getRuleDetailsOnApply(_selectedRule, _sourceEnt, _sourceCol, _id, typ){
	let ruleId = _selectedRule.replace(/ /g, "-").replace('/','-');
	let _ruleDesc = '', _ruleExp = '', _divVal = '', _sourceEnAttr = [], _ruleJson = '', _rl='', transformationRule = {};
	let _ruleMappings = document.querySelector('#ruleMappings').textContent;
	const _ruleMapObj = JSON.parse(_ruleMappings);
	for(let i = 0, len = _ruleMapObj.length; i < len; i++){
		let _ruleObj = _ruleMapObj[i];
		let _ruleName = _ruleObj.transformationRule.ruleName;
		_rl = _ruleObj.transformationRule.ruleName; // used for Aggregator - Expression
		_ruleName = _ruleName.substring(_ruleName.indexOf('-')+1, _ruleName.length).trim();
		let _type = _ruleObj.transformationRule.ruleName.substring(0,_ruleObj.transformationRule.ruleName.indexOf('-')).trim();
		
		let inputDataElmnsArray = [], parameterArray = [];
		let radioButtonVal='';
		_ruleExp = _ruleObj.expression;
		_ruleDesc = _ruleObj.transformationRule.ruleDescription;
		if(_selectedRule.toLowerCase() === _ruleName.toLowerCase() && _type.includes(typ) && _rl!='Aggregator - Expression'){
			
			if(_selectedRule == 'Concatenation'){
				if($('#appliedRule'+_id).find('div.inp-param').length == 2){
					if($('.iTagConcatenation0').hasClass('fa-toggle-on') && $('.iTagConcatenation1').hasClass('fa-toggle-on')){
						_ruleDesc = "Concatenation of #var0# and #var1#";
						_ruleExp = "CONCAT(#var1#,#var2#)";
						$('#appliedRule'+_id).find('div.inp-param').each(function(index){
							let inputDataElmn = {}, parameter = {};
							transformationRule.rulename = _selectedRule;
							transformationRule.type = _type;
							inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
							inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
							inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
							inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
							inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
							inputDataElmn.componentname = $(this).find('select option:selected').data('componentname');
							inputDataElmnsArray.push(inputDataElmn);
							parameter.name = 'var';
							parameter.type = 'DE';
							parameter.value = $(this).find('select option:selected').data('entityphyname')+'.'+$(this).find('select option:selected').data('attribphyname');
							parameterArray.push(parameter);
							_ruleDesc = _ruleDesc.replace('#var'+index+'#', $(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+')');
							_ruleExp = _ruleExp.replace('#var'+index+'#', $(this).find('select option:selected').data('edit-value'));
						});
					}else{
						_ruleDesc = "Concatenation of #var0# and #var1#";
						_ruleExp = "CONCAT(#var1#,#var2#)";
						$('#appliedRule'+_id).find('div.inp-param').each(function(index){
							let inputDataElmn = {}, parameter = {};
							transformationRule.rulename = 'Concatenation';
							transformationRule.type = 'String';
							if($(this).find('i').hasClass('fa-toggle-on')){
								inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
								inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
								inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
								inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
								inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
								inputDataElmn.componentname = $(this).find('select option:selected').data('componentname');
								inputDataElmnsArray.push(inputDataElmn);
								parameter.name = 'var';
								parameter.type = 'DE';
								parameter.value = $(this).find('select option:selected').data('entityphyname')+'.'+$(this).find('select option:selected').data('attribphyname');
								parameterArray.push(parameter);
								_ruleDesc = _ruleDesc.replace('#var'+index+'#', $(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+')');
								_ruleExp = _ruleExp.replace('#var'+index+'#', $(this).find('select option:selected').data('edit-value'));
							}else{
								radioButtonVal = $('input[name="typeValueRadio'+index+'"]:checked').val();
								parameter.name = 'Value';
								parameter.type = radioButtonVal;
								parameter.value = $(this).find('input').val();
								parameterArray.push(parameter);
								_ruleDesc = _ruleDesc.replace('#var'+index+'#', $(this).find('input').val());
								_ruleExp = _ruleExp.replace('#var'+index+'#', $(this).find('input').val());
							}
						});
					}
				}else{
					_ruleDesc = "Concatenation of ";
					_ruleExp = "CONCAT(";
					let paramVal = '';
					$('#appliedRule'+_id).find('div.inp-param').each(function(index){
						let inputDataElmn = {};
						let parameter = {};
						transformationRule.rulename = 'Concatenation';
						transformationRule.type = 'String';
						if($(this).find('i').hasClass('fa-toggle-on')){
							inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
							inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
							inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
							inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
							inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
							inputDataElmn.componentname = $(this).find('select option:selected').data('componentname');
							inputDataElmnsArray.push(inputDataElmn);
							parameter.name = 'var';
							parameter.type = 'DE';
							parameter.value = $(this).find('select option:selected').data('entityphyname')+'.'+$(this).find('select option:selected').data('attribphyname');
							parameterArray.push(parameter);
							_ruleDesc += $(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+') ';
							_ruleExp += $(this).find('select option:selected').data('edit-value')+' , ';
//							paramVal +=  $(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+') || ';
						}else{
							radioButtonVal = $('input[name="typeValueRadio'+index+'"]:checked').val();
							parameter.name = 'Value';
							parameter.type = radioButtonVal;
							parameter.value = $(this).find('input').val();
							parameterArray.push(parameter);
//							paramVal += $(this).find('input').val() + "|| ";
							_ruleDesc += $(this).find('input').val()+' ';
							_ruleExp += $(this).find('input').val()+' , ';
						}
					});
					if(_ruleExp.endsWith(',')){
						_ruleExp = _ruleExp.substring(0,_ruleExp.lastIndexOf(','));
					}
					_ruleExp = _ruleExp+')';
//					if(paramVal.endsWith('|| '))
//						paramVal = paramVal.substring(0, paramVal.lastIndexOf('||'));
//					let parameter = {};
//					parameter.name = 'Expression';
//					parameter.type = _paramType;
//					parameter.value = paramVal;
//					parameterArray.push(parameter);
				}
			}else if(_ruleObj.transformationType === 'Aggregator'){
				transformationRule.rulename = _selectedRule;
				transformationRule.type = _type;
				if(_selectedRule=='GroupBy'){
					let inputDataElmn = {}, parameter = {},groupByCols = [];
					$('#addedGroupByColumns').find('option:selected').each(function(){
						parameter = {}
						parameter.name = 'Group By';
						parameter.type = 'DE';
						parameter.value = $(this).data('entityphyname')+'.'+$(this).data('attribphyname');
						parameterArray.push(parameter);
						groupByCols.push($(this).data('attribphyname')+' ('+$(this).data('entityphyname')+')');
					});
					_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',groupByCols.join(','));
					
				}else{
					$('#appliedRule'+_id).find('div.inp-param').each(function(index){
						let inputDataElmn = {}, parameter = {};
						if($(this).find('i').hasClass('fa-toggle-on')){
							inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
							inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
							inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
							inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
							inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
							inputDataElmn.componentname = $(this).find('select option:selected').data('componentname');
							inputDataElmnsArray.push(inputDataElmn);
//							parameter.name = _ruleObj.transformationRule.parameter[0].name;
							parameter.name = 'var';
							parameter.type = 'DE';
							//parameter.value = groupByCols;
							parameter.value = $(this).find('select option:selected').data('entityphyname')+'.'+$(this).find('select option:selected').data('attribphyname');
							parameterArray.push(parameter);
							let groupByCols = [];
							$('#addedGroupByColumns').find('option:selected').each(function(){
								parameter = {}
								parameter.name = 'Group By';
								parameter.type = 'DE';
								parameter.value = $(this).data('entityphyname')+'.'+$(this).data('attribphyname');
								parameterArray.push(parameter);
								groupByCols.push($(this).data('attribphyname')+' ('+$(this).data('entityphyname')+')');
							});
							_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',$(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+') group by '+groupByCols.join(','));
						}else{
							//not applicable
						}
					});
					let groupByCols = [];
				}
			}else if(_selectedRule == 'Difference between Dates'){
				$('#appliedRule'+_id).find('div.inp-param').each(function(index){
					transformationRule.rulename = _selectedRule;
					transformationRule.type = _type;
					let inputDataElmn = {},parameter = {};
					if(index<2){
						let id = index+1;
						if($(this).find('i').hasClass('fa-toggle-on')){
							inputDataElmn = {};
							inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
							inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
							inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
							inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
							inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
							inputDataElmn.componentname = $(this).find('select option:selected').data('componentname');
							inputDataElmnsArray.push(inputDataElmn);
							
							parameter.name = 'var';
							parameter.type = 'DE';
							parameter.value = $(this).find('select option:selected').data('entityphyname')+"."+$(this).find('select option:selected').data('attribphyname');
							parameterArray.push(parameter);
							_ruleDesc = _ruleDesc.replace('#var'+id+'#', $(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+')');
							_ruleExp = _ruleExp.replace('#var'+id+'#', $(this).find('select option:selected').data('edit-value'));
							
						}else{
							radioButtonVal = $('input[name="typeValueRadio'+index+'"]:checked').val();
							parameter.name = 'value';
							parameter.type = radioButtonVal;
							parameter.value = $(this).find('input').val();
							parameterArray.push(parameter);
							_ruleDesc = _ruleDesc.replace('#var'+id+'#',$(this).find('input').val());
							_ruleExp = _ruleExp.replace('#var'+id+'#',$(this).find('input').val());
						}
					}else{
						if($(this).find('i').hasClass('fa-toggle-on')){
							inputDataElmn = {};
							inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
							inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
							inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
							inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
							inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
							inputDataElmn.componentname = $(this).find('select option:selected').data('componentname');
							inputDataElmnsArray.push(inputDataElmn);
							parameter.name = 'var';
							parameter.type = 'DE';
							parameter.value = $(this).find('select option:selected').data('entityphyname')+"."+$(this).find('select option:selected').data('attribphyname');
							parameterArray.push(parameter);
							_ruleDesc = _ruleDesc.replace('#Date Part#', $(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+')');
							_ruleExp = _ruleExp.replace('#Date Part#',$(this).find('select option:selected').data('edit-value'));
							
						}else{
							radioButtonVal = $('input[name="typeValueRadio'+index+'"]:checked').val();
							parameter.name = 'value';
							parameter.type = radioButtonVal;
							parameter.value = $(this).find('input').val();
							parameterArray.push(parameter);
							_ruleDesc = _ruleDesc.replace('#Date Part#',$(this).find('input').val());
							_ruleExp = _ruleExp.replace('#Date Part#',$(this).find('input').val());
						}
					}
				});
			}else if(_selectedRule == 'Rank Index'){
				transformationRule.rulename = 'Rank Index';
				transformationRule.type = 'Special';
				let parameter = {};
				parameter.name = 'Rank Port';
				parameter.type = 'DE';
				parameter.value = $('#rankCol'+_id).val();
				parameterArray.push(parameter);
				
				parameter = {};
				parameter.name = 'Rank Number';
				parameter.type = 'NC';
				parameter.value = $('#noRanks'+_id).val();
				parameterArray.push(parameter);
				
				parameter = {};
				parameter.name = 'Top / Bottom Flag';
				parameter.type = 'SC';
				parameter.value = $('#topBot'+_id).val();
				parameterArray.push(parameter);
				
				parameter = {};
				parameter.name = 'Group By';
				parameter.type = 'DE';
				parameter.value = $('#groupBy'+_id).val();
				parameterArray.push(parameter);
				
				_ruleDesc = _ruleDesc.replace('#Rank Port#',$('#rankCol'+_id).val());
				_ruleDesc = _ruleDesc.replace('#Rank Number#',$('#noRanks'+_id).val());
				_ruleDesc = _ruleDesc.replace('#Top / Bottom Flag#',$('#topBot'+_id).val());
				_ruleDesc = _ruleDesc.replace('#Group By#',$('#groupBy'+_id).val());
				
				_ruleExp = _ruleExp.replace('#Rank Port#',$('#rankCol'+_id).val());
				_ruleExp = _ruleExp.replace('#Rank Number#',$('#noRanks'+_id).val());
				_ruleExp = _ruleExp.replace('#Top / Bottom Flag#',$('#topBot'+_id).val());
				_ruleExp = _ruleExp.replace('#Group By#',$('#groupBy'+_id).val());
				
			}else if(_selectedRule=='System Timestamp' || _selectedRule=='System Date'){
				transformationRule.rulename = _selectedRule;
				transformationRule.type = _type;
				
			}else if(_selectedRule=='Union'){
				transformationRule.rulename = _selectedRule;
				transformationRule.type = _type;
				
			}else{
				$('#appliedRule'+_id).find('div.inp-param').each(function(index){
					let inputDataElmn = {}, parameter = {},parameterName = 'var';
					transformationRule.rulename = _selectedRule;
					transformationRule.type = _type;
					if($(this).find('i').hasClass('fa-toggle-on'))
					{
//						if(index>0){
//							if(_ruleObj.transformationRule.parameter!=null){
//								parameterName = _ruleObj.transformationRule.parameter[index-1].name;
//							}
//						}
						parameterName = _ruleObj.transformationRule.parameter[index].name;
						inputDataElmn = {};
						inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
						inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
						inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
						inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
						inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
						inputDataElmn.componentname = $(this).find('select option:selected').data('componentname');
						inputDataElmnsArray.push(inputDataElmn);
						parameter.name = parameterName;
						parameter.type = 'DE';
						parameter.value = $(this).find('select option:selected').data('entityphyname')+"."+$(this).find('select option:selected').data('attribphyname');
						parameterArray.push(parameter);
						
						_ruleDesc = _ruleDesc.replace('#'+parameterName+'#', $(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+')');
						_ruleExp = _ruleExp.replace('#'+parameterName+'#',$(this).find('select option:selected').data('edit-value'));
						
					}
					else{
						radioButtonVal = $('input[name="typeValueRadio'+index+'"]:checked').val();
						parameterName = _ruleObj.transformationRule.parameter[index].name;
						parameter.name = parameterName;
						parameter.type = radioButtonVal;
						parameter.value = $(this).find('input').val();
						parameterArray.push(parameter);
						if(parameterName=='#var#'){
							_ruleDesc = _ruleDesc.replace('#var#',$(this).find('input').val());
							_ruleExp = _ruleExp.replace('#var#',$(this).find('input').val());
						}else{
							_ruleDesc = _ruleDesc.replace('#'+parameterName+'#',$(this).find('input').val());
							_ruleExp = _ruleExp.replace('#'+parameterName+'#',$(this).find('input').val());
						}
					}
					
					
					
					
					
//					if(index == 0){
//						alert("in index0");
//						if($(this).find('i').hasClass('fa-toggle-on')){
//							transformationRule.rulename = _selectedRule;
//							transformationRule.type = _type;
//							inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
//							inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
//							inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
//							inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
//							inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
//							inputDataElmnsArray.push(inputDataElmn);
//							if(_ruleObj.transformationRule.parameter == null){
//								alert("parameter exist");
//								parameter.name = 'var';
//								parameter.type = 'DE';
//								parameter.value = $(this).find('select option:selected').data('entityphyname')+"."+$(this).find('select option:selected').data('attribphyname');
//								parameterArray.push(parameter);
//							}else{
//								
//							}
//							_ruleDesc = _ruleDesc.replace('#var#', $(this).find('select option:selected').data('attribphyname')+' ('+$(this).find('select option:selected').data('entityphyname')+')');
//						}else{
//							transformationRule.rulename = 'General - Expression';
//							transformationRule.type = 'General';
//							parameter.name = 'Expression';
//							if(_ruleExp.includes('#var#'))
//								parameter.value = _ruleExp.replace('#var#',$(this).find('input').val());
//							else
//								parameter.value = $(this).find('input').val();
//							parameter.type = 'SC';
//							parameterArray.push(parameter);
//							_ruleDesc = _ruleDesc.replace('#var#',$(this).find('input').val());
//							_ruleDesc = _ruleDesc.replace('#Expression#', $(this).find('input').val());
//							_ruleDesc = _ruleDesc.replace('#Constant Value#', $(this).find('input').val());
//						}
//					}else{
//						alert("in else index0");
//						if($(this).find('i').hasClass('fa-toggle-on')){
//							inputDataElmn = {};
//							inputDataElmn.catalogName = $(this).find('select option:selected').data('catalogname');
//							inputDataElmn.inputComponentRef = $(this).find('select option:selected').data('entityphyname');
//							inputDataElmn.inputLogComponentRef = $(this).find('select option:selected').data('entitylogname');
//							inputDataElmn.inputDataElementRef = $(this).find('select option:selected').data('attribphyname');
//							inputDataElmn.inputLogDataElementRef = $(this).find('select option:selected').data('attriblogname');
//							inputDataElmnsArray.push(inputDataElmn);
//							if(_ruleObj.transformationRule.parameter != null && _ruleObj.transformationRule.parameter.length > 1){
//								parameter.name = _ruleObj.transformationRule.parameter[index].name;
//								parameter.type = 'DE';
//								parameter.value = $(this).find('select option:selected').data('entityphyname')+"."+$(this).find('select option:selected').data('attribphyname');
//								parameterArray.push(parameter);
//							}
//							_ruleDesc = _ruleDesc.replace('#'+_ruleObj.transformationRule.parameter[index-1].name+'#',$(this).find('select option:selected').data('entityphyname')+"."+$(this).find('select option:selected').data('attribphyname'));
//						}else{
//							parameter.name = _ruleObj.transformationRule.parameter[index-1].name;
//							parameter.value = $(this).find('input').val();
//							parameter.type = 'SC';
//							parameterArray.push(parameter);
//							_ruleDesc = _ruleDesc.replace('#'+_ruleObj.transformationRule.parameter[index-1].name+'#',$(this).find('input').val());
//						}
//					}
				});
				console.log('parameter');
				console.log(parameterArray);
			}
			
			if($('.derivedDiv').hasClass('none')){
				transformationRule.derivedVariableName = null;
				transformationRule.derivedDatatype = null;
				transformationRule.derivedPrecision = null;
				transformationRule.derivedScale = null;
			}else{
				transformationRule.derivedVariableName = $('#derVariableName').val();
				transformationRule.derivedDatatype = $('#derivedDatatypeId').val();
				transformationRule.derivedPrecision = $('#derivedPrecisionId').val();
				transformationRule.derivedScale = $('#derivedScaleId').val();
			}
			
			transformationRule.inputDataElements = inputDataElmnsArray;
			transformationRule.parameters = parameterArray;
			transformationRule.ruleDescription = _ruleDesc;
			transformationRule.ruleExpression = _ruleExp;
		}
		
		
		
		
		
		
		
		/*if(_selectedRule.toLowerCase() === _ruleName.toLowerCase() && _type.includes(typ) && _rl!='Aggregator - Expression'){
			let _paramObj = {}, _paramArray = [], _paramCount = 0;
			if(_ruleObj.transformationRule.parameter == null)
				_paramCount = 0;
			else
				_paramCount = _ruleObj.transformationRule.parameter.length;
			if(_selectedRule == 'Concatenation'){
				_paramObj.name = 'ConcatColumn';
				_paramObj.value = $('#select1').val();
				_paramArray.push(_paramObj);
				if(_sourceEnt != null && _sourceCol != null){
					_sourceEnAttr = _sourceEnt+'.'+_sourceCol;
					_ruleExp = _ruleObj.expression.replace('#var1#',_sourceEnt+'.'+_sourceCol).replace('#var2#',$('#select1').val());
				}else{
					_sourceEnAttr = $('#select0').val();
					_ruleExp = _ruleObj.expression.replace('#var1#',$('#select0').val()).replace('#var2#',$('#select1').val());
				}
				
				//_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var1#',_sourceCol+'('+_sourceEnt+')').replace('#var2#',$('#select1').val());
				_ruleDesc = _ruleExp;
				
			}else if(_selectedRule == 'Difference between Dates'){
				_ruleExp = _ruleObj.expression
				_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
				let _paramObjVal = $('#input0').val();
				
				
				_sourceEnAttr = $('#select0').val();
				_ruleExp = _ruleExp.replace('#var1#',$('#select0').val()).replace('#var2#',$('#select1').val());
				_ruleExp = _ruleExp.replace('#'+_ruleObj.transformationRule.parameter[0].name+'#',_paramObjVal);
				
				_paramObj.name = 'datefrom';
				_paramObj.value = _sourceCol+'('+_sourceEnt+')';
				_paramArray.push(_paramObj);
				_paramObj = {};
				
				_paramObj.name = 'dateto';
				_paramObj.value = $('#select1').val();
				_paramArray.push(_paramObj);
				_paramObj = {};
				
				_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
				_paramObj.value = $('#input0').val();
				_paramArray.push(_paramObj);
				
				_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var1#',_sourceCol+'('+_sourceEnt+')').replace('#var2#',$('#select1').val()).replace('#'+_ruleObj.transformationRule.parameter[0].name+'#',_paramObjVal);
			}else{
				switch(_paramCount){
				case 0:
					let inputDataElmns = {};
					if($('.iTag'+ruleId+0).hasClass('fa-toggle-off')){
						
						transformationRule.rulename = 'General - Expression';
						transformationRule.transformationType = 'Expression';
						inputDataElmns.inputComponentRef = 
						
						_ruleName = "General - Expression"
						_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',$('#input0').val());
						_ruleExp = _ruleObj.expression.replace('#var#',$('#input0').val());
						_paramObj.name = "Expression";
						_paramObj.value = _ruleExp;
						_paramArray.push(_paramObj);
						_type = 'General';
					}else{
						_ruleName = _type + ' - '+_ruleName;
						_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',$('#select0').val());
						_ruleExp = _ruleObj.expression.replace('#var#',$('#select0').val());
						_sourceEnAttr.push($('#select0').find('option:selected'));
					}
					break;
				
				case 1:
					if(_ruleObj.transformationType === 'Aggregator'){
						let groupByCols = [];
						$('#addedGroupByColumns').find('option:selected').each(function(){
							groupByCols.push($(this).text());
						});
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = groupByCols;
						_paramArray.push(_paramObj);
						_sourceEnAttr = $('#select0').val();
						_ruleExp = _ruleObj.expression.replace('#var#',$('#select0').val());
						_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',_sourceCol+'('+_sourceEnt+') group by '+groupByCols.join(','));
						
					}else{
						_ruleExp = _ruleObj.expression;
						_ruleDesc = _ruleObj.transformationRule.ruleDescription;
						if($('.iTag'+ruleId+0).hasClass('fa-toggle-off')){
							let _paramObjVal = $('#input0').val();
							if(_ruleObj.transformationRule.ruleName=='General - Expression' && _paramObjVal.includes('\'')){
								_paramObjVal = _paramObjVal.replace(/'/g, '"');
							}
							_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
							_paramObj.value = _paramObjVal;
							_paramArray.push(_paramObj);
							_ruleExp = _ruleExp.replace('#var#',$('#input0').val());
							_ruleDesc = _ruleDesc.replace('#var#',$('#input0').val());
						}else{
							_sourceEnAttr.push($('#select0').find('option:selected'));
							_ruleExp = _ruleExp.replace('#var#',$('#select0').val());
							_ruleDesc = _ruleDesc.replace('#var#',$('#select0').val());
						}
						
						if($('.iTag'+ruleId+1).hasClass('fa-toggle-off')){
							let _paramObjVal = $('#input1').val();
							if(_ruleObj.transformationRule.ruleName=='General - Expression' && _paramObjVal.includes('\'')){
								_paramObjVal = _paramObjVal.replace(/'/g, '"');
							}
							_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
							_paramObj.value = _paramObjVal;
							_paramArray.push(_paramObj);
							_ruleExp = _ruleExp.replace('#var#',$('#input1').val());
							_ruleDesc = _ruleDesc.replace('#'+_ruleObj.transformationRule.parameter[0].name+'#',$('#input1').val());
						}else{
							_sourceEnAttr.push($('#select1').find('option:selected'));
							_ruleExp = _ruleExp.replace('#var#',$('#select1').val());
							_ruleDesc = _ruleDesc.replace('#'+_ruleObj.transformationRule.parameter[0].name+'#',$('#select1').val());
						}
						
						
						
					}
					break;
					
				case 2:
					_ruleExp = _ruleObj.expression;
					_ruleDesc = _ruleObj.transformationRule.ruleDescription;
					if($('.iTag'+ruleId+0).hasClass('fa-toggle-off')){
						let _paramObjVal = $('#input0').val();
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = _paramObjVal;
						_paramArray.push(_paramObj);
						_ruleExp = _ruleExp.replace('#var#',$('#input0').val());
						_ruleDesc = _ruleDesc.replace('#var#',$('#input0').val());
					}else{
						let _paramObjVal = $('#select0').val();
						_sourceEnAttr.push($('#select0').find('option:selected'));
						_ruleExp = _ruleExp.replace('#var#',$('#select0').val());
						_ruleDesc = _ruleDesc.replace('#var#',$('#select0').val());
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = _paramObjVal;
						_paramArray.push(_paramObj);
					}
					
					if($('.iTag'+ruleId+1).hasClass('fa-toggle-off')){
						let _paramObjVal = $('#input1').val();
						_paramObj = {};
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = _paramObjVal;
						_paramArray.push(_paramObj);
						_ruleExp = _ruleExp.replace('#var#',$('#input1').val());
						_ruleDesc = _ruleDesc.replace('#'+_ruleObj.transformationRule.parameter[0].name+'#',$('#input1').val());
					}else{
						let _paramObjVal = $('#select1').val();
						_sourceEnAttr.push($('#select1').find('option:selected'));
						_ruleExp = _ruleExp.replace('#var#',$('#select1').val());
						_ruleDesc = _ruleDesc.replace('#'+_ruleObj.transformationRule.parameter[0].name+'#',$('#select1').val());
						_paramObj = {};
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = _paramObjVal;
						_paramArray.push(_paramObj);
					}
					
					if($('.iTag'+ruleId+2).hasClass('fa-toggle-off')){
						let _paramObjVal = $('#input2').val();
						_paramObj = {};
						_paramObj.name = _ruleObj.transformationRule.parameter[1].name;
						_paramObj.value = _paramObjVal;
						_paramArray.push(_paramObj);
						_ruleExp = _ruleExp.replace('#var#',$('#input2').val());
						_ruleDesc = _ruleDesc.replace('#'+_ruleObj.transformationRule.parameter[1].name+'#',$('#input2').val());
					}else{
						let _paramObjVal = $('#select2').val();
						_sourceEnAttr.push($('#select2').find('option:selected'));
						_ruleExp = _ruleExp.replace('#var#',$('#select2').val());
						_ruleDesc = _ruleDesc.replace('#'+_ruleObj.transformationRule.parameter[1].name+'#',$('#select2').val());
						_paramObj = {};
						_paramObj.name = _ruleObj.transformationRule.parameter[1].name;
						_paramObj.value = _paramObjVal;
						_paramArray.push(_paramObj);
					}
					break;
					
				case 3:
					console.log(_ruleObj);
					_ruleDesc = _ruleObj.transformationRule.ruleDescription;
					_ruleExp = _ruleObj.expression;
					if(_ruleObj.transformationRule.ruleName=='Check - Condition'){
						for(let p in _ruleObj.transformationRule.parameter){
							_paramObj = {};
							_paramObj.name = _ruleObj.transformationRule.parameter[p].name;
							let _paramObjVal =  $('#input'+p).val();
							if(_paramObjVal.includes('\'')){
								_paramObjVal = _paramObjVal.replace(/'/g, '"');
							}
							if(_paramObj.name == 'Return Value If True' || _paramObj.name == 'Return Value If False'){
								if(_paramObjVal.includes('"')){
									_paramObj.value = _paramObjVal;
								}else{
									_paramObj.value = "\""+_paramObjVal+"\"";
								}
								_paramArray.push(_paramObj);
								_ruleDesc = _ruleDesc.replace('#var#',_sourceCol+'('+_sourceEnt+')').replace('#'+_paramObj.name+'#',_paramObj.value);
								_ruleExp = _ruleExp.replace('#var#',$('#select0').val()).replace('#'+_paramObj.name+'#',_paramObj.value);
								_sourceEnAttr = $('#select0').val();
							}else{
								_paramObj.value = _paramObjVal;
								_paramArray.push(_paramObj);
								_ruleDesc = _ruleDesc.replace('#var#',_sourceCol+'('+_sourceEnt+')').replace('#'+_paramObj.name+'#',_paramObj.value);
								_ruleExp = _ruleExp.replace('#var#',$('#select0').val()).replace('#'+_paramObj.name+'#',_paramObj.value);
								_sourceEnAttr = $('#select0').val();
							}
						}
					}else{
						for(let p in _ruleObj.transformationRule.parameter){
							_paramObj = {};
							_paramObj.name = _ruleObj.transformationRule.parameter[p].name;
							let _paramObjVal =  $('#input'+p).val();
							if(_paramObjVal.includes('\'')){
								_paramObjVal = _paramObjVal.replace(/'/g, '"');
							}
							_paramObj.value = _paramObjVal;
							_paramArray.push(_paramObj);
							_ruleDesc = _ruleDesc.replace('#var#',_sourceCol+'('+_sourceEnt+')').replace('#'+_paramObj.name+'#',_paramObj.value);
							_ruleExp = _ruleExp.replace('#var#',$('#select0').val()).replace('#'+_paramObj.name+'#',_paramObj.value);
							_sourceEnAttr = $('#select0').val();
						}
					}
					break;
					
				case 4 :
					
					_paramObj = {};
					_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
					_paramObj.value = $('#rankCol'+_id).val();
					_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#'+_paramObj.name+'#',_paramObj.value);
					_paramArray.push(_paramObj);
					
					_paramObj = {};
					_paramObj.name = _ruleObj.transformationRule.parameter[1].name;
					_paramObj.value = $('#noRanks'+_id).val();
					_ruleDesc = _ruleDesc.replace('#'+_paramObj.name+'#',_paramObj.value);
					_paramArray.push(_paramObj);
					_paramObj = {};
					
					_paramObj.name = _ruleObj.transformationRule.parameter[2].name;
					_paramObj.value = $('#topBot'+_id).val();
					_ruleDesc = _ruleDesc.replace('#'+_paramObj.name+'#',_paramObj.value);
					_paramArray.push(_paramObj);
					_paramObj = {};
					
					_paramObj.name = _ruleObj.transformationRule.parameter[3].name;
					_paramObj.value = $('#groupBy'+_id).val();
					_ruleDesc = _ruleDesc.replace('#'+_paramObj.name+'#',_paramObj.value);
					_paramArray.push(_paramObj);
					
					_type = 'Special';
					break;
				}
			}
			_ruleJson = {
					'ruleName' : _ruleName,
					'transComponentName' : _ruleObj.transformationType,
					'parameter' :  _paramArray,
					'type' : _type,
					'ruleDescription' :  _ruleDesc,
					'ruleExpression'  : _ruleExp,
					'sourceEnAttr' : _sourceEnAttr
			}
		}*/
		
	}
	return transformationRule;
}

/*function getRuleDetailsOnApply(_selectedRule, _sourceEnt, _sourceCol, _id){
	let _paramCount = '', _ruleMappings = '', _ruleJson = '', _type = '', _ruleDesc = '';
	_ruleMappings = document.querySelector('#ruleMappings').textContent;
	const _ruleMapObj = JSON.parse(_ruleMappings);
	for(let i = 0, len = _ruleMapObj.ruleMapping.length; i < len; i++){
		let _ruleObj = _ruleMapObj.ruleMapping[i];
		let _ruleName = _ruleObj.transformationRule.ruleName;
		_ruleName = _ruleName.substring(_ruleName.indexOf('-')+1, _ruleName.length).trim();
		_type = _ruleObj.transformationRule.ruleName.substring(0,_ruleObj.transformationRule.ruleName.indexOf('-')).trim();
		if(_selectedRule.toLowerCase() === _ruleName.toLowerCase()){
			let _paramObj = {};
			let _paramArray = [];
			let _ruleExp = '', _sourceEnAttr = '';
			_paramCount = _ruleObj.transformationRule.parameter.length;
			switch(_paramCount){
				
				case 0 :
					if(_ruleName == 'Concatenation'){
						if(_sourceEnt == null || _sourceCol == null){
							_paramObj.name = 'concatColumn';
							_paramObj.value = $('#select'+(+_id+1)).val();
							_paramArray.push(_paramObj);
							_sourceEnAttr = $('#select'+_id).val();
							_ruleExp = _ruleObj.expression.replace('#var2#',$('#select'+(+_id+1)).val()).replace('#var1#',$('#select'+_id).val());
						}else{
							_paramObj.name = 'concatColumn';
							_paramObj.value = $('#select1').val();
							_paramArray.push(_paramObj);
							_ruleDesc = `Concatenate ${_sourceCol} (${_sourceEnt}) and ${_paramObj.value}`;
						}
					}else{
						if(_sourceEnt == null || _sourceCol == null){
							_sourceEnAttr = $('#select'+_id).val();
							_ruleExp = _ruleObj.expression.replace('#var#',$('#select'+_id).val());
						}else{
							_paramObj = {};
							_paramArray.push(_paramObj);
							_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',_sourceCol+'('+_sourceEnt+')');
						}
					}
					break;
				
				case 1 :
					if(_ruleObj.transformationRule.parameter[0].name == 'Group By'){
						let groupByCols = [];
						$('#addedGroupByColumns').find('option:selected').each(function(){
							groupByCols.push($(this).text());
						});
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = groupByCols;
						_paramArray.push(_paramObj);
						if(_sourceEnt == null || _sourceCol == null){
							_sourceEnAttr = $('#select'+_id).val();
							_ruleExp = _ruleObj.expression.replace('#var#',$('#select'+_id).val());
						}else{
							_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',_sourceCol+'('+_sourceEnt+') group by '+groupByCols.join(','));
						}
						
					}else{
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = $('#input'+_id).val();
						_paramArray.push(_paramObj);
						if(_sourceEnt == null || _sourceCol == null){
							_sourceEnAttr = $('#select'+_id).val();
							_ruleExp = _ruleObj.expression.replace('#var#',$('#select'+_id).val());
						}else{
							_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',_sourceCol+'('+_sourceEnt+')').replace('#'+_paramObj.name+'#',_paramObj.value);
						}
					}
					break;
					
				case 2 :
					_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',_sourceCol+'('+_sourceEnt+')');
					_ruleExp = _ruleObj.expression;
					if(_sourceEnt == null || _sourceCol == null){
						_sourceEnAttr = $('#select'+_id).val();
						for(let p = 0; p < _ruleObj.transformationRule.parameter.length; p++){
							_paramObj = {};
							_paramObj.name = _ruleObj.transformationRule.parameter[p].name;
							_paramObj.value = $('#input'+p).val();
							_paramArray.push(_paramObj);
							_ruleExp = _ruleExp.replace('#var#',$('#select'+_id).val()).replace('#'+_ruleObj.transformationRule.parameter[p].name+'#',$('#input'+p).val());
						}
					}else{
						for(let p = 0; p < _ruleObj.transformationRule.parameter.length; p++){
							_paramObj = {};
							_paramObj.name = _ruleObj.transformationRule.parameter[p].name;
							_paramObj.value = $('#input'+p).val();
							_paramArray.push(_paramObj);
							_ruleDesc = _ruleDesc.replace('#'+_paramObj.name+'#',_paramObj.value);
						}
						
					}
					
					break;
				
				case 3 :
					_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#var#',_sourceCol+'('+_sourceEnt+')');
					if(_sourceEnt == null || _sourceCol == null){
						_sourceEnAttr = $('#select'+_id).val();
						for(let p = 0; p < _ruleObj.transformationRule.parameter.length; p++){
							_paramObj = {};
							_paramObj.name = _ruleObj.transformationRule.parameter[p].name;
							_paramObj.value = $('#input'+p).val();
							_paramArray.push(_paramObj);
							_ruleExp = _ruleExp.replace('#var#',$('#select'+_id).val()).replace('#'+_ruleObj.transformationRule.parameter[p].name+'#',$('#input'+p).val());
						}
					}else{
						for(let p = 0; p < _ruleObj.transformationRule.parameter.length; p++){
							_paramObj = {};
							_paramObj.name = _ruleObj.transformationRule.parameter[p].name;
							_paramObj.value = $('#input'+p).val();
							_paramArray.push(_paramObj);
							_ruleDesc = _ruleDesc.replace('#'+_paramObj.name+'#',_paramObj.value);
						}
					}
					
					break;
				
				case 4 :
					//_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#Rank Port#',_sourceCol+'('+_sourceEnt+')');
					
					_paramObj = {};
					_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
					_paramObj.value = $('#rankCol'+_id).val();
					_ruleDesc = _ruleObj.transformationRule.ruleDescription.replace('#'+_paramObj.name+'#',_paramObj.value);
					_paramArray.push(_paramObj);
					
					_paramObj = {};
					_paramObj.name = _ruleObj.transformationRule.parameter[1].name;
					_paramObj.value = $('#noRanks'+_id).val();
					_ruleDesc = _ruleDesc.replace('#'+_paramObj.name+'#',_paramObj.value);
					_paramArray.push(_paramObj);
					_paramObj = {};
					
					_paramObj.name = _ruleObj.transformationRule.parameter[2].name;
					_paramObj.value = $('#topBot'+_id).val();
					_ruleDesc = _ruleDesc.replace('#'+_paramObj.name+'#',_paramObj.value);
					_paramArray.push(_paramObj);
					_paramObj = {};
					
					_paramObj.name = _ruleObj.transformationRule.parameter[3].name;
					_paramObj.value = $('#groupBy'+_id).val();
					_ruleDesc = _ruleDesc.replace('#'+_paramObj.name+'#',_paramObj.value);
					_paramArray.push(_paramObj);
					
					_type = 'Special';
			}
			_ruleJson = {
					'ruleName' : _ruleName,
					'transComponentName' : _ruleObj.transformationType,
					'parameter' :  _paramArray,
					'type' : _type,
					'ruleDescription' :  _ruleDesc,
					'ruleExpression'  : _ruleExp,
					'sourceEnAttr' : _sourceEnAttr
			}
		}
	}
	console.log(_ruleJson);
	return _ruleJson;
}*/

function getRuleDescForOldRules(_srcAtrRefList, _parameter, _selectedRule){
	_parameter = JSON.parse(_parameter);
	console.log(_parameter);
	let  _ruleMappings = '', _type = '', _updatedRuleDesc = '', _retObj = {};
	_ruleMappings = document.querySelector('#ruleMappings').textContent;
	const _ruleMapObj = JSON.parse(_ruleMappings);
	for(let i = 0, len = _ruleMapObj.length; i < len; i++){
		let _ruleObj = _ruleMapObj[i];
		let _ruleName = _ruleObj.transformationRule.ruleName;
		_ruleName = _ruleName.substring(_ruleName.indexOf('-')+1, _ruleName.length).trim();
		_type = _ruleObj.transformationRule.ruleName.substring(0,_ruleObj.transformationRule.ruleName.indexOf('-')).trim();
		if(_selectedRule.toLowerCase() == 'lookup')
			continue;
		if(_selectedRule.toLowerCase() === _ruleName.toLowerCase()){
			let _ruleDesc = _ruleObj.transformationRule.ruleDescription;
			console.log(_ruleDesc);
			let _paramCount;
			if(_ruleObj.transformationRule.parameter == null)
				_paramCount = 0;
			else
				_paramCount = _ruleObj.transformationRule.parameter.length;
			let _paramArr = [], _paramObj={};
			switch(_paramCount){
				case 0 :
					if(_selectedRule == 'Concatenation'){
						_updatedRuleDesc = `Concatenate ${_srcAtrRefList[0].sourceDataElementRef} (${_srcAtrRefList[0].sourceFeedRef}) and ${_srcAtrRefList[1].sourceDataElementRef} (${_srcAtrRefList[1].sourceFeedRef})`;
						_retObj._updatedRuleDesc = _updatedRuleDesc;
						_retObj._type = _type;
					}else{
						_updatedRuleDesc = _ruleDesc.replace('#var#',_srcAtrRefList[0].sourceDataElementRef+' ('+_srcAtrRefList[0].sourceFeedRef+')');
						_retObj._updatedRuleDesc = _updatedRuleDesc;
						_retObj._type = _type;
					}
					break;
					
				case 1 : 
					if(_parameter.parameterValue!=undefined){
						if(_srcAtrRefList.length > 0){
							_updatedRuleDesc = _ruleDesc.replace('#var#',_srcAtrRefList[0].sourceDataElementRef+' ('+_srcAtrRefList[0].sourceFeedRef+')');
						}else{
							_updatedRuleDesc = _ruleDesc;
						}
						console.log(_ruleObj.transformationRule.parameter[0].name+"::::"+_parameter.parameterValue[0]);
						_updatedRuleDesc = _updatedRuleDesc.replace('#'+_ruleObj.transformationRule.parameter[0].name+'#', _parameter.parameterValue[0]);
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = _parameter.parameterValue[0];
						_paramArr.push(_paramObj);
						_retObj._updatedRuleDesc = _updatedRuleDesc;
						_retObj._paramValue = _paramArr;
						_retObj._type = _type;
					}
					if(_parameter.GroupBy!=undefined){
						if(_srcAtrRefList.length > 0){
							_updatedRuleDesc = _ruleDesc.replace('#var#',_srcAtrRefList[0].sourceDataElementRef+' ('+_srcAtrRefList[0].sourceFeedRef+')');
						}
						let groupByCols = _parameter.GroupBy.split(';');
						_paramObj.name = _ruleObj.transformationRule.parameter[0].name;
						_paramObj.value = groupByCols;
						_paramArr.push(_paramObj);
						_retObj._updatedRuleDesc = _updatedRuleDesc +' group by '+_parameter.GroupBy.replace(';',',');
						_retObj._paramValue = _paramArr;
						_retObj._type = _type;
					}
					break;
				
				case 2 : 
					_updatedRuleDesc = _ruleDesc.replace('#var#',_srcAtrRefList[0].sourceDataElementRef+' ('+_srcAtrRefList[0].sourceFeedRef+')');
					for(let p in _ruleObj.transformationRule.parameter){
						_updatedRuleDesc = _updatedRuleDesc.replace('#'+_ruleObj.transformationRule.parameter[p].name+'#', _parameter.parameterValue[p]);
						_paramObj={};
						_paramObj.name = _ruleObj.transformationRule.parameter[p].name;
						_paramObj.value = _parameter.parameterValue[p];
						_paramArr.push(_paramObj);
					}
					_retObj._updatedRuleDesc = _updatedRuleDesc;
					_retObj._paramValue = _paramArr;
					_retObj._type = _type;
					break;
			}
		}
	}
	console.log(_retObj);
	return _retObj;
}

function getRuleConsole(index,ruleNo,type,event,source,option,logTargetEntity){
	
	let _variableType = 'auto-variable';
	
	let _div = 
	`<ul class="nav nav-tabs mt-2 ruleSection" id="ruleSection${index}">
		<li class="nav-item" id="${index}">
			<a class="nav-link" href="#predictiveTyping${index}">Predictive Typing</a>
		</li>
		<li class="nav-item">
			<a class="nav-link active" href="#manualSearch${index}">Manual Search</a>
		</li>
	</ul>
	<div class="none divCheck" id="ruleApplyDiv${index}">	
			<div id="predictiveTyping${index}" class="ulDiv none">
				<div class="row mt-4">
			    	<div class="col-md-10">
						<div class="form-group">
						    <textarea type="text" class="form-control form-control-sm preType" aria-describedby="textHelp" id="predTyping${index}" rows="3" placeholder="Describe your rule" ></textarea>
							<small id="textHelp" class="form-text text-muted">Start typing, for eg : Trim Leading and Trailing Spaces for Title</small>
						</div>
					</div>
					<div class="col-md-2">
						<button class="btn btn-sm btn-info mt-3" id="fetchBtn" onclick="getRuleDetails('${index}')" style="margin-top:-10px">Fetch</button>&ensp;<img src="${ctx}/resources/images/loader.gif" height="45" width="45" style="margin-top:-8px" class="loaderGenerate invisible">
					</div>
				</div>
				<table class="table table-bordered table-sm none mt-2" id="ruleTable${index}">
					<thead>
						<tr class="bg-info text-center text-light">
							<td>Selected Rule</td>
							<td>Rule Formula</td>
						</tr>
					</thead>
					<tbody id="ruleBody${index}">
					
					</tbody>
				</table>
				<div class="text-right">
					<button class="text-right btn btn-sm btn-info none" id="saveBt${index}" onclick="saveTrRule('${index}','${ruleNo}','${type}','${source}')">Save</button>
				</div>
			</div>
			
			<div id="manualSearch${index}" class="ulDiv">
				<div class="card-deck m-0 mt-3">
				    <div class="card card-string text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','String',null,'${source}')">
				    	<button class="btn btn-link p-0">
							<span class="fa-stack fa-2x" style="font-size:0.85em">
								<i class="fas fa-circle text-warning fa-stack-2x" id="String_i" style="opacity:0.3"></i>
								<i class="fas fa-font fa-stack-1x fa-inverse"></i>
							</span>
						</button>
					    <div class="card-body p-0 mb-1">
					      <p class="card-title text-muted heading" id="head${index}">String</p>
					    </div>
				   </div>
				   <div class="card card-date text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','Date',null,'${source}')">
				    	<button class="btn btn-link p-0">
							<span class="fa-stack fa-2x" style="font-size:0.85em">
								<i class="fas fa-circle text-danger fa-stack-2x" id="Date_i" style="opacity:0.4"></i>
								<i class="fas fa-calendar-alt fa-stack-1x fa-inverse"></i>
							</span>
						</button>
					    <div class="card-body p-0 mb-1">
					      <p class="card-title text-muted" id="head${index}">Date/Time</p>
					    </div>
				   </div>
				   <div class="card card-numeric text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','Numeric',null,'${source}')">
				    	<button class="btn btn-link p-0">
							<span class="fa-stack fa-2x" style="font-size:0.85em">
								<i class="fas fa-circle text-info fa-stack-2x" id="Numeric_i" style="opacity:0.3"></i>
								<i class="fas fa-sort-numeric-down fa-stack-1x fa-inverse"></i>
							</span>
						</button>
					    <div class="card-body p-0 mb-1">
					      <p class="card-title text-muted heading" id="head${index}">Numeric</p>
					    </div>
				   </div>
				   <div class="card card-aggregate text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','Aggregate',null,'${source}')">
				    	<button class="btn btn-link p-0">
							<span class="fa-stack fa-2x" style="font-size:0.85em">
								<i class="fas fa-circle text-success fa-stack-2x" id="Aggregate_i" style="opacity:0.3"></i>
								<i class="fas fa-plus-square fa-stack-1x fa-inverse"></i>
							</span>
						</button>
					    <div class="card-body p-0 mb-1">
					      <p class="card-title text-muted heading" id="head${index}">Aggregate</p>
					    </div>
				   </div>
				   <div class="card card-conversion text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','Conversion',null,'${source}')">
				    	<button class="btn btn-link p-0">
							<span class="fa-stack fa-2x" style="font-size:0.85em">
								<i class="fas fa-circle text-secondary fa-stack-2x" id="Conversion_i" style="opacity:0.3"></i>
								<i class="fas fa-exchange-alt fa-stack-1x fa-inverse"></i>
							</span>
						</button>
					    <div class="card-body p-0 mb-1">
					      <p class="card-title text-muted heading" id="head${index}">Convert</p>
					    </div>
				   </div>
				   <div class="card card-encoding text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','Encoding',null,'${source}')">
				    	<button class="btn btn-link p-0">
							<span class="fa-stack fa-2x" style="font-size:0.85em">
								<i class="fas fa-circle fa-stack-2x" id="Encoding_i" style="opacity:0.3;color:#12CBC4"></i>
								<i class="fas fa-lock fa-stack-1x fa-inverse"></i>
							</span>
						</button>
					    <div class="card-body p-0 mb-1">
					      <p class="card-title text-muted heading" id="head${index}">Encoding</p>
					    </div>
				   </div>
				   <div class="card card-check text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','Check',null,'${source}')">
				    	<button class="btn btn-link p-0">
							<span class="fa-stack fa-2x" style="font-size:0.85em">
								<i class="fas fa-circle fa-stack-2x" id="Check_i" style="opacity:0.3;color:#9b59b6"></i>
								<i class="fas fa-check fa-stack-1x fa-inverse"></i>
							</span>
						</button>
					    <div class="card-body p-0 mb-1">
					      <p class="card-title text-muted heading" id="head${index}">Check</p>
					    </div>
				   </div>
				   <div class="card card-special text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','Special','Data Flow','${source}')">
				    	<button class="btn btn-link p-0">
							<span class="fa-stack fa-2x" style="font-size:0.85em">
								<i class="fas fa-circle text-primary fa-stack-2x" id="Special_i" style="opacity:0.3"></i>
								<i class="fas fa-search fa-stack-1x fa-inverse"></i>
							</span>
						</button>
					    <div class="card-body p-0 mb-1">
					      <p class="card-title text-muted heading" id="head${index}">Others</p>
					    </div>
				   </div>
				   <div class="card card-general text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','General',null,'${source}')">
						    	<button class="btn btn-link p-0">
									<span class="fa-stack fa-2x" style="font-size:0.85em">
										<i class="fas fa-circle fa-stack-2x" id="General_i" style="opacity:0.3;color:#4e8240"></i>
										<i class="fas fa-pen-nib fa-stack-1x fa-inverse"></i>
									</span>
								</button>
							    <div class="card-body p-0 mb-1">
							      <p class="card-title text-muted heading" id="headCheck">General</p>
							    </div>
					</div>
					<div class="card card-audit text-center m-0 border-0 pointer card-sm" onclick="showList('${index}','Audit',null,'${source}')">
						    	<button class="btn btn-link p-0">
									<span class="fa-stack fa-2x" style="font-size:0.85em">
										<i class="fas fa-circle fa-stack-2x" id="Audit_i" style="opacity:0.3;color:#4E81DE"></i>
										<i class="fas fa-history fa-stack-1x fa-inverse"></i>
									</span>
								</button>
							    <div class="card-body p-0 mb-1">
							      <p class="card-title text-muted heading" id="headCheck">Audit</p>
							    </div>
					</div>
				</div>
				<div class="row">
					<div class="col-4">
						<div class="list-group" id="trUlList${index}" style="display:none;overflow:auto">
				  
						</div>
					</div>
					<div class="col-8">
						<div class="form-group">
						    <div class="row">
						    	<div class="col-md-6 none">
						    		<label for="transformationRule">Transformation Rule</label>
								    <select class="form-control form-control-sm" id="transformationRule${index}" onchange="showExpressionForTransformation('${index}','${_variableType}','${source}')">
								    	<option>--Select Transformation Rule--</option>
								    	${option}
								    </select>
						    	</div>
						    	<div class="col-md-12">
						    		<input type="text" id="searchRule${index}" onKeyup="filterTrRules('${index}','${source}')" class="form-control form-control-sm" placeholder="Search Rule"/>
						    	</div>
						    </div>
						  </div>
					   <div class="form-group mt-2">
					    <div class="row none" id="ruleSyntaxRow${index}">
					    	<div class="col-md-12">
					    		<label for="ruleSyntax${index}">Selected Rule Syntax</label>
							    <input type="text" class="form-control form-control-sm" id="ruleSyntax${index}" readonly/>
							    <input type="text" class="form-control form-control-sm" id="varType" value = "${logTargetEntity}" />
							    <input type="text" class="form-control form-control-sm" id="oldDerivedVariable"/>
					    	</div>
					    </div>
					  </div>
					  <div class="applyRule1 fnt12"></div>
					</div>
				</div>
			</div>
		</div>`
	
		let _div1 = `<div class="row mt-2 none" id="appRule${index}">
	    	<div class="col-md-12">
	    		<label for="appliedRuleUdv">Applied Rule <span class="text-danger none pl-4" id="mandatory">Mandatory field is empty.</span></label>
			    <div class="border p-1" id="appliedRule${index}"></div>
	    	</div>
	    	<div class="col-md-12">
	    		<label> </label>
	    	</div>
	    	<div class="col-md-12">
	    		<label> </label>
	    		<label>Rule Description : </label>
				<input type="text" class="form-control form-control-sm col-md-12" id="ruledescrptiontext"  placeholder="Enter Rule Description"/>
	    	</div>
	    </div>
	    <p class="none" id="ruleType${index}"></p>
	    <p id="requiredVar" class="fnt12" style="color:red;">Please Enter all mandatory Fields</p>
					    <div class="derivedDiv none">
					    <div class="row mt-3 mr-0 fnt12">
					    	<label  class="col-md-4">Data Type <span style="color: red;">*</span> : </label>
						    <select id="derivedDatatypeId" class="form-control form-control-sm col-md-8">
					    		<option value="char">char</option>
					    		<option value="string">string</option>
					    		<option value="text">text</option>
					    		<option value="nchar">nchar</option>
					    		<option value="nstring">nstring</option>
					    		<option value="ntext">ntext</option>
					    		<option value="binary">binary</option>
					    		<option value="binstring">binstring</option>
					    		<option value="bintext">bintext</option>
					    		<option value="bit">bit</option>
					    		<option value="tinyint">tinyint</option>
					    		<option value="smallint">smallint</option>
					    		<option value="usmallint">usmallint</option>
					    		<option value="number">number</option>
					    		<option value="unumber">unumber</option>
					    		<option value="long">long</option>
					    		<option value="ulong">ulong</option>
					    		<option value="smallmoney">smallmoney</option>
					    		<option value="money">money</option>
					    		<option value="decimal">decimal</option>
					    		<option value="char">char</option>
					    		<option value="double">double</option>
					    		<option value="float">float</option>
					    		<option value="real">real</option>
					    		<option value="date">date</option>
					    		<option value="time">time</option>
					    		<option value="datetime">datetime</option>
					    		<option value="date_tz">date_tz</option>
					    		<option value="smalldatetime">smalldatetime</option>
					    		<option value="_variableType">_variableType</option>
					    		<option value="UnknownType">UnknownType</option>
					    	</select>
					    </div>
					    
					    <div class="row  mt-2 mr-0" id="derVariableName">
					    	<label  class="col-md-4" >Variable Name <span style="color: red;padding-left: 5px;">*</span>  : </label>
				    		<input type="text" class="form-control form-control-sm col-md-8" id="derivedVariableName"  placeholder="Enter name"/>
				    	</div>
					    <div class="row  mt-2 mr-0">
					    	<label  class="col-md-4" >Precision <span style="color: red;padding-left: 5px;">*</span>  : </label>
				    		<input name="number" type="text" class="form-control form-control-sm col-md-8" id="derivedPrecisionId"  placeholder="Enter Precision of Derived Variable"/>
				    	</div>
				    	<div class="row mt-2 mr-0">
					    	<label class="col-md-4">Scale <span style="color: red;padding-left: 27px;">*</span>  : </label>
				    		<input name="number" type="text" class="form-control form-control-sm col-md-8" id="derivedScaleId"  placeholder="Enter Scale of Derived Variable"/>
				    	</div>
					    </div>
					    <br>
				    	 <div class="text-right">
			  	 <button class="btn btn-info btn-sm" onclick="saveTrRule('${index}','${ruleNo}','${type}','${source}')">Apply</button>
			  </div>
			  <div class="p-2"></div>`
		return _div+"@@"+_div1;
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

function toggleInput(el,id,options){
	if($(el).hasClass('fa-toggle-on')){
		$(el).removeClass('fa-toggle-on').addClass('fa-toggle-off');
		$(el).next().addClass('none');
		$(el).next().next().removeClass('none');
		//hiding and showing Radio buttons
		$(el).next().next().next().removeClass('none');
		$(el).next().next().next().next().removeClass('none');
		$(el).next().next().next().next().next().removeClass('none');
	}else{
		$(el).removeClass('fa-toggle-off').addClass('fa-toggle-on');
		$(el).next().removeClass('none');
		$(el).next().next().addClass('none');
		//Hiding and showing Radio buttons
		$(el).next().next().next().addClass('none');
		$(el).next().next().next().next().addClass('none');
		$(el).next().next().next().next().next().addClass('none');
	}
	/*if($(el).next().hasClass('select-t')){
		$(el).next().remove();
		$(el).after(`<input id="input${id}" style="width:50%!important" class="form-control ml-1 custom-width form-control-sm mt-2" />`);
	}else{
		$(el).next().remove();
		$(el).after(`<select id="select${id}" style="width:50%!important" class="select-t ml-1 custom-width form-control form-control-sm mt-2" >${options}</select>`);
	}*/
	
}

function addNewParamForConcat(el, id, paramInd, options, ruleId){
	let index = paramInd+1;
	let radioButtonsWithNone = `<div class="custom-control custom-radio custom-control-inline none ml-2" title="String Constant">
								  <input type="radio" class="custom-control-input" id="SCnone${index}" name="typeValueRadio${index}" value="SC" checked>
								  <label class="custom-control-label" for="SCnone${index}">SC</label>
								</div>
								
								<div class="custom-control custom-radio custom-control-inline none" title="Numeric Constant">
								  <input type="radio" class="custom-control-input" id="NCnone${index}" name="typeValueRadio${index}" value="NC">
								  <label class="custom-control-label" for="NCnone${index}">NC</label>
								</div>
								
								<div class="custom-control custom-radio custom-control-inline none" title="Expression">
								  <input type="radio" class="custom-control-input" id="EXPnone${index}" name="typeValueRadio${index}" value="EXP">
								  <label class="custom-control-label" for="EXPnone${index}">EXP</label>
								</div>`;
	let divVal = `<div class="inp-param param${index}"><i class="fas fa-toggle-on pointer text-info iTag${ruleId}${index}"></i><select id="select${index}" class="form-control form-control-sm custom-width ml-1 mr-1 mt-1" style="width:50%!important">${options}</select><input id="input${index}" class="form-control input-t none form-control-sm custom-width ml-1 mr-1 mt-2" style="width:50%!important" />${radioButtonsWithNone}<i class="pl-1 pointer fa fa-plus-circle text-info add${index}" title="Add another parameter"></i></div>`;
	let parent = $(el).parent();
	$(el).parent().after(divVal);
	console.log($(el).parent());
	document.querySelector('.add'+index).addEventListener('click',function(e){
		addNewParamForConcat($(e.target),id,index,options);
	});
	document.querySelector('.iTag'+ruleId+index).addEventListener('click',function(e){
		toggleInput($(e.target),id,options);
	});
	$(el).remove();
	$(parent).append('<i class="pl-1 pointer fa fa-times text-danger removeParam'+paramInd+'" title="Remove Parameter"></i>')
	document.querySelector('.removeParam'+paramInd).addEventListener('click',function(e){
		removeParamForConcat($(e.target));
	});
}

function removeParamForConcat(el){
	$(el).parent().remove();
}


