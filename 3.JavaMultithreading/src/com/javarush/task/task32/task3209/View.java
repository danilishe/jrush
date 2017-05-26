package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 Реализуем метод actionPerformed(ActionEvent actionEvent) у представления,
 этот метод наследуется от интерфейса ActionListener и будет вызваться при выборе пунктов меню,
 у которых наше представление указано в виде слушателя событий.
 19.1. Получи из события команду с помощью метода getActionCommand(). Это будет обычная строка.
 По этой строке ты можешь понять какой пункт меню создал данное событие.
 19.2. Если это команда «Новый«, вызови у контроллера метод createNewDocument().
 В этом пункте и далее, если необходимого метода в контроллере еще нет — создай заглушки.
 19.3. Если это команда «Открыть«, вызови метод openDocument().
 19.4. Если «Сохранить«, то вызови saveDocument().
 19.5. Если «Сохранить как…» — saveDocumentAs().
 19.6. Если «Выход» — exit().
 19.7. Если «О программе«, то вызови метод showAbout() у представления.
 Проверь, что заработали пункты меню Выход и О программе.


 */
public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();

    public void selectedTabChanged() {
        int selectedTab = tabbedPane.getSelectedIndex();
        if (selectedTab == 0) controller.setPlainText(plainTextPane.getText());
        if (selectedTab == 1) plainTextPane.setText(controller.getPlainText());
        resetUndo();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Новый".equals(command)) controller.createNewDocument();
        if ("Открыть".equals(command)) controller.openDocument();
        if ("Сохранить".equals(command)) controller.saveDocument();
        if ("Сохранить как...".equals(command)) controller.saveDocumentAs();
        if ("Выход".equals(command)) controller.exit();
        if ("О программе".equals(command)) showAbout();


    }
    public UndoListener getUndoListener() {
        return undoListener;
    }
    public boolean isHtmlTabSelected() {
        if (tabbedPane.getSelectedIndex() == 0) return true;
        return false;
    }
    public void update(){
        htmlTextPane.setDocument(
            controller.getDocument());
    }
    public void showAbout(){
       JOptionPane.showMessageDialog(this, "Какое-то собщение!", "О мегапроге...", JOptionPane.INFORMATION_MESSAGE);
    }
    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }
    public void setUndoListener(UndoListener undoListener) {
        this.undoListener = undoListener;
    }

    private UndoListener undoListener = new UndoListener(undoManager);

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        /*Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь.*/
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor(){
        /*6.1. Устанавливать значение «text/html» в качестве типа контента для компонента htmlTextPane.
Найди и используй подходящий метод.
6.2. Создавать новый локальный компонент JScrollPane на базе htmlTextPane.
6.3. Добавлять вкладку в панель tabbedPane с именем «HTML» и компонентом из предыдущего
пункта.
6.4. Создавать новый локальный компонент JScrollPane на базе plainTextPane.
6.5. Добавлять еще одну вкладку в tabbedPane с именем «Текст» и компонентом из
предыдущего пункта.
6.6. Устанавливать предпочтительный размер панели tabbedPane.
6.7. Создавать объект класса TabbedPaneChangeListener и устанавливать его в качестве слушателя изменений в tabbedPane.
6.8. Добавлять по центру панели контента текущего фрейма нашу панель с вкладками.
Получить панель контента текущего фрейма можно с помощью метода getContentPane(), его реализация унаследовалась от JFrame.*/
        htmlTextPane.setContentType("text/html");
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(600, 600));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }


    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init(){
        initGui();
        this.addWindowListener(new FrameListener(this));
        this.setVisible(true);
    }

    public void exit() {
        controller.exit();
    }



    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }
}
