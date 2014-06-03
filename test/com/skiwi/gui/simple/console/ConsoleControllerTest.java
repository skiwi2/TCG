
package com.skiwi.gui.simple.console;

import java.io.IOException;
import java.io.UncheckedIOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import static org.junit.Assert.*;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

/**
 *
 * @author Frank van Heeswijk
 */
public class ConsoleControllerTest extends GuiTest {
    @Override
    protected Parent getRootNode() {
        try {
            return FXMLLoader.load(ConsoleController.class.getResource("console.fxml"));
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
    
    @Test
    public void testEmptyTextFieldOnStart() {
        TextField textField = find("#textField");
        assertEquals("", textField.getText());
    }
    
    @Test
    public void testTextFieldHasFocusOnStart() {
        TextField textField = find("#textField");
        type("test");
        assertEquals("test", textField.getText());
    }
    
    @Test
    public void testLoopbackFromTextFieldToTextArea() {
        TextArea textArea = find("#textArea");
        TextField textField = find("#textField");
        Platform.runLater(textArea::clear);
        
        String testString = "veryspecial343434368sentencewithrandomstuff00---";
        type(testString).push(KeyCode.ENTER);
        assertEquals("", textField.getText());
        assertTrue(textArea.getText().contains(testString));
    }
}