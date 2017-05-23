package com.javarush.task.task32.task3209.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/**
 * Created by Данил on 21.05.2017.
 *     public StrikeThroughAction() {
 super(StyleConstants.StrikeThrough.toString());
 }

 public void actionPerformed(ActionEvent actionEvent) {
 JEditorPane editor = getEditor(actionEvent);
 if (editor != null) {
 MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editor).getInputAttributes();
 SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
 StyleConstants.setStrikeThrough(simpleAttributeSet, !StyleConstants.isStrikeThrough(mutableAttributeSet));
 setCharacterAttributes(editor, simpleAttributeSet, false);
 }
 }
 */
public class SubscriptAction extends StyledEditorKit.StyledTextAction {

    public SubscriptAction() {
        super(StyleConstants.Subscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JEditorPane editorPane = getEditor(actionEvent);
        if (editorPane != null) {
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editorPane).getInputAttributes();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
            StyleConstants.setSubscript(simpleAttributeSet, !StyleConstants.isSubscript(mutableAttributeSet));
            setCharacterAttributes(editorPane, simpleAttributeSet, false);
        }
    }
}
