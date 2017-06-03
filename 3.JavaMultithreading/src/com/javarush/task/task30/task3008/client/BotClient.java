package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Данил on 03.06.2017.
 */
public class BotClient extends Client {
    public static void main(String[] args) {
        new BotClient().run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    public String getUserName() {
        return "date_bot_" + (int) (100 * Math.random());
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String name = message.substring(0, message.indexOf(":"));
                String text = message.substring(message.indexOf(":") + 1).trim();
                // System.err.println("'" + name + "' '" + text + "'");
                SimpleDateFormat dateFormatter;
                if ("дата".equals(text)) {
                    dateFormatter = new SimpleDateFormat("d.MM.YYYY");
                } else if ("день".equals(text)) {
                    dateFormatter = new SimpleDateFormat("d");
                } else if ("месяц".equals(text)) {
                    dateFormatter = new SimpleDateFormat("MMMM");
                } else if ("год".equals(text)) {
                    dateFormatter = new SimpleDateFormat("YYYY");
                } else if ("время".equals(text)) {
                    dateFormatter = new SimpleDateFormat("H:mm:ss");
                } else if ("час".equals(text)) {
                    dateFormatter = new SimpleDateFormat("H");
                } else if ("минуты".equals(text)) {
                    dateFormatter = new SimpleDateFormat("m");
                } else if ("секунды".equals(text)) {
                    dateFormatter = new SimpleDateFormat("s");
                } else return;
                String date = dateFormatter.format(Calendar.getInstance().getTime());
                sendTextMessage("Информация для " + name + ": " + date);
            }
        }

    }
}
