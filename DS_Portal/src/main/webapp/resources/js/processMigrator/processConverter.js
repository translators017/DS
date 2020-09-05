var id = "";
var
		topCurrentHeight = 0,
		bottomCurrentHeight = 0,
		currentPosition = 0,
		newPosition = 0,
		direction = 'Released';
(function(){
	
	if($('body').hasClass('project-groups')){
		document.querySelector('.add-grp').addEventListener('click', function(){
			let form = document.createElement('form');
			form.action = ctx+'/home/migrate/process-migrator/process-converter/add-process';
			form.method = 'GET';
			document.body.append(form);
			form.submit();
		});
		
		let deleteGroupIcons = document.querySelectorAll('.delete-group');
			deleteGroupIcons.forEach(function(el){
				el.addEventListener('click', function(e){
				deleteConfirmation(e);
			});
		});
		
		/*let groupSels = document.querySelectorAll('.group-sel');
			groupSels.forEach(function(item){
			item.addEventListener('click', function(e){
			if($('input.group-sel:checked').length == 1){
				$("#run-sec").show();
				$("#run-sec").css('height', '30vh');
				$("#group-sec").css('height', '30vh');
				$('.resize').removeClass('none');
				allowResize();
				displayRunData(e.target.id);
				$('.conv-btn').attr('disabled', false);
			}else{
				$("#run-sec").hide();
				$("#run-sec").css('height', '30vh');
				$("#group-sec").css('height', '60vh');
				$('.resize').addClass('none');
				$('.conv-btn').attr('disabled', true);
			}
		});
		
	});*/
	
	document.querySelector('.conv-btn').addEventListener('click', function(){
		$('.convertFileInput').click();
		$('.convertFileInput').val(null);
	});
	document.querySelector('.convertFileInput').addEventListener('change', function(e){
		addFilesToQueue(e);
	});
		
		/*Split(['#group-sec', '#run-sec'], {
			direction : 'vertical',
			gutterSize :20,
			gutterAlign: 'center',
			snapOffset: 0,
			cursor: 'row-resize',
		    sizes: [50, 50],
			minSize: 200,
		});*/
		
		$('.custom-table').find('tr').click(function(e){
			e.stopPropagation();
			e.preventDefault();
			if($(this).hasClass('clicked')){
				$(this).removeClass('clicked');
				$("#run-sec").hide();
				$("#run-sec").css('height', '35vh');
				$("#group-sec").css('height', '60vh');
				$('.resize').addClass('none');
				$('.conv-btn').attr('disabled', true);
			}else{
				$(this).addClass('clicked').siblings().removeClass('clicked');
				$("#run-sec").show();
				$("#run-sec").css('height', '35vh');
				$("#group-sec").css('height', '30vh');
				$('.resize').removeClass('none');
				allowResize();
				displayRunData();
				$('.conv-btn').attr('disabled', false);
				$('a[href="#summary"]').click();
			}
			console.log($(this));
			$(this).find('td:eq(0) input').prop('checked', !$(this).find('td:eq(0) input').is(':checked'));
		});
		
		
		$('.login-popover').popover({
		    placement: 'left',
		    title: 'Select columns to view',
		    content: getCol(),
		    trigger: 'click',
		    html : true,
		    sanitize: false,
		  });

		
		$('.login-popover').on('inserted.bs.popover', function () {
		  	$('.popover-body').find('input').click(function(){
				if($(this).is(':checked')){
					updateTable($(this).attr('id'), 'show');
				}else{
					updateTable($(this).attr('id'), 'hide');
				}
			});
		})
		
		
		
		document.querySelector('.retry').addEventListener('click', function(){
			let selectedTr = $('#processRunDiv tbody tr.clicked');
			let group = getSelectedGroup();
			let runID = $(selectedTr).find('td:eq(1)').text();
			$(selectedTr).find('td:eq(3)').empty().html('Converting <i class="fa fa-spin fa-spinner"></i>');
			convertFile(runID,group);
		});
		
		$('.run-navs').find('li a').click(function(e){
			e.stopPropagation();
			e.preventDefault();
			$('.active').removeClass('active');
			$(this).addClass('active');
			$('.group-nav').addClass('none');
			let id = $(this).attr('href');
			$(id).removeClass('none');
			
			if(id == '#details'){
				getConfigDataForGroup();
			}
			if(id == '#metrics'){
				getMetricsDataForGroup();
			}
		});
		
		$("input[name=search-group]").keydown(function() {
	        setTimeout(function() {
	            $("td:eq(2)", "#processGroupBody>tr").each(function() {
	                let lookfor = $("input[name=search-group]").val();
	                if (new RegExp(lookfor, "i").test($(this).text()))
	                    $(this).parent().show();
	                else
	                    $(this).parent().fadeOut();
	            }, 0);
	
	        });
		});
		
		document.querySelector('.full-overlay-close').addEventListener('click', function(){
			document.querySelector('.full-overlay').classList.add('none');
		});
	
	}
	
	if($('body').hasClass('add-proc-view')){
		//addEventListener for source tech types
		let sourceTech = document.querySelectorAll('.source-sel input[name="radio-1"]');
		sourceTech.forEach(function(item){
				item.addEventListener('click', function(e){
				let value = e.target.value;
				updateScreen('source', value);
			})
		});
		
		let targetTech = document.querySelectorAll('.target-sel input[name="radio-2"]');
		targetTech.forEach(function(item){
				item.addEventListener('click', function(e){
				let value = e.target.value;
				updateScreen('target', value);
			})
		});
		
		FilePond.registerPlugin(
	  		FilePondPluginFileEncode,
			FilePondPluginFileValidateSize
		);
		
		const pond = FilePond.create(document.querySelector('#processConvFile'), {
	    	onaddfilestart: (file) => { isLoadingCheck(); },
	    	onprocessfile: (files) => { isLoadingCheck(); }
		});
		
		function isLoadingCheck(){
			//console.log(pond.getFiles().filter(x => console.log(x)));
		    let isLoading = pond.getFiles().filter(x=>x.status !== 5).length !== 0;
		    if(!isLoading) {
				$('#section-processConv-4').empty();
		        convertFile();
		    }
		}
	
		FilePond.setOptions({
		    server: {
				'url' : ctx+'/home/migrate/process-migrator/process-converter/uploadScript',
				process: {
		            headers: {
		                "X-CSRF-Token": $('meta[name="_csrf"]').attr('content')
		            }
	       	 	}
			}
			
		});
		
		//Is schema design done check event handler
		let mappingDoneCheck = document.querySelectorAll('input[name="mpdone"]');
		mappingDoneCheck.forEach(function(item){
			item.addEventListener('click', function(e){
				if(e.target.value === 'Yes'){
					document.querySelector('.map-done-div').style.display = 'block';
				}else{
					document.querySelector('.map-done-div').style.display = 'none';
				}
			});
		});
		
		let underlyingDbCheck = document.querySelectorAll('input[name="radio-ETLDB"]');
		underlyingDbCheck.forEach(function(item){
			item.addEventListener('click', function(e){
				if(e.target.value == 'Yes')
					$('.new-conn-div').removeClass('none');
				else
					$('.new-conn-div').addClass('none');
			});
		});
		
		document.querySelector('.save-btn').addEventListener('click', function(){
			validateAndSave();
		});
		
		document.querySelector('.add-conn').addEventListener('click', function(){
			let div = '';
			const connName = document.querySelector('#connName').value;
			const dbType = document.querySelector('#dbType').value;
			if(connName == ''){
				alert('Connection name is mandatory');
			}else{
				if(!validateIfExists(connName,dbType)){
					div = 
					`
					<div class="badge success-light d-flex flex-row ml-3" data-conn-name="${connName}" data-dbtype="${dbType}">
						<div class="d-flex align-items-center justify-content-center">
							<p class="text-light mb-1 fnt12">${connName}</p>
							<p class="text-light mb-1 fnt12 pl-1">(${dbType})</p>
						</div>
						<button type="button" class="badge-delete close pl-1" aria-label="Close">
						  <span aria-hidden="true">&times;</span>
						</button>
					</div>
					`
					$('.badge-conn').append(div);
					$('.badge-conn').find('div.badge:last button').click(function(){
						$(this).parent().remove();
					});
				}else{
					alert('Data has been already added. Please choose a different name or db type');
				}
			}	
			
		});
		
		document.querySelector('.side-console-close').addEventListener('click', function(){
			document.querySelector('.side-console').style.display = 'none';
			$('.custom-table').find('tr').each(function(){
				$(this).removeClass('clicked');
				$(this).find('input').prop('checked', false);
			});
		});
		
		$("input[name=search-current-catalog]").keydown(function() {
			
	        setTimeout(function() {
	            $("#catalogs .col-3").each(function() {
	                let lookfor = $("input[name=search-current-catalog]").val();
	                if (new RegExp(lookfor, "i").test($(this).find('div.custom-control label').text()))
	                    $(this).show();
	                else
	                    $(this).fadeOut();
	            }, 0);
	
	        });
	
	    });
		
		let sourceMap = new Map();
		sourceMap.set('Teradata', ['BTEQ:BTEQ', 'TPMP:TPMP', '(k)sh:(k)sh', 'Macro:Macro', 'Stored Procedure / PL SQL:PLSQL Proc', 'DDL(Table / Views):DDL(Table/Views)']);
		sourceMap.set('Oracle', ['Stored Procedure / PL SQL:PLSQL Proc', 'DDL(Table / Views):DDL(Table/Views)']);
		sourceMap.set('Netezza', ['Stored Procedure / PL SQL:PLSQL Proc', 'DDL(Table / Views):DDL(Table/Views)']);
		sourceMap.set('Vertica', ['Stored Procedure / PL SQL:PLSQL Proc', 'DDL(Table / Views):DDL(Table/Views)']);
		sourceMap.set('MySQL', ['Stored Procedure / PL SQL:PLSQL Proc', 'DDL(Table / Views):DDL(Table/Views)']);
		
		let targetMap = new Map();
		targetMap.set('Snowflake', ['Python QL', 'Query', 'DDL(Table / Views)', 'Macro', 'Stored Procedure / PL SQL (with control statements)', 'Cloud / Bigdata / ETL']);
		targetMap.set('AWS Redshift', ['Python QL', 'Query', 'DDL(Table / Views)', 'Macro', 'Stored Procedure / PL SQL (with control statements)', 'Cloud / Bigdata / ETL']);
		targetMap.set('Azure Synapse', ['Python QL', 'Query', 'DDL(Table / Views)', 'Stored Procedure / PL SQL (with control statements)', 'Cloud / Bigdata / ETL']);
		targetMap.set('GCP BigQuery', ['Python QL', 'Query', 'DDL(Table / Views)', 'Stored Procedure / PL SQL (with control statements)', 'Cloud / Bigdata / ETL']);
		
		document.querySelector('#sourceDb').addEventListener('change', function(e){
			updateSourceScriptValues(e,sourceMap);
		});
		
		$('#process-mig-tab nav ul').find('li a').click(function(e){
			e.preventDefault();
			e.stopPropagation();
			updateTabs(this);
		});
		
		function updateTabs(self){
			let id = $(self).attr('href');
			$(self).parent().parent().find('li.tab-current').removeClass('tab-current');
			$(self).parent().addClass('tab-current');
			$(self).parents().eq(3).find('div.content-wrap').find('section.content-current').removeClass('content-current');
			$(id).addClass('content-current');
		}
		
		let nextBtn = document.querySelector('.next-btn');
		nextBtn.addEventListener('click', function(){
			let href = $(this).data('href');
			$('a[href="'+href+'"]').click();
		});
	}
	
})();

