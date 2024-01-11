package com.demoapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoapi.model.Transaction;
import com.demoapi.model.Users;
import com.demoapi.repository.TransactionRepository;
import com.demoapi.repository.UsersRepository;
import com.demoapi.requestbody.RequestBodyTransaction;
import com.demoapi.responsebody.ResponseBodyTransaction;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController{
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UsersRepository userRepository;
	
	
	//controller add new transaction
	@PostMapping(value = "/addTransaction", produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseBodyTransaction addTransaction(@Valid @RequestBody RequestBodyTransaction trx, @RequestHeader (name = "Authorization") String token) {
		Transaction trxs = new Transaction();
		ResponseBodyTransaction responseBodyTransaction = new ResponseBodyTransaction();

		//find user by token jwt
		Optional<Users> user 			= userRepository.findByAccesToken(token);
		
		if(user.isPresent()) {
			trxs.setUser_id(user.get().getUser_id());
			trxs.setAmount(trx.getAmount());
			trxs.setTransaction_type_id(trx.getTrxTypeId());
			try {
				transactionRepository.save(trxs);
				responseBodyTransaction.setMessage("Transaksi Berhasil");
				responseBodyTransaction.setRespCode("00");
			} catch (Exception e) {
				// TODO: handle exception
				responseBodyTransaction.setMessage("Transaksi Gagal");
				responseBodyTransaction.setRespCode("05");
				e.printStackTrace();
			}
		}
		else {
			
			responseBodyTransaction.setMessage("Gagal");
			responseBodyTransaction.setRespCode("05");
			
		}
		
		return responseBodyTransaction;

	}
	
	//controller list all transaction
	@PostMapping(value = "/listTransaction", produces = "application/json")
	@ResponseBody
	public ResponseBodyTransaction listTransaction(@Valid @RequestHeader (name = "Authorization") String token) {
		ResponseBodyTransaction responseBodyTransaction = new ResponseBodyTransaction();
		//find user by token jwt
		Optional<Users> user 			= userRepository.findByAccesToken(token);
		
		if(user.isPresent()) {
			List<Transaction> listDataTransaction = new ArrayList<Transaction>();
			listDataTransaction = transactionRepository.findByUsersId(user.get().getUser_id());
			try {
				
				responseBodyTransaction.setMessage("Transaksi Berhasil");
				responseBodyTransaction.setRespCode("00");
				responseBodyTransaction.setData(listDataTransaction);
			} catch (Exception e) {
				// TODO: handle exception
				responseBodyTransaction.setMessage("Transaksi Gagal");
				responseBodyTransaction.setRespCode("05");
				responseBodyTransaction.setData(new ArrayList<>());
				e.printStackTrace();
			}
		}
		else {
			
			responseBodyTransaction.setMessage("Gagal");
			responseBodyTransaction.setRespCode("05");
			responseBodyTransaction.setData(new ArrayList<>());
			
		}
		
		return responseBodyTransaction;

	}

}
