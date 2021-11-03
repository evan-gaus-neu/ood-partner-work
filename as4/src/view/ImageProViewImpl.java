package view;

import java.io.IOException;

import model.ImageProModel;

/**
 * The class that acts as the View for this design
 * and is based on the interface ImageProView.
 */
public class ImageProViewImpl implements ImageProView {

  // Data
  private ImageProModel model;
  private Appendable out;

  /**
   * The default constructor that instantiates model to the given parameter
   * and sets the field out to "System.out".
   * @param model ImageProModel that contains all images
   * @throws IllegalArgumentException when the model is null
   */
  public ImageProViewImpl(ImageProModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model was null");
    }

    this.model = model;
    this.out = System.out;

  }

  /**
   * The secondary constructor that instantiates model to the first parameter
   * and also the field out to the second parameter.
   * @param model ImageProModel that contains all images.
   * @param out Appendable that is used to display output.
   * @throws IllegalArgumentException when the model is null
   */
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
