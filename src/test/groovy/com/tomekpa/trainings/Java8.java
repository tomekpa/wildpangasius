package com.tomekpa.trainings;


import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.of;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * An aggregate operation works on a list of item and results in a single value.
 * <p>
 * A collection is an in-memory data structure that stores all its elements.
 * <p>
 * A stream has no storage. A stream pulls elements from a data source on-demand and passes them to a pipeline of operations for processing.
 * <p>
 * For a collection we talk about the storage or how the data elements are stored, how to access data elements.
 * <p>
 * For a stream we focus on the operations, for example, how to sum a stream.
 */
public class Java8 {

    @Test
    public void shouldCalculateFactorialUsingMapReduce() {
        Integer reduce = IntStream
                .rangeClosed(1, 6)
                .boxed()
                .reduce(1, (a, b) -> a * b);

        assertThat(reduce).isEqualTo(720); // 6! = 720
    }

    @Test
    public void shouldShowHowReduceWork() {

        Stream<String> stream = Stream.of("a", "b", "c");

        String reduce = stream.reduce("x", this::debugMeShouldShowHowReduceWork);

        assertThat(reduce).isEqualTo("x_a_b_c"); // 6! = 720
    }

    private String debugMeShouldShowHowReduceWork(String s1, String s2) {
        return s1 + "_" + s2;
    }

    /**
     * NON-PARALLEL
     * filter:1
     * filter:2
     * map   :2
     * reduce:4
     * filter:3
     * filter:4
     * map   :4
     * reduce:16
     * filter:5
     * filter:6
     * map   :6
     * reduce:36
     * filter:7
     * filter:8
     * map   :8
     * reduce:64
     * filter:9
     * filter:10
     * map   :10
     * reduce:100
     * <p>
     * PARALLEL
     * filter:2
     * filter:3
     * filter:5
     * filter:7
     * filter:4
     * filter:8
     * map   :8
     * filter:1
     * reduce:64
     * filter:9
     * filter:6
     * map   :4
     * filter:10
     * map   :2
     * map   :10
     * reduce:16
     * map   :6
     * reduce:1
     * reduce:100
     * reduce:4
     * reduce:101
     * reduce:18
     * reduce:36
     * reduce:102
     * reduce:5
     * reduce:1
     * reduce:19
     * reduce:167
     * reduce:205
     */

    @Test
    public void shouldShowLazyVsEagerOperations() {

        int min = 0;
        int max = 10;

        Integer reduceSequential = IntStream
                .rangeClosed(min, max)
                .boxed()
                .filter(this::debugMeFilterShouldShowLazyVsEagerOperations)
                .map(this::debugMeMapShouldShowLazyVsEagerOperations)
                .reduce(0, this::debugMeReduceShouldShowLazyVsEagerOperations);

        Integer reduceParallel = IntStream
                .rangeClosed(min, max)
                .parallel()
                .boxed()
                .filter(this::debugMeFilterShouldShowLazyVsEagerOperations)
                .map(this::debugMeMapShouldShowLazyVsEagerOperations)
                .reduce(0, this::debugMeReduceShouldShowLazyVsEagerOperations);

         assertThat(reduceSequential).isEqualTo(reduceParallel);
    }

    private boolean debugMeFilterShouldShowLazyVsEagerOperations(int i) {
        System.out.println("filter:" + i);
        return i % 2 == 0;
    }

    private int debugMeMapShouldShowLazyVsEagerOperations(int i) {
        System.out.println("map   :" + i);
        return i * i;
    }

    private int debugMeReduceShouldShowLazyVsEagerOperations(int a, int d) {
        System.out.println("reduce:" + a +", "+d);
        return a + d;
    }

    @Test
    public void shouldPerformSimpleGrouping() {
        Map<Integer, Long>  map = IntStream.rangeClosed(1,10)
                .map(i->i%3)
                .boxed()
                .collect(Collectors.groupingBy( i-> i ,Collectors.counting()));

        System.out.println(map);
    }

    @Test
    public void shouldPerformSimplePartitionBy() {
        /*PartitioningBy boolean always */
        Map<Boolean, List<Integer>>  map = IntStream.rangeClosed(1,10)
                .boxed()
                .collect(Collectors.partitioningBy( i -> i*i < 30  ));

        System.out.println(map);
    }

    private Integer debugMeshouldPerformSimpleGrouping(Integer i) {
        System.out.println("filter:" + i);
        return i % 2;
    }
}
