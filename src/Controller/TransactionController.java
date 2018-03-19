package Controller;

import Model.GameState;
import Model.Transaction;
import javafx.scene.input.KeyCode;

public class TransactionController extends SubKeyController {
    private Transaction transaction;
    private int selectedIndex;
    private boolean confirmTransaction;
    private boolean closeRequest;
    private GameState gameState;
    public TransactionController(GameState gameState) {
        this.gameState = gameState;
        selectedIndex = 0;
        confirmTransaction = false;
    }

    @Override
    void keyInput(KeyCode code) {
        if(transaction == null) {
            return;
        }

        switch (code) {
            case B:
                confirmTransaction = true;
                break;
            case DOWN:
                if(selectedIndex < transaction.getMerchant().getInventory().numOfItems()-1) {
                    selectedIndex++;
                }
                break;
            case UP:
                if(selectedIndex > 0) {
                    selectedIndex--;
                }
                break;
            case ESCAPE:
                transaction = null;
                closeRequest = true;
                gameState.getTransaction().getMerchant().setIsSelling(false);
                break;


        }

    }

    @Override
    boolean isActive() {
        if(transaction != null) {
            return true;
        }
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

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public boolean getCloseRequest() { return closeRequest; }
    public void setCloseRequest(boolean bool) { closeRequest = bool; }
}
