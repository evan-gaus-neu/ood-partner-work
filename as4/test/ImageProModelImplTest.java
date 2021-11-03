import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.ImageProController;
import controller.ImageProControllerImpl;
import data.ColorPixel;
import model.ImageProModel;
import model.ImageProModelImpl;
import view.ImageProView;
import view.ImageProViewImpl;

import static org.junit.Assert.assertEquals;


public class ImageProModelImplTest {

  @Test
  public void sampleTest() {

    // This string is what input we are giving it
    Reader in = new StringReader("q");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    ImageProModel model = new ImageProModelImpl();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertEquals("Welcome to the Image Processor!\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());

  }

  @Test
  public void checkAll() {

    // load res/sun.ppm sun   red-component sun red   green-component sun green    blue-component sun blue   value-component sun value   intensity-component sun intensity   luma-component sun luma   vertical-flip sun vflip   horizontal-flip sun hflip    brighten 100 sun bright    brighten -100 sun dark   save res/red.ppm red   save res/green.ppm green   save res/blue.ppm blue    save res/value.ppm value    save res/intensity.ppm intensity    save res/luma.ppm luma    save res/vflip.ppm vflip    save res/hflip.ppm hflip    save res/bright.ppm bright    save res/dark.ppm dark    quit

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
            "quit");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    ImageProModel model = new ImageProModelImpl();
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
    ImageProModel model = new ImageProModelImpl();
    ImageProView view = new ImageProViewImpl(model, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void contNullModel() {
    // This string is what input we are giving it
    Reader in = new StringReader("q");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    ImageProModel model = new ImageProModelImpl();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(null, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void contNullView() {
    // This string is what input we are giving it
    Reader in = new StringReader("q");

    // Set up the model, view, and controller
    ImageProModel model = new ImageProModelImpl();
    ImageProController controller = new ImageProControllerImpl(model, null, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void contNullIn() {
    // This string is what input we are giving it
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    ImageProModel model = new ImageProModelImpl();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, null);
  }


  // TEST BAD CONTROLLER INPUTS (AND THE VIEW DISPLAYING THAT THESE ARE BAD)

  @Test
  public void contBadInput() {
    // This string is what input we are giving it
    Reader in = new StringReader("nothing bad command    load nothing lame     brighten not-an-int something something     quit ");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    ImageProModel model = new ImageProModelImpl();
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
            "Invalid: File: nothing was not found\n" +
            "Type instruction: \n" +
            "Invalid input for increment!\n" +
            "Type instruction: \n" +
            "Unrecognized Command: not-an-int\n" +
            "Type instruction: \n" +
            "Unrecognized Command: something\n" +
            "Type instruction: \n" +
            "Unrecognized Command: something\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());
  }


  // TEST THE MODEL WITH BAD INPUT

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
            "save res/file.ppm dne   " +
            "quit");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    ImageProModel model = new ImageProModelImpl();
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
            "Program quit. Thanks!", out.toString());
  }


  // Test bad inputs for the model commands

  // Test good things for all of the different commands
  // Using a helper method to compare the images

}