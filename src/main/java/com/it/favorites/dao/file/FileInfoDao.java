package com.it.favorites.dao.file;

import com.it.favorites.model.file.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoDao extends JpaRepository<FileInfo, String> {

}