function getNoOfCheckedRows(table){
	let count = 0;
	$('.'+table).find('tr').each(function(){
		if($(this).find('td:eq(0) input').is(':checked')){
			count = count + 1;
		}
	});
	return count;
}

function getCol(){
	let div = '', id='', checked="";
	$('#processRunDiv thead th').each(function(index, el){
		if(index > 0){
			id = $(this).text();
			checked = ($(this).hasClass('none')) ? '' : 'checked';
			div += `<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input" ${checked} id="${id}"><label class="custom-control-label" for="${id}">${id}</label></div>`	
		}
	});
	return div;
}

function updateTable(id, type){
	
	$('#processRunDiv thead th').each(function(index, el){
		if($(this).text() === id){
			if(type=='hide'){
				$(this).addClass('none');
				$('#processRunDiv tbody tr').each(function(){
					$(this).find('td:eq('+index+')').addClass('none');
				});
			}else{
				$(this).removeClass('none');
				$('#processRunDiv tbody tr').each(function(){
					$(this).find('td:eq('+index+')').removeClass('none');
				});
			}	
		}
	});
}

function updateSourceScriptValues(e,sourceMap){
	let currentScript = e.target.value;
	let sourceScripts = sourceMap.get(currentScript);
	console.log(sourceScripts);
	let options = '';
	for(let option of sourceScripts){
		options += `<option value="${option.split(':')[1]}">${option.split(':')[0]}</option>`;
	}
	$('#sourceScriptType').empty().append(options);
}


