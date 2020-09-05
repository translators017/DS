
package com.dataSwitch.xml.TalendCodegen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.xml.connectorspecs.Connector;

public class getAuditDetails {

	

public static void getAuditDetail(Connector Docspecs,String logOutput,Console console, LogHelper logHelperObj) 
{
	//private static void getAuditDetails(Connector Docspecs ) {
		// TODO Auto-generated method stub
		try{             
            
            int count=0;
            int unhandled=0;
            int reapted_number=0;
            int number_Transformations=0;
            String name=Docspecs.getName();
            List<String> expList = new ArrayList<String>();      
            for(int loop=0;loop<Docspecs.getDataMappings().getDataMapping().size();loop++){
                  for(int inner_Loop=0;inner_Loop<Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().size();inner_Loop++){
                  if(Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getTransformationType().equalsIgnoreCase("Other Transformation")){
                         expList.add(Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getOtherTransformation().getTransformationType()+";"+Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getName()+";"+Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getTransformationType());
                                unhandled=unhandled+1;
                                System.out.println("other Transformations-------------------"+Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getName()+";"+Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getOtherTransformation().getTransformationType()+";"+Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getTransformationType());

                         //obj.manualElements.add(Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getName());
                         }
                  if(Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().get(inner_Loop).getTransformationType().equalsIgnoreCase("Expression"))
                         {
                                reapted_number=reapted_number+1;
                         }
                         
                  }
            //Number of Transformations
     number_Transformations=Docspecs.getDataMappings().getDataMapping().get(loop).getDataTransformations().getDataTransformation().size();

            
            }
            System.out.println("number_Transformations------"+number_Transformations);
            //List setting
            
            
            //For General Expression
            for(int loop=0;loop<Docspecs.getDataMappings().getDataMapping().size();loop++){
                  for(int inner_Loop=0;inner_Loop<Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().size();inner_Loop++){
                  if(Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule() !=null){
                                for(int nested=0;nested<Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().size();nested++){ 
                         if(Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getRuleName().equalsIgnoreCase("General - Expression")){
                                //System.out.println("General----------"+Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getComponentName());
                  //     expList.add(Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTargetDataElementRef()+"------"+Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getComponentName());
                                //To Print the value
                                //for(int rule_loop=0;rule_loop<Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getInputDataElements().size();rule_loop++){
                                if(Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getParameter().get(0).getValue() != null){
                                       expList.add(Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTargetDataElementRef()+";"+Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getComponentName()+";"+Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getParameter().get(0).getValue());
            System.out.println("Genral-----------"+Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTargetDataElementRef()+","+Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getComponentName()+","+Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getParameter().get(0).getValue());
                                       }
                                //}
                                
                                unhandled=unhandled+1;
                                
                                String paramVal = Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().get(nested).getParameter().get(0).getValue();
                                
                                if(paramVal.equalsIgnoreCase("DSJobName")){
                                	if(unhandled != 0){
                                		unhandled--;
                                	}
                                }
                                
	                             char singleQuote = '\'';
	                   			 char doubleQuote = '"';
	                   			 int countSingleQuote = 0;
	                   			 int countDoubleQuote = 0;
	                   			 
	                   			 for(int i=0;i<paramVal.length();i++){
	                   				 if(paramVal.charAt(i)==singleQuote){
	                   					 countSingleQuote++;
	                   				 }
	                   				 if(paramVal.charAt(i)==doubleQuote){
	                   					 countDoubleQuote++;
	                   				 }
	                   			 }
	                   			 
	                   			/*if>>>#
	                			  	else>>>@
	                			  	then>>>>%*/
	                 			 
	                 			 int countIf = 0;
	                 			 int countElse = 0;
	                 			 int countThen = 0;
	                 			 
	                 			 String temp =  paramVal.replaceAll("(?i)IIF ", "#").replaceAll(" (?i)IIF ", "#");
	                 			 temp =  temp.replaceAll("(?i)If ", "#").replaceAll(" (?i)if ", "#");
	                 			 temp =  temp.replaceAll(" (?i)Then ", "%");
	                 			 temp =  temp.replaceAll(" (?i)Else ", "@");
	                 			 
	                 			 for(int l=0;l<temp.length();l++){
	                 				 char c = temp.charAt(l);
	                 				 if(c=='#'){
	                 					 countIf++;
	                 				 }
	                 				 else if(c=='@'){
	                 					 countElse++;
	                 				 }
	                 				 else if(c=='%'){
	                 					 countThen++;
	                 				 }
	                 			 }
	                 			 
	                 			 String ifCondition = "";
	                 			 String thenStatement = "";
	                 			 String elseStatement = "";
	                 			 
	                 			 if(countIf==1 && countThen==1 && countElse==1){
	                 				 ifCondition = temp.substring(temp.indexOf('#')+1,temp.indexOf('%'));
	                     			 thenStatement = temp.substring(temp.indexOf('%')+1,temp.indexOf('@'));
	                     			 elseStatement = temp.substring(temp.indexOf('@')+1);
	                 			 }
	                   			 
	                   			 if(countSingleQuote==2 && paramVal.charAt(0)==singleQuote && paramVal.charAt(paramVal.length()-1)==singleQuote){
	                   				 unhandled--;
	                   			 }
	                   			else if(countDoubleQuote==2 && paramVal.charAt(0)==singleQuote && paramVal.charAt(paramVal.length()-1)==singleQuote){
	                   				unhandled--;
	                			 }
	                   			else if(countIf==1 && countThen==1 && countElse==1 && !thenStatement.contains("(") && !ifCondition.contains("(") && !elseStatement.contains("(")){
	                                unhandled--;
	                			 }
                                
                                }
                                
                                
                                }      
                         count=count+Docspecs.getDataMappings().getDataMapping().get(loop).getTargetDataElements().getTargetDataElement().get(inner_Loop).getTransformationRule().size();
                                }
                         }
                  }
            
            String type_job="";
            if(number_Transformations<5){
                  type_job="Simple";
            }
            else if (number_Transformations >=5 && number_Transformations<10){
                  type_job="Medium";
            }
            else {
                  type_job="Complex";
            }
            
            
            //int total=count-reapted_number+number_Transformations;
            int total=count+number_Transformations;
            unhandled = unhandled - logHelperObj.genExpVarsInCols;
            float percent=(float)(((total-unhandled)*100)/total);
            
            
            
            
            /*
            LogDetails obj=new LogDetails();
            obj.setManual_Data(expList);
            obj.setTotal_count(total);              
            obj.setType_job(type_job);
            obj.setNumber_transformations(number_Transformations);
            obj.setNumber_transformations_rules(count);
            obj.setAutomation(percent);*/
            
            BufferedWriter bw = null;
            FileWriter fw = null;
            fw = new FileWriter(logOutput);
            bw = new BufferedWriter(fw);
            String content="";
            
            content = "#############################\n Talend Codegen Log \n#############################\n";
            bw.write(content);
            content="Mapping Name - "+name+"\n";
            bw.write(content);
            content="Number of Entity Transformation - "+number_Transformations+"\n";
            bw.write(content);
            content="Number of  Attribute Transformation Rules - "+count+"\n";
            bw.write(content);
            content="Total Number Of Transformations - "+total+"\n";
            bw.write(content);
            content="Complexity Type - "+type_job+"\n";
            bw.write(content);
            content="Automation Percentage - "+percent+"\n";
            bw.write(content);
            content="Number Of Transformations to be changed manually - "+unhandled+"\n";
            bw.write(content);
            //content = "\n---------------------------------------------------\nFollowing Transformations has to be handled Manually \n---------------------------------------------------\n";
            //bw.write(content);
            
            //Find Distinct Transformastions
            List<String> distlist = new ArrayList<String>();     
            for (int i = 0; i < expList.size(); i++) {                         
                         if(!distlist.contains(expList.get(i).split(";")[1]))
                         distlist.add(expList.get(i).split(";")[1]);                                                          
            }
            
            System.out.println("distlist--------"+distlist.toString());
            for(int loop=0;loop<distlist.size();loop++){
                  
                  boolean check=true;
                  int test=1;
                  for (int i = 0; i < expList.size(); i++) {
                         if(expList.get(i).split(";")[2].equalsIgnoreCase("Other Transformation") & loop==0){
                                content=(loop+1)+". Tramsformation Name : "+expList.get(i).split(";")[1]+"\t Transformation Name : "+expList.get(i).split(";")[0]+"\n";
                                //sno=sno+1;
                                bw.write(content);
                                
                         }
                         else{
                                if(distlist.get(loop).contains("exp")){
                                       if(distlist.get(loop).contains("exp") & check & expList.get(i).split(";")[1].equalsIgnoreCase(distlist.get(loop))){
                                              content=(loop+1)+". Tramsformation Name : Expression\t  Transformation Name : "+expList.get(i).split(";")[1]+"\n";                                                
                                              bw.write(content);
                                              //sno=sno+1;
                                              check=false;
                                              
                                       }
                                       
                                       if(expList.get(i).split(";")[1].equalsIgnoreCase(distlist.get(loop))){
                                              content=" "+(loop+1)+"."+(test)+". Variable Name : "+expList.get(i).split(";")[0]+"\t Rule : "+expList.get(i).split(";")[2]+"\n";
                                              test=test+1;
                                              bw.write(content);
                                       }
                                }
                         }
                  }
                  check=true;
            }
            
            
            /*for(int loop=0;loop<distlist.size();loop++){
                  if(distlist.get(loop).contains("exp")){
                         content=(i+1)+". Tramsformation Name : Expression\t Name : "+expList.get(i).split(",")[1]+"\n";                                             
                         bw.write(content);
                  for (int i = 0; i < expList.size(); i++) {
                                                            
                                
                                       int sno=1;
                                       if(expList.get(i).split(",")[1].equalsIgnoreCase(distlist.get(loop))){
                                              content=" "+(sno)+". Variable Name : "+expList.get(i).split(",")[0]+"\t Rule : "+expList.get(i).split(",")[2];;
                                              sno=sno+1;
                                              bw.write(content);
                                       }
                                }
                         
                  }
            }*/
            /*String temp=(i+1) +". "+expList.get(i).split(",")[1]+"\t\t\t\t";
            String temp1=expList.get(i).split(",")[0]+"\t\t\t\t";
            
                  String temp2=expList.get(i).split(",")[2]+"\t\n";
                  bw.write(temp+temp1+temp2);*/
            
            
            content = "\n--------------------------------------------------------\nComponents Which needs Manual Update\n--------------------------------------------------------\n";
            bw.write(content);
            
            
            
            
            //To Get Glue Code
//            for (int i = 0; i < pythonObjList.size(); i++) {
//
//if(pythonObjList.get(i).Rule.contains("general(") & !pythonObjList.get(i).Rule.contains("query =") & pythonObjList.get(i).Rule.contains("=")){
//content = pythonObjList.get(i).Rule.replaceAll("#", "")+"\n";
//bw.write(content);
//}
//else if(!pythonObjList.get(i).Rule.contains(".") & !pythonObjList.get(i).Rule.contains("query =") & pythonObjList.get(i).Rule.contains("=")){
//content = pythonObjList.get(i).Rule.replaceAll("#", "")+"\n";
//bw.write(content);
//}
//                                              
//                  
//            }
//            
            
            
            
            
            
            
            
            
            
            
            /*for(int loop=0;loop<expList.size();loop++){
                  content=(loop+1)+". "+expList.get(loop)+"\n";
                  bw.write(content);
            }*/
            
            
            
            if (bw != null)
                  bw.close();

            if (fw != null)
                  fw.close();
            console.put("Message","Mapping Name :"+name);
            console.put("Message","Number of Entity Transformation :"+number_Transformations);
            console.put("Message","Number of  Attribute Transformation Rules :"+count);
            console.put("Message","Total Number Of Transformations :"+Integer.toString(total));
            console.put("Message","Complexity Type :"+type_job);
            console.put("Message","Automation Percentage :"+percent);
            console.put("Message","Number Of Transformations to be changed manually :"+unhandled);

            System.out.println("number_rules-----"+count);
            System.out.println("reapted_number---"+reapted_number);
            System.out.println("unhandled-----"+unhandled);
            // console.put("Total_Transformations",Integer.toString(total));
            System.out.println("Count--------"+(count-reapted_number+number_Transformations));
            // console.put("Automation Percentage",Float.toString(percent));
            System.out.println("Automation-----"+percent);
            
     
}
			catch (Exception e) {

				e.printStackTrace();

			}
		
	}



	
	
	
	
	
	
	
}