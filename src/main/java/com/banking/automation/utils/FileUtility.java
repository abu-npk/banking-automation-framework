package com.banking.automation.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileUtility {
	private FileUtility() {
		throw new UnsupportedOperationException(
				"FileUtility should not be instantiated...");
	}
	
	public static Path createDirectory(final Path directory) {

        validatePath(directory, "Directory");

        try {
            return Files.createDirectories(directory)
                    .toAbsolutePath()
                    .normalize();

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to create directory: " + directory,
                    e);
        }
    }
	
	public static boolean exists(final Path path) {
        validatePath(path, "Path");

        return Files.exists(path);
    }
	
	public static boolean isDirectory(final Path path) {
		validatePath(path, "Path");
		
		return Files.isDirectory(path);
	}
	
	public static boolean isRegularFile(final Path path) {

        validatePath(path, "Path");

        return Files.isRegularFile(path);
    }
	
	public static void deleteIfExists(final Path path) {

        validatePath(path, "Path");

        try {
            Files.deleteIfExists(path);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to delete path: " + path,
                    e);
        }
    }
	
	public static void copy(final Path source, final Path target) {

        validatePath(source, "Source");
        validatePath(target, "Target");

        try {
            createParentDirectory(target);

            Files.copy(
                    source,
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to copy file from "
                            + source
                            + " to "
                            + target,
                    e);
        }
    }
	
	public static void move(final Path source, final Path target) {

        validatePath(source, "Source");
        validatePath(target, "Target");

        try {
            createParentDirectory(target);

            Files.move(
                    source,
                    target,
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to move file from "
                            + source
                            + " to "
                            + target,
                    e);
        }
    }
	
	public static String readString(final Path file) {

        validatePath(file, "File");

        try {
            return Files.readString(file);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to read file: " + file,
                    e);
        }
    }
	
	public static void writeString(final Path file, final String content) {

        validatePath(file, "File");

        if (Objects.isNull(content)) {
            throw new IllegalArgumentException(
                    "File content cannot be null...");
        }

        try {
            createParentDirectory(file);

            Files.writeString(
                    file,
                    content);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to write file: " + file,
                    e);
        }
    }

    public static Path normalize(final Path path) {

        validatePath(path, "Path");

        return path
                .toAbsolutePath()
                .normalize();
    }

    private static void createParentDirectory(final Path file) {

        final Path parent = file.toAbsolutePath()
                .normalize()
                .getParent();

        if (Objects.nonNull(parent)) {
            createDirectory(parent);
        }
    }

    private static void validatePath(final Path path, final String argumentName) {

        if (Objects.isNull(path)) {
            throw new IllegalArgumentException(
                    argumentName + " path cannot be null."
            );
        }
    }
}

