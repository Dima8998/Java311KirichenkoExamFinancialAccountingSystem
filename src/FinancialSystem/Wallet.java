package FinancialSystem;

import java.util.*;

import static java.lang.System.out;

public class Wallet {
    private String surnameOfOwner;
    private String nameOfWallet;
    private int balance;
    private static ArrayList<Wallet> wallets = new ArrayList<>();
    private static boolean isUnique = false;
    private static boolean isPrint = false;
    private String adminPassword = "111";

    public Wallet(String surnameOfOwner, String nameOfWallet, int balance) {
        this.surnameOfOwner = surnameOfOwner;
        this.nameOfWallet = nameOfWallet;
        if (balance < 0) {
            out.println("Balance can not be lower then 0");
            return;
        }
        this.balance = balance;
    }

    public String getSurnameOfOwner() {
        return surnameOfOwner;
    }

    public String getNameOfWallet() {
        return nameOfWallet;
    }

    public int getBalance() {
        return balance;
    }

    public void setSurnameOfOwner(String surnameOfOwner, String inputPassword) {
        if (adminPassword.equals(inputPassword)) {
            this.surnameOfOwner = surnameOfOwner;
            out.println("Surname has been changed");
        } else {
            out.println("Incorrect password");
        }
    }

    public void setNameOfWallet(String nameOfWallet, String inputPassword) {
        if (adminPassword.equals(inputPassword)) {
            this.nameOfWallet = nameOfWallet;
            out.println("Name of wallet has been changed");
        } else {
            out.println("Incorrect password");
        }
    }

    public void setBalance(int balance, String inputPassword) {
        if (adminPassword.equals(inputPassword)) {
            if (balance > 0) {
                this.balance = balance;
                out.println("Balance has been changed");
            } else {
                out.println("Balance can`t be lower then zero");
            }
        } else {
            out.println("Incorrect password");
        }
    }

    public void deleteSurnameOfOwner(String inputPassword) {
        if (!this.surnameOfOwner.equals("")) {
            if (adminPassword.equals(inputPassword)) {
                this.surnameOfOwner = "";
                out.println("Surname has been deleted");
            } else {
                out.println("Incorrect password");
            }
        } else {
            out.println("Surname already empty");
        }
    }

    public void deleteNameOfWallet(String inputPassword) {
        if (!this.nameOfWallet.equals("")) {
            if (adminPassword.equals(inputPassword)) {
                this.nameOfWallet = "";
            } else {
                out.println("Incorrect password");
            }
        } else {
            out.println("Name of wallet already empty");
        }
    }

    public void deleteBalance(String inputPassword) {
        if (!(this.balance == 0)) {
            if (adminPassword.equals(inputPassword)) {
                this.balance = 0;
            } else {
                out.println("Incorrect password");
            }
        } else {
            out.println("Balance already 0");
        }
    }

    public static boolean addNewWallet(Wallet newWallet) {
        if (wallets.isEmpty()) {
            isUnique = true;
        } else {
            for (Wallet wallet : wallets) {
                if (!(wallet.getNameOfWallet().equals(newWallet.getNameOfWallet()) && wallet.getSurnameOfOwner().equals(newWallet.getSurnameOfOwner()))) {
                    isUnique = true;
                } else {
                    isUnique = false;
                }
            }
        }
        if (isUnique) {
            wallets.add(newWallet);
            out.println("A new wallet has been added to the list");
            isUnique = false;
            return true;
        } else {
            out.println("List of wallets already contains this wallet");
        }
        return false;
    }

    public static void printInfoAboutWalletByNameWalletAndSurnameOfOwner(String nameOfWallet, String surnameOfOwner) {
        for (Wallet wallet : wallets) {
            if (wallet.getNameOfWallet().equals(nameOfWallet) && wallet.getSurnameOfOwner().equals(surnameOfOwner)) {
                out.println(wallet.toString());
                isPrint = true;
                break;
            }
        }
        if (!isPrint) {
            out.println("Can`t find wallet by this name: " + nameOfWallet);
        } else {
            isPrint = false;
        }
    }

    public static void printAllWallets() {
        if (wallets.isEmpty()) {
            out.println("\nList of wallets is empty");
        } else {
            wallets.stream().forEach(out::println);
        }
    }

    public static int getBalanceOfAllWallets(String surnameOfOwner) {
        int sum = 0;
        for (Wallet wallet : wallets) {
            if (wallet.getSurnameOfOwner().equals(surnameOfOwner)) {
                sum += wallet.balance;
            }
        }
        return sum;
    }

    protected static boolean getAmountFromPotentialIncome(PotentialIncome potentialIncome) {
        if (wallets.isEmpty()) {
            out.println("The list of wallets is empty");
            return false;
        }
        for (Wallet wallet : wallets) {
            if (wallet.surnameOfOwner.equals(potentialIncome.getPayeeOfIncome())) {
                wallet.balance += potentialIncome.getAmountOfIncome();
                out.println("The potential income has been moved to wallet");
                out.println(wallet);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "\nSurname of owner: " + surnameOfOwner +
                "\nName of wallet: " + nameOfWallet +
                "\nBalance: " + balance;
    }
}
