package com.example.SpringBootFileUploadDownload.Entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class FileUploadDownloadVO {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String filename;
	private String fileType;
	@Lob
	private byte[] data;

	public FileUploadDownloadVO(String filename, String fileType, byte[] data) {
		this.filename = filename;
		this.fileType = fileType;
		this.data = data;
	}

}
