package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {
	
	Scanner scanner;
	
	public IOConsole (Scanner scanner) {
		this.scanner = scanner;
	}
	
	@Override
    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }
    
	@Override
    public String leggiRiga() {
        return this.scanner.nextLine();
    }
}