function addFilesToQueue(e){
	let selectedGroup = getSelectedGroup();
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let files = $(e.target).prop("files");
	console.log(files);
	let filenames = $.map(files, function(val) { return val.name; });
	console.log(filenames);
	$.ajax({
		url : ctx+'/home/migrate/process-migrator/process-converter/addFilesToDTO',
		type : 'post',
		data : {'filenames' : filenames.join(','), 'selectedGroup' : selectedGroup},
		headers: {'X-CSRF-Token': csrf_token },
		success:function(data){
			data = DOMPurify.sanitize(data);
			addSelectedFilesToRunTable(data,files);
		},error:function(){
			alert('Unable to upload file');
		}
	})
}

function addSelectedFilesToRunTable(data,files){
	console.log(files);
	let obj = JSON.parse(data);
	let div = '';
	let runID = '';
	$('#processRunDiv tbody tr.norecord').remove();
	for(let item of obj){
		runID = item.runId;
		div = 
			`<tr>
				<td></td>
				<td style="width:200px">${item.runId}</td>
				<td>${item.fileName}</td>
				<td style="width:150px">
					<div class="progress" style="height: 20px;">
				  		<div class="progress-bar progress-bar-striped success-light progress-bar-animated" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
					</div>
					<span><small>uploading</small></span>
				</td>
				<td class="none"></td>
				<td>${item.startTime}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="none"></td>
				<td class="none"></td>
				<td style="width:100px"><i class="far fa-file-alt pr-3 lead pointer" disable title="Download report log"></i><i class="far fa-file-archive lead pointer" disable title="Download generated Code"></i></td>
			</tr>`
	$('#processRunDiv tbody').prepend(div);
	uploadFilesToDB(runID, files, item.fileName)
	}
}

