package com.example.unpigeon.loader.network.utils;

import java.io.Closeable;
import java.io.IOException;

public class FileUtils {
    public static void closeIO(Closeable ... closeables) {
        for (Closeable var : closeables) {
            if (var != null) {
                try {
                    var.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
