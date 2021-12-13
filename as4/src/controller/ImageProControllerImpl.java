package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import data.ColorPixel;
import model.IPMV2;
import view.ImageProView;

/**
 * The class that acts as the Controller for this design
 * and is based on the interface ImageProController.
 */
public class ImageProControllerImpl implements ImageProController {

  // Data
  private IPMV2 model;
  private ImageProView view;
  private Readable in;

  // Constructor
  /**
   * This is the constructor for the ImageProControllerImpl that is used to make an instance.
   * @param model an ImageProModel that is used for the model field.
   * @param view an ImageProView that is used for the view field.
   * @param in a Readable that is used for getting an input.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public ImageProControllerImpl(IPMV2 model, ImageProView view, Readable in)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model was null");
    }
    if (view == null) {
      throw new IllegalArgumentException("View was null");
    }
    if (in == null) {
      throw new IllegalArgumentException("Readable was null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }


  @Override
  public void run() throws IllegalStateException {

    // Important data
    Scanner scan = new Scanner(in);
    boolean quit = false;
    // Other stored things

    // Do a welcome message?
    renderMessageHelper("Welcome to the Image Processor!\n");

    // Loop while not quit
    while (!quit) {

      // Print message
      renderMessageHelper("Type instruction: \n");

      // Get the next input (QQ maybe check if there is one first)
      String userInput = scan.next();

      // Switch for userInput
      switch (userInput) {
        case "load": // load image
          try {
            loadImage(scan.next(), scan.next());
            renderMessageHelper("Image loaded!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "save": // save image
          try {
            saveImage(scan.next(), scan.next());
            renderMessageHelper("Image saved!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "red-component":
          try {
            model.redComponent(scan.next(), scan.next());
            renderMessageHelper("Red component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "green-component":
          try {
            model.greenComponent(scan.next(), scan.next());
            renderMessageHelper("Green component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "blue-component":
          try {
            model.blueComponent(scan.next(), scan.next());
            renderMessageHelper("Blue component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "value-component":
          try {
            model.valueComponent(scan.next(), scan.next());
            renderMessageHelper("Value component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "intensity-component":
          try {
            model.intensityComponent(scan.next(), scan.next());
            renderMessageHelper("Intensity component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "luma-component":
          try {
            model.lumaComponent(scan.next(), scan.next());
            renderMessageHelper("Luma component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "horizontal-flip":
          try {
            model.horFlip(scan.next(), scan.next());
            renderMessageHelper("Horizontal flip created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "vertical-flip":
          try {
            model.vertFlip(scan.next(), scan.next());
            renderMessageHelper("Vertical flip created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "brighten":
          try {
            model.brighten(scan.nextInt(), scan.next(), scan.next());
            renderMessageHelper("Brighter / Darker image created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          catch (InputMismatchException e) {
            renderMessageHelper("Invalid input for increment!\n");
          }
          break;
        case "blur":
          try {
            model.blur(scan.next(), scan.next());
            renderMessageHelper("Blurred image created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "sharpen":
          try {
            model.sharpen(scan.next(), scan.next());
            renderMessageHelper("Sharpened image created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "greyscale":
          try {
            model.greyscale(scan.next(), scan.next());
            renderMessageHelper("Greyscale image created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "sepia":
          try {
            model.sepia(scan.next(), scan.next());
            renderMessageHelper("Sepia image created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "resize":
          try {
            model.resize(scan.nextInt(), scan.nextInt(), scan.next(), scan.next());
            renderMessageHelper("Resized image created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          catch (InputMismatchException e) {
            renderMessageHelper("Invalid input for size!\n");
          }
          break;
        case "q":
        case "quit":
          quit = true;
          renderMessageHelper("Program quit. Thanks!");
          break;
        default: // Unrecognized command
          renderMessageHelper("Unrecognized Command: " + userInput + "\n");
      }

    }

  }

  private void renderMessageHelper(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }









  // For buffered images it's x, then y
  // For our arrays its Y then X

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
    String extension = path.substring(path.lastIndexOf('.'));

    // Call something based on the extension
    if (extension.equals(".ppm")) {
      savePpmImage(path, name);
    }
    else {
      saveOtherImage(path, name);
    }

  }

  protected void saveOtherImage(String path, String name) throws IllegalArgumentException {
    // We need to create a buffered image with the dimensions necessary
    // Then set all the pixels
    // Then do the fancy read write stuff

    // Get the pixel image
    ColorPixel[][] image = model.saveImage(name);

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

    String extension;

    try {
      extension = path.substring(path.lastIndexOf('.') + 1);
    }
    catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid file extension");
    }


    // Write the file
    try {
      File outputFile = new File(path);
      ImageIO.write(bufferedImage, extension, outputFile);
    }
    catch (IOException e) {
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
      throw new IllegalArgumentException("File write failed");
    }
  }

}