function uploadFilesToDB(runID, files, fileName){
	let selectedGroup = getSelectedGroup();
	//uploadFiles
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let oForm = new FormData();
	for (let i = 0, numFiles = files.length; i < numFiles; i++) {
		if(files[i].name === fileName)
			oForm.append("files", files[i]);
	}
	
	$.ajax({
	  	xhr: function() {
	            var xhr = $.ajaxSettings.xhr();
	            if (xhr.upload) {
	                xhr.upload.addEventListener('progress', function(evt) {
	                    var percent = Math.floor((evt.loaded / evt.total) * 100);
						$('.progress-bar').each(function(){
							$(this).css('width',percent+'%');
							$(this).text(percent +"%");
						});
						if(percent == 100){
							$('#processRunDiv tbody').find('tr').each(function(){
								if($(this).find('td:eq(0)').text() == runID){
									$(this).find('td:eq(2)').empty().html('Converting <i class="fa fa-spin fa-spinner"></i>');
								}
							});
						}
	                }, false);
	            }
	            return xhr;
	      },
		  url: ctx + `/home/migrate/process-migrator/process-converter/${selectedGroup}/${runID}/uploadScript`,
		  type: "POST",
		  headers: {'X-CSRF-Token': csrf_token },
		  data: oForm,
	      processData: false,
	      contentType: false,
		  success: function(result) {
		    convertFile(runID,selectedGroup);
		  },error:function(data){
			
		}
	});
}

function getSelectedGroup(){
	let groupName = $('#processGroupBody tr.clicked').data('groupname')
	return groupName;
}

function allowResize(){
	$('body').mouseup(function() {
		direction = 'Released';
	});

	$('body').mousemove(function(event) {
		SetDivPosition();
	});
	
	$('.resize').mousedown(function(event) {
		getDivPosition();
	});

}

function getDivPosition(mouse) {
		
	direction = 'Pressed';
	currentPosition = event.pageY;
	topTempHeight = $('#group-sec').css('height');
	topHeightArray = topTempHeight.split('p');
	topCurrentHeight = parseInt(topHeightArray[0]);
	bottomTempHeight = $('#run-sec').css('height');
	bottomHeightArray = bottomTempHeight.split('p');
	bottomCurrentHeight = parseInt(bottomHeightArray[0]);
}

function SetDivPosition(mouse) {
	if (direction=='Pressed') {
		newPosition = event.pageY;
		console.log(newPosition);
		var movePerPixels = parseInt(newPosition - currentPosition);
		var topDivNewLocation = parseInt(topCurrentHeight + movePerPixels);
		if (topDivNewLocation < 10) {
			$('#group-sec').css('height', '20px');
		}
		var bottomDivNewLocation = parseInt(bottomCurrentHeight - movePerPixels);
		if (bottomDivNewLocation < 10) {
			$('#run-sec').css('height', '20px');
		}
		else {
			$('#group-sec').css('height', topDivNewLocation + 'px');
			$('#run-sec').css('height', bottomDivNewLocation + 'px');
		}
	}
}

function displayRunData(){
	let groupName = getSelectedGroup();
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	$.ajax({
		url : ctx + '/home/migrate/process-migrator/process-converter/getRunDetails',
		data : {'groupName' : groupName},
		type : 'get',
		headers: {'X-CSRF-Token': csrf_token },
		success : function(data){
			data = DOMPurify.sanitize(data);
			showRunDetailsForGroup(data);
		},error:function(){
			alert('Unable to get run details');
		}
	});
}

function showRunDetailsForGroup(data){
	let div = "";
	let obj = JSON.parse(data);
	console.log(obj);
	let color = '', disableClass = '', disable='';
	$('#processRunDiv tbody').empty();
	if(obj.length == 0){
		$('#processRunDiv tbody').empty().append('<tr class="norecord"><td colspan="8">No records available</td></tr>')
	}else{
		for(let item of obj){
			
			color =	(item.status == 'success' ? 'text-success' : 'text-danger');
			disable = (item.status == 'success' ? '' : 'disabled');
			disableClass =	(item.status == 'success' ? '' : 'disable');
			div += 
				`<tr>
					<td>
						<div class="custom-control custom-radio">
							<input type="radio" class="custom-control-input rungroups" ${disable} name="rungroups" id="${item.runId}">
							<label class="custom-control-label" for="${item.runId}"></label> 
						</div>
					</td>
					<td style="max-width:150px" class="text-truncate" title="${item.runId}">${item.runId}</td>
					<td style="max-width:100px" class="text-truncate" title="${item.fileName}">${item.fileName}</td>
					<td style="width:150px" class="${color}"><strong>${item.status}</strong></td>
					<td class="none"</td>
					<td style="max-width:100px" class="text-truncate" title="${item.startTime}">${item.startTime}</td>
					<td style="max-width:100px" class="text-truncate" title="${item.endTime}">${item.endTime}</td>
					<td>${item.complexityType!=null ? item.complexityType : ""}</td>
					<td>${item.automationPercentage!=null ? item.automationPercentage : ""}</td>
					<td>${item.sqlQueryCount!=null ? item.sqlQueryCount : ""}</td>
					<td>${item.entityTransformationCount!=null ? item.entityTransformationCount : ""}</td>
					<td>${item.attributeTransformationCount!=null ? item.attributeTransformationCount : ""}</td>
					<td class="none">${item.totalTransformationCount!=null ? item.totalTransformationCount : ""}</td>
					<td class="none">${item.unsuccessfulTransformationCount!=null ? item.unsuccessfulTransformationCount : ""}</td>
					<td style="width:120px"><i class="far fa-file-alt pr-3 reportLog lead pointer ${disableClass}" title="Download report log"></i><i class="far fa-file-archive lead pointer downloadCode ${disableClass}" title="Download generated Code"></i></td>
				</tr>`
		}
		$('#processRunDiv tbody').append(div);
		let reportLog = document.querySelectorAll('.reportLog');
		reportLog.forEach(function(el){
			el.addEventListener('click', function(e){
				e.stopPropagation();
				downloadReportLog(e);
			})
		});
				
		let downloadCodeIcon = document.querySelectorAll('.downloadCode');
		downloadCodeIcon.forEach(function(el){
			el.addEventListener('click', function(e){
				e.stopPropagation();
				downloadCode(e);
			})
		});
		$('#processRunDiv tbody').find('tr').click(function(e){
			e.stopPropagation();
			e.preventDefault();
			if($(this).hasClass('clicked')){
				$(this).removeClass('clicked');
				$('.extra-options i:nth-child(2)').addClass('disable');
			}else{
				$(this).addClass('clicked').siblings().removeClass('clicked');
				showOutputComparison();
				$('.extra-options i:nth-child(2)').removeClass('disable');
			}
			$(this).find('td:eq(0) input').prop('checked', !$(this).find('td:eq(0) input').is(':checked'));
			
		});
	}
}

