package io.money.boot.wooquestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author lishijie-me
 * @date 2025/3/25 星期二 21:30:51
 * @description TimeInfo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeInfo {
    private String date;            // 当前日期
    private String dayOfWeek;       // 星期几
    private int dayOfYear;          // 年中第几天
    private int weekOfYear;         // 年中第几周
    private String festival;        // 节日（含农历）
}
