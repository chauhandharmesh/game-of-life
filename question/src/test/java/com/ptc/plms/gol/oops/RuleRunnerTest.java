package com.ptc.plms.gol.oops;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ptc.plms.gol.oops.helper.RuleRunnerHelper;

import org.junit.Assert;

public class RuleRunnerTest {

	RuleRunner ruleRunner = null;
	GameStratedy gameStratedy = null;
	RuleRunnerHelper runnerHelper = null;
	Set<Cell> cellSet = new HashSet<>();

	@Before
	public void init() {
		 gameStratedy = Mockito.mock(GameStratedy.class);
		 runnerHelper = Mockito.mock(RuleRunnerHelper.class);
		ruleRunner = new RuleRunner(gameStratedy, runnerHelper);
		cellSet.add(new Cell(10, 20));
	}

	@Test
	public void testApplyRulesScenario_1() {
		
		  Mockito.doNothing().when(runnerHelper).processCell(Mockito.anyObject(),
		  Mockito.anySet(), Mockito.anySet());
		 

		Mockito.when(gameStratedy.findNeighbours(Mockito.anyObject(), Mockito.anySet()))
				.thenReturn(new HashSet<Cell>());
		Mockito.when(runnerHelper.filterDead((HashSet<Cell>) Mockito.anySetOf(Cell.class))).thenReturn(cellSet);
        
		Assert.assertNotNull(ruleRunner.applyRules(cellSet));
	}

}
