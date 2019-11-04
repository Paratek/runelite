package net.runelite.client.plugins.logger;

import joptsimple.internal.Strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Logger {

    private static final String home = System.getProperty("user.home");
    private static final Path out = Paths.get(home + "/rl-logs/log_"
            + new SimpleDateFormat("yyyyMMdd-HH-MM-SS").format(new Date()) + ".log");

    public static void write(final String text) {
        try {
            Files.write(out, (System.currentTimeMillis() + " " + text + "\n").getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(final String... texts) {
        write(Strings.join(texts, ","));
    }

    public static void write(final Object... objects) {
        write(Strings.join(Arrays.stream(objects).map(String::valueOf).collect(Collectors.toList()), ","));
    }

}
