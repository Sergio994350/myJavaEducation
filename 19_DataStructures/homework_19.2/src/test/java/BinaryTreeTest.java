import binary_tree.BinaryTree;
import binary_tree.BinaryTreeVisualizer;
import binary_tree.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование бинарного дерева поиска")
public class BinaryTreeTest {
    private BinaryTree binaryTree;

    @BeforeEach
    void setUp() {
        binaryTree = new BinaryTree();
    }

    @Test
    @DisplayName("Добавление одного элемента")
    void addOneItemInEmptyTree() {
        String rootData = "1";
        binaryTree.addNode(rootData);

        Node root = binaryTree.getRoot();
        assertEquals(rootData, root.getData());
        assertNull(root.getLeft());
        assertNull(root.getRight());
    }

    @Test
    @DisplayName("Добавление корня и элемента справа")
    void addRootAndOneRight() {
        binaryTree.addNode("1");
        binaryTree.addNode("2");

        Node root = binaryTree.getRoot();
        assertNull(root.getLeft());
        assertNotNull(root.getRight());
        assertEquals("2", root.getRight().getData());
    }

    @Test
    @DisplayName("Добавление корня и элемента справа")
    void addRootAndOneLeft() {
        binaryTree.addNode("9");
        binaryTree.addNode("3");

        Node root = binaryTree.getRoot();
        assertNotNull(root.getLeft());
        assertNull(root.getRight());
        assertEquals("3", root.getLeft().getData());
    }

    @Test
    @DisplayName("Добавление одинаковых элементов")
    void addEqualsData() {
        binaryTree.addNode("9a");
        binaryTree.addNode("9a");

        Node root = binaryTree.getRoot();
        assertNull(root.getLeft());
//        assertNull(root.getRight());
        assertEquals("9a", root.getData());
    }

    /*
             -------------55---------------
           /                               \
    -----44l-----                     -----69r-----
   /              \                 /              \
  40l             46r              58l             80r
/                                                /      \
34l                                              79l     81r
     */
    @Test
    @DisplayName("Бинарное дерево с тремя уровнями")
    void testBinaryTreeWithThreeLayers() {

        binaryTree.addNode("55");
        binaryTree.addNode("44l");
        binaryTree.addNode("69r");
        binaryTree.addNode("40l");
        binaryTree.addNode("34l");
        binaryTree.addNode("46r");
        binaryTree.addNode("80r");
        binaryTree.addNode("81r");
        binaryTree.addNode("79l");
        binaryTree.addNode("58l");

        String actualTree = BinaryTreeVisualizer.display(binaryTree);

        String expectedTree =
                "                              55               \n" +
                        "               /                               \\\n" +
                        "             44l                               69r     \n" +
                        "      /             \\                 /              \\\n" +
                        "     40l             46r              58l             80r\n" +
                        "   /                                                /      \\\n" +
                        "  34l                                              79l     81r";

        System.out.printf(" -> Expected:%n%s%n%n -> Actual:%n%s%n", expectedTree, actualTree);

        Node root = binaryTree.getRoot();

        assertEquals("55", root.getData());
        //left branch
        assertEquals("44l", root.getLeft().getData());
        assertEquals("46r", root.getLeft().getRight().getData());
        assertEquals("40l", root.getLeft().getLeft().getData());
        assertEquals("34l", root.getLeft().getLeft().getLeft().getData());
        //right branch
        assertEquals("69r", root.getRight().getData());
        assertEquals("58l", root.getRight().getLeft().getData());
        assertEquals("80r", root.getRight().getRight().getData());

        assertEquals("79l", root.getRight().getRight().getLeft().getData());
        assertEquals("81r", root.getRight().getRight().getRight().getData());
    }

    @Test
    @DisplayName("Поиск по бинарному дереву: элемент существует")
    void searchExistedNodeInTree() {
        binaryTree.addNode("55");
        binaryTree.addNode("44l");
        binaryTree.addNode("69r");
        binaryTree.addNode("40l");

//        assertTrue(binaryTree.isContains("40l"));
    }

    @Test
    @DisplayName("Поиск по бинарному дереву: элемент НЕ существует")
    void searchNotExistedNodeInTree() {
        binaryTree.addNode("55");
        binaryTree.addNode("44l");
        binaryTree.addNode("69r");
        binaryTree.addNode("40l");

        assertFalse(binaryTree.isContains("999"));
    }

}
