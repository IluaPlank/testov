package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class GraphicsConverter implements TextGraphicsConverter{
    private int maxWidth = 0;
    private int maxHight = 0;
    private double ratio = 0;
    public int newHeight;
    public int newWidth;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage image = ImageIO.read(new URL(url));
        int width = image.getWidth();
        int height = image.getHeight();
        newWidth=width;
        newHeight=height;
        size(height,width);
        if (ratio !=0){
            if ((double)newWidth/newHeight > ratio)
                throw new BadImageSizeException((double) newWidth / newHeight, ratio);
        }

        Image scaledImage = image.getScaledInstance(newHeight,newWidth, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage (newHeight,newWidth, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImg, "png", new File("out.png"));


        WritableRaster bwRaster = bwImg.getRaster();
        ColorSchema schema = new ColorSchema();

        char [][] charsImage = new char[newWidth][newHeight];
        for (int h =0;h < newHeight;h++) {
            for  (int w =0;w<newWidth;w++){
                int color = bwRaster.getPixel(h, w, new int[3])[0];
                char c = schema.convert(color);
                charsImage[w][h] = (c);
            }
        }
        return Arrays.deepToString(charsImage).replaceAll("]", System.lineSeparator());
    }



    public void size (int height,int width){
        if (maxHight !=0 && maxHight !=height || maxWidth !=0 && maxWidth !=width){
            //установлен макс высота или макс ширина и они не равны стандарту картинки
            if (maxHight <= height){
                newHeight = maxHight;
            }
            else newHeight = height*maxHight/width;
            if (maxWidth <= width ){
                newWidth =maxWidth;
            }
            else newWidth = width*maxHight/height;
        }
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
