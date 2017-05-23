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
 * Created by Данил on 20.05.2017.
 * 3.1. Добавь и проинициализируй поля в классе представления:
 3.1.1. JTabbedPane tabbedPane — это будет панель с двумя вкладками.
 3.1.2. JTextPane htmlTextPane — это будет компонент для визуального редактирования html.
 Он будет размещен на первой вкладке.
 3.1.3. JEditorPane plainTextPane — это будет компонент для редактирования html в виде текста,
 он будет отображать код html (теги и их содержимое).
 */
public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public boolean isHtmlTabSelected() {
        if (tabbedPane.getSelectedIndex() == 0) return true;
        return false;
    }

    /*114.3. Добавь в представление метод update(), который должен получать документ у контроллера и устанавливать его в панель
редактирования htmlTextPane.
14.4. Добавь в представление метод showAbout(), который должен показывать диалоговое окно с информацией о программе.
Информацию придумай сам, а вот тип сообщения должен быть JOptionPane.INFORMATION_MESSAGE.
*/
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

    public void selectedTabChanged() {}

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }
}
