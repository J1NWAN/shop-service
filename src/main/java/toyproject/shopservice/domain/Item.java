package toyproject.shopservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

    private String tokken;
    private Long id;
    private String itemName;
    private Integer price;

    public Item() {}

    public Item(String tokken, String itemName, Integer price) {
        this.tokken = tokken;
        this.itemName = itemName;
        this.price = price;
    }
}
