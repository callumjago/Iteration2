package Controller;

import Model.GameState;
import Model.Transaction;
import javafx.scene.input.KeyCode;

public class TransactionController extends SubKeyController {
    private Transaction transaction;
    private int selectedIndex;
    private boolean confirmTransaction;
    public TransactionController() {
        selectedIndex = 0;
        confirmTransaction = false;
    }

    @Override
    void keyInput(KeyCode code) {
        if(transaction == null) {
            return;
        }

        switch (code) {
            case ENTER:
                confirmTransaction = true;
                break;


        }

    }

    @Override
    boolean isActive() {
        return false;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void handleTransaction(GameState gs) {
        if(confirmTransaction) {
            gs.performTransaction(selectedIndex);
            transaction = null;
            selectedIndex = 0;
            confirmTransaction = false;
        }
    }
}
