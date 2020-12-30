package com.it.favorites.service.file.impl;

import com.it.favorites.dao.file.FileInfoDao;
import com.it.favorites.model.file.FileInfo;
import com.it.favorites.service.file.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoDao fileInfoDao;

    @Override
    public List<FileInfo> getFileList() {
        return fileInfoDao.findAll(Sort.by("top"));
    }

    @Override
    public FileInfo save(FileInfo record) {
        return fileInfoDao.save(record);
    }
}
