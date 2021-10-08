
package com.mycompany.vocabularybuilder;

import java.sql.Connection;




public class Database {
    static final String dbLocation = System.getProperty("user.dir") + "/src/main/java/com/mycompany/vocabularybuilder/";
    static final String dbName = "vocabularyBuilder.db";
    
    
    static final String url = "jdbc:sqlite:" + dbLocation + dbName;
    static Connection connection = null;

}
