package messenger;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client {
	public Client (String name, Messenger pub) {
		window(name);
		user = name;
		publisher = pub;
	}
	public void newMessage() {
		publisher.publish(user + ": " + message + "\n");
	}
	public void send(String message) {
		textArea.append(message); 
	}
	public void window(String user) {
		frame = new JFrame(user); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,570);
		
		pane = frame.getContentPane();		
		pane.setLayout(new FlowLayout());
		
		textArea = new JTextArea(30,40);
		textArea.setEditable(false);
		pane.add(textArea);
		
		textField = new JTextField("",30);
		pane.add(textField);
		
		button = new JButton("send");
		button.addActionListener(listener);
		pane.add(button);
		frame.setVisible(true); 
	}
	// Action Listener
	public ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			message = textField.getText();
			newMessage();
			textField.setText("");
		}
	};        
	public Messenger publisher;
	public JFrame frame;
	public Container pane;
	public JTextArea textArea;
	public JTextField textField;
	public JButton button;
	public String user;
	public String message;
}
