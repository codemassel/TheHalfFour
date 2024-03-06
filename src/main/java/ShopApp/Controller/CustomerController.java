package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Model.ShopItem;
import ShopApp.Repository.CitiesRepository;
import ShopApp.Repository.CustomerRepository;
import ShopApp.Repository.ShopItemRepository;
import ShopApp.Service.CitiesService;
import ShopApp.Service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Transactional
@RequestMapping("/index")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final ShopItemRepository shopItemRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    public CustomerController(CustomerRepository customerRepository, ShopItemRepository shopItemRepository, CitiesRepository citiesRepository, CustomerService customerService,
                              CitiesService citiesService, PasswordEncoder passwordEncoder, HttpSession httpSession){
        this.customerRepository = customerRepository;
        this.shopItemRepository = shopItemRepository;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
    }

    /**
     * Gets every Customer
     * Endpoint: ../index/customers
     * @param model
     * @return String customersTest
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(Model model) {

        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customersTest";
    }

    /**
     * Created User and posts it, password gets decrypted in the corresponding service class
     * Endpoint: ../index/register
     * @param model
     * @param customer
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute Customer customer) {

        customerService.createCustomer(customer);

        return "redirect:/index";
    }

    /**
     * Opens index.html if the endpoint gets requested
     * Endpoint: ../index
     * @param model
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String returnIndex(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.getAttribute("loggedInUser");
        List<Customer> customers = customerService.getCustomers();
        List<ShopItem> shopItems = shopItemRepository.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("shopItems", shopItems);
        model.addAttribute("customer", new Customer());
        return "index";
    }

    /**
     * Method for the login
     * @return
     */
    /*
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public String login(Model model, @ModelAttribute Customer customer) {

        Customer existingCustomer = customerRepository.findByEmailId(customer.getEmailId());
        if (existingCustomer != null && passwordEncoder.matches(customer.getPassword(), existingCustomer.getPassword())) {
            return "redirect:/index";
        } else {
            return "redirect:/login?error";
        }
    }*/

    /**
     * Gets the customer for corresponding params id and hands it to thymeleaf
     * Endpoint: /index/customer
     * @param customerId
     * @param model
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/customer")
    public ResponseEntity<Optional<Customer>> getCustomerById(@RequestParam(name = "customerId") Long customerId, Model model) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        model.addAttribute("customer", customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getCustomerById/{id}")
    public ModelAndView getCustomerByIdJs(Model model, @PathVariable Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        model.addAttribute("customer", foundCustomer);
        return new ModelAndView("redirect:/adminpanel");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateCustomer")
    public ModelAndView updateUser(Model model, @ModelAttribute Customer customer) {
        customerService.updateCustomer(customer);

        return new ModelAndView("redirect:/adminpanel");
    }

    /**
     * Diese Methode kann aus unerklärlichen Gründen nicht vom Frontend aufgerufen werden.
     * Der direkte manuelle Call bspw. durch einen direkten apicall ist möglich.
     * @param email
     * @param password
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView checkifLoginDataCorrect(HttpServletRequest request, @RequestParam("emaillogin") String email, @RequestParam("passwordlogin") String password) {
        Customer customers = customerRepository.findByEmailId(email);
        if (customers != null && passwordEncoder.matches(password, customers.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", customers.getEmailId());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/index");
            return new ModelAndView("redirect:/index");
        } else {
            return new ModelAndView("redirect:/index");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/customerByFirstName")
    public List<Customer> getCustomersByName(@RequestParam (name="firstName") String firstName, @RequestParam (name = "lastName") String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/agb")
    public ModelAndView getAgb() {
        return new ModelAndView("agb");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/registerpage")
    public ModelAndView getRegisterpage(Model model) {

        model.addAttribute("customer", new Customer());
        return new ModelAndView("registerpage");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/impressum")
    public ModelAndView getImpressum() {
        return new ModelAndView("impressum");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/datenschutz")
    public ModelAndView getDatenschutz() {
        return new ModelAndView("datenschutz");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/productpage")
    public ModelAndView getProduktpage(Model model) {
        List<ShopItem> shopItems = shopItemRepository.findAll();
        model.addAttribute("shopItems", shopItems);

        return new ModelAndView("productpage2");
    }
}


