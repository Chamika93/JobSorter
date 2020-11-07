package test.onthebeach;

import main.onthebeach.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestJobSorterStart {

    @Test
    public void testLucky() {
        assertEquals(7, JobSorterStart.getLucky());
    }
	
}
