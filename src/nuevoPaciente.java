import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class nuevoPaciente extends JFrame{
    private JTextField txtHC;
    private JTextField txtnom;
    private JTextField txtape;
    private JTextField txttelf;
    private JTextField txtDE;
    private JButton registrarButton;
    private JButton buscarPacienteButton;
    private JPanel JPanel_reg;
    private JTextField txtcedula;
    private JTextField txtedad;

    public nuevoPaciente(){
        super("Registro");
        setSize(600,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_reg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar buscar = new buscar();
                buscar.setVisible(true);
                dispose();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registrar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void registrar() throws SQLException {
        String cedula = txtcedula.getText();
        String hc = txtHC.getText();
        String nombre = txtnom.getText();
        String apellido = txtape.getText();
        String telefono = txttelf.getText();
        String edad = txtedad.getText();
        String de = txtDE.getText();
        Connection conectar = conexion();
        String sql = "INSERT INTO * form paciente WHERE (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad)value(?,?,?,?,?,?,?)";
        PreparedStatement st = conectar.prepareStatement(sql);
        st.setString(1,cedula);
        st.setString(2,hc);
        st.setString(3,nombre);
        st.setString(4,apellido);
        st.setString(5,telefono);
        st.setString(6,edad);
        st.setString(7,de);

        int rowsAffected=st.executeUpdate();
        if ( (rowsAffected > 0)) {
            JOptionPane.showMessageDialog(null, "REGISTRO INSERTADO CORRECTAMENTE");

        }
        st.close();
        conectar.close();
    }

    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }
}
