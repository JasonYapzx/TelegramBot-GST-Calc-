package sgPayAssist_bot;



import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PayAssistBot extends TelegramLongPollingBot {

	
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		String temp = message.getText();
		
		if(temp != null && temp != "") {
			if(temp.equals("/start")) {
				sendMsg(message, "Welcome to SGPayAssistBot, " + update.getMessage().getFrom().getFirstName() + ". \n" +
							 "Please enter a price you would like to be adjusted for GST and Service Charge.");
			}
			
	
			if(temp.equals("/price")) {
				sendMsg(message, "Prices are calculated with a 7% GST as well as a 10% Service Charge!");
			}
			
			else if (isNumber(Double.parseDouble(temp))) {
				double price = calculatePrice(Double.parseDouble(temp));
				sendMsg(message, "Price: $" + price);
			}
		}
	}
	
	public boolean isNumber(Object object) {
		if(object instanceof Number) {
			return true;
		}
		return false;
	}
	
	private double calculatePrice(double initialPrice) {
		double calculatedPrice = (double) ((initialPrice*1.1) * 1.07);
		double finalPrice = ((Math.round(calculatedPrice * 100))/100D);
		return finalPrice;
	}
	
	private void sendMsg(Message message, String s) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId().toString());
		//sendMessage.setReplyToMessageId(message.getMessageId());
		sendMessage.setText(s);
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "gstcalculator_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1744352996:AAHQIHhtdueVICLxpZ59IffU1_66toaSIgA";
	}
	


}
