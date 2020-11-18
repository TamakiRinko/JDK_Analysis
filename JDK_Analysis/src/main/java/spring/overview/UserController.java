package spring.overview;

public class UserController {
    @Autowired_
    private UserService userService;
    // 不用注入
    private Integer integer;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
