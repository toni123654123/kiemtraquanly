package controller;

import model.Category;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;
import service.CategoryService;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> allProvinces() {

        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView showList(Optional<String> s,@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Customer> customers = s.isPresent() ? search(s, pageable) : getPage(pageable);
        modelAndView.addObject("keyword", s.orElse(null));
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView showInformation(@PathVariable Long id) throws Exception {
        try {
            ModelAndView modelAndView = new ModelAndView("info");
            Customer customer = null;
            customer = customerService.findOne(id);
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/customers");
        }
    }

    @PostMapping
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        customerService.delete(customer);
        return "redirect:/customers";
    }

//    @PostMapping
//    public String deleteCustomer(Customer customer){
//        customerService.delete(customer);
//        return "redirect:/customers";
//    }


//    @GetMapping("/create")
//    public ModelAndView create() {
//        ModelAndView modelAndView = new ModelAndView("create");
//        modelAndView.addObject("book", new Customer());
//        return modelAndView;
//    }
//    @PostMapping("/create")
//    public ModelAndView create(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            ModelAndView modelAndView = new ModelAndView("/create");
//            return modelAndView;
//        }
//        CategoryService.save(customer);
//        ModelAndView modelAndView = new ModelAndView("/create");
//        modelAndView.addObject("customer", new Customer());
//        modelAndView.addObject("message", "create success");
//        return modelAndView;
//    }

    private Page<Customer> getPage(Pageable pageInfo) {
        return customerService.findAll(pageInfo);
    }

    private Page<Customer> search(Optional<String> s, Pageable pageInfo) {
        return customerService.search(s.get(), pageInfo);
    }


}
