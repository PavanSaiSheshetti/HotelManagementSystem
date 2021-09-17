package com.revature.hms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Wallet;
import com.revature.hms.repository.WalletRepository;


@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	public WalletRepository walletRepository;
	
	@Autowired
	public static JavaMailSender mailSender;

	public WalletServiceImpl(JavaMailSender mailSender)
	{
		WalletServiceImpl.mailSender = mailSender;
	}
	
	@Override
	public boolean addWallet(Wallet wallet) {
		// TODO Auto-generated method stub
		walletRepository.save(wallet);
		return true;
	}

	@Override
	public boolean deleteWallet(String username) {
		// TODO Auto-generated method stub
		walletRepository.deleteById(username);
		;
		return true;
	}

	@Transactional
	@Override
	public boolean addMoneyForCancellation(String username, int price) {
		// TODO Auto-generated method stub
		walletRepository.addMoneyforCancellation(username, price);
		return true;
	}

	@Override
	@Transactional
	public boolean deductMoney(String username, int money) {
		// TODO Auto-generated method stub
		walletRepository.deductMoneyFromWallet(username, money);
		return true;
	}

	@Override
	public void sendMail(String from, String receiver, String subject, String message) {
		// TODO Auto-generated method stub
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(receiver);
		mail.setSubject(subject);
		mail.setText(message);
		mailSender.send(mail);	
	}

	@Override
	public Wallet getCustomerBalance(String customerUserName) {
		Wallet wallet= walletRepository.findByCustomerUserName(customerUserName);
		
		return wallet;
	}

}
