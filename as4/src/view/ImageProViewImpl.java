package view;

import java.io.IOException;

import model.ImageProModel;

public class ImageProViewImpl implements ImageProView {

  // Data
  private ImageProModel model;
  private Appendable out;

  public ImageProViewImpl(ImageProModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model was null");
    }

    this.model = model;
    this.out = System.out;

  }

  public ImageProViewImpl(ImageProModel model, Appendable out) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model was null");
    }
    if (out == null) {
      throw new IllegalArgumentException("Appendable was null");
    }

    this.model = model;
    this.out = out;

  }

  @Override
  public void renderMessage(String message) throws IOException {
    out.append(message);
  }

  // Make a to string that prints out every pixel


}
