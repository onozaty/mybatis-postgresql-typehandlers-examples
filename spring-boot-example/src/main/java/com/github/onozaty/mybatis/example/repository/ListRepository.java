package com.github.onozaty.mybatis.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.onozaty.mybatis.example.domain.ListRecord;
import com.github.onozaty.mybatis.pg.type.list.BooleanListTypeHandler;
import com.github.onozaty.mybatis.pg.type.list.DoubleListTypeHandler;
import com.github.onozaty.mybatis.pg.type.list.FloatListTypeHandler;
import com.github.onozaty.mybatis.pg.type.list.IntegerListTypeHandler;
import com.github.onozaty.mybatis.pg.type.list.LongListTypeHandler;
import com.github.onozaty.mybatis.pg.type.list.ShortListTypeHandler;
import com.github.onozaty.mybatis.pg.type.list.StringListTypeHandler;

/**
 * @author onozaty
 */
@Mapper
public interface ListRepository {

    @Select("""
INSERT INTO list_records (
  booleans,
  shorts,
  integers,
  longs,
  floats,
  doubles,
  strings
)
VALUES (
  #{booleans, typeHandler=booleanListTypeHandler},
  #{shorts, typeHandler=shortListTypeHandler},
  #{integers, typeHandler=integerListTypeHandler},
  #{longs, typeHandler=longListTypeHandler},
  #{floats, typeHandler=floatListTypeHandler},
  #{doubles, typeHandler=doubleListTypeHandler},
  #{strings, typeHandler=stringListTypeHandler}
)
RETURNING *
    """)
    @Results({
            @Result(property = "booleans", column = "booleans", typeHandler = BooleanListTypeHandler.class),
            @Result(property = "shorts", column = "shorts", typeHandler = ShortListTypeHandler.class),
            @Result(property = "integers", column = "integers", typeHandler = IntegerListTypeHandler.class),
            @Result(property = "longs", column = "longs", typeHandler = LongListTypeHandler.class),
            @Result(property = "floats", column = "floats", typeHandler = FloatListTypeHandler.class),
            @Result(property = "doubles", column = "doubles", typeHandler = DoubleListTypeHandler.class),
            @Result(property = "strings", column = "strings", typeHandler = StringListTypeHandler.class)
    })
    ListRecord insert(ListRecord record);

    @Select("""
SELECT * FROM list_records
    """)
    @Results({
            @Result(property = "booleans", column = "booleans", typeHandler = BooleanListTypeHandler.class),
            @Result(property = "shorts", column = "shorts", typeHandler = ShortListTypeHandler.class),
            @Result(property = "integers", column = "integers", typeHandler = IntegerListTypeHandler.class),
            @Result(property = "longs", column = "longs", typeHandler = LongListTypeHandler.class),
            @Result(property = "floats", column = "floats", typeHandler = FloatListTypeHandler.class),
            @Result(property = "doubles", column = "doubles", typeHandler = DoubleListTypeHandler.class),
            @Result(property = "strings", column = "strings", typeHandler = StringListTypeHandler.class)
    })
    List<ListRecord> selectAll();

    @Select("""
SELECT * FROM list_records WHERE id = #{id}
    """)
    @Results({
            @Result(property = "booleans", column = "booleans", typeHandler = BooleanListTypeHandler.class),
            @Result(property = "shorts", column = "shorts", typeHandler = ShortListTypeHandler.class),
            @Result(property = "integers", column = "integers", typeHandler = IntegerListTypeHandler.class),
            @Result(property = "longs", column = "longs", typeHandler = LongListTypeHandler.class),
            @Result(property = "floats", column = "floats", typeHandler = FloatListTypeHandler.class),
            @Result(property = "doubles", column = "doubles", typeHandler = DoubleListTypeHandler.class),
            @Result(property = "strings", column = "strings", typeHandler = StringListTypeHandler.class)
    })
    ListRecord select(int id);

    @Update("""
UPDATE list_records
SET
  id = #{id},
  booleans = #{booleans, typeHandler=booleanListTypeHandler},
  shorts = #{shorts, typeHandler=shortListTypeHandler},
  integers = #{integers, typeHandler=integerListTypeHandler},
  longs = #{longs, typeHandler=longListTypeHandler},
  floats = #{floats, typeHandler=floatListTypeHandler},
  doubles = #{doubles, typeHandler=doubleListTypeHandler},
  strings = #{strings, typeHandler=stringListTypeHandler}
WHERE
  id = #{id}
    """)
    void update(ListRecord record);
}
