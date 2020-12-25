package com.it.favorites.service.record.impl;

import com.it.favorites.dao.record.RecordDao;
import com.it.favorites.model.record.Record;
import com.it.favorites.service.record.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    @Override
    public Record save(Record record) {
        return recordDao.save(record);
    }

    @Override
    public List<Record> findAll() {
        return recordDao.findAll();
    }
}
