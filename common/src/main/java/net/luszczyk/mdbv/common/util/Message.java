package net.luszczyk.mdbv.common.util;

public class Message {
	
	private int status;
	private String msg;
	
	public Message() {
		super();
	}
	
	public Message(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
		

}
