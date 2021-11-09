package model;

import data.ColorPixel;

public class IPModelV2 extends ImageProModelImpl implements IPMV2 {

  /**
   * Constructor that just calls the super, setting up the map of images.
   */
  public IPModelV2() {
    super();
  }

  // New added methods

  @Override
  public void blur(String name, String dest) throws IllegalArgumentException {
    kernelHelper();
  }

  @Override
  public void sharpen(String name, String dest) throws IllegalArgumentException {
    kernelHelper();
  }

  private void kernelHelper(String name, String dest, Double[][] kernel) { // QQ Not sure what we need as inputs but that we can call. Probably the image and the kernel.

  }

  @Override
  public void greyscale(String name, String dest) throws IllegalArgumentException {
    filterHelper();
  }

  @Override
  public void sepia(String name, String dest) throws IllegalArgumentException {
    filterHelper();
  }

  private void filterHelper(String name, String dest, Double[][] kernel) { // QQ Same here, not sure about params
    // This one the kernel has to be 3x3, and it's used slightly differently

  }

}
