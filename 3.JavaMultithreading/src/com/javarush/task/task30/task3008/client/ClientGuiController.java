package com.javarush.task.task30.task3008.client;

/**
 * Created by Данил on 04.06.2017.

public class ClientGuiController extends Client {
    private ClientGuiModel model = new ClientGuiModel();

    public ClientGuiModel getModel() {
        return model;
    }

    public static void main(String[] args) {
        new ClientGuiController().run();
    }
    private ClientGuiView view = new ClientGuiView(this);

    protected SocketThread getSocketThread() {
        return new GuiSocketThread();
    }

    public void run() {
        getSocketThread().run();
    }
    public String getServerAddress() {
        return view.getServerAddress();
    }
    public int getServerPort() {
        return view.getServerPort();
    }
    public String getUserName() {
        return view.getUserName();
    }

    public class GuiSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
        }
        @Override
        protected void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshUsers();
        }
        @Override
        public void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshUsers();
        }
        @Override
        public void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }

}
*/