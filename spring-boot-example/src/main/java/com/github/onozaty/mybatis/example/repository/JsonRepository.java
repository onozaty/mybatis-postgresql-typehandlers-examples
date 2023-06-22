package com.github.onozaty.mybatis.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.onozaty.mybatis.example.domain.JsonRecord;

/**
 * @author onozaty
 */
@Mapper
public interface JsonRepository {

    @Select("""
INSERT INTO json_records (
  json
)
VALUES (
  #{json}
)
RETURNING *
    """)
    JsonRecord insert(JsonRecord record);

    @Select("""
SELECT * FROM json_records
    """)
    List<JsonRecord> selectAll();

    @Select("""
SELECT * FROM json_records WHERE id = #{id}
    """)
    JsonRecord select(int id);

    @Update("""
UPDATE json_records
SET
  json = #{json}
WHERE
  id = #{id}
    """)
    void update(JsonRecord record);
}