function validateIfExists(connName, dbType){
	let isExist = false;
	$('.badge-conn').find('div.badge').each(function(){
		if($(this).data('conn-name') == connName && $(this).data('dbtype') == dbType)
			isExist = true;
	});
	return isExist;
}

function updateScreen(type, value){
	$('.data-expose-'+type).each(function(){
		console.log($(this).data('expose'));
		if($(this).data('expose').includes(value))
			$(this).removeClass('none');
		else
			$(this).addClass('none');
	});
	if(type == 'target'){
		if($('#radio-etl-1').is(':checked') && $('#radio-etl-2').is(':checked')){
			//source etl is selected
			$('.new-conn-tbl').removeClass('none');
			fillNewConnectionTable();
		}else
			$('.new-conn-tbl').addClass('none');
	}
}

function fillNewConnectionTable(){
	let div = '', currentConnName = '', dbType='', schemaMapOptions='', optionVal;
	$('#schemaMappingName').children().each(function(){
		optionVal = $(this).text();
		schemaMapOptions += `<option value="${optionVal}">${optionVal}</option>`;
	});
	if($('.badge-conn').find('div.badge').length == 0){
		$('#new-conn-dtls').append('<tr><td colspan="5">Current connection details is not selected.</td></tr>')
	}
	$('.badge-conn').find('div.badge').each(function(){
		currentConnName = $(this).data('conn-name');
		dbType = $(this).data('dbtype');
		div += 
			`
			<tr>
				<td>${currentConnName}</td>
				<td>${dbType}</td>
				<td><input type="text" class="form-control" placeholder="Enter new connection" /></td>
				<td>
					<select class="form-control">
						<option>Teradata</option>
						<option>Oracle</option>
					</select>
				</td>
				<td><select class="form-control">${schemaMapOptions}</select></td>
			</tr>
			`		
	});
	$('#new-conn-dtls').empty().append(div);
}

function getConsoleData(){
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	$.ajax({
      type:"get",
	  async : true,
      url: ctx+"/home/console",
      cache : false,
	  headers: {'X-CSRF-Token': csrf_token },
      success:function(data){
		data = DOMPurify.sanitize(data);
		if (typeof String.prototype.trim !== 'function') {
			String.prototype.trim = function() {
				return this.replace(/^\s+|\s+$/g, '');
			};
		}

		if (data != "" && data != null) {
			document.getElementById("section-processConv-4").innerHTML = '<div class="m-0 fnt-15 text-dark text-left font-weight-normal">'
					+ data + '</div>';
			$('#section-processConv-4').append(`<p class="fnt-15 text-left text-dark font-weight-normal">Your downloaded should start in a few seconds. If not<a href="${ctx}/home/migrate/process-migrator/process-converter/downloadConvertedFile"> click to download</a></span>`);
		}
      }
    });
}



function convertFile(runID, selectedGroup){
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	$.ajax({								
		url: ctx+`/home/migrate/process-migrator/process-converter/${selectedGroup}/${runID}/convertScript`,
		headers: {'X-CSRF-Token': csrf_token },
		type: 'POST',
		success: function(data){
			data = DOMPurify.sanitize(data);
			updateRunTable(data,runID, selectedGroup);
		},error:function(data){
			alert('Unable to convert file. Please try again later. '+data);
			$('#processRunDiv tbody').find('tr').each(function(){
				if($(this).find('td:eq(0)').text() == runID){
					$(this).find('td:eq(7) i').addClass('disable');
					$(this).find('td:eq(2)').text('failed');
					$(this).find('td:eq(2)').addClass('font-weight-bold text-danger');
				}
			});
		},
	});	

}

