package com.it.favorites.dao.record;

import com.it.favorites.model.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordDao extends JpaRepository<Record, String> {

    /**
     * 列表
     * @return
     */
    List<Record> findAllByIdIsNotNullOrderByTopAsc();
}
