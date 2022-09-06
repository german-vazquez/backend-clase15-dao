package presencial.dao;

import presencial.modelo.Medicamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MedicamentoDaoH2 implements IDao <Medicamento> {

    private static final String SQL_INSERT = "insert into medicamentos (id, nombre, laboratorio, cantidad, precio), values (?,?,?,?,?)";

    @Override
    public Medicamento guardar(Medicamento medicamento) {
        // conecta con db y guardar el medicamento
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection= DriverManager.getConnection("jdbc:h2:~/clase15", "sa", "");
            PreparedStatement ps=connection.prepareStatement(SQL_INSERT);
            ps.setInt(1, medicamento.getId());
            ps.setString(2, medicamento.getNombre());
            ps.setString(3, medicamento.getLaboratorio());
            ps.setInt(4, medicamento.getCantidad());
            ps.setDouble(5, medicamento.getPrecio());
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return medicamento;
    }
}
