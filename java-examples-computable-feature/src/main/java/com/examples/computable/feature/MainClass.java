package com.examples.computable.feature;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) {
        try {
            Example1();
            Example2();
            Example3();
            Example4();
            Example5();
            Example6();
            Example7();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Example1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Test1");
        if ("Test1".equals(future.get())) {
            System.out.println(future.get());
        } else {
            System.out.println("in else" + future.get());
        }
    }

    public static void Example2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Test2");
        CompletableFuture<String> future = completableFuture.thenApply(s -> s + " after Test2");
        if ("Test2 after Test2".equals(future.get())) {
            System.out.println(future.get());
        } else {
            System.out.println("in else" + future.get());
        }
    }

    public static void Example3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Test3");
        CompletableFuture<Void> future = completableFuture.thenRun(() -> System.out.println("Computation finished."));
        System.out.println(completableFuture.get());
    }

    public static void Example4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Test4").thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Compose"));
        if ("Test4 Compose".equals(completableFuture.get())) {
            System.out.println(completableFuture.get());
        } else {
            System.out.println("in else" + completableFuture.get());
        }
    }

    public static void Example5() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Test5").thenCombine(CompletableFuture.supplyAsync(() -> " thenCombine"), (s1, s2) -> s1 + s2);
        if ("Test5 thenCombine".equals(completableFuture.get())) {
            System.out.println(completableFuture.get());
        } else {
            System.out.println("in else" + completableFuture.get());
        }
    }

    public static void Example6() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "Test6")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " thenAcceptBoth"),
                        (s1, s2) -> System.out.println(s1 + s2));
    }

    public static void Example7() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<String> list = Arrays.asList(
                "1", "2", "3", "4"
        );
        List<CompletableFuture<String>> completableFutureList = list.stream().
                map(item -> CompletableFuture.supplyAsync(() -> item, executor)).
                collect(Collectors.toList());

        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]));
        System.out.println(allDoneFuture);
    }

}
