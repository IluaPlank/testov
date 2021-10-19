package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema{
    @Override
    public char convert(int color) {
        char c = 12;
        if (color > 240){
            c =(char) 178;
        }
        if (color > 180 && color <= 240){
            c = (char) 36;
        }
        return c ;
    }
}
