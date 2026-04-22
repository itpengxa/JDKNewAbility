package jdk15.textblocks;

/**
 * JDK 15 文本块（Text Blocks，正式特性）
 * 在 JDK 15 中，文本块从预览特性变为正式特性。
 * 新增 \s 转义序列和 formatted 方法。
 */
public class TextBlocksDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 15 文本块（正式特性）演示 ===\n");

        // 1. 基本多行字符串
        String poem = """
                春眠不觉晓，
                处处闻啼鸟。
                夜来风雨声，
                花落知多少。
                """;
        System.out.println("古诗文本块:");
        System.out.println(poem);

        // 2. \s 转义序列：显式保留单个空格（防止行尾空格被 stripping）
        String withSpaces = """
                行尾有空格   \s
                下一行
                """;
        System.out.println("使用 \\s 保留行尾空格:");
        System.out.println("[" + withSpaces + "]");
        System.out.println();

        // 3. formatted 方法：在文本块中使用格式化占位符
        String template = """
                姓名: %s
                年龄: %d
                城市: %s
                """;
        String formatted = template.formatted("张三", 30, "北京");
        System.out.println("使用 formatted 方法填充模板:");
        System.out.println(formatted);

        // 4. 直接在文本块上调用 formatted
        String report = """
                === 系统报告 ===
                时间: %s
                CPU 使用率: %.1f%%
                内存使用: %d MB
                状态: %s
                """.formatted("2024-01-15 10:30:00", 45.5, 2048, "正常");
        System.out.println("链式调用 formatted:");
        System.out.println(report);

        // 5. \ 行尾反斜杠：避免自动换行
        String singleLine = """
                这是一段很长的文字，\
                使用反斜杠可以将多行合并为单行输出，\
                适合书写长段落而不影响实际输出格式。
                """;
        System.out.println("使用 \\ 合并多行为一行:");
        System.out.println(singleLine);

        // 6. 文本块与字符串等价性
        String traditional = "第一行\n第二行\n第三行\n";
        String textBlock = """
                第一行
                第二行
                第三行
                """;
        System.out.println("传统字符串与文本块是否相等: " + traditional.equals(textBlock));
    }
}
