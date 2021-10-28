package controller;

import model.ImageProModel;
import view.ImageProView;

public class ImageProControllerImpl implements ImageProController {

  // Data
  private ImageProModel model;
  private ImageProView view;
  private Readable in;

  // Constructor
  public ImageProControllerImpl(ImageProModel model, ImageProView view, Readable in)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model was null");
    }
    if (view == null) {
      throw new IllegalArgumentException("View was null");
    }
    if (in == null) {
      throw new IllegalArgumentException("Readable was null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }


  @Override
  public void run() throws IllegalStateException {

  }

}
