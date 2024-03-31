package FinancialSystem;

import java.util.*;

import static java.lang.System.out;

public class PotentialCost {
    private String nameOfShop;
    private int countOfProduct;
    private int summaryPrice;
    private static ArrayList<PotentialCost> potentialCosts = new ArrayList<>();
    private static boolean isUnique = false;

    public PotentialCost(String nameOfShop, int countOfProduct, int summaryPrice) {
        this.nameOfShop = nameOfShop;
        this.countOfProduct = countOfProduct;
        this.summaryPrice = summaryPrice;
    }

    public String getNameOfShop() {
        return nameOfShop;
    }

    public int getCountOfProduct() {
        return countOfProduct;
    }

    public int getSummaryPrice() {
        return summaryPrice;
    }

    public static void printPotentialCosts() {
        potentialCosts.stream().forEach(System.out::println);
    }

    public static boolean addNewPotentialCost(PotentialCost potentialCostForAdd) {
        if (potentialCosts.isEmpty()) {
            isUnique = true;
        } else {
            for (PotentialCost cost : potentialCosts) {
                if (!(cost.getNameOfShop().equals(potentialCostForAdd.getNameOfShop()) && cost.getSummaryPrice() == potentialCostForAdd.getSummaryPrice())) {
                    isUnique = true;
                }
                else {
                    isUnique = false;
                }
            }
        }
        if (isUnique) {
            potentialCosts.add(potentialCostForAdd);
            out.println("A new potential cost has been added");
            isUnique = false;
            return true;
        } else {
            out.println("List of potential costs already contains this cost");
        }
        return false;
    }

    public static boolean moveCoastFromPotentialToCurrentList(PotentialCost potentialCostForMove) {
        if(CurrentCost.getCoastFromPotentialList(potentialCostForMove)) {
            potentialCosts.remove(potentialCostForMove);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "\nName of shop: " + nameOfShop +
                "\nCount of product: " + countOfProduct +
                "\nSummary price: " + summaryPrice;
    }
}
