
import criptografia.CifraCesar;
import criptografia.CifraCesarHelper;
import java.util.Scanner;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Magdiel Ildefonso
 */
public class ClientCifraCesar {
    static CifraCesar cifraImpl;
    static Scanner in;

    public static void main(String args[]) {

        in = new Scanner(System.in);
        try {

// Create and initialize the ORB.
            ORB orb = ORB.init(args, System.getProperties());

            // Get the root naming context.
            org.omg.CORBA.Object objRef
                    = orb.resolve_initial_references("NameService");

 // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable Naming Service.
            NamingContextExt ncRef
                    = NamingContextExtHelper.narrow(objRef);

            String name = "CifraCesar";

            cifraImpl = CifraCesarHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtained a handle on server object: "
                    + cifraImpl);
            
            int op = 0;
            String frase = "";
            float chave = 0;
            
            while(op!=9){
                System.out.println("#######Menu#######");
                System.out.println(" 1 - Encriptar");
                System.out.println(" 2 - Desencriptar");
                System.out.println(" 9 - Sair");
                System.out.print(" Digite sua opcao: ");
                op = in.nextInt();
                
                switch(op){
                    case 1:
                        System.out.println("Digite a frase que deseja encriptar: ");
                        in.nextLine();
                        frase = in.nextLine();
                        System.out.println("Informe a chave de criptografia: ");
                        chave = in.nextInt();
                        System.out.println("Frase encriptada: "+cifraImpl.encript(frase, chave));
                        break;
                    case 2:
                        System.out.println("Digite a frase que deseja desencriptar: ");
                        in.nextLine();
                        frase = in.nextLine();
                        System.out.println("Informe a chave de criptografia: ");
                        chave = in.nextInt();
                        System.out.println("Frase descriptografada: " +cifraImpl.decript(frase, chave));
                        break;
                    case 9:
                        System.out.println("Software utilizando a tecnologia de computacao distribuida CORBA, construido por Kelson Duarte e Magdiel Bruno");
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            }
            
            cifraImpl.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
