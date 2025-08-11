import py4j.*;

import java.util.ArrayList;
import java.util.Scanner;
public class server{
    Area2Updater area2Updater = new Area2Updater();
    public String CorrectedStringData =null;
    public static String StringData=null;
    public boolean resultChanged = false;
    public  GatewayServer GateWayControl;
    public ArrayList<String> result = new ArrayList<>();
    public void getRes(String str)
    {
        result.add(str);
    }
    public void showRes()
    {
        System.out.println("RESULT : "+result);
    }
    public void getStringData(String string)
    {
        StringData = string;
    }
    public void ResultChanged(boolean res)
    {
        resultChanged = res;
        System.out.println("DATA : "+CorrectedStringData);
        area2Updater.TxtArea2.setText(CorrectedStringData);
    }
    public void CorrectedString(String str)
    {
        CorrectedStringData = str;
    }

    public String setString()
    {
        return StringData;
    }

}
