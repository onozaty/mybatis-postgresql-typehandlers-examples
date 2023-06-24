package com.github.onozaty.mybatis.pg.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author onozaty
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonData {

    private int id;

    private String name;
}
