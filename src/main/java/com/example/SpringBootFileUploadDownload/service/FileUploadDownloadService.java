package com.example.SpringBootFileUploadDownload.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.SpringBootFileUploadDownload.Entity.FileUploadDownloadVO;

public interface FileUploadDownloadService {

	FileUploadDownloadVO saveFile(MultipartFile file) throws Exception;

	FileUploadDownloadVO downloadFile(String fileId) throws Exception;

}
