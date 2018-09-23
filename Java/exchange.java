import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class exchange implements Runnable{
	static Thread y;
public static void main(String[] arg)
{y = new Thread(new exchange(),"exchange");
	try {

y.start();

}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

@Override
public void run() {
	// TODO Auto-generated method stub
	
	try(BufferedReader ob1 = new BufferedReader(new FileReader("calls.txt"))) {
	    //String string1 = new String();
	    String linereading = null;
	   // HashMap<calling, ArrayList<calling>> allval = new HashMap<>();
	    HashMap<String,calling> objectstorage=new HashMap<>();
        ArrayList<ArrayList<String>> lol=new ArrayList<ArrayList<String>>();
        ArrayList<calling> alist=new ArrayList<calling>();
	    while ((linereading = ob1.readLine())!= null) {
	    ArrayList<String> blist1=new ArrayList<String>();
	    String line0=linereading.replaceAll("[\\{\\[\\]\\.\\}]","");
	       String line00=line0.replaceAll(" ","");
	       String line1=line00.trim();
	       String[] dat = line1.split(",");
	       for(int i=0;i<dat.length;i++)
	       {
	    	   blist1.add(dat[i].trim());
	       }
	       lol.add(blist1);
	       for(int i=0;i<dat.length;i++)
	       {  
	    	   calling dd=new calling(dat[i]);
	    	      if(i==0)
	    	      {
	    	    objectstorage.put(dat[i].trim(),dd);
	    		 alist.add(dd); 
	    		  
	    	   }
	    	   
	    	
	       }
	       }
	   
	    for(int i=0;i<lol.size();i++)  
	      {ArrayList<calling> fin=new ArrayList<calling>();
	    	  for(int j=1;j<lol.get(i).size();j++)    
	    	  {  
	    		
	    		fin.add(objectstorage.get((lol.get(i).get(j)).trim()));
	    		
	    		  
	    		  
	    	  }
	    	   objectstorage.get(lol.get(i).get(0)).setHuuh(fin); 
	     }
	    System.out.println("**Calls to made**");
	    for(int i=0;i<lol.size();i++) 
	      {ArrayList<String> fin=new ArrayList<String>();
	    	  for(int j=1;j<lol.get(i).size();j++)   
	    	  {  
	    		 
	    		fin.add(lol.get(i).get(j));
	    		
	    		  
	    		  
	    	  }
	    	  System.out.println(lol.get(i).get(0)+": "+fin);
	    	    
	     }
		 System.out.println("");
	    try {
	    	
	    	for(int i=0;i<alist.size();i++)
	    	{	
	    		
	    		alist.get(i).td.start();
	    	}
	    	
	    	y.join(4500);
	    	System.out.println("Master has received no reply for 1.5 seconds,ending...");
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    
	    }catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}    
	
	void printSys(String st1,String st2,String s3,Long s4)
	{
		System.out.println(st1+" received "+st2+" from "+s3+" ["+s4+"]");
	}
}



