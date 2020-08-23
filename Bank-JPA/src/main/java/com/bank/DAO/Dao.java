package com.bank.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.bank.beans.Account;
import com.bank.beans.Loan;


public class Dao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersDB");
	EntityManager em = emf.createEntityManager();
	EntityTransaction trans = em.getTransaction();
	
	public void createAccount(Account account) {
		String id = account.getAccountId();
		String name = account.getAccountName();
		String address = account.getAddress();
		double deposit = account.getDepositAmount();
		trans.begin();
		account.setAccountId(id);
		account.setAccountName(name);
		account.setAddress(address);
		account.setDepositAmount(deposit);
		em.persist(account);
		trans.commit();
	}
	public double deposit(String id, double deposit) {
	
		double newBalance;
		Account account = em.find(Account.class, id);
		trans.begin();
	    newBalance = account.getDepositAmount() + deposit;
	    account.setDepositAmount(newBalance);
	    em.persist(account);
	    trans.commit();
		return newBalance;
		
	}

  public double withdraw(String id, double withdraw) {
	
	double newBalance;
	Account account = em.find(Account.class, id);
	trans.begin();
    newBalance = account.getDepositAmount() - withdraw;
    account.setDepositAmount(newBalance);
    em.persist(account);
    trans.commit();
	return newBalance;
	
}
  public void applyLoan(Loan loan) {
	  trans.begin();
	  em.persist(loan);
	  trans.commit();

}
public double payLoan(double amount, String id)
{
	double remLoan;
	double newBalance;
	Loan loan = em.find(Loan.class,id);
	trans.begin();
	remLoan = loan.getLoanAmount() - amount;
	newBalance = loan.getDepositAmount() - amount;
	loan.setDepositAmount(newBalance);
	em.persist(loan);
	trans.commit();
	System.out.println("Remaining loan is : "+ remLoan);
	return newBalance;
}

public Account getAccountDetails(String id) {
	
	Account account = em.find(Account.class, id);
	return account;
}

public Loan getLoanDetails(String id) {
	Loan loan = em.find(Loan.class, id);
	return loan;
	
}
}
