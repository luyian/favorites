package com.it.favorites.service.file;

import com.it.favorites.model.file.FileInfo;

import java.util.List;

public interface FileInfoService {
    /**
     * 获取文件列表
     * @return
     */
    List<FileInfo> getFileList();

    /**
     * 保存
     * @param record
     * @return
     */
    FileInfo save(FileInfo record);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);
}
