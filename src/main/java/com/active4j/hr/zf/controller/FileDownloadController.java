package com.active4j.hr.zf.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.active4j.hr.zf.entity.DraftEntity;
import com.active4j.hr.zf.service.DraftService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/fileDownloadController")
public class FileDownloadController {
	
	@Autowired
	private DraftService draftService;
	
	@RequestMapping("/download")
    public void  testDownload(HttpServletResponse response,HttpServletRequest request,String draftrId) {
		if(draftrId!=null && !draftrId.isEmpty()) {
			DraftEntity draftEntity = draftService.getById(draftrId);
			String fomart = draftEntity.getFilePath().substring(draftEntity.getFilePath().lastIndexOf("."));
			 //待下载文件名
	        String fileName = draftEntity.getTitle();
	        // 使用URLEncoding.decode对文件名进行解码
	        try {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} 
	      
            String uploadPath = request.getRealPath("/zfFile");
            File file = new File(uploadPath +"/"+ draftEntity.getFilePath());
            long fileLength = 0L;
               if(file.exists() && file.isFile()){
                    fileLength = file.length();
            }
	        response.setHeader("content-type", "image/png");
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName+fomart);
	        response.addHeader("Content-Length", "" + fileLength);   
	        byte[] buff = new byte[1024];
	        //创建缓冲输入流
	        BufferedInputStream bis = null;
	        OutputStream outputStream = null;

	        try {
	            outputStream = response.getOutputStream();
	            bis = new BufferedInputStream(new FileInputStream(file));
	            int read = bis.read(buff);

	            //通过while循环写入到指定了的文件夹中
	            while (read != -1) {
	                outputStream.write(buff, 0, buff.length);
	                outputStream.flush();
	                read = bis.read(buff);
	            }
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	            if (bis != null) {
	                try {
	                    bis.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (outputStream != null) {
	                try {
	                    outputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		}
       
    }
}
