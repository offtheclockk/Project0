import com.revature.daos.TaskDAO;
import com.revature.models.Task;
import com.revature.services.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TaskServiceTest {
    TaskDAO mockTaskDAO = Mockito.mock(TaskDAO.class);
    TaskService taskService = new TaskService(mockTaskDAO);

    @Test
    public void getTaskByIdZero() {
        Assertions.assertNull(taskService.getTaskById(0));
    }

    @Test
    public void getTaskByIdNegativeInt() {
        Assertions.assertNull(taskService.getTaskById(-3));
    }

    @Test
    public void getTaskByPositiveInt() {
        Task task = new Task("Eat food", "Eat before you die", false, 1);

        Mockito.when(mockTaskDAO.getTaskById(3)).thenReturn(task);
        Assertions.assertEquals(task, taskService.getTaskById(3));
    }
}
