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
    Double[][] kernel = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
    kernelHelper(name, dest, kernel);
  }

  @Override
  public void sharpen(String name, String dest) throws IllegalArgumentException {
    Double[][] kernel = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
    kernelHelper(name, dest, kernel);
  }

  private void kernelHelper(String name, String dest, Double[][] kernel) { // QQ Not sure what we need as inputs but that we can call. Probably the image and the kernel.
    ColorPixel[][] oldImage = images.get(name);
    ColorPixel [][] newImage = new ColorPixel[oldImage.length][oldImage[0].length];

    newImage = arraySetUp(newImage);

    String [] arr = {"Red", "Green", "Blue"};

    for (int i = 0; i < oldImage.length; i++) {
      for (int j = 0; j < oldImage[i].length; j++) {
        for (String str : arr) {
          int val = computeVal(oldImage, kernel, i, j, str);
          if (str.equals("Red")) {
            newImage[i][j].setR(val);
          }
          else if (str.equals("Green")) {
            newImage[i][j].setG(val);
          }
          else {
            newImage[i][j].setB(val);
          }
        }
      }
    }
    images.put(dest, newImage);
  }

  private int computeVal(ColorPixel [][] givenImage, Double [][] kernel, int x, int y, String str) {
    double tempVal = 0.0;
    int xCor = 0;
    int yCor = 0;
    for (int i = x - kernel.length/2; i < kernel.length + (x - kernel.length/2); i++) {
      for (int j = y - kernel.length/2; j < kernel.length + (y - kernel.length/2); j++) {
        if (!(i < 0 || i >= givenImage.length || j < 0 || j >= givenImage.length)) {
          if (str.equals("Red")) {
            tempVal += kernel[xCor][yCor] * givenImage[i][j].getR();
          }
          else if (str.equals("Green")) {
            tempVal += kernel[xCor][yCor] * givenImage[i][j].getG();
          }
          else {
            tempVal += kernel[xCor][yCor] * givenImage[i][j].getB();
          }
        }
        yCor++;
      }
      yCor = 0;
      xCor++;
    }
    int finalVal = (int) Math.round(tempVal);
    return finalVal;
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
  public void greyscale(String name, String dest) throws IllegalArgumentException {
    Double[][] kernel = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    filterHelper(name, dest, kernel);
  }

  @Override
  public void sepia(String name, String dest) throws IllegalArgumentException {
    Double[][] kernel = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
    filterHelper(name, dest, kernel);
  }

  private void filterHelper(String name, String dest, Double[][] kernel) { // QQ Same here, not sure about params
    ColorPixel[][] oldImage = images.get(name);
    ColorPixel [][] newImage = new ColorPixel[oldImage.length][oldImage[0].length];

    newImage = arraySetUp(newImage);

    String [] arr = {"Red", "Green", "Blue"};

    for (int i = 0; i < oldImage.length; i++) {
      for (int j = 0; j < oldImage[i].length; j++) {
        for (String str : arr) {
          if (str.equals("Red")) {
            Double[] singleKernel = kernel[0];
            int val = computeFilterVal(oldImage, singleKernel, i, j);
            newImage[i][j].setR(val);
          }
          else if (str.equals("Green")) {
            Double[] singleKernel = kernel[1];
            int val = computeFilterVal(oldImage, singleKernel, i, j);
            newImage[i][j].setG(val);
          }
          else {
            Double[] singleKernel = kernel[2];
            int val = computeFilterVal(oldImage, singleKernel, i, j);
            newImage[i][j].setB(val);
          }
        }
      }
    }
    images.put(dest, newImage);
  }

  private int computeFilterVal(ColorPixel [][] givenImage, Double [] kernel, int x, int y) {
    double tempVal = 0.0;
    for (int i = 0; i < kernel.length; i++) {
      if (i == 0) {
        tempVal += kernel[i] * givenImage[x][y].getR();
      }
      else if (i == 1) {
        tempVal += kernel[i] * givenImage[x][y].getG();
      }
      else {
        tempVal += kernel[i] * givenImage[x][y].getB();
      }
    }

    int finalVal = (int) Math.round(tempVal);
    return finalVal;
  }

}
