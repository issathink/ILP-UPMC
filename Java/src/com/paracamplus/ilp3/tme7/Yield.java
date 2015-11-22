package com.paracamplus.ilp3.tme7;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.Primitive;


public class Yield extends Primitive {
	

	public Yield(String name) {
		super(name);
		System.out.println("Create Yield");
	}

	@Override
	public int getArity () {
        return 0;
    }
	
	
	@Override
	public Object apply() throws EvaluationException {
		
		System.out.println("Apply yield");
		Thread thread = Thread.currentThread();
		if(thread instanceof CoroutineInstance) {
			try {
				((CoroutineInstance) thread).getSemaphoreMain().release(); /* Lache le main */
				((CoroutineInstance) thread).getSemaphoreCor().acquire(); /* Et s'endormir */
			} catch (InterruptedException e) {
				System.out.println("Tu peux pas yield si tu n'es pas dans une coroutine");
				e.printStackTrace();
			}
		} else 
			System.out.println("Ce message ne doit pas etre affiche.");
		
		return Boolean.FALSE;
	}

	//DABORD IL FAUT FAIRE LA SEMAPHORE ET ENSUITE IL FAUT GERER LE THREAD AFIN QUON LE RELEASE

}
