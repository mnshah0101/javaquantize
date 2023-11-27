package kmeans.main;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import kmeans.main.algo.KMeans;

public class Driver {

    public static void main(String[] args) {
    	
        String inputImagePath = args[0];
        String outputImagePath = args[1];

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(inputImagePath));
        } catch (IOException c) {
            c.printStackTrace();
        }

        //Create a raster and get the data from the image
        WritableRaster raster = img.getRaster();
        //Get the images height and width
        int height = raster.getHeight();
        int width = raster.getWidth();
        int size = height*width;
        int[] pixels = new int[size * raster.getNumDataElements()];
        //Get the pixels from the image and populate the array with it
        pixels = raster.getPixels(0, 0, width, height, pixels);

        KMeans kmeans = new KMeans(pixels, 10, 100);
        kmeans.fit();

        int[] newImage = kmeans.predict();
        
        int[] createImage;
        
        
        if(raster.getNumDataElements()<4) {

        createImage = new int[size*4];

        for(int i = 0; i < createImage.length; i++) {
            if(i%4 == 0) {
                createImage[i] = 255; // Max value for fully opaque
            } else {
                createImage[i] = newImage[(i-i/4)-1];
            }
        }
        }
        else {
            createImage = newImage;

        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster_out = image.getRaster();
        raster_out.setPixels(0, 0, width, height, createImage);

        File outputFile = new File(outputImagePath);
        try {
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image Written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}