package Applications.ParabankApplicaiton.Models;

public class TransferRequest {
    public String fromAccountId;
    public String toAccountId;
    public String amount;

    public TransferRequest(String fromAccountId, String toAccountId, String amount){
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }
}
