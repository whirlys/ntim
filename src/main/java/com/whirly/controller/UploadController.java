package com.whirly.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whirly.fastdfs.FastDFSClient;
import com.whirly.fastdfs.FastDFSFile;
import com.whirly.model.User;
import com.whirly.service.FileService;
import com.whirly.service.UserService;

@Controller
@RequestMapping("upload")
public class UploadController {
	private static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "avatar", method = RequestMethod.POST) // new annotation since 4.3
	@ResponseBody
	public Map<String, Object> singleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (file.isEmpty()) {
			map.put("code", 1);
			map.put("msg", "图片为空！上传失败");
			return map;
		}
		try {
			// Get the file and save it somewhere
			String path = saveFile(file);
			// file.getOriginalFilename()
			Map<String, Object> data = new HashMap<String, Object>();

			User user = (User) session.getAttribute("user");
			user.setAvatar(path);
			userService.updateAvatar(user);
			session.setAttribute("user", user);
			map.put("msg", "上传成功!");
			data.put("src", path);
			map.put("data", data);
		} catch (Exception e) {
			logger.error("upload file failed", e);
			map.put("code", 1);
			map.put("msg", "upload file failed: " + e);
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * 即时通讯上传图片
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "im/images", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> imImages(@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (file.isEmpty()) {
			map.put("code", 1);
			map.put("msg", "图片为空！上传失败");
			return map;
		}
		try {
			// Get the file and save it somewhere
			String path = saveFile(file);
			// file.getOriginalFilename()
			Map<String, Object> data = new HashMap<String, Object>();
			map.put("msg", "上传成功!");
			data.put("src", path);
			map.put("data", data);
		} catch (Exception e) {
			logger.error("upload file failed", e);
			map.put("code", 1);
			map.put("msg", "upload file failed: " + e);
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * 即时通讯上传文件
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "im/files", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> imFiles(@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (file.isEmpty()) {
			map.put("code", 1);
			map.put("msg", "文件为空！上传失败");
			return map;
		}
		try {
			// Get the file and save it somewhere
			String path = saveFile(file);
			// file.getOriginalFilename()
			Map<String, Object> data = new HashMap<String, Object>();
			map.put("msg", "上传成功!");

			data.put("src", path);
			data.put("name", file.getOriginalFilename());
			map.put("data", data);
		} catch (Exception e) {
			logger.error("upload file failed", e);
			map.put("code", 1);
			map.put("msg", "upload file failed: " + e);
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * layedit富文本编辑器上传图片
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "layedit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> layedit(@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (file.isEmpty()) {
			map.put("code", 1);
			map.put("msg", "图片为空！上传失败");
			return map;
		}
		try {
			// Get the file and save it somewhere
			String path = saveFile(file);
			// file.getOriginalFilename()
			Map<String, Object> data = new HashMap<String, Object>();
			map.put("msg", "上传成功!");

			data.put("src", path);
			data.put("title", file.getOriginalFilename());
			map.put("data", data);
		} catch (Exception e) {
			logger.error("upload file failed", e);
			map.put("code", 1);
			map.put("msg", "upload file failed: " + e);
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * 用于文件检索，从教务处网页上爬下来的文件，发布者上传的各种常用文件，如通知附件
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> file(@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (file.isEmpty()) {
			map.put("code", 1);
			map.put("msg", "图片为空！上传失败");
			return map;
		}
		try {
			String path = saveFile(file);
			Map<String, Object> data = new HashMap<String, Object>();
			map.put("msg", "上传成功!");

			data.put("src", path);
			data.put("title", file.getOriginalFilename());
			map.put("data", data);
		} catch (Exception e) {
			logger.error("upload file failed", e);
			map.put("code", 1);
			map.put("msg", "upload file failed: " + e);
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	public String saveFile(MultipartFile multipartFile) throws IOException {
		String[] fileAbsolutePath = {};
		String fileName = multipartFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		byte[] file_buff = null;
		InputStream inputStream = multipartFile.getInputStream();
		if (inputStream != null) {
			int len1 = inputStream.available();
			file_buff = new byte[len1];
			inputStream.read(file_buff);
		}
		inputStream.close();
		FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
		try {
			fileAbsolutePath = FastDFSClient.upload(file); // upload to fastdfs
		} catch (Exception e) {
			logger.error("upload file Exception!", e);
		}
		if (fileAbsolutePath == null) {
			logger.error("upload file failed,please upload again!");
		}
		String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
		return path;
	}
}