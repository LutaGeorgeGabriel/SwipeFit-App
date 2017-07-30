package eu.swipefit.application;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Product represents the main representation for a physical product in my application
 *
 * It describes the fields that the product is composed of and I set up getters and setters to interact with the Product's attributes*/

public class Product {

    /**
     * I used @SerializedName because its purpose as an annotation is that it indicates that a
     * member should be serialized to JSON with the provided name value as its field name.
     *
     *  NOTE: The value you specify in this annotation must be a valid JSON field name.
     *
     * Also @Exposed is an an annotation that indicates that a member should be exposed for JSON
     * serialization or deserialization.
     *
     *  NOTE: The @Expose annotation is useful in a style of programming where you want to explicitly
     *  specify all fields that should get considered for serialization or deserialization.
     *
     * Both of the annotations are part of Gson 2.6.2 API
     *
     * */

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("site")
    @Expose
    private String site;

    @SerializedName("retailer")
    @Expose
    private String retailer;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("ID")
    @Expose
    private String ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}