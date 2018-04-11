package br.com.agenda.formulario;

import java.sql.*;
import br.com.agenda.dal.ModuloConexao;

public class Teste {

	public static void main(String[] args) {
		Connection conexao = null;
		conexao = ModuloConexao.conector(); //chama o metodo que conecta com o banco
		if(conexao != null) {
			System.out.println("Conectado!");
		}else {
			System.out.println("Não conectado ! :(");
		}
	}
}
