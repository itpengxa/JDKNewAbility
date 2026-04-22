package jdk15.hiddenclass;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * JDK 15 隐藏类（Hidden Classes）演示
 * 使用 MethodHandles.Lookup.defineHiddenClass 动态定义隐藏类。
 * 隐藏类不能被其他类的字节码直接引用，生命周期由创建它的类控制。
 */
public class HiddenClassDemo {

    public static void main(String[] args) throws Throwable {
        System.out.println("=== JDK 15 隐藏类演示 ===\n");

        // 简单的隐藏类字节码：一个返回 "Hello from HiddenClass" 的类
        // 这里使用一个预编译好的极简类字节码来演示
        // 实际场景中可通过 ASM、ByteBuddy 等工具动态生成字节码

        byte[] classBytes = generateSimpleClassBytes();

        // 使用 MethodHandles.Lookup 定义隐藏类
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandles.Lookup hiddenLookup = lookup.defineHiddenClass(classBytes, true);

        Class<?> hiddenClass = hiddenLookup.lookupClass();
        System.out.println("隐藏类已创建:");
        System.out.println("  类名: " + hiddenClass.getName());
        System.out.println("  是否是隐藏类: " + hiddenClass.isHidden());
        System.out.println("  类加载器: " + hiddenClass.getClassLoader());

        // 调用隐藏类的方法
        Object instance = hiddenClass.getDeclaredConstructor().newInstance();
        java.lang.reflect.Method sayHello = hiddenClass.getMethod("sayHello");
        String result = (String) sayHello.invoke(instance);
        System.out.println("  调用 sayHello() 结果: " + result);

        System.out.println();
        System.out.println("隐藏类特性说明:");
        System.out.println("  1. 无法被其他类的字节码直接引用");
        System.out.println("  2. 不会出现在 ClassLoader 的类列表中");
        System.out.println("  3. 生命周期由创建它的 Lookup 对象控制");
        System.out.println("  4. 适合框架内部动态生成临时类的场景");
    }

