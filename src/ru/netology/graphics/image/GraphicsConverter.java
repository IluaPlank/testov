package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GraphicsConverter implements TextGraphicsConverter{
    private int maxWidth = 0;
    private int maxHight = 0;
    private double ratio = 0;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage image = ImageIO.read(new URL(url));
        int width = image.getWidth();
        int height = image.getHeight();
        int newWidth=width;
        int newHeight=height;
        if (maxHight !=0 && maxHight !=height || maxWidth !=0 && maxWidth !=width){
            newWidth = height/(width/maxWidth);
            newHeight = width/(height/maxHight);
        }
        if (ratio !=0){
            if ((double)newWidth/newHeight > ratio)
                throw new BadImageSizeException((double) newWidth / newHeight, ratio);
        }

        Image scaledImage = image.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImg, "png", new File("out.png"));


        WritableRaster bwRaster = bwImg.getRaster();
        ColorSchema schema = new ColorSchema();

        String [][] textImage = new String[newWidth][newHeight];
        for (int w =0;w<newWidth;w++) {
            for (int h =0;h < newHeight;h++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                textImage[w][h]= String.valueOf(c);
            }
        }

        return textImage;
    }

    @Override
    public void setMaxWidth(int width) {
        maxWidth=width;
    }

    @Override
    public void setMaxHeight(int height) {
        maxHight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        ratio=maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
