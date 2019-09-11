package me.pedroazzam.blockchain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

import me.pedroazzam.blockchain.models.Block;


public class BlockChain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//add our blocks to the blockchain ArrayList:
		blockchain.add(new Block("First block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add( new Block("Second block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add( new Block("Third block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		
	}
	
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		
		//loop through blockchain to check hashes:
		for(int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			//compare registered hash and calculated hash:
			if (!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Curent Hashes not equal");
				return false;
			}
			
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equals");
				return false;
			}
		}
		return true;
	}

}
