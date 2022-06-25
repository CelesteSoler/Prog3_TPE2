package Prog3_TPE2;
//package tp2Esp;

import java.util.Iterator;

public class IteradorAdyacentes<String> implements Iterator<String> {
	
	private Iterator<Arco<String>> it;
	
	

	public IteradorAdyacentes(Iterator<Arco<String>> it) {
		super();
		this.it = it;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return it.hasNext();
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return it.next().getVerticeDestino();
	}

}
