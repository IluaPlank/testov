package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema{
    @Override
    public char convert(int color) {
        return (char) 255;  
    }
}