function updateRunTable(data,runID, selectedGroup){
	let obj = JSON.parse(data);
	console.log(obj);
	let color = (obj.status == 'success' ? 'text-success' : 'text-danger');
	let disable = (obj.status == 'success' ? '' : 'disabled');
	$('#processRunDiv tbody').find('tr').each(function(){
		if($(this).find('td:eq(1)').text() == runID){
			$(this).find('td:eq(0)').html(`<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input rungroups" ${disable} name="rungroups" id="${runID}">
							<label class="custom-control-label" for="${runID}"></label> 
						</div>`)
			$(this).find('td:eq(3)').text(obj.status);
			$(this).find('td:eq(3)').addClass('font-weight-bold '+color);
			$(this).find('td:eq(6)').text(obj.endTime);
			$(this).find('td:eq(7)').text(obj.complexityType!=null ? obj.complexityType : "");
			$(this).find('td:eq(8)').text(obj.automationPercentage!=null ? obj.automationPercentage : "");
			$(this).find('td:eq(9)').text(obj.sqlQueryCount!=null ? obj.sqlQueryCount : "");
			$(this).find('td:eq(10)').text(obj.entityTransformationCount!=null ? obj.entityTransformationCount : "");
			$(this).find('td:eq(11)').text(obj.attributeTransformationCount!=null ? obj.attributeTransformationCount : "");
			$(this).find('td:eq(12)').text(obj.totalTransformationCount!=null ? obj.totalTransformationCount : "");
			$(this).find('td:eq(13)').text(obj.unsuccessfulTransformationCount!=null ? obj.unsuccessfulTransformationCount : "");
			if(obj.status == 'success'){
				$(this).find('td:eq(14) i').removeClass('disable');
				$(this).find('td:eq(14) i:first-child').click(function(e){
					e.stopPropagation();
					downloadReportLog(e);
				});
				$(this).find('td:eq(14) i:nth-child(2)').click(function(e){
					e.stopPropagation();
					downloadCode(e);
				});
			}else{
				$(this).find('td:eq(14) i').addClass('disable');
			}
				
		}
	});
	if($('#processGroupBody tr.clicked').data('groupname') == selectedGroup){
		$('#processGroupBody tr.clicked').find('td:eq(5)').text(obj.endTime);
	}
	
	
	$('#processRunDiv tbody').find('tr').click(function(e){
		e.stopPropagation();
		e.preventDefault();
		if($(this).hasClass('clicked')){
			$(this).removeClass('clicked');
		}else{
			$(this).addClass('clicked').siblings().removeClass('clicked');
		}
		$(this).find('td:eq(0) input').prop('checked', !$(this).find('td:eq(0) input').is(':checked'));
		showOutputComparison();	
		$('.extra-options').find('i.retry').toggle();
	});
}

function showSummary(data){
	let div = '', automationClass="text-success";
	let obj = JSON.parse(data);
	for(let i = 0; i < obj.length; i++){
		if(obj[i].automationPercentage >= 60 && obj[i].automationPercentage < 80)
			automationClass = text-warning;
		if(obj[i].automationPercentage < 60)
			automationClass = text-danger;
		div += 
			`<tr>
				<td>
					<div class="custom-control custom-radio custom-control-inline">
					   <input type="radio" id="${obj[i].scriptName}" name="summaryRadio" class="custom-control-input">
					   <label class="custom-control-label fnt-15" for="${obj[i].scriptName}"></label>
					</div>
				</td>
				<td class="text-left fnt-15">${obj[i].scriptName}</td>
				<td class="text-left fnt-15">${obj[i].complexityType}</td>
				<td class="text-left fnt-15 font-weight-bold ${automationClass}">${obj[i].automationPercentage}</td>
			</tr>`
	}
	
	$('.summaryTable').find('tbody').append(div);
	
	$('.custom-table').find('tr').click(function(){
		$(this).addClass('clicked').siblings().removeClass('clicked');
		if($(this).find('td:eq(0) input').length > 0){
			if($(this).find('td:eq(0) input').is(':checked')){
				$(this).find('td:eq(0) input').prop('checked', false);
				$(this).removeClass('clicked');
			}	
			else{
				$(this).find('td:eq(0) input').prop('checked', true);
				let name = $(this).find('td:eq(1)').text();
				showMoreDetails(data,name);
			}
	
		}
	});
	let downloadButton = document.querySelector('.download-output');
	downloadButton.disabled = false;
	downloadButton.addEventListener('click', function(){
		downloadConvertedFile();
	});
}

function showMoreDetails(data,name){
	let extraDetailsDiv = '';
	let obj = JSON.parse(data);
	for(let i = 0; i < obj.length; i++){
		if(obj[i].scriptName === name)
		extraDetailsDiv = 
			`<h6 class="font-weight-bold">File name : ${obj[i].scriptName}</h6><hr />
			<p class="mb-3">Current Technology - <strong>${obj[i].currentDb}</strong></p>
			<p class="mb-3">New Technology - <strong>${obj[i].newDB}</strong></p>
			<p class="mb-3">No of SQL Queries - <strong>${obj[i].sqlQueries}</strong></p>
			<p class="mb-3">No of Entity Transformations - <strong>${obj[i].entTr}</strong></p>
			<p class="mb-3">No of Attribute Transformations - <strong>${obj[i].attrTr}</strong></p>
			<p class="mb-3">Total No of Transformations - <strong>${obj[i].totalTr}</strong></p>
			<p class="mb-3">Complexity Type - <strong>${obj[i].complexityType}</strong></p>
			<p class="mb-3">Automation Percentage - <strong>${obj[i].automationPercentage}</strong></p>
			<p class="mb-3">Number of Transformation to be changed Manually - <strong>${obj[i].manualTr}</strong></p>
			
			`
		$('.side-console-body').empty().append(extraDetailsDiv);
	}
	$('.side-console').show();
}

