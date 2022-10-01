package gr.aueb.sweng22.team05.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {
    Department department;

    @Before
    public void setUp() {
        department = new Department();
    }

    @Test
    public void getIdEmpty() {
        Assert.assertEquals(0L, department.getId());
    }

    @Test
    public void getNameEmpty() {
        Assert.assertNull(department.getName());
    }

    @Test
    public void setId() {
        department.setId(18L);
        Assert.assertEquals(18L, department.getId());
    }

    @Test
    public void setName() {
        department.setName("Business");
        Assert.assertEquals("Business", department.getName());
    }

    @Test
    public void testToString() {
        department.setId(135L);
        department.setName("Costumer Care");
        Assert.assertEquals("Department (135): Costumer Care", department.toString());
    }
}