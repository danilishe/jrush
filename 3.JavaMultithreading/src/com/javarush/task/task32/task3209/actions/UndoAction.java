package com.javarush.task.task32.task3209.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import com.javarush.task.task32.task3209.View;

/**
 * Created by Данил on 21.05.2017.
 */
public class UndoAction  extends AbstractAction {
    private View view;
    public UndoAction(View view) {
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();
    }
}
