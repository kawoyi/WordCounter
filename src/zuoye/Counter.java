package zuoye;

import java.io.*;
import java.util.*;


/*
 * InputStreamReader(new FileInputStream(绝对文件名))进行文件的读取
 * BufferedReader(文件读取)调用readLine()的方法
 */

public class Counter {
	public static char[] seperator={';','{','}','+','-','*','=','%','&','!','"','<','>','(',')',',',' ','\n','\t','/','\r'};
	public String[] stoplist;
	public static String[] para={"-a","-w","-l","-c","-o","-s","-e"};
	public String path="";
	public String outputpath="";
	public String stoplistpath="";
	public String abspath="";
	
	ArrayList<String> type=new ArrayList<String>();
	ArrayList<String> word=new ArrayList<String>();
	ArrayList<Integer>linenum=new ArrayList<Integer>();
	ArrayList<Integer>expline=new ArrayList<Integer>();
	 int countchar = 0;
	 int countword = 0;
	 int countline =0;
	 int countexp=0;
	Counter(String[] args,int length) throws IOException
	{
		for(int i=0;i<length;i++)//判断参数
		{
		  if(isPara(args[i]))
		  {
			  type.add(args[i]);
		  }
		  if(args[i].equals("-o"))//改变输出路径
		  {
		  	outputpath=args[i+1];	
		  }
		  if(args[i].equals("-e"))//获取停用词表路径
		  {
			stoplistpath=args[i+1]; 
			
			stoplist=readStoplist(stoplistpath);
		  }
		  for(int n=0;n<args[i].length();n++)//获取读取.c文件路径
		  {
			if(args[i].charAt(n)=='.')
			{
			  if(args[i].charAt(n+1)=='c')
			  {
				 path=args[i];  
			  }
			}
		  }
		}
	
		
	       		
		
		
	}
	public String[] readStoplist(String filepath)throws IOException//读取停用词表
	{
		String a="";
		String[] temp = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath));
		  BufferedReader br = new BufferedReader(isr);
		  while((a=br.readLine())!=null)
		  {
			 temp=a.split(" ");//得到停用的单词
		  }
		 br.close();
		 
		return temp;
	}
	public boolean isPara(String s)//判断参数
	{
		boolean flag=false;
		   for(int i=0;i<para.length;i++)
		   {
			    if(s.equals(para[i]))
			    {
			      flag=true;	
			      break;
			    }
		   }
		   return flag;
	}
	public boolean isDigit(String s)//判断数字
	{
		boolean flag=true;
		for(int i=0;i<s.length();i++)
		{
		  if(s.charAt(i)>=48&&s.charAt(i)<=57){
			  
		  }else
		  {
			flag=false;  
		  }
		}
		return flag;
	}
