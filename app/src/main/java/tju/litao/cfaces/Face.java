package tju.litao.cfaces;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by litao on 2016/5/9.
 */
public class Face {

    private  long faceID;
    private  String name;
    private boolean trained= false;
    private long lastUpdated;
    private Contact contact;

    private Set<Sample> trainingSet;
    private Set<Sample> photos;

    private Face(){
    }

    public Face(Contact contact){
        this.name = contact.getName();
        this.contact = contact;
        this.trainingSet = new HashSet<Sample>();
        this.photos = new HashSet<Sample>();
    }

    public void train(Set<Sample> traingSet){
        //TODO:training
        this.trainingSet = traingSet;
        this.trained= true;
        saveIntoDB();
    }

    public boolean update(String location){
        if(!trained){
            return false;
        }
        //readFromDB()
        //TODO：打开location的位置的所有图片，并且进行识别
        saveIntoDB();
        lastUpdated= 1;
        return true;
    }

    private void saveIntoDB(){
        //TODO:存储到数据库
    }

    public void setFaceID( long faceID){
        this.faceID= faceID;
    }

    public long getFaceID(){
        return faceID;
    }
    public String getName() {
        return name;
    }

    public boolean hasBeenTrained() {
        return trained;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }
}
