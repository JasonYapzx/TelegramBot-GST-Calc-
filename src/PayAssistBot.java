package sgPayAssist_bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PayAssistBot extends TelegramLongPollingBot {

	public void onUpdateReceived(Update update) {
	    // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
	        message.setChatId(update.getMessage().getChatId().toString());
	        message.setText(update.getMessage().getText());
	        
	        try {
	            execute(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
		
	}

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "SGpayassist_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1637726184:AAGGl10484tFyCFZhmRljWucD3IJk7FYeNM";
	}
	


}
