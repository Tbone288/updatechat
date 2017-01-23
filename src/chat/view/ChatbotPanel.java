package chat.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import chat.controller.ChatController;
import javax.swing.SpringLayout;

public class ChatbotPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JLabel imageLabel;
	
	public ChatbotPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		this.baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		
		chatField = new JTextField(25);
		
		chatField.setBackground(Color.WHITE);
		chatButton = new JButton("Chat with me");
		
		imageLabel = new JLabel(new ImageIcon(getClass().getResource("images/chatbot.png")));
		
		setupChatDisplay();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private SpringLayout SpringLayout() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
		chatDisplay.setEnabled(false);
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.gray);
		this.add(chatDisplay);
		this.add(chatButton);
		this.add(chatField);
		this.add(imageLabel);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 106, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 0, SpringLayout.WEST, chatDisplay);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -32, SpringLayout.NORTH, chatDisplay);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatDisplay, -33, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, -43, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -198, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					String userWords = chatField.getText();
					String botResponse = baseController.useChatbotCheckers(userWords);
					chatField.setText("");
					chatDisplay.setText("you said: " + userWords + "\n" + "Chatbot said: " + botResponse); 
				}
			});
	}
	
}

