package com.nicai.algorithm.map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * MapSearchTest
 *
 * @author guozhe
 * @date 2020/09/07
 */
@Slf4j
public class MapSearchTest {

    /**
     * 被测试类列表
     */
    private static final List<MapSearch<Location>> TO_BE_TESTED = Lists.newArrayList(new BreadthFirstSearch<>(),
            new DepthFirstSearch<>());

    /**
     * 图：src/main/resources/map/图-基础.jpg
     * 1、key是节点名称，value是与节点相关联的其他节点的名称
     * 2、节点是地点，value是地点能到达的另一个地点
     */
    private static final Map<Location, List<Location>> MAP = Maps.newHashMap();

    static final Location wuzhen = new Location("乌镇");
    static final Location hangzhou = new Location("杭州");
    static final Location seoul = new Location("首尔");
    static final Location beijing = new Location("北京");
    static final Location tokyo = new Location("东京");
    static final Location bangkok = new Location("曼谷");
    static final Location singapore = new Location("新加坡");

    public void initMap() {
        MAP.clear();
        MAP.put(wuzhen, Lists.newArrayList(hangzhou));
        MAP.put(hangzhou, Lists.newArrayList(seoul, beijing, singapore));
        MAP.put(seoul, Lists.newArrayList(hangzhou, tokyo));
        MAP.put(beijing, Lists.newArrayList(hangzhou));
        MAP.put(singapore, Lists.newArrayList(hangzhou, bangkok));
        MAP.put(tokyo, Lists.newArrayList(seoul));
        MAP.put(bangkok, Lists.newArrayList(singapore));
    }

    @Test
    public void haveValue() {
        invokeAndAssert(true, beijing);
    }

    @Test
    public void haveNoValue() {
        invokeAndAssert(false, new Location("hello"));
    }

    private void invokeAndAssert(final boolean expect, Location location) {
        TO_BE_TESTED.forEach(mapSearch -> Assert.assertEquals(expect, invokeSearch(mapSearch, location)));
    }

    private boolean invokeSearch(MapSearch<Location> mapSearch, Location target) {
        initMap();
        log.info("mapSearch={}start=====================", mapSearch.getClass().getName());
        boolean result = mapSearch.search(MAP, wuzhen, target);
        log.info("mapSearch={}target=======================", mapSearch.getClass().getName());
        return result;
    }

}