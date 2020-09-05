window.getRuleSyntaxFromRule = getRuleSyntaxFromRule;
window.getRuleDetailsForEdit = getRuleDetailsForEdit;
window.clickRule = clickRule;
window.getRuleConsole = getRuleConsole;
window.getRuleDetailsOnApply = getRuleDetailsOnApply;
window.getRuleDescForOldRules = getRuleDescForOldRules;
window.showList = showList;
window.allowClick = allowClick;
window.closeConsole = closeConsole;

export function getRuleSyntaxFromRule(selectedRule,sourceOpts, _selectedSource, _tool, _type){
	let ruleId = selectedRule.replace(/ /g, "-").replace('/','-');
	const _ruleMappings = document.querySelector('#ruleMappings').textContent;
	const _ruleMapObj = JSON.parse(_ruleMappings);
	console.log(ruleId);
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
			console.log(_divVal);
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
/*export function getRuleSyntaxFromRule(id, _sourceEnt, _sourceColumn, selectedRule, sourceOpts, _toolType) {
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

export function getRuleDetailsForEdit(_id, _sourceList, _selectedRule, _type, _parameter,optionTag){
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

export function getRuleDetailsOnApply(_selectedRule, _sourceEnt, _sourceCol, _id, typ){
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

/*export function getRuleDetailsOnApply(_selectedRule, _sourceEnt, _sourceCol, _id){
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

export function getRuleDescForOldRules(_srcAtrRefList, _parameter, _selectedRule){
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

export function getRuleConsole(index,ruleNo,type,event,source,option,logTargetEntity){
	
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


export function showList(id,type,type2, source){
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

export function allowClick(id,source){
	$('#trUlList'+id).find('a').click(function(){
		let title = $(this).attr('title');
		var val = $(this).text();
		val = val.substring(val.indexOf('-')+1,val.length).trim();
		$('#searchRule'+id).val(val);
		$('#ruleType'+id).text(title);
		filterTrRules(id,source);
	});
}



export function closeConsole(){
	document.querySelector('#addUDFConsole').style.width = '0px';
	$('#overlay').addClass('none');
}

export function toggleInput(el,id,options){
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

export function addNewParamForConcat(el, id, paramInd, options, ruleId){
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

export function removeParamForConcat(el){
	$(el).parent().remove();
}