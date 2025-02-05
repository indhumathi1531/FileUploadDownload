package com.example.SpringBootFileUploadDownload.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootFileUploadDownload.Entity.FileUploadDownloadVO;

@Repository
public interface FileUploadDownloadRepository extends JpaRepository<FileUploadDownloadVO, String> {

}
