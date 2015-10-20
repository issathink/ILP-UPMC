package com.paracamplus.ilp1.ilp1tme1;

import java.io.File;

import com.paracamplus.ilp1.compiler.test.CompilerTest;

public class FileCompiler {

	public static void main(String[] args) {

		File file = new File(args[0]);
		CompilerTest t=new CompilerTest(file);

		try {
			t.processFile();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	
	}

}
