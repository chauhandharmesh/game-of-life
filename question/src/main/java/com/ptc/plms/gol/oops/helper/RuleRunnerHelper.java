package com.ptc.plms.gol.oops.helper;

import java.util.Set;

import com.ptc.plms.gol.oops.Cell;

public interface RuleRunnerHelper {

	int findLiveNeighbourCount(Cell cell, Set<Cell> liveCells);

	void processCell(Cell cell, Set<Cell> currentGeneration, Set<Cell> nextGeneration);

	Set<Cell> filterDead(Set<Cell> nextGeneration);

}
