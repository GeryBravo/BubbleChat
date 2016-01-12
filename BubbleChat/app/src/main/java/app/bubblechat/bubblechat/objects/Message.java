package app.bubblechat.bubblechat.objects;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Ben on 2016.01.11..
 */



@ParseClassName("Message")
public class Message extends ParseObject {

    public String getUserId() {
        return getString("userId");
    }
    public String getAdressId() {
        return getString("adressId");
    }
    public String getBody() {
        return getString("body");
    }
    public String getType(){return getString("type");}
    public void setUserId(String userId) {
        put("userId", userId);
    }
    public void setAdressId(String adressId) {
        put("adressId", adressId);
    }
    public void setBody(String body) {
        put("body", body);
    }
    public void setType(String type) { put("type", type);}
}