package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );
    private static boolean checkIfPasswordIsRight(String password, String path) {
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

    private static void CombinationsRecursion(String chars, String current, Integer maximalLen, List<String> combinations) {
        combinations.add(current);

        if (current.length() > maximalLen) {
            return;
        }

        for (int i = 0; i < chars.length(); i++) {
            final String next = current + chars.charAt(i);
            CombinationsRecursion(chars, next, maximalLen, combinations);
        }
    }

    private static List<String> getAllCombinations(String chars, int maxLen) {
        List<String> combinations = new ArrayList<>();
        CombinationsRecursion(chars, "", maxLen, combinations);
        return combinations;
    }

    private static String findCorrectPasswordIfExists(List<String> combinations, String pathToFile) {
        for (String combination : combinations){
            if (checkIfPasswordIsRight(combination, pathToFile)) {
                return combination;
            }
        }
        return "None of combinations is right";
    }

    public static String findCorrectPasswordInCombiantions(String chars, String path, int maxLen) {
        List<String> combinations = getAllCombinations(chars, maxLen);
        String passwordOrMessage = findCorrectPasswordIfExists(combinations, path);
        return passwordOrMessage;
    }
}