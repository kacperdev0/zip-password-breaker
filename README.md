Certainly! Here's a simple README file for your GitHub repository based on the provided Java code for a "Zip Password Breaker" application using Swing and Zip4j library:

---

# Zip Password Breaker

This Java application allows you to brute-force crack the password of a ZIP file using a specified set of characters and maximum password length. It employs a Swing GUI for user interaction and utilizes the Zip4j library for ZIP file handling.

## Features

- **Select ZIP File:** Choose the ZIP file for which you want to crack the password.
- **Specify Maximum Length:** Define the maximum length of the password to attempt.
- **Character Set:** Input the characters that should be used to generate password combinations.
- **Real-time Feedback:** Displays current password attempts and notifies when a correct password is found.

## Setup

To run the application locally or contribute:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/kacperdev0/zip-password-breaker.git
   cd zip-password-breaker
   ```

2. **Compile and Run:**
   - Ensure you have Java JDK and Maven installed.
   - Compile the project using Maven:
     ```bash
     mvn clean install
     ```
   - Run the application:
     ```bash
     mvn exec:java -Dexec.mainClass="org.example.GUI"
     ```

## Usage

1. Launch the application.
2. Click on "Select ZIP" button to choose the ZIP file.
3. Specify the maximum password length and characters to use.
4. Click "Submit" to start the password cracking process.
5. The application will attempt to crack the password and display the result.

## Libraries Used

- **Swing:** Java GUI toolkit.
- **Zip4j:** Library for handling ZIP files in Java.

## Contributing

Contributions are welcome! Feel free to open issues and pull requests.
