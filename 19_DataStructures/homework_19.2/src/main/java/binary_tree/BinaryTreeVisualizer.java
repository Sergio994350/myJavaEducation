package binary_tree;


public final class BinaryTreeVisualizer {

    public static String display(BinaryTree binaryTree) {
        final int height = 5, width = 64;

        int len = width * height * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 1; i <= len; i++)
            sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

        displayR(sb, width / 2, 1, width / 4, width, binaryTree.getRoot(), " ");
        return sb.toString();
    }

    private static void displayR(StringBuilder sb, int c, int r, int d, int w, Node n,
                                 String edge) {
        if (n != null) {
            displayR(sb, c - d, r + 2, d / 2, w, n.getLeft(), " /");

            String s = String.valueOf(n.getData());
            int idx1 = r * w + c - (s.length() + 1) / 2;
            int idx2 = idx1 + s.length();
            int idx3 = idx1 - w;
            if (idx2 < sb.length())
                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);

            displayR(sb, c + d, r + 2, d / 2, w, n.getRight(), "\\ ");
        }
    }
}
