package org.example.DataTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Random;


public class DataTime {

    // 1
    public static void printCurrentDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + time.format(formatter));
    }

    // 2
    public static String compareDates(LocalDate date1, LocalDate date2) {
        if (date1.isAfter(date2)) return "Первая дата больше";
        if (date1.isBefore(date2)) return "Первая дата меньше";
        return "Даты равны";
    }

    // 3
    public static long daysUntilNewYear() {
        LocalDate now = LocalDate.now();
        LocalDate newYear = LocalDate.of(now.getYear() + 1, 1, 1);
        return ChronoUnit.DAYS.between(now, newYear);
    }

    // 4
    public static boolean isLeapYear(int year) {
        return Year.isLeap(year);
    }

    // 5
    public static long countWeekends(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        long weekends = 0;
        while (date.getMonthValue() == month) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                weekends++;
            }
            date = date.plusDays(1);
        }
        return weekends;
    }

    // 6
    public static void measureExecutionTime() {
        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++);
        long end = System.nanoTime();
        System.out.println("Время выполнения: " + (end - start) / 1000000 + " мс");
    }

    // 7
    public static void parseAndFormatDate(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateStr, inputFormatter);
        date = date.plusDays(10);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(date.format(outputFormatter));
    }

    // 8
    public static void convertTimeZone(LocalDateTime dateTime) {
        ZonedDateTime utcTime = dateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime moscowTime = utcTime.withZoneSameInstant(ZoneId.of("Europe/Moscow"));
        System.out.println(moscowTime);
    }

    // 9
    public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // 10
    public static void printCalendar(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        while (date.getMonthValue() == month) {
            System.out.println(date + " - " + (date.getDayOfWeek().getValue() >= 6 ? "Выходной" : "Рабочий день"));
            date = date.plusDays(1);
        }
    }

    // 11
    public static LocalDate getRandomDate(LocalDate start, LocalDate end) {
        long days = ChronoUnit.DAYS.between(start, end);
        return start.plusDays(new Random().nextInt((int) days + 1));
    }

    // 12
    public static void timeUntilEvent(LocalDateTime eventTime) {
        Duration duration = Duration.between(LocalDateTime.now(), eventTime);
        System.out.println("Осталось: " + duration.toHours() + " ч " + duration.toMinutesPart() + " м " + duration.toSecondsPart() + " с");
    }

    // 13
    public static long workingHours(LocalDateTime start, LocalDateTime end) {
        long hours = ChronoUnit.HOURS.between(start, end);
        return hours - (hours / 24 * 16);
    }

    // 14
    public static String formatDateWithLocale(LocalDate date, Locale locale) {
        return date.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", locale));
    }

    // 15
    public static String getDayOfWeek(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("EEEE", new Locale("ru")));
    }
}
