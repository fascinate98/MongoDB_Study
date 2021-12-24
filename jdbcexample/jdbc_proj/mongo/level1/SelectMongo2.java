package mongo.level1;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class SelectMongo2 {
	public static void main(String[] args) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");  //
		mongoLogger.setLevel(Level.SEVERE);    //로그메세지 안뜨게해주는애 
		try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);  //컬렉션객체 얻어냄 
            MongoDatabase db = mongoClient.getDatabase("edudb");
            MongoCollection<Document> collection = db.getCollection("book");
            MongoCursor<Document>  cursor = collection.find().iterator();  //이터레이터 호출 해서 몽고커서라는 객체로 
            //받아옴 
            while(cursor.hasNext()) {
            	System.out.println(cursor.next()); //몽고커서안에잇는 도큐먼트 하나씪꺼내서 출력            
            }  		          
            System.out.println("--------------------------------");
            cursor = collection.find().iterator(); //다 꺼내고 나면 다시 이터레이터  코드방향으로 한번만 읽을 수 있어서
            //파인드를 또 해줘야함. 
            while(cursor.hasNext()) {
            	Document doc = cursor.next();
            	System.out.println(doc.get("name") + " : " + doc.get("price"));      
                System.out.println(doc.get("price").getClass().getName());     
            }      
            System.out.println("--------------------------------");
            Consumer<Document> printConsumer1 = new Consumer<Document>() {
                @Override
                public void accept(final Document document) {
                    System.out.println(document.toJson());
                }
            };            
           collection.find().forEach(printConsumer1);
           System.out.println("--------------------------------");
           Consumer<Document> printConsumer2 = doc -> System.out.println(doc.toJson());                    
           collection.find().forEach(printConsumer2);
            mongoClient.close();
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
	}
}



