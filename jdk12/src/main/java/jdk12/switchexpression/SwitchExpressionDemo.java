package jdk12.switchexpression;

/**
 * JDK 12 Switch 表达式（预览特性）
 * 展示箭头语法、返回值，switch 可作为表达式使用
 */
public class SwitchExpressionDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 12 Switch 表达式演示 ===\n");

        // 使用箭头语法的 switch 表达式
        for (int day = 1; day <= 7; day++) {
            String dayName = getDayName(day);
            System.out.println("星期 " + day + " 对应: " + dayName);
        }

        System.out.println();

        // 使用 break 返回值的传统 switch 表达式风格（JDK 12 预览）
        for (int month = 1; month <= 3; month++) {
            String season = getSeason(month);
            System.out.println("月份 " + month + " 属于: " + season);
        }
    }

    /**
     * 箭头语法：switch 作为表达式直接返回值
     */
    static String getDayName(int day) {
        return switch (day) {
            case 1 -> "星期一";
            case 2 -> "星期二";
            case 3 -> "星期三";
            case 4 -> "星期四";
            case 5 -> "星期五";
            case 6 -> "星期六";
            case 7 -> "星期日";
            default -> "未知";
        };
    }

    /**
     * 使用 break 返回值（JDK 12 预览特性语法）
     */
    static String getSeason(int month) {
        return switch (month) {
            case 12, 1, 2:
                break "冬季";
            case 3, 4, 5:
                break "春季";
            case 6, 7, 8:
                break "夏季";
            case 9, 10, 11:
                break "秋季";
            default:
                break "未知季节";
        };
    }
}
