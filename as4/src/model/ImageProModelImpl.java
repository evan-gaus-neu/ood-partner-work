package model;

import java.util.HashMap;
import java.util.Map;

import data.ColorPixel;

/**
 * The class that acts as the Model for this design
 * and is based on the interface ImageProModel.
 */
public class ImageProModelImpl implements ImageProModel {

  // Data
  // This represents the Map of all images
  // (stored in the <Name, Image> format)
  protected Map<String, ColorPixel[][]> images;

  public ImageProModelImpl() {
    this.images = new HashMap<String, ColorPixel[][]>();
  }

  @Override
  public void loadImage(String name, ColorPixel[][] image) throws IllegalArgumentException {
    // Put the array in the hashmap
    images.put(name, image);
  }

  @Override
  public ColorPixel[][] saveImage(String name) throws IllegalArgumentException {
    // Check if the name is valid
    if (!images.containsKey(name)) {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
    // The image
    return images.get(name);
  }


  private void colorComponent(String input, String name, String dest) {
    ColorPixel [][] oldImage = images.get(name);
    ColorPixel [][] newImage = new ColorPixel[oldImage.length][oldImage[0].length];

    newImage = arraySetUp(newImage);

    for (int i = 0; i < newImage.length; i++) {
      for (int j = 0; j < newImage[i].length; j++) {

        ColorPixel p = oldImage[i][j];

        if (input.equals("Red")) {
          newImage[i][j].setR(p.getR());
          newImage[i][j].setG(p.getR());
          newImage[i][j].setB(p.getR());
        }
        else if (input.equals("Green")) {
          newImage[i][j].setR(p.getG());
          newImage[i][j].setG(p.getG());
          newImage[i][j].setB(p.getG());
        }
        else if (input.equals("Blue")) {
          newImage[i][j].setR(p.getB());
          newImage[i][j].setG(p.getB());
          newImage[i][j].setB(p.getB());
        }
        else if (input.equals("Value")) {
          int max = Math.max(p.getR(), p.getG());
          max = Math.max(max, p.getB());
          newImage[i][j].setR(max);
          newImage[i][j].setG(max);
          newImage[i][j].setB(max);
        }
        else if (input.equals("Intensity")) {
          int average = (int) Math.round((p.getR() + p.getG() + p.getB()) / 3.0);
          newImage[i][j].setR(average);
          newImage[i][j].setG(average);
          newImage[i][j].setB(average);
        }
        else {
          int luma = (int) Math.round(0.2126 * p.getR() + 0.7152 * p.getG()
                  + 0.0722 * p.getB());
          newImage[i][j].setR(luma);
          newImage[i][j].setG(luma);
          newImage[i][j].setB(luma);
        }
      }
    }
    // Instead of returning
    images.put(dest, newImage);
  }

  private ColorPixel [][] arraySetUp(ColorPixel [][] newImage) {
    for (int i = 0; i < newImage.length; i++) {
      for (int j = 0; j < newImage[i].length; j++) {
        newImage[i][j] = new ColorPixel(0,0,0);
      }
    }
    return newImage;
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
      ColorPixel [][] newImage = new ColorPixel[oldImage.length][oldImage[0].length];

      newImage = arraySetUp(newImage);

      for (int i = 0; i < newImage.length; i++) {
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
      ColorPixel [][] newImage = new ColorPixel[oldImage.length][oldImage[0].length];

      newImage = arraySetUp(newImage);

      for (int i = 0; i < newImage.length; i++) {
        for (int j = 0; j < newImage[i].length; j++) {
          newImage[i][j] = oldImage[oldImage.length - 1 - i][j];
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
      ColorPixel [][] newImage = new ColorPixel[oldImage.length][oldImage[0].length];

      newImage = arraySetUp(newImage);

      for (int i = 0; i < newImage.length; i++) {
        for (int j = 0; j < newImage[i].length; j++) {
          ColorPixel pixel = oldImage[i][j];
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


  @Override
  public void resize(int newWidth, int newHeight, String name, String dest) throws IllegalArgumentException {
    if (images.containsKey(name)) {
      // Get the old image
      ColorPixel[][] oldImage = images.get(name);

      // Check if the new height and width are smaller than before, and that they're not zero
      if (newHeight > oldImage.length || newHeight < 1) {
        throw new IllegalArgumentException("Height was invalid");
      }
      if (newWidth > oldImage[0].length || newWidth < 1) {
        throw new IllegalArgumentException("Width was invalid");
      }

      // Create new image with new width and height
      ColorPixel [][] newImage = new ColorPixel[newHeight][newWidth];

      // Set pixels bs
      double heightRatio = (double) oldImage.length / newHeight; // Ex: 1.6
      double widthRatio = (double) oldImage[0].length / newWidth; // Ex: 1.3

      // Loop through the new image
      for (int i = 0; i < newImage.length; i++) {
        for (int j = 0; j < newImage[i].length; j++) {
          // i is height
          // j is width
          double hDouble = i * heightRatio; // 10.3, correspond to the old image
          double wDouble = j * widthRatio; // 12.4

          // Get four pixels
          int h1 = (int) Math.floor(hDouble);
          int h2 = h1 + 1;
          int w1 = (int) Math.floor(wDouble);
          int w2 = w1 + 1;

          // The 4 pixels
          ColorPixel a = oldImage[h1][w1];
          ColorPixel b = oldImage[h1][w2];
          ColorPixel c = oldImage[h2][w1];
          ColorPixel d = oldImage[h2][w2];

          // Ending variables
          int rVal = 0;
          int gVal = 0;
          int bVal = 0;

          for (int k = 0; k < 3; k++) {
            // Get the Colors
            int aR = a.getR();
            int bR = b.getR();
            int cR = c.getR();
            int dR = d.getR();

            if (k == 1) { // G
              aR = a.getG();
              bR = b.getG();
              cR = c.getG();
              dR = d.getG();
            }
            else if (k == 2) { // B
              aR = a.getB();
              bR = b.getB();
              cR = c.getB();
              dR = d.getB();
            }

            // The formula
            double m = aR * (wDouble - w1) + bR * (w2 - wDouble);
            double n = cR * (wDouble - w1) + dR * (w2 - wDouble);
            double val = m * (hDouble - h1) + n * (h2 - hDouble);

            // Round the final value
            if (k == 0) {
              rVal = (int) Math.round(val);
            }
            else if (k == 1) {
              gVal = (int) Math.round(val);
            }
            else {
              bVal = (int) Math.round(val);
            }
          }

          // Last step
          ColorPixel pixel = new ColorPixel(rVal, gVal, bVal);
          newImage[i][j] = pixel;
        }
      }
      // Put the image in the map
      images.put(dest, newImage);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }

}
