package com.it.favorites.service.file.impl;

import com.it.favorites.config.GlobalProperties;
import com.it.favorites.dao.file.FileInfoDao;
import com.it.favorites.model.file.FileInfo;
import com.it.favorites.service.file.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoDao fileInfoDao;
    @Autowired
    private GlobalProperties properties;

    @Override
    public List<FileInfo> getFileList() {
        return fileInfoDao.findAll(Sort.by("top"));
    }

    @Override
    public FileInfo save(FileInfo record) {
        return fileInfoDao.save(record);
    }

    @Override
    public void deleteById(String id) {
        FileInfo fileInfo = fileInfoDao.getOne(id);
        String filePath = properties.getFilePath() + fileInfo.getLocalFilePath();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        fileInfoDao.deleteById(id);
    }
}
