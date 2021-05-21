package test;

import datos.UsuarioDAO;
import domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TestLab {
    public static void main(String[] args) {
        
        Usuario usuarioNuevo = new Usuario(JOptionPane.showInputDialog("Introduce tu usuario"), JOptionPane.showInputDialog("Introduce tu pass"));
        
        //-----CREATE
        //new UsuarioDAO().create(usuarioNuevo);
        
        //-----DELETE
        //new UsuarioDAO().delete(new Usuario(10));
        //new UsuarioDAO().delete(new Usuario(11));
        
        //-----UPDATE
        //new UsuarioDAO().update(new Usuario(3, "hei hei", "5555"));
        
        //-----READ
        List<Usuario> usuarios = new UsuarioDAO().read();
        
//        for (Usuario usuario : usuarios) {
//            //System.out.println("persona = " + persona);
//            System.out.println(usuario.toString());
//        }
        
        usuarios.forEach(usuario -> {
            //System.out.println("persona = " + persona);
            System.out.println(usuario.toString());
        });
    }
}
