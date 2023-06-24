package com.github.onozaty.mybatis.pg.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.github.onozaty.mybatis.pg.example.domain.ListRecord;

@SpringBootTest
@Transactional
public class ListRepositoryTest {

    @Autowired
    private ListRepository listRepository;

    @Test
    public void insert() {

        ListRecord record = ListRecord.builder()
                .booleans(List.of(true, false, true))
                .shorts(List.of(Short.MIN_VALUE, Short.MAX_VALUE))
                .integers(List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE))
                .longs(List.of(Long.MIN_VALUE, Long.MAX_VALUE))
                .floats(List.of(Float.MIN_VALUE, Float.MAX_VALUE))
                .doubles(List.of(Double.MIN_VALUE, Double.MAX_VALUE))
                .strings(List.of("あ", "A"))
                .build();

        ListRecord insertedRecord = listRepository.insert(record);

        assertThat(insertedRecord.getId()).isNotEqualTo(0);
        assertThat(insertedRecord)
                .usingRecursiveComparison().ignoringFields("id")
                .isEqualTo(record);
    }

    @Test
    public void selectAll() {

        List<ListRecord> insertedRecords = List.of(
                listRepository.insert(
                        ListRecord.builder()
                                .booleans(List.of(true, false, true))
                                .shorts(List.of(Short.MIN_VALUE, Short.MAX_VALUE))
                                .integers(List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE))
                                .longs(List.of(Long.MIN_VALUE, Long.MAX_VALUE))
                                .floats(List.of(Float.MIN_VALUE, Float.MAX_VALUE))
                                .doubles(List.of(Double.MIN_VALUE, Double.MAX_VALUE))
                                .strings(List.of("あ", "A"))
                                .build()),
                listRepository.insert(
                        ListRecord.builder()
                                .booleans(List.of(true))
                                .shorts(List.of((short) 0))
                                .integers(List.of(1, 2, 3))
                                .longs(List.of(1L, 2L))
                                .floats(List.of(0.1f, 0.2f, 0.3f))
                                .doubles(List.of(0.2, 0.3))
                                .strings(Arrays.asList("", null)) // List.ofだとnull入れられないので
                                .build()),
                listRepository.insert(
                        ListRecord.builder()
                                .booleans(List.of())
                                .shorts(List.of())
                                .integers(List.of())
                                .longs(List.of())
                                .floats(List.of())
                                .doubles(List.of())
                                .strings(List.of())
                                .build()),
                listRepository.insert(
                        ListRecord.builder()
                                .booleans(null)
                                .shorts(null)
                                .integers(null)
                                .longs(null)
                                .floats(null)
                                .doubles(null)
                                .strings(null)
                                .build()));

        List<ListRecord> records = listRepository.selectAll();

        assertThat(records)
                .containsExactlyInAnyOrderElementsOf(insertedRecords);
    }

    @Test
    public void select() {

        ListRecord insertedRecord = listRepository.insert(
                ListRecord.builder()
                        .booleans(List.of(true, false, true))
                        .shorts(List.of(Short.MIN_VALUE, Short.MAX_VALUE))
                        .integers(List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE))
                        .longs(List.of(Long.MIN_VALUE, Long.MAX_VALUE))
                        .floats(List.of(Float.MIN_VALUE, Float.MAX_VALUE))
                        .doubles(List.of(Double.MIN_VALUE, Double.MAX_VALUE))
                        .strings(List.of("あ", "A"))
                        .build());

        ListRecord record = listRepository.select(insertedRecord.getId());

        assertThat(record).isEqualTo(insertedRecord);
    }

    @Test
    public void update() {

        ListRecord before = listRepository.insert(
                ListRecord.builder()
                        .booleans(List.of(true, false, true))
                        .shorts(List.of(Short.MIN_VALUE, Short.MAX_VALUE))
                        .integers(List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE))
                        .longs(List.of(Long.MIN_VALUE, Long.MAX_VALUE))
                        .floats(List.of(Float.MIN_VALUE, Float.MAX_VALUE))
                        .doubles(List.of(Double.MIN_VALUE, Double.MAX_VALUE))
                        .strings(List.of("あ", "A"))
                        .build());

        int id = before.getId();

        ListRecord after = ListRecord.builder()
                .id(id)
                .booleans(List.of(false))
                .shorts(List.of(Short.MAX_VALUE))
                .integers(List.of(Integer.MIN_VALUE))
                .longs(List.of(Long.MIN_VALUE))
                .floats(List.of())
                .doubles(List.of(Double.MAX_VALUE))
                .strings(List.of(""))
                .build();

        listRepository.update(after);

        ListRecord record = listRepository.select(id);

        assertThat(record).isEqualTo(after);
    }
}
