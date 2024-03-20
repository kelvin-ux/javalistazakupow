import java.io.*;
import java.util.*;


public class ShoppingList {
    private String filename_products;
    private Map<String, List<String>> products = new HashMap<>();

    public ShoppingList(String filename_products){
        this.filename_products = filename_products;
    }

    public void load_products_from_file(){
        try(BufferedReader reader = new BufferedReader(new FileReader(filename_products))){
            String ln;
            String curCat = null;
            while((ln = reader.readLine()) != null){
                if(ln.startsWith("&")){
                    curCat = ln.substring(1);
                    products.putIfAbsent(curCat, new ArrayList<>());
                } else if(curCat != null) {
                    products.get(curCat).add(ln);
                }
            }
        } catch (IOException e) {
            System.out.println("nie mozna otworzyc pliku");
        }
    }

    public void addCategory(String category){
        if (!products.containsKey(category)) {
            products.put(category, new ArrayList<>());
            System.out.println("dodano kategorie:" + category);
        } else {
            System.out.println("kategoria juz instnieje");
        }
    }

    public void addProduct(String category, String product){
        if(products.containsKey(category)){
            products.get(category).add(product);
            System.out.println("dodano do kategori:" + category);
        } else {
            System.out.println("nie ma takiej kategori");
        }
    }

    public void displayProducts(){
        if(products.isEmpty()){
            System.out.println("lista jest pusta");
            return;
        }
        products.forEach((category, productList) -> {
            System.out.println(category + ":");
            productList.forEach(prod -> System.out.println(" - " + prod));
        });
    }
    public void removeProduct(String category, String product) {
        if (products.containsKey(category)) {
            List<String> productList = products.get(category);
            if (productList.remove(product)) {
                System.out.println("usunieto '" + product + "'z '" + category);
            } else {
                System.out.println("nie ma produktu '" + product + "' w '" + category);
            }
        } else {
            System.out.println(category + "' nie istnieje");
        }
    }
    public void savetoFile(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename_products))){
            products.forEach((category, productList) -> {
                writer.println("&" + category);
                productList.forEach(writer::println);
            });
            System.out.println("zapisano");
        } catch (IOException e) {
            System.out.println("nie mozna utworzyc pliku");
        }
    }
    public void dispaly_category(String category){
        if(products.containsKey(category)){
            System.out.println(category + ":");
            products.get(category).forEach(product -> System.out.println(" - " + product));
        }else{
            System.out.println("nie ma takiej kategori");
        }
    }
    public void clear_whole_list(){
        products.clear();
        System.out.println("lista zostala usunieta");
    }
    public void clear_category(String category){
        if(products.containsKey(category)){
            products.remove(category);
            System.out.println("usunieto kategorie: " + category);
        }else{
            System.out.println("nie ma takiej kategori");
        }
    }
}