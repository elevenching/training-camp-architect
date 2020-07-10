package com.example.training.week5;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * https://blog.csdn.net/l1028386804/article/details/54573106
 * @author chenjun
 */
public class CalSta {

    public static void main(String[] args) {
        Set<Double> testData = Sets.newHashSet(2d, 4d, 6d, 7d, 8d, 9d, 12d, 36d);
        System.out.println("总和Sum  " + CalSta.sum(testData));
        System.out.println("平均值Mean  " + CalSta.mean(testData));
        System.out.println("总体方差Population Variance  " + CalSta.popVariance(testData));
        System.out.println("总体标准差Population STD_dev   " + CalSta.popStdDev(testData));
        System.out.println("样本方差Sample Variance  " + CalSta.sampleVariance(testData));
        System.out.println("样本标准差Sample STD_dev   " + CalSta.sampleStdDev(testData));
    }

    public static double sum(Set<Double> data) {
        double sum = 0;
        for (double datum : data) {
            sum = sum + datum;
        }
        return sum;
    }

    public static double mean(Set<Double> data) {
        double mean;
        mean = sum(data) / data.size();
        return mean;
    }

    /**
     * population variance 总体方差
     *
     * @param data 数据
     * @return 总体方差
     */
    public static double popVariance(Set<Double> data) {
        double variance = 0;
        for (double datum : data) {
            variance = variance + (Math.pow((datum - mean(data)), 2));
        }
        variance = variance / data.size();
        return variance;
    }

    /**
     * population standard deviation 总体标准差
     *
     * @param data 数据
     * @return 样本标准差
     */
    public static double popStdDev(Set<Double> data) {
        double stdDev;
        stdDev = Math.sqrt(popVariance(data));
        return stdDev;
    }

    /**
     * sample variance 样本方差
     *
     * @param data 数据
     * @return 样本标准差
     */
    public static double sampleVariance(Set<Double> data) {
        double variance = 0;
        for (double datum : data) {
            variance = variance + (Math.pow((datum - mean(data)), 2));
        }
        variance = variance / (data.size() - 1);
        return variance;
    }

    /**
     * sample standard deviation 样本标准差
     *
     * @param data 数据
     * @return 样本标准差
     */
    public static double sampleStdDev(Set<Double> data) {
        double stdDev;
        stdDev = Math.sqrt(sampleVariance(data));
        return stdDev;
    }

}
