import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

    public class Main {
        private static final String HOST = "127.0.0.1";
        private static final int PORT = 27017;
        private static final String DB = "local";
        final static String collectionStores = "stores";
        final static String collectionProducts = "products";
        final static String cmdAddStoreReg = "ADD_STORE";
        final static String cmdAddProductReg = "ADD_PRODUCT";
        final static String cmdDisplayProductReg = "EXPO_PRODUCT";
        final static String cmdStatisticsProductReg = "STATISTICS";
        final static String exitCommand = "EXIT";
        static JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);

        public static void main(String[] args) {

            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase database = mongoClient.getDatabase(DB);

            MongoCollection<Document> stores = database.getCollection(collectionStores);
            MongoCollection<Document> products = database.getCollection(collectionProducts);
            //stores.drop();
            //products.drop();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Please, use next commands:\nADD_STORE store_name\n" +
                    "ADD_PRODUCT product_name product_price" +
                    "\nEXPO_PRODUCT product_name store_name" +
                    "\nSTATISTICS\nEXIT");
            for (; ; ) {
                String cmd = scanner.nextLine();
                if (checkExit(cmd)) {
                    break;
                } else
                if (checkAddStoreCmd(cmd)) {
                    addStore(cmd, stores);
                    writeMemberOfCollection(stores);
                } else
                if (checkAddProductCmd(cmd)) {
                    addProduct(cmd, products);
                    writeMemberOfCollection(products);
                } else
                if (checkDisplayProductCmd(cmd)) {
                    displayProductToStore(cmd, products, stores);
                    writeMemberOfCollection(stores);
                } else
                if (checkProductStatisticsCmd(cmd)) {
                    writeProductStatistics(products, stores);
                } else {
                    System.out.println("<!> Wrong command! Please, repeat");
                }
            }
        }

        private static boolean checkExit(String cmd) {
            return (cmd.contains(exitCommand) && (cmd.trim().split(" ").length == 1));
        }

        private static boolean checkAddStoreCmd(String cmd) {
            return (cmd.contains(cmdAddStoreReg) && (cmd.trim().split(" ").length == 2));
        }

        private static boolean checkAddProductCmd(String cmd) {
            return (cmd.contains(cmdAddProductReg) && (cmd.trim().split(" ").length == 3));
        }

        private static boolean checkDisplayProductCmd(String cmd) {
            return (cmd.contains(cmdDisplayProductReg) && (cmd.trim().split(" ").length == 3));
        }

        private static boolean checkProductStatisticsCmd(String cmd) {
            return (cmd.contains(cmdStatisticsProductReg) && (cmd.trim().split(" ").length == 1));
        }


        private static void addStore(String cmd, MongoCollection<Document> collectionStore) {
            cmd = cmd.replaceAll(cmdAddStoreReg, "").trim();
            Document storeDoc = new Document().append("Store", cmd).append("Products", new ArrayList<String>());
            collectionStore.insertOne(storeDoc);
            System.out.println("Store \"" + cmd + "\" just added");
        }

        private static void addProduct(String cmd, MongoCollection<Document> collectionProducts) {
            int productName = 0;
            int productPrice = 1;
            String[] cmdArray = cmd.replaceAll(cmdAddProductReg, "").trim().split(" ");
            Document productDoc = new Document().append("Product", cmdArray[productName]).append("Price", Integer.valueOf(cmdArray[productPrice]));
            collectionProducts.insertOne(productDoc);
            System.out.println("Product \"" + cmdArray[productName] + "\" by price " + cmdArray[productPrice] + "RUB just added");
        }

        private static void displayProductToStore(String cmd, MongoCollection<Document> collectionProducts, MongoCollection<Document> collectionStore) {
            int productName = 0;
            int storeName = 1;
            String[] cmdArray = cmd.replaceAll(cmdDisplayProductReg, "").trim().split(" ");
            if (checkExistenceProduct(cmdArray[productName], collectionProducts) && checkExistenceStore(cmdArray[storeName], collectionStore)) {
                Document storeDoc = collectionStore.find(eq("Store", cmdArray[storeName])).first();
                ArrayList<String> productsList = (ArrayList<String>) storeDoc.get("Products");
                System.out.println(productsList);
                if ((productsList.size() == 0) || (!productsList.contains(cmdArray[productName]))) {
                    productsList.add(cmdArray[productName]);
                }
                collectionStore.findOneAndUpdate(eq("Store", cmdArray[storeName]), new Document("$set", new Document("Products", productsList)));
                System.out.println("Product \"" + cmdArray[productName] + "\" just added to store \"" + cmdArray[storeName] + "\"");
            }
        }

        private static void writeProductStatistics(MongoCollection<Document> collectionProducts, MongoCollection<Document> collectionStore) {
            AggregateIterable<Document> productsPciceStatistcs = collectionStore.aggregate(Arrays.asList(
                    Aggregates.lookup(collectionProducts.getNamespace().getCollectionName(), "Products", "Product", "Products"),
                    Aggregates.unwind("$Products"),
                    Aggregates.group("$Store", Accumulators.sum("ProductsSum", 1),
                            Accumulators.avg("AvgPrice", "$Products.Price"),
                            Accumulators.max("MaxPrice", "$Products.Price"),
                            Accumulators.min("MinPrice", "$Products.Price"))
            ));
            AggregateIterable<Document> productsPriceLTs100 = collectionStore.aggregate(Arrays.asList(
                    Aggregates.lookup(collectionProducts.getNamespace().getCollectionName(), "Products", "Product", "Products"),
                    Aggregates.unwind("$Products"),
                    Aggregates.match(lt("Products.Price", 100)),
                    Aggregates.group("$Store", Accumulators.sum("ProductLowPriceSum", 1))
            ));
            System.out.println("Statistics for each store: ");

            productsPciceStatistcs.forEach((Consumer<Document>) doc -> {
                System.out.println(doc.toJson(writerSettings));
            });
            productsPriceLTs100.forEach((Consumer<Document>) doc -> {
                System.out.println(doc.toJson(writerSettings));
            });
        }

        public static boolean checkExistenceProduct(String productName, MongoCollection<Document> collectionProducts){
            if (collectionProducts.find(eq("Product", productName)).first() != null) {
                return true;
            }
            System.out.println("<!>Please, add product first!");
            return false;
        }

        public static boolean checkExistenceStore (String storeName, MongoCollection<Document> collectionStores){
            if (collectionStores.find(eq("Store", storeName)).first() != null) {
                return true;
            }
            System.out.println("<!>Please, add store first!");
            return false;
        }

        public static void writeMemberOfCollection(MongoCollection<Document> collection) {
            collection.find().forEach((Consumer<Document>) doc -> {
                System.out.println(doc.toJson(writerSettings));
            });
        }

    }