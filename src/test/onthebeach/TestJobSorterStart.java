package test.onthebeach;

import main.onthebeach.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestJobSorterStart {

	@Test
	public void tesCase1() throws Exception {
		String[][] processedUserInput = { { "a" } };
		assertEquals("a", JobSorterStart.jobHandler(processedUserInput));
	}

	@Test
	public void tesCase2() throws Exception {
		String[][] processedUserInput = { { "a" }, { "b" }, { "c" } };
		assertEquals("cba", JobSorterStart.jobHandler(processedUserInput));
	}

	@Test
	public void tesCase3() throws Exception {
		String[][] processedUserInput = { { "a" }, { "b", "c" }, { "c" } };
		assertEquals("cba", JobSorterStart.jobHandler(processedUserInput));
	}

	@Test
	public void tesCase4() throws Exception {
		String[][] processedUserInput = { { "a" }, { "b", "c" }, { "c", "f" }, { "d", "a" }, { "e", "b" }, { "f" } };
		assertEquals("fcbead", JobSorterStart.jobHandler(processedUserInput));
	}

	@Test
	public void tesCase5() {
		String[][] processedUserInput = { { "a" }, { "b" }, { "c", "c" } };
		try {
			JobSorterStart.jobHandler(processedUserInput);
		} catch (Exception e) {
			assertEquals("Jobs can’t depend on themselves", e.getMessage());
		}
	}

	@Test
	public void tesCase6() {
		String[][] processedUserInput = { { "a" }, { "b", "c" }, { "c", "f" }, { "d", "a" }, { "e" }, { "f", "b" } };
		try {
			JobSorterStart.jobHandler(processedUserInput);
		} catch (Exception e) {
			assertEquals("Cyclic dependency", e.getMessage());
		}
	}

}
