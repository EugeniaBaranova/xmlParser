package com.epam.xmlparsing.parser;

import java.net.URL;
import java.util.Optional;

public class FilePathGetter {

    public Optional<String> getFilePath(String fileName) {
        if (fileName != null) {
            ClassLoader classLoader = getClass().getClassLoader();
            if (classLoader != null) {
                URL resource = classLoader.getResource(fileName);
                if (resource != null) {
                    String path = resource.getPath();
                    return Optional.of(path);
                }
            }
        }
        return Optional.empty();
    }
}
