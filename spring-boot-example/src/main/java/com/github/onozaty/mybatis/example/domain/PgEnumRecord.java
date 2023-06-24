package com.github.onozaty.mybatis.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author onozaty
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PgEnumRecord {

    private int id;

    private Day day;
}
