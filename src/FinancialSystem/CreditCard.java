package FinancialSystem;

import java.util.*;

import static java.lang.System.out;

public class CreditCard {
    private String surnameOfOwner;
    private String nameOfCard;
    private int limitRUB = 0;
    private int debt = 0;
    private int balance = 0;
    private static ArrayList<CreditCard> creditCards = new ArrayList<>();
    private static boolean isUnique = false;
    private static boolean isPrint = false;
    private String adminPassword = "111";

    public CreditCard(String surnameOfOwner, String nameOfCard, int limitRUB, int balance) {
        this.surnameOfOwner = surnameOfOwner;
        this.nameOfCard = nameOfCard;
        if (limitRUB > 0) {
            this.limitRUB = limitRUB;
        } else {
            out.println("Limit can`t be lower then zero");
            this.limitRUB = 0;
        }
        if (balance > limitRUB && limitRUB != 0) {
            out.println("The limit has been exceeded");
            this.balance = limitRUB;
        } else {
            if (balance < 0) {
                this.debt = balance * -1;
            } else {
                this.balance = balance;
            }
        }
    }

    public String getSurnameOfOwner() {
        return surnameOfOwner;
    }

    public String getNameOfCard() {
        return nameOfCard;
    }

    public int getLimitRUB() {
        return limitRUB;
    }

    public int getDebt() {
        return debt;
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

    public void setNameOfCard(String nameOfCard, String inputPassword) {
        if (adminPassword.equals(inputPassword)) {
            this.nameOfCard = nameOfCard;
            out.println("Name of credit card has been changed");
        } else {
            out.println("Incorrect password");
        }
    }

    public void setLimitRUB(int limitRUB, String inputPassword) {
        if (adminPassword.equals(inputPassword)) {
            if (limitRUB < 0) {
                out.println("Limit can`t be lower then zero");
            } else {
                this.limitRUB = limitRUB;
                out.println("Limit has been changed");
            }
        } else {
            out.println("Incorrect password");
        }
    }

    public void setBalance(int balance, String inputPassword) {
        if (adminPassword.equals(inputPassword)) {
            if (limitRUB == 0) {
                this.balance = balance;
            } else {
                if (balance > limitRUB) {
                    out.println("The limit has been exceeded");
                } else {
                    if (balance < 0) {
                        this.debt = balance * -1;
                        this.balance = 0;
                        out.println("Balance has been changed");
                    } else {
                        this.balance = balance;
                        this.debt = 0;
                        out.println("Balance has been changed");
                    }
                }
            }
        } else {
            out.println("Incorrect password");
        }
    }

    public void deleteSurnameOfOwner(String inputPassword) {
        if (!this.surnameOfOwner.isEmpty()) {
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

    public void deleteNameOfCard(String inputPassword) {
        if (!this.nameOfCard.isEmpty()) {
            if (adminPassword.equals(inputPassword)) {
                this.nameOfCard = "";
                out.println("Name of credit card has been deleted");
            } else {
                out.println("Incorrect password");
            }
        } else {
            out.println("Name of credit card already empty");
        }
    }

    public void deleteLimitRUB(String inputPassword) {
        if (!(this.limitRUB == 0)) {
            if (adminPassword.equals(inputPassword)) {
                this.limitRUB = 0;
                out.println("Limit has been deleted");
            } else {
                out.println("Incorrect password");
            }
        } else {
            out.println("Limit already 0");
        }
    }

    public void deleteBalance(String inputPassword) {
        if (!(this.balance == 0)) {
            if (adminPassword.equals(inputPassword)) {
                this.balance = 0;
                out.println("Balance has been deleted");
            } else {
                out.println("Incorrect password");
            }
        } else {
            out.println("Balance already 0");
        }
    }

    public void deleteDebt(String inputPassword) {
        if (!(this.debt == 0)) {
            if (adminPassword.equals(inputPassword)) {
                this.debt = 0;
                out.println("Debt has been deleted");
            } else {
                out.println("Incorrect password");
            }
        } else {
            out.println("Debt already 0");
        }
    }

    public static boolean addNewCreditCard(CreditCard newCreditCard) {
        if (creditCards.isEmpty()) {
            isUnique = true;
        } else {
            for (CreditCard card : creditCards) {
                if (!(card.getNameOfCard().equals(newCreditCard.getNameOfCard()) && card.getSurnameOfOwner().equals(newCreditCard.getSurnameOfOwner()))) {
                    isUnique = true;
                }
                else {
                    isUnique = false;
                }
            }
        }
        if (isUnique) {
            creditCards.add(newCreditCard);
            out.println("A new credit card has been added to the list");
            isUnique = false;
            return true;
        } else {
            out.println("List of credit cards already contains this card");
        }
        return false;
    }

    public static void printInfoAboutCreditCardByNameCreditCardAndSurnameOwner(String nameOfCard, String surnameOfOwner) {
        for (CreditCard card : creditCards) {
            if (card.getNameOfCard().equals(nameOfCard) && card.getSurnameOfOwner().equals(surnameOfOwner)) {
                out.println(card.toString());
                isPrint = true;
            }
        }
        if (!isPrint) {
            out.println("Can`t find credit card by this name: " + nameOfCard);
        } else {
            isPrint = false;
        }
    }

    public static void printAllCreditCards() {
        creditCards.stream().forEach(out::println);
    }

    public static int getBalanceOfAllCreditCardBySurnameOfOwner(String surname) {
        int sum = 0;
        for (CreditCard card : creditCards) {
            if (card.getSurnameOfOwner().equals(surname)) {
                if (card.getBalance() < 0) {
                    sum -= card.getDebt();
                } else {
                    sum += card.getBalance();
                }
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        return "\nSurname of owner: " + surnameOfOwner +
                "\nName of card: " + nameOfCard +
                "\nLimit on the card in RUB: " + limitRUB +
                "\nDebt: " + debt +
                "\nBalance: " + balance;
    }
}
