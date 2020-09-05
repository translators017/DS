
This project can be used in 2 ways:

a) As Stand Alone Application:

  Please follow these steps:

	1) Run the project
	2) It asks for user 3 inputs -  - source db name, target db name, source data type 	(optional, can type "skip" to skip it)	
	3) If you have used "skip" as 3rd user argument, it will provide you the whole list 	of corresponding data type mappings between mentioned source and target db


b) Integrated with a project: 

   Steps:

	1) Make an object of DataTypeConvertor class (say "dtc" is the formed object)
	2) call the function: 
	     String tgtDtType= dtc.getTargetData(db2Tool.get(sourceDBName), db2Tool.get                  (targetDBName), srcDType); 
	3) It returns you the corresponding target datatype as a String. 