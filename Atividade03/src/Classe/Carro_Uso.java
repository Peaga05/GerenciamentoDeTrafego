package Classe;

public class Carro_Uso {
	
	protected String Nome_Motorista;
	protected String Placa_Carro;
	protected String Data_Entrada;
	protected String Data_Saida;
	protected boolean dentro;
	
	public String getNome_Motorista() {
		return this.Nome_Motorista;
	}
	
	public void setNome_Motorista(String nome_Motorista) {
		this.Nome_Motorista = nome_Motorista;
	}
	
	public String getPlaca_Carro() {
		return this.Placa_Carro;
	}
	
	public void setPlaca_Carro(String placa_Carro) {
		this.Placa_Carro = placa_Carro;
	}
	
	public String getData_Entrada() {
		return this.Data_Entrada;
	}
	
	public void setData_Entrada(String data_Entrada) {
		this.Data_Entrada = data_Entrada;
	}
	
	public String getData_Saida() {
		return this.Data_Saida;
	}
	
	public void setData_Saida(String data_Saida) {
		this.Data_Saida = data_Saida;
	}

	public boolean isDentro() {
		return dentro;
	}

	public void setDentro(boolean dentro) {
		this.dentro = dentro;
	}
	
}
