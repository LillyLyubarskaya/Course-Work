package network;
import java.util.ArrayList;
import consumer.Account;

public class Database {
	private static volatile Database instance;
	private Database (){};
	private volatile ArrayList<Ñonformity> records=new ArrayList<>();
	public synchronized boolean register(Account account,Tower tower){
		boolean res=false;
		if(search(account)==null){
			records.add(new Ñonformity(account,tower));
			res=true;
		}
		else
		{
			System.out.println(" >> ACCOUNT IS ALREADY REGISTERED!");
		}
		return res;
	}
	public synchronized void disconnect(Account account){
		if(records.remove(search(account)))
		System.out.println("ACCOUNT DISCONNECTED");
	}
	public static Database getInstance(){
		if (instance == null)
        {
            instance = new Database();
        }
        return instance;
	}
    public synchronized Ñonformity search(Account a){
    	Ñonformity res=null;
    	for(Ñonformity c:records){
    		if(c.getAccount()==a)
    			res=c;
    	}
    	return res;
    }
}