public  boolean  isSeperator(char c)//判断分隔符
{
	
	boolean flag=false;
   for(int i=0;i<seperator.length;i++)
   {
	    if(c==seperator[i])
	    {
	      flag=true;	
	      break;
	    }
   }
   return flag;
}
public void writer()throws IOException//结果输出
{
	 File file;
	
     
	if(outputpath.equals(""))//没用-o参数
	{
	    file=new File("result.txt");
	    
	    if (!file.exists()) 
	    {
	        file.createNewFile();
	    }
	}else
	{
	  file=new File(outputpath);//改变输出路径
	  if (!file.exists()) 
	    {
	        file.createNewFile();
	    }
	}
	   FileWriter fw = new FileWriter(file,true);
	   BufferedWriter bw = new BufferedWriter(fw);
	   System.out.println(path+"\n");
	   bw.write(path+",");
	   bw.newLine();
	   if(type.contains("-c"))
	 	{
		  // System.out.println(s3+"\n");
		   System.out.println(" "+"字符数: "+countchar+"\n");
	 		//bw.write(s3+",");
	 		bw.write(" "+"字符数: "+countchar+"\n");
	 		bw.newLine();
	 	}
	 	if(type.contains("-w"))
	 	{
	 		//System.out.println(word);
	 		/*for(int m=0;m<stoplist.length;m++)
	 		{
	 		System.out.println(stoplist[m]);
	 		}*/
	 		//System.out.println(s3+"\n");
	 		 System.out.println(" "+"单词数: "+word.size()+"\n");
	 		//bw.write(s3+",");
	 		bw.write(" "+"单词数: "+word.size()+"\n");
	 		bw.newLine();
	 	}
	 	if(type.contains("-l"))
	 	{
	 		//System.out.println(s3+"\n");
	 		System.out.println(" "+"行数: "+countline+"\n");
	 		//bw.write(s3+",");
	 		bw.write(" "+"行数: "+countline+"\n");
	 		bw.newLine();
	 	}
	 	if(type.contains("-a"))
	 	{
	 		//bw.write(s3+",");
	 		//System.out.println(s3+"\n");
	 		for(int i=0;i<expline.size();i++)
	 		{
	 		   if(linenum.contains(expline.get(i)))
	 		   {
	 			   linenum.remove(expline.get(i));
	 		   }
	 		}
	 		System.out.println("\t"+"代码行: "+linenum.size()+" 空行: "+(countline-linenum.size()-expline.size())+" 注释行: "+expline.size()+"\n");
	 		//System.out.println(linenum);
	 		//System.out.println(expline);
	 		bw.write("代码行: "+linenum.size()+" 空行: "+(countline-linenum.size()-expline.size())+" 注释行: "+expline.size()+"\n");
	 		bw.newLine();
	 	}
	    bw.close();
	}
public void analyse()throws IOException{ //功能实现
  String s=null;
  InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
  BufferedReader br = new BufferedReader(isr);
  while((s=br.readLine())!=null)//null表示文件读取结束
  {
	 String ss="";//记录单词
	 if(!s.equals("")){
		 countline++;//行增加
		 //System.out.println(s);
	 }
	 countchar += s.length();//字符个数就是字符长度
	for(int i=0;i<s.length();i++)//判断一行里字符种类
	{
		char c=s.charAt(i);
	  if(!isSeperator(c))//不是分隔符
	    {
		  String temp=Character.toString(c);
	      ss=ss.concat(temp);//拼接字符
	      if(i==s.length()-1)//行末尾没有分隔符
	      {
	    	  if(!stoplistpath.equals(""))//判断停用词表
  	    	   {
  	    		  boolean flag=false;
  	              for(int p=0;p<stoplist.length;p++)
  	              {
  	            	  if(ss.equals(stoplist[p]))
  	            	  {
  	            		  flag=true;
  	            		  break;
  	            	}
  	              }
  	                if(flag==false)
  	                {
  	                  word.add(ss);	//添加单词
  	                }
  	    	   }
  	    	   else 
  	    	   {
  	    		 word.add(ss);   //添加单词
  	    		//System.out.print("2"+ss); 
  	    	   }
	      }
	    }else
	    {    //注释行判断
	    	 if(c=='/')
	    	 {
	    	   if(i<s.length()-1&&s.charAt(i+1)=='/')
	    	   {
	    	    if(!expline.contains(countline))
	    	     {
	    	        expline.add(countline); //添加注释所在行
	    	     }
	    	   }
	         }
	     if(!ss.equals(""))
             {
   	       if(!isDigit(ss))
   	          {
   	          //System.out.println("ss:"+ss);
   	    	   if(!stoplistpath.equals(""))
   	    	   {
   	    		  boolean flag=false;
   	              for(int p=0;p<stoplist.length;p++)
   	              {
   	            	  if(ss.equals(stoplist[p]))
   	            	  {
   	            		  flag=true;
   	            		  break;
   	            	}
   	              }
   	                if(flag==false)
   	                {
   	                  word.add(ss);	
   	                }
   	    	   }
   	    	   else 
   	    	   {
   	    		 word.add(ss);   
   	    		//System.out.print("2"+ss); 
   	    	   }
   	          if(!linenum.contains(countline))
   	             {
   	        	  linenum.add(countline);//添加代码所在行
   	        	 }
   	               countword++;
   	           }
   	              ss="";
	          }
	    }
	}
}
  isr.close();//关闭文件
   writer();//输出结果
}
}


