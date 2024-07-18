import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class buscar extends JFrame {
    private JTextField txtCedula;
    private JButton buscarButton;
    private JTextField txtHC;
    private JTextField txtnom;
    private JTextField txtape;
    private JTextField txtDE;
    private JButton registrarButton;
    private JButton menubtn;
    private JPanel JPanel_buscar;
    private JTextField txttelf;
    private JTextField txtedad;

    public buscar(){
        super("Buscar");
        setSize(600,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_buscar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menubtn.addActionListener(new ActionListener() {
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
                    buscar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void buscar() throws SQLException{
        int id= Integer.parseInt(txtCedula.getText());
        Connection conectar = conexion();
        String sql = "SELECT * FROM paciente WHERE cedula = ?";

        PreparedStatement st = conectar.prepareStatement(sql);
        st.setInt(1,id);

        ResultSet rs=st.executeQuery();
        if(rs.next()){
            String hc=rs.getString("n_historial_clinico");
            txtHC.setText(hc);
            String nombre=rs.getString("nombre");
            txtnom.setText(nombre);
            String apellido=rs.getString("apellido");
            txtape.setText(apellido);
            String telefono=rs.getString("telefono");
            txttelf.setText(telefono);
            String edad=rs.getString("edad");
            txtedad.setText(edad);
            String ds=rs.getString("descripcion_enfermedad");
            txtDE.setText(ds);
        }
        rs.close();
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
