package org.example;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );
    private static final AtomicReference<String> found = new AtomicReference<>(null);
    public static boolean checkIfPasswordIsRight(String password, String path) {
        try {
            ZipFile zip = new ZipFile(path, password.toCharArray());
            if (zip.isEncrypted())
            {
                zip.extractAll("/tmp");
                return true;
            }
            return false;
         } catch (ZipException e) {
            return false;
        }
    }

    public static void backtrack(String chars, String current, Integer maximalLen, String path) {
        System.out.println(current);

        if (checkIfPasswordIsRight(current, path)) {
            found.set(current);
            executor.shutdownNow();
            return;
        }

        if (current.length() < maximalLen && found.get() == null) {
            for (int i = 0; i < chars.length(); i++) {
               final String next = current + chars.charAt(i);
               executor.execute(() -> backtrack(chars, next, maximalLen, path));
            }
        }
    }

    public static String checkAllCombinations(String chars, int maxLen, String path) {
        backtrack(chars, "", maxLen, path);
        while (executor.isTerminated() == false) {}
        return found.toString();
    }
}