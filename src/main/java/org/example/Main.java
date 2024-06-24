package org.example;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Main {
    private static final AtomicReference<String> found = new AtomicReference<>(null);
    public static boolean checkIfPasswordIsRight(String password) {
        try {
            ZipFile zip = new ZipFile("C:/KacperCV.zip", password.toCharArray());
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

    public static void backtrack(String chars, String current, Integer maximalLen) {
        System.out.println(current);
        if (checkIfPasswordIsRight(current)) {
            found.set(current);
        }

        if (current.length() < maximalLen) {
            for (int i = 0; i < chars.length(); i++) {
                backtrack(chars, current + chars.charAt(i), maximalLen);
            }
        }
    }


    public static void main(String[] args) {
        backtrack("abcd", "", 8);
        System.out.println(found);
    }
}