package model;

import java.awt.*;

import data.ColorPixel;

public class ImageProModelImpl implements ImageProModel {

  // Data
  // This represents the image, it's a matrix of color
  private ColorPixel[][] image;

  public ImageProModelImpl() {
    // I don't think we do anything, but possible load an image on instantiation
  }

  @Override
  public void loadImage(String path) {

  }

  @Override
  public void saveImage(String path) {

  }

  private ImageProModel colorComponent(String input) {
    ImageProModelImpl newModel = this;
    for(int i = 0; i < newModel.image.length; i++) {
      for(int j = 0; j < newModel.image[i].length; j++) {
        ColorPixel pixel = new ColorPixel(newModel.image[i][j].getR(),
                newModel.image[i][j].getG(), newModel.image[i][j].getB());
        if (input.equals("Red")) {
          newModel.image[i][j] = new ColorPixel(pixel.getR(), pixel.getR(), pixel.getR());
        }
        else if (input.equals("Green")) {
          newModel.image[i][j] = new ColorPixel(pixel.getG(), pixel.getG(), pixel.getG());
        }
        else if (input.equals("Blue")) {
          newModel.image[i][j] = new ColorPixel(pixel.getB(), pixel.getB(), pixel.getB());
        }
        else if (input.equals("Value")) {
          int max = Math.max(pixel.getB(), Math.max(pixel.getR(), pixel.getG()));
          newModel.image[i][j] = new ColorPixel(max, max, max);
        }
        else if (input.equals("Intensity")) {
          int average = (int) Math.round((pixel.getR() + pixel.getG() + pixel.getB())/3.0);
          newModel.image[i][j] = new ColorPixel(average, average, average);
        }
        else {
          int luma = (int) Math.round(0.2126 * pixel.getR() + 0.7152 * pixel.getG()
                  + 0.0722 * pixel.getB());
          newModel.image[i][j] = new ColorPixel(luma, luma, luma);
        }
      }
    }
    return newModel;
  }

  @Override
  public ImageProModel redComponent() {
    return colorComponent("Red");
  }

  @Override
  public ImageProModel greenComponent() {
    return colorComponent("Green");
  }

  @Override
  public ImageProModel blueComponent() {
    return colorComponent("Blue");
  }

  @Override
  public ImageProModel valueComponent() {
    return colorComponent("Value");
  }

  @Override
  public ImageProModel intensityComponent() {
    return colorComponent("Intensity");
  }

  @Override
  public ImageProModel lumaComponent() {
    return colorComponent("Luma");
  }

  @Override
  public ImageProModel horFlip() {
    return null;
  }

  @Override
  public ImageProModel vertFlip() {
    return null;
  }

  @Override
  public ImageProModel brighten(int increment) {
    return null;
  }
}
