package eu.riasol.games.untangle.client;

import com.google.gwt.core.client.EntryPoint;

public class GwtUntangleModule implements EntryPoint {
	@Override
	public void onModuleLoad() {
		GwtUntangle game = new GwtUntangle("GwtUntangleContainer", 500, 400);
		LevelCreator lCreator = new LevelCreator();
		game.setLevel(lCreator.getLevels().get(0));
	}
}
