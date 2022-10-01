package gr.aueb.sweng22.team05.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AgreementTypeTest {
    AgreementType agrT;

    @Before
    public void setUp() {
        agrT = new AgreementType();
    }

    @Test
    public void getIdEmpty() {
        Assert.assertEquals(0L, agrT.getId());
    }

    @Test
    public void getNameEmpty() {
        Assert.assertNull(agrT.getName());
    }

    @Test
    public void getSalaryEmpty() {
        Assert.assertEquals(0.0f, agrT.getSalary(),0.00001);
    }

    @Test
    public void getRestDaysEmpty() {
        Assert.assertEquals(0, agrT.getRestDays());
    }

    @Test
    public void getSickDaysEmpty() {
        Assert.assertEquals(0, agrT.getSickDays());
    }

    @Test
    public void getAgrTypeEmpty() {
        Assert.assertNull(agrT.getAgrType());
    }

    @Test
    public void getEmTypeEmpty() {
        Assert.assertNull(agrT.getEmType());
    }

    @Test
    public void setId() {
        agrT.setId(6L);
        Assert.assertEquals(6L, agrT.getId());
    }

    @Test
    public void setName() {
        agrT.setName("Glam");
        Assert.assertEquals("Glam", agrT.getName());
    }

    @Test
    public void setSalary() {
        agrT.setSalary(1200.5f);
        Assert.assertEquals(1200.5f, agrT.getSalary(), 0.00001);
    }

    @Test
    public void setRestDays() {
        agrT.setRestDays(5);
        Assert.assertEquals(5, agrT.getRestDays());
    }

    @Test
    public void setSickDays() {
        agrT.setSickDays(7);
        Assert.assertEquals(7, agrT.getSickDays());
    }

    @Test
    public void setAgrType() {
        agrT.setAgrType(typeOfAgr.FULLTIME);
        Assert.assertEquals(typeOfAgr.FULLTIME, agrT.getAgrType());
    }

    @Test
    public void setEmType() {
        agrT.setEmType(emplType.PAIDBYWAGE);
        Assert.assertEquals(emplType.PAIDBYWAGE, agrT.getEmType());
    }

    @Test
    public void getTypehours() {
        agrT.setAgrType(typeOfAgr.FULLTIME);
        Assert.assertEquals(8, agrT.getTypehours());
    }
}