package com.quizo.app.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjectMapper {
    private static com.fasterxml.jackson.databind.ObjectMapper mapper;

    static {
        mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public ObjectMapper() {
        // Constructor for instantiation if needed
    }

    private static com.fasterxml.jackson.databind.ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return mapper;
    }

    public static  <T> T map(Object source, Class<T> destinationType) {
        // Convert object to JSON and back to destination type
        return getMapper().convertValue(source, destinationType);
    }

    // Convert Object to JSON string
    public static String toJson(Object source) {
        try {
            return getMapper().writeValueAsString(source);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    // Read file content and convert to JSON object
    public static JsonNode readFileAsJson(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }
        String fileContent = Files.readString(path);
        return getMapper().readTree(fileContent);
    }

    // Read file content and convert to specific type
    public static  <T> T readFileAs(String filePath, Class<T> type) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }
        return getMapper().readValue(file, type);
    }

    // Read file content from classpath resource and convert to JSON object
    public static JsonNode readClassPathResource(String resourcePath) throws IOException {
        if (resourcePath == null || resourcePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Resource path cannot be null or empty");
        }

        // Handle path variations - normalize to classpath format
        String normalizedPath = normalizeResourcePath(resourcePath);

        Resource resource = new ClassPathResource(normalizedPath);
        if (!resource.exists()) {
            throw new IOException("Classpath resource not found: " + resourcePath + " (resolved to: " + normalizedPath + ")");
        }

        try (InputStream inputStream = resource.getInputStream()) {
            return getMapper().readTree(inputStream);
        }
    }

    // Normalize resource paths to work with ClassPathResource
    private static String normalizeResourcePath(String path) {
        // Convert Windows backslashes to forward slashes
        path = path.replace("\\", "/");
        // Remove leading slashes
        path = path.replaceAll("^/+", "");
        // Remove file: protocol if present
        if (path.startsWith("file:")) {
            path = path.substring(5).replaceAll("^/+", "");
        }
        return path;
    }

    // Read file from classpath and convert to specific type
    public static <T> T readClassPathResourceAs(String resourcePath, Class<T> type) throws IOException {
        if (resourcePath == null || resourcePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Resource path cannot be null or empty");
        }

        String normalizedPath = normalizeResourcePath(resourcePath);
        Resource resource = new ClassPathResource(normalizedPath);

        if (!resource.exists()) {
            throw new IOException("Classpath resource not found: " + resourcePath);
        }

        try (InputStream inputStream = resource.getInputStream()) {
            return getMapper().readValue(inputStream, type);
        }
    }

    // Convert classpath resource to formatted JSON string
    public static String classPathResourceToJsonString(String resourcePath) throws IOException {
        JsonNode jsonNode = readClassPathResource(resourcePath);
        return getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
    }

    public static JsonNode readJsonFromFile(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        return getMapper().readTree(file);
    }

    public static JsonNode editJson(String filePath, String key, String newValue) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }
        JsonNode rootNode = getMapper().readTree(file);

        if(rootNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) rootNode;
            objectNode.put(key, newValue);
            return objectNode;
        }
        return rootNode;
    }

    // Edit JSON from classpath resource
    public static JsonNode editJsonFromClassPath(String resourcePath, String key, String newValue) throws IOException {
        if (resourcePath == null || resourcePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Resource path cannot be null or empty");
        }

        String normalizedPath = normalizeResourcePath(resourcePath);
        Resource resource = new ClassPathResource(normalizedPath);

        if (!resource.exists()) {
            throw new IOException("Classpath resource not found: " + resourcePath);
        }

        JsonNode rootNode;
        try (InputStream inputStream = resource.getInputStream()) {
            rootNode = getMapper().readTree(inputStream);
        }

        if (rootNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) rootNode;
            objectNode.put(key, newValue);
            return objectNode;
        }
        return rootNode;
    }

}
