import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import javax.swing.JPanel;


public class Ship {

  private JPanel panel;
  private int x, y;  // x-y coordinates of ship
  private int w, h;  // width and height of ship object (in pixels)

  private  int dx; // increment of x-axis i.e speed
  private  int dy;  // increment of y-axis i.e speed

  private Color backgroundColour;
  private Dimension dimension;

  // Subcomponents of Ship Object

  protected Rectangle2D.Double body; // body of ship
  protected Rectangle2D.Double cabin; // cabin of ship
  protected Rectangle2D.Double lWing; // left wing of ship
  protected Rectangle2D.Double rWing; // right wing of ship
  protected Rectangle2D.Double lTail; // left wing of ship
  protected Rectangle2D.Double rTail; // right wing of ship

  protected Area temp;   // reference varible to store the ship


  public Ship (JPanel p, int xPos, int yPos) {

    this.panel = p;
    this.dimension = panel.getSize();
    this.backgroundColour = panel.getBackground();

    this.x = xPos;  // starting x-coordinate of ship
    this.y = yPos;  // starting y-coordinate of ship

    this.w=60;
    this.h=30;
    this.dx = 10;
    this.dy = 8;
  }


  public void draw() {  // draws the ship object

     // creates ship parts and instantiates them

    body = new Rectangle2D.Double(x, y, 50, 20); 
    cabin = new Rectangle2D.Double(x+15, y-15, 20, 15);
    lWing = new Rectangle2D.Double(x-10, y, 10, 20);
    rWing = new Rectangle2D.Double(x+50, y, 10, 20); 
    lTail = new Rectangle2D.Double(x, y+20, 10, 10);
    rTail = new Rectangle2D.Double(x+40, y+20, 10, 10);
    
    // adds ship parts into a single object(ship) via the Area class
    
    Area ship =  new Area(body);
    ship.add(new Area(cabin));
    ship.add(new Area(lWing));
    ship.add(new Area(rWing));
    ship.add(new Area(lTail));
    ship.add(new Area(rTail));

    Graphics g = panel.getGraphics();
    Graphics2D g2 = (Graphics2D) g;

    // draw and color ship and its components
    
    g2.setColor(Color.BLUE);
    
    g2.fill(body);
    g2.fill(cabin);
    g2.fill(lWing);
    g2.fill(rWing);
    g2.fill(lTail);
    g2.fill(rTail);

    temp = new Area(ship); // copy reference for updating object later

    g2.dispose();
  }


  public void erase() {  // erases ship by drawing over it
 
    Graphics g = panel.getGraphics();
    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(backgroundColour);
    g2.fill(temp);    // a copy of the ship object and its dimensions to draw over and erase

    g2.dispose();
  }


  public void move(int direction) {  // moves ship object across the screen
    
    if(!panel.isVisible()) return;
    dimension = panel.getSize();

    if (direction == 1) {	// move left
      x -= dx;
      if (x <- w)                    // wrap around screen from left side
        x = dimension.width - 10;
      return;
    }
    
    else if (direction == 2) {  	// move right
      x += dx;
      if (x > dimension.width - 10)  // wrap around screen from right side
        x =- w;
      return;
    }

    else if(direction == 3) { // move up
    y -= dy;
    if(y < 0)
      y = 0;  // prevents ship from moving off the top of the screen
    return;
    }

    else if(direction == 4) { // move down
      y += dy;
      if(y > dimension.height - h)
        y = dimension.height - h; // prevents ship from moving off the bottom of the screen
      return;
    }
      
    else {
      x=x;
      y=y;
    }
    
  }
  

  public Rectangle2D.Double getBoundingRectangle() { // creates rectangle to act as boundary
      return new Rectangle2D.Double (x, y, w+5, h+20);
   }

  
  // Getters & Setters
  
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return w;
  }

  public int getHeight() {
    return h;
  }

  public Rectangle2D.Double getBody() {
    return body;
  }
  public Rectangle2D.Double getCabin() {
    return cabin;
  }

  public Rectangle2D.Double getLWing() {
    return lWing;
  }
  public Rectangle2D.Double rWing() {
    return rWing;
  }
  public Rectangle2D.Double getLTail() {
    return lTail;
  }

  public Rectangle2D.Double getRTail() {
    return rTail;
  }

  

}