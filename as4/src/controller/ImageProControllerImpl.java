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
    renderMessageHelper();

    // Loop while not quit
    while (!quit) {

      // Print message
      renderMessageHelper("Type instruction: \n");

      // Get the next input (QQ maybe check if there is one first)
      String userInput = scan.next();

      // Switch for userInput
      switch (userInput) {
        case "load": // load image
          try {
            model.loadImage(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Image loaded!\n");
          break;
        case "save": // save image
          try {
            model.saveImage(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Image saved!\n");
          break;
        case "red-component":
          try {
            model.redComponent(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Red component created!\n");
          break;
        case "green-component":
          try {
            model.greenComponent(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Green component created!\n");
          break;
        case "blue-component":
          try {
            model.blueComponent(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Blue component created!\n");
          break;
        case "value-component":
          try {
            model.valueComponent(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Value component created!\n");
          break;
        case "intensity-component":
          try {
            model.intensityComponent(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Intensity component created!\n");
          break;
        case "luma-component":
          try {
            model.lumaComponent(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        renderMessageHelper("Luma component created!\n");
        case "horizontal-flip":
          try {
            model.horFlip(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Horizontal flip created!\n");
          break;
        case "vertical-flip":
          try {
            model.vertFlip(scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Vertical flip created!\n");
          break;
        case "brighten":
          try {
            model.brighten(scan.nextInt(), scan.next(), scan.next());
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          renderMessageHelper("Brighter / Darker image created!\n");
          break;
        case "q":
        case "quit":
          quit = true;
          renderMessageHelper("Program quit. Thanks!");
          break;
        default: // Unrecognized command
          renderMessageHelper("Unrecognized Command: " + userInput + "\n");
      }




    }


  }

  private void renderMessageHelper(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

}
