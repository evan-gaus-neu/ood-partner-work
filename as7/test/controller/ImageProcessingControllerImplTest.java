package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * Test the ImageProcessingControllerImpl.
 */
public class ImageProcessingControllerImplTest {
  private ImageProcessingControllerImpl controller1;
  private ImageProcessingView view1;
  private StringBuilder out;
  private StringReader inLoad;

  @Before
  public void setUp() {
    out = new StringBuilder();
    inLoad = new StringReader("load code/imagename.ppm image \nq");
    view1 = new ImageProcessingTextView(out);
    controller1 = new ImageProcessingControllerImpl(view1, inLoad);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    new ImageProcessingControllerImpl(null, inLoad);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadableObject() {
    new ImageProcessingControllerImpl(view1, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBothNull() {
    new ImageProcessingControllerImpl(null, null);
  }

  @Test
  public void testLoadCommandImageNotFound() throws IOException {
    controller1.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[1];
    String expectedOutput = "File cannot be found or not in correct format.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testLoadCommandImageLoaded() throws IOException {
    String inputString = "load res/Tree.ppm tree \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[1];
    String expectedOutput = "Image loaded.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSaveCommandImageNotFound() throws IOException {
    String inputString = "save res/random.ppm random \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[1];
    String expectedOutput = "Image source not found.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSaveCommandImageFound() throws IOException {
    String inputString = "load res/Tree.ppm tree \nsave res/Tree.ppm tree \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image saved.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testBrightenCommandInvalidFactor() throws IOException {
    String inputString = "load res/Tree.ppm tree \nbrighten tree 10 tree-brighten \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Factor is not a valid integer.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testBrightenCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nbrighten 10 tree tree-brighten \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testDarkenCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nbrighten -30 tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testVerticalFlipCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nvertical-flip tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testHorizontalFlipCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nhorizontal-flip tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testBadCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nplay-baseball tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Command not found! Try again.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testValueCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nvalue-component tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testIntenstiyCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nintensity-component tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testLumaCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nluma-component tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testRedCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nred-component tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testGreenCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \ngreen-component tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testBlueCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nblue-component tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testQuitCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Quit";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSepiaCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nsepia-tone tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testBlurCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nblur tree tree-blur \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSharpenCommand() throws IOException {
    String inputString = "load res/Tree.ppm tree \nsharpen tree tree-dark \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image transformed.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testLoadPNGCommand() throws IOException {
    String inputString = "load res/tree-blur.png tree\nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[1];
    String expectedOutput = "Image loaded.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testLoadJPGCommand() throws IOException {
    String inputString = "load res/tree-sharpen.jpg tree\nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[1];
    String expectedOutput = "Image loaded.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testLoadBMPCommand() throws IOException {
    String inputString = "load res/tree-luma.bmp tree\nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[1];
    String expectedOutput = "Image loaded.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSaveCommandPPMtoJPG() throws IOException {
    String inputString = "load res/Tree.ppm tree \nsave res/Tree.jpg tree \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image saved.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSaveCommandPPMtoPNG() throws IOException {
    String inputString = "load res/Tree.ppm tree \nsave res/Tree.png tree \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image saved.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSaveCommandPPMtoBMP() throws IOException {
    String inputString = "load res/Tree.ppm tree \nsave res/Tree.bmp tree \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image saved.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSaveCommandJPGtoPPM() throws IOException {
    String inputString = "load res/Tree.jpg class \nsave res/Tree.ppm class \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image saved.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSaveCommandPNGtoPPM() throws IOException {
    String inputString = "load res/tree-blur.png tree \nsave res/tree-sharp.ppm tree \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image saved.";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testSaveCommandBMPtoPPM() throws IOException {
    String inputString = "load res/tree-luma.bmp tree \nsave res/tree-luma.ppm tree \nq";
    StringReader input = new StringReader(inputString);
    ImageProcessingControllerImpl controller2 = new ImageProcessingControllerImpl(view1, input);
    controller2.runImageProcessing();
    String actualOutput = out.toString();
    String[] lines = actualOutput.split("\n");
    actualOutput = lines[2];
    String expectedOutput = "Image saved.";
    assertEquals(expectedOutput, actualOutput);
  }





}