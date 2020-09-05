<!-- main nav -->
 <%-- <nav class="navbar navbar-expand-sm p-0 bg-white" style="z-index:2000;">
     <div class="container-fluid">
         <!-- Brand/logo -->
         <a class="navbar-brand" href="/">
             <img src="<%=request.getContextPath()%>/resources/images/ds_logo_header.png" style="min-width: 200px; min-height: 60px;" alt="DataSwitch">
         </a>
         <div class="collapse navbar-collapse">
             <ul class="navbar-nav ml-auto">
                 <li class="nav-item">
                     <a class="nav-link" href="<%=request.getContextPath()%>/home">Home</a>
                 </li>
                 <li class="nav-item dropdown">
                     <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                         aria-haspopup="true" aria-expanded="false">Migrate</a>
                     <div class="dropdown-menu">
                         <a class="dropdown-item" href="<%=request.getContextPath()%>/home/migrate/schema-migrator">Schema</a>
                         <a class="dropdown-item" href="<%=request.getContextPath()%>/home/migrate/data-migrator">Data</a>
                         <a class="dropdown-item" href="<%=request.getContextPath()%>/home/migrate/process-migrator">Process</a>
                     </div>
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">Integrate</a>
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">Democratize</a>
                 </li>
                 <li class="nav-item d-flex align-items-center lh-2">
                     <a class="nav-link btn btn-green" href="<%=request.getContextPath()%>/logout">LogOut</a>
                 </li>
             </ul>
         </div>
     </div>
 </nav> --%>
 <nav class="navigation navbar navbar-expand-sm p-0 bg-white" style="z-index:2000;">
      <a class="navbar-brand" href="/">
           <img src="<%=request.getContextPath()%>/resources/images/ds_logo_header.png" style="min-width: 200px; min-height: 60px;" alt="DataSwitch">
       </a>
      <!-- <span class='hamburger-menu'>Navigate
        <span class='burger-1'></span>
        <span class='burger-2'></span>
        <span class='burger-3'></span>
      </span> -->
      <ul class='core-menu w-100 mr-5 text-right'>
      	<li><a href='<%=request.getContextPath()%>/home'>Home</a></li>
        <li><a href='#'>Migrate<i class="nav-arrow fas fa-angle-down nav-arrow-main"></i></a></a>
          <ul class='dropdown'>
            <li><a href='<%=request.getContextPath()%>/home/migrate/schema-migrator'>Schema Migrator<i class="nav-arrow fas fa-angle-right"></i></a>
              <ul class='dropdown2'>
                <li><a href='<%=request.getContextPath()%>/home/migrate/schema-migrator/catalogs'>Data Catalog</a></li>
                <li><a href='<%=request.getContextPath()%>/home/migrate/schema-migrator/schema-designer'>Schema Designer</a></li>
                <li><a href='#'>Data Mapper</a></li>
              </ul>
            </li>
            <li><a href='<%=request.getContextPath()%>/home/migrate/data-migrator'>Data Migrator<i class="nav-arrow fas fa-angle-right"></i></a></a>
            	<ul class='dropdown2'>
	                <li><a href='#'>Job Designer</a></li>
	                <li><a href='#'>Job Execution</a></li>
	                <li><a href='#'>Job Monitor</a></li>
	                <li><a href='#'>Job Validation</a></li>
	            </ul>
            </li>
            <li><a href='<%=request.getContextPath()%>/home/migrate/process-migrator'>Process Migrator<i class="nav-arrow fas fa-angle-right"></i></a></a>
            	<ul class='dropdown2'>
	                <li><a href='<%=request.getContextPath()%>/home/migrate/process-migrator/process-converter'>Process Converter</a></li>
	                <li><a href='#'>App Remediator</a></li>
	                <li><a href='#'>App Refactor</a></li>
	                <li><a href='#'>Process Validator</a></li>
	            </ul>
            </li>
          </ul>
        </li>
        <li><a href='#'>Integrate<span class='toggle'></a>
        
        </li>
        <li><a href='#'>Democratize</a></li>
        <li><a class="nav-link btn btn-green ml-4" style="line-height:30px;" href="<%=request.getContextPath()%>/logout">Logout</a></li>
      </ul>
    </nav>