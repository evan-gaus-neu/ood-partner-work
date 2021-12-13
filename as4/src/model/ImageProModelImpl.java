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

    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }
  }



}
