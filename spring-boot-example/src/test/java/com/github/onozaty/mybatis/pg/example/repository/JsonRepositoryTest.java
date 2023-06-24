package com.github.onozaty.mybatis.pg.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.github.onozaty.mybatis.pg.example.domain.JsonData;
import com.github.onozaty.mybatis.pg.example.domain.JsonRecord;

@SpringBootTest
@Transactional
public class JsonRepositoryTest {

    @Autowired
    private JsonRepository jsonRepository;

    @Test
    public void insert() {

        JsonData data = JsonData.builder()
                .id(1)
                .name("Taro")
                .build();

        JsonRecord record = JsonRecord.builder()
                .json(data)
                .build();

        JsonRecord insertedRecord = jsonRepository.insert(record);

        assertThat(insertedRecord.getId()).isNotEqualTo(0);
        assertThat(insertedRecord.getJson()).isEqualTo(data);
    }

    @Test
    public void selectAll() {

        List<JsonRecord> insertedRecords = List.of(
                jsonRepository.insert(
                        JsonRecord.builder()
                                .json(JsonData.builder().id(1).name("Taro").build())
                                .build()),
                jsonRepository.insert(
                        JsonRecord.builder()
                                .json(JsonData.builder().id(2).name("Hanako").build())
                                .build()),
                jsonRepository.insert(
                        JsonRecord.builder()
                                .json(JsonData.builder().id(3).name("Jack").build())
                                .build()));

        List<JsonRecord> records = jsonRepository.selectAll();

        assertThat(records)
                .containsExactlyInAnyOrderElementsOf(insertedRecords);
    }

    @Test
    public void select() {

        JsonRecord insertedRecord = jsonRepository.insert(
                JsonRecord.builder()
                        .json(JsonData.builder().id(1).name("Taro").build())
                        .build());

        JsonRecord record = jsonRepository.select(insertedRecord.getId());

        assertThat(record).isEqualTo(insertedRecord);
    }

    @Test
    public void update() {

        JsonRecord before = jsonRepository.insert(
                JsonRecord.builder()
                        .json(JsonData.builder().id(1).name("Taro").build())
                        .build());

        int id = before.getId();

        JsonRecord after = JsonRecord.builder()
                .id(id)
                .json(JsonData.builder().id(100).name("XYZ").build())
                .build();

        jsonRepository.update(after);

        JsonRecord record = jsonRepository.select(id);

        assertThat(record).isEqualTo(after);
    }

}
