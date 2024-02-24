import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel {
  
  Asteroid asteroid; 
  Ship ship;
  Dimension dimension = this.getSize();
  Rectangle2D.Double shipRect, asteroidRect,cabinRect,lWingRect,lTailRect;  // Rectangles acting as boundaries for collisions
  
  public GamePanel() { 
    asteroid = null; 
    ship = null;
  }


  public void createGameEntities() {  // creates game objects
    ship = new Ship(this, 100, (int) (this.getSize().height /2));
    shipRect = ship.getBoundingRectangle();
    lWingRect = ship.getLWing();
    lTailRect = ship.getLTail();
    asteroid = new Asteroid(this, 30, 100, ship); 
    asteroidRect = asteroid.getBoundingRectangle();
  }

  
  public void drawGameEntities() {
     if(ship!= null) ship.draw();
  }

  public void asteroidStorm() {  // creates asteroids simulation via a thread 
    if(asteroid == null) return;
    asteroid.start();  
  }

  public void updateGameEntities(int direction) {  // updates game objects and states
    if (ship == null) return;  
    ship.erase();
    ship.move(direction);  
	}

  
  // Getters & Setters

  public Asteroid getAsteroid() {
    return this.asteroid; 
  }
  
  public Ship getShip() {
    return this.ship; 
  }

  public Rectangle2D.Double getShipRect() {
    return this.shipRect;
  }

  public Rectangle2D.Double getAsteroidRect() {
    return this.asteroidRect;
  }

  public GamePanel getGamepanel() {
    return this;
  }

}