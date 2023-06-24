package com.github.onozaty.mybatis.pg.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.onozaty.mybatis.pg.example.domain.PgEnumRecord;

/**
 * @author onozaty
 */
@Mapper
public interface PgEnumRepository {

    @Select("""
INSERT INTO pgenum_records (
  day
)
VALUES (
  #{day}
)
RETURNING *
    """)
    PgEnumRecord insert(PgEnumRecord record);

    @Select("""
SELECT * FROM pgenum_records
    """)
    List<PgEnumRecord> selectAll();

    @Select("""
SELECT * FROM pgenum_records WHERE id = #{id}
    """)
    PgEnumRecord select(int id);

    @Update("""
UPDATE pgenum_records
SET
  day = #{day}
WHERE
  id = #{id}
    """)
    void update(PgEnumRecord record);
}
