package br.com.kaezansystem.biblioteca.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.kaezansystem.biblioteca.Livro;
import br.com.kaezansystem.biblioteca.DB.ConnectDB;
import br.com.kaezansystem.biblioteca.interfaces.AtualizarTabelaLista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BibliotecaController implements AtualizarTabelaLista{
	
	

	@FXML
	private Button bntAddLivro;

	@FXML
	private Button bntRemover;

	@FXML
	private TextField inputPesquisa;

	@FXML
	private TableView<Livro> tabelaDeLivros;

	@FXML
	private TableColumn<Livro, Integer> tbAno;

	@FXML
	private TableColumn<Livro, String> tbAutor;

	@FXML
	private TableColumn<Livro, String> tbEditora;

	@FXML
	private TableColumn<Livro, Integer> tbID;

	@FXML
	private TableColumn<Livro, Integer> tbTitulo;
	
	private ObservableList<Livro> livros = FXCollections.observableArrayList();
	
	private ConnectDB connection = new ConnectDB();
	
	
	@FXML
	public void initialize() {
		carregarDadosNaTabela();	
		
	}
	
	@FXML
	public void carregarDadosNaTabela() {
		livros.clear();
		try {
			Connection conn = connection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM livros");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editora = rs.getString("editora");
				Integer ano = rs.getInt("ano");
				
				livros.add(new Livro(id, titulo, autor, editora, ano));
			}
			
			
			tabelaDeLivros.setItems(livros);
			
			tbID.setCellValueFactory(new PropertyValueFactory<>("id"));
			tbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
			tbAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
			tbEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
			tbAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Janela de adicionar um novo livro
	@FXML
	public void addLivro() {
		try {
			//Parent root = FXMLLoader.load(getClass().getResource("/views/TelaAdicionarLivro.fxml"));
			FXMLLoader root =  new FXMLLoader(getClass().getResource("/views/TelaAdicionarLivro.fxml"));
			Parent telaAdicionar = root.load();
			
			NovoLivroController adicaoLivro = root.getController();
			adicaoLivro.setAtualizarTabelaListener(this);
			
			
			Stage telaAddLivro = new Stage();
			
			telaAddLivro.initModality(Modality.APPLICATION_MODAL);
			telaAddLivro.setTitle("Adicionar novo livro");
			telaAddLivro.setResizable(false);
			telaAddLivro.setScene(new Scene(telaAdicionar));
			telaAddLivro.show();	
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	public void removelivro() {
		Livro livroRemover = tabelaDeLivros.getSelectionModel().getSelectedItem();
		
		if(livroRemover == null) {
			JOptionPane.showMessageDialog(null, "Selecione um livro para remover" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}else {
			try {
				Connection conn = connection.getConnection();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM livros WHERE id = ?");
				ps.setInt(1, livroRemover.getId());
				ps.executeUpdate();
				
				carregarDadosNaTabela();
				
				JOptionPane.showMessageDialog(null, "Livro removido com sucesso" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
				ps.close();
				conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void atulizarTabela() {
		carregarDadosNaTabela();
	}
	
	

}
