//package io.money.boot.wooquestion.controller;
//
///**
// * @author lishijie-me
// * @date 2025/3/25 星期二 19:37:33
// * @description DemoController
// */
//
//import io.money.boot.wooquestion.dto.TimeInfo;
//import io.money.boot.wooquestion.util.FestivalUtils;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import reactor.core.publisher.Flux;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.WeekFields;
//import java.util.Locale;
//
//@Controller
//public class PageController {
//
//    @GetMapping("/")
//    public String index() {
//        return "index"; // 返回index.html模板
//    }
//
//    @GetMapping("/home")
//    public String home() {
//        return "home"; // 返回index.html模板
//    }
//
//    @GetMapping("/api/time")
//    @ResponseBody
//    public String getServerTime() {
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//    }
//
//    // PageController.java
//    @GetMapping(path = "/sse/time", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<String> streamTime() {
//        return Flux.interval(Duration.ofSeconds(1))
//                .map(sequence -> LocalDateTime.now().format(
//                        DateTimeFormatter.ofPattern("HH:mm:ss")));
//    }
//
//    // SSE 流接口
//    @GetMapping(value = "/sse/time-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<TimeInfo> streamTimeAll() {
//        return Flux.interval(Duration.ofSeconds(1))
//                .map(seq -> getTimeInfo());
//    }
//
////    public static void main(String[] args) {
////        LocalDateTime now = LocalDateTime.of(2025, 1, 29, 12,34,56);
////        TimeInfo eeee = new TimeInfo(
////                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
////                now.format(DateTimeFormatter.ofPattern("EEEE", Locale.CHINA)),
////                now.getDayOfYear(),
////                now.get(WeekFields.of(Locale.CHINA).weekOfYear()),
////                FestivalUtils.getLunarFestival(now)
////        );
////        System.out.println(eeee);
////    }
//
//    public TimeInfo getTimeInfo() {
//        LocalDateTime now = LocalDateTime.now();
//
//        return new TimeInfo(
//                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                now.format(DateTimeFormatter.ofPattern("EEEE", Locale.CHINA)),
//                now.getDayOfYear(),
//                now.get(WeekFields.of(Locale.CHINA).weekOfYear()),
//                FestivalUtils.getLunarFestival(now)
//        );
//    }
//}
