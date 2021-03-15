import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourcePathUser = " ";
        String targetPathUser = " ";

        for (; ; ) {
            System.out.println("Please, enter path for directory to copy (in format as:  D:/source" +
                    "\n0 - for exit");
            sourcePathUser = new Scanner(System.in).nextLine();
            if (sourcePathUser.equals("0")) {
                break;
            }
            System.out.println("Please, enter path for new target directory (in format as:  D:/target" +
                    "\n0 - for exit");
            targetPathUser = new Scanner(System.in).nextLine();
            if (targetPathUser.equals("0")) {
                break;
            }
            if (sourcePathUser.equalsIgnoreCase("")
                    || (targetPathUser.equalsIgnoreCase(""))) {
                System.out.println("String can not be empty!");
                continue;
            }


            Path pathSource = Paths.get(sourcePathUser);
            Path pathTarget = Paths.get(targetPathUser);
            if (Files.exists(pathSource) && !Files.exists(pathTarget)) {
                Files.walkFileTree(pathSource, new CopyFileVisitor(pathSource, pathTarget));
            } else if (!Files.exists(pathSource)) {
                System.out.println("Source directory does not exist");
            } else if (!Files.exists(pathTarget)) {
                System.out.println("Target directory already exists");
            }
        }
    }

    public static class CopyFileVisitor extends SimpleFileVisitor<Path> {
        Path source;
        Path target;
        public CopyFileVisitor(Path source, Path target) {
            this.source = source;
            this.target = target;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
            try {
                copy(path);
            } catch (IOException ex) {
                System.out.println("Copy directory error " + path);
                ex.printStackTrace();
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }

        private void copy(Path path) throws IOException {
            Path relative = source.relativize(path);
            System.out.println("Relative path " + relative);
            Path targetPath = target.resolve(relative);
            System.out.println("Target path " + targetPath);
            Files.copy(path, targetPath);
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            try {
                copy(path);
            } catch (IOException ex) {
                System.out.println("Copy file error " + path);
                ex.printStackTrace();
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("Copy file failed" + file);
            return FileVisitResult.CONTINUE;
        }
    }
}