import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame {
    private JButton registrarPacienteButton;
    private JButton buscarPacienteButton;
    private JButton actualizarPacienteButton;
    private JButton borrarPacienteButton;
    private JButton loginButton;
    private JPanel JPanel_menu;

    public menu(){
        super("Menu");
        setSize(600,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        registrarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoPaciente nuevo = new nuevoPaciente();
                nuevo.setVisible(true);
                dispose();
            }
        });
        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar bus = new buscar();
                bus.setVisible(true);
                dispose();
            }
        });
        actualizarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarpac editar = new editarpac();
                editar.setVisible(true);
                dispose();
            }
        });
        borrarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrar borrar = new borrar();
                borrar.setVisible(true);
                dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login salir = new Login();
                salir.setVisible(true);
                dispose();
            }
        });
    }
}