    /**
     * 生成一个简单的类字节码，包含无参构造器和 sayHello 方法。
     * 这里使用硬编码的字节码数组表示以下类：
     *
     * public class HiddenHello {
     *     public HiddenHello() {}
     *     public String sayHello() { return "Hello from HiddenClass"; }
     * }
     *
     * 实际项目中建议使用 ASM、Javassist 或 ByteBuddy 生成字节码。
     */
    private static byte[] generateSimpleClassBytes() {
        // 使用 javac 编译后提取的简化字节码，对应上面的 HiddenHello 类
        // 类名使用 jdk15/hiddenclass/HiddenHello
        return new byte[]{
                (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE, // magic
                0x00, 0x00, 0x00, 0x3D, // version (Java 13+ for hidden class support, 0x3D = Java 17 compatible)
                0x00, 0x12, // constant pool count = 18

                // constant pool
                0x0A, 0x00, 0x02, 0x00, 0x03, // #1 Methodref #2.#3
                0x07, 0x00, 0x04,             // #2 Class #4
                0x0C, 0x00, 0x05, 0x00, 0x06, // #3 NameAndType #5:#6
                0x01, 0x00, 0x10, 0x6A, 0x61, 0x76, 0x61, 0x2F, 0x6C, 0x61, 0x6E, 0x67, 0x2F, 0x4F, 0x62, 0x6A, 0x65, 0x63, 0x74, // #4 "java/lang/Object"
                0x01, 0x00, 0x06, 0x3C, 0x69, 0x6E, 0x69, 0x74, 0x3E, // #5 "<init>"
                0x01, 0x00, 0x03, 0x28, 0x29, 0x56, // #6 "()V"
                0x08, 0x00, 0x08,             // #7 String #8
                0x01, 0x00, 0x18, 0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x20, 0x66, 0x72, 0x6F, 0x6D, 0x20, 0x48, 0x69, 0x64, 0x64, 0x65, 0x6E, 0x43, 0x6C, 0x61, 0x73, 0x73, // #8 "Hello from HiddenClass"
                0x01, 0x00, 0x08, 0x73, 0x61, 0x79, 0x48, 0x65, 0x6C, 0x6C, 0x6F, // #9 "sayHello"
                0x01, 0x00, 0x14, 0x28, 0x29, 0x4C, 0x6A, 0x61, 0x76, 0x61, 0x2F, 0x6C, 0x61, 0x6E, 0x67, 0x2F, 0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x3B, // #10 "()Ljava/lang/String;"
                0x01, 0x00, 0x04, 0x43, 0x6F, 0x64, 0x65, // #11 "Code"
                0x01, 0x00, 0x0F, 0x4C, 0x69, 0x6E, 0x65, 0x4E, 0x75, 0x6D, 0x62, 0x65, 0x72, 0x54, 0x61, 0x62, 0x6C, 0x65, // #12 "LineNumberTable"
                0x01, 0x00, 0x12, 0x4C, 0x6F, 0x63, 0x61, 0x6C, 0x56, 0x61, 0x72, 0x69, 0x61, 0x62, 0x6C, 0x65, 0x54, 0x61, 0x62, 0x6C, 0x65, // #13 "LocalVariableTable"
                0x01, 0x00, 0x04, 0x74, 0x68, 0x69, 0x73, // #14 "this"
                0x01, 0x00, 0x26, 0x6A, 0x64, 0x6B, 0x31, 0x35, 0x2F, 0x68, 0x69, 0x64, 0x64, 0x65, 0x6E, 0x63, 0x6C, 0x61, 0x73, 0x73, 0x2F, 0x48, 0x69, 0x64, 0x64, 0x65, 0x6E, 0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x3B, // #15 "Ljdk15/hiddenclass/HiddenHello;"
                0x01, 0x00, 0x0A, 0x53, 0x6F, 0x75, 0x72, 0x63, 0x65, 0x46, 0x69, 0x6C, 0x65, // #16 "SourceFile"
                0x01, 0x00, 0x0F, 0x48, 0x69, 0x64, 0x64, 0x65, 0x6E, 0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x2E, 0x6A, 0x61, 0x76, 0x61, // #17 "HiddenHello.java"

                // access_flags
                0x00, 0x21, // ACC_PUBLIC | ACC_SUPER
                // this_class
                0x00, 0x02, // #2
                // super_class
                0x00, 0x02, // #2 java/lang/Object (simplified: self reference for demo, normally Object)
                // interfaces_count
                0x00, 0x00,
                // fields_count
                0x00, 0x00,
                // methods_count
                0x00, 0x02,

                // method 1: <init>()V
                0x00, 0x01, // ACC_PUBLIC
                0x00, 0x05, // name_index #5 "<init>"
                0x00, 0x06, // descriptor_index #6 "()V"
                0x00, 0x01, // attributes_count
                // Code attribute
                0x00, 0x0B, // name_index #11 "Code"
                0x00, 0x00, 0x00, 0x1D, // attribute_length = 29
                0x00, 0x01, // max_stack
                0x00, 0x01, // max_locals
                0x00, 0x00, 0x00, 0x05, // code_length = 5
                0x2A,       // aload_0
                0xB7, 0x00, 0x01, // invokespecial #1
                0xB1,       // return
                0x00, 0x00, // exception_table_length
                0x00, 0x00, // attributes_count

                // method 2: sayHello()Ljava/lang/String;
                0x00, 0x01, // ACC_PUBLIC
                0x00, 0x09, // name_index #9 "sayHello"
                0x00, 0x0A, // descriptor_index #10 "()Ljava/lang/String;"
                0x00, 0x01, // attributes_count
                // Code attribute
                0x00, 0x0B, // name_index #11 "Code"
                0x00, 0x00, 0x00, 0x12, // attribute_length = 18
                0x00, 0x01, // max_stack
                0x00, 0x01, // max_locals
                0x00, 0x00, 0x00, 0x02, // code_length = 2
                0x12, 0x07, // ldc #7
                0xB0,       // areturn
                0x00, 0x00, // exception_table_length
                0x00, 0x00, // attributes_count

                // class attributes_count
                0x00, 0x01,
                // SourceFile attribute
                0x00, 0x10, // name_index #16 "SourceFile"
                0x00, 0x00, 0x00, 0x02, // attribute_length = 2
                0x00, 0x11  // sourcefile_index #17 "HiddenHello.java"
        };
    }
}
