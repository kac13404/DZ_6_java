import java.util.*;

class Laptop {
    private String brand;
    private int ram;
    private int storage;
    private String os;
    private String color;

    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 8, 512, "Windows 10", "Black"));
        laptops.add(new Laptop("HP", 16, 1024, "Windows 10", "Silver"));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Silver"));

        Map<String, Object> filters = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");

            int criteria = scanner.nextInt();

            switch (criteria) {
                case 1:
                    System.out.println("Введите минимальное значение ОЗУ:");
                    int minRam = scanner.nextInt();
                    filters.put("ram", minRam);
                    break;
                case 2:
                    System.out.println("Введите минимальный объем ЖД:");
                    int minStorage = scanner.nextInt();
                    filters.put("storage", minStorage);
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    String os = scanner.next();
                    filters.put("os", os);
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    String color = scanner.next();
                    filters.put("color", color);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        for (Laptop laptop : laptops) {
            boolean passFilter = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (laptop.getRam() < (int) entry.getValue()) {
                            passFilter = false;
                        }
                        break;
                    case "storage":
                        if (laptop.getStorage() < (int) entry.getValue()) {
                            passFilter = false;
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equalsIgnoreCase((String) entry.getValue())) {
                            passFilter = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equalsIgnoreCase((String) entry.getValue())) {
                            passFilter = false;
                        }
                        break;
                }
            }
            if (passFilter) {
                System.out.println("Ноутбук " + laptop.getBrand() + " проходит по условиям фильтра.");
            }
        }
    }
}