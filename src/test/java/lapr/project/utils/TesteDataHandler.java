package demojdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteDataHandler {

    public static void main(String[] args) {

        DataHandler dh = null;

        try {

            // URL da BD a usar...
            // ... do servidor local.
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
            // ... do servidor do DEI.
            //String jdbcUrl = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";

            // Credenciais do utilizador da BD.
            String username = "nmf2";
            String password = "testes";

            dh = new DataHandler(jdbcUrl, username, password);

            System.out.println("\nEstabelecer a ligação à BD...");
            dh.openConnection();
            System.out.println("\t... Ligação obtida.");

            // TESTE 1
            String cor = "red";
            System.out.println("\nBarcos " + cor);
            ResultSet rSet= dh.getBoats(cor);
            while(rSet.next()){
                System.out.printf("%5d %s %n", rSet.getInt("bid"), rSet.getString("bname"));
            }

            // TESTE 2
            rSet = dh.getSailor(100);
            if (rSet!=null){
                dh.removeSailor(100);
                System.out.println("\nMarinheiro removido.");
            }

            // TESTE 3
            System.out.println("\nAdicionar Sailor ...");
            dh.addSailor(100, "Popeye", 11, 85);
            System.out.println("\t... Sailor Adicionado.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            String mensagem = dh.closeAll();
            if (!mensagem.isEmpty())
                System.out.println(mensagem);
            System.out.println("\nTerminada a ligação à BD.");
        }

    }

}
