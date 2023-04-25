package edu.ntnu.g60.utils.fileParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListJavaFiles {
    public static void main(String[] args) {
        Path srcPath = Paths.get("src/main");
        Path outputFile = srcPath.resolve("all_java_files_contents.txt");

        try {
            Files.deleteIfExists(outputFile);
            Files.createFile(outputFile);

            Files.walk(srcPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> {
                        try {
                            String fileContent = new String(Files.readAllBytes(path));
                            String filteredContent = processFileContent(fileContent);

                            Files.writeString(outputFile, "File: " + path.toString() + "\n", StandardOpenOption.APPEND);
                            Files.writeString(outputFile, filteredContent + "\n", StandardOpenOption.APPEND);
                            Files.writeString(outputFile, "----------\n", StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            System.err.println("Error while writing file content:");
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error while processing files:");
            e.printStackTrace();
        }
    }

    private static String processFileContent(String fileContent) {
        String contentWithoutJavadoc = removeJavadocComments(fileContent);
        return filterLines(contentWithoutJavadoc);
    }

    private static String removeJavadocComments(String fileContent) {
        Pattern javadocPattern = Pattern.compile("/\\*\\*(?:.|[\\r\\n])*?\\*/");
        return javadocPattern.matcher(fileContent).replaceAll("");
    }

    private static String filterLines(String content) {
        Stream<String> lines = content.lines();
        return lines.filter(line -> !line.trim().isEmpty() && !line.trim().startsWith("import"))
                    .collect(Collectors.joining("\n"));
    }
}