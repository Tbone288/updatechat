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
	private JButton browseTwitter;
	private JButton saveFile;
	private JButton loadFile;
	private JButton searchTwitter;
	private JLabel imageLabel;
	private JScrollPane chatPane;
	
	public ChatbotPanel(ChatController baseController)
	{
		super();
		setBackground(Color.BLACK);
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);		
		chatField = new JTextField(25);
		chatField.setBackground(Color.WHITE);
		chatButton = new JButton("Chat with me");
		chatPane = new JScrollPane();
		//imageLabel = new JLabel(new ImageIcon(getClass().getResource("images/chatbot.png")));
		
		
		setupChatDisplay();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	

	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
		chatDisplay.setEnabled(false);
		chatPane.setViewportView(chatDisplay);
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		//this.add(chatDisplay);
		this.add(chatButton);
		this.add(chatField);
		this.add(chatPane);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -108, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 6, SpringLayout.SOUTH, chatField);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -159, SpringLayout.EAST, this);		
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 0, SpringLayout.WEST, chatPane);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, 0, SpringLayout.EAST, chatPane);
		baseLayout.putConstraint(SpringLayout.NORTH, chatPane, 50, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatPane, 50, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatPane, -50, SpringLayout.EAST, this);
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
					String oldText = chatDisplay.getText();
					
					chatField.setText("");
					chatDisplay.setText(oldText + "\nYou said: " + userWords + "\n" + "Chatbot said: " + botResponse); 
					
					chatDisplay.setCaretPosition(0);
				}
			});
	}
	
}

