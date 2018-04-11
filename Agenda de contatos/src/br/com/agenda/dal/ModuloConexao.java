/**
 * 
 */
package br.com.agenda.dal;

import java.sql.*; // importar recursos sql

/**
 * @author Eduardo Diniz
 * Modulo responsavel pela conex�o com o banco de dados
 */
public class ModuloConexao {
	// criando um m�todo est�tico com retorno usando a classe Connection que faz
	// parte do pacote java.sql.*
	public static Connection conector() {
		// criando uma variavel especial referenciada(tipada) pela classe Connection
		// para estabelecer a conex�o com o banco atribuimos o valor null para n�o
		// estabelecer uma conex�o sem a devida permiss�o
		Connection conexao = null;
		// Criando uma variavel do tipo String e atribuindo a ela o driver
		// correspondente ao tipo de banco (n�o esquecer de importar antes este driver)
		String driver = "com.mysql.jdbc.Driver";
		// Criando uma variavel do tipo string e atribuindo a ela o caminho para chegar
		// ao banco de dados

		// 10.26.49.49 --> Ip do servidor do banco de dados
		// 3306 --> porta padr�o do mysql
		// agenda --> nome do banco de dados que vai ser usado
		// ?useSSL=false --> n�o usar criptografia na transferencia de dados
		String url = "jdbc:mysql://10.26.49.49:3306/agenda?useSSL=false";

		// criando duas variaveis para autenticac�o no banco de dados
		String user = "admin";
		String password = "123456";
		
		//estabelecendo a conex�o
		//sempre tratar exce��es ao tentar conectar o banco de dados
		//try --> na tentativa de estabelecer a conex�o 
		try {
			//se tudo ok conectar
			Class.forName(driver); //use driver de conexao
			//a linha abaixo atribui os parametros necessarios para a conex�o
			conexao = DriverManager.getConnection(url, user, password);
			return conexao; // abre a conex�o com o banco quando solicitado 
		} catch (Exception e) {
			//se ocorrer uma exce��o
			System.out.println(e); 
			return null; //se ocorrer erro retorne null, conex�o permanece null
		}
	}
}
