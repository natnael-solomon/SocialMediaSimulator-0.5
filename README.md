# OOP Project

### Step 1: Download and Install JavaFX SDK

1. **Download the JavaFX SDK** from the official [Gluon website](https://gluonhq.com/products/javafx/).
2. **Extract the SDK** to a folder on your system, e.g., `C:\Java\javafx-sdk-22.0.1`.

---

### Step 2: Set Environment Variables

1. Press `Win + S`, type "Environment Variables," and select **Edit the system environment variables**.
2. In the **System Properties** dialog, click the **Environment Variables** button.
3. In the **System variables** section:
    - Locate and select the `Path` variable, then click **Edit**.
    - Click **New** and add the path to the `bin` folder of the JavaFX SDK, e.g.,
      ```
      C:\Java\javafx-sdk-22.0.1\bin
      ```
4. Click **OK** to save your changes.

---

### Step 3: Run JavaFX in Visual Studio Code

1. **Install the Extension Pack for Java**:
    - Press `Ctrl + Shift + X` to open the Extensions view.
    - Search for and install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).

2. **Open the Project Folder**:
    - Press `Ctrl + K + O` (or navigate to File > Open Folder).
    - Select your project folder and open it.

3. **Run the JavaFX Application**:
    - Open the Maven Explorer (`Ctrl + Shift + E`).
    - Expand `socialmediaSimulator > Plugins > javafx`.
    - Run the Maven goal: `javafx:run`.

---

### Notes:
- **Work in Progress**: Some parts of the project are still incomplete.
- The code structure may not yet be perfectly organized, but major changes are not expected.
