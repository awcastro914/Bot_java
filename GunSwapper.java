import java.io.*;
import java.util.*;  
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.*;
import java.util.*;  
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GunSwapper{
    Robot robot = new Robot();
    String currentItem = "Tunguska";
    String ourItem = "";
    String stop = "";
    static int howmanytimes = 0;
    public static int skipWait =0;
    public static int linePrev;
    String passedItem = "";
    String killFiletxt = "";
    public static Queue<String> queue= new LinkedList<String>(); 
    //FILES
    public static String killFile = "C:\\Users\\blacktavius\\Desktop\\Bot\\KillFile.txt";
    public static String gunFile = "C:\\Users\\blacktavius\\Desktop\\Bot\\guns.txt";
    public static String gunListFile = "C:\\Users\\blacktavius\\Desktop\\Bot\\gunList.txt";
    public static String toText = "D:\\Steam\\SteamApps\\common\\Borderlands 2\\Binaries\\toText.txt";
    //FILES
    public static void main(String[] args) throws AWTException{
        writeFile(killFile,"");   // clears kill file
        int keepGoing = 1;
        while (keepGoing == 1){
         new GunSwapper();
         howmanytimes++; // not necesary
        }

}

public  GunSwapper() throws AWTException {
      if(skipWait == 0){
      for (int counter = 0; counter < 100; counter++){             // INCREASE 100 TO INCREASE TIME BETWEEN SWAPS
        ourItem = readFile(gunFile);
        killFiletxt = readFile(killFile);    // if it contains END it will end program
        if(((ourItem).equals(passedItem))==false){    // assures that we never get the same weapon twice in a row
        System.out.println(ourItem + " added to queue");
          passedItem = ourItem;
          queue.add(ourItem);                          /// if next is not our current we add it to queue
        }
         ourWait(140);
      }
      }
      if (new String("END").equals(killFiletxt)){
      System.exit(0);
      }
      boolean isPause =killFiletxt.equals("PAUSE");
      if(isPause == false){
      robot.setAutoWaitForIdle(true);
      System.out.println(Integer.toString(queue.size())+ " is size of queue");
      if(queue.peek() != null){
      String popGun = queue.poll();            //pops gun off of queue
      String[] itemParts = popGun.split(",");
      int lineMatch = readFileGun(gunListFile,itemParts[1]);   // attempts to find a match in the file to find gun location
      lineMatch = lineMatch -1;    
      //System.out.println(Integer.toString(lineMatch));
     // System.out.println(Integer.toString(linePrev));
      if((lineMatch >= 0) && (lineMatch != linePrev)){
        //where print to file goes
        String say = "say " + itemParts[1] + " was chosen by " + itemParts[0];
        writeFile(toText,say);
        if(lineMatch<77){                   //GUNS SECTION END
        currentItem = itemParts[1];
        Consol();
        GunSlot(lineMatch);
        skipWait = 0;
        System.out.println("GUNSLOT");
        }
        else if((lineMatch > 77) && (lineMatch < 92)){             // GUN SECTION END & SHIELD LINE START
        currentItem = itemParts[1];
        Consol();
        ShieldSlot(lineMatch-77);                                 //OFFSET FOR SHIELD
        skipWait = 0;
        System.out.println("GUNSLOT");
        }
        else{
        currentItem = itemParts[1];
        Consol();
        GrenadeSlot(lineMatch-92);                                 // OFFSET FOR GRENADES
        skipWait = 0;
        }

      }
      else{
        itemParts[1] = queue.peek();
        skipWait = 1;
        System.out.println("Gun Not On List");
      }
      linePrev = lineMatch;
      System.out.println(Integer.toString(howmanytimes));
      }
      else{
        skipWait =0;
      }
      }
  }



public static void writeFile(String path, String write){
             try{
        PrintWriter writer2 = new PrintWriter(path);
        writer2.println(write);
        writer2.close();
        		} 
        catch (IOException e) {
		  	e.printStackTrace();
	    	}
}

public String readFile(String path){
  		String line =  "";
  		try {
			File file = new File(path);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
      return line;
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    return line;
     // returns this if does not find
}


private int readFileGun(String path, String item){
      int lineNum =0;
  		try {
			File file = new File(path);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
      lineNum++;
      if(line.equals(item)){
        Integer.toString(lineNum);
        //System.out.println("It Matched Line " + lineNum);
        return lineNum;
      }
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    return 0; // returns this if does not find
}

private void Consol(){
      typeKey(KeyEvent.VK_I);
      ourWait(550);
      robot.keyPress(KeyEvent.VK_SHIFT);
      robot.keyPress(192);
      robot.keyRelease(192);
      robot.keyRelease(KeyEvent.VK_SHIFT);
      typeKey(KeyEvent.VK_UP);
      typeKey(KeyEvent.VK_ENTER);
      robot.keyPress(KeyEvent.VK_SHIFT);
      robot.keyPress(192);
      robot.keyRelease(192);
      robot.keyRelease(KeyEvent.VK_SHIFT);
}

private void GunSlot(int i){
      typeKey(KeyEvent.VK_I);
      ourWait(550);
           for(int j=0; j<5; j++){
      typeKey(KeyEvent.VK_UP);
      }
      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ENTER);
      for(int j=0; j<i; j++){
        typeKey(KeyEvent.VK_DOWN);
      }
      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ESCAPE);
}

private void ShieldSlot(int i){
      typeKey(KeyEvent.VK_I);
      ourWait(550);
      for(int j=0; j<5; j++){
      typeKey(KeyEvent.VK_UP);
      }
      for(int j=0; j<4; j++){
      typeKey(KeyEvent.VK_DOWN);
      }


      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ENTER);
      for(int j=0; j<i; j++){
        typeKey(KeyEvent.VK_DOWN);
      }
      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ESCAPE);
}

private void GrenadeSlot(int i){
      typeKey(KeyEvent.VK_I);
      ourWait(550);
     for(int j=0; j<5; j++){
      typeKey(KeyEvent.VK_UP);
      }
       for(int j=0; j<5; j++){
      typeKey(KeyEvent.VK_DOWN);
      }
      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ENTER);
      for(int j=0; j<i; j++){
        typeKey(KeyEvent.VK_DOWN);
      }
      typeKey(KeyEvent.VK_ENTER);
      typeKey(KeyEvent.VK_ESCAPE);
}



private void ourWait(int i){
         try {
    TimeUnit.MILLISECONDS.sleep(i);
     } catch (Exception e) {
         //TODO: handle exception
     }
}


private void typeKey(int i){
    robot.delay(1);
    robot.keyPress(i);
    robot.keyRelease(i);
}

  private void type(String s)
  {
    byte[] bytes = s.getBytes();
    for (byte b : bytes)
    {
      int code = b;
      // keycode only handles [A-Z] (which is ASCII decimal [65-90])
      if (code > 96 && code < 123) code = code - 32;
      robot.delay(40);
      robot.keyPress(code);
      robot.keyRelease(code);
    }
  }
  private void arrowType(int s){
robot.keyPress(KeyEvent.VK_ALT);
robot.keyRelease(KeyEvent.VK_ALT);
robot.delay(1); // seems to be required for the event to be registered
robot.keyPress(KeyEvent.VK_ALT);
robot.keyPress(s);
robot.keyRelease(s);
robot.keyRelease(KeyEvent.VK_ALT);
}
}

//set GD_Balance_HealthAndDamage.HealthAndDamage.Att_UniversalBalanceScaler:ConstantAttributeValueResolver_0 ConstantValue 1.4_