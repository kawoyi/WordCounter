package zuoye;
import java.io.File;  
import java.util.ArrayList;  
import java.util.List;  
  
/** 
 * 递归读取某个目录下的所有文件 

 */ 
public class ReadFile {  
	public static List<File>list=new ArrayList<File>();
	/*public static List<File> getFileList(String strPath) {
		 List<File> filelist = new ArrayList<File>(); 
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("avi")) { // 判断文件名是否以.avi结尾
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
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
        if (files == null) {// 如果目录为空，直接退出  
            return  ;  
        }  
        // 遍历，目录下的所有文件  
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