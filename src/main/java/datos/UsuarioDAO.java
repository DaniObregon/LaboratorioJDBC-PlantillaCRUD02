package datos;

import static datos.Conexion.close;
import domain.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    private static final String SQL_INSERT = "INSERT INTO test.usuario (usuario, password) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM test.usuario WHERE id_usuario = ?";
    private static final String SQL_UPDATE = "UPDATE test.usuario SET usuario = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_SELECT = "SELECT * FROM test.usuario";
    
    
    public int create(Usuario usuario){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            System.out.println("Ejecutando query: " + SQL_INSERT);
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            rows = ps.executeUpdate();
            System.out.println("Created rows: " + rows);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally{
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return rows;
    }
    
    public int delete(Usuario usuario){
        int rows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            System.out.println("Ejecutando query: " + SQL_DELETE);
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getId_usuario());
            rows = ps.executeUpdate();
            System.out.println("Deleted rows: " + rows);
        } catch (Exception e) {
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return rows;
    }
    
    public int update(Usuario usuario){
        var rows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getId_usuario());
            rows = ps.executeUpdate();
            System.out.println("Updated rows: " + rows);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return rows;
    }
    
    public List<Usuario> read(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario;
        List<Usuario> usuarios = new ArrayList();
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while(rs.next()){
                int id_usuario = rs.getInt("id_usuario");
                String user = rs.getString("usuario");
                String password = rs.getString("password");
                usuario = new Usuario(id_usuario, user, password);
                usuarios.add(usuario);
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return usuarios;
    }
}
