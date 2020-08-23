package com.bank.Bank_JPA;

import java.util.Scanner;

import com.bank.DAO.Dao;
import com.bank.beans.Account;
import com.bank.beans.Loan;
import com.bank.service.Service;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String name, address, loan_id, loan_type,id;
		double deposit, withdraw, loan_amount;
		
		Dao dao = new Dao();
		Service vd = new Service();
		
		
		while (true) {
			System.out.println("1. Open Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Apply Loan");
			System.out.println("5. Show Account Details");
			System.out.println("6. Pay Loan");
			System.out.println("7. Show Loan Details");
			System.out.println("8. Exit");

			Scanner scanner = new Scanner(System.in);
			System.out.println("enter option");
			int option = scanner.nextInt();

			switch (option) {

			case 1: {
				while (true) {
					System.out.println("Enter your name (First letter Capital)");
					name = scanner.next();
					if (vd.nameValidation(name)) {
						System.out.println("Enter Account Id");
						id = scanner.next();
						if(vd.idValidation(id)) {
						System.out.println("Enter address");
							address = scanner.next();
							System.out.println("Enter deposit");
							deposit = scanner.nextDouble();
							Account acc1 = new Account(id,name, address, deposit);
							dao.createAccount(acc1);
							break;
					}
		           else {
						System.out.println("Please enter valid id");
				}
				}
					else
					{
						System.out.println("Please enter valid name");
					}
				}
				break;
			}
			case 2: {
				System.out.println("Enter account Id and amount");
				id = scanner.next();
				deposit = scanner.nextDouble();
				System.out.println("New balance after deposit is: "+ dao.deposit(id, deposit));
				break;
			}
			
			case 3: {
				System.out.println("Enter account Id and amount");
				id = scanner.next();
				withdraw = scanner.nextDouble();
				System.out.println("New balance after withdrawl is: " +dao.withdraw(id, withdraw));
				break;
			}
			
			case 4:
			{
				while(true) {
				System.out.println("Enter Name");
				name = scanner.next();
				if(vd.nameValidation(name)) {
				System.out.println("Enter account id");
				id = scanner.next();
				if(vd.idValidation(id)) {
				System.out.println("Enter Address");
				address = scanner.next();
				System.out.println("Enter deposit ");
				deposit = scanner.nextDouble();
				System.out.println("Enter Loan Id");
			    loan_id = scanner.next();
				System.out.println("Select Loan Type :");
				loan_type = scanner.next();
				System.out.println("Enter loan amount");
				loan_amount = scanner.nextDouble();
				Loan loan = new Loan(loan_id,loan_type, loan_amount);
				loan.setAccountId(id);
				loan.setAccountName(name);
				loan.setAddress(address);
				loan.setDepositAmount(deposit);
				dao.applyLoan(loan);
				break;
			}
				else
					System.out.println("Please enter valid id");
				}
				else
					System.out.println("Please enter valid name");
			}
			
			}
			
			case 5:
			{
				System.out.println("Enter account Id");
				id = scanner.next();
				Account acc = dao.getAccountDetails(id);
				System.out.println(acc);
				break;
			}
			
			case 6:
			{
				System.out.println("Enter loan Id");
				loan_id = scanner.next();
				System.out.println("Enter amount to pay");
				double amount = scanner.nextDouble();
				double balance = dao.payLoan(amount,loan_id);
				System.out.println("New Balance is : "+balance);
				break;
			}
			
			case 7:
			{
				System.out.println("Enter loan Id");
				loan_id = scanner.next();
				Loan loan = dao.getLoanDetails(loan_id);
				System.out.println(loan);
				break;
			}
			
			case 8: 
			{
				System.exit(0);
			}
			
			default:
				System.out.println("Please enter choice in range (1-7)");
			}
			
			}
    }
}
