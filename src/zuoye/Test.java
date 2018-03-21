package zuoye;

import java.io.*;
import java.util.*;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		        String[] arg=new String[args.length+1];
		        for(int n=0;n<args.length;n++)
		        {
		        	arg[n]=args[n];
		        }
				ReadFile.test(System.getProperty("user.dir"));
				List<File>fileList=ReadFile.list;
				List<File>list=new ArrayList<File>();
				 for (File f1 : fileList) {  
			        	
			         if( (f1.getName().charAt(f1.getName().length()-1)=='c')&&(f1.getName().charAt(f1.getName().length()-2)=='.'))
			         {
			        	 if(!list.contains(f1))
			        	 {
			        	 list.add(f1);
			        	 }
			         }

			      }  
				/* for(File f1: list)
				 {
					 System.out.println(f1.getAbsolutePath());
				 }*/
				 
		boolean flag=false;
		for(int i=0;i<args.length;i++)
		{
			if(args[i].equals("-s"))
			{
				flag=true;
				break;
			}
		}
		if(flag==true)
		{
			for(File f: list)
		 {
		  
		
		  arg[arg.length-1]=f.getAbsolutePath();//.substring(System.getProperty("user.dir").length()+1, f.getAbsolutePath().length());
		/*for(int i=0;i<arg.length;i++)
		   {
			  for(int n=0;n<arg[i].length();n++)
			  {
				if(arg[i].charAt(n)=='.')
				{
				  if(arg[i].charAt(n+1)=='c')
				  {
					  System.getProperty("user.dir");
					 arg[i]=f.getAbsolutePath().substring(System.getProperty("user.dir").length(), f.getAbsolutePath().length());
					 break;
				  }
				}
			  }
		   }*/
		    Counter counter=new Counter(arg,arg.length);
	        counter.analyse();
		 }
		}
		else
		{
	     Counter counter=new Counter(args,args.length);
         counter.analyse();
		}
        
        
	}
   
}
