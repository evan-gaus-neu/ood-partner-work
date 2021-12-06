package model.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import model.ImageProcessingModel;

/**
 * This class contains utility methods to read a PPM image and its max pixel value from file.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and return a 3d array representing the pixels.
   * @param filename the path of the file.
   * @return a 3d int array representing height, width, color pixels,
   *     or returns null if the code cannot read the image.
   */
  public static int[][][] readPPM(String filename) {
    Scanner sc;
    String[] ext = filename.split("\\.");
    if (ext.length == 1 || !ext[ext.length - 1].equals("ppm")) {
      return null;
    }
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.length() > 0 && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      return null;
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt();

    int[][][] data = new int[height][width][4];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        data[i][j][0] = r;
        data[i][j][1] = g;
        data[i][j][2] = b;
        data[i][j][3] = 255;
      }
    }
    return data;
  }

  /**
   * Read an image file in a non PPM format and return a 3d array representing the pixels.
   * @param filename the path of the file.
   * @return a 3d int array representing height, width, color pixels,
   *     or returns null if the code cannot read the image.
   */
  public static int[][][] readNonPPM(String filename) {

    File file = new File(filename);
    BufferedImage img;
    try {
      img = ImageIO.read(file);
    } catch (IOException e) {
      return null;
    }
    try {
      int q = img.getHeight();
      int p = img.getWidth();
      int[][][] data = new int[q][p][4];
      for (int i = 0; i < q; i++) {
        for (int j = 0; j < p; j++) {
          int pixel = img.getRGB(j, i);
          // bitwise and with max pixel value to keep first 8 bits post shifting
          int alpha = (pixel >> 24) & 255;
          int r = (pixel >> 16) & 255; // Red stored in bits 16 through 24
          int g = (pixel >> 8) & 255; // Green stored in bits 8 through 16
          int b = pixel & 255; // Blue stored in bits 8 through 0
          data[i][j][0] = r;
          data[i][j][1] = g;
          data[i][j][2] = b;
          data[i][j][3] = alpha;
        }
      }
      return data;

    } catch (NullPointerException e) {
      return null;
    }
  }

  /**
   * Saves the image to the string path file specified in ppm format.
   * @param m a ImageProcessingModel that represents the model that wants to be saved.
   * @param path a string representing where to save the model.
   * @throws IOException if the file cannot be written to.
   */
  public static void savePPM(ImageProcessingModel m, String path)
          throws IOException {
    OutputStream output = new FileOutputStream(path);
    int[][][] d = m.getData();

    StringBuilder content = new StringBuilder("P3\n");
    content.append(d[0].length).append(" ").append(d.length).append("\n");
    content.append(m.getMaxValue()).append("\n");

    for (int i = 0; i < d.length; i++) {
      for (int j = 0; j < d[i].length; j++) {
        content.append(d[i][j][0]).append("\n")
                .append(d[i][j][1]).append("\n")
                .append(d[i][j][2]).append("\n");
      }
    }

    byte[] bytesArray = content.toString().getBytes();
    output.write(bytesArray);
    output.close();
  }

  /**
   * Saves the image to the string path file specified in a non ppm format.
   * @param m a ImageProcessingModel that represents the model that wants to be saved.
   * @param path a string representing where to save the model.
   * @param ext a string representing the file extension the new file will be saved.
   * @throws IOException if the file cannot be written to.
   */
  public static void saveNonPPM(ImageProcessingModel m, String path, String ext)
          throws IOException {
    int[][][] d = m.getData();
    BufferedImage bi = new BufferedImage(d[0].length, d.length, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < d.length; i++) {
      for (int j = 0; j < d[i].length; j++) {
        int rgb = d[i][j][3] << 24 | d[i][j][0] << 16 | d[i][j][1] << 8 | d[i][j][2];
        bi.setRGB(j, i, rgb);
      }
    }
    File outputfile = new File(path);
    ImageIO.write(bi, ext, outputfile);
  }

  /**
   * Read an image file in the PPM format and return the max value that a pixel can be.
   * @param filename the path of the file.
   * @return an int representing the max value that a pixel can be.
   */
  public static int getMaxValue(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      return -1;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    sc.next();
    sc.nextInt();
    sc.nextInt();
    return sc.nextInt();
  }

}

