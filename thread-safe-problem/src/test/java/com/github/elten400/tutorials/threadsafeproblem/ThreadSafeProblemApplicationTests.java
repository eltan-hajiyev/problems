package com.github.elten400.tutorials.threadsafeproblem;

import com.github.elten400.tutorials.threadsafeproblem.client.ApiFeignClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ThreadSafeProblemApplicationTests {

    @Autowired
    private ApiFeignClient client;

    /**
     * It shows that:
     * Sometimes {@link com.github.elten400.tutorials.threadsafeproblem.validation.NumberConstraintValidator} class accepts wrong numbers.
     * Why it's happening? Solve it.
     */
    @Test
    void parallel_request_test() throws Exception {
        final List<Integer> values = Collections.synchronizedList(new ArrayList<>());

        Thread wrongNumbers = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 500).forEach(v -> {
                    try {
                        Integer res = client.method(new Random().nextInt(30));
                        values.add(res);
                    } catch (Throwable t) {
                    }
                });
            }
        };
        Thread rightNumber = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 500).forEach(v -> {
                    try {
                        Integer res = client.method(60);
                        values.add(res);
                    } catch (Throwable t) {
                    }
                });
            }
        };

        wrongNumbers.start();
        rightNumber.start();

        wrongNumbers.join();
        rightNumber.join();

        List<Integer> results = values.stream().filter(v -> v <= 30).collect(Collectors.toList());

        System.out.println("Accepted wrong numbers: " + results);
        Assertions.assertThat(results).hasSizeGreaterThan(0);
    }

}
