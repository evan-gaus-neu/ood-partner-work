package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.ColorPixel;
import model.IPMV2;
import model.IPModelV2;

/**
 * The Java frame that represents the GUI for the image processor.
 */
public class SwingFrame extends JFrame implements ActionListener,
        ItemListener, ListSelectionListener {

  // Model data
  private IPMV2 model;

  // All the data for this
  private JPanel mainPanel;
  private JLabel fileOpenDisplay;
  private JTextArea brightenAmount;
  private JTextArea pathText;

  /**
   * Constructor to create the JFrame for the GUI of the image processor.
   */
  public SwingFrame() {

    // Set up the basics
    super();
    setTitle("ImagePro");
    setSize(800, 700);

    // ALL THE MODEL THINGS ===== ===== ===== ===== =====
    // Make a model to work with
    model = new IPModelV2();

    // Stuff with the main panel
    mainPanel = new JPanel();
    // Arrange elements vertically
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    // Add a scroll bar for main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    // Add a button to open a file ===== ===== ===== =====
    //dialog boxes
    JPanel actionsPanel = new JPanel();
    actionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
    actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.PAGE_AXIS));
    // actionsPanel.setLayout(new FlowLayout());
    mainPanel.add(actionsPanel);

    // Save Load Panel
    JPanel saveLoadPanel = new JPanel();
    saveLoadPanel.setLayout(new FlowLayout());
    actionsPanel.add(saveLoadPanel);

    // Open button
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    saveLoadPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    saveLoadPanel.add(fileOpenDisplay);

    // Saving an image text box
    // text area
    pathText = new JTextArea(1, 20);
    pathText.setBorder(BorderFactory.createTitledBorder("Path to image (including file)"));
    saveLoadPanel.add(pathText);

    // Add the saving button
    JButton saveButton = new JButton("Save Image");
    saveButton.setActionCommand("Save");
    saveButton.addActionListener(this);
    saveLoadPanel.add(saveButton);


    // Add all of the functions ===== =====

    // BIG PANEL FOR THE EFFECTS
    JPanel bigEffectsPanel = new JPanel();
    bigEffectsPanel.setLayout(new BoxLayout(bigEffectsPanel, BoxLayout.PAGE_AXIS));
    bigEffectsPanel.setBorder(BorderFactory.createTitledBorder("Effects"));
    actionsPanel.add(bigEffectsPanel);


    // PANEL FOR THE EFFECTS
    JPanel effectsPanel = new JPanel();
    effectsPanel.setLayout(new GridLayout(3,4));
    bigEffectsPanel.add(effectsPanel);


    // Red comp
    JButton redButton = new JButton("Red Component");
    redButton.setActionCommand("Red Component");
    redButton.addActionListener(this);
    effectsPanel.add(redButton);
    // Green comp
    JButton greenButton = new JButton("Green Component");
    greenButton.setActionCommand("Green Component");
    greenButton.addActionListener(this);
    effectsPanel.add(greenButton);
    // Blue comp
    JButton blueButton = new JButton("Blue Component");
    blueButton.setActionCommand("Blue Component");
    blueButton.addActionListener(this);
    effectsPanel.add(blueButton);

    // Value comp
    JButton valueButton = new JButton("Value Component");
    valueButton.setActionCommand("Value Component");
    valueButton.addActionListener(this);
    effectsPanel.add(valueButton);
    // Intensity comp
    JButton intensityButton = new JButton("Intensity Component");
    intensityButton.setActionCommand("Intensity Component");
    intensityButton.addActionListener(this);
    effectsPanel.add(intensityButton);
    // Luma comp
    JButton lumaButton = new JButton("Luma Component");
    lumaButton.setActionCommand("Luma Component");
    lumaButton.addActionListener(this);
    effectsPanel.add(lumaButton);

    // Hor flip
    JButton horFlipButton = new JButton("Horizontal Flip");
    horFlipButton.setActionCommand("Horizontal Flip");
    horFlipButton.addActionListener(this);
    effectsPanel.add(horFlipButton);
    // Vert flip
    JButton vertFlipButton = new JButton("Vertical Flip");
    vertFlipButton.setActionCommand("Vertical Flip");
    vertFlipButton.addActionListener(this);
    effectsPanel.add(vertFlipButton);

    // Blur
    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    blurButton.addActionListener(this);
    effectsPanel.add(blurButton);
    // Sharpen
    JButton sharpButton = new JButton("Sharpen");
    sharpButton.setActionCommand("Sharpen");
    sharpButton.addActionListener(this);
    effectsPanel.add(sharpButton);
    // Greyscale
    JButton greyButton = new JButton("Greyscale");
    greyButton.setActionCommand("Greyscale");
    greyButton.addActionListener(this);
    effectsPanel.add(greyButton);
    // Sepia
    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(this);
    effectsPanel.add(sepiaButton);

    // Brighten + text
    // Brighten panel
    JPanel brightPanel = new JPanel();
    brightPanel.setLayout(new FlowLayout());
    brightPanel.setBorder(BorderFactory.createTitledBorder("Brighten"));
    bigEffectsPanel.add(brightPanel);
    // text area
    brightenAmount = new JTextArea(1, 20);
    brightenAmount.setBorder(BorderFactory.createTitledBorder("Amount to brighten (0-255)"));
    brightPanel.add(brightenAmount);

    // Add the bright button
    JButton brightButton = new JButton("Brighten Image");
    brightButton.setActionCommand("Bright");
    brightButton.addActionListener(this);
    brightPanel.add(brightButton);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, GIF, PNG, PPM Images", "jpg", "gif", "png", "ppm");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();

          // Get the path from as4
          String tempStr = f.getPath();
          try {
            int index = tempStr.indexOf("as4");
            if (index >= 0) {
              tempStr = tempStr.substring(index + 4);
            }
            else {
              tempStr = "error";
            }

          }
          catch (StringIndexOutOfBoundsException er) {
            tempStr = "error";
            fileOpenDisplay.setText("Error Loading image");
          }
          // Display the path
          fileOpenDisplay.setText(tempStr);

          // Open the image into the model
          loadImage(tempStr, "image");
          // Convert the image to a buffered image
          // Visualize the image
          fileOpenDisplay.setText("Loading image...");
          visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
          fileOpenDisplay.setText("Loaded image");
        }
      }
      break;
      case "Save": {
        boolean errorBool = false;
        // Get the path
        String path = pathText.getText();
        // Call save image
        try {
          saveImage(path, "image");
        }
        catch (IllegalArgumentException err) {
          fileOpenDisplay.setText(err.getMessage());
          errorBool = true;
        }
        if (!errorBool) {
          fileOpenDisplay.setText("Image saved");
        }
      }
      break;
      case "Red Component": {
        // Call sepia to the model
        model.redComponent("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Red running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Red run");
      }
      break;
      case "Green Component": {
        // Call sepia to the model
        model.greenComponent("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Green running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Green run");
      }
      break;
      case "Blue Component": {
        // Call sepia to the model
        model.blueComponent("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Blue running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Blue run");
      }
      break;
      case "Value Component": {
        // Call sepia to the model
        model.valueComponent("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Value running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Value run");
      }
      break;
      case "Intensity Component": {
        // Call sepia to the model
        model.intensityComponent("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Intensity running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Intensity run");
      }
      break;
      case "Luma Component": {
        // Call sepia to the model
        model.lumaComponent("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Luma running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Luma run");
      }
      break;
      case "Horizontal Flip": {
        // Call sepia to the model
        model.horFlip("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Horizontal Flip running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Horizontal Flip run");
      }
      break;
      case "Vertical Flip": {
        // Call sepia to the model
        model.vertFlip("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Vertical Flip running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Vertical Flip run");
      }
      break;
      case "Blur": {
        // Call sepia to the model
        model.blur("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Blur running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Blur run");
      }
      break;
      case "Sharpen": {
        // Call sepia to the model
        model.sharpen("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Sharpen running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Sharpen run");
      }
      break;
      case "Greyscale": {
        // Call sepia to the model
        model.greyscale("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Greyscale running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Greyscale run");
      }
      break;
      case "Sepia": {
        // Call sepia to the model
        model.sepia("image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Sepia running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Sepia run");
      }
      break;
      case "Bright": {
        boolean nonInt = false;
        // Get the brightness
        String brightStr = brightenAmount.getText();
        int brightNum = 0;
        try {
          brightNum = Integer.parseInt(brightStr);
        }
        catch (NumberFormatException e2) {
          brightNum = 0;
          nonInt = true;
        }

        // Call bright to the model
        model.brighten(brightNum,"image", "image");
        // Visualize the image
        fileOpenDisplay.setText("Brighten running...");
        visualizeBufferedImage(convertPixelArrayToBufferedImage(model.saveImage("image")));
        fileOpenDisplay.setText("Brighten run");
        if (nonInt) {
          fileOpenDisplay.setText("ERROR! Must give Bright an integer!");
        }
      }
      break;
      default: {
        // Do nothing
      }
      break;
    }

  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    // We don't need to do anything here
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // We don't need to do anything here either
  }


  // HELPER FUNCTIONS


  protected void visualizeBufferedImage(BufferedImage bufferedImage) {
    // Remove the old image potentially?
    try {
      mainPanel.remove(1);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      // There wasn't an item to remove
    }

    // Make the panel
    JPanel imagePanel = new JPanel();
    // Add a border and caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    // Add it to the main panel
    mainPanel.add(imagePanel, 1);

    // Opens the given image
    JLabel imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(bufferedImage));
    imageScrollPane.setPreferredSize(new Dimension(300,300));
    imagePanel.add(imageScrollPane);

    // Add the charts
    addColorCharts(model.saveImage("image"));
  }

  protected void addColorCharts(ColorPixel[][] image) {
    // Remove the old graph potentially?
    try {
      mainPanel.remove(2);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      // There wasn't an item to remove
    }

    // Generate r,g,b,intensity color pixel arrays
    model.redComponent("image","red");
    model.greenComponent("image","green");
    model.blueComponent("image","blue");
    model.intensityComponent("image","intensity");

    // Generate the arrays based on the images
    int[] redArray = generateArray(model.saveImage("red"));
    int[] greenArray = generateArray(model.saveImage("green"));
    int[] blueArray = generateArray(model.saveImage("blue"));
    int[] intensityArray = generateArray(model.saveImage("intensity"));

    // Make the charts
    // Make strings
    String s1 = "Red";
    String s2 = "Green";
    String s3 = "Blue";
    String s4 = "Intensity";

    // Make the data set
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Make the data sets
    // This only works if they're the same size array, which they are
    for (int i = 0; i < redArray.length; i++) {
      dataset.addValue(redArray[i], s1, String.valueOf(i));
      dataset.addValue(blueArray[i], s3, String.valueOf(i));
      dataset.addValue(greenArray[i], s2, String.valueOf(i));
      dataset.addValue(intensityArray[i], s4, String.valueOf(i));
    }

    // Create the chart
    JFreeChart chart = ChartFactory.createLineChart("RGBI Values",
            "Pixel Values", "Level", dataset);
    ChartPanel chartPanel = new ChartPanel(chart);
    // Are we supposed to be adding this to the main panel...?
    mainPanel.add(chartPanel);
  }

  protected int[] generateArray(ColorPixel[][] image) {

    // Loop through the image
    int[] arr = new int [256];

    // Loop through the image counting the numbers
    for (int i = 0; i < image.length; i++) {
      for (int k = 0; k < image[i].length; k++) {
        // Get just the R because they should all be the same
        int r = image[i][k].getR();
        // Add one to the value of this spot
        arr[r]++;
      }
    }

    // Return the array
    return arr;
  }

  // Loading an image stuff ===== ===== ===== ===== =====

  protected void loadImage(String path, String name) throws IllegalArgumentException {
    // This is the first handler, which figures out which type of file it is

    // Check what extension it is, get just the extension
    if (path.lastIndexOf('.') < 0) {
      throw new IllegalArgumentException("Invalid path, no file extension found");
    }
    String extension = path.substring(path.lastIndexOf('.'));

    // Call something based on the extension
    if (extension.equals(".ppm")) {
      loadPpmImage(path, name);
    }
    else {
      loadOtherImage(path, name);
    }

  }

  // Load other image
  protected void loadOtherImage(String path, String name) throws IllegalArgumentException {

    // Get the buffered image from the path
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new File(path));
    }
    catch (IOException e) {
      fileOpenDisplay.setText("ERROR! Attempt to read the file at given path failed");
      throw new IllegalArgumentException("Attempt to read the file at given path failed");
    }

    // Set up the image
    ColorPixel[][] image = new ColorPixel[bufferedImage.getHeight()][bufferedImage.getWidth()];

    // Loop through the image and get the pixels
    for (int i = 0; i < bufferedImage.getHeight(); i++) {
      for (int k = 0; k < bufferedImage.getWidth(); k++) {
        // Get the colors for this pixel, and set them for image
        Color c = new Color(bufferedImage.getRGB(k, i));
        // Make it a color pixel, then set it
        image[i][k] = new ColorPixel(0,0,0);
        image[i][k].setR(c.getRed());
        image[i][k].setG(c.getGreen());
        image[i][k].setB(c.getBlue());
      }
    }

    // Send the image to the model
    model.loadImage(name, image);
  }

  // Load just a ppm image
  protected void loadPpmImage(String path, String name) throws IllegalArgumentException {
    // Open the file in a scanner
    Scanner scan;
    try {
      scan = new Scanner(new FileInputStream(path));
    }
    catch (FileNotFoundException e) {
      fileOpenDisplay.setText("File: " + path + " was not found");
      throw new IllegalArgumentException("File: " + path + " was not found");
    }

    // Populate it ignoring comments
    StringBuilder sb = new StringBuilder();
    while (scan.hasNextLine()) {
      String st = scan.nextLine();
      if (st.length() > 0) {
        if (st.charAt(0) != '#') {
          sb.append(st + "\n");
        }
      }

    }

    // Now make the scanner read from the string we just built
    scan = new Scanner(sb.toString());

    // Check stuff
    String type = scan.next();
    if (!type.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM: Valid PPM should start with P3");
    }

    // Get info
    int width = scan.nextInt();
    int height = scan.nextInt();
    int maxValue = scan.nextInt();

    // Create the image array
    ColorPixel[][] image = new ColorPixel[height][width];

    // Populate the image array
    for (int i = 0; i < height; i++) {
      for (int k = 0; k < width; k++) {
        // Get the color
        int r = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();
        // Set the color
        image[i][k] = new ColorPixel(r, g, b);
      }
    }

    // Send the image to the model
    model.loadImage(name, image);

  }

  // Saving an image stuff ===== ===== ===== ===== =====

  // Save image handler
  protected void saveImage(String path, String name) throws IllegalArgumentException {
    // This is the handler, then it calls other save methods

    // Find out what extension it is
    String extension = "";
    try {
      extension = path.substring(path.lastIndexOf('.'));
    }
    catch (StringIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid file extension");
    }


    // Call something based on the extension
    if (extension.equals(".ppm")) {
      try {
        savePpmImage(path, name);
      }
      catch (IllegalArgumentException e) {
        throw e;
      }
    }
    else {
      try {
        saveOtherImage(path, name);
      }
      catch (IllegalArgumentException e) {
        throw e;
      }
    }

  }

  protected void saveOtherImage(String path, String name) throws IllegalArgumentException {
    // We need to create a buffered image with the dimensions necessary
    // Then set all the pixels
    // Then do the fancy read write stuff

    // Get the pixel image
    ColorPixel[][] image = model.saveImage(name);

    BufferedImage bufferedImage = convertPixelArrayToBufferedImage(image);

    String extension;

    try {
      extension = path.substring(path.lastIndexOf('.') + 1);
    }
    catch (IndexOutOfBoundsException e) {
      fileOpenDisplay.setText("Invalid file extension");
      throw new IllegalArgumentException("Invalid file extension");
    }


    // Write the file
    try {
      File outputFile = new File(path);
      ImageIO.write(bufferedImage, extension, outputFile);
    }
    catch (IOException e) {
      fileOpenDisplay.setText("Unable to write to the file at the given path");
      throw new IllegalArgumentException("Unable to write to the file at the given path");
    }

  }

  // Save a ppm image
  protected void savePpmImage(String path, String name) throws IllegalArgumentException {

    // Get the image from the model
    ColorPixel [][] image = model.saveImage(name);

    // Get the image as a string
    String str = "";
    for (int i = 0; i < image.length; i++) {
      for (int k = 0; k < image[i].length; k++) {
        // Add this pixel
        ColorPixel cp = image[i][k];
        str += cp.getR() + "\n";
        str += cp.getG() + "\n";
        str += cp.getB() + "\n";
      }
    }

    // Create a new file
    try {
      File newFile = new File(path);
      if (newFile.createNewFile()) {
        // Created a new file
        try {
          writeToFile(path, image[0].length, image.length, str);
        }
        catch (IllegalArgumentException e) {
          throw e;
        }
      }
      else {
        // File was already there
        try {
          writeToFile(path, image[0].length, image.length, str);
        }
        catch (IllegalArgumentException e) {
          throw e;
        }
      }
    }
    catch (IOException e) {
      fileOpenDisplay.setText("New file could not be created");
      throw new IllegalArgumentException("New file could not be created");
    }

  }

  protected void writeToFile(String path, int width, int height, String str)
          throws IllegalArgumentException {
    try {
      FileWriter fw = new FileWriter(path);
      fw.write("P3\n" + width + " " + height + "\n255\n" + str);
      fw.close();
      // Successfully wrote the file
    }
    catch (IOException e) {
      fileOpenDisplay.setText("File write failed");
      throw new IllegalArgumentException("File write failed");
    }
  }

  protected BufferedImage convertPixelArrayToBufferedImage(ColorPixel[][] image) {

    // Create the image
    BufferedImage bufferedImage = new BufferedImage(image[0].length, image.length,
            BufferedImage.TYPE_INT_RGB);

    // Set all the pixels of the image
    for (int i = 0; i < image.length; i++) {
      for (int k = 0; k < image[i].length; k++) {
        // Get the colors of the pixels
        int r = image[i][k].getR();
        int g = image[i][k].getG();
        int b = image[i][k].getB();

        // Create a color from the pixel
        Color c = new Color(r, g, b);

        // Set that
        bufferedImage.setRGB(k, i, c.getRGB());
      }
    }

    // Return the buffered image
    return bufferedImage;

  }

}
