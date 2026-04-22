package jdk8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 新日期时间 API Demo
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. LocalDate / LocalTime / LocalDateTime ===");
        // LocalDate: 日期（年月日）
        LocalDate today = LocalDate.now();
        System.out.println("LocalDate 今天: " + today);

        LocalDate specificDate = LocalDate.of(2026, 4, 22);
        System.out.println("LocalDate 指定日期: " + specificDate);

        // LocalTime: 时间（时分秒）
        LocalTime nowTime = LocalTime.now();
        System.out.println("LocalTime 现在: " + nowTime);

        LocalTime specificTime = LocalTime.of(14, 30, 0);
        System.out.println("LocalTime 指定时间: " + specificTime);

        // LocalDateTime: 日期时间
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime 现在: " + nowDateTime);

        LocalDateTime specificDateTime = LocalDateTime.of(2026, 4, 22, 14, 30);
        System.out.println("LocalDateTime 指定: " + specificDateTime);

        // 日期计算
        LocalDate tomorrow = today.plusDays(1);
        System.out.println("明天: " + tomorrow);
        LocalDate lastMonth = today.minusMonths(1);
        System.out.println("上个月: " + lastMonth);

        System.out.println("\n=== 2. ZonedDateTime ===");
        // 带时区的日期时间
        ZonedDateTime zonedNow = ZonedDateTime.now();
        System.out.println("当前时区时间: " + zonedNow);

        ZonedDateTime shanghaiTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("上海时间: " + shanghaiTime);

        ZonedDateTime newYorkTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("纽约时间: " + newYorkTime);

        System.out.println("\n=== 3. Instant ===");
        // Instant: 时间戳（UTC 时间线）
        Instant instant = Instant.now();
        System.out.println("Instant 现在: " + instant);
        System.out.println("Epoch 毫秒: " + instant.toEpochMilli());

        Instant later = instant.plusSeconds(60);
        System.out.println("60秒后: " + later);

        System.out.println("\n=== 4. DateTimeFormatter ===");
        LocalDateTime sample = LocalDateTime.of(2026, 4, 22, 14, 30, 0);

        // 预定义格式
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println("ISO 格式: " + sample.format(isoFormatter));

        // 自定义格式
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        System.out.println("自定义格式: " + sample.format(customFormatter));

        // 解析字符串
        String dateStr = "2026-04-22 14:30:00";
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsed = LocalDateTime.parse(dateStr, parser);
        System.out.println("解析结果: " + parsed);

        System.out.println("\n=== 5. Period / Duration ===");
        // Period: 日期之间的差值（年月日）
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2026, 4, 22);
        Period period = Period.between(startDate, endDate);
        System.out.println("Period 相差: " + period.getYears() + "年 " + period.getMonths() + "月 " + period.getDays() + "天");

        // Duration: 时间之间的差值（秒/纳秒）
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 30);
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Duration 相差小时: " + duration.toHours());
        System.out.println("Duration 相差分钟: " + duration.toMinutes());

        // ChronoUnit 计算差值
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("ChronoUnit 相差天数: " + daysBetween);
    }
}
