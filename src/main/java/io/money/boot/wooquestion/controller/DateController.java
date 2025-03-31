package io.money.boot.wooquestion.controller;

/**
 * @author lishijie-me
 * @date 2025/3/30 星期日 21:27:25
 * @description DateController
 */
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import io.money.boot.wooquestion.util.FestivalUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Controller
public class DateController {

    @GetMapping("/")
    public String index(Model model) {
        LocalDate birthDate = LocalDate.of(1993, 6, 13);
        LocalDate now = LocalDate.now();

        // 计算存活天数
        long between = ChronoUnit.DAYS.between(birthDate, now);
        long days = between+1L;

        // 计算年龄
        int age = Period.between(birthDate, now).getYears();

        // 农历日期
        Solar solar = new Solar(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        Lunar lunar = solar.getLunar();
        String lunarDate = lunar.getYearInGanZhi() + "年"
                + (lunar.getMonth() < 0 ? "闰" : "")
                + lunar.getMonthInChinese() + "月"
                + lunar.getDayInChinese();

        // 当前日期格式化
        String currentDate = now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));

        // 星期几
        String weekDay = now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA);
        // 添加周末判断
        boolean isWeekend = now.getDayOfWeek() == DayOfWeek.SATURDAY
                || now.getDayOfWeek() == DayOfWeek.SUNDAY;
        model.addAttribute("isWeekend", isWeekend);

        // 本年第几周
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 4);
        int weekNumber = now.get(weekFields.weekOfWeekBasedYear());
        // 本年的第几天
        int dayOfYear = now.getDayOfYear();

        model.addAttribute("days", days);
        model.addAttribute("age", age);
        model.addAttribute("lunarDate", lunarDate);
        model.addAttribute("currentDate", currentDate);
        model.addAttribute("weekDay", weekDay);
        model.addAttribute("weekNumber", weekNumber);
        model.addAttribute("dayOfYear", dayOfYear);

        String holiday = FestivalUtils.getLunarFestival(now);
        model.addAttribute("holiday", holiday);

        return "index";
    }

    @GetMapping("/another-page")
    public String anotherPage(@RequestParam(required = false, name = "source", defaultValue = "direct") String source,
                              @RequestParam(name = "time", required = false) String timeParam,
                              Model model) {
        // 自动添加当前时间戳（如果未传时间参数）
        String time = (timeParam != null) ? timeParam : LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        model.addAttribute("source", source);
        model.addAttribute("timeParam", time);
        return "another";
    }
}
