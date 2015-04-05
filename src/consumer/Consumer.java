package consumer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Consumer extends Thread{
	private Point coordinates;
	private ArrayList<Account> contacts=new ArrayList<>();
	private ArrayList<Account>accounts=new ArrayList<>();
	public Consumer(Point coordinates){
		setCoordinates(coordinates);
	}
	public void addAccount(Account... a){
		for(Account ac:a){
			accounts.add(ac);
		}
	}
	public void addContact(Account...a){
		for(Account ac:a){
			contacts.add(ac);
		}
	}
	ArrayList<Account> getContacts() {
		return contacts;
	}
	void setContacts(ArrayList<Account> contacts) {
		this.contacts = contacts;
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}
	public Account getRandomAccount(ArrayList<Account> a) {
		Random random=new Random();
		int index=random.nextInt(a.size());
		return a.get(index);
	}
	void setAcounts(ArrayList<Account> acounts) {
		this.accounts = acounts;
	}
	
	public void transfer(int money,Account sender,Account recepient){
		if(sender.getOperator()==recepient.getOperator()){
			if(sender.getBalance()-money<0){
				System.out.println(" >> NOT ENOUGH MONEY FOR TRANSFER!");
			}else{
				sender.changeBalane(-money);
				recepient.changeBalane(money);
				System.out.println("TRANSFER PERFORMED SUCCESSFULLY!!!");
			}
		}else{
			System.out.println(" >> TRANSFER CAN BE PERFORMED ONLY IN INTERNAL NETWORK!!!");
		}
	}
	public void run(){
		for(Account a:accounts){
			a.connect();
		}
		for(int i=0;i<100;i++){
			Account sender=getRandomAccount(accounts);
			Account receiver=getRandomAccount(contacts);
			if(i%2==0){
				System.out.println("TRY TO CALL FROM "+sender.getNumber()+" TO "+receiver.getNumber());
				sender.communicate(receiver, new Integer(10));
			}else{
				System.out.println("TRY TO SEND SMS FROM "+sender.getNumber()+" TO "+receiver.getNumber());
				sender.communicate(receiver, "Hello.How are you?");
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	

}
