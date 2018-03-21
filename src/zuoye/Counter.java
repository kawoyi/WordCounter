package zuoye;

import java.io.*;
import java.util.*;


/*
 * InputStreamReader(new FileInputStream(�����ļ���))�����ļ��Ķ�ȡ
 * BufferedReader(�ļ���ȡ)����readLine()�ķ���
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
		for(int i=0;i<length;i++)//�жϲ���
		{
		  if(isPara(args[i]))
		  {
			  type.add(args[i]);
		  }
		  if(args[i].equals("-o"))//�ı����·��
		  {
		  	outputpath=args[i+1];	
		  }
		  if(args[i].equals("-e"))//��ȡͣ�ôʱ�·��
		  {
			stoplistpath=args[i+1]; 
			
			stoplist=readStoplist(stoplistpath);
		  }
		  for(int n=0;n<args[i].length();n++)//��ȡ��ȡ.c�ļ�·��
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
	public String[] readStoplist(String filepath)throws IOException//��ȡͣ�ôʱ�
	{
		String a="";
		String[] temp = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath));
		  BufferedReader br = new BufferedReader(isr);
		  while((a=br.readLine())!=null)
		  {
			 temp=a.split(" ");//�õ�ͣ�õĵ���
		  }
		 br.close();
		 
		return temp;
	}
	public boolean isPara(String s)//�жϲ���
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
	public boolean isDigit(String s)//�ж�����
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
public  boolean  isSeperator(char c)//�жϷָ���
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
public void writer()throws IOException//������
{
	 File file;
	
     
	if(outputpath.equals(""))//û��-o����
	{
	    file=new File("result.txt");
	    
	    if (!file.exists()) 
	    {
	        file.createNewFile();
	    }
	}else
	{
	  file=new File(outputpath);//�ı����·��
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
		   System.out.println(" "+"�ַ���: "+countchar+"\n");
	 		//bw.write(s3+",");
	 		bw.write(" "+"�ַ���: "+countchar+"\n");
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
	 		 System.out.println(" "+"������: "+word.size()+"\n");
	 		//bw.write(s3+",");
	 		bw.write(" "+"������: "+word.size()+"\n");
	 		bw.newLine();
	 	}
	 	if(type.contains("-l"))
	 	{
	 		//System.out.println(s3+"\n");
	 		System.out.println(" "+"����: "+countline+"\n");
	 		//bw.write(s3+",");
	 		bw.write(" "+"����: "+countline+"\n");
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
	 		System.out.println("\t"+"������: "+linenum.size()+" ����: "+(countline-linenum.size()-expline.size())+" ע����: "+expline.size()+"\n");
	 		//System.out.println(linenum);
	 		//System.out.println(expline);
	 		bw.write("������: "+linenum.size()+" ����: "+(countline-linenum.size()-expline.size())+" ע����: "+expline.size()+"\n");
	 		bw.newLine();
	 	}
	    bw.close();
	}
public void analyse()throws IOException{ //����ʵ��
  String s=null;
  InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
  BufferedReader br = new BufferedReader(isr);
  while((s=br.readLine())!=null)//null��ʾ�ļ���ȡ����
  {
	 String ss="";//��¼����
	 if(!s.equals("")){
		 countline++;//������
		 //System.out.println(s);
	 }
	 countchar += s.length();//�ַ����������ַ�����
	for(int i=0;i<s.length();i++)//�ж�һ�����ַ�����
	{
		char c=s.charAt(i);
	  if(!isSeperator(c))//���Ƿָ���
	    {
		  String temp=Character.toString(c);
	      ss=ss.concat(temp);//ƴ���ַ�
	      if(i==s.length()-1)//��ĩβû�зָ���
	      {
	    	  if(!stoplistpath.equals(""))//�ж�ͣ�ôʱ�
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
  	                  word.add(ss);	//��ӵ���
  	                }
  	    	   }
  	    	   else 
  	    	   {
  	    		 word.add(ss);   //��ӵ���
  	    		//System.out.print("2"+ss); 
  	    	   }
	      }
	    }else
	    {    //ע�����ж�
	    	 if(c=='/')
	    	 {
	    	   if(i<s.length()-1&&s.charAt(i+1)=='/')
	    	   {
	    	    if(!expline.contains(countline))
	    	     {
	    	        expline.add(countline); //���ע��������
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
   	        	  linenum.add(countline);//��Ӵ���������
   	        	 }
   	               countword++;
   	           }
   	              ss="";
	          }
	    }
	}
}
  isr.close();//�ر��ļ�
   writer();//������
}
}


