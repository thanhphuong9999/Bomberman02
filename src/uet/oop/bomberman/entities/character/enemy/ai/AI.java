package uet.oop.bomberman.entities.character.enemy.ai;

import java.util.Random;

public abstract class AI {
	
	protected Random random = new Random();

	/**
	 * Thu?t toán tìm ???ng ?i
	 * @return h??ng ?i xu?ng/ph?i/trái/lên t??ng ?ng v?i các giá tr? 0/1/2/3
	 */
	public abstract int calculateDirection();
}
