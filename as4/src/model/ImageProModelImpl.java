package model;

import java.util.HashMap;
import java.util.Map;

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
  public void loadImage(String path, String name) {

  }

  @Override
  public void saveImage(String path, String name) {

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
