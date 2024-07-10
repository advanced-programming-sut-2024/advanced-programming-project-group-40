package views.ViewController;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TerminalController {
    public TextArea area;

    public void initialize() {
        area.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                int caretPosition = area.getCaretPosition();
                String currentText = area.getText();
                int nextLineIndex = currentText.indexOf('\n', caretPosition);
                if (nextLineIndex == -1) nextLineIndex = currentText.length();
                String textToInsert = "\ninvalid command\n";
                String newText = currentText.substring(0, caretPosition) + textToInsert + currentText.substring(caretPosition);
                area.setText(newText);
                area.positionCaret(caretPosition + textToInsert.length());
                event.consume();
            }
        });
    }
}

