import java.util.*;

import static java.lang.System.out;

import FinancialSystem.*;

public class Main {
    private static final Map<Integer, Wallet> listOfWallets = new HashMap<>();
    private static final Map<Integer, CreditCard> listOfCreditCard = new HashMap<>();
    private static final Map<Integer, PotentialCost> listOfPotentialCosts = new HashMap<>();
    private static final Map<Integer, CurrentCost> listOfCurrentCosts = new HashMap<>();
    private static final Map<Integer, PotentialIncome> listOfPotentialIncome = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);
    private static String selectedItem;
    private static int inputIndexForGetSetDelete;
    private static int countOfCreditCards = 0;
    private static int countOfWallets = 0;
    private static int countOfPotentialCosts = 0;
    private static int countOfCurrentCosts = 0;
    private static int countOfPotentialIncome = 0;

    private static String inputPassword;

    public static void main(String[] args) {
        out.println("Welcome to financial system");
        out.println("""
                List of options:\s
                1. Work with wallets (wallet)
                2. Work with credit card (card)
                3. Work with potential costs (potential cost)
                4. Work with current costs (current cost)
                5. Work with potential income (potential income)
                0. Exit the program (exit)""");
        out.print("Input your choice: ");
        selectedItem = sc.next().toLowerCase().replace(" ", "");
        while (!selectedItem.equals("exit")) {
            switch (selectedItem) {
                case "wallet":
                    printOptionsForWalletsAndEnterTheChoice();
                    while (!selectedItem.equals("return")) {
                        if (!selectedItem.equals("add") && listOfWallets.isEmpty()) {
                            out.println("List of wallets is empty");
                            printOptionsForWalletsAndEnterTheChoice();
                            continue;
                        }
                        workWithWallets();
                        printOptionsForWalletsAndEnterTheChoice();
                    }
                    break;
                case "card":
                    printOptionsForCreditCardsAndEnterTheChoice();
                    while (!selectedItem.equals("return")) {
                        if (!selectedItem.equals("add") && listOfCreditCard.isEmpty()) {
                            out.println("List of cards is empty");
                            printOptionsForCreditCardsAndEnterTheChoice();
                            continue;
                        }
                        workWithCreditCards();
                        printOptionsForCreditCardsAndEnterTheChoice();
                    }
                    break;
                case "potentialcost":
                    printOptionsForPotentialCostsAndEnterTheChoice();
                    while (!selectedItem.equals("return")) {
                        if (!selectedItem.equals("add") && listOfPotentialCosts.isEmpty()) {
                            out.println("List of potential costs is empty");
                            printOptionsForPotentialCostsAndEnterTheChoice();
                            continue;
                        }
                        workWithPotentialCost();
                        printOptionsForPotentialCostsAndEnterTheChoice();
                    }
                    break;
                case "currentcost":
                    printOptionsForCurrentCostsAndEnterTheChoice();
                    while (!selectedItem.equals("return")) {
                        if (!selectedItem.equals("add") && listOfCurrentCosts.isEmpty()) {
                            out.println("List of current costs is empty");
                            printOptionsForCurrentCostsAndEnterTheChoice();
                            continue;
                        }
                        workWithCurrentCost();
                        printOptionsForCurrentCostsAndEnterTheChoice();
                    }
                    break;
                case "potentialincome":
                    printOptionsForPotentialIncomeAndEnterTheChoice();
                    while (!selectedItem.equals("return")) {
                        if (!selectedItem.equals("add") && listOfPotentialIncome.isEmpty()) {
                            out.println("List of potential income is empty");
                            printOptionsForPotentialIncomeAndEnterTheChoice();
                            continue;
                        }
                        workWithPotentialIncome();
                        printOptionsForPotentialIncomeAndEnterTheChoice();
                    }
                    break;
                default:
                    out.println("""
                            List of options:\s
                            1. Work with wallets (wallet)
                            2. Work with credit card (card)
                            3. Work with potential costs (potential cost)
                            4. Work with current costs (current cost)
                            5. Work with potential income (potential income)
                            0. Exit the program (exit)""");
                    out.print("Input your choice: ");
                    selectedItem = sc.next();
                    break;
            }
        }
    }

    public static void workWithWallets() {
        String nameOfWallet;
        String surnameWalletOwner;
        int balanceOfWallet;
        switch (selectedItem) {
            case "add":
                out.print("Write surname of wallet owner: ");
                nameOfWallet = sc.next();
                out.print("Write name of wallet: ");
                surnameWalletOwner = sc.next();
                out.print("Write balance of wallet: ");
                balanceOfWallet = sc.nextInt();
                if (balanceOfWallet < 0) {
                    while (balanceOfWallet < 0) {
                        out.println("Balance can`t be lower then 0");
                        balanceOfWallet = sc.nextInt();
                    }
                }
                listOfWallets.put(countOfWallets, new Wallet(nameOfWallet, surnameWalletOwner, balanceOfWallet));
                boolean isAdd = Wallet.addNewWallet(listOfWallets.get(countOfWallets));
                if (isAdd) {
                    countOfWallets++;
                    break;
                }
                listOfWallets.remove(countOfWallets);
                break;
            case "printall":
                Wallet.printAllWallets();
                break;
            case "printbyname":
                out.print("Write name of wallet to find: ");
                nameOfWallet = sc.next();
                out.print("Write surname of owner of wallet to find wallet: ");
                surnameWalletOwner = sc.next();
                Wallet.printInfoAboutWalletByNameWalletAndSurnameOfOwner(nameOfWallet, surnameWalletOwner);
                break;
            case "countbalance":
                out.print("Write surname of owner to count balance of all wallets: ");
                surnameWalletOwner = sc.next();
                out.println(surnameWalletOwner + " has the balance of all wallets: " + Wallet.getBalanceOfAllWallets(surnameWalletOwner));
                break;
            case "getsurname":
                selectNumberOfWallet();
                out.println(listOfWallets.get(inputIndexForGetSetDelete).getSurnameOfOwner());
                break;
            case "getname":
                selectNumberOfWallet();
                out.println(listOfWallets.get(inputIndexForGetSetDelete).getNameOfWallet());
                break;
            case "getbalance":
                selectNumberOfWallet();
                out.println(listOfWallets.get(inputIndexForGetSetDelete).getBalance());
                break;
            case "setsurname":
                selectNumberOfWallet();
                out.print("Write new surname of owner: ");
                surnameWalletOwner = sc.next();
                getPasswordFromUser();
                listOfWallets.get(inputIndexForGetSetDelete).setSurnameOfOwner(surnameWalletOwner, inputPassword);
                break;
            case "setname":
                selectNumberOfWallet();
                out.print("Write new name of wallet: ");
                nameOfWallet = sc.next();
                getPasswordFromUser();
                listOfWallets.get(inputIndexForGetSetDelete).setNameOfWallet(nameOfWallet, inputPassword);
                break;
            case "setbalance":
                selectNumberOfWallet();
                out.print("Write new balance of wallet: ");
                balanceOfWallet = sc.nextInt();
                getPasswordFromUser();
                listOfWallets.get(inputIndexForGetSetDelete).setBalance(balanceOfWallet, inputPassword);
                break;
            case "deletesurname":
                selectNumberOfWallet();
                getPasswordFromUser();
                listOfWallets.get(inputIndexForGetSetDelete).deleteSurnameOfOwner(inputPassword);
                break;
            case "deletename":
                selectNumberOfWallet();
                getPasswordFromUser();
                listOfWallets.get(inputIndexForGetSetDelete).deleteNameOfWallet(inputPassword);
                break;
            case "deletebalance":
                selectNumberOfWallet();
                getPasswordFromUser();
                listOfWallets.get(inputIndexForGetSetDelete).deleteBalance(inputPassword);
                break;
            default:
                out.println("Detected incorrect choice. Try again.");
                break;
        }
    }

    public static void workWithCreditCards() {
        String nameOfCreditCard;
        String surnameCreditCardOwner;
        int limitOfCreditCard;
        int balanceOfCreditCard;
        switch (selectedItem) {
            case "add":
                out.print("Write surname of credit card owner: ");
                nameOfCreditCard = sc.next();
                out.print("Write name of credit card: ");
                surnameCreditCardOwner = sc.next();
                out.print("Write limit of credit card: ");
                limitOfCreditCard = sc.nextInt();
                out.print("Write balance of credit card: ");
                balanceOfCreditCard = sc.nextInt();
                listOfCreditCard.put(countOfCreditCards, new CreditCard(surnameCreditCardOwner, nameOfCreditCard, limitOfCreditCard, balanceOfCreditCard));
                boolean isAdd = CreditCard.addNewCreditCard(listOfCreditCard.get(countOfCreditCards));
                if (isAdd) {
                    countOfCreditCards++;
                    break;
                }
                listOfWallets.remove(countOfCreditCards);
                break;
            case "printall":
                CreditCard.printAllCreditCards();
                break;
            case "printbyname":
                out.print("Write surname of owner to find credit card: ");
                surnameCreditCardOwner = sc.next();
                out.print("Write name of credit card to find: ");
                nameOfCreditCard = sc.next();
                CreditCard.printInfoAboutCreditCardByNameCreditCardAndSurnameOwner(nameOfCreditCard, surnameCreditCardOwner);
                break;
            case "countbalance":
                out.print("Write surname of owner to count balance of all his credit cards: ");
                surnameCreditCardOwner = sc.next();
                out.println(surnameCreditCardOwner + " has the balance of all wallets: " + CreditCard.getBalanceOfAllCreditCardBySurnameOfOwner(surnameCreditCardOwner));
                break;
            case "getsurname":
                selectNumberOfCreditCard();
                out.println(listOfCreditCard.get(inputIndexForGetSetDelete).getSurnameOfOwner());
                break;
            case "getname":
                selectNumberOfCreditCard();
                out.println(listOfCreditCard.get(inputIndexForGetSetDelete).getNameOfCard());
                break;
            case "getlimit":
                selectNumberOfCreditCard();
                out.println(listOfCreditCard.get(inputIndexForGetSetDelete).getLimitRUB());
                break;
            case "getbalance":
                selectNumberOfCreditCard();
                out.println(listOfCreditCard.get(inputIndexForGetSetDelete).getBalance());
                break;
            case "getdebt":
                selectNumberOfCreditCard();
                out.println(listOfCreditCard.get(inputIndexForGetSetDelete).getDebt());
                break;
            case "setsurname":
                selectNumberOfCreditCard();
                out.print("Write new surname of credit card owner: ");
                surnameCreditCardOwner = sc.next();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).setSurnameOfOwner(surnameCreditCardOwner, inputPassword);
                break;
            case "setname":
                selectNumberOfCreditCard();
                out.print("Write new name of credit card: ");
                nameOfCreditCard = sc.next();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).setNameOfCard(nameOfCreditCard, inputPassword);
                break;
            case "setlimit":
                selectNumberOfCreditCard();
                out.print("Write new limit of credit card: ");
                limitOfCreditCard = sc.nextInt();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).setLimitRUB(limitOfCreditCard, inputPassword);
                break;
            case "setbalance":
                selectNumberOfCreditCard();
                out.print("Write new balance of credit card: ");
                balanceOfCreditCard = sc.nextInt();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).setBalance(balanceOfCreditCard, inputPassword);
                break;
            case "deletesurname":
                selectNumberOfCreditCard();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).deleteSurnameOfOwner(inputPassword);
                break;
            case "deletename":
                selectNumberOfCreditCard();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).deleteNameOfCard(inputPassword);
                break;
            case "deletelimit":
                selectNumberOfCreditCard();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).deleteLimitRUB(inputPassword);
                break;
            case "deletebalance":
                selectNumberOfCreditCard();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).deleteBalance(inputPassword);
                break;
            case "deletedebt":
                selectNumberOfCreditCard();
                getPasswordFromUser();
                listOfCreditCard.get(inputIndexForGetSetDelete).deleteDebt(inputPassword);
                break;
            default:
                out.println("Detected incorrect choice. Try again.");
                break;
        }
    }

    public static void workWithPotentialCost() {
        String nameOfShop;
        int countOfProduct;
        int summaryPrice;
        switch (selectedItem) {
            case "add":
                out.print("Write name of shop: ");
                nameOfShop = sc.next();
                out.print("Write count of product: ");
                countOfProduct = sc.nextInt();
                out.print("Write summary price: ");
                summaryPrice = sc.nextInt();
                listOfPotentialCosts.put(countOfPotentialCosts, new PotentialCost(nameOfShop, countOfProduct, summaryPrice));
                boolean isAdd = PotentialCost.addNewPotentialCost(listOfPotentialCosts.get(countOfPotentialCosts));
                if (isAdd) {
                    countOfPotentialCosts++;
                    break;
                }
                listOfPotentialCosts.remove(countOfPotentialCosts);
                break;
            case "printall":
                PotentialCost.printPotentialCosts();
                break;
            case "getname":
                selectNumberOfPotentialCost();
                out.println(listOfPotentialCosts.get(inputIndexForGetSetDelete).getNameOfShop());
                break;
            case "getcount":
                selectNumberOfPotentialCost();
                out.println(listOfPotentialCosts.get(inputIndexForGetSetDelete).getCountOfProduct());
                break;
            case "getsum":
                selectNumberOfPotentialCost();
                out.println(listOfPotentialCosts.get(inputIndexForGetSetDelete).getSummaryPrice());
                break;
            case "move":
                selectNumberOfPotentialCost();
                if (PotentialCost.moveCoastFromPotentialToCurrentList(listOfPotentialCosts.get(inputIndexForGetSetDelete))) {
                    listOfCurrentCosts.put(countOfCurrentCosts, new CurrentCost(listOfPotentialCosts.get(inputIndexForGetSetDelete).getNameOfShop(), listOfPotentialCosts.get(inputIndexForGetSetDelete).getCountOfProduct(), listOfPotentialCosts.get(inputIndexForGetSetDelete).getSummaryPrice()));
                    countOfCurrentCosts++;
                    listOfPotentialCosts.remove(inputIndexForGetSetDelete);
                    break;
                }
                listOfCurrentCosts.remove(inputIndexForGetSetDelete);
                break;
            default:
                out.println("Detected incorrect choice. Try again.");
                break;
        }
    }

    public static void workWithCurrentCost() {
        String nameOfShop;
        int countOfProduct;
        int summaryPrice;
        switch (selectedItem) {
            case "add":
                out.print("Write name of shop: ");
                nameOfShop = sc.next();
                out.print("Write count of product: ");
                countOfProduct = sc.nextInt();
                out.print("Write summary price: ");
                summaryPrice = sc.nextInt();
                listOfCurrentCosts.put(countOfCurrentCosts, new CurrentCost(nameOfShop, countOfProduct, summaryPrice));
                boolean isAdd = CurrentCost.addNewCurrentCoast(listOfCurrentCosts.get(countOfCurrentCosts));
                if (isAdd) {
                    countOfCurrentCosts++;
                    break;
                }
                listOfCurrentCosts.remove(countOfCurrentCosts);
                break;
            case "printall":
                CurrentCost.printCurrentCosts();
                break;
            case "getname":
                selectNumberOfCurrentCost();
                out.println(listOfCurrentCosts.get(inputIndexForGetSetDelete).getNameOfShop());
                break;
            case "getcount":
                selectNumberOfCurrentCost();
                out.println(listOfCurrentCosts.get(inputIndexForGetSetDelete).getCountOfProduct());
                break;
            case "getsum":
                selectNumberOfCurrentCost();
                out.println(listOfCurrentCosts.get(inputIndexForGetSetDelete).getSummaryPrice());
                break;
            default:
                out.println("Detected incorrect choice. Try again.");
                break;
        }
    }

    public static void workWithPotentialIncome() {
        String nameOfIncome;
        int amountOfIncome;
        String payeeOfIncome;
        switch (selectedItem) {
            case "add":
                out.print("Write name of income: ");
                nameOfIncome = sc.next();
                out.print("Write amount of income: ");
                amountOfIncome = sc.nextInt();
                out.print("Write payee of income: ");
                payeeOfIncome = sc.next();
                listOfPotentialIncome.put(countOfPotentialIncome, new PotentialIncome(nameOfIncome, amountOfIncome, payeeOfIncome));
                if (PotentialIncome.addNewPotentialIncome(listOfPotentialIncome.get(countOfPotentialIncome))) {
                    countOfPotentialIncome++;
                    break;
                }
                listOfPotentialIncome.remove(countOfPotentialIncome);
                break;
            case "printall":
                PotentialIncome.printPotentialIncome();
                break;
            case "getname":
                selectNumberOfPotentialIncome();
                out.println(listOfPotentialIncome.get(inputIndexForGetSetDelete).getNameOfIncome());
                break;
            case "getamount":
                selectNumberOfPotentialIncome();
                out.println(listOfPotentialIncome.get(inputIndexForGetSetDelete).getAmountOfIncome());
                break;
            case "getpayee":
                selectNumberOfPotentialIncome();
                out.println(listOfPotentialIncome.get(inputIndexForGetSetDelete).getPayeeOfIncome());
                break;
            case "move":
                selectNumberOfPotentialIncome();
                if (PotentialIncome.moveAmountOfPotentialIncomeToWalletAndRemove(listOfPotentialIncome.get(inputIndexForGetSetDelete))) {
                    listOfPotentialIncome.remove(inputIndexForGetSetDelete);
                }
                break;

            default:
                out.println("Detected incorrect choice. Try again.");
                break;
        }
    }

    public static void selectNumberOfWallet() {
        ArrayList<String> arrayOfKeys = new ArrayList<>();
        for (Integer index : listOfWallets.keySet()) {
            String key = index.toString();
            arrayOfKeys.add(key);
            String wallet = listOfWallets.get(index).toString();
            out.println(key + " " + wallet);
        }
        out.print("Choose the number of wallet: ");
        inputIndexForGetSetDelete = sc.nextInt();
        if (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
            while (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
                out.print("Choose correct number: ");
                inputIndexForGetSetDelete = sc.nextInt();
            }
        }
    }

    public static void selectNumberOfCreditCard() {
        ArrayList<String> arrayOfKeys = new ArrayList<>();
        for (Integer index : listOfCreditCard.keySet()) {
            String key = index.toString();
            arrayOfKeys.add(key);
            String creditCard = listOfCreditCard.get(index).toString();
            out.println(key + " " + creditCard);
        }
        out.print("Choose the number of credit card: ");
        inputIndexForGetSetDelete = sc.nextInt();
        if (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
            while (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
                out.print("Choose correct number: ");
                inputIndexForGetSetDelete = sc.nextInt();
            }
        }
    }

    public static void selectNumberOfPotentialCost() {
        ArrayList<String> arrayOfKeys = new ArrayList<>();
        for (Integer index : listOfPotentialCosts.keySet()) {
            String key = index.toString();
            arrayOfKeys.add(key);
            String cost = listOfPotentialCosts.get(index).toString();
            out.println(key + " " + cost);
        }
        out.print("Choose the number of potential cost: ");
        inputIndexForGetSetDelete = sc.nextInt();
        if (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
            while (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
                out.print("Choose correct number: ");
                inputIndexForGetSetDelete = sc.nextInt();
            }
        }
    }

    public static void selectNumberOfCurrentCost() {
        ArrayList<String> arrayOfKeys = new ArrayList<>();
        for (Integer index : listOfCurrentCosts.keySet()) {
            String key = index.toString();
            arrayOfKeys.add(key);
            String cost = listOfCurrentCosts.get(index).toString();
            out.println(key + " " + cost);
        }
        out.print("Choose the number of current cost: ");
        inputIndexForGetSetDelete = sc.nextInt();
        if (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
            while (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
                out.print("Choose correct number: ");
                inputIndexForGetSetDelete = sc.nextInt();
            }
        }
    }

    public static void selectNumberOfPotentialIncome() {
        ArrayList<String> arrayOfKeys = new ArrayList<>();
        for (Integer index : listOfPotentialIncome.keySet()) {
            String key = index.toString();
            arrayOfKeys.add(key);
            String cost = listOfPotentialIncome.get(index).toString();
            out.println(key + " " + cost);
        }
        out.print("Choose the number of potential income: ");
        inputIndexForGetSetDelete = sc.nextInt();
        if (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
            while (!(arrayOfKeys.contains(String.valueOf(inputIndexForGetSetDelete)))) {
                out.print("Choose correct number: ");
                inputIndexForGetSetDelete = sc.nextInt();
            }
        }
    }

    public static void getPasswordFromUser() {
        out.print("Write admin password: ");
        inputPassword = sc.next();
    }

    public static void printOptionsForWalletsAndEnterTheChoice() {
        out.println("""

                List of options:\s
                1. Add new wallet (add)
                2. Print info about all wallets (print all)
                3. Print info about wallet by name of wallet and surname of owner (print by name)
                4. Get balance of all wallets by surname of owner (count balance)
                5. Get surname of wallet owner (get surname)
                6. Get name of wallet (get name)
                7. Get balance of wallet (get balance)
                8. Set surname to wallet owner (need admin password) (set surname)
                9. Set name to wallet (need admin password) (set name)
                10. Set balance to wallet (need admin password) (set balance)
                11. Delete surname of wallet owner (need admin password) (delete surname)
                12. Delete name of wallet (need admin password) (delete name)
                13. Delete balance of wallet (need admin password) (delete balance)
                14. Return to main menu (return)
                """);
        out.print("Input your choice: ");
        selectedItem = sc.next().toLowerCase().replace(" ", "");
    }

    public static void printOptionsForCreditCardsAndEnterTheChoice() {
        selectedItem = "";
        out.println("""

                List of options:\s
                1. Add new credit card (add)
                2. Print info about all credit cards (print all)
                3. Print info about credit card by name of card and surname of owner (print by name)
                4. Get balance of all credit cards by surname of owner (count balance)
                5. Get surname of credit card owner (get surname)
                6. Get name of credit card (get name)
                7. Get limit of credit card (get limit)
                8. Get balance of credit card (get balance)
                9. Get debt of credit card (get debt)
                10. Set surname to credit card owner (need admin password) (set surname)
                11. Set name to credit card (need admin password) (set name)
                12. Set limit to credit card (need admin password) (set limit)
                13. Set balance to credit card (need admin password) (set balance)
                14. Delete surname of credit card owner (need admin password) (delete surname)
                15. Delete name of credit card (need admin password) (delete name)
                16. Delete limit of credit card (need admin password) (delete limit)
                17. Delete balance of credit card (need admin password) (delete balance)
                18. Delete debt of credit card (need admin password) (delete debt)
                19. Return to main menu (return)
                """);
        out.print("Input your choice: ");
        selectedItem = sc.next().toLowerCase().replace(" ", "");
    }

    public static void printOptionsForPotentialCostsAndEnterTheChoice() {
        selectedItem = "";
        out.println("""

                List of options:\s
                1. Add new potential cost (add)
                2. Print info about all potential costs (print all)
                3. Get name of shop (get name)
                4. Get count of product (get count)
                5. Get summary price (get sum)
                6. Move cost from potential to current (move)
                7. Return to main menu (return)
                """);
        out.print("Input your choice: ");
        selectedItem = sc.next().toLowerCase().replace(" ", "");
    }

    public static void printOptionsForCurrentCostsAndEnterTheChoice() {
        selectedItem = "";
        out.println("""

                List of options:\s
                1. Add new current cost (add)
                2. Print info about all current costs (print all)
                3. Get name of shop (get name)
                4. Get count of product (get count)
                5. Get summary price (get sum)
                6. Return to main menu (return)
                """);
        out.print("Input your choice: ");
        selectedItem = sc.next().toLowerCase().replace(" ", "");
    }

    public static void printOptionsForPotentialIncomeAndEnterTheChoice() {
        selectedItem = "";
        out.println("""

                List of options:\s
                1. Add new potential income (add)
                2. Print info about all potential income (print all)
                3. Get name of income (get name)
                4. Get amount of income (get amount)
                5. Get payee of income (get payee)
                6. Move amount of potential income to wallet (move)
                7. Return to main menu (return)
                """);
        out.print("Input your choice: ");
        selectedItem = sc.next().toLowerCase().replace(" ", "");
    }
}