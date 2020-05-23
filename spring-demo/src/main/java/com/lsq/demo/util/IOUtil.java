package com.lsq.demo.util;

import java.io.Closeable;
import java.io.IOException;

public class IOUtil {
    public static void close(Closeable...io){
        for (Closeable closeable : io) {
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
