package network;

import java.awt.Point;
import java.sql.Time;
import java.util.ArrayList;

import operator.Kievstar;
import operator.Life;
import operator.MTS;
import operator.Operator;
import consumer.Account;
import consumer.Consumer;
public class Network {
	private static ArrayList<Tower> towers=new ArrayList<>();
	private static Database db=Database.getInstance();
	private Network(){};
	public static  Tower searchTower(Account account){
		Tower res=null;
		for(Tower t:towers){
			if(t.containsOperator(account.getOperator())){
				int x1=account.getOwner().getCoordinates().x;
				int y1=account.getOwner().getCoordinates().y;
				int x2= t.getCoordinates().x;
				int y2= t.getCoordinates().y;
				double distance=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
				if(distance<=1000){
					res=t;
					break;
				}	
			}
		}
		return res;
	}
	public static boolean registerInNetwork(Account account){
		Tower t=searchTower(account);
		if(t!=null){
			if(db.register(account,t))
				return true;
		}
		return false;
	}
	public static void addTower(Tower...tower){
		for(Tower t:tower)
			towers.add(t);
	}
	public void sendSMS(){
		
	}
	public static void commutateWith(Account sender,Account receiver,Object context){
		Ñonformity res=db.search(receiver);
		Ñonformity sen=db.search(sender);
		if(res!=null && sen!=null){
			if(context.getClass()==String.class){
				if(res.getTower().addConnection() && sen.getTower().addConnection()){
					System.out.println("SEND SMS << "+sender.getNumber()+"  TO >> "+receiver.getNumber());
					receiver.acceptSMS((String) context);
					res.getTower().deleteConnection();
					sen.getTower().deleteConnection();
				}
			}
			else if(context.getClass()==Integer.class){
					if(receiver.isFree() && res.getTower().addConnection() && sen.getTower().addConnection()){
						sender.setBusy();
						receiver.setBusy();
						System.out.println("CONNECTION SUCCESSFUL FROM "+sender.getNumber()+"  TO >> "+receiver.getNumber());
						System.out.println("CALL FROM << "+sender.getNumber()+"  TO >> "+receiver.getNumber());
						try {
							Thread.sleep(((Integer)context)*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						sender.setFree();
						receiver.setFree();
						res.getTower().deleteConnection();
						sen.getTower().deleteConnection();
						System.out.println("CALL FROM << "+sender.getNumber()+"  TO >> "+receiver.getNumber()+" ENDED");
					}else{
						System.out.println("NETWORK IS OVERLOADED OR RECEIVER IS BUSY "+receiver.getNumber());
					} 
			    } 
		}
	}
	public static void main(String...args){
		Operator[] operators=new Operator[]{new MTS(),new Life(),new Kievstar()};
		Account a=new Account(operators[0],700,"095-194-0910");
		Account b=new Account(operators[1],700,"093-123-44-44");
		Account c=new Account(operators[2],700,"067-456-89-01");
		Consumer Eugene=new Consumer(new Point(10, 10));
		Consumer Anna=new Consumer(new Point(30, 10));
		Consumer Ivan=new Consumer(new Point(20, 10));
		a.setOwner(Anna);
		b.setOwner(Eugene);
		c.setOwner(Ivan);
		Anna.addAccount(a);
		Anna.addContact(c,b);
		Eugene.addAccount(b);
		Eugene.addContact(a,c);
		Ivan.addAccount(c);
		Ivan.addContact(a,b);
		Tower t=new Tower(new Point(20,20));
		t.addOperator(operators[0],operators[1],operators[2]);
		addTower(t);
		Eugene.start();
		Anna.start();
		Ivan.start();
	}
	

}
