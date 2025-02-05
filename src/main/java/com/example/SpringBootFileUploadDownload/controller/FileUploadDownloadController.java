package com.example.SpringBootFileUploadDownload.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.SpringBootFileUploadDownload.Entity.FileUploadDownloadVO;
import com.example.SpringBootFileUploadDownload.Model.ResponseData;
import com.example.SpringBootFileUploadDownload.service.FileUploadDownloadService;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;

@RestController
public class FileUploadDownloadController {

	public FileUploadDownloadService service;

	public FileUploadDownloadController(FileUploadDownloadService service) {
		this.service = service;
	}

	@PostMapping("/upload")
	public ResponseData uploadFile(@RequestParam("File") MultipartFile file) throws Exception {
		FileUploadDownloadVO vo = null;
		String downloadURL = "";
		vo = service.saveFile(file);
		downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download").path(vo.getId())
				.toUriString();
		return new ResponseData(vo.getFilename(), downloadURL, file.getSize(), file.getContentType());
	}

	@GetMapping("/download{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
		FileUploadDownloadVO vo = null;
		vo = service.downloadFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(vo.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + vo.getFilename() + "\"")
				.body(new ByteArrayResource(vo.getData()));
	}

}
