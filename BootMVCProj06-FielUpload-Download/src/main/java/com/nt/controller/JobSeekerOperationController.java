package com.nt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.JobSeekerData;
import com.nt.entity.JobSeekerInfo;
import com.nt.service.IJobSeekerMgmtService;

@Controller
public class JobSeekerOperationController {
	@Autowired
	private IJobSeekerMgmtService service;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ServletContext sc;

	@GetMapping("/")
	public String showHomePage() {
		return "welcome";
	}

	@GetMapping("/register")
	public String showJSRegistrationForm(@ModelAttribute("js") JobSeekerData jsData) {
		return "jobseeker_register";
	}

	@PostMapping("/register")
	public String registerJSByUploadingFiles(@ModelAttribute("js") JobSeekerData jsData, Map<String, Object> map)
			throws Exception {

		//get upload folder location
		String storeLocation =env.getRequiredProperty("upload.store");
		//if that not available then create
		File file = new File(storeLocation);
		if (!file.exists()) {
			file.mkdir();
		}

		//get input streams representing the upload file content
		MultipartFile resumeFile = jsData.getResume();
		MultipartFile photoFile = jsData.getPhoto();
		InputStream isResume = resumeFile.getInputStream();
		InputStream isPhoto = photoFile.getInputStream();

		//get the name of the uploaded file
		String resumeFileName = resumeFile.getOriginalFilename();
		String photoFileName = photoFile.getOriginalFilename();

		//create outstreams representing empty destination files
		OutputStream osResume = new FileOutputStream(file.getAbsolutePath() + "//" + resumeFileName);
		OutputStream osPhoto = new FileOutputStream(file.getAbsolutePath() + "//" + photoFileName);

		//perform file copy operation
		IOUtils.copy(isResume, osResume);
		IOUtils.copy(isPhoto, osPhoto);

		//close stream
		isResume.close();
		osResume.close();
		isPhoto.close();
		isResume.close();

		//prepare entiry class obj from model class obj
		JobSeekerInfo info = new JobSeekerInfo();
		info.setJsName(jsData.getJsName());
		info.setJsAddrs(jsData.getJsAddrs());
		info.setResumePath(file.getAbsolutePath() + "/" + resumeFileName);
		info.setPhotoPath(file.getAbsolutePath() + "/" + photoFileName);

		String msg = service.registerJobSeeker(info);
		//keep the uploaded file names and location in model attributes
		map.put("file1", resumeFileName);
		map.put("file2", photoFileName);
		map.put("resultMsg", msg);
		return "show_result";
	}
	
	@GetMapping("/list_js")
	public String showReport(Map<String, Object> map) {
		System.out.println("JobSeekerOperationController.showReport()");
		
		
		List<JobSeekerInfo> info=service.fetchAllJobSeeker();
		map.put("jsList", info);
		
		return "show_report";
	}
	
	@GetMapping("/download")
	public String fileDownload(HttpServletResponse res,@ModelAttribute("jsId")Integer jsId,@ModelAttribute("type")String type) throws IOException {
		String filePath=null;
		if(type.equalsIgnoreCase("resume")) {
			filePath=service.fetchResumePathByJsId(jsId);
		}else {
			filePath=service.fetchPhotoPathByJsId(jsId);
		}
		File file=new File(filePath);
		
		//get the length of the file and make it as the response content length
		res.setContentLengthLong(file.length());
		
		//get mime type of the type and make it as the response content type
		String mimeType=sc.getMimeType(filePath);
		mimeType=mimeType==null?"application/octet-stream":mimeType;
		res.setContentType(mimeType);
		
		//creating inputstream pointing to the file
		InputStream is=new FileInputStream(file);
		//create output stream pointing to the response obj
		OutputStream os=res.getOutputStream();
		
		//instruct the browser to give 
		res.setHeader("Content-Disposition","attachment;fileName="+file.getName());
		
		IOUtils.copy(is, os);
		is.close();
		os.close();
		
		return null;
	}

}
