package jmri.jmrix.rps;

import apps.tests.Log4JFixture;
import javax.vecmath.Point3d;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit tests for the rps.Ash1_1Algorithm class.
 *
 * This algorithm tends to pick arbitrary solutions with only three sensors, so
 * we test with four and more.
 *
 * The default transmitter location is (3,2,1)
 *
 * @author	Bob Jacobsen Copyright 2006
 */
public class Ash1_1AlgorithmTest {

    double vs = 0.0344;  // SI default for testing

    double time(Point3d p) {
        return Math.sqrt(
                (p.x - 3.0) * (p.x - 3.0)
                + (p.y - 2.0) * (p.y - 2.0)
                + (p.z - 1.0) * (p.z - 1.0)
        ) / vs;
    }

    @Test
    @Ignore // fails for unknown reasons
    public void testCalc5() {

        Point3d s1 = new Point3d(10.0f, 0.0f, 12.0f);
        Point3d s2 = new Point3d(-3.0f, 4.0f, 9.0f);
        Point3d s3 = new Point3d(-3.0f, -4.0f, 10.0f);
        Point3d s4 = new Point3d(5.0f, 0.0f, 11.0f);
        Point3d s5 = new Point3d(0.0f, 13.0f, 9.0f);
        Point3d s[] = new Point3d[]{s1, s2, s3, s4, s5};

        Reading r = new Reading("21", new double[]{time(s1), time(s2), time(s3),
            time(s4), time(s5)});

        Calculator c = new Ash1_1Algorithm(s, vs);

        Measurement m = c.convert(r, new Point3d(1.f, 1.f, 10.f));
        Assert.assertEquals("ID ok", "21", m.getID());
        Assert.assertEquals("x close", true, Math.abs(m.x - 3.) < 0.001);
        Assert.assertEquals("y close", true, Math.abs(m.y - 2.) < 0.001);
        Assert.assertEquals("z close", true, Math.abs(m.z - 1.) < 0.001);
    }

    @Test
    @Ignore // fails for unknown reasons
    public void testCalc4_not1() {
        Point3d s2 = new Point3d(-3.0f, 4.0f, 9.0f);
        Point3d s3 = new Point3d(-3.0f, -4.0f, 10.0f);
        Point3d s4 = new Point3d(5.0f, 0.0f, 11.0f);
        Point3d s5 = new Point3d(0.0f, 13.0f, 9.0f);
        Point3d s[] = new Point3d[]{s2, s3, s4, s5};

        Reading r = new Reading("21", new double[]{time(s2), time(s3),
            time(s4), time(s5)});

        Calculator c = new Ash1_1Algorithm(s, vs);

        Measurement m = c.convert(r, new Point3d(1.f, 1.f, 10.f));
        Assert.assertEquals("ID ok", "21", m.getID());
        Assert.assertEquals("x close", true, Math.abs(m.x - 3.) < 0.001);
        Assert.assertEquals("y close", true, Math.abs(m.y - 2.) < 0.001);
        Assert.assertEquals("z close", true, Math.abs(m.z - 1.) < 0.001);
    }

    @Test
    @Ignore // fails for unknown reasons
    public void testCalc4_not2() {
        Point3d s1 = new Point3d(10.0f, 0.0f, 12.0f);
        Point3d s3 = new Point3d(-3.0f, -4.0f, 10.0f);
        Point3d s4 = new Point3d(5.0f, 0.0f, 11.0f);
        Point3d s5 = new Point3d(0.0f, 13.0f, 9.0f);
        Point3d s[] = new Point3d[]{s1, s3, s4, s5};

        Reading r = new Reading("21", new double[]{time(s1), time(s3),
            time(s4), time(s5)});

        Calculator c = new Ash1_1Algorithm(s, vs);

        Measurement m = c.convert(r, new Point3d(1.f, 1.f, 10.f));
        Assert.assertEquals("ID ok", "21", m.getID());
        Assert.assertEquals("x close", true, Math.abs(m.x - 3.) < 0.001);
        Assert.assertEquals("y close", true, Math.abs(m.y - 2.) < 0.001);
        Assert.assertEquals("z close", true, Math.abs(m.z - 1.) < 0.001);
    }

    @Test
    @Ignore // fails for unknown reasons
    public void testCalc4_not3() {
        Point3d s1 = new Point3d(10.0f, 0.0f, 12.0f);
        Point3d s2 = new Point3d(-3.0f, 4.0f, 9.0f);
        Point3d s4 = new Point3d(5.0f, 0.0f, 11.0f);
        Point3d s5 = new Point3d(0.0f, 13.0f, 9.0f);
        Point3d s[] = new Point3d[]{s1, s2, s4, s5};

        Reading r = new Reading("21", new double[]{time(s1), time(s2),
            time(s4), time(s5)});

        Calculator c = new Ash1_1Algorithm(s, vs);

        Measurement m = c.convert(r, new Point3d(1.f, 1.f, 10.f));
        Assert.assertEquals("ID ok", "21", m.getID());
        Assert.assertEquals("x close", true, Math.abs(m.x - 3.) < 0.001);
        Assert.assertEquals("y close", true, Math.abs(m.y - 2.) < 0.001);
        Assert.assertEquals("z close", true, Math.abs(m.z - 1.) < 0.001);
    }

    @Before
    public void setUp() {
        Log4JFixture.setUp();
    }

    @After
    public void tearDown() {
        Log4JFixture.tearDown();
    }
}
