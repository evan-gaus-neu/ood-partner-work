import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageProController;
import controller.ImageProControllerImpl;
import data.ColorPixel;
import model.IPMV2;
import model.IPModelV2;
import view.ImageProView;
import view.ImageProViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The class that contains all of the testing for the Model, View, and Controller.
 */
public class ImageProModelImplTest {

  @Test
  public void sampleTest() {

    // This string is what input we are giving it
    Reader in = new StringReader("q");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertEquals("Welcome to the Image Processor!\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());

  }


  // TEST COLOR PIXEL FUNCTIONALITY =====

  @Test(expected = IllegalArgumentException.class)
  public void cpInvalidRed() {
    ColorPixel cp = new ColorPixel(-2, 20,20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cpInvalidGreen() {
    ColorPixel cp = new ColorPixel(0, 300,20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cpInvalidBlue() {
    ColorPixel cp = new ColorPixel(20, 20,4000);
  }

  @Test
  public void cpGetTest() {
    ColorPixel cp = new ColorPixel(12, 34,56);
    assertEquals(12, cp.getR());
    assertEquals(34, cp.getG());
    assertEquals(56, cp.getB());
  }

  @Test
  public void cpSetLowTest() {
    ColorPixel cp = new ColorPixel(12, 34,56);
    cp.setR(-12);
    cp.setG(-34);
    cp.setB(-56);
    assertEquals(0, cp.getR());
    assertEquals(0, cp.getG());
    assertEquals(0, cp.getB());
  }

  @Test
  public void cpSetMedTest() {
    ColorPixel cp = new ColorPixel(0, 0,0);
    cp.setR(12);
    cp.setG(34);
    cp.setB(56);
    assertEquals(12, cp.getR());
    assertEquals(34, cp.getG());
    assertEquals(56, cp.getB());
  }

  @Test
  public void cpSetHighTest() {
    ColorPixel cp = new ColorPixel(12, 34,56);
    cp.setR(256);
    cp.setG(127941);
    cp.setB(300);
    assertEquals(255, cp.getR());
    assertEquals(255, cp.getG());
    assertEquals(255, cp.getB());
  }


  // TEST NULL INPUTS FOR CONSTRUCTORS =====

  @Test(expected = IllegalArgumentException.class)
  public void viewNullModel() {
    // This string is what input we are giving it
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    ImageProView view = new ImageProViewImpl(null, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void viewNullOut() {
    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void contNullModel() {
    // This string is what input we are giving it
    Reader in = new StringReader("q");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(null, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void contNullView() {
    // This string is what input we are giving it
    Reader in = new StringReader("q");

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProController controller = new ImageProControllerImpl(model, null, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void contNullIn() {
    // This string is what input we are giving it
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, null);
  }


  // TEST BAD CONTROLLER INPUTS (AND THE VIEW DISPLAYING THAT THESE ARE BAD) =====

  @Test
  public void contBadInput() {
    // This string is what input we are giving it
    Reader in = new StringReader("nothing bad command    load nothing lame     " +
            "load nothing.txt dne     load res/nothing.jpg bad       " +
            "load brighten not-an-int something something     quit ");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertEquals("Welcome to the Image Processor!\n" +
            "Type instruction: \n" +
            "Unrecognized Command: nothing\n" +
            "Type instruction: \n" +
            "Unrecognized Command: bad\n" +
            "Type instruction: \n" +
            "Unrecognized Command: command\n" +
            "Type instruction: \n" +
            "Invalid: Invalid path, no file extension found\n" +
            "Type instruction: \n" +
            "Invalid: Attempt to read the file at given path failed\n" +
            "Type instruction: \n" +
            "Invalid: Attempt to read the file at given path failed\n" +
            "Type instruction: \n" +
            "Invalid: Invalid path, no file extension found\n" +
            "Type instruction: \n" +
            "Unrecognized Command: something\n" +
            "Type instruction: \n" +
            "Unrecognized Command: something\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());
  }


  // TEST THE MODEL WITH BAD INPUT =====

  @Test
  public void modelBadInput() {
    // This string is what input we are giving it
    Reader in = new StringReader("load res/does-not-exist.ppm dne   " +
            "red-component dne red   " +
            "green-component dne green    " +
            "blue-component dne blue   " +
            "value-component dne value   " +
            "intensity-component dne intensity   " +
            "luma-component dne luma   " +
            "vertical-flip dne vflip   " +
            "horizontal-flip dne hflip    " +
            "brighten 100 dne bright    " +
            "brighten -100 dne dark    " +
            "brighten not-int    " +
            "blur dne blur   " +
            "sharpen dne sharp   " +
            "greyscale dne grey   " +
            "sepia dne sepia   " +
            "save res/file.ppm dne   " +
            "load res/sun.jpg sun    " +
            "resize 20 20 dne resized      " +
            "resize -20 20 dne resized    " +
            "resize 20 -20 dne resized     " +
            "resize dne dne dne resized    " +
            "resize 400 20 sun resized    " +
            "resize 20 400 sun resized    " +
            "load res/sun-mask.jpg mask    " +
            "mask dne sun mask mask-sun 0    " +
            "mask greyscale dne mask mask-sun 0    " +
            "mask greyscale sun dne mask-sun 0    " +
            "mask brighten sun mask mask-sun dne    " +
            "quit");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertEquals("Welcome to the Image Processor!\n" +
            "Type instruction: \n" +
            "Invalid: File: res/does-not-exist.ppm was not found\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid input for increment!\n" +
            "Type instruction: \n" +
            "Unrecognized Command: not-int\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Image loaded!\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid input for size!\n" +
            "Type instruction: \n" +
            "Unrecognized Command: dne\n" +
            "Type instruction: \n" +
            "Unrecognized Command: dne\n" +
            "Type instruction: \n" +
            "Unrecognized Command: dne\n" +
            "Type instruction: \n" +
            "Unrecognized Command: resized\n" +
            "Type instruction: \n" +
            "Invalid: Width was invalid\n" +
            "Type instruction: \n" +
            "Invalid: Height was invalid\n" +
            "Type instruction: \n" +
            "Image loaded!\n" +
            "Type instruction: \n" +
            "Invalid: Filter command not recognized\n" +
            "Type instruction: \n" +
            "Invalid: Given name or mask didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid: Given name or mask didn't correspond to an image\n" +
            "Type instruction: \n" +
            "Invalid input for increment!\n" +
            "Type instruction: \n" +
            "Unrecognized Command: dne\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());
  }


  // TEST ALL OF THE CORRECT FUNCTIONALITY WITH IMAGES =====

  @Test
  public void checkAll() {

    // Set up the check images
    setUpCorrectImages();

    // This string is what input we are giving it
    Reader in = new StringReader("load res/sun.ppm sun   " +
            "red-component sun red   " +
            "green-component sun green    " +
            "blue-component sun blue   " +
            "value-component sun value   " +
            "intensity-component sun intensity   " +
            "luma-component sun luma   " +
            "vertical-flip sun vflip   " +
            "horizontal-flip sun hflip    " +
            "brighten 100 sun bright    " +
            "brighten -100 sun dark   " +
            "blur sun blur   " +
            "sharpen sun sharp   " +
            "greyscale sun grey   " +
            "sepia sun sepia   " +
            "save res/red.ppm red   " +
            "save res/green.ppm green   " +
            "save res/blue.ppm blue    " +
            "save res/value.ppm value    " +
            "save res/intensity.ppm intensity    " +
            "save res/luma.ppm luma    " +
            "save res/vflip.ppm vflip    " +
            "save res/hflip.ppm hflip    " +
            "save res/bright.ppm bright    " +
            "save res/dark.ppm dark    " +
            "save res/blur.ppm blur   " +
            "save res/sharp.ppm sharp   " +
            "save res/grey.ppm grey   " +
            "save res/sepia.ppm sepia   " +
            "quit");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertTrue(twoImagesTheSame("res/red-check.ppm","res/red.ppm"));
    assertTrue(twoImagesTheSame("res/green-check.ppm","res/green.ppm"));
    assertTrue(twoImagesTheSame("res/blue-check.ppm","res/blue.ppm"));
    assertTrue(twoImagesTheSame("res/value-check.ppm","res/value.ppm"));
    assertTrue(twoImagesTheSame("res/intensity-check.ppm","res/intensity.ppm"));
    assertTrue(twoImagesTheSame("res/luma-check.ppm","res/luma.ppm"));
    assertTrue(twoImagesTheSame("res/vflip-check.ppm","res/vflip.ppm"));
    assertTrue(twoImagesTheSame("res/hflip-check.ppm","res/hflip.ppm"));
    assertTrue(twoImagesTheSame("res/bright-check.ppm","res/bright.ppm"));
    assertTrue(twoImagesTheSame("res/dark-check.ppm","res/dark.ppm"));
    assertTrue(twoImagesTheSame("res/blur-check.ppm","res/blur.ppm"));
    assertTrue(twoImagesTheSame("res/sharp-check.ppm","res/sharp.ppm"));
    assertTrue(twoImagesTheSame("res/grey-check.ppm","res/grey.ppm"));
    assertTrue(twoImagesTheSame("res/sepia-check.ppm","res/sepia.ppm"));

  }

  /**
   * Sets up the correct images to compare other images to.
   */
  private void setUpCorrectImages() {
    // This string is what input we are giving it
    Reader in = new StringReader("load res/sun.ppm sun   " +
            "red-component sun red   " +
            "green-component sun green    " +
            "blue-component sun blue   " +
            "value-component sun value   " +
            "intensity-component sun intensity   " +
            "luma-component sun luma   " +
            "vertical-flip sun vflip   " +
            "horizontal-flip sun hflip    " +
            "brighten 100 sun bright    " +
            "brighten -100 sun dark   " +
            "blur sun blur   " +
            "sharpen sun sharp   " +
            "greyscale sun grey   " +
            "sepia sun sepia   " +
            "save res/red-check.ppm red   " +
            "save res/green-check.ppm green   " +
            "save res/blue-check.ppm blue    " +
            "save res/value-check.ppm value    " +
            "save res/intensity-check.ppm intensity    " +
            "save res/luma-check.ppm luma    " +
            "save res/vflip-check.ppm vflip    " +
            "save res/hflip-check.ppm hflip    " +
            "save res/bright-check.ppm bright    " +
            "save res/dark-check.ppm dark    " +
            "save res/blur-check.ppm blur   " +
            "save res/sharp-check.ppm sharp   " +
            "save res/grey-check.ppm grey   " +
            "save res/sepia-check.ppm sepia   " +
            "quit");
    StringBuilder out = new StringBuilder();
    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);
    // Run the controller
    controller.run();

    // Because we moved the loading functionality to the controller
    // (per your feedback) our only option to open images, and create
    // these check images is to open them (and save them) using the controller
  }

  /**
   * Compares two files line by line to check if they're equal.
   * @param path1 the path to the first file
   * @param path2 the path to the second file
   * @return boolean, true if the files were equal
   */
  private boolean twoImagesTheSame(String path1, String path2) {
    // Checks if two images are the same

    // ===== ===== Open file 1 ===== =====
    // Open the file in a scanner
    Scanner scan1;
    try {
      scan1 = new Scanner(new FileInputStream(path1));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File: " + path1 + " was not found");
    }

    // ===== ===== Open file 2 ===== =====
    // Open the file in a scanner
    Scanner scan2;
    try {
      scan2 = new Scanner(new FileInputStream(path2));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File: " + path2 + " was not found");
    }

    // ===== ===== Check if they're equal ===== =====
    boolean check = true;
    while (scan1.hasNextLine() && scan2.hasNextLine()) {
      if (!scan1.nextLine().equals(scan2.nextLine())) {
        check = false;
      }
    }

    return check;
  }

  @Test
  public void jpgPngGifTest() {

    // This string is what input we are giving it
    Reader in = new StringReader("load res/sun.jpg sun-jpg     " +
            "load res/sun.png sun-png     " +
            "load res/sun.gif sun-gif     " +
            "vertical-flip sun-jpg sun-flip-jpg     " +
            "vertical-flip sun-png sun-flip-png     " +
            "vertical-flip sun-gif sun-flip-gif     " +
            "save res/sun-flip.jpg sun-flip-jpg     " +
            "save res/sun-flip.png sun-flip-png     " +
            "save res/sun-flip.gif sun-flip-gif     " +
            "quit");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertEquals("Welcome to the Image Processor!\n" +
            "Type instruction: \n" +
            "Image loaded!\n" +
            "Type instruction: \n" +
            "Image loaded!\n" +
            "Type instruction: \n" +
            "Image loaded!\n" +
            "Type instruction: \n" +
            "Vertical flip created!\n" +
            "Type instruction: \n" +
            "Vertical flip created!\n" +
            "Type instruction: \n" +
            "Vertical flip created!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());

  }

  @Test
  public void blurSharpenGreyscaleSepiaTest() {

    // This string is what input we are giving it
    Reader in = new StringReader("load res/sun.ppm sun     " +
            "blur sun sun-blur     " +
            "sharpen sun sun-sharp     " +
            "greyscale sun sun-grey     " +
            "sepia sun sun-sepia     " +
            "save res/sun-blur.ppm sun-blur     " +
            "save res/sun-sharp.ppm sun-sharp     " +
            "save res/sun-grey.ppm sun-grey     " +
            "save res/sun-sepia.ppm sun-sepia     " +
            "quit");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertEquals("Welcome to the Image Processor!\n" +
            "Type instruction: \n" +
            "Image loaded!\n" +
            "Type instruction: \n" +
            "Blurred image created!\n" +
            "Type instruction: \n" +
            "Sharpened image created!\n" +
            "Type instruction: \n" +
            "Greyscale image created!\n" +
            "Type instruction: \n" +
            "Sepia image created!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());

  }

  @Test
  public void maskResizeTest() {

    // This string is what input we are giving it
    Reader in = new StringReader("load res/sun.jpg sun     " +
            "load res/sun-mask.jpg mask      " +
            "resize 5 30 sun sun-s1     " +
            "resize 30 30 sun sun-s2     " +
            "mask greyscale sun mask sun-m1 0     " +
            "mask blur sun mask sun-m2 0     " +
            "save res/sun-s1.jpg sun-s1     " +
            "save res/sun-s2.jpg sun-s2     " +
            "save res/sun-m1.jpg sun-m1     " +
            "save res/sun-m2.jpg sun-m2     " +
            "quit");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    IPMV2 model = new IPModelV2();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertEquals("Welcome to the Image Processor!\n" +
            "Type instruction: \n" +
            "Image loaded!\n" +
            "Type instruction: \n" +
            "Image loaded!\n" +
            "Type instruction: \n" +
            "Resized image created!\n" +
            "Type instruction: \n" +
            "Resized image created!\n" +
            "Type instruction: \n" +
            "Masked image created!\n" +
            "Type instruction: \n" +
            "Masked image created!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Image saved!\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());

  }


}