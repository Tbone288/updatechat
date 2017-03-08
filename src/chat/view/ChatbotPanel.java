package chat.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import chat.controller.ChatController;
import chat.controller.FileController;

import javax.swing.SpringLayout;

public class ChatbotPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JButton postTwitter;
	private JButton saveFile;
	private JButton loadFile;
	private JButton searchTwitter;
//	private JLabel imageLabel;
	private JScrollPane chatPane;
	
	
	public ChatbotPanel(ChatController baseController)
	{
		super();
		setBackground(new Color(176, 224, 230));
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);		
		chatField = new JTextField(25);
		chatField.setBackground(Color.WHITE);
		chatButton = new JButton("Chat with me");
		postTwitter = new JButton("Post to Twitter");
		loadFile = new JButton("Load");
		saveFile = new JButton("Save");
		searchTwitter = new JButton("Search Twitter");
		chatPane = new JScrollPane();
		saveFile.setToolTipText("Put a name in the testfield");
		loadFile.setToolTipText("");
		
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
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//chatPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		//this.add(chatDisplay);

		this.add(chatButton);
		this.add(loadFile);
		this.add(saveFile);
		this.add(searchTwitter);
		this.add(postTwitter);
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
		baseLayout.putConstraint(SpringLayout.WEST, postTwitter, 0, SpringLayout.WEST, this);		
		baseLayout.putConstraint(SpringLayout.NORTH, postTwitter, 0, SpringLayout.NORTH, saveFile);
		baseLayout.putConstraint(SpringLayout.SOUTH, saveFile, -6, SpringLayout.NORTH, loadFile);
		baseLayout.putConstraint(SpringLayout.EAST, saveFile, 0, SpringLayout.EAST, loadFile);
		baseLayout.putConstraint(SpringLayout.SOUTH, loadFile, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, loadFile, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, searchTwitter, 0, SpringLayout.NORTH, loadFile);
		baseLayout.putConstraint(SpringLayout.WEST, searchTwitter, 0, SpringLayout.WEST, postTwitter);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 150, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, -50, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatPane, -150, SpringLayout.SOUTH, this);
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
			{
				
				public void actionPerformed(ActionEvent click)
				{
					String userWords = chatField.getText();
					String botResponse = baseController.useChatbotCheckers(userWords);
					String oldText = chatDisplay.getText();
					
					chatField.setText("");
					
					chatDisplay.setText(oldText + "\nYou said: " + userWords + "\n" + "Chatbot said: " + botResponse); 
					chatDisplay.setCaretPosition(chatDisplay.getCaretPosition());
				}
			});
	
	
	saveFile.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent click)
		{
			String fileName = chatField.getText();
					
			FileController.saveFile(baseController, fileName.trim(), chatDisplay.getText());
		}
	});
	
	loadFile.addActionListener(new ActionListener()
			{
		public void actionPerformed(ActionEvent click)
		{
			String fileName = chatField.getText() + ".txt";
			String saved = FileController.readFile(baseController, fileName);
			chatDisplay.setText(saved);
		}
			});
}
}

