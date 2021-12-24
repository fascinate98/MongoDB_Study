package lab;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class SelectMongoLab {
    public static void main(String[] args) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");  //
		mongoLogger.setLevel(Level.SEVERE);    //로그메세지 안뜨게해주는애 
		try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);  //컬렉션객체 얻어냄 
            MongoDatabase db = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = db.getCollection("avengers");
            MongoCursor<Document>  cursor = collection.find().iterator();  //이터레이터 호출 해서 몽고커서라는 객체로 

            cursor = collection.find().iterator(); 
            //파인드를 또 해줘야함. 
            while(cursor.hasNext()) {
            	Document doc = cursor.next();
            	System.out.println(doc.get("name") + " 팀원의 나이는 " + doc.getDouble("age").intValue() + "입니다.");       
                System.out.println("[좋아하는 책리스트]"); 

                List<Document> questionAnswers = (List<Document>)doc.get("book");
                for (Document questionAnswer: questionAnswers) {
                    // do whatever you need here
                    System.out.println("책제목 : " + questionAnswer.getString("bookName"));
                    System.out.println("출판사 : " +questionAnswer.getString("publisher"));
                    System.out.println("장르 : " +questionAnswer.getString("genre"));
                }
                System.out.println("--------------------------------");
            }      

            mongoClient.close();
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
	}

  
}
