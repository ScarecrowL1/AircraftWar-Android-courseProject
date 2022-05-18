package DrawAction;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;

import aircraft.Prop;

public class Draw_Prop {
    public static void draw(List<Prop> Prop_List, Canvas canvas){
        for(Prop prop:Prop_List){
            Bitmap prop_image = prop.getImage();
            canvas.drawBitmap(prop_image,prop.getLocationX()-prop_image.getWidth()/2,prop.getLocationY()-prop_image.getHeight()/2,null);
        }
    }
}
