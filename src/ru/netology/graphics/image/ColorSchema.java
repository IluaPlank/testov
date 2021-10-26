package ru.netology.graphics.image;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ColorSchema implements TextColorSchema{

    char [] c = {'#', '$', '@', '%', '*', '+', ':', '.', '-'};
    int result;
    @Override
    public char convert(int color) {
        if (color == 0){
            result = 0;
        }
        else result=color/28;

        return c[result];
    }
}
