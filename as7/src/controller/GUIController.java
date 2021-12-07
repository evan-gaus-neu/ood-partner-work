package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import model.BlurModel;
import model.BrightenDarkenModel;
import model.HorizontalModel;
import model.ImageProcessingModel;
import model.IntensityModel;
import model.LumaModel;
import model.MosaicModel;
import model.RGBModel;
import model.SepiaModel;
import model.SharpenModel;
import model.ValueModel;
import model.VerticalModel;
import model.util.ImageUtil;
import view.JFrameView;

/**
 * Represents a Controller that utilizes a GUI.
 */
public class GUIController implements Features {
  private ImageProcessingModel model;
  private JFrameView view;

  /**
   * Constructs a GUIController object.
   */
  public GUIController() {
    super();
  }

  /**
   * Constructs a GUIController object with a given model.
   *
   * @param m given ImageProcessingModel
   */
  public GUIController(ImageProcessingModel m) {
    model = m;
  }

  @Override
  public void setView(JFrameView v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void load(String path) {
    String[] ext = path.split("\\.");
    int[][][] d;
    if (ext[ext.length - 1].equals("ppm")) {
      d = ImageUtil.readPPM(path);
    } else {
      d = ImageUtil.readNonPPM(path);
    }
    this.model = new BrightenDarkenModel(d, 0);
    BufferedImage bi = arrayToBufferedImage(model.getData());
    view.visualize(bi);
    view.histogram(model.getHistograms());
  }

  @Override
  public void save(String path) throws IOException {
    try {
      String[] ext = path.split("\\.");
      if (ext[ext.length - 1].equals("ppm")) {
        ImageUtil.savePPM(model, path);
      } else if (validExt(ext[ext.length - 1])) {
        ImageUtil.saveNonPPM(model, path, ext[ext.length - 1]);
      } else {
        view.renderMessage("Invalid file type. Must be png, jpg, bmp, or ppm");
      }
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  private boolean validExt(String s) {
    return s.equals("png") || s.equals("jpg") || s.equals("bmp") || s.equals("jpeg");
  }

  @Override
  public void brighten(String factor) throws IOException {
    try {
      model = new BrightenDarkenModel(model.getData(), Integer.parseInt(factor));
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NumberFormatException e) {
      view.renderMessage("Invalid Factor");
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void darken(String factor) throws IOException {
    try {
      model = new BrightenDarkenModel(model.getData(), Integer.parseInt(factor) * -1);
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NumberFormatException e) {
      view.renderMessage("Invalid Factor");
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void mosaic(String numSeeds) throws IOException {
    try {
      model = new MosaicModel(model.getData(), Integer.parseInt(numSeeds));
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NumberFormatException e) {
      view.renderMessage("Invalid Number of Seeds");
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void blur() throws IOException {
    try {
      model = new BlurModel(model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void sharpen() throws IOException {
    try {
      model = new SharpenModel(model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void redComponent() throws IOException {
    try {
      model = new RGBModel(ImageProcessingModel.RedGreenBlue.R, model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void greenComponent() throws IOException {
    try {
      model = new RGBModel(ImageProcessingModel.RedGreenBlue.G, model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void blueComponent() throws IOException {
    try {
      model = new RGBModel(ImageProcessingModel.RedGreenBlue.B, model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void valueComponent() throws IOException {
    try {
      model = new ValueModel(model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void lumaComponent() throws IOException {
    try {
      model = new LumaModel(model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void intensityComponent() throws IOException {
    try {
      model = new IntensityModel(model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void horizontalFlip() throws IOException {
    try {
      model = new HorizontalModel(model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void verticalFlip() throws IOException {
    try {
      model = new VerticalModel(model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  @Override
  public void sepia() throws IOException {
    try {
      model = new SepiaModel(model.getData());
      model.transform();
      BufferedImage bi = arrayToBufferedImage(model.getData());
      view.visualize(bi);
      view.histogram(model.getHistograms());
    } catch (NullPointerException n) {
      view.renderMessage("No image loaded");
    }
  }

  private BufferedImage arrayToBufferedImage(int[][][] d) {
    BufferedImage bi = new BufferedImage(d[0].length, d.length, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < d.length; i++) {
      for (int j = 0; j < d[i].length; j++) {
        int rgb = d[i][j][3] << 24 | d[i][j][0] << 16 | d[i][j][1] << 8 | d[i][j][2];
        bi.setRGB(j, i, rgb);
      }
    }
    return bi;
  }
}
