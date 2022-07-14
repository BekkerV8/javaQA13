package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book1 = new Book(1, "МАСТЕР И МАРГАРИТА", 1_000, "БУЛГАКОВ М.А.");
    private Book book2 = new Book(2, "ПРЕСТУПЛЕНИЕ И НАКАЗАНИЕ", 550, "ДОСТОЕВСКИЙ Ф.М.");
    private Book book3 = new Book(3, "ВОЙНА И МИР", 1_500, "ТОЛСТОЙ Л.Н.");
    private Smartphone smartphone1 = new Smartphone(4, "iPhone 13", 80_000, "Apple");
    private Smartphone smartphone2 = new Smartphone(5, "iPhone 13pro", 90_000, "Apple");
    private Smartphone smartphone3 = new Smartphone(6, "iPhone 13proMax", 100_000, "Apple");

    @BeforeEach
    public void SetUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
    }
    @Test
    public void shouldSave() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
        Product[] expected = {book1, book2, book3, smartphone1, smartphone2, smartphone3};
    }

    @Test
    public void shouldSearchNameBook() {
        Product[] expected = new Product[]{book3};
        Product[] actual = manager.searchBy("ВОЙНА И МИР");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNameSmartphone() {
        Product[] expected = new Product[]{smartphone3};
        Product[] actual = manager.searchBy("iPhone 13proMax");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMultipleProducts() {
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("МАСТЕР И МАРГАРИТА");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotFoundProduct() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("tShirt");
        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchNotFoundSmartphone() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Sumsung");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAll() {

        Product[] expected = new Product[]{book1, book2, book3, smartphone1, smartphone2, smartphone3};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindId() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("0");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralItemsByName() {

        Product[] expected = new Product[]{book3};
        Product[] actual = manager.searchBy("ВОЙНА");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralDifferentItemsByName() {

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("iPhone 13" + "ВОЙНА И МИР");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralDifferent() {

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("iPhone 13" + "ТОЛСТОЙ Л.Н.");
        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldFindAuthor() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("БУЛГАКОВ М.А.");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNameBook() {
        Product[] expected = {book1};
        Product[] actual = manager.searchBy("МАСТЕР И МАРГАРИТА");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNameBookAndAuthor() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("ПРЕСТУПЛЕНИЕ И НАКАЗАНИЕ" + "ДОСТОЕВСКИЙ Ф.М.");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNameBookAndId() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("ПРЕСТУПЛЕНИЕ И НАКАЗАНИЕ" + 2);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindPhoneName() {
        Product[] expected = {smartphone3};
        Product[] actual = manager.searchBy("iPhone 13proMax");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindPhoneManufacturer() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindPhoneManufacturerAndCost() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Apple" + 100_000);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindCost() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("90_000");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindCostNull() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Книга");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFound() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Something");
        assertArrayEquals(expected, actual);
    }
}
