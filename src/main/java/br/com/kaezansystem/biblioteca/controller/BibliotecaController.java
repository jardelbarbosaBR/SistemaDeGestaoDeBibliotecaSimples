package br.com.kaezansystem.biblioteca.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.kaezansystem.biblioteca.Livro;
import br.com.kaezansystem.biblioteca.Main;
import br.com.kaezansystem.biblioteca.DB.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BibliotecaController {
	
	

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
		tabelaDeLivros.setItems(livros);
		
		tbID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		tbAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		tbEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
		tbAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		
		carregarDadosNaTabela();
	}
	
	@FXML
	public void carregarDadosNaTabela() {
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
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	@FXML
	public void addLivro() {
		try {
			Main telaAddLivro = new Main();
			telaAddLivro.telaAddLivro();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@FXML
	public void removelivro() {
		
	}
	
	

}
