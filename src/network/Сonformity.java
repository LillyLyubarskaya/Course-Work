package network;
import consumer.Account;
public class �onformity {
	private Tower tower;
	private Account account;
	Tower getTower() {
		return tower;
	}
	void setTower(Tower tower) {
		this.tower = tower;
	}
	Account getAccount() {
		return account;
	}
	void setAccount(Account account) {
		this.account = account;
	}
	public  �onformity(Account account, Tower tower){
		setAccount(account);
		setTower(tower);
	}

}
