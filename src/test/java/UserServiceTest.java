import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {
    UserDAO mockUserDAO = Mockito.mock(UserDAO.class);

    UserService userService = new UserService(mockUserDAO);

    @Test
    public void getRoleByIdZero() {
        Assertions.assertNull(userService.getUserById(0));
    }

    @Test
    public void getRoleByIdNegativeInt(){
        Assertions.assertNull(userService.getUserById(-3));
    }

    @Test
    public void getRoleByIdPositiveInt() {
        User user = new User(4, "Ash", "Ketchum");

        Mockito.when(mockUserDAO.getUserById(4)).thenReturn(user);
        Assertions.assertEquals(user, userService.getUserById(4));
    }
}
