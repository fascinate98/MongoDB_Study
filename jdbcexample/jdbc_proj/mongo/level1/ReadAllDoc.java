package mongo.level1;

import org.bson.Document; //이 api가 쓰이고이씀
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadAllDoc {
    public static void main(String[] args) {
        try {
            Logger mongoLogger = Logger.getLogger("org.mongodb.driver"); // 로거
            mongoLogger.setLevel(Level.SEVERE);

            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase db = mongoClient.getDatabase("edudb");
            MongoCollection<Document> collection = db.getCollection("book");
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            System.out.println("--------------------------------");
            cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(doc.get("name") + " : " + doc.get("price"));
            }
            System.out.println("--------------------------------");
            Consumer<Document> printConsumer = new Consumer<Document>() {
                @Override
                public void accept(final Document document) {
                    System.out.println(document.toJson());
                }
            };
            collection.find().forEach(printConsumer);
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }
}
