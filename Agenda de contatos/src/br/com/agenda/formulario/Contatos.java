package br.com.agenda.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Toolkit;

import java.sql.*;
import br.com.agenda.dal.ModuloConexao;

public class Contatos extends JFrame {

	//variaveis e objetos usados para conexão com o banco
	//criando uma variavel para estabelecer a conexão com o banco
	Connection conexao = null;
	//executa comando sql
	//pst nome da variavel que dá apoio para as querys sql
	PreparedStatement pst = null;
	//resultado dos comandos sql
	//rs é o nome da variavel de apoio
	// TODAS as variavel são referenciadas pelas classes principais 
	ResultSet rs = null;
	
	// Metodos //
	
	// Metodo pesquisar contato
	private void limpar() {
		txt_id.setText(null);
		txt_nome.setText(null);	
		txt_telefone.setText(null);
		txt_email.setText(null);
	}
	private void pesquisar() {
		try {
			String pesquisar = "select * from tb_contatos where id = ?";
			pst = conexao.prepareStatement(pesquisar);
			//substituir o argumento ? pelo conteudo da caixa de testo txt_id
			pst.setString(1, txt_id.getText());
			rs = pst.executeQuery();
			if(rs.next()) {
				txt_nome.setText(rs.getString(2));	
				txt_telefone.setText(rs.getString(3));
				txt_email.setText(rs.getString(4));
			}else {
				limpar();
				//mensagem para o usuario
				JOptionPane.showMessageDialog(null, "Id inexiste no banco de dados");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	// Metodo de adicionar contato

	private void adicionar() {
		String add = "INSERT INTO tb_contatos(id, nome, fone, email) VALUES(?,?,?,?)";
		try {
			//ve se o id já está sendo utilizado!
			
			String pesquisar = "select * from tb_contatos where id = ?";
			pst = conexao.prepareStatement(pesquisar);
			//substituir o argumento ? pelo conteudo da caixa de testo txt_id
			pst.setString(1, txt_id.getText());
			rs = pst.executeQuery();
			//se o id já estiver sendo utilizado mostra a mensagem caso contrario cadastre o usuario em questão
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Id já está sendo utilizado!");
			}else {

				pst = conexao.prepareStatement(add);
				pst.setString(1, txt_id.getText());
				pst.setString(2, txt_nome.getText());
				pst.setString(3, txt_telefone.getText());
				pst.setString(4, txt_email.getText());
				int adicionado = pst.executeUpdate();
				if(adicionado > 0) {
					JOptionPane.showMessageDialog(null, txt_nome.getText() + " adicionado(a) com sucesso!");
					limpar();
				}else {
					JOptionPane.showMessageDialog(null, "Não foi possivel adicionar o contato");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Fim dos metodos //
	
	
	private JPanel contentPane;
	private JTextField txt_id;
	private JTextField txt_nome;
	private JTextField txt_telefone;
	private JTextField txt_email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contatos frame = new Contatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor.
	 */
	public Contatos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Contatos.class.getResource("/br/com/agenda/icones/if_contacts_1055082.png")));
		setTitle("Agenda de contatos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 255);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 35, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 63, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(10, 88, 46, 14);
		contentPane.add(lblFone);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 116, 46, 14);
		contentPane.add(lblEmail);
		
		txt_id = new JTextField();
		txt_id.setBounds(66, 32, 185, 20);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		txt_nome = new JTextField();
		txt_nome.setBounds(66, 60, 227, 20);
		contentPane.add(txt_nome);
		txt_nome.setColumns(10);
		
		txt_telefone = new JTextField();
		txt_telefone.setBounds(66, 85, 227, 20);
		contentPane.add(txt_telefone);
		txt_telefone.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setBounds(66, 113, 227, 20);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		JButton btn_add = new JButton("");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btn_add.setToolTipText("Adicionar");
		btn_add.setIcon(new ImageIcon(Contatos.class.getResource("/br/com/agenda/icones/if_user-add_216490.png")));
		btn_add.setBounds(84, 141, 64, 64);
		contentPane.add(btn_add);
		
		JButton btn_edit = new JButton("");
		btn_edit.setToolTipText("Editar");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_edit.setIcon(new ImageIcon(Contatos.class.getResource("/br/com/agenda/icones/if_user_close_edit_103748.png")));
		btn_edit.setBounds(158, 141, 64, 64);
		contentPane.add(btn_edit);
		
		JButton btn_remove = new JButton("");
		btn_remove.setToolTipText("Remover");
		btn_remove.setIcon(new ImageIcon(Contatos.class.getResource("/br/com/agenda/icones/if_user_close_remove_103763.png")));
		btn_remove.setBounds(229, 141, 64, 64);
		contentPane.add(btn_remove);
		
		JButton btn_busca = new JButton("");
		btn_busca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btn_busca.setToolTipText("Buscar");
		btn_busca.setIcon(new ImageIcon(Contatos.class.getResource("/br/com/agenda/icones/if_11_Search_106236.png")));
		btn_busca.setBounds(10, 141, 64, 64);
		contentPane.add(btn_busca);
		
		JLabel lbl_banco = new JLabel("");
		lbl_banco.setToolTipText("Status do banco de dados");
		lbl_banco.setBounds(261, 19, 30, 30);
		contentPane.add(lbl_banco);
		
		//estabelecer a conexão com o banco na interface grafica dentro do construtor
		conexao = ModuloConexao.conector();
		
		if(conexao != null) {
			//a linha abaixo troca a imagem do icone da label
			lbl_banco.setIcon(new ImageIcon(Contatos.class.getResource("/br/com/agenda/icones/BancoON.png")));
		}else {
			lbl_banco.setIcon(new ImageIcon(Contatos.class.getResource("/br/com/agenda/icones/BancoOFF.png")));
		}
		
	}
}
