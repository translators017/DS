////////////////Added for Data Switch - Code starts here/////////////////
//to delete the project - ask confirmation before deleting the project


(function() {
	//adding event listeners
	document.querySelector('#add-project').addEventListener('click', function(){
		addProject();
	});
	
	let editIcons = document.querySelectorAll('.edit-project');
	editIcons.forEach(function(el){
		el.addEventListener('click', function(e){
			editSchemaDesign(e);
		})
	});
	
	let deleteIcons = document.querySelectorAll('.delete-project');
	deleteIcons.forEach(function(el){
		el.addEventListener('click', function(e){
			deleteSchemaDesignConfirm(e);
		})
	});
	
	$("input[name=search-projects]").keydown(function() {
	    setTimeout(function() {
	        $("td:eq(0)", "#projectsBody>tr").each(function() {
	            let lookfor = $("input[name=search-projects]").val();
	            if (new RegExp(lookfor, "i").test($(this).text()))
	                $(this).parent().show();
	            else
	                $(this).parent().fadeOut();
	        }, 0);
	
	    });
	});
	
	const dbListArray = $('#dbListArray').val();
	let dbDiv = updateDBList(dbListArray);
	$('#dbType').append(dbDiv);
	
	let downloadSchemaIcons = document.querySelectorAll('.download-schema');
	downloadSchemaIcons.forEach(function(item, index){
		item.addEventListener('click', function(e){
			downloadSchema(e);
		});
	});
	
	let downloadCodeIcons = document.querySelectorAll('.download-code');
	downloadCodeIcons.forEach(function(item, index){
		item.addEventListener('click', function(e){
			downloadCode(e);
		});
	});
	
	
	let visualizeIcons = document.querySelectorAll('.visualize');
	visualizeIcons.forEach(function(item, index){
		item.addEventListener('click', function(e){
			visualize(e);
		});
	});
	
	document.querySelector('.full-overlay-close').addEventListener('click', function(){
		closeVisualization();
	});
	
})();

function updateDBList(dbListArray){
	let dbDiv = '';
	const dbObj = JSON.parse(dbListArray);
	try{
		  for(let i = 0, len = dbObj.length; i < len; i++){
			  let options = ''
			  for(let j = 0; j < dbObj[i].dbList.length; j++){
				  options += 
					  `<option value="${dbObj[i].dbList[j].value}">${dbObj[i].dbList[j].name}</option>`;
			  }
			  dbDiv += 
				  `<optgroup label="${dbObj[i].name}">${options}</optgroup>`;
		  }
	}catch(e){
		console.log('Error parsing db list - '+e);
	}
	return dbDiv;
}

const deleteSchemaDesignConfirm = (e) =>{
//function deleteSchemaDesignConfirm(){
	let validationDiv = '';
	let projectName = $(e.target).parent().parent().find('td:eq(1)').text();
	if(projectName=="" || projectName==undefined){
		validationDiv = 
			`
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
			  <strong>Error!</strong> Please select a project to delete
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			`;
	}else{
		validationDiv = 
			`
			<div class="alert alert-warning alert-dismissible fade show" role="alert">
			  <strong>Are you sure you want to delete?</strong>
			  <button class="btn btn-outline-secondary btn-sm ml-2 delete-project">Yes</button>
			  <button class="btn btn-outline-secondary btn-sm ml-2" data-dismiss="alert">No</button>
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			`;
	}
	$('.validation-message').empty().append(validationDiv);
	document.querySelector('.delete-project').addEventListener('click', function(){
		deleteSchemaDesign(projectName);
	});
}

const deleteSchemaDesign = (projectName) =>{
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let csrf_name = $('meta[name="_csrf_parameter_name"]').attr('content');
	let form = document.createElement('form');
	form.action = ctx+'/home/migrate/schema-migrator/schema-designer/delete-schema-project';
	form.method = 'POST';
	form.innerHTML = `<input name="projectName" value="${projectName}">`;
	form.innerHTML += `<input type="hidden" name="${csrf_name}"value="${csrf_token}" />`;
	document.body.append(form);
	form.submit();
}

