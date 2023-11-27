
# Image Quantization using K-Means Clustering

This program takes an input image file and performs color quantization using the K-Means clustering algorithm. It reduces the number of distinct colors in the image while preserving the overall structure and appearance.

## Requirements

- Java Development Kit (JDK)
- Java IDE or command-line interface

## Usage

1. Clone the repository or download the source code.

2. Open the project in a Java IDE or navigate to the project directory using a command-line interface.

3. Compile the Java source files:

    ```
    javac kmeans/main/Driver.java
    ```

4. Run the program, providing the input image file path and output image file path as command-line arguments:

    ```
    java kmeans.main.Driver <input_image_path> <output_image_path>
    ```

    Replace `<input_image_path>` with the path to the image file you want to quantize, and `<output_image_path>` with the desired path for the quantized image file.

5. The program will perform color quantization on the input image using the K-Means clustering algorithm and save the quantized image to the specified output file path.

## Note

- The `kmeans.main.algo.KMeans` class is responsible for the implementation of the K-Means algorithm used for color quantization. You can modify the parameters passed to the `KMeans` constructor (`pixels`, `numClusters`, `maxIterations`) to adjust the clustering behavior.
- The `kmeans.main.Driver.Image` nested class is responsible for encapsulating the image operations. It reads the input image, extracts the pixels, and provides methods to access image properties such as width, height, and size.

## Example

To quantize an image called "galaxy.png" located at "/Users/moksh/Desktop/galaxy.png" and save the quantized image as "test_image_output.png" to "/Users/moksh/Desktop/test_image_output.png", run the following command:

```
java kmeans.main.Driver /Users/moksh/Desktop/galaxy.png /Users/moksh/Desktop/test_image_output.png
```

After execution, the program will output "Image Written" to the console if the quantized image is successfully saved.

## Contributions

Contributions to the project are welcome. If you find any issues or have ideas for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).