function downloadCode(e){
	let runID = $(e.target).parents().eq(1).find('td:eq(1)').text();
	let selectedGroup  = getSelectedGroup();
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let csrf_name = $('meta[name="_csrf_parameter_name"]').attr('content');
	let form = document.createElement('form');
	form.action = ctx+`/home/migrate/process-migrator/process-converter/${selectedGroup}/${runID}/downloadCode`;
	form.method = 'post';
	form.innerHTML = `<input type="hidden" name="${csrf_name}"value="${csrf_token}" />`;
	document.body.append(form);
	form.submit();
}

function downloadBulk(){
	let runIds = [];
	$('#processRunDiv tbody tr').each(function(){
		if($(this).find('td:eq(0) input').is(':checked')){
			runIds.push($(this).find('td:eq(1)').text());
		}
	});
	let selectedGroup  = getSelectedGroup();
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let csrf_name = $('meta[name="_csrf_parameter_name"]').attr('content');
	let form = document.createElement('form');
	form.action = ctx+`/home/migrate/process-migrator/process-converter/${selectedGroup}/downloadBulk`;
	form.method = 'post';
	form.innerHTML = `<input type="hidden" name="runIds"value="${runIds.join(',')}" />`;
	form.innerHTML += `<input type="hidden" name="${csrf_name}"value="${csrf_token}" />`;
	document.body.append(form);
	form.submit();
}

function downloadReportLog(e){
	let runID = $(e.target).parents().eq(1).find('td:eq(1)').text();
	let selectedGroup  = getSelectedGroup();
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let csrf_name = $('meta[name="_csrf_parameter_name"]').attr('content');
	let form = document.createElement('form');
	form.action = ctx+`/home/migrate/process-migrator/process-converter/${selectedGroup}/${runID}/downloadReportLog`;
	form.method = 'post';
	form.innerHTML = `<input type="hidden" name="${csrf_name}"value="${csrf_token}" />`;
	document.body.append(form);
	form.submit();
}

function validateAndSave(){
	let catalogList = [];
	let json = {};
	let currentTech = $('#sourceDb').val();
	let currentScript = $('#sourceScriptType').val();
	let targetTech = $('#targetDb').val();
	let targetScript = $('#targetScriptType').val();
	
	let schemaMappingName = "";
	$('input[name="mpdone"]').each(function(){
		if($(this).is(':checked'))
			isMappingDone = $(this).val();
	});
	if(isMappingDone == 'Yes')
		schemaMappingName = $('#schemaMappingName').val();
	
	let currentCode = $('input[name="radio-1"]:checked').val();
	let targetCode = $('input[name="radio-2"]:checked').val();
	json.currentCode = currentCode;
	json.targetCode = targetCode;
	if(currentCode == 'DB' && targetCode == 'DB'){
		json.currentTech = currentTech;
		json.currentScript = currentScript;
		json.targetTech = targetTech;
		json.targetScript = targetScript;
		json.schemaMappingName = schemaMappingName;
	}
	if(currentCode == 'DB' && targetCode == 'ETL'){
		json.currentTech = currentTech;
		json.currentScript = currentScript;
		json.targetTech = $('#targetETL').val();
		json.processFlow = $('#targetProcessFlow').val();
		$('#catalogs').find('div.col-3').each(function(){
			if($(this).find('div.custom-control input').is(':checked')){
				catalogList.push($(this).find('div.custom-control label').text());
			}
		});
		json.catalogList = catalogList.join(',');
	}
	if(currentCode == 'ETL' && targetCode == 'DB'){
		currentTech = $('#sourceETL').val();
		json.currentTech = currentTech;
		json.targetTech = targetTech;
		json.targetScript = targetScript;
		json.schemaMappingName = schemaMappingName;
	}
	if(currentCode == 'ETL' && targetCode == 'ETL'){
		currentTech = $('#sourceETL').val();
		targetTech = $('#targetETL').val();
		let targetProcessFlow = $('#targetProcessFlow').val();
		
		let connDtlArray = [];
		$('#new-conn-dtls').find('tr').each(function(){
			let obj = {};
			obj.currentConnection = $(this).find('td:eq(0)').text();
			obj.currentDB = $(this).find('td:eq(1)').text();
			obj.newConnection = $(this).find('td:eq(2) input').val();
			obj.newDB = $(this).find('td:eq(3) select').val();
			obj.schemaMapping = $(this).find('td:eq(4) select').val();
			connDtlArray.push(obj);
		});
		
		json.currentTech = currentTech;
		json.targetTech = targetTech;
		json.targetProcessFlow = targetProcessFlow;
		json.connDtlArray = connDtlArray;
	}
	console.log(json);
	let groupName = $('#group-name').val();
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	$.ajax({
		url : ctx+'/home/migrate/process-migrator/process-converter/add-process-group',
		type : 'post',
		data : {'json' : JSON.stringify(json), 'groupName' : groupName},
		headers: {'X-CSRF-Token': csrf_token },
		success:function(data){
			if(data === true){
				window.location.href = ctx+'/home/migrate/process-migrator/process-converter';
			}
		},error:function(data){
			alert('Unable to add project - '+data)
			$('#groupNameModal').modal('hide');
		}
	});
}

