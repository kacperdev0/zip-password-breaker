package org.example;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import javax.swing.*;
import java.util.concurrent.*;

public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>(1000);
    private static volatile boolean found = false;

    private static boolean checkIfPasswordIsRight(String password, String path) {
        try {
            ZipFile zip = new ZipFile(path, password.toCharArray());
            if (zip.isEncrypted()) {
                zip.extractAll("/tmp");
                return true;
            }
            return false;
        } catch (ZipException e) {
            return false;
        }
    }

    private static void CombinationsRecursion(String chars, String current, Integer maximalLen) throws InterruptedException {
        if (current.length() > maximalLen || found) {
            return;
        }

        queue.put(current);

        for (int i = 0; i < chars.length(); i++) {
            final String next = current + chars.charAt(i);
            CombinationsRecursion(chars, next, maximalLen);
        }
    }

    private static void getAllCombinations(String chars, int maxLen) throws InterruptedException {
        CombinationsRecursion(chars, "", maxLen);
        queue.put("END");
    }

    private static String findCorrectPasswordIfExists(String pathToFile, JLabel label) throws InterruptedException, ExecutionException {
        while (true) {
            String combination = queue.take();
            if ("END".equals(combination)) {
                break;
            }
            if (checkIfPasswordIsRight(combination, pathToFile)) {
                found = true;
                return combination;
            }
            if (queue.size() % 100 == 0) {
                SwingUtilities.invokeLater(() -> label.setText("Trying: " + combination));
            }
        }
        return "None of the combinations is right";
    }

    public static void findPasswordInBackground(String chars, String path, int maxLen, JLabel resultLabel) {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                ExecutorService producerExecutor = Executors.newSingleThreadExecutor();
                Future<?> producerFuture = producerExecutor.submit(() -> {
                    try {
                        getAllCombinations(chars, maxLen);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

                ExecutorService consumerExecutor = Executors.newSingleThreadExecutor();
                Future<String> consumerFuture = consumerExecutor.submit(() -> {
                    try {
                        return findCorrectPasswordIfExists(path, resultLabel);
                    } catch (InterruptedException | ExecutionException e) {
                        Thread.currentThread().interrupt();
                        return "Error: " + e.getMessage();
                    }
                });

                producerFuture.get();
                return consumerFuture.get();
            }

            @Override
            protected void done() {
                try {
                    resultLabel.setText(get());
                } catch (Exception e) {
                    resultLabel.setText("Error: " + e.getMessage());
                }
            }
        };

        worker.execute();
    }
}
