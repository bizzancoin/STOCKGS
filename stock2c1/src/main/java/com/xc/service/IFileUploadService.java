package com.xc.service;


import com.xc.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {
  ServerResponse upload(MultipartFile paramMultipartFile, String paramString);
}