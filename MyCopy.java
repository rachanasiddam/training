package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyCopy {

    public static void main(String[] args) throws InterruptedException,
            IOException {

        File source = new File(args[0]);
        File dest = new File(args[1]);

        long start = System.nanoTime();
        copyFile(source, dest);
        System.out.println("Time taken to Copy = "
                + (System.nanoTime() - start));

    }

    private static void copyFile(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }
}