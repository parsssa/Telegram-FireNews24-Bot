package Rss;

import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.BanChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.BanChatSenderChat;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class myBotDef extends TelegramLongPollingBot {

    @Override

    public void onUpdateReceived(Update update) {


      if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            var chat_id = update.getMessage().getChatId();
            var punta = update.getMessage().getReplyToMessage().getFrom().getId();
           // var personaDaBannare = update.getMessage().getReplyToMessage().getChatId();



          if (message_text.equals("/ban")){



               var bannaggio = BanChatMember.builder().chatId(String.valueOf(chat_id)).userId(punta).build();
               //var bananaaa = new BanChatMember(String.valueOf(chat_id), punta);

                try{
                    execute(bannaggio);

                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }

            }


            if (message_text.equals("/fotoInnocente")){
                var foto = SendPhoto.builder().chatId(String.valueOf(chat_id)).photo(new InputFile("https://static-ca-cdn.eporner.com/gallery/cl/RH/bmnjxXXRHcl/707023-hitomi-tanaka-nude.jpg")).build();
                var valutazioni = SendPoll.builder().chatId(String.valueOf(chat_id)).question(" valuta questa news! ").option("1/3 😇").option("4/6 😄").option("7/10 🤩 ").build();
                var randomico = SendDice.builder().chatId(String.valueOf(chat_id)).emoji("🎲").build();


                try {
                    execute(foto);
                    execute(valutazioni);
                    execute(randomico);

                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }


            }
        }

        //"fire_24_bot";
       // "5363385439:AAFniNqbbJy-9KcoT4Y_Wgn6YEdSqzvXIMo"


    }


    @Override
    public String getBotUsername() {
        return "fire_news_24_max_bot";
    }

    @Override
    public String getBotToken() {
        return "5206084185:AAEbrndHghRn0SDzVSsTTjJkJql8u9mfrV4";
    }
}