package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;

public class ChatController
{
	private Chatbot stupidBot;
	private ChatViewer chatView;
	private ChatFrame baseFrame;
	
	public ChatController()
	{
		stupidBot = new Chatbot("Boaty McBoatFace");
		chatView = new ChatViewer();
	}
	
	public void start()
	{
		String response = chatView.collectResponse("What do you want to talk about today?");
				
		while(stupidBot.lengthChecker(response))
		{
			chatView.displayMessage(useChatbotCheckers(response));
			response = chatView.collectResponse("Oh, you are interested in " + response);
		}
	}
	
	public String useChatbotCheckers(String input)
	{
		String answer = "";
		
		if(stupidBot.contentChecker(input))
		{
			answer += "\nYou know my special secret\n";
		}
		if(stupidBot.memeChecker(input))
		{
			answer += "\nI can has memes?\n";
		}
		
		if (answer.length() == 0)
		{
			answer += "Sorry, I don't know about " + input;
		}
		int canBeRandom = (int) (Math.random() * 7);
		if (canBeRandom % 7 == 0)
		{
			answer += randomTopicGenerator();
		}
		else
		{
			chatView.displayMessage("Thank you for chatting with me");
			System.exit(0);
		}
		return answer;
	}
	
	private String randomTopicGenerator()
	{
		String randomTopic = "";
		int random = (int)(Math.random() * 7);
		
		switch (random)
		{
		case 0:
			randomTopic = "Have I told you how great you are?";
			break;
		case 1:
			randomTopic = "I am cooler than you are!";
			break;
		case 2:
			randomTopic = "Do you like pasta?";
			break;
		case 3:
			randomTopic = "Would you like some toast?";
			break;
		case 4:
			randomTopic = "I ate some bugs, I ate some grass...";
			break;
		case 5:
			randomTopic = "I am not very smart:(";
			break;
		case 6:
			randomTopic = "For a chat bot I don't say many different things.";
			break;
		default:
			randomTopic = "I hope you are doing well";
			break;
		}
		return randomTopic;
		
	}
	public Object getBaseFrame()
	{
		return baseFrame;
	}
	public Chatbot getChatbot()
	{
		return stupidBot;
	}
}
