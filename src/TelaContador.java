import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import java.awt.Color;


public class TelaContador extends JFrame {

    // Listar na memória para guardar os gastos
    private List<Gasto> listaGastos = new ArrayList<>();
    private double rendaMensal = 0.0;
    private double totalGasto = 0.0;

    // Componentes da interface
    private JTextField txtRenda;
    private JTextField txtDescricao;
    private JTextField txtValor;
    private DefaultListModel<String> modeloLista;
    private JList<String> visualizadorGastos;
    private JLabel lblStatus;

    public TelaContador() {
        // Configurações da Janela
        setTitle("Controle de Despesas e Orçamento");
        setSize(520, 600);
        getContentPane().setBackground(Color.black); // cor do fundo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BorderLayout(10, 10));

        // 1. Painel Superior: Definição de Renda
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSuperior.setBorder(new EmptyBorder(10, 10, 5, 10));
        painelSuperior.add(new JLabel("Sua Renda do Mês (R$):"));

        txtRenda = new JTextField(10);
        JButton btnDefinirRenda = new JButton("Definir Renda");
        btnDefinirRenda.setBackground(Color.black);
        btnDefinirRenda.setForeground(Color.blue);
        painelSuperior.add(txtRenda);
        painelSuperior.add(btnDefinirRenda);

        // 2. Painel de Cadastro de Gastos
        JPanel painelCadastro = new JPanel(new GridLayout(3, 2, 5, 5));
        painelCadastro.setBorder(new EmptyBorder(0, 10, 10, 10));
        painelCadastro.add(new JLabel("Descrição do Gasto:"));
        txtDescricao = new JTextField();
        painelCadastro.add(txtDescricao);

        painelCadastro.add(new JLabel("Valor do Gasto (R$):"));
        txtValor = new JTextField();
        painelCadastro.add(txtValor);

        JButton btnAdicionar = new JButton("➕ Adicionar Gasto");
        btnAdicionar.setBackground(new Color(230, 240, 255));
        painelCadastro.add(new JLabel("")); // Espaço vazio para alinhar
        painelCadastro.add(btnAdicionar);

        // Agrupa os painéis de entrada no topo
        JPanel topoContainer = new JPanel(new BorderLayout());
        topoContainer.add(painelSuperior, BorderLayout.NORTH);
        topoContainer.add(painelCadastro, BorderLayout.SOUTH);
        add(topoContainer, BorderLayout.NORTH);

        // 3. Área Central: Lista de Gastos
        modeloLista = new DefaultListModel<>();
        visualizadorGastos = new JList<>(modeloLista);
        visualizadorGastos.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(visualizadorGastos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Histórico de Despesas"));
        add(scrollPane, BorderLayout.CENTER);

        // 4. Painel Inferior: Diagnóstico e Balanço (O "Alerta Vermelho")
        JPanel painelStatus = new JPanel(new BorderLayout());
        painelStatus.setBorder(new EmptyBorder(15, 15, 15, 15));

        lblStatus = new JLabel("Defina sua renda inicial para começar.", SwingConstants.CENTER);
        lblStatus.setFont(new Font("SansSerif", Font.BOLD, 14));
        painelStatus.add(lblStatus, BorderLayout.CENTER);
        add(painelStatus, BorderLayout.SOUTH);

        // --- AÇÕES DOS BOTÕES ---

        // Ação: Definir Renda
        btnDefinirRenda.addActionListener(e -> {
            try {
                rendaMensal = Double.parseDouble(txtRenda.getText().replace(",", "."));
                txtRenda.setEditable(false);
                btnDefinirRenda.setEnabled(false);
                atualizarBalanco();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, digite um valor numérico válido para a renda.", "Valor Inválido", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação: Adicionar Gasto
        btnAdicionar.addActionListener(e -> {
            adicionarGasto();
        });
    }

    private void adicionarGasto() {
        String desc = txtDescricao.getText().trim();
        String valorTexto = txtValor.getText().trim().replace(",", ".");

        if (desc.isEmpty() || valorTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha a descrição e o valor!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double valor = Double.parseDouble(valorTexto);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "O valor deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cria o objeto e adiciona na lista
            Gasto gasto = new Gasto(desc, valor);
            listaGastos.add(gasto);
            modeloLista.addElement(gasto.toString());

            totalGasto += valor;

            // Limpa os campos de texto
            txtDescricao.setText("");
            txtValor.setText("");
            txtDescricao.requestFocus();

            atualizarBalanco();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um valor numérico válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarBalanco() {
        double saldoRestante = rendaMensal - totalGasto;

        if (saldoRestante < 0) {
            // No vermelho!
            lblStatus.setText(String.format("🚨 NO VERMELHO! Déficit: R$ %.2f (Total gasto: R$ %.2f)", Math.abs(saldoRestante), totalGasto));
            lblStatus.setForeground(new Color(180, 0, 0)); // Vermelho escuro
        } else {
            // No azul/verde
            lblStatus.setText(String.format("✅ Saldo Restante: R$ %.2f (Total gasto: R$ %.2f)", saldoRestante, totalGasto));
            lblStatus.setForeground(new Color(0, 120, 40)); // Verde escuro
        }
    }

    public static void main(String[] args) {
        // Deixa a interface com o estilo nativo do seu sistema operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Inicia a tela
        SwingUtilities.invokeLater(() -> {
            new TelaContador().setVisible(true);
        });
    }
}