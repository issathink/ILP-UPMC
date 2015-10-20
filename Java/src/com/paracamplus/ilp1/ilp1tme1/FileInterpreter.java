package com.paracamplus.ilp1.ilp1tme1;

import java.io.File;

import com.paracamplus.ilp1.interpreter.test.InterpreterTest;

public class FileInterpreter {

	public static void main(String[] args) {

		File file = new File(args[0]);
		InterpreterTest t = new InterpreterTest(file);

		try {
			t.processFile();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
