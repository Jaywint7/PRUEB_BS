import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class borrar extends JFrame {
    private JPanel JPanel_borrar;
    private JTextField txtCedula;
    private JButton eliminarRegistroButton;
    private JButton menuButton;

    public borrar(){
        super("Borrar");
        setSize(400,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_borrar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        eliminarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    borrarP();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu nuevo = new menu();
                nuevo.setVisible(true);
                dispose();
            }
        });
    }

    public void borrarP() throws SQLException {
        String cedula = txtCedula.getText();

        Connection conectar = conexion();
        String sql = "DELETE FROM paciente WHERE cedula = ?";
        PreparedStatement st = conectar.prepareStatement(sql);

        st.setString(1, cedula);

        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO CORRECTAMENTE");
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
