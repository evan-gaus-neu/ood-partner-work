package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import data.ColorPixel;

public class ImageProModelImpl implements ImageProModel {

  // Data
  // This represents the Map of all images
  // (stored in the <Name, Image> format)
  private Map<String, ColorPixel[][]> images;

  public ImageProModelImpl() {
    this.images = new HashMap<String, ColorPixel[][]>();
  }

  @Override
  public void loadImage(String path, String name) throws IllegalArgumentException {
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
      String s = scan.nextLine();
      if (s.charAt(0) != '#') {
        sb.append(s + "\n");
      }
    }

    // Now make the scanner read from the string we just built
    scan = new Scanner(sb.toString());

    // Check stuff
    String type = scan.next();
    if (type != "P6") {
      throw new IllegalArgumentException("Invalid PPM: Valid PPM should start with P6");
    }

    // Get info
    int width = scan.nextInt();
    int height = scan.nextInt();
    int maxValue = scan.nextInt();

    // Create the image array
    ColorPixel [][] image = new ColorPixel[height][width];

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

    // Put the array in the hashmap
    images.put(name, image);

  }

  @Override
  public void saveImage(String path, String name) throws IllegalArgumentException {
    // Check if the name is valid
    if (!images.containsKey(name)) {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }

    // The image
    ColorPixel [][] image = images.get(name);

    // Get the image as a string
    String str = new String();
    for (int i = 0; i < image.length; i++) {
      for (int k = 0; k < image[i].length; k++) {
        // Add this pixel
        ColorPixel cp = image[i][k];
        str += cp.getR() + " " + cp.getG() + " " + cp.getB() + " ";
      }
      // Add a new line
      str += "\n";
    }

    // Create a new file
    File newFile = new File(path);
    if (newFile.createNewFile()) {
      // Created a new file
      writeToFile(path, image[0].length, image.length, str);
    }

  }

  private void writeToFile(String path, int width, int height, String str) throws IllegalArgumentException {
    try {
      FileWriter fw = new FileWriter(path);
      fw.write("P6\n" + width + " " + height + "\n255\n" + str);
      fw.close();
      // Successfully wrote the file
    }
    catch (IOException e) {
      throw new IllegalArgumentException("File write failed");
    }
  }

  private void colorComponent(String input, String name, String dest) {
    // Might have to add error handling
    ColorPixel [][] oldImage = images.get(name);
    ColorPixel [][] newImage = oldImage;

    ImageProModelImpl newModel = this;
    for(int i = 0; i < newImage.length; i++) {
      for(int j = 0; j < newImage[i].length; j++) {
        ColorPixel pixel = new ColorPixel(oldImage[i][j].getR(),
                oldImage[i][j].getG(), oldImage[i][j].getB());
        if (input.equals("Red")) {
          newImage[i][j] = new ColorPixel(pixel.getR(), pixel.getR(), pixel.getR());
        }
        else if (input.equals("Green")) {
          newImage[i][j] = new ColorPixel(pixel.getG(), pixel.getG(), pixel.getG());
        }
        else if (input.equals("Blue")) {
          newImage[i][j] = new ColorPixel(pixel.getB(), pixel.getB(), pixel.getB());
        }
        else if (input.equals("Value")) {
          int max = Math.max(pixel.getB(), Math.max(pixel.getR(), pixel.getG()));
          newImage[i][j] = new ColorPixel(max, max, max);
        }
        else if (input.equals("Intensity")) {
          int average = (int) Math.round((pixel.getR() + pixel.getG() + pixel.getB())/3.0);
          newImage[i][j] = new ColorPixel(average, average, average);
        }
        else {
          int luma = (int) Math.round(0.2126 * pixel.getR() + 0.7152 * pixel.getG()
                  + 0.0722 * pixel.getB());
          newImage[i][j] = new ColorPixel(luma, luma, luma);
        }
      }
    }
    // Instead of returning
    images.put(dest, newImage);
  }

  @Override
  public void redComponent(String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      colorComponent("Red", name, dest);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void greenComponent(String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      colorComponent("Green", name, dest);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void blueComponent(String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      colorComponent("Blue", name, dest);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void valueComponent(String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      colorComponent("Value", name, dest);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void intensityComponent(String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      colorComponent("Intensity", name, dest);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void lumaComponent(String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      colorComponent("Luma", name, dest);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void horFlip(String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      ColorPixel[][] oldImage = images.get(name);
      ColorPixel[][] newImage = oldImage;

      for(int i = 0; i < newImage.length; i++) {
        for (int j = 0; j < newImage[i].length; j++) {
          newImage[i][j] = oldImage[i][(newImage[i].length) - 1 - j];
        }
      }
      images.put(dest, newImage);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void vertFlip(String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      ColorPixel[][] oldImage = images.get(name);
      ColorPixel[][] newImage = oldImage;

      for(int i = 0; i < newImage.length; i++) {
        for (int j = 0; j < newImage[i].length; j++) {
          newImage[i][j] = oldImage[(newImage.length) - 1 - i][j];
        }
      }
      images.put(dest, newImage);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

  @Override
  public void brighten(int increment, String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      ColorPixel[][] oldImage = images.get(name);
      ColorPixel[][] newImage = oldImage;

      for(int i = 0; i < newImage.length; i++) {
        for (int j = 0; j < newImage[i].length; j++) {
          ColorPixel pixel = new ColorPixel(oldImage[i][j].getR(),
                  oldImage[i][j].getG(), oldImage[i][j].getB());
          newImage[i][j].setR(pixel.getR() + increment);
          newImage[i][j].setG(pixel.getG() + increment);
          newImage[i][j].setB(pixel.getB() + increment);
        }
      }
      images.put(dest, newImage);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }
}
