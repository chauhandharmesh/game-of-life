package com.ptc.plms.gol.oops.helper;

import java.util.Iterator;
import java.util.Set;

import com.ptc.plms.gol.oops.Cell;
import com.ptc.plms.gol.oops.GameStratedy;
import com.ptc.plms.gol.oops.State;
import com.ptc.plms.gol.oops.rules.Rule;

public class RuleRunnerHelperImpl implements RuleRunnerHelper {

	private GameStratedy gameStratedy;

	public RuleRunnerHelperImpl(GameStratedy gameStratedy) {
		this.gameStratedy = gameStratedy;
	}

	@Override
	public Set<Cell> filterDead(Set<Cell> nextGeneration) {
		Iterator<Cell> iterator = nextGeneration.iterator();

		while (iterator.hasNext()) {
			if (State.DEAD.equals(iterator.next().getState())) {
				iterator.remove();
			}
		}

		return nextGeneration;
	}

	@Override
	public void processCell(Cell cell, Set<Cell> currentGeneration, Set<Cell> nextGeneration) {
		if (nextGeneration.contains(cell))
			return; // already processed

		cell = cell.createCopy();

		State nextState = cell.getState();
		for (Rule rule : gameStratedy.getRules()) {
			nextState = rule.nextState(cell.getState(), findLiveNeighbourCount(cell, currentGeneration));

			if (!cell.getState().equals(nextState)) {
				break;
			}
		}

		cell.setState(nextState);
		nextGeneration.add(cell);
	}

	@Override
	public int findLiveNeighbourCount(Cell cell, Set<Cell> liveCells) {
		int count = 0;

		count = (int) gameStratedy.findNeighbours(cell, liveCells).stream()
				.filter(cell1 -> State.LIVE.equals(cell1.getState())).count();

		return count;
	}

}
