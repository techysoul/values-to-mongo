package com.test;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.bson.Document;

public class MongoTester {

    //https://mlab.com/databases/techysoul/collections/trades
    //private static final String MONGODB_URI = "mongodb://xxxxx:xxxxx@ds261969.mlab.com:61969/?authSource=techysoul&ssl=false";
    private static final String MONGODB_URI = "mongodb://localhost:27017";

    public static void main(String[] args) throws IOException {
        String path = "/Users/madhav/github/values-to-mongo/src/main/resources/data.xml";
        String xml = FileUtils.readFileToString(new File(path),"UTF-8");
        JSONObject json = XML.toJSONObject(xml);
        System.out.println(xml);
        System.out.println(json);

        final Document doc = Document.parse(json.toString());
        doc.append("correctDate",new Date());
        System.out.println(doc);

        MongoDatabase db = new MongoClient(new MongoClientURI(MONGODB_URI)).getDatabase("goldeneye");
        db.getCollection("trades").insertOne(doc);


    }

}
