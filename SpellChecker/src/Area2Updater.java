import javax.swing.*;
import java.awt.*;

public class Area2Updater{
    private String text;
    public boolean isArea2Updated = false;
    public final TextArea TxtArea2 = new TextArea();
    public void Updater$2(TextArea textArea ,JPanel panel)
    {
        textArea.setText(text);
        System.out.println("UPDATED");
    }
    public void setText$Area2(String txt)
    {
        this.text = txt;
    }
}
