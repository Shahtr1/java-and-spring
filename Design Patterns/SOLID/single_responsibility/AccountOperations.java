import java.util.HashMap;
import java.util.Map;

public class AccountOperations {
    private static Map<Integer, Account> accountMap = new HashMap<>();

    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void updateAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public Account getAccount(int accountNumber) {
        return accountMap.get(accountNumber);
    }

    /**
     * This method should not be part of Account Operations
     * Reason is accoutn operations are responsible for doing operation for account
     * like add account, update and get.
     * deposit should be calles as transaction
     */

    public void deposit(int amount, int accountNumber) {
        // Move to different class
    }
}
