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
@NoArgsConstructor
@AllArgsConstructor
public class JsonRecord {

    private int id;

    private JsonData json;
}
