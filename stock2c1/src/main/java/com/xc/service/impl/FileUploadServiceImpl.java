package com.xc.service.impl;

import com.google.common.collect.Lists;
import com.xc.common.ServerResponse;
import com.xc.service.IFileUploadService;
import com.xc.utils.FTPUtil;
import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service("iFileUploadService")

public class FileUploadServiceImpl

        implements IFileUploadService {

    private static final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);


    public ServerResponse upload(MultipartFile file, String path) {

        String fileName = file.getOriginalFilename();


        String fileExtentionName = fileName.substring(fileName.lastIndexOf(".") + 1);


        String uploadFileName = UUID.randomUUID() + "." + fileExtentionName;


        File fileDir = new File(path);


        if (!fileDir.exists()) {


            fileDir.setWritable(true);

            fileDir.mkdirs();

        }


        File tartgetFile = new File(path, uploadFileName);

        boolean result = false;

        try {

            file.transferTo(tartgetFile);


            result = FTPUtil.uploadFile(Lists.newArrayList(new File[]{tartgetFile}));


            tartgetFile.delete();

        } catch (Exception e) {

            log.error("上传文件异常 , 错误信息 = {}", e);

            return null;

        }


        if (result) {

            return ServerResponse.createBySuccess(tartgetFile.getName());

        }

        return ServerResponse.createByErrorMsg("上传失败");

    }

}
