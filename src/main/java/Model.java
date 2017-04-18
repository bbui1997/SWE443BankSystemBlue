import com.sun.tools.javac.code.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import swe443.bluebank.Transaction;

import java.math.BigInteger;
import java.util.Enumeration;


/**
 * Created by Truong on 3/28/17.
 */
public class Model {

    /**
     *
     * @see <a href='../../../doc/Model.html'>Model.html</a>
     * @see <a href='../../../doc/model.html'>model.html</a>
     */
    public static void main(String[] arg) {
        ClassModel model = new ClassModel("swe443.bluebank");

        Clazz accountClass = model.createClazz("Account")
                .withAttribute("name", DataType.STRING)
                .withAttribute("ssn", DataType.INT)
                .withAttribute("dob", DataType.OBJECT)
                .withAttribute("username", DataType.STRING)
                .withAttribute("password", DataType.STRING)
                .withAttribute("initialAmount", DataType.DOUBLE)
                .withAttribute("accountBalance", DataType.DOUBLE)
                .withAttribute("recentTransaction",DataType.STRING)
                .withAttribute("iOweTheBank",DataType.DOUBLE)
                //.withMethod("deposit", DataType.VOID)
                //.withMethod("withdraw",DataType.DOUBLE)
                //.withMethod("undoRecentTransaction", DataType.VOID)
                .withMethod("transfer", DataType.VOID);

        Clazz bankClass = model.createClazz("Bank")
                .withAttribute("bankName", DataType.STRING)
                .withMethod("mainMenu", DataType.OBJECT)
                .withMethod("createAccount", DataType.VOID)
                .withMethod("logIn", DataType.VOID)
                .withMethod("makeDeposit", DataType.VOID)
                .withMethod("makeWithdrawal", DataType.VOID)
                .withMethod("makeTransfer", DataType.VOID)
                .withMethod("undoMostRecentTransaction",DataType.VOID)
                .withMethod("viewBalance", DataType.VOID);

        Clazz transactionClass = model.createClazz("Transaction")
                .withAttribute("amount", DataType.DOUBLE)
                .withMethod("logbuilder",DataType.STRING);


        Clazz userClass = model.createClazz("User")
                .withAttribute("userName", DataType.STRING);

        //bank has MANY user & user has ONE banks
        bankClass.withBidirectional(userClass, "User_In", Cardinality.MANY, "Bank_has", Cardinality.ONE);

        //user has MANY accounts & account has ONE user
        userClass.withBidirectional(accountClass, "Account_Has", Cardinality.MANY, "User_Has", Cardinality.ONE);

        //account belongs to ONE bank & bank can have MANY accounts
        accountClass.withBidirectional(bankClass, "Bank_has", Cardinality.ONE, "Account_Has", Cardinality.MANY);


        model.generate();

        Storyboard storyboard = new Storyboard();
        storyboard.add("Bank Storyboard");
        storyboard.addClassDiagram(model);
        storyboard.dumpHTML();
    }
}
