package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teste {

	public static void main(String[] args) {
		String regex = "^[A-Z]{1,1}[a-z]{3,10}\\s[A-Z]{1,1}[a-z]{3,10}\\s[\\w]{3,10}?";
		String teste = "Victor Alves Mende";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(teste);
		boolean resultadoVerificacao = matcher.matches();

		System.out.println(resultadoVerificacao);
		
	}

}
