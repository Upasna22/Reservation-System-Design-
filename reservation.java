import java.util.*;
public class reservation{
	
	public static void main(String args[])
	{
		customer c1 = new customer(1,"Ted");
		customer c2 = new customer(2,"Jon");
		
		reservation_system rc = new reservation_system();
		rc.makereservation(11, 1, 2);
		rc.makereservation(16, 2, 4);
		rc.getreservation(1);
		rc.getreservation(2);
		rc.cancelreservation(1);
	}
}
class customer{
	private int custid;
	private String custname;
	//private String phone;
	
	public customer(int id,String name)
	{
		this.custid =id;
		this.custname = name;
	}
	
	public int getid()
	{
		return this.custid;
	}
	public String getname()
	{
		return this.custname;
	}
}

class table
{
	private int tid;
	private int size;
	private String status;
	
	public table(int id,int size)
	{
		this.tid = id;
		this.size = size;
		this.status ="free";
	}
	
	public int gettableid()
	{
		return this.tid;
	}
	public int getsize()
	{
		return this.size;
	}
	public String getstatus()
	{
		return this.status;
	}
	public void setstatus(String s)
	{
		this.status =s;
	}
	public table gettable(int id)
	{
		if(this.tid ==id) return this;
		return null;
	}
}

class reservation_class
{
	private int starttime;
	private int custid;
	private int tid;
	
	
	public reservation_class(int st,int cid, int tid)
	{
		this.starttime =st;
		this.custid =cid;
		this.tid = tid;
	}
	
	public int getstarttime()
	{
		return this.starttime;
	}
	public int getcustid()
	{
		return custid;
	}
	public int gettableid()
	{
		return this.tid;
	}
	public void returnreservation()
	{
		System.out.println("Reservation made by customer: "+ this.getcustid()+ " at time: "+this.getstarttime() +" for table :"+ this.tid);
	}
}
class reservation_system
{
	ArrayList<ArrayList<table>> tables; 
	Map<Integer,reservation_class> map; 
	public static final int nooftables =8;
	public static final int noofhours =10;
	
	public reservation_system()
	{
		tables = new ArrayList<ArrayList<table>>();
		map = new HashMap<Integer, reservation_class>();
		ArrayList<table> temp = new ArrayList<table>();
		for(int i =0;i<nooftables/2;i++)
		{
			temp.add(new table(i,2));
		}
		for(int j =nooftables/2;j<nooftables;j++)
		{
			temp.add(new table(j,4));
		}
		for(int k=0;k<noofhours;k++)
		{
			tables.add(k,temp);
		}
	}
	
	public void makereservation(int starttime, int cid, int size)
	{
		table t=findtable(starttime ,size);
		if(t!=null)
		{
			
			reservation_class r=new reservation_class(starttime,cid,t.gettableid());
			map.put(cid, r);
			

		}
	}
	public table findtable(int starttime, int size)
	{
		int index = starttime -10;
		ArrayList<table> temp = tables.get(index);
		for(table t:temp)
		{
			if(t.getstatus()=="free" && t.getsize() ==size)
			{   t.setstatus("occupied");
				return t;
			}
		}
		return null;
	}
	
	public void cancelreservation(int custid)
	{
		reservation_class r =map.get(custid);
		map.remove(custid);
	//	t.setstatus("free");
		System.out.println("Reservation make by: " +custid+ " has been cancelled");
				
	}
	
	public void getreservation(int custid)
	{
		map.get(custid).returnreservation();
		
	}
	
}