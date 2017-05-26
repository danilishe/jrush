package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.*;

/**
 * Created by Данил on 20.05.2017.
 */
public class Controller {
    /*
20.1. Реализуй метод создания нового документа createNewDocument() в контроллере. Он должен:
20.1.1. Выбирать html вкладку у представления.
20.1.2. Сбрасывать текущий документ.
20.1.3. Устанавливать новый заголовок окна, например: «HTML редактор«. Воспользуйся методом setTitle(),
который унаследован в нашем представлении.
20.1.4. Сбрасывать правки в Undo менеджере. Используй метод resetUndo представления.
20.1.5. Обнулить переменную currentFile.
20.2. Реализуй метод инициализации init() контроллера. Он должен просто вызывать метод создания
нового документа.
Проверь работу пункта меню Новый.
*/
    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document,0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }
    public void setPlainText(String text){
        try {
            resetDocument();
            StringReader stringReader = new StringReader(text);
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
    public void resetDocument(){
        if (document != null)
            document.removeUndoableEditListener(view.getUndoListener());
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
    public void init(){
        createNewDocument();
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

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        jFileChooser.setFileFilter(new HTMLFileFilter());
        if (jFileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try(FileReader fileReader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(fileReader, document, 0);

            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
        }
    }

    public void saveDocument() {
        if (currentFile != null) {
            view.selectHtmlTab();
            try(FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                fileWriter.flush();
            } catch (Exception e){
                ExceptionHandler.log(e);
            }
        } else {
            saveDocumentAs();
        }
    }
/*

Когда файл выбран, необходимо:
23.2.1. Установить новое значение currentFile.
23.2.2. Сбросить документ.
23.2.3. Установить имя файла в заголовок у представления.
23.2.4. Создать FileReader, используя currentFile.
23.2.5. Вычитать данные из FileReader-а в документ document с помощью объекта класса HTMLEditorKit.
23.2.6. Сбросить правки (вызвать метод resetUndo представления).
23.2.7. Если возникнут исключения — залогируй их и не пробрасывай наружу.
Проверь работу пунктов меню Сохранить и Открыть.

*/
    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        jFileChooser.setFileFilter(new HTMLFileFilter());

        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                fileWriter.flush();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }

    }
}
