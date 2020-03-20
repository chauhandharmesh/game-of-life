package com.ptc.plms.gol.oops;

import java.util.HashSet;
import java.util.Set;

import com.ptc.plms.gol.oops.helper.RuleRunnerHelper;

public class RuleRunner {

	private GameStratedy gameStratedy;
	private RuleRunnerHelper ruleRunnerHelper;

	public RuleRunner(GameStratedy gameStratedy, RuleRunnerHelper ruleRunnerHelper) {
		this.gameStratedy = gameStratedy;
		this.ruleRunnerHelper = ruleRunnerHelper;
	}

	public Set<Cell> applyRules(Set<Cell> liveCells) {
		HashSet<Cell> nextGeneration = new HashSet<>();

		liveCells.stream().forEach(cellFromCurrentGeneration -> {
			ruleRunnerHelper.processCell(cellFromCurrentGeneration, liveCells, nextGeneration);

			gameStratedy.findNeighbours(cellFromCurrentGeneration, liveCells).stream().forEach(
					neighbouringCell -> ruleRunnerHelper.processCell(neighbouringCell, liveCells, nextGeneration));

		});
		return ruleRunnerHelper.filterDead(nextGeneration);
	}

}
