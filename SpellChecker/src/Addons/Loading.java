package Addons;

import javax.swing.*;

public class Loading extends JFrame {
    public Colors colors =new Colors();
    private JProgressBar Bar = new JProgressBar();
    private void CreateLoader()
    {
        setUndecorated(true);
        setSize(200,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(200,300);
        mainPanel.setLayout(null);
        mainPanel.setLocation(0,0);
        setVisible(true);
        add(mainPanel);
        SwingUtilities.invokeLater(()->{
            addLoadingBar(mainPanel);});

    }
    private void addLoadingBar(JPanel panel)
    {
        int val = 0;

        Bar.setSize(180,30);
        Bar.setLocation(20,260);
        Bar.setVisible(true);
        Bar.setValue(val);
        Bar.setForeground(colors.buttonRed);
        Bar.setBackground(colors.specialTextArea);

        panel.add(Bar);
        while(val<=100)
        {
            val+=10;
            try{
                Thread.sleep(1000);
                Bar.setValue(val);
                repaint();
            }
            catch (InterruptedException interruptedException)
            {
                System.out.println("ProgressBar Failed");
                return;
            }
        }
    }

    public static void main(String[] args) {
        Loading load =new Loading();
        SwingUtilities.invokeLater(()->{
            load.CreateLoader();
        });
    }

}