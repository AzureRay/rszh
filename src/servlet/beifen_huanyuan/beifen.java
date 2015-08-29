package servlet.beifen_huanyuan;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.log.SysoCounter;

public class beifen {
	 public void down_FILE(HttpServletRequest req,HttpServletResponse resp,String szPath_file,String szFile_name)
	    {
	        String  szServer_path="";
	        szServer_path=szPath_file;
	        BufferedInputStream  bis = null;
	        BufferedOutputStream bos = null;
	        try
	        {
	           //和java.io.PrintWriter out 不能共用
	            ServletOutputStream outm = null;
	            try
	            {
	                outm=resp.getOutputStream();
	            }
	            catch(Exception e)
	            {
	                 //ExceptionOutput(resp,e.toString()+" abcd");
	            	e.printStackTrace();
	            }
	            try
	            {
	            resp.setContentType( "application/octet-stream" ); // MIME type for exe
	            resp.setHeader("Content-disposition","attachment; filename="+szFile_name);//显示的随机文件名，可以用这个保存或另起一个名
	            // Open file
	            File f1;
	            szServer_path = szServer_path+"\\"+szFile_name ;
	            System.out.println("szServer_path"+szServer_path);
	           
	                f1=new File(szServer_path);
	                System.out.println("fie");
	                FileInputStream fis=new FileInputStream(f1);
	                //System.out.println("fie1");
	                bis = new BufferedInputStream(fis);
	               // System.out.println("fie"+fis);
	                bos = new BufferedOutputStream(resp.getOutputStream());
	                System.out.println("fie1"+bos);
	                System.out.print(req.getAttribute("bos"));
	                byte[] buff = new byte[2048];
	                int bytesRead;
	                while(-1 != (bytesRead = bis.read(buff, 0, buff.length)))
	                {
	                    bos.write(buff, 0, bytesRead);
	                    System.out.println("fie2"+bos);
	                }
	                bos.flush();
	                System.out.println("fie3"+bos);
	            }
	            catch(Exception e)
	            {
	            	e.printStackTrace();
	            }
	        }
	        finally
	        {
	        	IOUtils.closeQuietly(bos);
	            try
	            {
	                if (bis != null) bis.close();
	                if (bos != null) bos.close();
	            }
	            catch(IOException e)
	            {
	            }
	        }
	    } 
}
