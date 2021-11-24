package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SwingFrame extends JFrame implements ActionListener, ItemListener, ListSelectionListener {

  // All the data for this
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JLabel fileOpenDisplay;
  private JPanel imagePanel;

  public SwingFrame() {

    // Set up the basics
    super();
    setTitle("ImagePro");
    setSize(800, 800);

    // Stuff with the main panel
    mainPanel = new JPanel();
    // Arrange elements vertically
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    // Add a scroll bar for main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);



    // Just try to display an image first ===== ===== ===== =====
    // Add a panel for the image
//    imagePanel = new JPanel();
//    // Add a border and caption
//    imagePanel.setBorder(BorderFactory.createTitledBorder("INSERT NAME OF IMAGE"));
//    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
//    // Add it to the main panel
//    mainPanel.add(imagePanel);

    // Making the label and scroll pane
//    String imagePath = new String("res/sun.jpg");
//    JLabel imageLabel = new JLabel();
//    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
//    imageLabel.setIcon(new ImageIcon(imagePath));
//    imageScrollPane.setPreferredSize(new Dimension(300,300));
//    imagePanel.add(imageScrollPane);


    // Add a button to open a file ===== ===== ===== =====

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    // Add all of the functions
    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(this);
    fileopenPanel.add(sepiaButton);



  }

  private void openImage(String path) {
    // Remove the old image potentially?
    try {
      mainPanel.remove(1);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      // There wasn't an item to remove
    }


    // Make the panel
    imagePanel = new JPanel();
    // Add a border and caption
    imagePanel.setBorder(BorderFactory.createTitledBorder(path));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    // Add it to the main panel
    mainPanel.add(imagePanel);

    // Opens an image at path
    String imagePath = new String(path);
    JLabel imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(imagePath));
    imageScrollPane.setPreferredSize(new Dimension(300,300));
    imagePanel.add(imageScrollPane);
  }



  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();

          // Get the path from as4
          String tempStr = f.getPath();
          try {
            int index = tempStr.indexOf("as4");
            if (index >= 0) {
              tempStr = tempStr.substring(index + 4);
            }
            else {
              tempStr = "error";
            }

          }
          catch (StringIndexOutOfBoundsException er) {
            tempStr = "error";
          }
          // Display the path
          fileOpenDisplay.setText(tempStr);

          // Open the image...? (if not error)
          if (!tempStr.equals("error")) {
            openImage(tempStr);
          }


        }
      }
      break;
      case "Sepia": {
        // Call some shit from the model...?
        // Basically we're going to have to save all of the files into a temp place in order to open them as we change them
        System.out.println("Sepia got clicked");
      }
      break;
    }

  }

  @Override
  public void itemStateChanged(ItemEvent e) {

  }

  @Override
  public void valueChanged(ListSelectionEvent e) {

  }
}
