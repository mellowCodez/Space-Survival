import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for Layout Managers
import java.awt.event.*;		// need this to respond to GUI events


public class GameWindow extends JFrame 
        implements ActionListener, KeyListener, MouseListener {
  
  private final int WIDTH = 400, HEIGHT = 400;
  private final int WINDOW_W = 600, WINDOW_H = 550;
  private int score, lives;

  // declare labels 

  private JLabel statusBarL;
  private JLabel keyL;
  private JLabel mouseL;
  private JLabel livesL;
  private JLabel scoreL;

  // declare text fields

  private JTextField statusBarTF;
  private JTextField livesTF;
  private JTextField scoreTF;

  // declare buttons

  private JButton startB;
  private JButton dropB;
  private JButton exitB;

  private Container c;

  private JPanel mainPanel;
  private GamePanel gamePanel;

  @SuppressWarnings({"unchecked"})
  public GameWindow() {
    
    score=0;
    lives=3;
    
    // set up the window
    
    setTitle ("Space Survival");
    setSize (WINDOW_W, WINDOW_H);

    // create user interface objects

    
    // create labels

    statusBarL = new JLabel ("S T A T U S :");
    livesL = new JLabel("L I V E S :" );
    scoreL = new JLabel("S C O R E :");

    // create text fields and set their colour, etc.

    statusBarTF = new JTextField (25);
    livesTF = new JTextField (25);
    scoreTF = new JTextField (25);

    statusBarTF.setEditable(false);
    livesTF.setEditable(false);
    scoreTF.setEditable(false);


    statusBarTF.setBackground(Color.WHITE);
    livesTF.setBackground(Color.WHITE);
    scoreTF.setBackground(Color.WHITE);

   
    // create buttons

    startB = new JButton ("S T A R T");
    dropB = new JButton ("DROP ASTEROIDS");
    exitB = new JButton ("E X I T");

    // add listener to each button (same as the current object)

    startB.addActionListener(this);
    dropB.addActionListener(this);
    exitB.addActionListener(this);
    exitB.addActionListener(this);


    // create mainPanel

    mainPanel = new JPanel();
    FlowLayout flowLayout = new FlowLayout();
    mainPanel.setLayout(flowLayout);
    GridLayout gridLayout;
    

    // create the gamePanel for game entities

    gamePanel = new GamePanel();
    gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    gamePanel.setBackground(Color.BLACK);
    

    // create infoPanel

    JPanel infoPanel = new JPanel();
    gridLayout = new GridLayout(3, 2);
    infoPanel.setLayout(gridLayout);
    infoPanel.setBackground(Color.MAGENTA);

    // add user interface objects to infoPanel

    infoPanel.add(statusBarL);
    infoPanel.add(statusBarTF);

    infoPanel.add(livesL);
    infoPanel.add(livesTF);

    infoPanel.add(scoreL);
    infoPanel.add(scoreTF);


  // create buttonPanel

    JPanel buttonPanel = new JPanel();
    gridLayout = new GridLayout(1, 3);
    buttonPanel.setLayout(gridLayout);

    // add buttons to buttonPanel

    buttonPanel.add (startB);
    buttonPanel.add (dropB);
    buttonPanel.add (exitB);
    buttonPanel.add (exitB);

    // add sub-panels with GUI objects to mainPanel and set its colour

    mainPanel.add(infoPanel);
    mainPanel.add(gamePanel);
    mainPanel.add(buttonPanel);
    mainPanel.setBackground(Color.BLUE);

    // set up mainPanel to respond to keyboard and mouse

    gamePanel.addMouseListener(this);
    mainPanel.addKeyListener(this);

    // add mainPanel to window surface

    c = getContentPane();
    c.add(mainPanel);

    // set properties of window

    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
 
    gamePanel.createGameEntities(); // creates game objects i.e. asteroids and ship
    statusBarTF.setText("Game Loaded..."); // set status bar message
    livesTF.setText(Integer.toString(getLives()));  // shows player lives 
    scoreTF.setText(Integer.toString(getScore()));   // shows playe score
  }


  // implement single method in ActionListener interface

  public void actionPerformed(ActionEvent e) {

    String command = e.getActionCommand();
    statusBarTF.setText(command + " button clicked.");

    if (command.equals(startB.getText())) {
      gamePanel.drawGameEntities();
      statusBarTF.setText("Game Started...");
      long startTime = System.currentTimeMillis();
      long duration = 3000; 
      while (System.nanoTime() - startTime < duration) {
            // Waiting 3 seconds 
      }
      gamePanel.asteroidStorm();
      statusBarTF.setText("Asteroids Dropped...");
      statusBarTF.setText("Asteroid Storm!!!");
    }

    if (command.equals(dropB.getText())) {
      gamePanel.asteroidStorm();
      statusBarTF.setText("Asteroids Dropped...");
    }
    

    if (command.equals(exitB.getText())) 
       System.exit(0);

    mainPanel.requestFocus();
  }


  // implement methods in KeyListener interface

  public void keyPressed(KeyEvent e) {

    int keyCode = e.getKeyCode();
    String keyText = e.getKeyText(keyCode);

    if(keyCode == KeyEvent.VK_LEFT) { // when moving left
      gamePanel.updateGameEntities(1);
      gamePanel.drawGameEntities();
      
      if(updateScore(gamePanel.getAsteroid())) {
        scoreTF.setText(Integer.toString(getScore()/100));
        scoreTF.setBackground (Color.GREEN);
        if(getScore()/100 >= 1)
          statusBarTF.setText("Scored Points");
        statusBarTF.setBackground(Color.YELLOW);
      }

      if(updateLives(gamePanel.getAsteroid())) {
        livesTF.setText(Integer.toString(getLives()));
        livesTF.setBackground (Color.RED);
        statusBarTF.setText ("Lost a Life!");
        statusBarTF.setBackground (Color.ORANGE);

        if(getLives() == 0) {
          statusBarTF.setBackground (Color.RED);
          statusBarTF.setText ("Game Over!!!");
          scoreTF.setText(Integer.toString(getScore()));
          livesTF.setText(Integer.toString(getLives()));
          statusBarTF.setEnabled(false);
          scoreTF.setEnabled(false);
          livesTF.setEnabled(false);
          resetScore();
          resetLives();
          return;
        }
        
      }
      
    }

    if(keyCode == KeyEvent.VK_RIGHT) { // when moving right
      gamePanel.updateGameEntities(2);
      gamePanel.drawGameEntities();
      
      if(updateScore(gamePanel.getAsteroid())) {
        scoreTF.setText(Integer.toString(getScore()/100));
        scoreTF.setBackground (Color.GREEN);
        if(getScore()/100 >= 1)
          statusBarTF.setText("Scored Points");
        statusBarTF.setBackground(Color.YELLOW);
      }
      
      if(updateLives(gamePanel.getAsteroid())) {
        livesTF.setText(Integer.toString(getLives()));
        livesTF.setBackground (Color.RED);
        statusBarTF.setText ("Lost a Life!");
        statusBarTF.setBackground (Color.ORANGE);

        if(getLives() == 0) {
          statusBarTF.setBackground (Color.RED);
          statusBarTF.setText ("Game Over!!!");
          scoreTF.setText(Integer.toString(getScore()));
          livesTF.setText(Integer.toString(getLives()));
          statusBarTF.setEnabled(false);
          scoreTF.setEnabled(false);
          livesTF.setEnabled(false);
          resetScore();
          resetLives();
          return;
        }

      }
         
    }

    if(keyCode == KeyEvent.VK_UP) {   // when moving up
      gamePanel.updateGameEntities(3);
      gamePanel.drawGameEntities();

      if(updateScore(gamePanel.getAsteroid())) {
        scoreTF.setText(Integer.toString(getScore()/100));
        scoreTF.setBackground (Color.GREEN);
        if(getScore()/100 >= 1)
          statusBarTF.setText("Scored Points");
        statusBarTF.setBackground(Color.YELLOW);
      }
      
      if(updateLives(gamePanel.getAsteroid())) {
        livesTF.setText(Integer.toString(getLives()));
        livesTF.setBackground (Color.RED);
        statusBarTF.setText ("Lost a Life!");
        statusBarTF.setBackground (Color.ORANGE);
        
        if(getLives() == 0) {
          statusBarTF.setBackground (Color.RED);
          statusBarTF.setText ("Game Over!!!");
          scoreTF.setText(Integer.toString(getScore()));
          livesTF.setText(Integer.toString(getLives()));
          statusBarTF.setEnabled(false);
          scoreTF.setEnabled(false);
          livesTF.setEnabled(false);
          resetScore();
          resetLives();
          return;
        }

      }
         
    }

    if(keyCode == KeyEvent.VK_DOWN) {  // when moving down
      gamePanel.updateGameEntities(4);
      gamePanel.drawGameEntities();

      if(updateScore(gamePanel.getAsteroid())) {
        scoreTF.setText(Integer.toString(getScore()/100));
        scoreTF.setBackground (Color.GREEN);
        if(getScore()/100 >= 1)
          statusBarTF.setText("Scored Points");
        statusBarTF.setBackground(Color.YELLOW);
      }
      
      if(updateLives(gamePanel.getAsteroid())) {
         livesTF.setText(Integer.toString(getLives()));
         livesTF.setBackground (Color.RED);
         statusBarTF.setText ("Lost a Life!");
         statusBarTF.setBackground (Color.ORANGE);
         
        if(getLives() == 0) {
           statusBarTF.setBackground (Color.RED);
           statusBarTF.setText ("Game Over!!!");
           scoreTF.setText(Integer.toString(getScore()));
           livesTF.setText(Integer.toString(getLives()));
           statusBarTF.setEnabled(false);
           scoreTF.setEnabled(false);
           livesTF.setEnabled(false);
           resetScore();
           resetLives();
           return;
         }
 
      }    
      
    }
     
  }

  public void keyReleased(KeyEvent e) {

  }

  public void keyTyped(KeyEvent e) {

  }

  // implement methods in MouseListener interface

  public void mouseClicked(MouseEvent e) {

    int x = e.getX();
    int y = e.getY();
  }


  public void mouseEntered(MouseEvent e) {

  }

  public void mouseExited(MouseEvent e) {

  }

  public void mousePressed(MouseEvent e) {

  }

  public void mouseReleased(MouseEvent e) {

  }


 // Auxiliary Methods 
          
  boolean updateScore(Asteroid a) { // updates score when asteroids are dodged
    if(!a.collidesWithShip()) {
      this.score++;
      return true;
    }
      
    return false;
  }

          
  boolean updateLives(Asteroid a) { // updates lives when asteroids are hit
    if(a.collidesWithShip()) {
      this.lives--;
      return true;
    }
       
     return false;
  }

          
  // getters and setters

  public int getLives() {
    return this.lives; 
  }

  public int getScore() {
    return this.score; 
  }

  public void resetLives() {
    this.lives = 3;
  }

  public void resetScore() {
    this.score = 0;
  }
          
}