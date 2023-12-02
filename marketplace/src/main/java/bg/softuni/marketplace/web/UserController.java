package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.ShowItemDto;
import bg.softuni.marketplace.model.dto.UserViewDto;
import bg.softuni.marketplace.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/details/{id}")
    public String userDetails(@PathVariable Long id, Model model){
        UserViewDto userViewDto = this.userService.userView(id);
        model.addAttribute("userInfo", userViewDto);
        return "user-view";
    }

    @GetMapping("/show")
    public String showItems(@AuthenticationPrincipal UserDetails principal,
                            Model model){
        UserEntity user = this.userService.findByUsername(principal.getUsername());
        List<ShowItemDto> itemsForSale = user.getItemsForSale()
                .stream()
                .map(i -> this.mapper.map(i, ShowItemDto.class).setImgId(i.getImage().getId()))
                .toList();
        List<ShowItemDto> boughtItems = user.getBoughtItemEntities()
                .stream()
                .map(i -> this.mapper.map(i, ShowItemDto.class).setImgId(i.getImage().getId()))
                .toList();

        model.addAttribute("saleItems", itemsForSale);
        model.addAttribute("boughtItems", boughtItems);

        return "user-items";
    }
}
