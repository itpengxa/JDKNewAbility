package jdk14.switchexpression;

/**
 * JDK 14 Switch 表达式（标准特性）
 * 在 JDK 14 中，Switch 表达式从预览特性变为标准特性。
 * 支持箭头语法和 yield 返回值。
 */
public class SwitchExpressionDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 14 Switch 表达式（标准特性）演示 ===\n");

        // 箭头语法直接返回值
        for (int month = 1; month <= 12; month++) {
            String season = getSeason(month);
            System.out.printf("月份 %2d -> %s%n", month, season);
        }

        System.out.println();

        // 使用 yield 的代码块形式
        String[] inputs = {"A", "B", "C", "D", "E"};
        for (String input : inputs) {
            String result = evaluateGrade(input);
            System.out.println("等级 " + input + " -> " + result);
        }

        System.out.println();

        // switch 表达式作为方法参数
        int day = 5;
        System.out.println("星期 " + day + " 是 " +
                switch (day) {
                    case 1, 2, 3, 4, 5 -> "工作日";
                    case 6, 7 -> "周末";
                    default -> "未知";
                });
    }

    static String getSeason(int month) {
        return switch (month) {
            case 3, 4, 5 -> "春季";
            case 6, 7, 8 -> "夏季";
            case 9, 10, 11 -> "秋季";
            case 12, 1, 2 -> "冬季";
            default -> throw new IllegalArgumentException("非法月份: " + month);
        };
    }

    static String evaluateGrade(String grade) {
        return switch (grade) {
            case "A" -> {
                System.out.println("  [处理] 优秀等级");
                yield "优秀";
            }
            case "B" -> {
                System.out.println("  [处理] 良好等级");
                yield "良好";
            }
            case "C" -> {
                System.out.println("  [处理] 中等等级");
                yield "中等";
            }
            case "D" -> {
                System.out.println("  [处理] 及格等级");
                yield "及格";
            }
            default -> {
                System.out.println("  [处理] 未知等级");
                yield "未知";
            }
        };
    }
}
