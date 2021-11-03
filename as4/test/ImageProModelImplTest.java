import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.ImageProController;
import controller.ImageProControllerImpl;
import model.ImageProModel;
import model.ImageProModelImpl;
import view.ImageProView;
import view.ImageProViewImpl;

import static org.junit.Assert.*;

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




  // Test all of the ColorPixel functionality (rounding and stuff)

  // Test null inputs for the model controller and view

  // Test bad inputs for the model controller and view

  // Test bad inputs for the model commands

  // Test good things for all of the different commands
  // Using a helper method to compare the images

}