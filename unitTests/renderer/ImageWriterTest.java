package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void writeToImage() {
        int nX=800;
        int nY=500;
        ImageWriter imageWriter=new ImageWriter("testYellow" ,nX,nY);//יצרנו משטח לתמונה חדשה
        Color Yellow=new Color(java.awt.Color.YELLOW);
        Color red= new Color(255d,0,0);
        for (int i = 0; i <nX ; i++) {
            for (int j = 0; j <nY ; j++) {
                //800/50=16
                //50/50=10 -בשביל הפסים
                if ((i%50==0 || j%50==0))
                imageWriter.writePixel(i,j, red);
                else
                    imageWriter.writePixel(i,j, Yellow);
            }
        }
        imageWriter.writeToImage();//כתיבה לזכרון
    }
}

