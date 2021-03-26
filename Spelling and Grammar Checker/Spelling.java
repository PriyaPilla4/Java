import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import javafx.scene.control.TextArea;

public class Spelling {

	private HashSet<String> dictionary;  //hashset for dictionary
	private ArrayList<String> incorrectWords; 
	private ArrayList<String>  suggestions; 
	
	Spelling(){
		dictionary = new HashSet<String>();
		incorrectWords = new ArrayList<String>();
        suggestions = new ArrayList<String>();		
	}
	
	//setters and getters for instance variables
	public void setDictionary(HashSet<String> dictionary) {
		this.dictionary = dictionary;
	}
	
	public void setIncorrectWords(ArrayList<String> incorrectWords) {
		this.incorrectWords = incorrectWords;
	}
	
	public void setSuggestions(ArrayList<String>  suggestions) {
		this.suggestions = suggestions;
	}
	
	public HashSet<String> getDictionary(){
		return dictionary;
	}
	
	public ArrayList<String> getIncorrectWords(){
		return incorrectWords;
	}
	
	public ArrayList<String> getSuggestions(){
		return suggestions;
	}
	
	//opens Words.txt, reads the words and hashes them into a hash container
    public void makeDictionary() throws Exception { 
    
    	File file = new File("Words.txt");
   	 	@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
   	 	String st; 
   	 	
   	 	//putting words into hashset
   	 	while ((st = br.readLine()) != null) { 
   	 		getDictionary().add(st); 
   	 	}
    }
    
    public void generateSuggestions(TextArea textArea){
   	 
   	 	String text = textArea.getText();
        String[] words = text.split("\\W+");
       
        getIncorrectWords().clear();
        getSuggestions().clear();
        
        //check each word in text area for misspelling
        for(int i = 0; i < words.length; i++) {
       	 	boolean wordFound = false;
       	 
       	 	//check word with each word in the dictionary
       	 	for(String s : getDictionary()) {
       	 		if(words[i].equalsIgnoreCase(s)) { 
       	 			wordFound = true;
       	 		}
       	 	}
       	 
       	 	if (wordFound == false) {
       	 	
       	 		boolean duplicateWord = false;
       		 
       	 		//makes sure misspelled word is not already found
       	 		for(String s : getIncorrectWords()) { 
       	 			if(words[i].equalsIgnoreCase(s)) {
       	 				duplicateWord = true;
       	 			}
       	 		}
       		 
       	 		if(duplicateWord == false) {
       	 		getIncorrectWords().add(words[i]);
       	 			String suggestionsList = "";
       					 
       	 			//calls functions to get suggestion words and adds them to a string
       	 			if(!(oneLetterMissing(words[i])).isEmpty()) {
       	 				for(String s1 : oneLetterMissing(words[i])) { 
       	 					suggestionsList = suggestionsList + s1 + "\n";     			
       	 				}
       	 			}

       	 			if(!(oneLetterAdded(words[i])).isEmpty()) {
       	 				for(String s1 : oneLetterAdded(words[i])) { 
       	 					suggestionsList = suggestionsList + s1 + "\n";   			
       	 				}
       	 			}
           		 
       	 			if(!(twoLettersReversed(words[i])).isEmpty()) {
       	 				for(String s1 : twoLettersReversed(words[i])) { 
       	 					suggestionsList = suggestionsList + s1 + "\n"; 			
       	 				}
       	 			}
           		 
       	 			//puts the string of suggestions for the misspelled word in array list
       	 			getSuggestions().add(suggestionsList);
       	 			suggestionsList = "";
       	 		} 
       	  }
       }
    }
    
    //generates suggestions if one letter is missing
    public HashSet<String> oneLetterMissing(String word) {
   	 
   	 	String alphabet[] = {"a", "b", "c", "d", "e", "f", "g", "h",
   			 "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
   			 "t", "u", "v", "w", "x", "y", "z"};
   	 	char[] wordChar = new char[word.length()];
   	 	HashSet<String>  wordSuggestions = new HashSet<String>(); 
   	 
   	 	//Copy character by character into array 
        for (int i = 0; i < word.length(); i++) { 
            wordChar[i] = word.charAt(i); 
        }
        
        //iterates letters in alphabet
        for(int i = 0; i <  alphabet.length; i++) { 
       	 
        	//iterates letter's new position in word
        	for(int j = 0; j < wordChar.length+1; j++){ 
       		 	String newWord = "";
       		 
       		 	//puts letter in the word at that position
       		 	for(int k = 0; k < wordChar.length+1; k++){ 
       			 
       		 		if(k == j) { 
       		 			if(k == wordChar.length) { //for insertion at the end of the word
       		 				newWord = newWord + String.valueOf(alphabet[i]);
       		 			}else {
       		 				newWord = newWord + String.valueOf(alphabet[i]);
       		 				newWord = newWord + String.valueOf(wordChar[k]);
       		 			}
       				 
       		 		}else {
       		 			if(k != wordChar.length) {
       		 				newWord = newWord + String.valueOf(wordChar[k]);
       		 			}
       		 		}
       		 	}
       		 
       		 	//if newWord matches a word in dictionary, adds it to wordSuggestions
       		 	for(String s : getDictionary()) { 
       		 		if(newWord.equalsIgnoreCase(s)) { 
       		 			wordSuggestions.add(newWord); 
       		 		}        			
       		 	}        		 
        	}
       }
   	 	return wordSuggestions;
    }
    
    //generates words if one letter is added
    public HashSet<String>  oneLetterAdded(String word) {
   	
   	 	char[] wordChar = new char[word.length()]; 
   	 	HashSet<String>  wordSuggestions = new HashSet<String>(); 
   	  
        // Copy character by character into array 
        for (int i = 0; i < word.length(); i++) { 
            wordChar[i] = word.charAt(i); 
        } 
  
        //scan through the word deleting each of the letters in turn
        for(int i = 0; i < wordChar.length; i++) {
       	 	String newWord = "";
       	 	for(int j = 0; j < wordChar.length; j++){
       	 		if(!(j == i)) {
       	 			newWord = newWord + String.valueOf(wordChar[j]);
       	 		}
       	 	}
       	
       	 	//if newWord matches a word in dictionary, adds it to wordSuggestions
       	 	for(String s : getDictionary()) {
       	 		if(newWord.equalsIgnoreCase(s)) {  
       	 			wordSuggestions.add(newWord); 
       	 		}
       	 	}
        }
        
   	 return wordSuggestions;
    }
    
    //generates words if two letters reversed
    public HashSet<String> twoLettersReversed(String word) {
   	 
   	 	char[] wordChar = new char[word.length()]; 
   	 	HashSet<String>  wordSuggestions = new HashSet<String>(); 
 
        for(int i = 0; i < word.length() - 1; i++) {
       	 
        	//Copy character by character into array
       	 	for (int j = 0; j < word.length(); j++) { 
                wordChar[j] = word.charAt(j); 
            } 
       	 
       	 	//swap letters
       	 	char temp = wordChar[i]; 
            wordChar[i] = wordChar[i+1]; 
            wordChar[i+1] = temp; 
            
            String newWord = toString(wordChar);
            
            //if newWord matches a word in dictionary, adds it to wordSuggestions
            for(String s : getDictionary()) {
       		 	if(newWord.equalsIgnoreCase(s)) {  
       		 		wordSuggestions.add(newWord); 
       		 	}
       	 	}
       } 	 
   	 return wordSuggestions;
    }
    
    //to convert char array to string
    public static String toString(char[] charArray){ 
        String string = new String(charArray); 
        return string; 
    }
}
