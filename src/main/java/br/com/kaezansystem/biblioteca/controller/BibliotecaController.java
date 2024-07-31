package br.com.kaezansystem.biblioteca.controller;


import br.com.kaezansystem.biblioteca.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BibliotecaController {
	
	

	@FXML
	private Button bntAddLivro;

	@FXML
	private Button bntRemover;

	@FXML
	private TextField inputPesquisa;

	@FXML
	private TableView<?> tabelaDeLivros;

	@FXML
	private TableColumn<?, ?> tbAno;

	@FXML
	private TableColumn<?, ?> tbAutor;

	@FXML
	private TableColumn<?, ?> tbEditora;

	@FXML
	private TableColumn<?, ?> tbID;

	@FXML
	private TableColumn<?, ?> tbTitulo;
	
	
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