const editSchemaDesign = (e) => {
	let validationDiv = '';
	let projectName = $(e.target).parent().parent().find('td:eq(1)').text();
	let dbType = $(e.target).parent().parent().find('td:eq(2)').text();
	let catalogName = $(e.target).parent().parent().find('td:eq(3)').text();
	let projectDesc = $(e.target).parent().parent().find('td:eq(4)').text();
	let status = $(e.target).parent().parent().find('td:eq(8)').text();
	
	if(projectName == "" || projectName == undefined){
		validationDiv = 
			`
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
			  <strong>Error!</strong> Please select a project to edit
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			`
		$('.validation-message').empty().append(validationDiv);
	}else{
		
		$('#projectName').val(projectName);
		$('#projectDescription').val(projectDesc);
		
		$("#dbType option").filter(function() {
		    return $(this).text() == dbType;
		}).attr("selected", "selected");
		
		$("#catalogName option").filter(function() {
		    return $(this).text() == catalogName;
		}).attr("selected", "selected");
		
		$('#projectName').val(projectName).attr('readonly', true); //project name cannot be changed
		if(status != 'new'){
			$("#catalogName").attr('disabled', true); //cannot edit catalog name if status is draft or mapped
			$("#dbType").attr('disabled', true);
		}else{
			$("#catalogName").attr('disabled', false);
			$("#dbType").attr('disabled', false);
		}
		
		$("#project-add-modal").modal('show');
		$('#AddprojectForm').attr('action',ctx+'/home/migrate/schema-migrator/schema-designer/edit-schema-project');
		$('#crt-upd-prj-btn').text('Update');

	}
}

const addProject = () => {
	$("#project-add-modal").modal('show');
	$('#crt-upd-prj-btn').text('Create');
	$('#AddprojectForm').attr('action',ctx+'/home/migrate/schema-migrator/schema-designer/add-schema-project');
	$('#AddprojectForm').trigger("reset");
	$('#projectName').attr('readonly', false);	
	$('#dbType').attr('disabled', false); //that is project db
	$('#catalogName').attr('disabled', false);
}

function downloadSchema(e){
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let csrf_name = $('meta[name="_csrf_parameter_name"]').attr('content');
	let selectedProject = $(e.target).parents().eq(1).find('td:eq(1)').text();
	let form = document.createElement('form');
	form.action = ctx+'/schemaDesigner/'+selectedProject+'/downloadSchema';
	form.innerHTML = `<input type="hidden" name="${csrf_name}"value="${csrf_token}" />`;
	form.method = 'POST';
	document.body.append(form);

	form.submit();
}
////////////////Added for Data Switch - Code ends here/////////////////



function closeVisualization(){
	$('.full-overlay').hide();
}




function downloadCode(e){
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let csrf_name = $('meta[name="_csrf_parameter_name"]').attr('content');
	let selectedProject = $(e.target).parents().eq(1).find('td:eq(1)').text();
	let form = document.createElement('form');
	form.action = ctx+'/home/migrate/schemaDesigner/'+selectedProject+'/generate-code';
	form.innerHTML = '<input type="hidden" name="'+csrf_name+'" value="'+csrf_token+'" />';
	form.method = 'POST';
	// the form must be in the document to submit it
	document.body.append(form);

	form.submit();
}

function visualize(e){
	let csrf_token = $('meta[name="_csrf"]').attr('content');
	let selectedProject = $(e.target).parents().eq(1).find('td:eq(1)').text();
	$.ajax({
		url : ctx + '/schemaDesigner/'+selectedProject+'/visualize',
		type :'get',
		headers: {'X-CSRF-Token': csrf_token },
		success:function(data){
			$('.full-overlay').show();
			updateVisualization(data);
			
		},error:function(){
			alert('Error! Please try again later.')
		}
	})

}

