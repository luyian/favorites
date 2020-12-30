package com.it.favorites.dao.record;

import com.it.favorites.model.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordDao extends JpaRepository<Record, String> {
    /**
     * 保存
     * @param record
     * @return
     */
    Record save(Record record);

    /**
     * 列表
     * @return
     */
    List<Record> findAllByIdIsNotNullOrderByTopAsc();
}
