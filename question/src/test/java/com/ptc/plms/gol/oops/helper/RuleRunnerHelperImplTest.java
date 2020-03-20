package com.ptc.plms.gol.oops.helper;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ptc.plms.gol.oops.Cell;
import com.ptc.plms.gol.oops.GameStratedy;
import com.ptc.plms.gol.oops.State;
import com.ptc.plms.gol.oops.rules.Rule;
import com.ptc.plms.gol.oops.rules.RuleImpl;

public class RuleRunnerHelperImplTest {

	GameStratedy gameStratedy = null;
	RuleRunnerHelperImpl ruleRunnerHelperImpl = null;

	@Before
	public void init() {
		gameStratedy = Mockito.mock(GameStratedy.class);
		ruleRunnerHelperImpl = new RuleRunnerHelperImpl(gameStratedy);
	}

	@Test
	public void testFilterDead() {
		Set<Cell> nextGeneration = new HashSet<>();
		Cell cell = new Cell(10, 20, State.DEAD);
		nextGeneration.add(cell);
		Set<Cell> cells = ruleRunnerHelperImpl.filterDead(nextGeneration);
		Assert.assertEquals(0, cells.size());
	}

	@Test
	public void testProcessCell_CurrentCellPresent() {
		Set<Cell> nextGeneration = new HashSet<>();
		Set<Cell> currentGeneration = new HashSet<>();

		Cell cell = new Cell(10, 20, State.DEAD);
		nextGeneration.add(cell);
		ruleRunnerHelperImpl.processCell(cell, currentGeneration, nextGeneration);
		Assert.assertEquals(1, nextGeneration.size());
	}

	@Test
	public void testProcessCell_CurrentCellNotPresent() {
		Set<Cell> nextGeneration = new HashSet<>();
		Set<Cell> currentGeneration = new HashSet<>();

		Cell deadCell = new Cell(10, 20, State.DEAD);
		Cell liveCell = new Cell(20, 30, State.LIVE);

		nextGeneration.add(deadCell);

		Mockito.when(gameStratedy.getRules()).thenReturn(new Rule[] { new RuleImpl() });

		ruleRunnerHelperImpl.processCell(liveCell, currentGeneration, nextGeneration);
		Assert.assertEquals(2, nextGeneration.size());
	}

}
