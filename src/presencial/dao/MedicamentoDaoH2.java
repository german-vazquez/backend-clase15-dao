package presencial.dao;

import org.apache.log4j.Logger;
import presencial.modelo.Medicamento;

import java.sql.*;

public class MedicamentoDaoH2 implements IDao <Medicamento> {

    private static final String SQL_INSERT = "insert into medicamentos (nombre, laboratorio, cantidad, precio) values (?,?,?,?)";
    private static final String SQL_SELECT="select * from medicamentos where id=?";

    private static final Logger logger= Logger.getLogger(MedicamentoDaoH2.class);



    @Override
    public Medicamento guardar(Medicamento medicamento) {
        // conecta con db y guardar el medicamento

        logger.info("Registrando un nuevo medicamento: "+medicamento.getNombre());
        Connection connection = null;

        try {
            connection=BD.getConnection();
            PreparedStatement ps=connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, medicamento.getNombre());
            ps.setString(2, medicamento.getLaboratorio());
            ps.setInt(3, medicamento.getCantidad());
            ps.setDouble(4, medicamento.getPrecio());
            ps.executeUpdate();
            //procesar la clave autogenerada
            ResultSet claveGenerada = ps.getGeneratedKeys();
            while (claveGenerada.next()){
                medicamento.setId(claveGenerada.getInt(1));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return medicamento;
    }

    @Override
    public Medicamento buscar(Integer id) {

        logger.info("Buscando al medicamento con id: "+id);

        Connection connection;
        Medicamento medicamento = null;
        try{
            connection=BD.getConnection();
            PreparedStatement ps=connection.prepareStatement(SQL_SELECT);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                medicamento=new Medicamento(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDouble(5)
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return medicamento;
    }
}
