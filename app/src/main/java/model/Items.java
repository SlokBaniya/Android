package model;

public class Items {

    private String itemsname, price, desc, category, image, created_at, updated_at;

    private int id;
    private String CURRENT_TIMESTAMP;

    public Items(String itemsname, String price, String desc, String category, String image, String created_at, String updated_at) {
        this.itemsname = itemsname;
        this.price = price;
        this.desc = desc;
        this.category = category;
        this.image = image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }



    public String getItemsname() {
        return itemsname;
    }

    public void setItemsname(String itemsname) {
        this.itemsname = itemsname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCURRENT_TIMESTAMP() {
        return CURRENT_TIMESTAMP;
    }

    public void setCURRENT_TIMESTAMP(String CURRENT_TIMESTAMP) {
        this.CURRENT_TIMESTAMP = CURRENT_TIMESTAMP;
    }


}
