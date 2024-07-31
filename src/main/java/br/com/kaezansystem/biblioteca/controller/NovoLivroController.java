package br.com.kaezansystem.biblioteca.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import br.com.kaezansystem.biblioteca.DB.ConnectDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NovoLivroController {
	
	 
	 @FXML
	 private Button bntCancelar;

	 @FXML
	 private Button btnAdicionar;

	 @FXML
	 private TextField inputAno;

	 @FXML
	 private TextField inputAutor;

	 @FXML
	 private TextField inputEditora;

	 @FXML
	 private TextField inputTitulo;
	 
	 private ConnectDB connection = new ConnectDB();
	 
	 
	 
	 // Filtro de entrada para aceitar so numeros no campo inputAno
	 @FXML
	 public void initialize() {
		 inputAno.textProperty().addListener((observable, oldValue, newValue)->{
			 inputAno.setText(newValue.replaceAll("[^\\d]", ""));
		 });
	 }
	 
	 //Adcionar um novo livro ao banco de dados
	 @FXML
	 public void addNovoLivro() {
		 String titulo = inputTitulo.getText();
		 String autor = inputAutor.getText();
		 String editora = inputEditora.getText();
		 Integer ano = Integer.parseInt(inputAno.getText());
		 
		 if (titulo.isEmpty() || autor.isEmpty() || editora.isEmpty())  {
			 JOptionPane.showMessageDialog(null, "Todos os campos tem que esta preenchido" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
		 }else {
			 try {
				 Connection con = connection.getConnection();
				 PreparedStatement ps = con.prepareStatement("INSERT INTO livros (titulo, autor, editora, ano) VALUES(?, ?, ?, ?)");
				 ps.setString(1, titulo);
				 ps.setString(2, autor);
				 ps.setString(3, editora);
				 ps.setInt(4, ano);
				 ps.executeUpdate();
				 
				 JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
				 
				 ps.close();
				 con.close();
			 }catch (Exception e) {
				 JOptionPane.showMessageDialog(null, e , "Aviso", JOptionPane.INFORMATION_MESSAGE);
			 }finally {
				 inputTitulo.clear();
				 inputAutor.clear();
				 inputEditora.clear();
				 inputAno.clear();
				 
			 }
		 }
	 }
}
