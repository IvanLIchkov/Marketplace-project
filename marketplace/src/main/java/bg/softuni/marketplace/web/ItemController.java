package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.dto.AddItemDto;
import bg.softuni.marketplace.model.dto.ItemDetailsDto;
import bg.softuni.marketplace.model.dto.ShowItemDto;
import bg.softuni.marketplace.service.CategoryService;
import bg.softuni.marketplace.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    public ItemController(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @GetMapping("/add")
    public String add(){
        return "add-item";
    }

    @ModelAttribute("addItemDto")
    public AddItemDto addItemDto(){
        return new AddItemDto();
    }

    @ModelAttribute("categories")
    public List<CategoryEntity> categoryEntities(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public String add(@Valid AddItemDto addItemDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addItemDto", bindingResult);
            redirectAttributes.addFlashAttribute("addItemDto", addItemDto);
            return "redirect:/items/add";
        }
        String fileName ="";
        if(!multipartFile.isEmpty()){
            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            addItemDto.setPictureName(fileName);
        }else {
            if(addItemDto.getPicture().isEmpty()){
                addItemDto.setPicture(null);
            }
        }
        this.itemService.addItem(addItemDto,fileName, multipartFile);
        return "redirect:/";
    }
    @GetMapping("/all")
    public ModelAndView showAllItems( ModelAndView modelAndView){
        List<ShowItemDto> showItemDtos = this.itemService.allItems();
        modelAndView.setViewName("show-all-items");
        modelAndView.addObject("allItems", showItemDtos);
        return modelAndView;
    }

    @GetMapping("/all/{id}")
    public ModelAndView itemsSpecificCategory(@PathVariable String id, ModelAndView modelAndView){
        List<ItemEntity> itemEntities = itemService.allItemsByType(Long.valueOf(id));
        modelAndView.setViewName("show-items-by-category");
        modelAndView.addObject("items", itemEntities);
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView itemDetails(@PathVariable String id, ModelAndView modelAndView){
        ItemDetailsDto itemDetailsDto = this.itemService.itemDetailsById(id);
        modelAndView.setViewName("item-details");
        modelAndView.addObject("itemDetails", itemDetailsDto);
        return modelAndView;
    }
}
