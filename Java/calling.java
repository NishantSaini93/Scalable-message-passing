import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class calling implements Runnable {
	String callername;
	ArrayList<calling> huuh;
	int tot;
	Thread td;
	Object ob1;
	
	public calling(String callername)
	{  this.callername = callername;
	   td = new Thread(this,callername);
      		
		
	}
	
	
	


	public ArrayList<calling> getHuuh() {
		return huuh;
	}


	public void setHuuh(ArrayList<calling> huuh) {
		this.huuh = huuh;
	}


	public String getName() {
		return callername;
	}


	
public void recieveCall(String ss)
{
	try {
	exchange aa=new exchange();
	Long x=System.currentTimeMillis();
	//String ww=this.callername+" recieved intro from "+ss+" ["+x+"]";
    
    aa.printSys(this.callername,"intro",ss,x);
    
    Random timersleeping = new Random();
    int  howmuch = timersleeping.nextInt(100) + 1;
    Thread.sleep(howmuch);
	replyOfCall(ss,x);
    
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	
}
public void replyOfCall(String ss,Long x)
{
//String ww=ss+" recieved reply from "+this.callername+" ["+x+"]";
exchange aa=new exchange();
aa.printSys(ss,"reply",this.callername,x);
	
	
}



	@Override
	public void run() {
		try
		{
		
			
			
		Random timersleeping = new Random();
        int  howmuch = timersleeping.nextInt(100) + 1;
	
		for(int i=0;i<huuh.size();i++)
		{
			String ss=this.callername;
			 Thread.sleep(howmuch);
			huuh.get(i).recieveCall(ss);
		}
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
		td.join(2000);
		System.out.println("Process "+this.callername+" has received no calls for 1 second,ending... ");
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}}
