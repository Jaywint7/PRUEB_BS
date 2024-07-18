import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class editarpac extends JFrame {
    private JTextField txtCedula;
    private JButton buscarButton;
    private JTextField txtHC;
    private JTextField txtnom;
    private JTextField txtape;
    private JTextField txtDE;
    private JButton registrarButton;
    private JButton loginButton;
    private JPanel JPanel_ed;
    private JTextField txttelf;
    private JTextField txtedad;

    public editarpac(){
        super("Actualizar");
        setSize(600,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_ed);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu menu = new menu();
                menu.setVisible(true);
                dispose();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void editar() throws SQLException{
        String cedula = txtCedula.getText();
        String hc = txtHC.getText();
        String nombre = txtnom.getText();
        String apellido = txtape.getText();
        String telefono = txttelf.getText();
        String edad = txtedad.getText();
        String de = txtDE.getText();

        Connection conectar = conexion();
        String sql = "UPDATE paciente SET n_historial_clinico = ?, nombre = ?, apellido = ?, telefono = ?, edad = ?, descripcion_enfermedad = ? WHERE cedula = ?";
        PreparedStatement st = conectar.prepareStatement(sql);

        st.setString(1, hc);
        st.setString(2, nombre);
        st.setString(3, apellido);
        st.setString(4, telefono);
        st.setString(5, edad);
        st.setString(6, de);
        st.setString(7, cedula);

        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "REGISTRO ACTUALIZADO CORRECTAMENTE");
            txtHC.setText("");
            txtnom.setText("");
            txtape.setText("");
            txttelf.setText("");
            txtedad.setText("");
            txtDE.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN REGISTRO CON LA CEDULA PROPORCIONADA");
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
