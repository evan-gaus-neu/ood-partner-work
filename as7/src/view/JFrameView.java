package view;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;

/**
 * Represents a GUI View.
 */
public class JFrameView extends JFrame implements ImageProcessingView {
  private JButton loadButton;
  private JButton saveButton;
  private JComboBox<String> combobox;
  private JTextField enterFactor;
  private JButton transformButton;
  private JLabel imageLabel;
  private String state = "red";
  private Histogram hist;

  /**
   * Constructs a GUI View object using the JFrame class.
   */
  public JFrameView() {
    super();
    setTitle("Image Processor");
    setSize(1150, 850);
    setLocation(25, 25);
    this.setLayout(new FlowLayout());
    JPanel display = new JPanel();
    display.setLayout(new BoxLayout(display, BoxLayout.PAGE_AXIS));
    this.add(display);


    //Save Load Panel
    JPanel fileLoadPanel = new JPanel();
    fileLoadPanel.setLayout(new FlowLayout());
    display.add(fileLoadPanel);

    loadButton = new JButton("Load Image");
    fileLoadPanel.add(loadButton);
    saveButton = new JButton("Save Image");
    fileLoadPanel.add(saveButton);


    JPanel transformOptions = new JPanel();
    String[] options = {"red", "green", "blue", "value", "luma", "intensity", "horizontal flip",
      "vertical flip", "brighten", "darken", "blur", "sharpen", "sepia"};
    combobox = new JComboBox<String>();
    for (int i = 0; i < options.length; i++) {
      combobox.addItem(options[i]);
    }
    transformOptions.add(combobox);
    enterFactor = new JTextField(10);
    transformOptions.add(enterFactor);
    transformButton = new JButton("Transform");
    transformOptions.add(transformButton);
    display.add(transformOptions);

    JPanel imagePanel = new JPanel();
    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(750, 500));
    imagePanel.add(imageScrollPane);
    display.add(imagePanel);

    int[][] initial = {{}};
    hist = new Histogram(initial);
    hist.setPreferredSize(new Dimension(1100, 300));
    display.add(hist);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  /**
   * Make the image appear in the GUI.
   *
   * @param bi the image that will be visualized
   */
  public void visualize(BufferedImage bi) {
    imageLabel.setIcon(new ImageIcon(bi));
  }

  @Override
  public void renderMessage(String message) throws IOException {
    JOptionPane.showMessageDialog(null, message);
  }

  /**
   * Adds features or transformations to the image.
   *
   * @param features what will be added to the image
   */
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> generatePopUpLoad(features));
    saveButton.addActionListener(evt -> {
      try {
        generatePopUpSave(features);
      } catch (IOException ignored) {
      }
    });
    combobox.addActionListener(this::delegateTransformation);
    transformButton.addActionListener(evt -> {
      try {
        transformState(features);
      } catch (IOException ignored) {
      }
    });
  }

  /**
   * Generates a Load image screen.
   *
   * @param features with these transformations
   */
  public void generatePopUpLoad(Features features) {
    JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PPM & PNG & BMP Images", "jpg", "ppm", "png", "bmp");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(JFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      features.load(f.getAbsolutePath());
    }
  }

  /**
   * Generate a screen to save an image.
   *
   * @param features what will be added to the image.
   * @throws IOException if the extension is invalid
   */
  public void generatePopUpSave(Features features) throws IOException {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(JFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      features.save(f.getAbsolutePath());
    }
  }

  /**
   * Delegate to the features interface.
   *
   * @param a object that needs to be delegated
   */
  public void delegateTransformation(ActionEvent a) {
    if (a.getSource() instanceof JComboBox) {
      JComboBox<String> box = (JComboBox<String>) a.getSource();
      state = (String) box.getSelectedItem();
    }
  }

  /**
   * Transform the image based on given features.
   *
   * @param features what to do to the image.
   * @throws IOException operation does not exist
   */
  public void transformState(Features features) throws IOException {
    switch (state) {
      case "red":
        features.redComponent();
        break;
      case "green":
        features.greenComponent();
        break;
      case "blue":
        features.blueComponent();
        break;
      case "value":
        features.valueComponent();
        break;
      case "luma":
        features.lumaComponent();
        break;
      case "intensity":
        features.intensityComponent();
        break;
      case "horizontal flip":
        features.horizontalFlip();
        break;
      case "vertical flip":
        features.verticalFlip();
        break;
      case "brighten":
        features.brighten(enterFactor.getText());
        break;
      case "darken":
        features.darken(enterFactor.getText());
        break;
      case "blur":
        features.blur();
        break;
      case "sharpen":
        features.sharpen();
        break;
      case "sepia":
        features.sepia();
        break;
      default:
        System.out.println("Operation not found");
    }
  }

  /**
   * Create a histogram.
   *
   * @param histograms Using these points to make lines.
   */
  public void histogram(int[][] histograms) {
    hist.changeCoordinates(histograms);
    hist.repaint();
  }
}
