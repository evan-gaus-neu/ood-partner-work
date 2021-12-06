package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.BlurModel;
import model.BrightenDarkenModel;
import model.HorizontalModel;
import model.ImageProcessingModel;
import model.ImageProcessingModel.RedGreenBlue;
import model.IntensityModel;
import model.LumaModel;
import model.RGBModel;
import model.SepiaModel;
import model.SharpenModel;
import model.ValueModel;
import model.VerticalModel;
import model.util.ImageUtil;
import view.ImageProcessingView;

/**
 * Implementation of the controller that allows text inputs through commands.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private Map<String, ImageProcessingModel> imageProcessingModel;
  private ImageProcessingView imageProcessingView;
  private Readable readable;

  /**
   * Initializes the class which contains a hashmap of loaded images, the view, and text input.
   * @param imageProcessingView The view class which helps output the information.
   * @param readable The class which allows users to input into the controller.
   * @throws IllegalArgumentException if either the view or readable is null.
   */
  public ImageProcessingControllerImpl(ImageProcessingView imageProcessingView,
          Readable readable) {
    if (imageProcessingView == null || readable == null) {
      throw new IllegalArgumentException();
    }
    this.imageProcessingModel = new HashMap<>();
    this.imageProcessingView = imageProcessingView;
    this.readable = readable;
  }

  /**
   * Runs the image processing start for the controller.
   * This includes looking for either quit command or image modifying commands.
   * At the same time, the program sends prompts for either successful or unsuccessful commands.
   * @throws IOException If the message prompts cannot be transmitted.
   */
  @Override
  public void runImageProcessing() throws IOException {
    Scanner sn = new Scanner(this.readable);

    boolean quit = false;
    this.imageProcessingView.renderMessage("Start Program Below\n");
    while (!quit) {
      if (sn.hasNextLine()) {

        String command = sn.nextLine();
        String[] args = command.split(" ");

        if (args.length == 1 && args[0].equalsIgnoreCase("q")) {
          this.imageProcessingView.renderMessage("Quit\n");
          quit = true;

        } else if (args.length == 3 && validLoadSave(args[0])) {

          if (args[0].equals("load")) {
            loadImage(args);
          } else if (args[0].equals("save")) {
            saveImage(args);
          }

        } else if (args.length == 3 && validThreeStart(args[0])) {

          if (imageProcessingModel.containsKey(args[1])) {
            ImageProcessingModel orig = imageProcessingModel.get(args[1]);
            int[][][] dat = copy3dArray(orig.getData());
            switch (args[0]) {
              case "red-component":
                generalTransform(
                        new RGBModel(RedGreenBlue.R, dat, orig.getMaxValue()), args[2]);
                break;
              case "green-component":
                generalTransform(
                        new RGBModel(RedGreenBlue.G, dat, orig.getMaxValue()), args[2]);
                break;
              case "blue-component":
                generalTransform(
                        new RGBModel(RedGreenBlue.B, dat, orig.getMaxValue()), args[2]);
                break;
              case "value-component":
                generalTransform(
                        new ValueModel(dat, orig.getMaxValue()), args[2]);
                break;
              case "luma-component":
                generalTransform(
                        new LumaModel(dat, orig.getMaxValue()), args[2]);
                break;
              case "intensity-component":
                generalTransform(
                        new IntensityModel(dat, orig.getMaxValue()), args[2]);
                break;
              case "horizontal-flip":
                generalTransform(
                        new HorizontalModel(dat, orig.getMaxValue()), args[2]);
                break;
              case "vertical-flip":
                generalTransform(
                        new VerticalModel(dat, orig.getMaxValue()), args[2]);
                break;
              case "sepia-tone":
                generalTransform(
                        new SepiaModel(dat, orig.getMaxValue()), args[2]);
                break;
              case "blur":
                generalTransform(
                        new BlurModel(dat, orig.getMaxValue()), args[2]
                );
                break;
              case "sharpen":
                generalTransform(
                        new SharpenModel(dat, orig.getMaxValue()), args[2]);
                break;
              default:
                break;
            }
          } else {
            this.imageProcessingView.renderMessage("Image source not found.\n");
          }

        } else if (args.length == 4 && args[0].equals("brighten")) {

          brightenImage(args);

        } else {

          this.imageProcessingView.renderMessage("Command not found! Try again.\n");

        }
      }
    }
  }

  /**
   * This runs the load image using the utils package.
   * The image is stored as a brighten model with a factor of 0 so that there is no difference.
   * @param args is a String array where the first argument is "load", the second is the path,
   *             and the third is the new name for the image that it will be referenced as.
   * @throws IOException If the message prompts cannot be transmitted.
   */
  private void loadImage(String[] args) throws IOException {
    String[] ext = args[1].split("\\.");
    int[][][] d;
    if (ext[ext.length - 1].equals("ppm")) {
      d = ImageUtil.readPPM(args[1]);
    } else {
      d = ImageUtil.readNonPPM(args[1]);
    }
    if (d != null) {
      imageProcessingModel.put(args[2], new BrightenDarkenModel(d, 0));
      this.imageProcessingView.renderMessage("Image loaded.\n");
    } else {
      this.imageProcessingView.renderMessage("File cannot be found or not in correct format.\n");
    }
  }

  /**
   * This runs the save image which saves the image in the specified path.
   * @param args is a String array where the first argument is "save", the second is the path,
   *             and the third is the name of the image to save.
   * @throws IOException If the message prompts cannot be transmitted.
   */
  private void saveImage(String[] args) throws IOException {
    if (imageProcessingModel.containsKey(args[2])) {
      try {
        String[] ext = args[1].split("\\.");
        if (ext[ext.length - 1].equals("ppm")) {
          ImageUtil.savePPM(imageProcessingModel.get(args[2]), args[1]);
          this.imageProcessingView.renderMessage("Image saved.\n");
        } else if (validExt(ext[ext.length - 1])) {
          ImageUtil.saveNonPPM(imageProcessingModel.get(args[2]), args[1], ext[ext.length - 1]);
          this.imageProcessingView.renderMessage("Image saved.\n");
        } else {
          this.imageProcessingView.renderMessage("Image file format not supported.\n");
        }
      } catch (FileNotFoundException f) {
        this.imageProcessingView.renderMessage("File name invalid.\n");
      }
    } else {
      this.imageProcessingView.renderMessage("Image source not found.\n");
    }
  }

  /**
   * Runs the transform function on the given model and puts the transformed model as the name.
   * @param m is the model that the user wants to transform.
   * @param newName is the string that the user wants to reference the transformed image as.
   * @throws IOException If the message prompts cannot be transmitted.
   */
  private void generalTransform(ImageProcessingModel m, String newName) throws IOException {
    m.transform();
    imageProcessingModel.put(newName, m);
    this.imageProcessingView.renderMessage("Image transformed.\n");
  }

  /**
   * Brightens the image given the arguments of the command.
   * It also checks that the brighten factor can be converted into an integer.
   * @param args A string array of the command which guarantees the 1st argument is "brighten",
   *             the second argument is the number of the brighten
   *             the third argument is the image name to brighten
   *             the fourth argument is the image name to save the transformed version
   * @throws IOException If the message prompts cannot be transmitted.
   */
  private void brightenImage(String[] args) throws IOException {
    try {
      int factor = Integer.parseInt(args[1]);
      if (imageProcessingModel.containsKey(args[2])) {
        ImageProcessingModel orig = imageProcessingModel.get(args[2]);
        int[][][] dat = copy3dArray(orig.getData());
        ImageProcessingModel brightened =
                new BrightenDarkenModel(dat, factor, orig.getMaxValue());
        brightened.transform();
        imageProcessingModel.put(args[3], brightened);
        this.imageProcessingView.renderMessage("Image transformed.\n");
      } else {
        this.imageProcessingView.renderMessage("Image source not found.\n");
      }
    } catch (NumberFormatException n) {
      this.imageProcessingView.renderMessage("Factor is not a valid integer.\n");
    }
  }

  /**
   * Checks if the three string command given starts with load or save.
   * @param s The first command in a three word command.
   * @return a boolean representing if the first command is valid.
   */
  private boolean validLoadSave(String s) {
    return s.equals("load") || s.equals("save");
  }

  /**
   * Checks if the three string command given starts with a valid key word for the command.
   * @param s The first command in a three word command.
   * @return a boolean representing if the first command is a valid command.
   */
  private boolean validThreeStart(String s) {
    return s.equals("red-component")
            || s.equals("green-component") || s.equals("blue-component")
            || s.equals("value-component") || s.equals("luma-component")
            || s.equals("intensity-component") || s.equals("horizontal-flip")
            || s.equals("vertical-flip") || s.equals("sepia-tone")
            || s.equals("blur") || s.equals("sharpen");
  }

  /**
   * Copies the contents of the array into another array so that data is not passed by reference.
   * @param orig original 3d array of the data
   * @return the array which contains the copied version of the orig array.
   */
  private int[][][] copy3dArray(int[][][] orig) {
    int[][][] dat =
          new int[orig.length][orig[0].length][orig[0][0].length];
    for (int i = 0; i < orig.length; i++) {
      for (int j = 0; j < orig[i].length; j++) {
        for (int k = 0; k < orig[i][j].length; k++) {
          dat[i][j][k] = orig[i][j][k];
        }
      }
    }
    return dat;
  }

  /**
   * A function that makes sure the file extension is a valid image type.
   * @param s string representing the file extension
   * @return a boolean representing if s is a valid file image extension
   */
  private boolean validExt(String s) {
    return s.equals("png") || s.equals("jpg") || s.equals("bmp") || s.equals("jpeg");
  }


}
