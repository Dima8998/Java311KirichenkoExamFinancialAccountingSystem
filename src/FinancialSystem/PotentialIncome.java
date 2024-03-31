package FinancialSystem;

import java.util.*;

import static java.lang.System.out;

public class PotentialIncome {
    private String nameOfIncome;
    private int amountOfIncome;
    private String payeeOfIncome;
    private static ArrayList<PotentialIncome> potentialIncome = new ArrayList<>();
    private static boolean isUnique = false;

    public PotentialIncome(String nameOfIncome, int amountOfIncome, String payeeOfIncome) {
        this.nameOfIncome = nameOfIncome;
        this.amountOfIncome = amountOfIncome;
        this.payeeOfIncome = payeeOfIncome;
    }

    public String getNameOfIncome() {
        return nameOfIncome;
    }

    public int getAmountOfIncome() {
        return amountOfIncome;
    }

    public String getPayeeOfIncome() {
        return payeeOfIncome;
    }

    public static void printPotentialIncome() {
        potentialIncome.stream().forEach(out::println);
    }

    public static boolean addNewPotentialIncome(PotentialIncome potentialIncomeForAdd) {
        if (potentialIncome.isEmpty()) {
            isUnique = true;
        } else {
            for (PotentialIncome income : potentialIncome) {
                if (!(income.getNameOfIncome().equals(potentialIncomeForAdd.getNameOfIncome()) && income.getPayeeOfIncome().equals(potentialIncomeForAdd.getPayeeOfIncome()))) {
                    isUnique = true;
                } else {
                    isUnique = false;
                }
            }
        }
        if (isUnique) {
            potentialIncome.add(potentialIncomeForAdd);
            out.println("A new potential income has been added");
            isUnique = false;
            return true;
        } else {
            out.println("List of potential income already contains this income");
        }
        return false;
    }

    public static boolean moveAmountOfPotentialIncomeToWalletAndRemove(PotentialIncome potentialIncomeForMove) {
        if (Wallet.getAmountFromPotentialIncome(potentialIncomeForMove)) {
            potentialIncome.remove(potentialIncomeForMove);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "\nName of income: " + nameOfIncome +
                "\nPayee of income" + payeeOfIncome +
                "\nAmount of income: " + amountOfIncome;
    }
}
