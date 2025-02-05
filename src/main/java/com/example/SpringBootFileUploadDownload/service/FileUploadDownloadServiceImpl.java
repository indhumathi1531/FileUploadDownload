package com.example.SpringBootFileUploadDownload.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringBootFileUploadDownload.Entity.FileUploadDownloadVO;
import com.example.SpringBootFileUploadDownload.Repository.FileUploadDownloadRepository;

@Service
public class FileUploadDownloadServiceImpl implements FileUploadDownloadService {

	public FileUploadDownloadRepository repo;

	public FileUploadDownloadServiceImpl(FileUploadDownloadRepository repo) {
		this.repo = repo;
	}

	@Override
	public FileUploadDownloadVO saveFile(MultipartFile file) throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new Exception("Filename contains Invalid sequence ");
			}
			FileUploadDownloadVO vo = new FileUploadDownloadVO(fileName, file.getContentType(), file.getBytes());
			return repo.save(vo);

		} catch (Exception e) {
			throw new Exception("Could not save file: " + fileName);
		}

	}

	@Override
	public FileUploadDownloadVO downloadFile(String fileId) throws Exception {

		return repo.findById(fileId).orElseThrow(() -> new Exception("File not found " + fileId));

	}

}
