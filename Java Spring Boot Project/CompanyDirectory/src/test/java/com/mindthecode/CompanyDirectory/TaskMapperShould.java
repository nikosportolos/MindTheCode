package com.mindthecode.CompanyDirectory;

import com.mindthecode.CompanyDirectory.common.Enums;
import com.mindthecode.CompanyDirectory.models.entities.Task;
import com.mindthecode.CompanyDirectory.models.responses.TaskResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaskMapperShould {

    private TaskMapper mapper;
    private Task taskInput;
    private TaskResponse expectedOutput;

    @Before()
    public void setup(){
        mapper = new TaskMapper();
        taskInput = new Task("Refactor","Refactoring some entities",1,2,3, Enums.TaskStatus.NEW);
        taskInput.setId(1);
        expectedOutput = new TaskResponse(1,"Refactor", "Refactoring some entities",Enums.TaskDifficulty.MEDIUM, Enums.TaskStatus.NEW);
    }

    @Test
    public void mapTaskToResponse(){
        TaskResponse output = mapper.mapTaskToResponse(taskInput);
        Assert.assertThat(expectedOutput, Matchers.samePropertyValuesAs(output));
    }
}
