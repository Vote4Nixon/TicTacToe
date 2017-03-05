package com.ttt.game.strategy;

import com.ttt.game.model.Square;

public abstract class Strategy {
	public abstract Square findMove();
	public abstract void update(Square s);
	public abstract int maxEvals();
}
