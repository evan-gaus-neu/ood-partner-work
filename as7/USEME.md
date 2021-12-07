##USEME

###How to use the GUI

* Start by running the Main.java program with no arguments
* In order to load an image, click the load button on the top left, 
and it will show a file selector prompt where it can select only a select few image extensions.
* In order to save the image being worked on, simply click the save button, and
then select the file path and specify the image name to be saved as.
Note that you must enter a valid image file extension in order to save an image.
If either no image is in the work space or the file extension is invalid, there will be a pop up message.
* The second row is for transformations specifically. On the left side there is a drop down menu to select which transformation.
This defaults to red on launch of the GUI.
* In the middle is a text field. This is used only for brighten and darken transformtions.
There must be a valid integer in the text field to specify how much to brighten and darken the image.
If it is invalid, a popup message will appear.
* On the right is a transform button. This actually enacts the transformation specified by the left and middle fields.
Again, if there is no image in the work space, the program shows a popup message.
* Now we have the actual image display to show what the image is being worked on. Here, you can scroll around the image display.
* Below this is a panel which we display the histogram values of the image. 
The colors correspond to the color pixel, except the dark grey represents the intensity value histogram.