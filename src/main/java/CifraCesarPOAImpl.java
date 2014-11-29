
import criptografia.CifraCesarPOA;
import org.omg.CORBA.ORB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Magdiel Ildefonso
 */
public class CifraCesarPOAImpl extends CifraCesarPOA{
    
    private ORB orb;
    
    public void setORB(ORB nOrb) {
        orb = nOrb;
    }

    public String encript(String frase, float chave) {
        String result = "";
        int ordChar = 0;
        for (int i = 0; i < frase.length(); i++) {
            ordChar = (int) (((int) frase.charAt(i)) - chave);
            if (frase.charAt(i) == ' ') {
                result += " ";
            } else if (ordChar >= 65 && ordChar <= 90) {
                result += (char) ordChar;
            } else if (ordChar < 97) {
                result += (char) (122 - ((97 - ordChar) - 1));
            } else if (ordChar >= 97 && ordChar <= 122) {
                result += (char) ordChar;
            }
        }
        return result;
    }

    public String decript(String frase, float chave) {
       
        String result = "";
        int ordChar=0;
        
        for(int i=0; i < frase.length(); i++){
            ordChar = (int) (((int) frase.charAt(i)) + chave);
            
            if(frase.charAt(i) == ' '){
                result += " ";
            }else if(ordChar >= 65 && ordChar <= 90){
                result += (char) ordChar;
            }else if(ordChar >= 97 && ordChar <= 122){
                result += (char) ordChar;
            }else if(ordChar > 90){
                result += (char) (((ordChar-90)-1)+65);
            }    
        }
        return result;
    }

    public void shutdown() {
        orb.shutdown(false);
    }
}
