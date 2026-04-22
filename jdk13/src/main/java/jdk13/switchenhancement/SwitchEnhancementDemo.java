package jdk13.switchenhancement;

/**
 * JDK 13 Switch 表达式增强（预览特性）
 * 使用 yield 关键字从 switch 代码块中返回值
 */
public class SwitchEnhancementDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 13 Switch yield 演示 ===\n");

        // 使用 yield 在代码块中返回值
        for (int day = 1; day <= 7; day++) {
            String type = getDayType(day);
            System.out.println("星期 " + day + " 是: " + type);
        }

        System.out.println();

        // yield 可以在复杂逻辑中使用
        for (int score = 55; score <= 95; score += 20) {
            String grade = getGrade(score);
            System.out.println("分数 " + score + " 对应等级: " + grade);
        }
    }

    /**
     * 使用 yield 从 switch 代码块返回值
     */
    static String getDayType(int day) {
        return switch (day) {
            case 1, 2, 3, 4, 5 -> {
                System.out.println("  [debug] 工作日处理: day=" + day);
                yield "工作日";
            }
            case 6, 7 -> {
                System.out.println("  [debug] 休息日处理: day=" + day);
                yield "休息日";
            }
            default -> {
                System.out.println("  [debug] 无效日期: day=" + day);
                yield "无效日期";
            }
        };
    }

    /**
     * yield 支持更复杂的分支逻辑
     */
    static String getGrade(int score) {
        return switch (score / 10) {
            case 10 -> {
                if (score == 100) {
                    yield "满分";
                }
                yield "优秀";
            }
            case 9 -> "优秀";
            case 8 -> "良好";
            case 7 -> "中等";
            case 6 -> {
                System.out.println("  [debug] 及格线边缘");
                yield "及格";
            }
            default -> {
                System.out.println("  [debug] 不及格处理");
                yield "不及格";
            }
        };
    }
}
