package org.example.Singleton_Enum;

import java.util.*;

public class SingletonEnum {

    // 1
    class Database {
        private static Database instance;

        private Database() {
            System.out.println("Создано подключение к базе данных");
        }

        public Database getInstance() {
            if (instance == null) {
                instance = new Database();
            }
            return instance;
        }
    }

    // 2
    class Logger {
        private static Logger instance;
        private List<String> logs = new ArrayList<>();

        private Logger() {}

        public Logger getInstance() {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }

        public void log(String message) {
            logs.add(message);
        }

        public void showLogs() {
            logs.forEach(System.out::println);
        }
    }

    // 3
    enum OrderStatus {
        NEW, IN_PROGRESS, DELIVERED, CANCELLED
    }

    class Order {
        private int id;
        private OrderStatus status;

        public Order(int id) {
            this.id = id;
            this.status = OrderStatus.NEW;
        }

        public void changeStatus(OrderStatus newStatus) {
            if (this.status == OrderStatus.DELIVERED && newStatus == OrderStatus.CANCELLED) {
                System.out.println("Нельзя отменить доставленный заказ");
                return;
            }
            this.status = newStatus;
        }

        public void printStatus() {
            System.out.println("Заказ " + id + " - статус: " + status);
        }
    }

    // 4
    enum Season {
        WINTER, SPRING, SUMMER, AUTUMN
    }

    class SeasonTranslator {
        public static String getSeasonName(Season season) {
            switch (season) {
                case WINTER: return "Зима";
                case SPRING: return "Весна";
                case SUMMER: return "Лето";
                case AUTUMN: return "Осень";
                default: return "Неизвестный сезон";
            }
        }
    }
}
