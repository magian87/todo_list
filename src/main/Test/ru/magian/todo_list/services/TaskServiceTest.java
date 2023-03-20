package ru.magian.todo_list.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.magian.todo_list.models.Status;
import ru.magian.todo_list.models.Task;
import ru.magian.todo_list.repositories.TaskRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoException.class)

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void should_find_task_by_id() {
        when(taskRepository.findById(10L)).thenReturn(Optional.of(new Task("kkk", Status.DONE)));

        var actual = taskService.findOne(10L);
        assertNotNull(actual);

        assertTrue(actual.getDescription().equals("kkk"));

        verify(taskRepository, times(1)).findById(10L);

    }

    @Test
    void should_not_find_task_by_id() {
        when(taskRepository.findById(100L)).thenReturn(Optional.empty());

        var actual = taskService.findOne(100L);
        assertNull(actual);

        verify(taskRepository, times(1)).findById(100L);
    }


    @Test
    void should_update_by_id_task() {
        //т.к. в реальный репозиторий макито, как я понимаю не лезет, даже по строчке taskService.findOne(10L);
        //говорю что если вызвана функция ниже, то возвращаю описание preUpdate
        when(taskRepository.findById(10L)).thenReturn(Optional.of(new Task("preUpdate", Status.IN_PROGRESS)));

        var actual = taskService.findOne(10L);
        assertNotNull(actual);

        assertTrue(actual.getDescription().equals("preUpdate"));
        verify(taskRepository, times(1)).findById(10L);

        actual.setDescription("AfterUpdate");
        actual.setStatus(Status.DONE);
        taskService.update(10L,actual);

        verify(taskRepository, times(1)).save(actual);
        assertEquals("AfterUpdate", taskService.findOne(10L).getDescription());
        assertEquals(Status.DONE, taskService.findOne(10L).getStatus());

    }

    //Тест что вставка проходит успешно
    @Test
    void should_save_task(){

        Task task = new Task("insert", Status.IN_PROGRESS);
        taskService.save(task);

        verify(taskRepository, times(1)).save(task);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task("insert", Status.IN_PROGRESS)));

        assertEquals("insert", taskService.findOne(1L).getDescription());
        assertEquals(Status.IN_PROGRESS,taskService.findOne(1L).getStatus());
    }

    @Test
    void should_delete_by_id(){
        when(taskRepository.findById(10L)).thenReturn(Optional.of(new Task("preDelete", Status.IN_PROGRESS)));

        var actual = taskService.findOne(10L);
        assertNotNull(actual);

        taskService.delete(10L);
        verify(taskRepository, times(1)).deleteById(10L);

        //when(taskRepository.findById(10L)).thenReturn(Optional.empty());

        assertNull(taskService.findOne(10L));
    }

}