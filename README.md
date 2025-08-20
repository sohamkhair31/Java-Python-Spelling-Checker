# Spell Checker Application

This is a Java Swing-based spell checker application with a Python backend. It's a simple GUI tool that allows users to input text, check for spelling errors, and receive a corrected output.
# ✨ Features

  >1. GUI Frontend (Java Swing): A user-friendly graphical interface built with Java Swing for easy interaction.
  >2. Backend Processing (Python): Utilizes a Python script to perform the core spell-checking logic.
  >3. Error Identification: Detects and lists all spelling mistakes found in the user's input.
  >4. Corrected Output: Provides a text area displaying the corrected version of the input.
  >5. Cross-Language Communication: Implements ProcessBuilder in Java to execute the Python script and handle data exchange between the two languages.
# 🚀 How It Works

The application follows a simple process:
    User Input: The user types text into the "USER INPUT" text area.
    Check Button: When the "Check" button is clicked, the Java application takes the input string.
    Process Execution: It uses ProcessBuilder to start the Python script as a separate process. The input string is passed to the script.
    Spell Checking: The Python script, using a library like pyspellchecker or textblob, analyzes the input for spelling errors.
    Output & Mistakes: The script returns both the list of mistakes and the corrected text.
    GUI Update: The Java application reads the output from the Python process and updates the "Mistakes" and "OUTPUT" text areas accordingly.
# 🛠️ Technologies Used
    Frontend: Java (Swing)
    Backend: Python
    Communication: java.lang.ProcessBuilder
# 📊 Performance
The current model has an accuracy rate of 60-70%. I am actively working on improving the algorithm and expanding the dictionary to enhance its performance.

# 💡 Future Enhancements

    [ ] Improve the spell-checking algorithm for higher accuracy.

    [ ] Add a feature for users to add words to a custom dictionary.

    [ ] Implement a progress bar or loading indicator for longer texts.

    [ ] Develop an installer or runnable JAR file for easier distribution.

🤝 Contributing

Contributions are welcome! If you have any suggestions or find a bug, please open an issue or submit a pull request.
