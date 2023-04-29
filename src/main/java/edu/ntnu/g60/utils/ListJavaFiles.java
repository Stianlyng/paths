package edu.ntnu.g60.utils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListJavaFiles {
    private static Set<String> excludedFilenames = new HashSet<>();
    private static Set<String> excludedFolders = new HashSet<>();

    public static void main(String[] args) {
        // Add filenames and folders you want to exclude
        excludedFilenames.add("ListJavaFiles.java");
        excludedFilenames.add("module-info.java");
        excludedFilenames.add("App.java");
        //excludedFilenames.add("GameRunner.java");
        excludedFilenames.add("StoryParser.java");
        excludedFilenames.add("GameAppLauncher.java");

        excludedFolders.add("frontend");
        excludedFolders.add("utils");
        excludedFolders.add("exceptions");
        excludedFolders.add("entities");
        excludedFolders.add("controllers");
        excludedFolders.add("views");


        Path srcPath = Paths.get("src/main");
        Path outputFile = srcPath.resolve("all_java_files_contents.txt");

        try {
            Files.deleteIfExists(outputFile);
            Files.createFile(outputFile);

            Files.walk(srcPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .filter(path -> !isExcludedFile(path))
                    .filter(path -> !isExcludedFolder(path))
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

    private static boolean isExcludedFile(Path path) {
        return excludedFilenames.contains(path.getFileName().toString());
    }


    private static boolean isExcludedFolder(Path path) {
        return excludedFolders.stream().anyMatch(excluded -> path.getParent().toString().contains(excluded));
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