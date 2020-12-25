package com.it.favorites.service.record;

import com.it.favorites.model.record.Record;

import java.util.List;

public interface RecordService {
    Record save(Record record);
    List<Record> findAll();
}
