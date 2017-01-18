package chat.view;

import chat.controller.ChatController;
import javax.swing.JFrame;
import java.awt.Dimension;

public class ChatFrame extends JFrame
{
	private ChatController baseController;
	private ChatbotPanel appPanel;
	
	public ChatFrame(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		this.appPanel = new ChatbotPanel(baseController);
		
		setupFrame();
	}
	
	public void setupFrame()
	{
		this.setContentPane(appPanel);
		this.setSize(new Dimension(600, 400));
		this.setTitle("cool thingy");
		this.setVisible(true);
	}
	
	public ChatbotPanel getAppPanel()
	{
		return appPanel;
	}
}
