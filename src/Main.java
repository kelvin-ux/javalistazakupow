import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ShoppingList manager = new ShoppingList("produkty.txt");
        manager.load_products_from_file();
        Scanner sc = new Scanner(System.in);
        boolean is_running = true;
        String category, product;

        while(is_running) {
            print_menu();
            int choice = czy_liczba(sc);
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("podaj nazwe kategorii: ");
                    String newCat = sc.nextLine();
                    manager.addCategory(newCat);
                }
                case 2 -> {
                    System.out.print("kategoria: ");
                    category = sc.nextLine();
                    System.out.print("nazwa produktu: ");
                    product = sc.nextLine();
                    manager.addProduct(category, product);
                }
                case 3 -> manager.displayProducts();
                case 4 -> {
                    System.out.println("podaj kategorie:");
                    category = sc.nextLine();
                    manager.dispaly_category(category);
                }
                case 5 -> manager.clear_whole_list();
                case 6 -> {
                    System.out.println("podaj kategorie:");
                    category = sc.nextLine();
                    manager.clear_category(category);
                }
                case 7 -> {
                    System.out.print("kategoria: ");
                    category = sc.nextLine();
                    System.out.print("nazwa produktu(delete): ");
                    product = sc.nextLine();
                    manager.removeProduct(category, product);
                }
                case 8 -> {
                    manager.savetoFile();
                    is_running = false;
                }
                default -> System.out.println("Zly wybor!");
            }
        }
        sc.close();
    }
    public static void print_menu(){
        System.out.println("-----------------------------------------");
        System.out.println("Menu:");
        System.out.println("1. Dodaj kategorię");
        System.out.println("2. dodaj produkt");
        System.out.println("3. wyswietl liste");
        System.out.println("4. wyswietl produkty z kategori");
        System.out.println("5. usun cala liste");
        System.out.println("6. usun kategorie");
        System.out.println("7. usun produkt z kategorii");
        System.out.println("8. wyjdz");
        System.out.print("wybierz(1-8): ");

    }
    private static int czy_liczba(Scanner sc){
        while(!sc.hasNextInt()){
            System.out.print("bledne wprowadzenie, try again!\n");
            sc.next();
        }
        return sc.nextInt();
    }
}