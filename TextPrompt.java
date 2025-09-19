import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

public class TextPrompt extends JLabel {
    private JTextComponent component;
    private boolean showPromptOnce;
    private int focusLost;

    public TextPrompt(String text, JTextComponent component) {
        this.component = component;
        setText(text);
        setFont(component.getFont());
        setForeground(Color.GRAY);
        setBorder(new EmptyBorder(component.getInsets()));
        setHorizontalAlignment(SwingConstants.LEADING);

        component.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                checkForPrompt();
            }
            public void focusLost(FocusEvent e) {
                checkForPrompt();
            }
        });

        component.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                checkForPrompt();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                checkForPrompt();
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                checkForPrompt();
            }
        });

        component.setLayout(new BorderLayout());
        component.add(this);
        checkForPrompt();
    }

    private void checkForPrompt() {
        if (component.getText().length() == 0 && (!showPromptOnce || focusLost == 0)) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    }
}
