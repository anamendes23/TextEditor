package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if(sourceText == null) {
			throw new NullPointerException("Please enter a valid text source. Nulls are not accepted.");
		}

        if(sourceText.equals("")) return;

		String[] words = sourceText.split("[ ]+");
		starter = words[0];
		String prevWord = starter;

		for(int i = 1; i < words.length; i++) {
			if(listContainsWord(prevWord)) {
				int index = getIndexOfWord(prevWord);
				wordList.get(index).addNextWord(words[i]);
			}
			else {
				ListNode newNode = new ListNode(prevWord);
				newNode.addNextWord(words[i]);
				wordList.add(newNode);
			}

			prevWord = words[i];
		}

		if(listContainsWord(prevWord)) {
			int index = getIndexOfWord(prevWord);
			wordList.get(index).addNextWord(starter);
		}
		else {
			ListNode newNode = new ListNode(prevWord);
			newNode.addNextWord(starter);
			wordList.add(newNode);
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if(starter.equals("") || numWords == 0) return "";

		String currWord = starter;
		String output = starter;

		while(numWords > 1) {
			int index = getIndexOfWord(currWord);
			ListNode currNode = wordList.get(index);
			String w = currNode.getRandomNextWord(rnGenerator);
			output += " " + w;
			currWord = w;

			numWords--;
		}

		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList.clear();
		starter = "";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	private boolean listContainsWord(String word) {
		for(ListNode node : wordList) {
			if(node.getWord().equals(word))
				return true;
		}

		return false;
	}

	private int getIndexOfWord(String word) {
		for(int i = 0; i < getListSize(); i++) {
			ListNode node = wordList.get(i);
			if(node.getWord().equals(word))
				return i;
		}

		return -1;
	}

	public int getListSize() { return wordList.size(); }
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		//System.out.println(gen.getListSize());
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		//gen.train(textString2);
		//System.out.println(gen.getListSize());
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int index = generator.nextInt(nextWords.size());
	    return nextWords.get(index);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


