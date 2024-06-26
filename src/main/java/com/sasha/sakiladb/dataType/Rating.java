package com.sasha.sakiladb.dataType;
import java.util.stream.Stream;
public enum Rating {
    G("G"), PG("PG"), R("R"), NC17("NC-17"), PG13("PG-13");

    private String code;

    private Rating(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }


}
