package com.epam.xmlparsing.utils;

import java.net.URL;
import java.util.Optional;

public class FileUtil {

    public Optional<String> getFilePath(String fileName) {
        if (fileName != null) {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            if (resource != null) {
                String path = resource.getPath();
                return Optional.of(path);
            }
        }
        return Optional.empty();
    }
}
