
import criptografia.CifraCesar;
import criptografia.CifraCesarHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Magdiel Ildefonso
 */
public class ServerCifraCesar {
    public static void main(String args[]) {

        try {

            ORB orb = ORB.init(args, System.getProperties());

            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

            CifraCesarPOAImpl cesarImpl = new CifraCesarPOAImpl();

            cesarImpl.setORB(orb);

            org.omg.CORBA.Object ref
                    = rootPOA.servant_to_reference(cesarImpl);

            CifraCesar href = CifraCesarHelper.narrow(ref);

            org.omg.CORBA.Object objRef
                    = orb.resolve_initial_references("NameService");

// Create and initialize the ORB
// Get reference to rootpoa
// Create servant and register it with the ORB
// Get object reference from the servant
// Get the root naming context
            NamingContextExt ncRef
                    = NamingContextExtHelper.narrow(objRef);

            String name = "CifraCesar";

            NameComponent path[] = ncRef.to_name(name);

            ncRef.rebind(path, href);

            rootPOA.the_POAManager().activate();

            System.out.println("CifraCesar ready and waiting ...");

            System.out.println(orb.object_to_string(href));

            orb.run();

        } catch (Exception e) {

            System.err.println("ERROR: " + e);

            e.printStackTrace(System.out);

        }

        System.out.println("CifraCesar Exiting ...");

    }
}
