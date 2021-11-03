package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.ImageProModel;
import view.ImageProView;

/**
 * The class that acts as the Controller for this design
 * and is based on the interface ImageProController.
 */
public class ImageProControllerImpl implements ImageProController {

  // Data
  private ImageProModel model;
  private ImageProView view;
  private Readable in;

  // Constructor
  /**
   * This is the constructor for the ImageProControllerImpl that is used to make an instance.
   * @param model an ImageProModel that is used for the model field.
   * @param view an ImageProView that is used for the view field.
   * @param in a Readable that is used for getting an input.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
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
    renderMessageHelper("Welcome to the Image Processor!\n");

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
            renderMessageHelper("Image loaded!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "save": // save image
          try {
            model.saveImage(scan.next(), scan.next());
            renderMessageHelper("Image saved!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "red-component":
          try {
            model.redComponent(scan.next(), scan.next());
            renderMessageHelper("Red component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "green-component":
          try {
            model.greenComponent(scan.next(), scan.next());
            renderMessageHelper("Green component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "blue-component":
          try {
            model.blueComponent(scan.next(), scan.next());
            renderMessageHelper("Blue component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "value-component":
          try {
            model.valueComponent(scan.next(), scan.next());
            renderMessageHelper("Value component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "intensity-component":
          try {
            model.intensityComponent(scan.next(), scan.next());
            renderMessageHelper("Intensity component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "luma-component":
          try {
            model.lumaComponent(scan.next(), scan.next());
            renderMessageHelper("Luma component created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "horizontal-flip":
          try {
            model.horFlip(scan.next(), scan.next());
            renderMessageHelper("Horizontal flip created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "vertical-flip":
          try {
            model.vertFlip(scan.next(), scan.next());
            renderMessageHelper("Vertical flip created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          break;
        case "brighten":
          try {
            model.brighten(scan.nextInt(), scan.next(), scan.next());
            renderMessageHelper("Brighter / Darker image created!\n");
          }
          catch (IllegalArgumentException e) {
            renderMessageHelper("Invalid: " + e.getMessage() + "\n");
          }
          catch (InputMismatchException e) {
            renderMessageHelper("Invalid input for increment!\n");
          }
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
