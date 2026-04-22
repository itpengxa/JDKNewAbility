package jdk16.vectorapi;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

/**
 * JDK 16 Vector API（孵化特性）
 * <p>
 * 使用 jdk.incubator.vector 进行 SIMD（单指令多数据）风格的向量运算，
 * 可在支持硬件上实现并行数据计算加速。
 * <p>
 * 编译和运行需添加 --add-modules jdk.incubator.vector
 */
public class VectorAPIDemo {

    public static void main(String[] args) {
        // 定义向量种类：128 位浮点向量（4 个 float）
        VectorSpecies<Float> species = FloatVector.SPECIES_128;

        float[] a = {1.0f, 2.0f, 3.0f, 4.0f};
        float[] b = {5.0f, 6.0f, 7.0f, 8.0f};
        float[] c = new float[a.length];

        // 将数组加载为向量
        FloatVector va = FloatVector.fromArray(species, a, 0);
        FloatVector vb = FloatVector.fromArray(species, b, 0);

        // 向量加法：一次对 4 个 float 做并行加法
        FloatVector vc = va.add(vb);

        // 将结果写回数组
        vc.intoArray(c, 0);

        System.out.println("Vector API 向量加法结果:");
        for (int i = 0; i < c.length; i++) {
            System.out.println("  c[" + i + "] = " + a[i] + " + " + b[i] + " = " + c[i]);
        }

        System.out.println("VectorAPIDemo 执行完毕");
    }
}
