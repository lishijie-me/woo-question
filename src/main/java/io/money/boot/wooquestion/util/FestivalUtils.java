package io.money.boot.wooquestion.util;

/**
 * @author lishijie-me
 * @date 2025/3/25 星期二 22:29:16
 * @description LunarUtils
 */
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.HashMap;
import java.util.Map;

public class FestivalUtils {

    /**
     * 获取农历日期和节日
     * @param date 公历日期
     * @return 节日名称（无节日返回空字符串）
     */
    public static String getLunarFestival(LocalDate date) {
        // 公历转农历
        Solar solar = new Solar(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        Lunar lunar = solar.getLunar();
        // 农历节日判断
        String festival = checkLunarFestival(lunar);
        // 节气
        if (festival.isEmpty()) {
            festival = lunar.getJieQi();
        }else{
            festival = festival + "/" + lunar.getJieQi();
        }
        // 补充阳历节日
        if (festival.isEmpty()) {
            festival = getSolarFestival(date);
        }else {
            festival = festival + "/" + getSolarFestival(date);
        }
        return festival;
    }

    // 获取农历节假日判断方法
    private static String checkLunarFestival(Lunar lunar) {
        return lunar.getFestivals().stream()
                .filter(f -> !f.isEmpty())
                .findFirst()
                .orElse("");
    }

    /**
     * 获取阳历节日
     */
    private static String getSolarFestival(LocalDate date) {
        Map<MonthDay, String> SOLAR_FESTIVALS = new HashMap<>();
        SOLAR_FESTIVALS.put(MonthDay.of(1, 1), "元旦");
        SOLAR_FESTIVALS.put(MonthDay.of(2, 14), "情人节");
        SOLAR_FESTIVALS.put(MonthDay.of(3, 12), "植树节");
        SOLAR_FESTIVALS.put(MonthDay.of(3, 15), "消费者日");
        SOLAR_FESTIVALS.put(MonthDay.of(5, 1), "劳动节");
        SOLAR_FESTIVALS.put(MonthDay.of(10, 1), "国庆节");
        // 添加更多阳历节日...

        return SOLAR_FESTIVALS.getOrDefault(
                MonthDay.of(date.getMonth(), date.getDayOfMonth()),
                ""
        );
    }
}
