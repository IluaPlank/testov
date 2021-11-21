package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema {

    private static final char[] SYMBOL = {'#', '$', '@', '%', '*', '+', ':', '.', '-'};
    int result;

    @Override
    public char convert(int color) {
        return SYMBOL[result = color / 29];
    }
}
