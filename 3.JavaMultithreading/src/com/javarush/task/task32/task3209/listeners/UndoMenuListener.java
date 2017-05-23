package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Created by Данил on 21.05.2017.
 * 10.1.1. Представление View view.
 10.1.2. Пункт меню «Отменить» JMenuItem undoMenuItem.
 10.1.3. Пункт меню «Вернуть» JMenuItem redoMenuItem.
 10.2. Реализуй конструктор UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem), о
 н должен инициализировать поля класса.
 10.3. Реализуй метод menuSelected(MenuEvent menuEvent). Он будет вызываться перед показом меню. Он должен:
 10.3.1. Спрашивать у представления можем ли мы отменить действие с помощью метода boolean canUndo(). П
 ока у представления нет такого метода, поэтому добавь заглушку, которая всегда возвращает false.
 10.3.2. Делать доступным или не доступным пункт меню undoMenuItem в зависимости от того, что нам вернуло представление.

 Подсказка: используй метод setEnabled().

 10.3.3. Аналогично поступи и для пункта меню redoMenuItem, добавив метод-заглушку canRedo() в представление.
 Запусти программу и убедись, что пункты меню Отменить и Вернуть недоступны.
 */
public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    public UndoMenuListener() {

    }

    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem) {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        if (view.canUndo()) undoMenuItem.setEnabled(true);
        else undoMenuItem.setEnabled(false);
        if (view.canRedo()) redoMenuItem.setEnabled(true);
        else redoMenuItem.setEnabled(false);
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
