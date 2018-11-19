package uet.oop.bomberman.entities.character.enemy.ai;

import java.util.Random;

public abstract class AI {
	
	protected Random random = new Random();

	/**
	 * Thu?t to�n t�m ???ng ?i
	 * @return h??ng ?i xu?ng/ph?i/tr�i/l�n t??ng ?ng v?i c�c gi� tr? 0/1/2/3
	 */
	public abstract int calculateDirection();
}
