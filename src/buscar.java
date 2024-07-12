import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class buscar extends JFrame {
    private JTextField textField1;
    private JButton buscarButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton registrarButton;
    private JButton loginButton;
    private JPanel JPanel_buscar;

    public buscar(){
        super("Buscar");
        setSize(600,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_buscar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoPaciente crear = new nuevoPaciente();
                crear.setVisible(true);
                dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ir = new Login();
                ir.setVisible(true);
                dispose();
            }
        });
    }
    public void buscar() throws SQLException{
        Connection conectar = conexion();
        String sql = "SELECT * FORM paciente WHERE (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad)value(?,?,?,?,?,?,?)";
        PreparedStatement st = conectar.prepareStatement(sql);
    }
    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }
}
