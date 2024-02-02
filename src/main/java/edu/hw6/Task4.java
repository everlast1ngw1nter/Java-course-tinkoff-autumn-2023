package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    private Task4() {
    }

    private static final String MESSAGE =
            "Programming is learned by writing programs. ― Brian Kernighan";

    public static void outputStreamComposition(File file)
            throws IOException {
        try (var outputStream = new FileOutputStream(file);
             var checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
             var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             var printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.println(MESSAGE);
        }
    }
}
