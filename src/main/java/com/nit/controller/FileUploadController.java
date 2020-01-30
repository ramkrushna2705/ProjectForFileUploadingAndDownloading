package com.nit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nit.command.RegisterCommand;

@Controller
public class FileUploadController {

	private static final String UPLOAD_BASE_DIR = "E:/ram";

	@RequestMapping(value = "/register.htm", method = RequestMethod.GET)
	public String showHomePage(@ModelAttribute("regCmd") RegisterCommand cmd) {
		return "register";

	}

	@RequestMapping(value = "/register.htm", method = RequestMethod.POST)
	public String processPage(Map<String, Object> map, @ModelAttribute("regCmd") RegisterCommand cmd) throws Exception {
		InputStream is1 = null, is2 = null;
		String fname1 = null, fname2 = null;
		File destFile1 = null, destFile2 = null;
		File destDir = null;
		OutputStream os1 = null, os2 = null;
		is1 = cmd.getFile1().getInputStream();
		is2 = cmd.getFile2().getInputStream();
		fname1 = cmd.getFile1().getOriginalFilename();
		fname2 = cmd.getFile2().getOriginalFilename();
		destDir = new File("E:/ram");
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		destFile1 = new File(destDir.getAbsolutePath() + "/" + fname1);
		destFile2 = new File(destDir.getAbsolutePath() + "/" + fname2);
		os1 = new FileOutputStream(destFile1);
		os2 = new FileOutputStream(destFile2);
		IOUtils.copy(is1, os1);
		IOUtils.copy(is2, os2);
		map.put("file1", fname1);
		map.put("file2", fname2);
		return "result";
	}

	@RequestMapping(value = "/download.htm")
	public String downloadFile(@RequestParam("file_name") String fname, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		System.out.println("filename::" + fname);
		File file = null;
		InputStream is = null;
		OutputStream os = null;
		res.addHeader("Content-Disposition", "attachment;fileName=" + fname);
		file = new File(UPLOAD_BASE_DIR + "/" + fname);
		res.setContentType(req.getServletContext().getMimeType(file.getAbsolutePath()));
		res.setContentLength((int) file.length());
		is = new FileInputStream(file);
		os = res.getOutputStream();
		IOUtils.copy(is, os);
		res.flushBuffer();
		return null;
	}
}
