package com.nicai.algorithm.sort;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * SortTest
 *
 * @author guozhe
 * @date 2020/08/14
 */
@Slf4j
public class SortTest {

    /**
     * 等待被测试的排序的实现类
     */
    private static final List<Sort> TO_BE_TEST_SORT_TYPE = Lists.newArrayList(new BubbleSort());

    /**
     * 待排序的数组
     */
    private static final int[] TO_BE_SORTED_NUMS = new int[100];

    /**
     * 生成待排序数组的随机类
     */
    private static final Random random = new Random(47);

    @BeforeClass
    public static void init() {
        int length = TO_BE_SORTED_NUMS.length;
        for (int i = 0; i < length; i++) {
            TO_BE_SORTED_NUMS[i] = random.nextInt(length);
        }
    }

    @Test
    public void sort() {
        log.info("TO_BE_SORTED_NUMS = {}", TO_BE_SORTED_NUMS);
        for (Sort sort : TO_BE_TEST_SORT_TYPE) {
            log.info("sort name = {} ========================================================", sort.getName());
            int[] sorted = sort.sort(TO_BE_SORTED_NUMS);
            log.info("TO_BE_SORTED_NUMS = {}", sorted);
            sortedAssert(sorted);
        }
    }

    private void sortedAssert(int[] sorted) {
        Assert.assertEquals(TO_BE_SORTED_NUMS.length, sorted.length);
        for (int i = 0; i < sorted.length - 1; i++) {
            Assert.assertTrue(sorted[i] <= sorted[i + 1]);
        }
    }
}