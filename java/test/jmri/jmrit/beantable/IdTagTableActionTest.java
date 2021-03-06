package jmri.jmrit.beantable;

import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Paul Bender Copyright (C) 2017	
 */
public class IdTagTableActionTest extends AbstractTableActionBase {

    @Test
    public void testCTor() {
        Assert.assertNotNull("exists",a);
    }

    @Override
    public String getTableFrameName(){
        return Bundle.getMessage("TitleIdTagTable");
    }

    @Override
    @Test
    public void testGetClassDescription(){
         Assert.assertEquals("IdTag Table Action class description","ID Tag Table",a.getClassDescription());
    }

    /**
     * Check the return value of includeAddButton.  The table generated by 
     * this action includes an Add Button.
     */
    @Override
    @Test
    public void testIncludeAddButton(){
         Assert.assertTrue("Default include add button",a.includeAddButton());
    }

    // The minimal setup for log4J
    @Before
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        jmri.util.JUnitUtil.resetProfileManager();
        jmri.util.JUnitUtil.initShutDownManager();
        helpTarget = "package.jmri.jmrit.beantable.IdTagTable"; 
        a = new IdTagTableAction();
    }

    @After
    @Override
    public void tearDown() {
        JUnitUtil.tearDown();
        a = null;
    }

    // private final static Logger log = LoggerFactory.getLogger(IdTagTableActionTest.class);

}
