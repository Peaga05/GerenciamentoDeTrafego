import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classe.Carro_Uso;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PaginaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPlaca;
	private JTable tblDados;
	List<Carro_Uso> CarroList = new ArrayList<Carro_Uso>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaPrincipal frame = new PaginaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaginaPrincipal() {
		setTitle("Controle de Veiculos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(33, 28, 334, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome do Condutor:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(33, 11, 165, 14);
		contentPane.add(lblNome);
		
		JLabel lblPlaca = new JLabel("Placa do Carro:");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPlaca.setBounds(33, 62, 110, 14);
		contentPane.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setColumns(10);
		txtPlaca.setBounds(33, 77, 334, 20);
		contentPane.add(txtPlaca);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(33, 125, 532, 150);
		contentPane.add(scrollPane);
		
		tblDados = new JTable();
		tblDados.setEnabled(false);
		//Criando modelo da tabela
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Placa do Carro");
		modelo.addColumn("Data de Entrada");
		modelo.addColumn("Data de Saida");
		tblDados.setModel(modelo);
		scrollPane.setViewportView(tblDados);
		
		JButton btnInserir = new JButton("Marca Entrada");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MsgErro = "";
				//Verificando se existem campos vazios
				if(txtNome.getText().isEmpty() || txtNome.getText().equals(" ")) {
					MsgErro += "Nome \n";
				}
				if(txtPlaca.getText().isEmpty()  || txtPlaca.getText().equals(" ")) {
					MsgErro += "Placa";
				}
				
				if(!MsgErro.isEmpty()) {
					JOptionPane.showMessageDialog(btnInserir,
							"Por favor preencha os campos abaixo:\n" + MsgErro,
							"Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					//Verificando se esse carro já está na tabela
					Carro_Uso carro = new Carro_Uso();
					carro.setNome_Motorista(txtNome.getText().toUpperCase());
					carro.setPlaca_Carro(txtPlaca.getText().toUpperCase());
					for(Carro_Uso temp: CarroList) {
						if(temp.getPlaca_Carro().toUpperCase().equals(carro.getPlaca_Carro()) && temp.isDentro() == true){
							MsgErro = "Esse carro já está no campus!";
							break;
						}
					}
					if(!MsgErro.isEmpty()) {
						JOptionPane.showMessageDialog(btnInserir,
								MsgErro,
								"Erro", JOptionPane.ERROR_MESSAGE);
						txtNome.setText("");
						txtPlaca.setText("");
					}else {
						//Adicionando carro caso não esteja na lista
						Date dataHoraAtual = new Date();
						String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
						String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
						carro.setData_Entrada(hora + " - " + data);
						carro.setDentro(true);
						CarroList.add(carro);
						modelo.addRow(new Object[] {carro.getNome_Motorista(), carro.getPlaca_Carro(), carro.getData_Entrada()});
						tblDados.setModel(modelo);
						txtNome.setText("");
						txtPlaca.setText("");
						JOptionPane.showMessageDialog(btnInserir,
								"Entrada do carro confirmada",
								"Sucesso", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			}
		});
		btnInserir.setBounds(388, 27, 177, 23);
		contentPane.add(btnInserir);
		
		JButton btnFinalizar = new JButton("Marca Saída");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtPlaca.setText("");
				
				int posicao = tblDados.getSelectedRow();
				if(posicao < 0){
					//Verificando se o usuario selecionou uma linha da tabela
					JOptionPane.showMessageDialog(btnInserir,
							"Selecione uma linha da tabela para adicionar a hora de saida",
							"Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					//Confirmação se o usuario deseja mesmo marca a saida
					int opcao = JOptionPane.showConfirmDialog(btnFinalizar,"Deseja mesmo confirmada a saída do carro?", 
						"Marca saída", JOptionPane.YES_NO_OPTION ,JOptionPane.INFORMATION_MESSAGE,null);
				
					if(opcao == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(btnFinalizar, "Operação cancelada!", "Marca saída", 
							JOptionPane.ERROR_MESSAGE,null);
					
					}else if(opcao == JOptionPane.YES_OPTION) {
					
						if(CarroList.get(posicao).getData_Saida() != null) {
							JOptionPane.showMessageDialog(btnInserir,
								"Esse carro já saiu do campus!",
								"Erro", JOptionPane.ERROR_MESSAGE);
							
						}else {
							//Confirmando a saida 

							Date dataHoraAtual = new Date();
							String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
							String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
							CarroList.get(posicao).setData_Saida(hora + " - " + data);
							CarroList.get(posicao).setDentro(false);
							modelo.setNumRows(0);
							for(Carro_Uso temp: CarroList) {
								modelo.addRow(new Object[] {temp.getNome_Motorista(), temp.getPlaca_Carro(), 
										temp.getData_Entrada(), temp.getData_Saida()});
							}
							JOptionPane.showMessageDialog(btnInserir,
									"Saída confirmada!",
									"Sucesso", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		btnFinalizar.setBounds(388, 76, 177, 23);
		contentPane.add(btnFinalizar);
		
		//Menssagem de orientação 
		JLabel lblObs = new JLabel("*Selecione uma linha para poder marca a saída do veiculo");
		lblObs.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblObs.setHorizontalAlignment(SwingConstants.CENTER);
		lblObs.setBounds(43, 286, 522, 14);
		contentPane.add(lblObs);
		
		
	}
}
