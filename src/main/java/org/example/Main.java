package org.example;

import java.io.File;
import java.io.IOException;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Main {
    public static boolean checkIfPasswordIsRight(String password) {
        try {
            ZipFile zip = new ZipFile("C:/Kacper_Ołdziejewski_CV.zip", password.toCharArray());
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

    public static void main(String[] args) {

    }
}