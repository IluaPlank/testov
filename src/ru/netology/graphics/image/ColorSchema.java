package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema{
    @Override
    public char convert(int color) {
        char [] c = {'#', '$', '@', '%', '*', '+', '-', '.', '-'};
        char result= c[8];

        if (color>226){
            result = c[0];
        }
        if (color>197 && color <= 226){
            result=c[1];
        }
        if (color>168 && color <=197){
            result=c[2];
        }
        if (color>139 && color <=168){
            result=c[3];
        }
        if (color>110 && color <=139){
            result=c[4];
        }
        if (color>81 && color <=110){
            result=c[5];
        }
        if (color>52 && color <=81){
            result=c[6];
        }
        if (color>23 && color <=52){
            result=c[7];
        }

        return result ;
    }
}
