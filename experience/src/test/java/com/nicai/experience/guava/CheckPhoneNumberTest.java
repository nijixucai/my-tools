package com.nicai.experience.guava;

import com.google.common.base.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CheckPhoneNumberTest {

    /**
     * 返回手机号码
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    @Test
    public void test() {
        List<String> collect = IntStream.range(0, 1000000).parallel()
                .mapToObj(i -> getTel()).collect(Collectors.toList());
        print(collect, CheckPhoneNumber::check, "check");
        print(collect, CheckPhoneNumber::check2, "check2");
        print(collect, CheckPhoneNumber::check3, "check3");
    }

    private void print(List<String> collect, Consumer<? super String> function, String methodName) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        collect.forEach(function);
        System.out.println("methodName = " + methodName + " spend " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " MILLISECONDS");
    }

    @Test
    public void check() {
        Assert.assertTrue(CheckPhoneNumber.check("12345678901"));
    }

    @Test
    public void check1() {
        Assert.assertFalse(CheckPhoneNumber.check("18651866593"));
    }

}
