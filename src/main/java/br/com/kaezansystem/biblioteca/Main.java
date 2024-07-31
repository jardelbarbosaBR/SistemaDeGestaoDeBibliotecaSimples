package br.com.kaezansystem.biblioteca;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage TelaPrincipal;

	
	
	public void telaAddLivro() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/views/TelaAdicionarLivro.fxml"));
		Stage telaAddLivro = new Stage();
		telaAddLivro.initModality(Modality.WINDOW_MODAL);
		telaAddLivro.initOwner(TelaPrincipal);
		telaAddLivro.setTitle("Adicionar novo livro");
		telaAddLivro.setResizable(false);
		telaAddLivro.setScene(new Scene(root));
		telaAddLivro.show();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		TelaPrincipal = primaryStage;
		FXMLLoader root = new FXMLLoader(getClass().getResource("/views/TelaBiblioteca.fxml"));
		BorderPane telaPrincipal = root.load();
		Scene cenaPrincipal = new Scene(telaPrincipal);
		TelaPrincipal.setScene(cenaPrincipal);
		TelaPrincipal.setTitle("Sistema de Biblioteca Simples - SBS");
		TelaPrincipal.show();
	}

	
	public static void main(String[] args) {
		launch(args);
		
	}

	
}
