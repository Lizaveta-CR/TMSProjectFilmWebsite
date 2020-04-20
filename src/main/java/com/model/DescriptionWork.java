package com.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DescriptionWork {
    private Map<String, String> map;
    private static final String FILE_PATH = "/Users/elizaveta/Downloads/SS/src/main/resources/descriptionFile";

    public DescriptionWork() {
    }

    public DescriptionWork(Map<String, String> map) {
        this.map = map;
    }

    //не записывать повторы
    public void writeToFile(Map<String, String> nameDescription) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_PATH), "UTF-8"))) {
            for (Map.Entry<String, String> entry : nameDescription.entrySet()) {
                String key = entry.getKey();
                int colonIndex = key.indexOf(":");
                if (colonIndex != -1) {
                    key = key.substring(0, colonIndex).concat(key.substring(colonIndex + 1, key.length()));
                }
                bw.write(key + ":" + entry.getValue());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
        }
    }

    public Map<String, String> readFromFile() throws IOException {
        Map<String, String> map = new HashMap<>();
        String delimiter = ":";
        String delimiterEnd = "Год выпуска";
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            lines.filter(line -> line.contains(delimiter)).forEach(
                    line -> map.putIfAbsent(line.split(delimiter)[0], line.split(delimiterEnd)[0])
            );
        }
        final Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        final HashSet<String> valueSet = new HashSet<String>();
        while (iter.hasNext()) {
            final Map.Entry<String, String> next = iter.next();
            if (!valueSet.add(next.getValue())) {
                iter.remove();
            }
        }
        return map;
    }
}
