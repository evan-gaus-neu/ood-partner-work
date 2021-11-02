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

  private ImageProModel colorComponent(String input, String name, String dest) {

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
  public void redComponent(String name, String dest) throws IllegalStateException {

    if (images.containsKey(name)) {
      colorComponent("Red", name, dest);
    }
    else {
      throw new IllegalArgumentException("Given name didn't correspond to an image");
    }

  }

  @Override
  public void greenComponent(String name, String dest) {
    colorComponent("Green", name, dest);
  }

  @Override
  public void blueComponent(String name, String dest) {
    colorComponent("Blue", name, dest);
  }

  @Override
  public void valueComponent(String name, String dest) {
    colorComponent("Value", name, dest);
  }

  @Override
  public void intensityComponent(String name, String dest) {
    colorComponent("Intensity", name, dest);
  }

  @Override
  public void lumaComponent(String name, String dest) {
    colorComponent("Luma", name, dest);
  }

  @Override
  public void horFlip(String name, String dest) {
    ImageProModelImpl original = this;
    ImageProModelImpl newModel = this;
    for(int i = 0; i < newModel.image.length; i++) {
      for (int j = 0; j < newModel.image[i].length; j++) {
        newModel.image[i][j] = original.image[i][(newModel.image.length) - 1 - j];
      }
    }
    return newModel;
  }

  @Override
  public void vertFlip(String name, String dest) {
    ImageProModelImpl original = this;
    ImageProModelImpl newModel = this;
    for(int i = 0; i < newModel.image.length; i++) {
      for (int j = 0; j < newModel.image[i].length; j++) {
        newModel.image[i][j] = original.image[(newModel.image.length) - 1 - i][j];
      }
    }
    return newModel;
  }

  @Override
  public void brighten(int increment, String name, String dest) {
    ImageProModelImpl newModel = this;
    for(int i = 0; i < newModel.image.length; i++) {
      for (int j = 0; j < newModel.image[i].length; j++) {
        ColorPixel pixel = new ColorPixel(newModel.image[i][j].getR(),
                newModel.image[i][j].getG(), newModel.image[i][j].getB());
        newModel.image[i][j].setR(pixel.getR() + increment);
        newModel.image[i][j].setG(pixel.getG() + increment);
        newModel.image[i][j].setB(pixel.getB() + increment);
      }
    }
    return newModel;
  }
}
