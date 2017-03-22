package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	private List <RichWord> words;
	private List <String> dizionario;
	private int errori=0;
	
	public int getErrori() {
		return errori;
	}

	public Dictionary() {
		this.words = new ArrayList <RichWord>();
		this.dizionario= new ArrayList<String>();
	}

	public void loadDictionary(String language){

			if(language.equals("Italian")){
				try {
					FileReader fr = new FileReader("rsc/Italian.txt"); 
					BufferedReader br = new BufferedReader(fr);
					String word;
					while ((word = br.readLine()) != null) {
					dizionario.add(word);
					} br.close();
					} catch (IOException e){
					System.out.println("Errore nella lettura del file");
					}}
			
			if(language.equals("English"))
				try {
					FileReader fr = new FileReader("rsc/English.txt"); 
					BufferedReader br = new BufferedReader(fr);
					String word;
					while ((word = br.readLine()) != null) {
					dizionario.add(word);
					} br.close();
					} catch (IOException e){
					System.out.println("Errore nella lettura del file");
					}
			
		
	}
	public List<RichWord> spellCheck2(List<String> inputTextList){
		List<RichWord> parole = new LinkedList<RichWord>();
		boolean trovato=false;
		
		for (String s : inputTextList){
			
			int inizio = 0;
			int fine = dizionario.size() -1;
			
			while(trovato == false && inizio < fine){
				int medio = (inizio + fine)/2;
				
				if(dizionario.get(medio).equals(s)){
					trovato = true;
				}
				else if(dizionario.get(medio).compareTo(s) >0){
					fine = medio -1;
				}
				else{
					inizio = medio +1;
				}
			}
			
			if(!trovato){
				RichWord r = new RichWord(s,false);
				parole.add(r);
			}


				
		}
		
		return parole;
	}


	public void addParola(RichWord parola){
		words.add(parola);
	}
	public List<RichWord> spellCheckText(List<String> inputTextList)
	{ List <RichWord> parole = new LinkedList <RichWord>();
		for(String s: inputTextList)
			if(!dizionario.contains(s))
			{
				RichWord r= new RichWord(s, true);
				parole.add(r);
		}
return parole;
	}

	public void clear() {
	dizionario.clear();
		
	}
}
