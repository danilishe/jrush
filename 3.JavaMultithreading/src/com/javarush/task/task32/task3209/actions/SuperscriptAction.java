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
public class SuperscriptAction extends  StyledEditorKit.StyledTextAction {
    public SuperscriptAction() {
        super(StyleConstants.Superscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JEditorPane editor = getEditor(actionEvent);
        if (editor != null) {
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editor).getInputAttributes();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
            StyleConstants.setSuperscript(simpleAttributeSet, !StyleConstants.isSuperscript(mutableAttributeSet));
            setCharacterAttributes(editor, simpleAttributeSet, false);
        }
    }
}
