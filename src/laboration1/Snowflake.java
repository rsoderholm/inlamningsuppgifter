package laboration1;
import f1.PaintWindow;
import java.awt.*;
/**
 *
 * @author tsroax
 */
public class Snowflake {
    public Point getTip(int x, int y, int len, int pos) {
        Point res = null;
        switch(pos % 6) {
            case 0 : res = new Point(x + len, y); break;
            case 1 : res = new Point(x + (int)(Math.cos(Math.PI/3)*len), y - (int)(Math.sin(Math.PI/3)*len)); break;
            case 2 : res = new Point(x - (int)(Math.cos(Math.PI/3)*len), y - (int)(Math.sin(Math.PI/3)*len)); break;
            case 3 : res = new Point(x - len, y); break;
            case 4 : res = new Point(x - (int)(Math.cos(Math.PI/3)*len), y + (int)(Math.sin(Math.PI/3)*len)); break;
            case 5 : res = new Point(x + (int)(Math.cos(Math.PI/3)*len), y + (int)(Math.sin(Math.PI/3)*len)); break;
        }
        return res;
    }
    
    public void snowflake( PaintWindow frame, int x, int y, int len, Color color ) {
        frame.line(x-len, y, x+len, y, color, 1);
        frame.line(x + (int)(Math.cos(Math.PI/3)*len), y - (int)(Math.sin(Math.PI/3)*len), x - (int)(Math.cos(Math.PI/3)*len), y + (int)(Math.sin(Math.PI/3)*len), color, 1);
        frame.line(x - (int)(Math.cos(Math.PI/3)*len), y - (int)(Math.sin(Math.PI/3)*len), x + (int)(Math.cos(Math.PI/3)*len), y + (int)(Math.sin(Math.PI/3)*len), color, 1);
    }
    
    public Color randomColor() {
        return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
    }
        
    // Ersätt halvkoden med kod
    public void newSnowflake( PaintWindow frame, int x, int y, int len, Color color ) {
        // Om flingans bredd (len) är minst 10
        //     Rita en flinga genom anrop till metoden snowflake med parametrarnas värden
        //     Generera en slumpfärg genom att anropa metoden randomColor
        //     Gör sex REKURSIVA anrop till newSnowflake. Vid anropen ska den nyss ritade flingans 
        //     spetsar vara nya positioner (se nedan), och len ska endast vara en 3:e-del mot 
        //     tidigare. Färgen ska vara den nyss genererade slumpfärgen.
    }
//     REKURSIVT ANROP TILL newSnowflake
//     Koordinater för flingans spetsar kan du erhålla genom anrop till metoden getTip( s, y, len, pos) där pos ska ha något av värdena 0-5
//     getTip returnerar ett Point-objekt: 
//     Point p;
//     p = getTip(x, y, len, 0);
//     newSnowflake( frame, p.x, p.y, len/3, randomColor() );
    
    public static void main(String[] args) {
        PaintWindow frame = new PaintWindow(600,600,Color.BLACK);
        Snowflake graf = new Snowflake();
        graf.newSnowflake(frame, 300, 300, 200, Color.WHITE);    
    }    
}
