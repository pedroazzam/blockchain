package me.pedroazzam.blockchain.models;

import java.util.Date;

import me.pedroazzam.blockchain.utilities.StringUtil;

public class Block {
	
	public String hash;
	public String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 01/01/1970.
	private int nonce;
	
	
	//Block Constructor.
	public Block(String data, String previousHash) {
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp) +
				data
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace("\0", "0"); //Create a string with difficulty * "0"
		
		while (hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined! : " + hash);
	}

}
