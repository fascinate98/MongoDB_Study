package mongo.level1;

import org.bson.Document;  //이 api가 쓰이고이씀
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadAllCollections {
    public static void main(String[] args) {
        try {
            Logger mongoLogger = Logger.getLogger("org.mongodb.driver");  //로거 
            mongoLogger.setLevel(Level.SEVERE);

            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase db = mongoClient.getDatabase("edudb");
            MongoCollection<Document> collection = db.getCollection("book");
            MongoCursor<Document> cursor = collection.find().iterator();  //이터러블한 객체. 몽고커서가 리턴댐 
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }
}
