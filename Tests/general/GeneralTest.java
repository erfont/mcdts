package general;

import junit.framework.Assert;

import org.junit.Test;

import algorithm.Mcdts;

public class GeneralTest {

    @Test
    public void test() {
        Mcdts algorithm = new Mcdts();
        Assert.assertTrue( algorithm.execute() );
    }

}
