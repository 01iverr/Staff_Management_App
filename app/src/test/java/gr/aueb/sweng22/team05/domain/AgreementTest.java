package gr.aueb.sweng22.team05.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

public class AgreementTest {
    Agreement agreement;

    @Before
    public void setUp() {
        agreement = new Agreement();
    }

    @Test
    public void getIdEmpty() {
        Assert.assertEquals(0L,agreement.getId());
    }

    @Test
    public void getHireDateEmpty() {
        Assert.assertNull(agreement.getHireDate());
    }

    @Test
    public void getStartDateEmpty() {
        Assert.assertNull(agreement.getStartDate());
    }

    @Test
    public void getEndDateEmpty() {
        Assert.assertNull(agreement.getEndDate());
    }

    @Test
    public void getAgrTypeEmpty() {
        Assert.assertNull(agreement.getAgrType());
    }

    @Test
    public void getActiveFalseEmpty() {
        Assert.assertNull(agreement.getActive());
    }

    @Test
    public void getActiveTrueEmpty() {
        Assert.assertNull(agreement.getActive());
    }

    @Test
    public void setId() {
        agreement.setId(5L);
        Assert.assertEquals(5L, agreement.getId());
    }

    @Test
    public void setHireDate() {
        LocalDate today = LocalDate.of(2017, 1, 13);
        agreement.setHireDate(today);
        Assert.assertEquals(LocalDate.of(2017, 1, 13), agreement.getHireDate());
    }

    @Test
    public void setStartDate() {
        LocalDate today = LocalDate.of(2018, 2, 15);
        agreement.setStartDate(today);
        Assert.assertEquals(LocalDate.of(2018, 2, 15), agreement.getStartDate());
    }

    @Test
    public void setEndDate() {
        LocalDate today = LocalDate.of(2019, 3, 2);
        agreement.setEndDate(today);
        Assert.assertEquals(today, agreement.getEndDate());
    }

    @Test
    public void setAgrType() {
        AgreementType agrT = new AgreementType(5L, "newAgr",48.5f, typeOfAgr.PARTTIME, emplType.PAIDBYSALARY, 20, 10);
        agreement.setAgrType(agrT);
        Assert.assertEquals(agrT, agreement.getAgrType());
    }

    @Test
    public void setActiveFalse() {
        agreement.setActive(false);
        Assert.assertEquals(false,agreement.getActive());
    }

    @Test
    public void setActiveTrue() {
        agreement.setActive(true);
        Assert.assertEquals(true,agreement.getActive());
    }

}