package toyproject.shopservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.shopservice.domain.Item;
import toyproject.shopservice.domain.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/shop/items")
public class ItemController {


    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * index.html
     * @param tokken: guest, admin
     * @param model
     * @return tokken
     */
    @GetMapping
    public String items(String tokken, Model model) {
        List<Item> items = itemRepository.findAll();

        model.addAttribute("tokken", items.get(0).getTokken());
        model.addAttribute("items", items);

        return "shop/items";
    }

    @GetMapping("{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "shop/item";
    }

    @GetMapping("/add")
    public String add() {
        return "shop/addForm";
    }

    @PostMapping("/add")
    public String addItem(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/shop/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("admin", "ItemA", 10000));
        itemRepository.save(new Item("admin", "ItemB", 20000));
    }
}
