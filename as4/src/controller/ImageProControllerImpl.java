package controller;

import java.io.IOException;
import java.util.Scanner;

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

    // Important data
    Scanner scan = new Scanner(in);
    boolean quit = false;
    // Other stored things

    // Do a welcome message?

    // Loop while not quit

    while (!quit) {

      // Print message
      try {
        view.renderMessage("Type instruction: \n");
      }
      catch (IOException e) {
        throw new IllegalStateException(e.getMessage());
      }

      // Get the next input (QQ maybe check if there is one first)
      String userInput = scan.next();

      // Switch for userInput
      switch (userInput) {
        case "load": // load image
          // Do stuff here
          break;
        case "save": // save image
          // Do stuff here
          break;
        case "red-component": // save image
          // Do stuff here
          break;
        case "green-component": // save image
          // Do stuff here
          break;
        case "blue-component": // save image
          // Do stuff here
          break;
          // value, intensity, luma
        case "value-component": // save image
          // Do stuff here
          break;
        case "intensity-component": // save image
          // Do stuff here
          break;
        case "luma-component": // save image
          // Do stuff here
          break;
        case "horizontal-flip": // save image
          // Do stuff here
          break;
        case "vertical-flip": // save image
          // Do stuff here
          break;
        case "brighten": // save image
          // Do stuff here
          break;
        case "q":
        case "quit":
          quit = true;
          break;
        default: // Unrecognized command
          try {
            view.renderMessage("Unrecognized Command: " + userInput + "\n");
          }
          catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
          }


      }




    }


  }

}
