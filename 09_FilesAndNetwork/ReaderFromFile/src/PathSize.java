import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathSize {
         public static long calculateSize(Path path) {
            try {
                if (Files.isRegularFile(path)) {
                    return Files.size(path);
                }
                return Files.list(path).mapToLong(PathSize::calculateSize).sum();
            } catch (IllegalArgumentException | IOException | NullPointerException ex) {
                return 0L;
            }
        }
    }