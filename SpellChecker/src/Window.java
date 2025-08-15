
import py4j.GatewayServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.lang.reflect.InvocationTargetException;


public class Window extends JFrame {
    private final server server = new server();
    private final Colors colors = new Colors();
    private final TextArea Area1 = new TextArea();
    private final Area2Updater Area2 = new Area2Updater();
    private final Area3Updater Area3 = new Area3Updater();
    private final JPanel mainPanel = new JPanel();
    private final JButton Check = new JButton("Check");
    public Window(boolean isVisible)
    {
        setUndecorated(true);
        setSize(500,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(isVisible);
        addControl(this);
        addMainPanel(this);
        SwingUtilities.invokeLater(()->{
            addTextArea$1(mainPanel);
            addTextArea$2(mainPanel);
            addTextArea$3(mainPanel);
            addCheckButton(mainPanel);
            ListenerForChecker(Check,Area1);
            Thread CheckResult = new Thread(new Runnable() {
                private volatile boolean running = true;
                @Override
                public void run() {
                    while(running)
                    {
                        try{
                            Thread.sleep(200);
                            Area2Updater updater = new Area2Updater();
                            if(server.CorrectedStringData!=null)
                            {
                                Area2.TxtArea2.setText(server.CorrectedStringData);
                                if(!String.valueOf(server.result).equals(null))
                                {
                                    Area3.TxtArea3.setText(server.result.toString());
                                }
                                else{
                                    Area3.TxtArea3.setText("NO MISTAKES FOUND !!");
                                }
                                server.GateWayControl.shutdown();
                                running = false;
                            }
                        }
                        catch (InterruptedException e)
                        {
                            System.out.println("THREAD FAILED");
                            running = false;
                        }
                    }
                }
                public void stop()
                {
                    running = false;
                }
            });
            CheckResult.start();
        });
    }

    private void addControl(JFrame frame)
    {
        JLabel Exit = new JLabel("X");
        JLabel MiniMize = new JLabel("-");
        JPanel Control = new JPanel();

        Control.setSize(500,35);
        Control.setLocation(0,0);
        Control.setBackground(colors.redPanel);
        Control.setLayout(null);

        Exit.setBounds(470,5,15,35);
        Exit.setFont(new Font("Calibri",Font.BOLD,22));
        Exit.setForeground(new Color(255,255,255));
        MiniMize.setBounds(445,5,15,35);
        MiniMize.setFont(new Font("Calibri",Font.BOLD,35));
        MiniMize.setForeground(new Color(255,255,255));

        ControlButtonAnimation(Exit,this);
        ControlButtonAnimation(MiniMize,this);
        Control.add(MiniMize);
        Control.add(Exit);
        frame.add(Control);
        frame.addWindowStateListener(e -> {
            if ((e.getNewState() & Frame.NORMAL) == Frame.NORMAL) {
                Exit.setBounds(470,5,15,35);
                MiniMize.setBounds(445,5,15,35);
                Control.repaint();
            }
        });

    }
    private void ControlButtonAnimation(JLabel label,JFrame frame)
    {
        final Color[] prev = new Color[1];
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                prev[0] = label.getForeground();
                if(label.getText().equals("X")){
                    label.setForeground(colors.hoverClose);
                }
                else if(label.getText().equals("-"))
                {
                    label.setForeground(colors.hoverMinimize);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                label.setForeground(prev[0]);
            }
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(label.getText().equals("X")){
                    System.exit(0);
                }
                else if(label.getText().equals("-"))
                {
                    frame.setState(1);
                }
            }
        });
    }
    private void addMainPanel(JFrame frame){
        mainPanel.setSize(500,565);
        mainPanel.setLocation(0,35);
        mainPanel.setBackground(colors.whiteBG);
        mainPanel.setLayout(null);
        frame.add(mainPanel);
    }
    private void addTextArea$1(JPanel panel)
    {
        JLabel UserIn = new JLabel("USER INPUT : ");
        UserIn.setFont(new Font("Calibri",Font.BOLD,20));
        UserIn.setBounds(50,20,150,25);
        Area1.setSize(400,100);
        Area1.setLocation(50,50);
        Area1.setBackground(colors.textAreaBG);
        Area1.setForeground(colors.textDark);
        Area1.setFont(new Font("Calibri",Font.BOLD,20));
        panel.add(Area1);
        panel.add(UserIn);
    }
    private void addTextArea$2(JPanel panel)
    {
        JLabel Output = new JLabel("OUTPUT : ");
        Output.setBounds(50,390,150,25);
        Output.setFont(new Font("Calibri",Font.BOLD,20));
        Area2.TxtArea2.setSize(400,120);
        Area2.TxtArea2.setLocation(50,430);
        Area2.TxtArea2.setBackground(colors.textAreaBG);
        Area2.TxtArea2.setForeground(colors.textDark);
        Area2.TxtArea2.setFont(new Font("Calibri",Font.BOLD,20));
        Area2.TxtArea2.setEditable(false);
        panel.add(Area2.TxtArea2);
        panel.add(Output);
    }
    private void addTextArea$3(JPanel panel)
    {
        JLabel Mistakes = new JLabel("Mistakes : ");
        Mistakes.setBounds(50,220,150,25);
        Mistakes.setFont(new Font("Calibri",Font.BOLD,25));
        Area3.TxtArea3.setSize(400,100);
        Area3.TxtArea3.setLocation(50,250);
        Area3.TxtArea3.setFont(new Font("Calibri",Font.BOLD,20));
        panel.add(Area3.TxtArea3);
        panel.add(Mistakes);
    }

    private void addCheckButton(JPanel panel)
    {
        Check.setSize(100,30);
        Check.setFont(new Font("Calibri",Font.BOLD,15));
        Check.setLocation(200,160);
        Check.setBackground(colors.buttonBlue);
        Check.setForeground(colors.whiteBG);
        Check.setFocusable(false);
        panel.add(Check);
    }
    private void ListenerForChecker(JButton button,TextArea textArea)
    {
        button.addActionListener(e->{
            StringSetter(textArea);
        });
    }
    private void StringSetter(TextArea textArea)
    {
        String TempData = textArea.getText();
        if(!TempData.isEmpty())
        {
            this.server.getStringData(TempData);
            this.server.GateWayControl = new GatewayServer(server);
            this.server.GateWayControl.start();
            System.out.println("Server Started");
        }
        else {
            System.out.println("Data Not Found!");
        }
    }

    public void setOutput(String string)
    {
        Area2.TxtArea2.setText(string);
    }
    public static void main(String[] args) {
        new Window(true);
    }
}
