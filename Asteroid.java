import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import java.util.Random;


public class Asteroid extends Thread {

  private JPanel panel;
  private final static int UNIT_SIZE = 25;  // game unit size i.e max size
  private int x, y;   //x-y coordinates of asteroid
  private int topY;
  
  private int dx = 0;		// increment to move along x-axis i.e speed 
  private int dy = 10;		// increment to move along y-axis i.e speed 
  
  private int w = UNIT_SIZE, h = UNIT_SIZE;  // default width and height of an asteroid 
  
  Ellipse2D.Double asteroid;	    // object representing asteroid
  private Color backgroundColour;
  private Dimension dimension;
  private boolean isRunning, isPaused;     // simulation states
  private Random num;
  private Ship ship;    // reference to ship object
  
  public Asteroid (JPanel p, int xPos, int yPos, Ship ship) {

    this.panel = p;
    this.dimension = panel.getSize();
    this.backgroundColour = panel.getBackground();
   
    x = xPos;
    y = yPos;
    this.ship=ship;
    this.topY = yPos;

    num = new Random();
    x = num.nextInt(dimension.width - UNIT_SIZE); // randomizes x-coordinate of asteroid 
  }


  public void setLocation() {  // sets location of asteroid randomly
    
    int panelWidth = panel.getWidth(), n;  
    num = new Random();
    
    n = num.nextInt (panelWidth - w);
    x = n;
    y = topY;
  }
  

  public void draw() { // draws asteroid on the panel
    
    Graphics g = panel.getGraphics();
    Graphics2D g2 = (Graphics2D) g;

    asteroid = new Ellipse2D.Double(x, y, w, h);  // instantiates asteroid to draw dimensions
   
    g2.draw(asteroid);
    g2.setColor(Color.GRAY);   
    g2.fill(asteroid); 

    g2.dispose();
  }

  
  public void erase () { // erases asteroid by drawing over it with the background colour

    Graphics g = panel.getGraphics ();
    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(backgroundColour);
    g2.fill(new Rectangle2D.Double(x-10,y-10,w+20,h+20));
    g2.dispose();
  }


  public void move() {  // movement simulation process of asteroid object
    if (!panel.isVisible ()) return;

    x += dx;
    y += dy;

    int height = panel.getHeight();
    boolean collision = collidesWithShip();  // checks if asteroid collides with ship

    if (collision) {
      setLocation();		// resets object location
    }
    
    else
      if (y > height) {
        setLocation();
        dy = dy + 1;			// speeds up asteroid once location was reset
      }
    
   }

  
  public void run() {   // simulation process of asteroid object

    isRunning = true;

    try { 
      while (isRunning) {
        erase();
        move();
        draw();
        Thread.sleep(90); // increase to slow down and vice-versa 
      }

    }
    catch (InterruptedException e) {
        e.printStackTrace();  // error tracing exception i.e shows what went wrong and where (method)
    }

  }

  
  public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (x, y, w, h);
   }


  public boolean collidesWithShip() {  // checks asteroid object collision with ship object
    Rectangle2D.Double myRect = getBoundingRectangle();
    Rectangle2D.Double shipRect = ship.getBoundingRectangle();
    return (myRect.intersects(shipRect)); 
  }

  
  // Getters & Setters

  public int getX() {
    return x; }

  public int getY() {
    return y; }
  
  public int getWidth() {
    return w; }

  public int getHeight() {
    return h; }

  public int getUnitSize() {
    return UNIT_SIZE; }

  public int getDx(){
    return dx; }

  public int getDy() {
    return dy; }

  public Ellipse2D.Double getAsteroid() {
    return asteroid; }

  public int getPos(Asteroid key, Asteroid[] asteroids) {  // gets position of asteroid in array
    int i;
      for (i = 0; i < asteroids.length; i++) {
        if (asteroids[i] == key) return i;
      }
      // If we get here, key was not found in array
      return -1;
  }
  
  public boolean getGameState() { 
    return isRunning; }
  
}