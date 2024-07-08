package EasyNote;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AlunosController;
import controller.EmprestimosController;
import controller.EquipamentosController;
import model.entities.AlunosEntity;
import model.entities.EmprestimosEntity;
import model.entities.EquipamentosEntity;
import model.service.AlunosService;
import model.service.EmprestimosService;
import model.service.EquipamentosService;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField raAlunoEmp;
	private JTextField patrimonioEmp;
	private JTextField nomeALunoEmp;
	private JTextField cpfAlunoEmp;
	private JTextField cursoAlunoEmp;
	private JTextField marcaEquipEmp;
	private JTextField modeloEquipEmp;
	private JTextField nomeAluno;
	private JTextField dataAluno;
	private JTextField cpfAluno;
	private JTextField emailAluno;
	private JTextField raAluno;
	private JTextField userAluno;
	private JPasswordField senhaAluno;
	private JTextField patrimonioEquip;
	private JTextField dtAquiEquip;
	private JTextField marcaEquip;
	private JTextField modeloEquip;
	private JTextField obsEquip;
	private JTable tableAlunos;
	private JTable tableEquipamentos;
	private JTable tableEmprestimos;

	private AlunosService alunosService;
	private EquipamentosService equipamentosService;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		this.equipamentosService = new EquipamentosService();

		AlunosController controlAluno = new AlunosController();
		EquipamentosController controlEquip = new EquipamentosController();
		EmprestimosController controlEmp = new EmprestimosController();
		new EmprestimosController();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 474);
		contentPane = new JPanel(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 731, 418);
		contentPane.add(tabbedPane);

		JPanel emprestimo_1 = new JPanel();
		tabbedPane.addTab("Empréstimo", null, emprestimo_1, null);
		emprestimo_1.setLayout(null);

		

		JLabel raEmp = new JLabel("RA do Aluno:");
		raEmp.setBounds(10, 12, 75, 21);
		emprestimo_1.add(raEmp);

		raAlunoEmp = new JTextField();
		raAlunoEmp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				AlunosEntity alunoBanco = controlAluno.findRa(raAlunoEmp.getText());

				// SÓ MOSTRA SE O ALUNO ESTÁ ATIVO
				// ---------------------------------------------------------------------
				if (raAlunoEmp.getText().isEmpty() || alunoBanco.isAtivo() == false || alunoBanco == null) {
					return;
				} else {
					//btnCadAluno.setEnabled(false);
					nomeALunoEmp.setText(alunoBanco.getNome());
					cpfAlunoEmp.setText(alunoBanco.getCpf());
					cursoAlunoEmp.setText(alunoBanco.getCurso());
				}
			}
		});
		raAlunoEmp.setBounds(89, 12, 127, 20);
		emprestimo_1.add(raAlunoEmp);
		raAlunoEmp.setColumns(10);
		
		

		JLabel equipEmp = new JLabel("Patrimônio:");
		equipEmp.setBounds(10, 128, 75, 21);
		emprestimo_1.add(equipEmp);
		
		JTextArea obsEmpArea = new JTextArea();
		obsEmpArea.setBounds(10, 243, 706, 104);
		emprestimo_1.add(obsEmpArea);
		
		JButton iniciarEmp = new JButton("Iniciar");
		iniciarEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println("RA do aluno: ");
				String ra = raAlunoEmp.getText();
				
				AlunosEntity aluno = new AlunosEntity();
				aluno.setRa(ra);
				
				//System.out.println("Patrimônio do equipamento: ");
				String pat = patrimonioEmp.getText();
				
				EquipamentosEntity equipamento = new EquipamentosEntity();
				equipamento.setPatrimonio(pat);
				
				EmprestimosEntity emprestimo = new EmprestimosEntity(null, aluno, equipamento);
				EmprestimosEntity novoEmp = controlEmp.createEmprestimos(emprestimo);
				
				if (novoEmp != null) {
					JOptionPane.showMessageDialog(null, "Empréstimo iniciado com sucesso!");
					raAlunoEmp.setText("");
					nomeALunoEmp.setText("");
					cpfAlunoEmp.setText("");
					cursoAlunoEmp.setText("");
					patrimonioEmp.setText("");
					marcaEquipEmp.setText("");
					modeloEquipEmp.setText("");
					obsEmpArea.setText("");
					
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao criar Empréstimo");
				}
				
			}
		});
		iniciarEmp.setBounds(606, 358, 110, 22);
		emprestimo_1.add(iniciarEmp);

		patrimonioEmp = new JTextField();
		patrimonioEmp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				// Obtém a lista de empréstimos
				List<EmprestimosEntity> emprestimos = (List<EmprestimosEntity>) controlEmp.getEmpByPat(patrimonioEmp.getText());
				
				// Verifica se a lista não é nula e não está vazia
				if (emprestimos != null && !emprestimos.isEmpty()) {
					
					// Itera sobre cada empréstimo e imprime suas informações
					for (EmprestimosEntity emprestimo : emprestimos) {
						
						//System.out.println(emprestimos.size());
						System.out.println(emprestimo.getSituacao());
						
						if(emprestimo.getSituacao().equalsIgnoreCase("Em Andamento")) {
							iniciarEmp.setEnabled(false);

							raAlunoEmp.setText(emprestimo.getAlunos().getRa());
							nomeALunoEmp.setText(emprestimo.getAlunos().getNome());
							cpfAlunoEmp.setText(emprestimo.getAlunos().getCpf());
							cursoAlunoEmp.setText(emprestimo.getAlunos().getCurso());
							marcaEquipEmp.setText(emprestimo.getEquipamento().getMarca());
							modeloEquipEmp.setText(emprestimo.getEquipamento().getModelo());
							obsEmpArea.setText(emprestimo.getEquipamento().getObservacao());
						}
						else {
							EquipamentosEntity equipBanco = controlEquip.findPat(patrimonioEmp.getText());

							// SÓ MOSTRA SE O ALUNO ESTÁ ATIVO
							// ---------------------------------------------------------------------
							//if (patrimonioEmp.getText().isEmpty() || equipBanco == null || !equipBanco.isAtivo()) {
							//	return;

								if (equipBanco != null) {
									//btnCadastrarEquip.setEnabled(false);
									//String equipSituacao = equipBanco.getSituacao();
									marcaEquipEmp.setText(equipBanco.getMarca());
									modeloEquipEmp.setText(equipBanco.getModelo());
									obsEmpArea.setText(equipBanco.getObservacao());
								}
							
						}
						
					}
				}else {
					EquipamentosEntity equipBanco = controlEquip.findPat(patrimonioEmp.getText());
					
					marcaEquipEmp.setText(equipBanco.getMarca());
					modeloEquipEmp.setText(equipBanco.getModelo());
					obsEmpArea.setText(equipBanco.getObservacao());
				}
				
				
				
			}
		});
		patrimonioEmp.setBounds(89, 129, 127, 19);
		emprestimo_1.add(patrimonioEmp);
		patrimonioEmp.setColumns(10);

		nomeALunoEmp = new JTextField();
		nomeALunoEmp.setEditable(false);
		nomeALunoEmp.setBounds(318, 12, 178, 19);
		emprestimo_1.add(nomeALunoEmp);
		nomeALunoEmp.setColumns(10);

		cpfAlunoEmp = new JTextField();
		cpfAlunoEmp.setEditable(false);
		cpfAlunoEmp.setBounds(318, 43, 178, 19);
		emprestimo_1.add(cpfAlunoEmp);
		cpfAlunoEmp.setColumns(10);

		cursoAlunoEmp = new JTextField();
		cursoAlunoEmp.setEditable(false);
		cursoAlunoEmp.setBounds(318, 72, 178, 19);
		emprestimo_1.add(cursoAlunoEmp);
		cursoAlunoEmp.setColumns(10);

		marcaEquipEmp = new JTextField();
		marcaEquipEmp.setEditable(false);
		marcaEquipEmp.setColumns(10);
		marcaEquipEmp.setBounds(318, 128, 178, 19);
		emprestimo_1.add(marcaEquipEmp);

		modeloEquipEmp = new JTextField();
		modeloEquipEmp.setEditable(false);
		modeloEquipEmp.setColumns(10);
		modeloEquipEmp.setBounds(318, 162, 178, 19);
		emprestimo_1.add(modeloEquipEmp);

		JLabel obsLabelEmp = new JLabel("Observação:");
		obsLabelEmp.setBounds(10, 224, 109, 13);
		emprestimo_1.add(obsLabelEmp);

		JButton encerrarEmp = new JButton("Encerrar");
		encerrarEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmprestimosEntity empBanco = controlEmp.findPat(patrimonioEmp.getText());
				
				Long idEquip = empBanco.getIdEmprestimo();
				System.out.println(empBanco.getIdEmprestimo());
				
				EmprestimosEntity encerrado = controlEmp.encerrarEmprestimos(idEquip);
				if (encerrado != null) {
					JOptionPane.showMessageDialog(null, "Empréstimo encerrado com sucesso!");
						raAlunoEmp.setText("");
						nomeALunoEmp.setText("");
						cpfAlunoEmp.setText("");
						cursoAlunoEmp.setText("");
						patrimonioEmp.setText("");
						marcaEquipEmp.setText("");
						modeloEquipEmp.setText("");
						obsEmpArea.setText("");
						iniciarEmp.setEnabled(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao encerrar empréstimo");
				}
				
			}
		});
		encerrarEmp.setBounds(486, 358, 110, 22);
		emprestimo_1.add(encerrarEmp);

		JButton limparEmp = new JButton("Limpar");
		limparEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raAlunoEmp.setText("");
				nomeALunoEmp.setText("");
				cpfAlunoEmp.setText("");
				cursoAlunoEmp.setText("");
				patrimonioEmp.setText("");
				marcaEquipEmp.setText("");
				modeloEquipEmp.setText("");
				obsEmpArea.setText("");
				iniciarEmp.setEnabled(true);
				
			}
		});
		limparEmp.setBounds(366, 358, 110, 22);
		emprestimo_1.add(limparEmp);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 115, 706, 13);
		emprestimo_1.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 200, 706, 13);
		emprestimo_1.add(separator_1);

		JPanel alunos = new JPanel();
		tabbedPane.addTab("Alunos", null, alunos, null);
		alunos.setLayout(null);

		JLabel labelAluno = new JLabel("Nome Completo:");
		labelAluno.setBounds(101, 12, 93, 13);
		alunos.add(labelAluno);

		nomeAluno = new JTextField();
		nomeAluno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar())) {
					e.consume(); // Ignora a tecla digitada se não for um número ou se o limite de caracteres for
					// atingido
					return;
				}
			}
		});
		nomeAluno.setBounds(101, 30, 246, 22);
		alunos.add(nomeAluno);
		nomeAluno.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nascimento:");
		lblNewLabel.setBounds(10, 63, 103, 13);
		alunos.add(lblNewLabel);

		dataAluno = new JTextField();
		dataAluno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) || dataAluno.getText().length() >= 10) {
					e.consume(); // Ignora a tecla digitada se não for um número ou se o limite de caracteres for
					// atingido
					return;
				}
				if (dataAluno.getText().length() == 2 || dataAluno.getText().length() == 5) {
					dataAluno.setText(dataAluno.getText() + "/"); // Adiciona o ponto após o terceiro e o sétimo dígito
				}
			}
		});
		dataAluno.setBounds(10, 79, 81, 22);
		alunos.add(dataAluno);
		dataAluno.setColumns(10);

		JLabel cpfAlunos = new JLabel("CPF:");
		cpfAlunos.setBounds(357, 12, 45, 13);
		alunos.add(cpfAlunos);

		cpfAluno = new JTextField();
		cpfAluno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// Entrada: 00839834900

				if (!Character.isDigit(e.getKeyChar()) || cpfAluno.getText().length() >= 14) {
					e.consume(); // Ignora a tecla digitada se não for um número ou se o limite de caracteres for
					// atingido
					return;
				}

				if (cpfAluno.getText().length() == 3 || cpfAluno.getText().length() == 7) {
					cpfAluno.setText(cpfAluno.getText() + "."); // Adiciona o ponto após o terceiro e o sétimo dígito
				} else if (cpfAluno.getText().length() == 11) {
					cpfAluno.setText(cpfAluno.getText() + "-"); // Adiciona o traço após o décimo primeiro dígito
				}

				// Saída: 008.398.349-00
			}
		});
		cpfAluno.setBounds(357, 30, 103, 22);
		alunos.add(cpfAluno);
		cpfAluno.setColumns(10);

		JLabel emailAlunos = new JLabel("E-mail:");
		emailAlunos.setBounds(470, 12, 45, 13);
		alunos.add(emailAlunos);

		emailAluno = new JTextField();
		emailAluno.setBounds(470, 30, 246, 22);
		alunos.add(emailAluno);
		emailAluno.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("RA:");
		lblNewLabel_1.setBounds(10, 12, 45, 13);
		alunos.add(lblNewLabel_1);

		String[] cursosUniamerica = { "Selecione", "Administração", "Análise e Desenvolvimento de Sistemas",
				"Arquitetura e Urbanismo", "Biomedicina", "Ciências Contábeis", "Direito", "Engenharia Civil",
				"Engenharia de Produção", "Engenharia de Software", "Engenharia Elétrica", "Fisioterapia", "Nutrição",
				"Psicologia" };
		// Criar o JComboBox e adicionar os cursos
		JComboBox<String> cursoComboAluno = new JComboBox<>(cursosUniamerica);
		cursoComboAluno.setBounds(101, 79, 246, 22);
		alunos.add(cursoComboAluno);

		// MUDEI AQUI -------------------------------------------------
		JButton btnCadAluno = new JButton("Cadastrar");
		btnCadAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emailAluno.getText();

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String data = dataAluno.getText();
				Date date = null;

				try {
					date = formatter.parse(data);
				} catch (ParseException er) {
					System.out.println("Formato inválido. Use dd/MM/yyyy.");
					return; // Exit the program if the date format is invalid
				} catch (Exception ex) {
					System.out.println(ex);
				}

				char[] password = senhaAluno.getPassword();
				String senha = new String(password);
				String cursoSelecionado = (String) cursoComboAluno.getSelectedItem();

				if (cpfAluno.getText().length() < 14 || dataAluno.getText().length() < 10 || raAluno.getText().isEmpty()
						|| labelAluno.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Dados Incompletos!");
					return;
				}

				AlunosEntity alunoBanco = controlAluno.findRa(raAluno.getText());

				if (isValidEmail(email)) {

					// VERIFICAÇÃO DE ALUNO DESATIVADO
					// -------------------------------------------------------

					if (alunoBanco == null) {
						AlunosEntity aluno = new AlunosEntity(null, nomeAluno.getText(), cpfAluno.getText(), date,
								email, userAluno.getText(), senha, raAluno.getText(), cursoSelecionado);
						AlunosEntity alunoNovo = controlAluno.createAlunos(aluno);
						if (alunoNovo != null) {
							nomeAluno.setText("");
							cpfAluno.setText("");
							dataAluno.setText("");
							emailAluno.setText("");
							userAluno.setText("");
							senhaAluno.setText("");
							raAluno.setText("");
							cursoComboAluno.setSelectedItem("Selecione");
							JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
							btnCadAluno.setEnabled(true);
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao criar Aluno");
						}
					} else {

						new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat newForm = new SimpleDateFormat("dd/MM/yyyy");
						Date dateN = new Date();

						btnCadAluno.setEnabled(false);
						nomeAluno.setText(alunoBanco.getNome());
						cpfAluno.setText(alunoBanco.getCpf());

						date = alunoBanco.getDtNascimento();
						String dataString = newForm.format(dateN);

						dataAluno.setText(dataString);
						emailAluno.setText(alunoBanco.getEmail());
						userAluno.setText(alunoBanco.getUsuario());
						senhaAluno.setText(alunoBanco.getSenha());
						cursoComboAluno.setSelectedItem(alunoBanco.getCurso());

						int resposta = JOptionPane.showConfirmDialog(null, "Deseja reativar o aluno?",
								"Aluno Desativado", JOptionPane.YES_NO_OPTION);

						if (resposta == JOptionPane.YES_OPTION) {
							alunoBanco.setAtivo(true);
							AlunosEntity alunoReativado = (AlunosEntity) controlAluno.service.repository
									.update(alunoBanco);

							if (alunoReativado != null) {
								nomeAluno.setText("");
								cpfAluno.setText("");
								dataAluno.setText("");
								emailAluno.setText("");
								userAluno.setText("");
								senhaAluno.setText("");
								raAluno.setText("");
								cursoComboAluno.setSelectedItem("Selecione");
								JOptionPane.showMessageDialog(null, "Aluno reativado com sucesso!");
								btnCadAluno.setEnabled(true);

							} else {
								JOptionPane.showMessageDialog(null, "Não foi possível reativar o aluno.");
							}

						} else {
							return;
						}

					}

				} else {
					JOptionPane.showMessageDialog(null, "Por favor, insira um endereço de e-mail válido!");
				}
			}

			// Método para validar o formato do e-mail
			private boolean isValidEmail(String email) {
				// Verifica se o e-mail contém "@" e ".com"
				return email.contains("@") && email.contains(".com");

			}
		});
		btnCadAluno.setBounds(606, 358, 110, 22);
		alunos.add(btnCadAluno);

		raAluno = new JTextField();
		raAluno.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat newForm = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				AlunosEntity alunoBanco = controlAluno.findRa(raAluno.getText());

				// SÓ MOSTRA SE O ALUNO ESTÁ ATIVO
				// ---------------------------------------------------------------------
				if (raAluno.getText().isEmpty() || alunoBanco.isAtivo() == false || alunoBanco == null) {
					return;
				} else {

					if (alunoBanco != null) {
						btnCadAluno.setEnabled(false);
						String alunoCurso = alunoBanco.getCurso();
						nomeAluno.setText(alunoBanco.getNome());
						cpfAluno.setText(alunoBanco.getCpf());

						date = alunoBanco.getDtNascimento();
						String dataString = newForm.format(date);

						dataAluno.setText(dataString);
						emailAluno.setText(alunoBanco.getEmail());
						userAluno.setText(alunoBanco.getUsuario());
						senhaAluno.setText(alunoBanco.getSenha());
						cursoComboAluno.setSelectedItem(alunoCurso);

					}

				}
			}
		});
		raAluno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		raAluno.setBounds(10, 30, 81, 22);
		alunos.add(raAluno);
		raAluno.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Curso:");
		lblNewLabel_2.setBounds(101, 63, 45, 13);
		alunos.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Usuário:");
		lblNewLabel_3.setBounds(357, 63, 88, 13);
		alunos.add(lblNewLabel_3);

		userAluno = new JTextField();
		userAluno.setBounds(357, 79, 103, 22);
		alunos.add(userAluno);
		userAluno.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Senha:");
		lblNewLabel_4.setBounds(470, 63, 45, 13);
		alunos.add(lblNewLabel_4);

		senhaAluno = new JPasswordField();
		senhaAluno.setBounds(470, 79, 84, 22);
		alunos.add(senhaAluno);

		JButton btnUpAluno = new JButton("Atualizar");
		btnUpAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunosEntity alunoBanco = controlAluno.findRa(raAluno.getText());
				System.out.println(raAluno.getText());
				System.out.println(alunoBanco.getIdAluno());

				String email = emailAluno.getText();

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String data = dataAluno.getText();
				Date date = null;

				try {
					date = formatter.parse(data);
				} catch (ParseException er) {
					System.out.println("Formato inválido. Use dd/MM/yyyy.");
					return; // Exit the program if the date format is invalid
				} catch (Exception ex) {
					System.out.println(ex);
				}

				char[] password = senhaAluno.getPassword();
				String senha = new String(password);

				if (isValidEmailUp(email)) {

					String cursoSelecionado = (String) cursoComboAluno.getSelectedItem();

					AlunosEntity alunoUp = new AlunosEntity(alunoBanco.getIdAluno(), nomeAluno.getText(),
							cpfAluno.getText(), date, email, userAluno.getText(), senha, raAluno.getText(),
							cursoSelecionado);

					AlunosEntity novosDados = controlAluno.updateAlunos(alunoUp);

					if (novosDados != null) {
						nomeAluno.setText("");
						cpfAluno.setText("");
						dataAluno.setText("");
						emailAluno.setText("");
						userAluno.setText("");
						senhaAluno.setText("");
						raAluno.setText("");
						cursoComboAluno.setSelectedItem("Selecione");
						JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
						btnCadAluno.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar Aluno");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Por favor, insira um endereço de e-mail válido!");
				}
			}

			// Método para validar o formato do e-mail
			private boolean isValidEmailUp(String email) {
				// Verifica se o e-mail contém "@" e ".com"
				return email.contains("@") && email.contains(".com");

			}

		});
		btnUpAluno.setBounds(486, 358, 110, 22);
		alunos.add(btnUpAluno);

		// EXCLUIR
		// ------------------------------------------------------------------------------------------------------
		JButton btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunosEntity alunoBanco = controlAluno.findRa(raAluno.getText());
				if (alunoBanco != null) {

					int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o aluno?", "Excluir Aluno",
							JOptionPane.YES_NO_OPTION);

					if (resposta == JOptionPane.YES_OPTION) {

						AlunosEntity alunoDeletado = controlAluno.deleteAlunos(alunoBanco.getRa());

						if (alunoDeletado == null) {
							JOptionPane.showMessageDialog(null, "Erro ao excluir aluno! Empréstimos em Aberto.");
						} else {
							nomeAluno.setText("");
							cpfAluno.setText("");
							dataAluno.setText("");
							emailAluno.setText("");
							userAluno.setText("");
							senhaAluno.setText("");
							raAluno.setText("");
							cursoComboAluno.setSelectedItem("Selecione");
							JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
							btnCadAluno.setEnabled(true);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao excluir Aluno");
				}

			}
		});
		btnNewButton_2.setBounds(246, 358, 110, 22);
		alunos.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Limpar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnCadAluno.setEnabled(true);
				// Limpa a tabela
				DefaultTableModel model = (DefaultTableModel) tableAlunos.getModel();
				model.setRowCount(0);
				nomeAluno.setText("");
				cpfAluno.setText("");
				dataAluno.setText("");
				emailAluno.setText("");
				userAluno.setText("");
				senhaAluno.setText("");
				raAluno.setText("");
				cursoComboAluno.setSelectedItem("Selecione");
			}
		});
		btnNewButton_3.setBounds(366, 358, 110, 22);
		alunos.add(btnNewButton_3);

		// Botão Pesquisar
		JButton alunoPesquisar = new JButton("Pesquisar");
		alunoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alunos.updateUI();
				// Limpa a tabela
				DefaultTableModel model1 = (DefaultTableModel) tableAlunos.getModel();
				model1.setRowCount(0);

				List<AlunosEntity> alunosList = alunosService.visualizarAlunos();
				System.out.println(alunosList.size());
				// Obtém os alunos do banco de dados

				// Verifica se a lista de alunos não é nula e não está vazia
				if (alunosList != null && !alunosList.isEmpty()) {
					// Adiciona os dados de cada aluno ao modelo da tabela
					for (AlunosEntity aluno : alunosList) {
						model1.addRow(new Object[] { aluno.getRa(), aluno.getNome(), aluno.getCpf(), aluno.getEmail(),
								aluno.getDtNascimento(), aluno.getCurso() });
					}
				} else {
					System.out.println("Nenhum aluno encontrado.");
				}

			}
		});
		alunoPesquisar.setBounds(564, 79, 152, 22);
		alunos.add(alunoPesquisar);

		// Tabela Alunos
		tableAlunos = new JTable();
		tableAlunos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "RA", "Nome", "CPF", "Email", "Nasc.", "Curso" }));
		tableAlunos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		// Adiciona a tabela dentro de um JScrollPane
		JScrollPane scrollPaneAlunos = new JScrollPane(tableAlunos);
		scrollPaneAlunos.setBounds(10, 125, 707, 224); // Ajuste a posição e tamanho conforme necessário
		alunos.add(scrollPaneAlunos);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 112, 706, 13);
		alunos.add(separator_2);

		JPanel equipamentos = new JPanel();
		tabbedPane.addTab("Equipamentos", null, equipamentos, null);
		equipamentos.setLayout(null);

		JLabel lblPatrimnio = new JLabel("Patrimônio:");
		lblPatrimnio.setBounds(10, 12, 119, 13);
		equipamentos.add(lblPatrimnio);

		JButton btnCadastrarEquip = new JButton("Cadastrar");
		btnCadastrarEquip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String data = dtAquiEquip.getText();
				Date date = null;

				try {
					date = formatter.parse(data);
				} catch (ParseException er) {
					System.out.println("Formato inválido. Use dd/MM/yyyy.");
					return; // Exit the program if the date format is invalid
				} catch (Exception ex) {
					System.out.println(ex);
				}

				EquipamentosEntity equipamento = new EquipamentosEntity(null, patrimonioEquip.getText(),
						marcaEquip.getText(), modeloEquip.getText(), date, obsEquip.getText());

				EquipamentosEntity equipamentoNovo = controlEquip.createEquipamentos(equipamento);
				if (equipamentoNovo != null) {
					patrimonioEquip.setText("");
					marcaEquip.setText("");
					modeloEquip.setText("");
					obsEquip.setText("");
					JOptionPane.showMessageDialog(null, "Equipamento cadastrado com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao criar Equipamento");
				}

			}
		});
		btnCadastrarEquip.setBounds(606, 358, 110, 22);
		equipamentos.add(btnCadastrarEquip);

		String[] tipos = { "Selecione", "Disponível", "Com defeito", "Em manutenção" };

		JComboBox<String> situacaoEquip = new JComboBox<>(tipos);
		situacaoEquip.setBounds(605, 30, 109, 21);
		equipamentos.add(situacaoEquip);

		patrimonioEquip = new JTextField();
		patrimonioEquip.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat newForm = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				EquipamentosEntity equipBanco = controlEquip.findPat(patrimonioEquip.getText());

				// SÓ MOSTRA SE O ALUNO ESTÁ ATIVO
				// ---------------------------------------------------------------------
				if (patrimonioEquip.getText().isEmpty() || equipBanco == null || !equipBanco.isAtivo()) {
					return;
				} else {
					if (equipBanco != null) {
						btnCadastrarEquip.setEnabled(false);
						String equipSituacao = equipBanco.getSituacao();
						patrimonioEquip.setText(equipBanco.getPatrimonio());
						marcaEquip.setText(equipBanco.getMarca());

						date = equipBanco.getDtAquisicao();
						String dataString = newForm.format(date);

						dtAquiEquip.setText(dataString);
						obsEquip.setText(equipBanco.getObservacao());
						situacaoEquip.setSelectedItem(equipSituacao); // CORRECTED ERROR
					}
				}
			}
		});

		patrimonioEquip.setColumns(10);
		patrimonioEquip.setBounds(10, 30, 119, 22);
		equipamentos.add(patrimonioEquip);

		JLabel lblDataDeAquisio = new JLabel("Data de Aquisição:");
		lblDataDeAquisio.setBounds(486, 14, 110, 13);
		equipamentos.add(lblDataDeAquisio);

		dtAquiEquip = new JTextField();
		dtAquiEquip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) || dtAquiEquip.getText().length() >= 10) {
					e.consume(); // Ignora a tecla digitada se não for um número ou se o limite de caracteres for
					// atingido
					return;
				}
				if (dtAquiEquip.getText().length() == 2 || dtAquiEquip.getText().length() == 5) {
					dtAquiEquip.setText(dtAquiEquip.getText() + "/"); // Adiciona o ponto após o terceiro e o
					// sétimo dígito
				}
			}
		});
		dtAquiEquip.setColumns(10);
		dtAquiEquip.setBounds(486, 30, 110, 22);
		equipamentos.add(dtAquiEquip);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(139, 14, 97, 13);
		equipamentos.add(lblMarca);

		marcaEquip = new JTextField();
		marcaEquip.setColumns(10);
		marcaEquip.setBounds(139, 30, 185, 22);
		equipamentos.add(marcaEquip);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(334, 14, 79, 13);
		equipamentos.add(lblModelo);

		modeloEquip = new JTextField();
		modeloEquip.setColumns(10);
		modeloEquip.setBounds(334, 30, 141, 22);
		equipamentos.add(modeloEquip);

		JLabel lblNewLabel_1_1 = new JLabel("Situação:");
		lblNewLabel_1_1.setBounds(604, 12, 97, 13);
		equipamentos.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Observação:");
		lblNewLabel_2_1.setBounds(10, 63, 81, 13);
		equipamentos.add(lblNewLabel_2_1);

		obsEquip = new JTextField();
		obsEquip.setColumns(10);
		obsEquip.setBounds(10, 79, 542, 22);
		equipamentos.add(obsEquip);

		JButton btnNewButton_1_1 = new JButton("Atualizar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquipamentosEntity equipBanco = controlEquip.findPat(patrimonioEquip.getText());

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String data = dtAquiEquip.getText();
				Date date = null;

				try {
					date = formatter.parse(data);
				} catch (ParseException er) {
					System.out.println("Formato inválido. Use dd/MM/yyyy.");
					return; // Exit the program if the date format is invalid
				} catch (Exception ex) {
					System.out.println(ex);
				}

				EquipamentosEntity equipamentoUp = new EquipamentosEntity(equipBanco.getIdEquipamentos(),
						patrimonioEquip.getText(), marcaEquip.getText(), modeloEquip.getText(), date,
						obsEquip.getText());

				EquipamentosEntity novosDados = controlEquip.updateEquipamentos(equipamentoUp);

				if (novosDados != null) {
					patrimonioEquip.setText("");
					marcaEquip.setText("");
					modeloEquip.setText("");
					obsEquip.setText("");
					dtAquiEquip.setText("");
					situacaoEquip.setSelectedItem("Selecione");
					JOptionPane.showMessageDialog(null, "Equipamento atualizado com sucesso!");
					btnCadAluno.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar Equipamento");
				}

			}
		});
		btnNewButton_1_1.setBounds(486, 358, 110, 22);
		equipamentos.add(btnNewButton_1_1);

		JButton btnNewButton_2_1 = new JButton("Excluir");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquipamentosEntity equipBanco = controlEquip.findPat(patrimonioEquip.getText());
				if (equipBanco != null) {

					int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o equipamento?",
							"Excluir Equipamento", JOptionPane.YES_NO_OPTION);

					if (resposta == JOptionPane.YES_OPTION) {

						EquipamentosEntity equipamentoDeletado = controlEquip
								.deleteEquipamentos(equipBanco.getPatrimonio());

						if (equipamentoDeletado == null) {
							JOptionPane.showMessageDialog(null, "Erro ao excluir equipamento! Empréstimos em Aberto.");
						} else {
							patrimonioEquip.setText("");
							marcaEquip.setText("");
							modeloEquip.setText("");
							dtAquiEquip.setText("");
							obsEquip.setText("");
							situacaoEquip.setSelectedItem("Selecione");
							JOptionPane.showMessageDialog(null, "Equipamento excluído com sucesso!");
							btnCadastrarEquip.setEnabled(true);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao excluir Equipamento");
				}

			}
		});
		btnNewButton_2_1.setBounds(246, 358, 110, 22);
		equipamentos.add(btnNewButton_2_1);

		JButton btnNewButton_3_1 = new JButton("Limpar");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarEquip.setEnabled(true);
				// Limpa a tabela
				DefaultTableModel model = (DefaultTableModel) tableEquipamentos.getModel();
				model.setRowCount(0);
				patrimonioEquip.setText("");
				marcaEquip.setText("");
				modeloEquip.setText("");
				obsEquip.setText("");
				obsEquip.setText("");
				situacaoEquip.setSelectedItem("Selecione");

			}
		});
		btnNewButton_3_1.setBounds(366, 358, 110, 22);
		equipamentos.add(btnNewButton_3_1);

		// Lista de cursos da Uniamérica de Foz do Iguaçu

		alunosService = new AlunosService();

		// Tabela Equipamentos
		tableEquipamentos = new JTable();
		tableEquipamentos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Patrimônio", "Marca", "Modelo", "Status", " Observação" }));
		tableEquipamentos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JScrollPane scrollPaneEquipamentos = new JScrollPane(tableEquipamentos);
		scrollPaneEquipamentos.setBounds(10, 125, 707, 224);
		equipamentos.add(scrollPaneEquipamentos);

		JButton equipPesquisar = new JButton("Pesquisar");
		equipPesquisar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Limpa a tabela
				DefaultTableModel modelEquipamento = (DefaultTableModel) tableEquipamentos.getModel();
				modelEquipamento.setRowCount(0);

				// Obtém os equipamentos do banco de dados
				List<EquipamentosEntity> equipamentosList = (List<EquipamentosEntity>) equipamentosService
						.visualizarEquipamentos();

				// Verifica se a lista de equipamentos não é nula e não está vazia
				if (equipamentosList != null && !equipamentosList.isEmpty()) {
					// Adiciona os dados de cada equipamento ao modelo da tabela
					for (EquipamentosEntity equipamento : equipamentosList) {
						modelEquipamento.addRow(new Object[] { equipamento.getPatrimonio(), equipamento.getMarca(),
								equipamento.getModelo(), equipamento.getSituacao(), equipamento.getObservacao() });
					}
				} else {
					System.out.println("Nenhum equipamento encontrado.");
				}
			}
		});
		equipPesquisar.setBounds(562, 79, 152, 22);
		equipamentos.add(equipPesquisar);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(10, 112, 706, 13);
		equipamentos.add(separator_2_1);

		JPanel relatorio = new JPanel();
		tabbedPane.addTab("Relatório", null, relatorio, null);
		relatorio.setLayout(null);
		JLabel lblPatrimnio_1 = new JLabel("Patrimônio:");
		lblPatrimnio_1.setBounds(10, 12, 81, 13);
		relatorio.add(lblPatrimnio_1);
		JButton btnCadastrarEquip_1 = new JButton("Cadastrar");
		btnCadastrarEquip_1.setBounds(606, 358, 110, 22);
		relatorio.add(btnCadastrarEquip_1);
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 29, 81, 22);
		relatorio.add(textField);
		JLabel lblDataDe = new JLabel("Data de:");
		lblDataDe.setBounds(101, 12, 79, 13);
		relatorio.add(lblDataDe);
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 29, 79, 19);
		relatorio.add(textField_1);
		JLabel lblMarca_1 = new JLabel("Marca:");
		lblMarca_1.setBounds(398, 12, 97, 13);
		relatorio.add(lblMarca_1);
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(398, 29, 167, 19);
		relatorio.add(textField_2);
		JLabel lblModelo_1 = new JLabel("Modelo:");
		lblModelo_1.setBounds(575, 12, 79, 13);
		relatorio.add(lblModelo_1);
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(575, 29, 141, 19);
		relatorio.add(textField_3);
		JLabel lblNewLabel_1_1_1 = new JLabel("Situação:");
		lblNewLabel_1_1_1.setBounds(279, 12, 72, 13);
		relatorio.add(lblNewLabel_1_1_1);
		JButton btnNewButton_1_1_1 = new JButton("Atualizar");
		btnNewButton_1_1_1.setBounds(486, 358, 110, 22);
		relatorio.add(btnNewButton_1_1_1);
		JButton btnNewButton_2_1_1 = new JButton("Excluir");
		btnNewButton_2_1_1.setBounds(246, 358, 110, 22);
		relatorio.add(btnNewButton_2_1_1);
		JButton btnNewButton_3_1_1 = new JButton("Limpar");
		btnNewButton_3_1_1.setBounds(366, 358, 110, 22);
		relatorio.add(btnNewButton_3_1_1);

		// Tabela Emprestimos

		tableEmprestimos = new JTable();
		tableEmprestimos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Início", "Patrimônio", "Aluno", "Status", "Final" }));
		tableEmprestimos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JScrollPane scrollPaneEmprestimos = new JScrollPane(tableEmprestimos);
		scrollPaneEmprestimos.setBounds(10, 125, 707, 224);
		relatorio.add(scrollPaneEmprestimos);

		JButton empPesquisar = new JButton("Pesquisar");
		empPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Limpa a tabela
				DefaultTableModel modelEmprestimo = (DefaultTableModel) tableEmprestimos.getModel();
				modelEmprestimo.setRowCount(0);

				// Obtém os equipamentos do banco de dados
				List<EmprestimosEntity> emprestimosList = (List<EmprestimosEntity>) controlEmp.viewEmprestimos();

				// Verifica se a lista de equipamentos não é nula e não está vazia
				if (emprestimosList != null && !emprestimosList.isEmpty()) {
					// Adiciona os dados de cada equipamento ao modelo da tabela
					for (EmprestimosEntity emprestimo : emprestimosList) {
						modelEmprestimo.addRow(new Object[] { emprestimo.getDtRetirada(),
								emprestimo.getEquipamento().getPatrimonio(), emprestimo.getAlunos().getNome(),
								emprestimo.getSituacao(), emprestimo.getDtDevolucao() });
					}
				} else {
					System.out.println("Nenhum equipamento encontrado.");
				}

			}
		});
		empPesquisar.setBounds(564, 79, 152, 22);
		relatorio.add(empPesquisar);
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(190, 29, 79, 19);
		relatorio.add(textField_5);
		JLabel lblAtAData = new JLabel("Até a Data");
		lblAtAData.setBounds(190, 12, 79, 13);
		relatorio.add(lblAtAData);

		JComboBox<String> statusEquip = new JComboBox<>(tipos);
		statusEquip.setBounds(279, 28, 109, 21);
		relatorio.add(statusEquip);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 79, 64, 22);
		relatorio.add(textField_4);

		JLabel lblNewLabel_1_2 = new JLabel("RA:");
		lblNewLabel_1_2.setBounds(10, 63, 45, 13);
		relatorio.add(lblNewLabel_1_2);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(84, 79, 185, 22);
		relatorio.add(textField_6);

		JLabel labelAluno_1 = new JLabel("Nome Completo:");
		labelAluno_1.setBounds(84, 62, 93, 13);
		relatorio.add(labelAluno_1);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(279, 79, 103, 22);
		relatorio.add(textField_7);

		JLabel cpfAlunos_1 = new JLabel("CPF:");
		cpfAlunos_1.setBounds(279, 62, 45, 13);
		relatorio.add(cpfAlunos_1);

		JComboBox<String> cursoComboAluno_1 = new JComboBox<>(cursosUniamerica);
		cursoComboAluno_1.setBounds(392, 79, 162, 22);
		relatorio.add(cursoComboAluno_1);

		JLabel lblNewLabel_2_2 = new JLabel("Curso:");
		lblNewLabel_2_2.setBounds(392, 62, 162, 13);
		relatorio.add(lblNewLabel_2_2);
		
		JSeparator separator_2_1_1 = new JSeparator();
		separator_2_1_1.setBounds(10, 112, 706, 13);
		relatorio.add(separator_2_1_1);

	}
}