package jdk17.enhancedrandom;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.SplittableRandom;

/**
 * JDK 17 Enhanced Pseudo-Random Number Generators（正式特性）
 *
 * <p>
 * 引入统一的 RandomGenerator 接口，以及 RandomGeneratorFactory 工厂，
 * 支持多种新算法（如 LXM、Xoroshiro128PlusPlus 等）。
 */
public class EnhancedRandomDemo {

    public static void main(String[] args) {
        // 1. 列出所有可用的随机数生成器算法
        System.out.println("可用的随机数生成器算法:");
        RandomGeneratorFactory.all()
                .map(RandomGeneratorFactory::name)
                .sorted()
                .forEach(name -> System.out.println("  - " + name));

        // 2. 使用工厂创建指定算法的生成器
        RandomGenerator xoroshiro = RandomGeneratorFactory.of("Xoroshiro128PlusPlus").create(12345L);
        System.out.println("\nXoroshiro128PlusPlus 生成的 5 个随机整数:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + xoroshiro.nextInt());
        }

        // 3. 使用 SplittableRandom（JDK 8 引入，JDK 17 归入 RandomGenerator 体系）
        SplittableRandom splittable = new SplittableRandom(42L);
        System.out.println("\nSplittableRandom 生成的 5 个 0-99 整数:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + splittable.nextInt(100));
        }

        // 4. 使用默认算法（JDK 17 默认通常为 L32X64MixRandom）
        RandomGenerator defaultRng = RandomGenerator.getDefault();
        System.out.println("\n默认随机数生成器 (" + defaultRng.getClass().getSimpleName() + ") 生成 3 个 double:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  " + defaultRng.nextDouble());
        }

        System.out.println("\nEnhancedRandomDemo 执行完毕");
    }
}
