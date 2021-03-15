import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String srcFolder = "D:/source";
        String dstFolder = "D:/target";

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();


        int step = files.length / 4; // 4 головы процессора, делим массив на 4 части

        File[] files1 = new File[step];
        System.arraycopy(files, 0, files1, 0, files1.length);
        ImageResizer resizer1 = new ImageResizer(files1, dstFolder, start);
        resizer1.start();

        File[] files2 = new File[step];
        System.arraycopy(files, step, files2, 0, files2.length);
        ImageResizer resizer2 = new ImageResizer(files2, dstFolder, start);
        resizer2.start();

        File[] files3 = new File[step];
        System.arraycopy(files, step, files3, 0, files3.length);
        ImageResizer resizer3 = new ImageResizer(files3, dstFolder, start);
        resizer3.start();

        File[] files4 = new File[files.length - (3 * step)];
        System.arraycopy(files, (int) (files.length - (3 * step)), files4, 0, files4.length);
        ImageResizer resizer4 = new ImageResizer(files4, dstFolder, start);
        resizer4.start();

    }
}