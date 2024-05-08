import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    public Connection conectarBD(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e){} //Falta uma tratativa dos erros
        return conn;}
    public String nome="";
    public boolean result = false;
    public boolean verificarUsuario (String login, String senha){
        String sql = "";
        Connection conn = conectarBD(); //Apesar de ser aberto a conexão com o banco de dados ela não é fechada
        //Instrução SQL
        sql += "Select nome from usuarios ";
        sql += "where login " + "'" + login + "'"; //Nessa consulta falta o símbolo "=" após o "where login "
        sql += "and senha = " + "'" + senha + "'"; //Essas inserções de comandos SQL são vulneráveis devido a facilidade de alteração
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                result = true;
                nome = rs.getString("nome");}
        }catch (Exception e){  }	
        return result; 
    } //Fim da class
}