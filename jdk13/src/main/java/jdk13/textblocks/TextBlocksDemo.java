package jdk13.textblocks;

/**
 * JDK 13 文本块（Text Blocks，预览特性）
 * 使用 """ 定义多行字符串，保留换行和缩进
 */
public class TextBlocksDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 13 文本块演示 ===\n");

        // 1. 基本用法：多行字符串
        String json = """
                {
                    "name": "张三",
                    "age": 30,
                    "city": "北京"
                }
                """;
        System.out.println("基本 JSON 文本块:");
        System.out.println(json);

        // 2. 文本块中的转义
        String html = """
                <html>
                    <body>
                        <h1>Hello JDK 13</h1>
                        <p>这是第一行\n这是强制换行</p>
                    </body>
                </html>
                """;
        System.out.println("HTML 文本块（含 \\n 强制换行）:");
        System.out.println(html);

        // 3. 使用 \ 避免换行（行尾反斜杠表示不换行）
        String longLine = """
                这是一段很长的文本，\
                通过反斜杠可以将多行合并为一行，\
                最终输出时不会有换行符。
                """;
        System.out.println("使用 \\ 合并多行为一行:");
        System.out.println(longLine);

        // 4. stripIndent 方法（去除每行公共前导空白）
        String indented = """
                    第一行
                    第二行
                    第三行
                """;
        System.out.println("原始文本块:");
        System.out.println(indented);

        String stripped = indented.stripIndent();
        System.out.println("stripIndent 后:");
        System.out.println(stripped);

        // 5. 文本块与字符串等价性
        String traditional = "第一行\n第二行\n第三行\n";
        String textBlock = """
                第一行
                第二行
                第三行
                """;
        System.out.println("传统字符串与文本块是否相等: " + traditional.equals(textBlock));
    }
}
