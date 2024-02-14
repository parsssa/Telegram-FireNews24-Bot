package ZonaBot;

import com.example.inferno_fx.Pannello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MainPrincipale {
    public static final Logger logger = LoggerFactory.getLogger(MainPrincipale.class);

    public static void main(String[] args) {

        try {

    logger.info("bot in esecuzione");
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new myBotDef());


        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("FIREBOTNEWS successfully started!");
        Pannello.main(args);
    }
}




