import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JTextField txtusu;
    private JTextField txtcontra;
    private JButton loginButton;
    private JPanel JPanel_login;

    public Login(){
        super("Login");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ingresar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void ingresar() throws SQLException {
        String user = txtusu.getText();
        String pswd = txtcontra.getText();
        Connection conectar = conexion();
        String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
        PreparedStatement st = conectar.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pswd);
        ResultSet rs = st.executeQuery();

        if(rs.next()){
            JOptionPane.showMessageDialog(null,"CREDENCIALES CORRECTAS");
            menu nuevo = new menu();
            nuevo.setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null,"CREDENCIALES INCORRECTAS");
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
