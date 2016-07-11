package com.hbs.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.spy;


/**
 * Created by jyo on 07/07/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(HBSUtils.class)
public class HBSAppTest {

    @Test(expected = Exception.class)
    public void mainMethodShouldThrowExceptionForNullArg() throws Exception {
        HBSApp.main(null);
    }

    @Test(expected = Exception.class)
    public void mainMethodShouldThrowExceptionForTwoArguments() throws Exception {
        HBSApp.main(new String[]{"file1.txt", "file2.txt"});
    }

    @Test
    public void mainMethodWithOneArgumentShouldWork() throws Exception {
        String[] args = new String[]{"file1.txt"};

        spy(HBSUtils.class);
        doNothing().when(HBSUtils.class, "printHierarchicalView", args[0]);

        HBSApp.main(new String[]{"file1.txt"});
        PowerMockito.verifyStatic();
    }
}
