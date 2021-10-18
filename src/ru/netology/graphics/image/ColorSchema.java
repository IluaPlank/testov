package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema{
    @Override
    public char convert(int color) {
        char c = 0;
        if (color > 240){
            c = 178;
        }
        if (color > 180 && color <= 240){
            c = 36;
        }
        return c ;
    }
}
