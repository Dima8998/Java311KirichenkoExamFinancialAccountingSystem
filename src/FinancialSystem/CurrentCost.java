package FinancialSystem;

import java.util.*;

import static java.lang.System.out;

public class CurrentCost {
    private String nameOfShop;
    private int countOfProduct;
    private int summaryPrice;
    private static ArrayList<CurrentCost> currentCosts = new ArrayList<>();
    private static boolean isUnique = false;

    public CurrentCost(String nameOfShop, int countOfProduct, int summaryPrice) {
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

    public static void printCurrentCosts() {
        currentCosts.stream().forEach(out::println);
    }

    public static boolean addNewCurrentCoast(CurrentCost currentCostForAdd) {
        if (currentCosts.isEmpty()) {
            isUnique = true;
        } else {
            for (CurrentCost cost : currentCosts) {
                if (!(cost.getNameOfShop().equals(currentCostForAdd.getNameOfShop()) && cost.getSummaryPrice() == currentCostForAdd.getSummaryPrice())) {
                    isUnique = true;
                } else {
                    isUnique = false;
                }
            }
        }
        if (isUnique) {
            currentCosts.add(currentCostForAdd);
            out.println("A new current cost has been added");
            isUnique = false;
            return true;
        } else {
            out.println("List of current costs already contains this cost");
        }
        return false;
    }

    public static boolean getCoastFromPotentialList(PotentialCost potentialCoastForAdd) {
        if (currentCosts.isEmpty()){
            out.println("The potential cost has been moved to current costs");
            currentCosts.add(new CurrentCost(potentialCoastForAdd.getNameOfShop(), potentialCoastForAdd.getCountOfProduct(), potentialCoastForAdd.getSummaryPrice()));
            return true;
        }
        for (CurrentCost cost : currentCosts) {
            if (!(cost.getNameOfShop().equals(potentialCoastForAdd.getNameOfShop()) && cost.getSummaryPrice() == potentialCoastForAdd.getSummaryPrice())) {
                isUnique = true;
            } else {
                isUnique = false;
            }
        }
        if (isUnique) {
            currentCosts.add(new CurrentCost(potentialCoastForAdd.getNameOfShop(), potentialCoastForAdd.getCountOfProduct(), potentialCoastForAdd.getSummaryPrice()));
            out.println("The potential cost has been moved to current costs");
            return true;
        }
        out.println("Can`t move potential cost to current cost");
        return false;
    }

    @Override
    public String toString() {
        return "\nName of shop: " + nameOfShop +
                "\nCount of product: " + countOfProduct +
                "\nSummary price: " + summaryPrice;
    }
}
