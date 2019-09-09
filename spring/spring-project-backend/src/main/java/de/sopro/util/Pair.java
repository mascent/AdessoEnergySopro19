package de.sopro.util;

public class Pair<A, B> {
	
	A first;
	B second;
	
	public Pair(A fst, B snd){
		this.first = fst;
		this.second = snd;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

}