//delete group
const deleteConfirmation = (e) =>{
	let groupName = $(e.target).parent().parent().find('td:eq(2)').text();
	if(groupName == "" || groupName == undefined){
		validationDiv = 
			`
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
			  <strong>Error!</strong> Please select a group to delete
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			`
		$('.validation-message').empty().append(validationDiv);

	}else{
		validationDiv = 
			`
			<div class="alert alert-warning alert-dismissible fade show" role="alert">
			  <strong>Are you sure you want to delete?</strong>
			  <button class="btn btn-outline-secondary btn-sm ml-2" onclick="deleteGroup('${groupName}')">Yes</button>
			  <button class="btn btn-outline-secondary btn-sm ml-2" data-dismiss="alert">No</button>
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			`
		$('.validation-message').empty().append(validationDiv);
	}
}

const deleteGroup = (groupName) =>{
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let csrf_name = $('meta[name="_csrf_parameter_name"]').attr('content');
	let form = document.createElement('form');
	form.action = ctx+'/home/migrate/process-migrator/process-converter/delete-group';
	form.method = 'POST';
	form.innerHTML = `<input type="text" name="groupName" value="${groupName}">`;
	form.innerHTML += `<input type="hidden" name="${csrf_name}"value="${csrf_token}" />`;
	document.body.append(form);
	form.submit();
}
//delete group code ends

//get details for group
function getConfigDataForGroup(){
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let groupName = getSelectedGroup();
	$.ajax({
		url : ctx+'/home/migrate/process-migrator/process-converter/getGroupDetails',
		data : {'groupName' : groupName},
		type : 'get',
		headers: {'X-CSRF-Token': csrf_token },
		success:function(data){
			data = DOMPurify.sanitize(data);
			updateDetailsSection(data);
		},error:function(){
			
		}
	})
}

function updateDetailsSection(data){
	let obj = JSON.parse(data);
	console.log(obj);
	let div = 
		`<div class="row w-75 m-0 mt-2">
			<div class="col-8">
				<div class="row m-0">
					<div class="col-6">
						<p class="mb-2">Current Technology : <strong>${obj.currentTech}</strong>
					</div>
					<div class="col-6">
						<p class="mb-2">Target Technology : <strong>${obj.targetTech}</strong>
					</div>
				</div>
				<div class="row m-0">
					<div class="col-6">
						<p class="mb-2">Current Script : <strong>${obj.currentScript!=null ? obj.currentScript : ''}</strong>
					</div>
					<div class="col-6">
						<p class="mb-2">Target Script : <strong>${obj.targetScript!=null ? obj.targetScript : ''}</strong>
					</div>
				</div>
				<div class="row m-0">
					<div class="col-6">
						<p class="mb-2">Target Process Flow : <strong>${obj.targetProcessFlow!=null ? obj.targetProcessFlow : ""}</strong>
					</div>
					<div class="col-6">
						<p class="mb-2">Source Catalog List : <strong>${obj.sourceCatalogList!=null ? obj.sourceCatalogList : ''}</strong>
					</div>
				</div>
			</div>
		</div>`
	$('#details').empty().append(div);
}

function getMetricsDataForGroup(){
	
}

function showOutputComparison(){
	let runId = $('.run-table tr.clicked').find('td:eq(1)').text();
	let status = $('.run-table tr.clicked').find('td:eq(3)').text();
	let  processType = $('#processGroupBody tr.clicked').find('td:eq(3)').text();
	if(status == 'success' && processType == 'DB - DB'){
		//call ajax and get the outputs for comparison
		let csrf_token = $('meta[name="_csrf"]').attr('content');
		let groupName = getSelectedGroup();
		$("#compare, #input-compare, #output-compare").empty();
		$('.full-overlay').removeClass('none');
		$('.spin-loader').show();
		$.ajax({
			url : ctx +`/home/migrate/process-migrator/process-converter/${groupName}/${runId}/getOutputs`,
			type : 'get',
			headers: {'X-CSRF-Token': csrf_token },
			success:function(data){
				data = DOMPurify.sanitize(data);
				compareOutput(data);
			}
		});
	}
}

function compareOutput(data){
	let obj = JSON.parse(data);
	console.log(obj);
	$('#input-compare').append(obj.input);
	$('#output-compare').append(obj.output);
	var diff = Diff.diffWords(obj.input, obj.output, {'ignoreCase' : true});
	var fragment = document.querySelector('#compare');
	for (var i=0; i < diff.length; i++) {

		if (diff[i].added && diff[i + 1] && diff[i + 1].removed) {
			var swap = diff[i];
			diff[i] = diff[i + 1];
			diff[i + 1] = swap;
		}

		var node;
		if (diff[i].removed) {
			node = document.createElement('del');
			node.appendChild(document.createTextNode(diff[i].value));
		} else if (diff[i].added) {
			node = document.createElement('ins');
			node.appendChild(document.createTextNode(diff[i].value));
		} else {
			node = document.createTextNode(diff[i].value);
		}
		fragment.appendChild(node);
	}
	console.log(fragment);
	$("#compare").append(fragment);
	$('.spin-loader').hide();
}