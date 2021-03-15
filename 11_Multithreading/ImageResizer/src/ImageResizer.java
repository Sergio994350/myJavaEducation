import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread {

    private File[] files;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, String dstFolder, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int w = image.getWidth();
                int h = image.getHeight();
//                System.out.println("" + w + " " + h);
                int crop = 8;

                BufferedImage scaledImage = new BufferedImage(w / crop, h / crop, BufferedImage.TYPE_INT_ARGB);
                final AffineTransform at = AffineTransform.getScaleInstance(1.0 / crop, 1.0 / crop);
                final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                scaledImage = ato.filter(image, scaledImage);
//                System.out.println("" + scaledImage.getHeight() + " " + scaledImage.getWidth());
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(scaledImage, "png", newFile);

//                int newHeight = (int) Math.round(
//                        image.getHeight() / (image.getWidth() / (double) newWidth)
//                );
//                 BufferedImage newImageSc = Scalr.resize(image, newWidth, newHeight);
//                File newFile = new File(dstFolder + "/" + file.getName());
//                ImageIO.write(newImageSc, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms");
    }
}