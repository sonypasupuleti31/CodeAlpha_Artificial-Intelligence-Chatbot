import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GPTChatBot {

    static Map<String, String> knowledgeBase = new HashMap<>();

    static String cleanText(String text) {
        return text.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").trim();
    }

    static String getResponse(String userInput) {
        userInput = cleanText(userInput);

        int maxScore = 0;
        String bestMatch = null;

        for (String question : knowledgeBase.keySet()) {
            int score = 0;
            String[] words = question.split(" ");
            for (String word : words) {
                if (userInput.contains(word)) {
                    score++;
                }
            }
            if (score > maxScore) {
                maxScore = score;
                bestMatch = question;
            }
        }

        if (bestMatch != null) {
            return knowledgeBase.get(bestMatch);
        }

        return "Sorry, I don't understand. Please ask something else.";
    }

    public static void main(String[] args) {

        knowledgeBase.put("hi", "Hello! How can I help you?");
        knowledgeBase.put("what is java", "Java is a high-level programming language.");
        knowledgeBase.put("who created java", "Java was created by James Gosling at Sun Microsystems.");
        knowledgeBase.put("what is jvm", "JVM stands for Java Virtual Machine.");
        knowledgeBase.put("what is oops",
                "OOPs means Object-Oriented Programming System. It uses classes and objects.");
        knowledgeBase.put("how to install java", "Go to oracle.com, download JDK, and install it.");
        knowledgeBase.put("difference between java and python",
                "Java is compiled, Python is interpreted. Java is faster, Python is easier.");
        knowledgeBase.put("thank you", "You're welcome!");
        knowledgeBase.put("bye", "Goodbye! Have a nice day.");

        // 5. GUI Setup using Swing
        JFrame frame = new JFrame("GPT-P: Java ChatBot");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(chatArea);
        scroll.setBounds(20, 20, 440, 350);
        frame.add(scroll);

        JTextField inputField = new JTextField();
        inputField.setBounds(20, 390, 330, 30);
        frame.add(inputField);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(360, 390, 100, 30);
        frame.add(sendButton);

        // 6. When user clicks "Send" or presses Enter
        ActionListener sendAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userText = inputField.getText();
                chatArea.append("You: " + userText + "\n");

                String botReply = getResponse(userText);
                chatArea.append("Bot: " + botReply + "\n\n");

                inputField.setText("");
            }
        };

        // 7. Add actions to button and input field
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        frame.setVisible(true);
    }
}
