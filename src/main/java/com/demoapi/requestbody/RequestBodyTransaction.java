package com.demoapi.requestbody;

public class RequestBodyTransaction {

	private long amount;
	private long trxTypeId;
	private String usersId;
	
	public long getTrxTypeId() {
		return trxTypeId;
	}
	public void setTrxTypIde(long trxTypeId) {
		this.trxTypeId = trxTypeId;
	}
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "RequestBodyTransaction [amount=" + amount + ", trxTypeId=" + trxTypeId + ", usersId=" + usersId + "]";
	}
	
	
	
	
}
