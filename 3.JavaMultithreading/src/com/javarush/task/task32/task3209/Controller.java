package com.javarush.task.task32.task3209;

import javax.swing.text.html.HTMLDocument;
import java.io.File;

/**
 * Created by Данил on 20.05.2017.
 */
public class Controller {
    /*14.2. Добавь в класс контроллера геттер для модели, в нашем случае это поле document.

*/
    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
    public void init(){

    }

    public HTMLDocument getDocument() {
        return document;
    }

    public Controller(View view) {
        this.view = view;
    }

    public void exit() {
        System.exit(0);
    }

    private View view;
    private HTMLDocument document;
    private File currentFile;
}
