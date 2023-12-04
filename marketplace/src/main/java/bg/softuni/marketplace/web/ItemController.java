package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.dto.*;
import bg.softuni.marketplace.service.CategoryService;
import bg.softuni.marketplace.service.FileService;
import bg.softuni.marketplace.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final CategoryService categoryService;
   private final ItemService itemService;
   private final FileService fileService;

    @Autowired
    public ItemController(CategoryService categoryService, ItemService itemService, FileService fileService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.fileService = fileService;
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
                      @AuthenticationPrincipal UserDetails seller) throws IOException {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addItemDto", bindingResult);
            redirectAttributes.addFlashAttribute("addItemDto", addItemDto);
            return "redirect:/items/add";
        }
        this.itemService.addItem(addItemDto, seller);
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
    public ModelAndView itemsSpecificCategory(@PathVariable Long id, ModelAndView modelAndView){

        List<ShowItemWithCategoryDto> itemEntities = itemService.allItemsByType(id);
        modelAndView.setViewName("show-items-by-category");
        modelAndView.addObject("items", itemEntities);
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView itemDetails(@PathVariable Long id, ModelAndView modelAndView,
                                    @AuthenticationPrincipal UserDetails viewer){
        ItemDetailsDto itemDetailsDto = this.itemService.itemDetailsById(id, viewer);
        modelAndView.setViewName("item-details");
        modelAndView.addObject("itemDetails", itemDetailsDto);
        return modelAndView;
    }
    @GetMapping("/download/{imgId}")
    public HttpEntity<byte[]> downloadImg(@PathVariable Long imgId){
        FileDownloadDto downloadedImg = this.fileService.download(imgId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MimeTypeUtils.parseMimeType(downloadedImg.getContentType())));
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+downloadedImg.getName());
        headers.setContentLength(downloadedImg.getDocument().length);

        return new HttpEntity<>(downloadedImg.getDocument(), headers);
    }

    @PreAuthorize("@itemServiceImpl.isOwner(#id, #principal)")
    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails principal){
        this.itemService.deleteOffer(id);
        return "redirect:/";
    }

    @PostMapping("/buy/{itemId}")
    public String buyItem(@PathVariable Long itemId,
                          @AuthenticationPrincipal UserDetails principal){
       this.itemService.buyItem(itemId, principal.getUsername());
        return "redirect:/";
    }
}
