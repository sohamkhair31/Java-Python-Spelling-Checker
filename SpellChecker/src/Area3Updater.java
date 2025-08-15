import javax.swing.*;
import java.awt.*;

public class Area3Updater  {
    private String text;
    public final TextArea TxtArea3 = new TextArea();

    public void Updater$3(TextArea textArea,JPanel panel)
    {
        textArea.setText(text);
        System.out.println("Updated Mistakes");
    }
    public void setText$Area3(String txt)
    {
        this.text = txt;
    }
}
