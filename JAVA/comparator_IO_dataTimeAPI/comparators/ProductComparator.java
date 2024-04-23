package comparator_IO_dataTimeAPI.comparators;

import java.util.Comparator;

import comparator_IO_dataTimeAPI.Product;

public class ProductComparator {
    public static Comparator<Product> descPrice = new Comparator<Product>() {

        @Override
        public int compare(Product o1, Product o2) {
            return o2.getPrice() - o1.getPrice();
        }

    };

    public static Comparator<Product> byCategoryName = new Comparator<Product>() {

        @Override
        public int compare(Product o1, Product o2) {
            return o2.getCategory().getName().compareTo(o1.getCategory().getName());
        }

    };
}
