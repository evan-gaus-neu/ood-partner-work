package model;

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

  @Override
  public ImageProModel redComponent() {
    return null;
  }

  @Override
  public ImageProModel greenComponent() {
    return null;
  }

  @Override
  public ImageProModel blueComponent() {
    return null;
  }

  @Override
  public ImageProModel intensityComponent() {
    return null;
  }

  @Override
  public ImageProModel lumaComponent() {
    return null;
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
