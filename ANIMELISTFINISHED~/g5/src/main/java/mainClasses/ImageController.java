package mainClasses;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ImageController {

    public String controlImage(ArrayList<String> imageURLs) throws IOException,
            Exception {

        // Here we use a graphics writer to create a grid of a size relative to 
        // the number of URLs that are passed to this function 
        
        // Our grid is the background base for our individual images and so must be of a 
        // size equal to or greater than the total size of the images that we want to print to it
        
        // I want our grid to be a maximum of 4 images wide for display purposes. 
        // If more than 4 images exist in our array of images then the height of our grid must be 
        // expanded by the height of an image in order to fit a new row of images onto it

        // We first calculate the dimensions required for our grid 
        // We later print our images to the grid, altering their x/y coordinates when necessary to 
        // control their position on the grid and ensure that they maintain a 4-image-wide format
        
        int numberOfBoxes = imageURLs.size();
        
        // Since we know that we want our grid to be only be "4 images wide", 
        // we can divide the total number of images that we have in our array by 4
        // and find the number of instances of 4 that those images will occupy
        
        // In the case of 17 images we can see that 4.25 instances of 1x4 rows are 
        // taken up and that the total length of our grid will need to be able to accomodate
        // a value equal to 5 image heights since there is overflow of at least one image
        // onto a next row of 4. 
        
        // The ceil function returns "the smallest whole number that is greater than or exactly equal to" a value.
        // We can use this in the example of our 17 images to round the value 4.25 up to 5.
        
        // This allows us to always know exactly the number of "heights" of image required by our
        // grid and does not lead to inconsistencies where the required height of the grid is 
        // under- or overestimated
        
        // Multiply required number of image heights by 200px to give us actual required height value in pixels 

        int totalHeight = ((int) Math.ceil(((double) numberOfBoxes) / 4)) * 200;

        // Set our background grid to a constant 600 px in width (and later set out individual 
        // image widths to 1/4 that at 150)
        
        BufferedImage result = new BufferedImage(600, totalHeight, 

                BufferedImage.TYPE_INT_RGB);

        Graphics g = result.getGraphics();

        Integer x = 0;
        Integer y = 0;
        Integer w = 150;
        Integer h = 200;

        String generatedImageFilePath = "C:\\Users\\J\\Desktop\\resultimage2.png";

        for (String imageURL : imageURLs) {

            // For each string URL contained in ArrayList
            
            URL url = new URL(imageURL);
            
            // Read the image data of that url and draw it to our grid

            BufferedImage bi = ImageIO.read(url);
            g.drawImage(bi, x, y, w, h, null);
            
            // Afterwards, increment the x coordinate of the drawer by the
            // width of an image. If this value has risen to be equal to or greater
            // than the width of our background grid (600) then we want to point our
            // graphics drawer to new coordinates: increase the y coordinate (height) of the 
            // drawer by the height of an image and decrease the x coordinate (width) to 0.
            // This in effect puts at the start of a new, higher row. Loop to draw the next image
            // at this position. 
            
            x += 150;
            if (x >= result.getWidth()) {
                x = 0;
                y += 200;

            }

            ImageIO.write(result, "png", new File(generatedImageFilePath));

        }
        
        // When done, return our image path

        return generatedImageFilePath;

    }

    private static int roundUp(int gridDepth, int multiple) {
        return (gridDepth + multiple) / multiple;
    }

    private static int gridFix(int numberOfBoxes) {
        int gridDepth = (int) Math.ceil(((double) numberOfBoxes) / 4);
        return gridDepth;
    }
}
