package market.constant;

public class ServiceImg {
    private int postId;
    private String image1;


    @Override
    public String toString() {
        return "ServiceImg{" +
                "postId='" + postId + '\'' +
                ", image1='" + image1 + '\'' +
                '}';
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }
}