function updateVisualization(data){
	let obj = JSON.parse(data);
	console.log(obj.schemaEntities);
	console.log(obj.attributes);
	let schemaEntitiesJson = JSON.parse(obj.schemaEntities);
	let attributejson = obj.attributes[0];
	function Node(id,cid,label,group){
		  this.id = id;
		  this.cid = cid;
		  this.label = label;
		  this.group = group;
		  
		}

		function Edge(from, to, dashes){
		  this.from = from;
		  this.to = to;
		  this.dashes = dashes;
		}

		let cid = 1, id=0;
		let nodeArrayList = [];
		let edgeArrayList = [];
		
		let schemaDesignObj = JSON.parse(schemaEntitiesJson.schemaDesign);
		for(var collectionName in schemaDesignObj){
		  let collectionValueObj = schemaDesignObj[collectionName];
		  console.log(collectionName); //claim - 10 columns
		  console.log(collectionValueObj); //{}
		  id++; //id = 1
		  let parentNode = new Node(id, cid, collectionName, "main-collection-group");
		  nodeArrayList.push(parentNode); // 1 value
		  console.log(attributejson.entityName+":::"+collectionName);
		  

		    for(let attribute of attributejson.attributeArr){
		    	if(attribute.targetEntityRef === collectionName){
		    id++; // id = 2
		    let node = new Node(id, cid, attribute.targetAttributeRef, "attribute-group");
		    nodeArrayList.push(node); // 10 cols - 11 values in array
		    let edge = new Edge(id, cid);
		    edgeArrayList.push(edge);
		    
		    }
		  }

		  for(let eachNode of collectionValueObj.nodes){
		    
		    if(eachNode.parentName === collectionName){
		      id++; //12
		      let embedNode = new Node(id, cid, eachNode.nodeName, "embed-collection-group");
		      
		      nodeArrayList.push(embedNode);

		      let edge = new Edge(id, cid, true);
		      edgeArrayList.push(edge);
		      
		      let nodeAttrParent = id;
		      
		    	  for(let attribute of attributejson.attributeArr){
		    		  let tableNm = attribute.targetEntityRef;
		    		  if(tableNm.includes('.')){
		    			  tableNm = tableNm.substring(tableNm.indexOf('.')+1, tableNm.length);
		    		  }
		    		  if(tableNm === eachNode.parentName){
					    id++;
					    let node = new Node(id, nodeAttrParent, attribute.targetAttributeRef, "attribute-group");
					    nodeArrayList.push(node);
					    let edge = new Edge(id, nodeAttrParent);
					    edgeArrayList.push(edge);
					    
				}
		      }
		      
		      
		      
		    }else{
		      // for(let x in nodeArrayList){
		      //   if(x.parentName === collectionName){
		      //     let embedNode = new Node(x+1, x.id, x.nodeName);
		      //   }
		      // }
		    }
		  }
		}
		console.log(nodeArrayList);
		console.log(edgeArrayList);
		
		

		var nodes = nodeArrayList;
		var edges = edgeArrayList;
		
		/*var x = 920;
        var y = -470;

        var step = 70;
        var stepX = 25;
        
        nodes.push({
            id: 1000,
            x: x,
            y: y - 30 + 10,
            label: "main-collection-group",
            group: "maincollectiongroup",
            value: 1,
            fixed: true,
            physics: false,
        });
        nodes.push({
            id: 1001,
            x: x,
            y: y + step,
            label: "embed-collection-group",
            group: "embedcollectiongroup",
            value: 1,
            fixed: true,
            physics: false,
        });
        nodes.push({
            id: 1002,
            x: x,
            y: y + 2 * step + 30,
            label: "attribute-group",
            group: "attributegroup",
            value: 1,
            fixed: true,
            physics: false,
        });*/

		  // create a network
		  var container = document.getElementById('mynetwork');
		  var options = {
				  interaction: {
			          navigationButtons: true,
			          keyboard: true,
			          autoResize : true,
			          
			        },
			        nodes: {
			            shape: 'box',
			            
			            size: 26,
			            

			            font: {
			                size: 20,
			                color: '#2d3436'
			            },
			            borderWidth: 2
			        },
			        edges: {
			            width: 2
			        },
			       
			         groups:{"attribute-group":{
			          color:{
			             background:'#f1c40f',
			             border:'#f1c40f'
			                },
			                shape:"ellipse",
			                font: {
			                size: 14,
			                
			            },
			          shadow:{enabled:true,
			                  color:'rgba(0,0,0,0.5)',
			                  x:6,
			                  y:6 
			                 }
			              
			           },
			           "main-collection-group":{
			          color:{background:'#3498db',
			             border:'#3498db'},
			             shape:"box",
			             font: {
			                size: 30,
			                color: '#fff'
			            },

			          shadow:{enabled:true,
			                  color:'rgba(0,0,0,0.5)',
			                  x:6,
			                  y:6 
			                 }
			              
			           },
			           "embed-collection-group":{
			          color:{background:'#e67e22',
			             border:'#e67e22'},
			              shape:"box",
			              font: {
			                size: 22,
			                color: 'white'
			            },
			          shadow:{enabled:true,
			                  color:'rgba(0,0,0,0.5)',
			                  x:6,
			                  y:6 
			                 }
			              
			           }, 
			           
			           maincollectiongroup: {
		                    shape: "square",
		                    color: "#e67e22", // orange
		                },
		                embedcollectiongroup: {
		                    shape: "square",
		                    color: "#3498db", // blue
		                },
		                attributegroup: {
		                    shape: "square",
		                    color: "#f1c40f", // yellow
		                }
			         
			     
			        }
			    }; 
		  var data = {
			nodes: nodes,
		    edges: edges,
		    animation: { 
		        duration: 500,
		        easingFunction: String
		      }  
		  };
		  var network = new vis.Network(container, data, options);
		  network.fit();

		  draw ();

		  network.on("selectNode", function (params) {
		         console.log("params", params);

		         console.log("node detail ", params.nodes[0]);
		         if (params.nodes.length == 1) {
		            if (network.isCluster(params.nodes[0]) == true) {
		               network.openCluster(params.nodes[0]);
		            }
		            else {
		            
		                let nodeval = nodes.find(element => element.id == params.nodes[0]);
		                console.log("node value", nodeval);
		                clusterByCid(params.nodes[0], nodeval.label, nodeval.cid);
		            }
		         }
		      });




		      function clusterByCid(val, labelval, cid) {
		         console.log('clustering '+val);
		         var clusterOptionsByData = {
		            joinCondition: function (childOptions) {
		               if (network.getConnectedNodes(val, "from").includes(childOptions.cid)
		                        || val == childOptions.cid ) {
		                        
		                        return true;
		                    }
		                    return false;
		            },
		            clusterNodeProperties: { id : 'cl'+val, cid : cid,  borderWidth: 10, shape: 'hexagon', label: labelval }
		         };
		         network.cluster(clusterOptionsByData);
		         console.log(network.getNodesInCluster(val));
		      }
		  
		  function resize() {
			  network.fit();
		  }
		  
		  $(document).ready(function () {
			    $('#mynetwork').resize(function() {
			    	resize();
			    });
		  });
		 

		  function draw () {
		    try {
		      $('#error').html('');
		      network.setData(data);
		      for(let node of nodes){
	               if(node.group == 'embed-collection-group'){
	                  clusterByCid(node.id, node.label, node.cid);
	               }
	            }
	            for(let node of nodes){
	               if(node.group == 'main-collection-group'){
	                  clusterByCid(node.id, node.label, node.cid);
	               }
	            
	            }
	            
		    }
		    catch (err) {
		      // set the cursor at the position where the error occurred
		      var match = /\(char (.*)\)/.exec(err);
		      if (match) {
		        var pos = Number(match[1]);
		        var textarea = $('#data')[0];
		        if(textarea.setSelectionRange) {
		          textarea.focus();
		          textarea.setSelectionRange(pos, pos);
		        }
		      }

		      // show an error message
		      $('#error').html(err.toString());
		    }
		  }

}