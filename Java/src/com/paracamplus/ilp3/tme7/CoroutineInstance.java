package com.paracamplus.ilp3.tme7;

import java.util.List;
import java.util.concurrent.Semaphore;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.Invocable;

public class CoroutineInstance extends Thread {

	private Object function;
	private List<Object> arguments;
	private Interpreter interpreter;
	private Semaphore semMain, semCor;
	private boolean isFinished;

	public CoroutineInstance(Object function, List<Object> arguments,
			Interpreter interpreter) {
		this.function = function;
		this.arguments = arguments;
		this.interpreter = interpreter;
		this.isFinished = false;
		semMain = new Semaphore(0);
		semCor = new Semaphore(0);
		System.out.println("Coroutine cree au calme.");
	}

	@Override
	public void run() {
		System.out.println("Start running coroutine.");
		try {
			semCor.acquire();
			
			if(function instanceof Invocable) {
				System.out.println(function + " ; is " + (function==null) + "; args : " + arguments);
				((Invocable) function).apply(interpreter, arguments.toArray());
			}
				 
			setFinisihed(true);
			semMain.release();
		} catch (EvaluationException e) {
			System.out.println("what da fuck");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("I'm not supposed to be interrupted.");
			e.printStackTrace();
		}
	}

	public Semaphore getSemaphoreMain() {
		return semMain;
	}

	public Semaphore getSemaphoreCor() {
		return semCor;
	}

	public boolean getFinished() {
		return isFinished;
	}
	
	public void setFinisihed(boolean isFinished) {
		this.isFinished = isFinished;
	}

}
