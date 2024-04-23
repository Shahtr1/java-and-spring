package comparator_IO_dataTimeAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import comparator_IO_dataTimeAPI.comparators.ProductComparator;

class Main {

    private static final Category kitchen = new Category(1L, "Kitchen");
    private static final Category bathroom = new Category(2L, "Bathroom");
    private static final Category livingRoom = new Category(3L, "Living Room");

    public static void main(String[] args) {

        Product riceCooker = new Product("Rice Cooker", 5000, kitchen);
        Product flask = new Product("Flask", 2000, kitchen);
        Product cup = new Product("Cup", 300, kitchen);

        Product tile = new Product("Tile", 15000, bathroom);
        Product shower = new Product("Shower", 4000, bathroom);
        Product bucket = new Product("Bucket", 500, bathroom);

        Product sofa = new Product("Sofa", 45000, livingRoom);
        Product diningTable = new Product("DiningTable", 34000, livingRoom);
        Product painting = new Product("Painting", 100000, livingRoom);

        List<Product> products = new ArrayList<>();
        products.add(riceCooker);
        products.add(flask);
        products.add(cup);

        products.add(tile);
        products.add(shower);
        products.add(bucket);

        products.add(sofa);
        products.add(diningTable);
        products.add(painting);

        Collections.sort(products, ProductComparator.byCategoryName);
        System.out.println(products);

        List<Object> objects1 = new ArrayList<>();
        // objects1 = products; // generics are invariant

        List<? extends Object> objects2 = new ArrayList<>();
        objects2 = products;

        System.out.println(objects1);
        System.out.println(objects2);

        Map<String, Product> pMap = new HashMap<>();
        pMap.put("bucket", null);

        // GetOrDefault Example
        System.out.println("bucket -> " + pMap.getOrDefault("bucket", sofa));
        System.out.println("bucket -> " + pMap.getOrDefault("bucketKeyNotPresent", sofa));

        Product updatedBucket = new Product("Bucket", 800, bathroom);

        // ListIterator<Product> listIterator = products.listIterator();
        // while (listIterator.hasNext()) {
        // Product next = listIterator.next();
        // if (next.getName().equals(updatedBucket.getName())) {
        // listIterator.set(updatedBucket);
        // }
        // }

        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            if (currentProduct.getName().equals(updatedBucket.getName())) {
                products.set(i, updatedBucket);
            }
        }

        System.out.println(products);

    }
}
