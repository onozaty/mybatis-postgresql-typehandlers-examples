package com.github.onozaty.mybatis.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.github.onozaty.mybatis.example.domain.Day;
import com.github.onozaty.mybatis.example.domain.PgEnumRecord;

@SpringBootTest
@Transactional
public class PgEnumRepositoryTest {

    @Autowired
    private PgEnumRepository pgEnumRepository;

    @Test
    public void insert() {

        PgEnumRecord record = PgEnumRecord.builder()
                .day(Day.FRIDAY)
                .build();

        PgEnumRecord insertedRecord = pgEnumRepository.insert(record);

        assertThat(insertedRecord.getId()).isNotEqualTo(0);
        assertThat(insertedRecord.getDay()).isEqualTo(Day.FRIDAY);
    }

    @Test
    public void selectAll() {

        List<PgEnumRecord> insertedRecords = List.of(
                pgEnumRepository.insert(
                        PgEnumRecord.builder()
                                .day(Day.MONDAY)
                                .build()),
                pgEnumRepository.insert(
                        PgEnumRecord.builder()
                                .day(Day.THURSDAY)
                                .build()),
                pgEnumRepository.insert(
                        PgEnumRecord.builder()
                                .day(Day.SUNDAY)
                                .build()));

        List<PgEnumRecord> records = pgEnumRepository.selectAll();

        assertThat(records)
                .containsExactlyInAnyOrderElementsOf(insertedRecords);
    }

    @Test
    public void select() {

        PgEnumRecord insertedRecord = pgEnumRepository.insert(
                PgEnumRecord.builder()
                        .day(Day.WEDNESDAY)
                        .build());

        PgEnumRecord record = pgEnumRepository.select(insertedRecord.getId());

        assertThat(record).isEqualTo(insertedRecord);
    }

    @Test
    public void update() {

        PgEnumRecord before = pgEnumRepository.insert(
                PgEnumRecord.builder()
                        .day(Day.MONDAY)
                        .build());

        int id = before.getId();

        PgEnumRecord after = PgEnumRecord.builder()
                .id(id)
                .day(Day.SATURDAY)
                .build();

        pgEnumRepository.update(after);

        PgEnumRecord record = pgEnumRepository.select(id);

        assertThat(record).isEqualTo(after);
    }

}
