package zuoye;
import java.io.File;  
import java.util.ArrayList;  
import java.util.List;  
  
/** 
 * �ݹ��ȡĳ��Ŀ¼�µ������ļ� 

 */ 
public class ReadFile {  
	public static List<File>list=new ArrayList<File>();
	/*public static List<File> getFileList(String strPath) {
		 List<File> filelist = new ArrayList<File>(); 
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // ���ļ�Ŀ¼���ļ�ȫ����������
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // �ж����ļ������ļ���
                    getFileList(files[i].getAbsolutePath()); // ��ȡ�ļ�����·��
                } else if (fileName.endsWith("avi")) { // �ж��ļ����Ƿ���.avi��β
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }*/
    public static void test(String fileDir) {  
        List<File> fileList = new ArrayList<File>();  
     
        File file = new File(fileDir);  
        File[] files = file.listFiles();// ��ȡĿ¼�µ������ļ����ļ���  
        if (files == null) {// ���Ŀ¼Ϊ�գ�ֱ���˳�  
            return  ;  
        }  
        // ������Ŀ¼�µ������ļ�  
        for (File f : files) {  
            if (f.isFile()) {  
                fileList.add(f);  
            	
            } else if (f.isDirectory()) {  
                //System.out.println(f.getAbsolutePath());  
                test(f.getAbsolutePath());  
            }  
        }  
        
        for (File f1 : fileList) {  
        	list.add(f1);
            //System.out.println(f1.getName());  
        }  
	 }  
  
    
}  