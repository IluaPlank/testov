package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema {

    private static final char[] symbol = {'#', '$', '@', '%', '*', '+', ':', '.', '-'};
    int result;

    @Override
    public char convert(int color) {
        return symbol[result = color / 29];
    }
}
