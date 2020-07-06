package com.project.back.models;
import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "tweet")
public class Tweet {
    //Declaración de datos sin guardar en BD
    @Id
    public String _id; 	//mandar
    public long tweetId;
    public String text;	//mandar
    public Date createdAt; //mandar
    public double latitude;
    public double longitude;
    public String city;
    public String country;
    public long userId;
    public String userName;  //mandar
    public int followersCount; //mandar
    public String sentimentAnalysis;

    public Tweet(){}

   //Se crea tweet con sus respectivos atributos, para luego ser alamacenado en una BD.
   public Tweet(long tweetId, String text, Date createdAt, double latitude, double longitude,
                 String city, String country, long userId, String userName, int followersCount, String sentimentAnalysis) {
		this.tweetId = tweetId;
		this.text = text;
		this.createdAt = createdAt;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.country = country;
		this.userId = userId;
		this.userName = userName;
		this.followersCount = followersCount;
		this.sentimentAnalysis = sentimentAnalysis;
	}

    //Funciones get y set para los atributos de Tweet (tal vez no tengan utilidad)
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

	public String getSentimentAnalysis() {
		return sentimentAnalysis;
	}

	public void setSentimentAnalysis(String sentimentAnalysis) {
		this.sentimentAnalysis = sentimentAnalysis;
	}

}
